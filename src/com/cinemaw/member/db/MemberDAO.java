package com.cinemaw.member.db;

import java.sql.*;

public class MemberDAO {

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	int count = 0;

	public void MemberInsert(MemberVo vo) {

		try {
			String DRIVER = "com.mysql.cj.jdbc.Driver";
			String DBURL = "jdbc:mysql://itwillbs8.cafe24.com:3306/itwillbs8";
			String DBID = "itwillbs8";
			String DBPW = "itwillbs8030909";

			// 드라이버 로드
			Class.forName(DRIVER);
			con = DriverManager.getConnection(DBURL, DBID, DBPW);

			// Sql작성
			String sql = "insert into user_master(u_id,u_pw,u_nm,u_join_dt,birth,email,"
					+ "phone,snstype,snsid,favorit,mailing) " + "values(?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);

			// ?????
			pstmt.setString(1, vo.getU_id());
			pstmt.setString(2, vo.getU_pw());
			pstmt.setString(3, vo.getU_nm());
			pstmt.setTimestamp(4, vo.getU_join_dt());
			pstmt.setString(5, vo.getBirth());
			pstmt.setString(6, vo.getEmail());
			pstmt.setString(7, vo.getPhone());
			pstmt.setString(8, vo.getSnstype());
			pstmt.setString(9, vo.getSnsid());
			pstmt.setString(10, vo.getFavorit());
			pstmt.setString(11, vo.getMailing());

			count = pstmt.executeUpdate();

			if (count > 0) {
				System.out.println(" 자원해제 ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 자원 해제
				con.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
