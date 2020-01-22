package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getParameter("nextpage");

		HttpSession session = request.getSession(false);

		if (session != null) {

			if(action.equals("はい")) {
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/logout.jsp");
			rd.forward(request, response);
			System.out.println(action+"と.equals()の中身[はい]が等しければlogoutjspに飛ぶはず");

			//カートの中身のsessionの破棄
			//commit and rollback


		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/search.jsp");
			rd.forward(request, response);
			System.out.println(action+"と.equals()の中身[いいえ]が等しければsearchjspに飛ぶはず");
		}


		}

	}

}
