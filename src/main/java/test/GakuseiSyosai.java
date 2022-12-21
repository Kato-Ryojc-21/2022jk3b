package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GakuseiDataBean;
import dao.GakuseiDAO;


@WebServlet("/syosai")
public class GakuseiSyosai extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GakuseiSyosai() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		GakuseiDataBean bean = null;
		boolean errflag = false;

		// --- 送信されたIDを受け取る 存在しなければdiplayallに戻る
		String strId = request.getParameter("id");
		if (strId == null || strId == "") {
			errflag = true;
		} else {
			// --- データベースから該当するデータを取得する なければdisplayallに戻る
			GakuseiDAO dao = new GakuseiDAO();
			bean = dao.getOneRec(strId);
			if (bean == null) {
				errflag = true;
			}
		}

		// --- エラーがあればdisplayallに戻る
		if (errflag) {
			response.sendRedirect("displayall");
		} else {
			// --- 更新用フォームを呼び出す
			request.setAttribute("data", bean);
			request.getRequestDispatcher("Syosai.jsp").forward(request, response);
		}
	}

}