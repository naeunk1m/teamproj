package com.cinemaw.member.db;

import java.sql.*;
import java.util.ArrayList;

public class MemberDAO2 {

	// DAO (Data Access Object) : 정보처리객체
	// => DB에 관한 모든정보를 처리하는 객체

	// 공통으로 사용되는 객체를 선언
	Connection con = null;			// 디비연결정보를 저장,처리
	PreparedStatement pstmt = null; // SQL 쿼리 실행,처리
	ResultSet rs = null;			// select 구문 결과 저장
	String sql = "";				// sql 쿼리 저장

	// 디비 연결메서드
	private Connection getCon() throws Exception {
		// 디비 연결정보
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String DBURL = "jdbc:mysql://itwillbs8.cafe24.com:3306/itwillbs8";
		String DBID = "itwillbs8";
		String DBPW = "itwillbs8030909";

		// 1. 드라이버로드
		Class.forName(DRIVER);
		System.out.println(" 드라이버로드 성공 ");
		// 2. 디비연결
		con = DriverManager.getConnection(DBURL, DBID, DBPW);
		System.out.println(" 디비연결 성공 ");
		System.out.println(" con : " + con);

		return con;
	}

	// 디비 자원해제
	public void closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public MemberDAO2() {

		String DBURL = "jdbc:mysql://itwillbs8.cafe24.com:3306/itwillbs8";
		String DBID = "itwillbs8";
		String DBPW = "itwillbs8030909";

		try {
			// jdbc드라이버로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// connection 얻어오기
			con = DriverManager.getConnection(DBURL, DBID, DBPW);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int loginMember(MemberVo vo) {
		// 1 - 로그인, 0 - 비밀번호 오류, -1 - 아이디오류
		int result = -1;

		try {
			// 1.2. 디비연결
			con = getCon();

			// 3. SQL 작성(select) & pstmt 객체
			String sql = "select u_pw from user_master where u_id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			// ???
			pstmt.setString(1, vo.getU_id());

			// 4. SQL 실행
			ResultSet rs = pstmt.executeQuery();

			// 5. 데이터처리
			if (rs.next()) {
				// 아이디 있음, 회원
				if (vo.getU_pw().equals(rs.getString("u_pw"))) {
					// 아이디 있음,비밀번호 동일 , 본인
					// => 로그인 성공
					result = 1;
				} else {
					// 아이디 있음,비밀번호 다름, 비밀번호오류
					result = 0;
				}
			} else {
				// 아이디 없음,비회원
				result = -1;
			}

			System.out.println(" DAO : 로그인 체크 완료 (" + result + ")");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	}

}