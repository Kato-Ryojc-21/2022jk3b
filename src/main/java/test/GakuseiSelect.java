package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/select")
public class GakuseiSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GakuseiSelect() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String proc = request.getParameter("submit");
		if (proc != null) {
			if (proc.equals("insert")) {
				response.sendRedirect("Insert.html");
				return;
			}else if (proc.equals("update")) {
				request.getRequestDispatcher("update").forward(request, response);
				return;
			} else if(proc.equals("searchk")) {
				request.getRequestDispatcher("displayall").forward(request, response);
				return;
			}else if (proc.equals("insert")) {
				response.sendRedirect("Insert.html");
				return;
			}else if(proc.equals("searchh")) {
				request.getRequestDispatcher("displayall").forward(request, response);
				return;
			} else if(proc.equals("syosai")) {
				request.getRequestDispatcher("syosai").forward(request, response);
				return;
			}
		}
		response.sendRedirect("displayall");
	}
}
