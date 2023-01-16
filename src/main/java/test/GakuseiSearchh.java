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

@WebServlet("/searchh")
public class GakuseiSearchh extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GakuseiSearchh() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		try {
			String keyword = request.getParameter("keywordh");
			List<GakuseiDataBean> b = new ArrayList<GakuseiDataBean>();
			GakuseiDAO sb = new GakuseiDAO();
			b = sb.getData(keyword);
			
			for (GakuseiDataBean x : b) {
				response.getWriter().print(x.getId() + "," + x.getGakusei_name() + "," + x.getGakusei_nameH() + "<br>");
			}

		} catch (Exception e) {
			response.getWriter().print(e.getMessage());
		}
	}

}
