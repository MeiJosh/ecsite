package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTest1 {
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
	String url = "jdbc:mysql://localhost/companydb";
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


	try {
		// 引数（url,id,pass）を元に、実際にDBに接続する。
		// connの代入結果としては、
		//接続が成功したか失敗したかの結果が格納される。
		cnct = DriverManager.getConnection(url,id,pass);
		System.out.println("DBMSとの接続完了");
		// 接続に成功した場合は、stmtに接続情報を設定する。
		stmt = cnct.createStatement();
		// 接続情報(stmt)を元に""内のSQL文を実行する。
		// 実行結果をrsに格納する。
		// ※rsには実行結果のテーブルが格納されているイメージ
		// MySQL上の実行結果が格納されているイメージでも可。
		//Mysqlのコマンドセレクト文で名前(NAME)と電話番号(TEL)の一覧を表示

		//最初のレコードのカラム番号が5の列のデータを表示してください。
		String query = "SELECT DEPT FROM addrbk WHERE ID =1; ";

//		取得データの一行目を扱いたかったら、
//		必ず初めにnext()メソッドを実行して次の行があるかどうかを判定し、
//		それから一行目のデータを扱う必要がある。

		// SQLを実行
		rs = stmt.executeQuery(query);
		if (rs.next()) {//if文でもし次の行にデータがあるなら←条件。現在地　コラム名の行
			String a = rs.getString("DEPT");//queryを実行した中で該当場所"DEPT"
			System.out.println("DEPT: " + a );//出力する。DEPTで次の行の値をだす
		}


	}catch (SQLException ex) {
		ex.printStackTrace();
	}finally {// finallyはエラーの発生有無にかかわらず実行される。
		try {
			// 各部品はDBの処理が終わったら閉じなければならない。
			// 閉じる順番も大切!!
			// rsをcloseする。
			if (rs!=null) rs.close();
			// stmtをcloseする。
			if (stmt!=null) stmt.close();
			// connectionをcloseする。
			if (cnct!=null) cnct.close();
		}catch(Exception ex) {}
	}
}
}