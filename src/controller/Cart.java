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
	int count = 0;
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


			//リストの呼び出し
			ArrayList<DetailBean> dp = sjdbc.goproduct(procd);

			ArrayList<CartBean> cartadd = new ArrayList<CartBean>();
			System.out.println("countは"+count);
			if(count >=1){

			//.gocart(procd);
			CartBean crb = new CartBean();
			cartadd =(ArrayList<CartBean>)session.getAttribute("cartadd");
			}
			count++;//sessionのカウントを1増やす
			DetailBean dbean = new DetailBean();//リストの要素を呼ぶbean
			dbean = dp.get(0);//0番目の要素
			int Price = Integer.parseInt(dbean.getProprice());

			//カート画面に必要なデータをリストに格納する
			CartBean cb = new CartBean();
			cb.setCd(dbean.getProcd());
			cb.setName(dbean.getProname());
			cb.setPrice(Price);//要素を入れる
			cb.setQuantity(quant);

			cartadd.add(cb);

			int total=0;

			for (int i = 0; i <cartadd.size(); i++) {
				CartBean CBcalc = new CartBean();
				CBcalc = cartadd.get(i);

				int calcPrice = CBcalc.getPrice();
				int calcQuant = Integer.parseInt(CBcalc.getQuantity());
				int priceA = calcPrice * calcQuant;
				total = priceA + total;
			}

			int tax = (int)(total*0.1);
			//System.out.print(crb2.getName()+crb2.getQuantity()+crb2.getPrice()+"出力１が出てる");
			session.setAttribute("cartadd", cartadd);
			session.setAttribute("total", total);
			session.setAttribute("tax",tax);

			int totalprice = total + tax;
			session.setAttribute("totalprice", totalprice);






//			//getParameterでもらった数量をカートに送る
//			session.setAttribute("quantity", quant);
//			System.out.println("数量:"+quant+"の表示しようとしてる");//ここまでok
//			//						RequestDispatcher rd = request.getRequestDispatcher("jsp/cart.jsp");
//			//						rd.forward(request, response);//can jump to cart jsp ok

			if(nxact.equals("戻る")) {//戻るであれば
				RequestDispatcher rd3 = request.getRequestDispatcher("/jsp/search.jsp");
				rd3.forward(request, response);
				System.out.println(nxact+"と.equals()の中身[戻る]が等しければsearchjspに飛ぶはず");

			}else {//カートへであれば
				RequestDispatcher rd2 = request.getRequestDispatcher("jsp/cart.jsp");
				rd2.forward(request, response);//can jump to cart jsp ok


			}//if addcart end



		}
	}
}



