package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(false);

		if (session ==null) {

			//action if its session is not countinue
			session.invalidate();

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");//back to login page
			rd.forward(request, response);
			return;
//
		}//else so, action when the session is still countinueing
//		//when session still keeps, display table set

		SearchBean search = new SearchBean();
		search.getString(a);



		//when click detail, jump to product.jsp
//		RequestDispatcher rd1 = request.getRequestDispatcher("/jsp/product.jsp");//back to login page
//		rd1.forward(request, response);

		//表を出す
		//その中にデータを反映


	}

}
