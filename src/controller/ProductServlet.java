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
int count = 0;
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
				if (session == null) {
					session.invalidate();

					RequestDispatcher rd4 = request.getRequestDispatcher("jsp/login.jsp");
					rd4.forward(request, response);
					return;

				}else {

					CateBean ct = new CateBean();
					ArrayList<CateBean> acl = (ArrayList<CateBean>)session.getAttribute("cate");
					ct = acl.get(0);
					session.setAttribute("cate", acl);


					sjdbc.goproduct(procd);//使用するメソッドを呼ぶ
					//DetailBean db = new DetailBean();
					//リストの呼び出し
					ArrayList<DetailBean> dp = sjdbc.goproduct(procd);

					ArrayList<CartBean> cartadd = new ArrayList<CartBean>();
					System.out.println(count);
					if(count < 0){

					//.gocart(procd);
					CartBean crb = new CartBean();
					cartadd =(ArrayList<CartBean>)session.getAttribute("cartadd");
					}
					count++;//sessionのカウントを1増やす
					DetailBean dbean = new DetailBean();//リストの要素を呼ぶbean
					dbean = dp.get(0);//0番目の要素
					int Price = dbean.getProprice();

					//カート画面に必要なデータをリストに格納する
					CartBean cb = new CartBean();
					cb.setCd(dbean.getProcd());
					cb.setName(dbean.getProname());
					cb.setPrice(Price);//要素を入れる
					cb.setQuantity(quantity);
					crb2 = cartadd.get(0);
					System.out.print(crb2.getName()+crb2.getQuantity()+crb2.getPrice()+"出力１が出てる");
					session.setAttribute("cartadd", cartadd);

					session.setAttribute("detail", dp);
					RequestDispatcher rd = request.getRequestDispatcher("jsp/product.jsp");
					rd.forward(request, response);



					System.out.println("ProductServlet終了");


				}




	}//end doPost

}//end class
