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
		DetailBean db = new DetailBean();
		db = dblist.get(0);

		System.out.println(db.getProname());

		SearchJdbc sjdbc = new SearchJdbc();

		DetailBean dt= new DetailBean();
		ArrayList<DetailBean> dlist = new ArrayList<DetailBean>();

		if(session != null) {

			CalcRsBean crb = new  CalcRsBean();
			//			 double price = crb.getPrice();
			//System.out.println(price+"呼んできたpriceの表示");

			ArrayList<CalcRsBean> addcart = new ArrayList<CalcRsBean>();//入れ物用意した
			addcart = (ArrayList<CalcRsBean>) session.getAttribute("calc");//list の呼び出し
			System.out.println(addcart.get(0));



			session.setAttribute("quantity", quant);
			System.out.println("数量:"+quant+"の表示しようとしてる");//ここまでok
			//			RequestDispatcher rd = request.getRequestDispatcher("jsp/cart.jsp");
			//			rd.forward(request, response);//can jump to cart jsp ok

			if(nxact.equals("戻る")) {//戻るであれば
				RequestDispatcher rd3 = request.getRequestDispatcher("/jsp/search.jsp");
				rd3.forward(request, response);
				System.out.println(nxact+"と.equals()の中身[戻る]が等しければsearchjspに飛ぶはず");

			}else {//カートへであれば
				RequestDispatcher rd2 = request.getRequestDispatcher("jsp/cart.jsp");
				rd2.forward(request, response);//can jump to cart jsp ok

				if(addcart == null) {
					addcart.add(quant);
					addcart.addAll(sjdbc.gocart(procd));//リストにaddして
					System.out.println(addcart.get(0));
					session.setAttribute("addcart", addcart);//sessionに送った
				}

			}//if addcart end



		}else {

			session.invalidate();

			RequestDispatcher rd4 = request.getRequestDispatcher("jsp/login.jsp");
			rd4.forward(request, response);

		}
	}
}



