package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchBean {

	//Logic Bean の役割
	//search beanではデータベースを呼ぶ作業
	//db接続
	//サーブレットでとりだす

	//field
	public ArrayList<Integer> listcd = new ArrayList<Integer>();//Arraylistの定義
	public ArrayList<String> listnm = new ArrayList<String>();
	public ArrayList<Integer> listpr = new ArrayList<Integer>();

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

	public ProductBean execute(int catid){//ログイン処理するメソッド

		ProductBean pbean = new ProductBean();//ProductBeanの内容を呼ぶ
		//int cid= Integer.parseInt(catid);
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

			String query = "select*from product;";
			System.out.println(query);//ここまできてる

			//pst = cnct.prepareStatement(query);
			//pst.setString(1, catid);//入力されたものをデータベースに入れる
			rs= st.executeQuery(query);
			//rs=st.executeQuery(query);

//catid が 0 のとき(catid == 0)
			if(catid == 0) {
			while(rs.next()) {
				//rs.getInt("pro_cd");//getterメソッド・・・現在行の各フィールドの値を取得する
				listcd.add(rs.getInt("pro_cd"));//arraylistに入れる

				listnm.add(rs.getString("pro_name"));

				listpr.add(rs.getInt("pro_price"));
				}
			}else {//(catid != 0), 1,2,3,4,,,,,

				while(rs.next()) {//rsがなくなるまで　下の処理を繰り返す

				listcd.add(rs.getInt("pro_cd"));//arraylistに入れる

				listnm.add(rs.getString("pro_name"));

				listpr.add(rs.getInt("pro_price"));
				}
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
		return pbean;//この内容をpbeanにて使用可能にする
	}


}
