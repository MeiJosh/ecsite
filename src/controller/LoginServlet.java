package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//送信されてくるパラメータのエンコーディングを指定
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");


				//初期 value
				String initUserName = "goka";
				String initPassWord = "goka0214";
				System.out.println(initUserName);
				System.out.println(initPassWord);

				//入力されたnameとpass
				String typedname = request.getParameter("username");
				String typedpass = request.getParameter("password");
				System.out.println(typedname);
				System.out.println(typedpass);



				//セッションの開始
				HttpSession session = request.getSession(true);


				//入力されたnameとpassが初期化パラメータと等しいか検証
				if(typedname.equals(initUserName) && typedpass.equals(initPassWord)) {
					System.out.println("ok");

					//等しければ
					//action below==ok, login successfully
					//jump to search.jsp
					//session.setAttribute("loginUser", true);
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/search.jsp");
					rd.forward(request, response);

					//stop right here when action is activate
					return;

				}else if ((!typedname.equals(initUserName) && !typedpass.equals(initPassWord))) {
				//===when session does not begin, request to show data

				//put data(error msg)
				request.setAttribute("errormsg", "名前またはパスワードが一致しません");//msg

				//back to login.jsp
				//and show error massage
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
				rd.forward(request, response);
				return;

				}else {
				request.setAttribute("noentry", "名前またはパスワードを入力してください");//noentry message
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
				rd.forward(request, response);
				}


	}

}
