package controller;

import java.io.IOException;
import java.util.ArrayList;

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

				//入力されたnameとpassを受け取る
				String typedname = request.getParameter("username");
				String typedpass = request.getParameter("password");

//LoginBean で処理中

//servletでしたいこと
				//loginbeanでデータベースから呼んできたlogin_cdとpwを初期値に設定
				//入力されたものデータベースの内容がただしいか
				//正しければ検索ページに飛ぶ
				//正しくなければエラー文表示

if(typedname==null) {
	request.setAttribute("noentry", "名前またはパスワードを入力してください");//noentry message
	RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
	rd.forward(request, response);
}
				//ということは、loginbeanではデータベースを呼ぶ作業-loginbean

				//servletではbean(data)をインスタンス化
				LoginBean lbean = new LoginBean();

				//beanのデータを入力されたものと比べる
				DataBean data =lbean.execute(typedname, typedpass);

				//入力されたnameとpassが初期化パラメータと等しいか検証
				if(typedname.equals(data.getLogincd()) && typedpass.equals(data.getLoginpw())) {

					//セッションの開始
					HttpSession session = request.getSession(true);
					System.out.println("ok");

					//等しければ
					//action below==ok, login successfully
					//jump to search.jsp
					//session.setAttribute("loginUser", true);
					CateBean ctbean = new CateBean();
					//ArrayList<CateBean> listcategory = new ArrayList<CateBean>();
					//getしたcat_idをsearch におくる
					SearchJdbc sjdbc = new SearchJdbc();;//search bean を使用できるようにした
					sjdbc.catematch(session);
					ArrayList<CateBean> b = (ArrayList<CateBean>)session.getAttribute("cate");

//					b.get(1);
//					b.get(2);
					System.out.println(	b.get(0));


					session.setAttribute("cate", b );

					//System.out.println(listcategory);
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/search.jsp");
					rd.forward(request, response);

					//stop right here when action is activate
					return;

//				}else{(!typedname.equals(data.toString()) && !typedpass.equals(data.toString() {
				//===when session does not begin, request to show data

				//put data(error msg)
				}else{
					request.setAttribute("errormsg", "名前またはパスワードが一致しません");//msg

				//back to login.jsp
				//and show error massage
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
				rd.forward(request, response);
				return;
				}
//				}else {
//				request.setAttribute("noentry", "名前またはパスワードを入力してください");//noentry message
//				RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
//				rd.forward(request, response);
//				}


	}

}
