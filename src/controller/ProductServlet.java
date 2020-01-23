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
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//SearchJdbc sjdbc = new SearchJdbc();
//
//		ProductBean pbean= new ProductBean();
//		ArrayList<ProductBean> pd = sjdbc.execute(catid);


		String procd = request.getParameter("productcode");

		//requestでpro_cdを受け取る
		//System.out.println(procd+"を受け取った");

		//もしセッションが続いていれば!=null
				HttpSession session = request.getSession(false);

			//	ArrayList<String> kr = new ArrayList<String>();

				SearchJdbc sjdbc = new SearchJdbc();


			//	System.out.println("jdbc 呼び出した");

				//ProductBean pb = new ProductBean();
				if (session != null) {

					CateBean ct = new CateBean();
					ArrayList<CateBean> acl = (ArrayList<CateBean>)session.getAttribute("cate");
					ct = acl.get(0);
					session.setAttribute("cate", acl);


					sjdbc.goproduct(procd);//使用するメソッドを呼ぶ
					//DetailBean db = new DetailBean();
					//リストの呼び出し
					ArrayList<DetailBean> dp = sjdbc.goproduct(procd);
			//		System.out.println("リスト呼んでます");

//					db=dp.get(0);
//					System.out.println(db.getMessage()+"sending list from ProductServlet ");

					//send the list to session
					//session.setAttribute("detail", dp);
					session.setAttribute("detail", dp);
					RequestDispatcher rd = request.getRequestDispatcher("jsp/product.jsp");
					rd.forward(request, response);

//					ArrayList<CalcRsBean> goca = sjdbc.gocart(procd);
//					System.out.println(goca +"を出したい send from cart servlet");
//					session.setAttribute("calc", goca);//ok


					System.out.println("ProductServlet終了");


				}




	}//end doPost

}//end class
