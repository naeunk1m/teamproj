package com.cinemaw.member.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO3 {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	public Connection getCon() throws Exception {
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String DBURL = "jdbc:mysql://itwillbs8.cafe24.com:3306/itwillbs8";
		String DBID = "itwillbs8";
		String DBPW = "itwillbs8030909";

		Class.forName(DRIVER);
		con = DriverManager.getConnection(DBURL, DBID, DBPW);

		return con;
	}

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

	// 회원정보조회
	public MemberVo getMember(String u_id) {

		MemberVo vo = null;
		try {
			con = getCon();
			String sql = "select * from user_master where u_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, u_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new MemberVo();
				vo.setU_id(rs.getString("u_id"));
				vo.setU_pw(rs.getString("u_pw"));
				vo.setU_nm(rs.getString("u_nm"));
				vo.setU_join_dt(rs.getTimestamp("u_join_dt"));
				vo.setBirth(rs.getString("birth"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setSnstype(rs.getString("snstype"));
				vo.setSnsid(rs.getString("snsid"));
				vo.setFavorit(rs.getString("favorit"));
				vo.setMailing(rs.getString("mailing"));
			}
			System.out.println("sql구문 실행완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return vo;
	}

	// 회원정보수정
	public int updateMember(MemberVo vo) {
		int result = -1;
		try {
			con = getCon();
			sql = "select * from user_master where u_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getU_id());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				sql = "update user_master set u_nm=?, phone=?, favorit=?, email=?, mailing=?, birth=?, u_pw=?"
						+ "where u_id=?";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, vo.getU_nm());
				pstmt.setString(2, vo.getPhone());
				pstmt.setString(3, vo.getFavorit());
				pstmt.setString(4, vo.getEmail());
				pstmt.setString(5, vo.getMailing());
				pstmt.setString(6, vo.getBirth());
				pstmt.setString(7, vo.getU_pw());
				pstmt.setString(8, vo.getU_id());

				pstmt.executeUpdate();
				result = 1;
				System.out.println("회원정보수정성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}// updateMember닫힘

	// 회원탈퇴
	// 7.delete구현
	public int deleteMember(String u_id, String u_pw) {
		int result = -1;
		try {
			con = getCon();
			sql = "select u_pw from user_master where u_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, u_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (u_pw.equals(rs.getString("u_pw"))) {
					sql = "delete from user_master where u_id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, u_id);
					pstmt.executeUpdate();
					result = 1;
					System.out.println("회원삭제성공-아디일치,비번일치");
				} else {
					result = 0;
					System.out.println("회원삭제실패-아디일치,비번불일치");
				}
			} else {
				result = -1;
				System.out.println("회원삭제실패-아이디불일치");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}// delete닫힘
}
