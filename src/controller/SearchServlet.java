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
	//	int cid = Integer.parseInt(catid);
		//String detail = request.getParameter("detail");
		String keywd = request.getParameter("keyword");//検索フォームを受け取る


		//もしセッションが続いていれば!=null
		HttpSession session = request.getSession(false);

		if(session != null) {
			//セッションがあれば
			 CateBean ctbean = new CateBean();

			    ArrayList<CateBean> a = (ArrayList<CateBean>)session.getAttribute("cate");

			ArrayList<String> pd = new ArrayList<String>();

			 if (keywd.equals("")) {// カテゴリー検索された場合
//					listproduct = sjdbc.execute(catid);
					//if 2-1
					if(catid.equals("選択してください")) {
						request.setAttribute("error", "検索してね");
						RequestDispatcher rd = request.getRequestDispatcher("jsp/search.jsp");
						rd.forward(request, response);
					}
					//if 2-2//カテゴリ選択したら　表を持ったresultjspに飛ぶ

					//今後はカテゴリ選択したもののみを表示する
					//if 2-2-1
						// if (catid on search jsp == cat_id on sql)であればその商品を表示
//
				SearchJdbc sjdbc = new SearchJdbc();//search bean を使用できるようにした

				sjdbc.catematch(session);//カテゴリー検索プルダウン
				CateBean ct = new CateBean();
				ArrayList<CateBean> acl = (ArrayList<CateBean>)session.getAttribute("cate");
				ct = acl.get(0);
			//	System.out.println(ct.getCatid()+"I am at Servlet for getting catid");
				//カテゴリー検索プルダウン
				session.setAttribute("catid", acl);


//カテゴリー検索商品
					sjdbc.execute(catid);//catidがあればexecuteメソッド開始
				//	ProductBean bn = new ProductBean();//bean 呼んだ
					//カテゴリー検索で検索された商品の情報リストをよんだ
					ArrayList<ProductBean> pdl = sjdbc.execute(catid);
					//ArrayList<ProductBean> c = (ArrayList<ProductBean>)session.getAttribute("product");
					//bean = list.get(index);
					//bn = c.get(0);

					//System.out.println(bn.getProname()+ "i am at Servlet");

					session.setAttribute("pdt", pdl);//検索された商品の情報リストをsessionに送る
					RequestDispatcher rd = request.getRequestDispatcher("jsp/searchresult.jsp");
					rd.forward(request, response);
//カテゴリー検索商品
					System.out.println("カテゴリー検索終了");
				}







//			//if 1
//			if (keywd.equals("") && catid.equals("")) {//どちらも入力されない場合
//
//				request.setAttribute("error", "検索してね");
//				RequestDispatcher rd = request.getRequestDispatcher("jsp/searchresult.jsp");
//				rd.forward(request, response);
//				return;
//
//				//if 2
//			} else if (keywd.equals("")) {// カテゴリー検索された場合
//				listproduct = sjdbc.execute(catid);
//				//if 2-1
//				if(listproduct.size()<1) {
//					request.setAttribute("error", "検索してね");
//				}
//				//if 2-2
//				session.setAttribute("product", listproduct);
//				RequestDispatcher rd = request.getRequestDispatcher("jsp/searchresult.jsp");
//				rd.forward(request, response);
//
//				//if 3
//			} else if (catid.equals("")){//キーワード検索された場合
//
//				listproduct = sjdbc.keysearch(keywd);
//
//				//if 3-1
//				if(listproduct.size()<1) {
//					request.setAttribute("error", "検索してね");
//
//				}
//				//if 3-2
//				session.setAttribute("product", listproduct);
//				RequestDispatcher rd = request.getRequestDispatcher("jsp/searchresult.jsp");
//				rd.forward(request, response);
//
//				//if 4
//			} else if (!(keywd.equals("")) && !(catid.equals(""))){//両方検索された場合
//				listproduct = sjdbc.bothsearch(keywd, catid);
//
//				//if 4-1
//				if(listproduct.size()<1) {
//					request.setAttribute("error", "検索してね");
//				}//if 4-2
//				session.setAttribute("product", listproduct);
//				RequestDispatcher rd = request.getRequestDispatcher("jsp/searchresult.jsp");
//				rd.forward(request, response);
//
//				//if 5
//			}else if(detail != null) {
//
//				ProductBean pbean = new ProductBean();
//				CateBean ctbean = new CateBean();
//
//				//商品コードをもとに商品データを全て持ってくる
//				listproduct = sjdbc.execute(catid);//where is syosai coming from in Ans
//
//				//カテゴリー名取ってくる
//				ctbean = listcategory.get(0);
//				//
//
//						//商品紹介画面でひつよう
//						session.setAttribute("product", listproduct);
//						session.setAttribute("catname", listcategory);
//
//						//jump to 商品紹介
//						RequestDispatcher rd = request.getRequestDispatcher("/jsp/product.jsp");//back to login page
//						rd.forward(request, response);
//
//			//if 6 emergency
//		} else {
//
//			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");//back to login page
//			rd.forward(request, response);
//			}
//
//
//		}else {//sessionがなければ
//
//
//			session.invalidate();
//
//			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");//back to login page
//			rd.forward(request, response);
//			//
//		}

	}
	}
}
