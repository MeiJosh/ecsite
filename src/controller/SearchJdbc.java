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
		ArrayList<CateBean> listcategory = new ArrayList<CateBean>();
		try {
			// JDBCドライバ(MYSQL用)を登録する
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBCドライバの登録終了");
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

			ArrayList<String> a = new ArrayList<String>();

			while(rs.next()) {//行が終わるまでループ
				//使うクラスをインスタンス化
				CateBean ctbean = new CateBean();
				//データを取り出すrs.get***("***");
				//データを入れるset****();
				//取り出したデータをbeanにいれる
				//bean変数.set***(rs.get***("***"));
				ctbean.setCatid(rs.getInt("cat_id"));
				ctbean.setCatname(rs.getString("cat_name"));
//
			a.add(rs.getString(2));
//

				//リストに入れる
				//リスト変数名.add(使用するbean)
				listcategory.add(ctbean);
				System.out.println(listcategory);
			}
//			System.out.println(a.get(0));
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
		return listcategory;
	}//ArrayList<CateBean>を使う作業終了

	public ArrayList<ProductBean> keysearch (String keywd){//keyword search
		ArrayList<ProductBean> listproduct= new ArrayList<ProductBean>();
		try {
			// JDBCドライバ(MYSQL用)を登録する
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBCドライバの登録終了");
			// 引数（url,id,pass）を元に、実際にDBに接続する。
			// connの代入結果としては、
			//接続が成功したか失敗したかの結果が格納される。
			cnct = DriverManager.getConnection(url,id,passwd);
			System.out.println("DBMSとの接続完了");//ここまできてる

			st = cnct.createStatement();

			String query = "select*from product where pro_name = ?;";//データベース呼んだ
			System.out.println(query);//ここまできてる

			pst = cnct.prepareStatement(query);
			pst.setString(1, keywd);//入力されたものをデータベースに入れる
			rs= pst.executeQuery();

			while(rs.next()) {
				ProductBean pbean = new ProductBean();//ProductBeanの内容を呼ぶ
				//
				pbean.setProname(rs.getString("pro_name"));
				pbean.setProprice(rs.getInt("pro_price"));
				pbean.setProcd(rs.getInt("pro_cd"));
				listproduct.add(pbean);
			}
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

		return listproduct;
	}
		public ArrayList<ProductBean> bothsearch(String keywd, String catid){//both search
			ArrayList<ProductBean> listproduct = new ArrayList<ProductBean>();

			try {
				// JDBCドライバ(MYSQL用)を登録する
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("JDBCドライバの登録終了");
				// 引数（url,id,pass）を元に、実際にDBに接続する。
				// connの代入結果としては、
				//接続が成功したか失敗したかの結果が格納される。
				cnct = DriverManager.getConnection(url,id,passwd);
				System.out.println("DBMSとの接続完了");//ここまできてる

			//	st = cnct.createStatement();

				String query = "select*from product where pro_name = ? and cat_id = ?;";//データベース呼んだ
				System.out.println(query);//ここまできてる

				pst = cnct.prepareStatement(query);
				pst.setString(1, keywd);
				pst.setString(2, catid);//入力されたものをデータベースに入れる
				rs= pst.executeQuery();

				while(rs.next()) {
					ProductBean pbean = new ProductBean();//ProductBeanの内容を呼ぶ
					//
					pbean.setProname(rs.getString("pro_name"));
					pbean.setProprice(rs.getInt("pro_price"));
					pbean.setProcd(rs.getInt("pro_cd"));
					listproduct.add(pbean);
				}
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

			return listproduct;

		}


	public ArrayList<ProductBean> execute(String catid){//カテゴリー検索結果
		ArrayList<ProductBean> listproduct = new ArrayList<ProductBean>();

		try {
			// JDBCドライバ(MYSQL用)を登録する
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBCドライバの登録終了");
			// 引数（url,id,pass）を元に、実際にDBに接続する。
			// connの代入結果としては、
			//接続が成功したか失敗したかの結果が格納される。
			cnct = DriverManager.getConnection(url,id,passwd);
			System.out.println("DBMSとの接続完了");//ここまできてる

			st = cnct.createStatement();

			String query = "select*from product where cat_id =?;";//データベース呼んだ
			System.out.println(query);//ここまできてる

			pst = cnct.prepareStatement(query);
			pst.setString(1, catid);//入力されたものをデータベースに入れる
			rs= pst.executeQuery();
			//rs=st.executeQuery(query);

			//catid が 0 のとき(catid == 0)//

			while(rs.next()) {
				ProductBean pbean = new ProductBean();//ProductBeanの内容を呼ぶ
				//
				pbean.setProname(rs.getString("pro_name"));
				pbean.setProprice(rs.getInt("pro_price"));
				pbean.setProcd(rs.getInt("pro_cd"));
				listproduct.add(pbean);
				//きてる

				//getterメソッド・・・現在行の各フィールドの値を取得する
			}

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
		return listproduct;
	}//カテゴリー検索を終了

}
