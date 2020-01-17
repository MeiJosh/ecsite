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

		String catid = request.getParameter("category");//requestでセレクトを受け取る
		int cid = Integer.parseInt(catid);
		String detail = request.getParameter("detail");

		ArrayList<ProductBean> listproduct = new ArrayList<ProductBean>();
		SearchJdbc sjdbc = new SearchJdbc();;//search bean を使用できるようにした
		ArrayList<CateBean> listcategory = new ArrayList<CateBean>();


		String keywd = request.getParameter("keyword");//検索フォームを受け取る
//
//		request.setAttribute("catname", listcategory);
//
//		RequestDispatcher rd = request.getRequestDispatcher("jsp/search.jsp");
//		rd.forward(request, response);

		//もしセッションが続いていれば!=null
		HttpSession session = request.getSession(false);
		if(session != null) {
			//セッションがあれば



			//if 1
			if (keywd.equals("") && catid.equals("")) {//どちらも入力されない場合

				request.setAttribute("error", "検索してね");
				RequestDispatcher rd = request.getRequestDispatcher("jsp/searchresult.jsp");
				rd.forward(request, response);
				return;

				//if 2
			} else if (keywd.equals("")) {// カテゴリー検索された場合
				listproduct = sjdbc.execute(catid);
				//if 2-1
				if(listproduct.size()<1) {
					request.setAttribute("error", "検索してね");
				}
				//if 2-2
				request.setAttribute("product", listproduct);
				RequestDispatcher rd = request.getRequestDispatcher("jsp/searchresult.jsp");
				rd.forward(request, response);

				//if 3
			} else if (catid.equals("")){//キーワード検索された場合

				listproduct = sjdbc.keysearch(keywd);

				//if 3-1
				if(listproduct.size()<1) {
					request.setAttribute("error", "検索してね");

				}
				//if 3-2
				request.setAttribute("product", listproduct);
				RequestDispatcher rd = request.getRequestDispatcher("jsp/searchresult.jsp");
				rd.forward(request, response);

				//if 4
			} else if (!(keywd.equals("")) && !(catid.equals(""))){//両方検索された場合
				listproduct = sjdbc.bothsearch(keywd, catid);

				//if 4-1
				if(listproduct.size()<1) {
					request.setAttribute("error", "検索してね");
				}//if 4-2
				request.setAttribute("product", listproduct);
				RequestDispatcher rd = request.getRequestDispatcher("jsp/searchresult.jsp");
				rd.forward(request, response);

				//if 5
			}else if(detail != null) {

				ProductBean pbean = new ProductBean();
				CateBean ctbean = new CateBean();

				//商品コードをもとに商品データを全て持ってくる
				listproduct = sjdbc.execute(catid);//where is syosai coming from in Ans

				//カテゴリー名取ってくる
				ctbean = listcategory.get(0);
				//

						//商品紹介画面でひつよう
						session.setAttribute("product", listproduct);
						request.setAttribute("catname", listcategory);

						//jump to 商品紹介
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/product.jsp");//back to login page
						rd.forward(request, response);

			//if 6 emergency
		} else {

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");//back to login page
			rd.forward(request, response);
			}


		}else {//sessionがなければ


			session.invalidate();

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");//back to login page
			rd.forward(request, response);
			//
		}

	}

}
