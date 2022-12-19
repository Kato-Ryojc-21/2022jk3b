package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GakuseiDataBean;
import dao.GakuseiDAO;

@WebServlet("/displayall")
public class Gakuseidisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Gakuseidisplay() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//---get送信されるページ番号を取得する　なければ１;
		String strPage =(String) request.getParameter("page");
		int page = 1;
		if(strPage != null) {
			try {
				page = Integer.parseInt(strPage);
			}catch (Exception e) {
				page=1;
			}
		}
		
		String keyword = (String) request.getParameter("keyword");
		if (keyword == null) {
			keyword = "";
		}
		List<GakuseiDataBean> list = new ArrayList<GakuseiDataBean>();
		GakuseiDAO dao = new GakuseiDAO();
		list = dao.getAllData(page, keyword);

		request.setAttribute("data", list);
		// 現在のページを送る
		request.setAttribute("page", page);
		// 総ページ数を送る
		request.setAttribute("allpage", dao.getMaxPage(keyword));
		// キーワードを送る
		request.setAttribute("keyword", keyword);
		// jspに遷移
		request.getRequestDispatcher("displayall.jsp").forward(request, response);
	}

}