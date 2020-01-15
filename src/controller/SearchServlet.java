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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String keywd = request.getParameter("keyword");//検索フォームを受け取る

	//	int cid= Integer.parseInt(catid);


		//もしセッションが続いていれば!=null
		HttpSession session = request.getSession(false);
		if(session != null) {
			//セッションがあれば
			String catid = request.getParameter("category");//requestでセレクトを受け取る
			//search bean に移動

			//servletに戻ってきた
			SearchBean sbean = new SearchBean();//search bean を使用できるようにした
			//sbeanの中のメソッドを使用
			sbean.execute(Integer.parseInt(catid));

			request.setAttribute("productinfo", "trying to send table");//sessionが続いていれば表示

			session.setAttribute("proname", sbean.listnm);
			session.setAttribute("proprice", sbean.listpr);
			session.setAttribute("procd", sbean.listcd);
			RequestDispatcher rd2 = request.getRequestDispatcher("/jsp/search.jsp");
			rd2.forward(request, response);

		}else {//sessionがなければ


			session.invalidate();

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");//back to login page
			rd.forward(request, response);
//
		}//else so, action when the session is still countinueing
//		//when session still keeps, display table set


		//beanのデータを入力されたものと比べる
		//DataBean data =sbean.execute(cid);
//
//		request.setAttribute("productinfo", "trying to send table");//ここまで送れる
//		request.setAttribute("table", pbean );
//		//request.setAttribute("table",);//ここでjspに呼んできたデータを送信
//		RequestDispatcher rd = request.getRequestDispatcher("/jsp/search.jsp");
//		rd.forward(request, response);
////		search.getString(a);



		//when click detail, jump to product.jsp


		//表を出す
		//その中にデータを反映


	}

}
