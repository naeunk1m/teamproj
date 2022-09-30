package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {

	private static DBConnection instance = new DBConnection();

	private DBConnection() {
	}

	public static DBConnection getInstance() {
		return instance;
	}

	public Connection getCon() {
		try {

			// jdbc드라이버 로딩
			String DRIVER = "com.mysql.cj.jdbc.Driver";
			String DBURL = "jdbc:mysql://itwillbs8.cafe24.com:3306/itwillbs8";
			String DBID = "itwillbs8";
			String DBPW = "itwillbs8030909";
			// connection 얻어오기
			Class.forName(DRIVER);
			return DriverManager.getConnection(DBURL, DBID, DBPW);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}