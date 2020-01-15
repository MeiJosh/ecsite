package controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginBean implements Serializable {

	//Logic Bean の役割
	//loginbeanではデータベースを呼ぶ作業
	//db接続
	//databeanに入れる
	//サーブレットでとりだす

	//field variable
		private final String url = "jdbc:mysql://localhost/ec";// 接続する場所を定義（URLとして)
		private final 	String id = "root";// 接続する際のIDを定義
		private final	String passwd = "password";// 接続するIDのpasswordを定義

		// DBにアクセスする、した際に必要な部品を定義
		// DBに接続する際に使用する部品。
		//(接続失敗時にSQLExceptionを投げる）
		Connection cnct=null;
		// SQLを実行する際に使う部品。
		PreparedStatement pst=null;

		// SQLの実行結果を格納する箱
		ResultSet rs= null;

		public DataBean execute(String name, String pass){//ログイン処理するメソッド

			DataBean data = new DataBean();//DataBeanの内容を呼ぶ

		try {
			// JDBCドライバ(MYSQL用)を登録する
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBCドライバの登録終了");
			// 引数（url,id,pass）を元に、実際にDBに接続する。
			// connの代入結果としては、
			//接続が成功したか失敗したかの結果が格納される。
			cnct = DriverManager.getConnection(url,id,passwd);
			System.out.println("DBMSとの接続完了");//ここまできてる

			//queryにsql内のコマンドを入れる
			String query = "select * from user where login_cd = ? and login_pw = ?;";
			System.out.println(query);//ここまできてる

			//プリコンパイル
//			– 同じ構成のSQL文で、値の内容だけを変えたものを繰
//			りかえし実行するための方法
//			– SQLを渡し、解析する手順が１度で済むので、繰りかえ
//			し実行する場合に効率が良い


			//pstにqueryの内容を入れる
			pst = cnct.prepareStatement(query);


			pst.setString(1, name);//入力されたものをデータベースに入れる
			pst.setString(2, pass);//

			//pstを実行する()の中はいらない
			rs=pst.executeQuery();
			//while (table user till end)
			while(rs.next()) {//rsがなくなるまで　下の処理を繰り返す
				data.setLogincd(rs.getString("login_cd"));//データ取り出した

				data.setLoginpw(rs.getString("login_pw"));
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
			if (pst!=null) pst.close();
				if (rs!=null) rs.close();

			// connectionをcloseする。
			if (cnct!=null) cnct.close();
		}catch(Exception ex) {}
	}
		return data;//この内容をdataにて使用可能にする
		}

}//end class
