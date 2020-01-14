package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchBean {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			// JDBCドライバ(MYSQL用)を登録する
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBCドライバの登録終了");
			} catch (ClassNotFoundException ex) {
			// 例外処理
			ex.printStackTrace();
		}

		// DBに接続するために必要な情報を変数定義している。
					// 接続する場所を定義（URLとして)
		String url = "jdbc:mysql://localhost/ec";
					// 接続する際のIDを定義
		String id = "root";
					// 接続する際のパスワードを定義
		String pass = "password";
		// DBにアクセスする、した際に必要な部品を定義
		// DBに接続する際に使用する部品。
		//(接続失敗時にSQLExceptionを投げる）
		Connection cnct=null;
		// SQLを実行する際に使う部品。
		Statement stmt=null;

		// SQLの実行結果を格納する箱
		ResultSet rs= null;
		ResultSet rs2= null;
		ResultSet rs3= null;


		try {
			// 引数（url,id,pass）を元に、実際にDBに接続する。
			// connの代入結果としては、
			//接続が成功したか失敗したかの結果が格納される。
			cnct = DriverManager.getConnection(url,id,pass);
			System.out.println("DBMSとの接続完了");
			// 接続に成功した場合は、stmtに接続情報を設定する。
			stmt = cnct.createStatement();

			String query = "select * from product where cat_id =1;";//家電をセレクトされた場合
			//String query2 ="select * from category where cat_id =2;";//pc parts
//			String query3 ="select * from category where cat_id =3;";//book
			//カテゴリで選択ー検索されたら家電の表を表示したい
			System.out.println(query);
			rs = stmt.executeQuery(query);

			if (rs.next()) {//if文でもし次の行にデータがあるなら←条件。現在地　コラム名の行
				String a = rs.getString("pro_name");//queryを実行した中で該当場所"pro_name"
				String b = rs.getString("pro_price");//queryを実行した中で該当場所"pro_price"
				System.out.println(a);//ここを表示
				System.out.println(b);//ここを表示
				return;
			}


		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {// finallyはエラーの発生有無にかかわらず実行される。
			try {
				// 各部品はDBの処理が終わったら閉じなければならない。
				// 閉じる順番も大切!!
				// rsをcloseする。
				if (rs!=null) rs.close();
				if (rs2!=null) rs2.close();
				if (rs3!=null) rs3.close();
				// stmtをcloseする。
				if (stmt!=null) stmt.close();
				// connectionをcloseする。
				if (cnct!=null) cnct.close();
			}catch(Exception ex) {}
	}
	}
}
