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
 * Servlet implementation class Cart
 */
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//		ProductServlet ps = new ProductServlet();
		//		SearchJdbc sjdbc = new SearchJdbc();
		//		ArrayList<DetailBean> cartadd = ps.cartadd();

		//		String addcartn = request.getParameter("addcartname");
		//		System.out.println(addcartn);
		//		String addcartp = request.getParameter("addcartprice");
		String quant= request.getParameter("quantity");//get request "quantity" from product jsp
		System.out.println("product jsp から"+ quant+"ga Cart Servletにきたとこ");
		String procd = request.getParameter("procd");
		System.out.println(procd+"product jspからhiddenできた ");
		String nxact = request.getParameter("nextpage");

		//if session is keeping or not
		HttpSession session = request.getSession(false);

		ArrayList<DetailBean> dblist = (ArrayList<DetailBean>)session.getAttribute("detail");
		//詳細リスト呼んだ
		DetailBean db = new DetailBean();
		//詳細が入ってるbean呼んだ
		db = dblist.get(0);
		//詳細リストのゼロ番目に入っているものを指定

		System.out.println(db.getProname());
		//ゼロ番目の中の商品名を呼んだはず

		SearchJdbc sjdbc = new SearchJdbc();
		//JDBCアクセスした
		sjdbc.goproduct(procd);
		//JDBCクラスの中にある商品紹介で使うメソッドを呼んだ

		if(session == null) {
			session.invalidate();

			RequestDispatcher rd4 = request.getRequestDispatcher("jsp/login.jsp");
			rd4.forward(request, response);
			return;
		}else{//もしセッションが切れていなければ
			//CalsRsBean cart =

			//			for(int count=0; count<4; count++)

//			////			if(count < 0){
//							ArrayList<CartBean> cartadd = sjdbc.gocart(procd);
//							CartBean crb2 = new Cart();
//							crb2 = cartadd.get(0);
//							System.out.print(crb2.getName()+crb2.getQuantity()+crb2.getPrice()+"出力１が出てる");
//							session.setAttribute("cartadd", cartadd);

			//			}else {
			////				session.getAttribute("cartadd");
			////				ArrayList<CalcRsBean> cartadd = sjdbc.gocart(procd);
			//CalcRsBean crb = new CalcRsBean();//リストを入れたいbean
			////				DetailBean dbean = new DetailBean();//リストの要素を呼ぶbean
			////				dbean.getProname();//product
			////				dbean.getProprice();
			//crb.setQuantity(quant);
			////
			//				cartadd.add(crb);//上3行をリストに入れたい
			//				System.out.print(cartadd.get(0)+"出力2が出てる");//数量はリストに入ってる
			//				session.setAttribute("cartadd", cartadd);
			//}

			//getParameterでもらった数量をカートに送る
			session.setAttribute("quantity", quant);
			System.out.println("数量:"+quant+"の表示しようとしてる");//ここまでok
			//						RequestDispatcher rd = request.getRequestDispatcher("jsp/cart.jsp");
			//						rd.forward(request, response);//can jump to cart jsp ok

			if(nxact.equals("戻る")) {//戻るであれば
				RequestDispatcher rd3 = request.getRequestDispatcher("/jsp/search.jsp");
				rd3.forward(request, response);
				System.out.println(nxact+"と.equals()の中身[戻る]が等しければsearchjspに飛ぶはず");

			}else {//カートへであれば
				RequestDispatcher rd2 = request.getRequestDispatcher("jsp/cart.jsp");
				rd2.forward(request, response);//can jump to cart jsp ok
				//
				//				if(addcart == null) {
				//					addcart.add(quant);
				//					addcart.addAll(sjdbc.gocart(procd));//リストにaddして
				//					System.out.println(addcart.get(0));
				//					session.setAttribute("addcart", addcart);//sessionに送った
				//				}

			}//if addcart end



		}
	}
}



