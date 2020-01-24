package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class SearchJdbc {


	//search beanではデータベースを呼ぶ作業=jdbc
	//db接続
	//サーブレットでとりだす

	//field

	private final String url = "jdbc:mysql://localhost/ec";// 接続する場所を定義（URLとして)
	private final 	String id = "root";// 接続する際のIDを定義
	private final	String passwd = "password";// 接続するIDのpasswordを定義

	// DBにアクセスする、した際に必要な部品を定義
	// DBに接続する際に使用する部品。
	//(接続失敗時にSQLExceptionを投げる）
	Connection cnct=null;
	// SQLを実行する際に使う部品。
	PreparedStatement pst=null;
	Statement st= null;

	// SQLの実行結果を格納する箱
	ResultSet rs= null;

	//アクセス ArrayList<使用するクラス名> メソッド名(){
	public ArrayList<CateBean> catematch(HttpSession session){//カテゴリー名のマッチング
		//ArrayListの宣言
		//ArrayList<使用するクラス名> list変数名 = インスタンス化
		ArrayList<CateBean> a = new ArrayList<CateBean>();
		try {
			// JDBCドライバ(MYSQL用)を登録する
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC1ドライバの登録終了");
			// 引数（url,id,pass）を元に、実際にDBに接続する。
			// connの代入結果としては、
			//接続が成功したか失敗したかの結果が格納される。
			cnct = DriverManager.getConnection(url,id,passwd);
			System.out.println("DBMSとの接続完了");//ここまできてる

			st = cnct.createStatement();

			String query = "select*from category";
			//			pst = cnct.prepareStatement(query);
			//		//入力されたものをデータベースに入れる
			//			rs= pst.executeQuery();

			rs= st.executeQuery(query);



			while(rs.next()) {//行が終わるまでループ
				//使うクラスをインスタンス化
				CateBean ctbean = new CateBean();
				//データを取り出すrs.get***("***");
				//データを入れるset****();
				//取り出したデータをbeanにいれる
				//bean変数.set***(rs.get***("***"));
				ctbean.setCatid(rs.getInt("cat_id"));
		//		System.out.println(ctbean.getCatid());

				ctbean.setCatname(rs.getString("cat_name"));
		//		System.out.println(ctbean.getCatname());
				//
				a.add(ctbean);


			}
			CateBean cb = new CateBean();
			cb = a.get(0);
			System.out.println(cb.getCatid()+"send this from JDBC");
			session.setAttribute("cate", a);
			//			session.

		} catch (ClassNotFoundException e) {
			// 例外処理
			e.printStackTrace();

		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			try {
				// 各部品はDBの処理が終わったら閉じなければならない。
				// 閉じる順番も大切!!
				// rsをcloseする。
				if (st!=null) st.close();
				//if (pst!=null) pst.close();
				if (rs!=null) rs.close();

				// connectionをcloseする。
				if (cnct!=null) cnct.close();
			}catch(Exception ex) {}
		}
		return a ;//listcategory;
	}//ArrayList<CateBean>を使う作業終了


	public ArrayList<ProductBean> execute(String catid){//カテゴリー検索結果
		//ArrayList<ProductBean> listproduct = new ArrayList<ProductBean>();
		ArrayList<ProductBean> pd = new ArrayList<ProductBean>();

		try {
			// JDBCドライバ(MYSQL用)を登録する
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC2ドライバの登録終了");
			// 引数（url,id,pass）を元に、実際にDBに接続する。
			// connの代入結果としては、
			//接続が成功したか失敗したかの結果が格納される。
			cnct = DriverManager.getConnection(url,id,passwd);
			System.out.println("DBMSとの接続完了");//ここまできてる

//			st = cnct.createStatement();
//			String query = "select*from product;";//データベース呼んだ
//			System.out.println(query);//ここまできてる
//			rs= st.executeQuery(query);

			String query = "select*from product where cat_id =?;";
			pst = cnct.prepareStatement(query);
			pst.setString(1, catid);//入力されたものをデータベースに入れる
			rs=pst.executeQuery();



			while(rs.next()) {
				ProductBean pbean = new ProductBean();//ProductBeanの内容を呼ぶ
				//
				pbean.setProname(rs.getString("pro_name"));
			//	System.out.println(rs.getString("pro_name"));

			//	System.out.println(pbean.getProname());
				pbean.setProprice(rs.getInt("pro_price"));
			//	System.out.println(pbean.getProprice());
				pbean.setProcd(rs.getInt("pro_cd"));
			//	System.out.println(pbean.getProcd());


				//きてる

				pd.add(pbean);
				//getterメソッド・・・現在行の各フィールドの値を取得する
			}
			ProductBean p = new ProductBean();//保存された状態のbeanを入れる入れ物を作った
			p=pd.get(0);//pにリストの0番目の要素を入れた kita
			//System.out.println(p.getProname());//出力
			//session.setAttribute("product", pd);


		} catch (ClassNotFoundException e) {
			// 例外処理
			e.printStackTrace();

		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			try {
				// 各部品はDBの処理が終わったら閉じなければならない。
				// 閉じる順番も大切!!
				// rsをcloseする。
				if (st!=null) st.close();
				//if (pst!=null) pst.close();
				if (rs!=null) rs.close();

				// connectionをcloseする。
				if (cnct!=null) cnct.close();
			}catch(Exception ex) {}
		}
		return pd;
	}//カテゴリー検索を終了


public ArrayList<DetailBean> goproduct(String procd){//商品紹介

	ArrayList<DetailBean> gopd = new ArrayList<DetailBean>();
	DetailBean d = new DetailBean();

	try {
		// JDBCドライバ(MYSQL用)を登録する
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("JDBC3ドライバの登録終了");
		// 引数（url,id,pass）を元に、実際にDBに接続する。
		// connの代入結果としては、
		//接続が成功したか失敗したかの結果が格納される。
		cnct = DriverManager.getConnection(url,id,passwd);
		System.out.println("DBMS3との接続完了");//ここまできてる

//		st = cnct.createStatement();
//		String query = "select*from product;";//データベース呼んだ
//		System.out.println(query);
//		rs= st.executeQuery(query);

		String query = "select*from product where pro_cd =?;";
	//	System.out.println(query);//ここまできてる

		pst = cnct.prepareStatement(query);
		pst.setString(1, procd);//入力されたものをデータベースに入れる
		rs=pst.executeQuery();



		while(rs.next()) {
			DetailBean dbean = new DetailBean();//DetailBeanの内容を呼ぶ
			//
			dbean.setProname(rs.getString("pro_name"));
		//	System.out.println(rs.getString("pro_name"));
		//	System.out.println(dbean.getProname());
			dbean.setProprice(rs.getInt("pro_price"));
		//	System.out.println(dbean.getProprice());
			dbean.setProcd(rs.getInt("pro_cd"));
		//	System.out.println(dbean.getProcd());
			dbean.setStockno(rs.getInt("stock_no"));
		//	System.out.println(dbean.getStockno());
			dbean.setImage(rs.getString("pro_img"));
		//	System.out.println(dbean.getImage());
			dbean.setMessage(rs.getString("pro_msg"));
		//	System.out.println(dbean.getMessage());

			gopd.add(dbean);

		}
		DetailBean dp = new DetailBean();//保存された状態のbeanを入れる入れ物を作った
		dp=gopd.get(0);//pにリストの0番目の要素を入れた
	//	System.out.println(dp.getProname());//出力



	} catch (ClassNotFoundException e) {
		// 例外処理
		e.printStackTrace();

	}catch (SQLException ex) {
		ex.printStackTrace();
	}finally {
		try {
			// 各部品はDBの処理が終わったら閉じなければならない。
			// 閉じる順番も大切!!
			// rsをcloseする。
			if (st!=null) st.close();
			//if (pst!=null) pst.close();
			if (rs!=null) rs.close();

			// connectionをcloseする。
			if (cnct!=null) cnct.close();
		}catch(Exception ex) {}
	}
	return gopd;
}//商品紹介

public ArrayList<CartBean> gocart(String procd){//カートの商品名と価格

	ArrayList<CartBean> cartadd = new ArrayList<CartBean>();
	//CalcRsBean calc = new CalcRsBean();

	try {
		// JDBCドライバ(MYSQL用)を登録する
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("JDBC4ドライバの登録終了");
		// 引数（url,id,pass）を元に、実際にDBに接続する。
		// connの代入結果としては、
		//接続が成功したか失敗したかの結果が格納される。
		cnct = DriverManager.getConnection(url,id,passwd);
		System.out.println("DBMS4との接続完了");//ここまできてる

//		st = cnct.createStatement();
//		String query = "select*from product;";//データベース呼んだ
//		System.out.println(query);
//		rs= st.executeQuery(query);

		String query = "select* from product where pro_cd =?;";
		System.out.println(query+"cartのけいさんの処理");//ここまできてる

		pst = cnct.prepareStatement(query);
		pst.setString(1, procd);//入力されたものをデータベースに入れる
		rs=pst.executeQuery();


//
		while(rs.next()) {
//			Cart cartjava = new Cart();
			CartBean crb = new CartBean();//リストを入れたいbean
//			String quant = crb.getQuantity();
//			System.out.println(quant);

			crb.setName(rs.getString("pro_name"));//product
			crb.setPrice(rs.getString("pro_price"));
		//	crb.setQuantity(rs.getString(quant));//問題発生


			cartadd.add(crb);
		}

			CartBean crb2 = new CartBean();
			crb2 = cartadd.get(0);
			System.out.print(crb2.getName()+crb2.getPrice()+"出力１が出てる");
			//session.setAttribute("cartadd", cartadd);

//			CalcRsBean clcbean = new CalcRsBean();//CalcRsBeanの内容を呼ぶ
//
//
//			clcbean.setProname(rs.getString("pro_name"));
//			System.out.println(clcbean.getProname());
//			clcbean.setPrice(rs.getInt("pro_price"));
//			System.out.println(clcbean.getPrice());
//
//			goca.add(clcbean);//add the price data on ArrayList

//		}
//		CalcRsBean cp = new CalcRsBean();//保存された状態のbeanを入れる入れ物を作った
//		cp=goca.get(0);//pにリストの0番目の要素を入れた
//		System.out.println(cp.getProname());//出力
////		.setAttribute("cartadd", cp);



	} catch (ClassNotFoundException e) {
		// 例外処理
		e.printStackTrace();

	}catch (SQLException ex) {
		ex.printStackTrace();
	}finally {
		try {
			// 各部品はDBの処理が終わったら閉じなければならない。
			// 閉じる順番も大切!!
			// rsをcloseする。
			if (st!=null) st.close();
			//if (pst!=null) pst.close();
			if (rs!=null) rs.close();

			// connectionをcloseする。
			if (cnct!=null) cnct.close();
		}catch(Exception ex) {}
	}
	return cartadd ;
}//end カート

}//end class

//	public ArrayList<ProductBean> keysearch (HttpSession session){//keyword search
//		ArrayList<ProductBean> listproduct= new ArrayList<ProductBean>();
//		try {
//			// JDBCドライバ(MYSQL用)を登録する
//			Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("JDBCドライバの登録終了");
//			// 引数（url,id,pass）を元に、実際にDBに接続する。
//			// connの代入結果としては、
//			//接続が成功したか失敗したかの結果が格納される。
//			cnct = DriverManager.getConnection(url,id,passwd);
//			System.out.println("DBMSとの接続完了");//ここまできてる
//
//			st = cnct.createStatement();
//
//			String query = "select*from product;";//データベース呼んだ
//			System.out.println(query);//ここまできてる
////
////			pst = cnct.prepareStatement(query);
////			pst.setString(1, keywd);//入力されたものをデータベースに入れる
//			rs= st.executeQuery(query);
//
//			ArrayList<String> pd = new ArrayList<String>();
//
//			while(rs.next()) {
//				ProductBean pbean = new ProductBean();//ProductBeanの内容を呼ぶ
//				//
//				pbean.setProname(rs.getString("pro_name"));
//				pbean.setProprice(rs.getInt("pro_price"));
//				pbean.setProcd(rs.getInt("pro_cd"));
//				//listproduct.add(pbean);
//
//				pd.add(rs.getString(3));
//
//			}
//
//			System.out.println(pd.get(0));
//			session.setAttribute("product", pd);
//
//		} catch (ClassNotFoundException e) {
//			// 例外処理
//			e.printStackTrace();
//
//		}catch (SQLException ex) {
//			ex.printStackTrace();
//		}finally {
//			try {
//				// 各部品はDBの処理が終わったら閉じなければならない。
//				// 閉じる順番も大切!!
//				// rsをcloseする。
//				if (st!=null) st.close();
//				//if (pst!=null) pst.close();
//				if (rs!=null) rs.close();
//
//				// connectionをcloseする。
//				if (cnct!=null) cnct.close();
//			}catch(Exception ex) {}
//		}
//
//		return listproduct;
//	}
//}
//		public ArrayList<ProductBean> bothsearch(String keywd, String catid){//both search
//			ArrayList<ProductBean> listproduct = new ArrayList<ProductBean>();
//
//			try {
//				// JDBCドライバ(MYSQL用)を登録する
//				Class.forName("com.mysql.jdbc.Driver");
//				System.out.println("JDBCドライバの登録終了");
//				// 引数（url,id,pass）を元に、実際にDBに接続する。
//				// connの代入結果としては、
//				//接続が成功したか失敗したかの結果が格納される。
//				cnct = DriverManager.getConnection(url,id,passwd);
//				System.out.println("DBMSとの接続完了");//ここまできてる
//
//			//	st = cnct.createStatement();
//
//				String query = "select*from product where pro_name = ? and cat_id = ?;";//データベース呼んだ
//				System.out.println(query);//ここまできてる
//
//				pst = cnct.prepareStatement(query);
//				pst.setString(1, keywd);
//				pst.setString(2, catid);//入力されたものをデータベースに入れる
//				rs= pst.executeQuery();
//
//				ArrayList<String> pd = new ArrayList<String>();
//				while(rs.next()) {
//					ProductBean pbean = new ProductBean();//ProductBeanの内容を呼ぶ
//					//
//					pbean.setProname(rs.getString("pro_name"));
//					pbean.setProprice(rs.getInt("pro_price"));
//					pbean.setProcd(rs.getInt("pro_cd"));
//					//listproduct.add(pbean);
//
//					pd.add(rs.getString(3));
//				}
//				System.out.println(pd.get(0));
//				//.setAttribute("product", pd);
//
//
//				} catch (ClassNotFoundException e) {
//					// 例外処理
//					e.printStackTrace();
//
//				}catch (SQLException ex) {
//					ex.printStackTrace();
//				}finally {
//					try {
//						// 各部品はDBの処理が終わったら閉じなければならない。
//						// 閉じる順番も大切!!
//						// rsをcloseする。
//						if (st!=null) st.close();
//						//if (pst!=null) pst.close();
//						if (rs!=null) rs.close();
//
//						// connectionをcloseする。
//						if (cnct!=null) cnct.close();
//					}catch(Exception ex) {}
//				}
//
//			return listproduct;
//
//		}
//
//
//	public ArrayList<ProductBean> execute(HttpSession session){//カテゴリー検索結果
//		ArrayList<ProductBean> listproduct = new ArrayList<ProductBean>();
//
//		try {
//			// JDBCドライバ(MYSQL用)を登録する
//			Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("JDBCドライバの登録終了");
//			// 引数（url,id,pass）を元に、実際にDBに接続する。
//			// connの代入結果としては、
//			//接続が成功したか失敗したかの結果が格納される。
//			cnct = DriverManager.getConnection(url,id,passwd);
//			System.out.println("DBMSとの接続完了");//ここまできてる
//
//			st = cnct.createStatement();
//
//			String query = "select*from product;";//データベース呼んだ
//			System.out.println(query);//ここまできてる
//
////			pst = cnct.prepareStatement(query);
////			pst.setString(1, catid);//入力されたものをデータベースに入れる
//			rs= st.executeQuery(query);
//			//rs=st.executeQuery(query);
//
//			//catid が 0 のとき(catid == 0)//
//
//			ArrayList<String> pd = new ArrayList<String>();
//			while(rs.next()) {
//				ProductBean pbean = new ProductBean();//ProductBeanの内容を呼ぶ
//				//
//				pbean.setProname(rs.getString("pro_name"));
//				pbean.setProprice(rs.getInt("pro_price"));
//				pbean.setProcd(rs.getInt("pro_cd"));
////				listproduct.add(pbean);
//				//きてる
//
//				pd.add(rs.getString(3));
//				//getterメソッド・・・現在行の各フィールドの値を取得する
//			}
//			System.out.println(pd.get(0));
//			session.setAttribute("product", pd);
//
//
//		} catch (ClassNotFoundException e) {
//			// 例外処理
//			e.printStackTrace();
//
//		}catch (SQLException ex) {
//			ex.printStackTrace();
//		}finally {
//			try {
//				// 各部品はDBの処理が終わったら閉じなければならない。
//				// 閉じる順番も大切!!
//				// rsをcloseする。
//				if (st!=null) st.close();
//				//if (pst!=null) pst.close();
//				if (rs!=null) rs.close();
//
//				// connectionをcloseする。
//				if (cnct!=null) cnct.close();
//			}catch(Exception ex) {}
//		}
//		return listproduct;
//	}//カテゴリー検索を終了
//
//	public ArrayList<ProductBean> execute(String catid) {
//		// TODO 自動生成されたメソッド・スタブ
//		return null;
//	}
//
//	public ArrayList<ProductBean> keysearch(String keywd) {
//		// TODO 自動生成されたメソッド・スタブ
//		return null;
//	}
//
//}
