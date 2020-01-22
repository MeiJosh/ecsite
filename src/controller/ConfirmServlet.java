package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ConfirmServlet
 */
public class ConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getParameter("nextpage");//when this param is sent, jump to search jsp
		System.out.println(action+"が来てるはず");

		HttpSession session = request.getSession(false);
		if(session != null) {

			if(action.equals("買い物を続ける")) {

				RequestDispatcher rd = request.getRequestDispatcher("/jsp/search.jsp");
				rd.forward(request, response);
				System.out.println(action+"と.equals()の中身[買い物を続ける]が等しければsearchjspに飛ぶはず");


			}else{

				RequestDispatcher rd2 = request.getRequestDispatcher("/jsp/confirm.jsp");
				rd2.forward(request, response);
				System.out.println(action+"と.equals()の中身[購入]が等しければconfirmjspに飛ぶはず");
			}
		}
	}

}
