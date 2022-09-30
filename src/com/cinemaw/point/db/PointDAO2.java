package com.cinemaw.point.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PointDAO2 {

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// 디비 연결
	private Connection getConnect() throws Exception {
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String DBURL = "jdbc:mysql://itwillbs8.cafe24.com:3306/itwillbs8";
		String DBID = "itwillbs8";
		String DBPW = "itwillbs8030909";

		// 1. 드라이버 로드
		Class.forName(DRIVER);
		System.out.println("드라이버로드 성공!");

		// 2. 디비연결
		con = DriverManager.getConnection(DBURL, DBID, DBPW);
		System.out.println("디비연결 성공");
		System.out.println("con :" + con);

		return con;
	} // 디비연결

	// 자원해제
	public void closeDB() {

		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	// 자원해제

	// 포인트 목록 조회 (all) - getPoint()
	public List<PointDTO2> getPointList2(String id){
		System.out.println("\n DAO : getPointList() 호출");
		
		List<PointDTO2> pointList2 = new ArrayList<PointDTO2>();
		
		
		try {
			// 1. 드라이버로드
			// 2. 디비연결
			con = getConnect();
			// 3. sql 작성 & pstmt 객체
			sql = "select r_id,r_user_point from reserve_info where u_id=?";
			pstmt = con.prepareStatement(sql);
			
			//???
			pstmt.setString(1, id);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			
			// 5. 데이터 처리
			while(rs.next()){
				// 데이터 있을때 DB에 저장된 정보를 DTO저장 -> List 저장
				
				// DB -> DTO 저장
				PointDTO2 dto2 = new PointDTO2();
				
				dto2.setR_id(rs.getInt("r_id"));
				dto2.setR_user_point(rs.getInt("r_user_point"));
				
				
				// DTO -> List
				pointList2.add(dto2);
				
				
			}// while
			
			System.out.println(" DAO : 포인트 목록 모두 저장완료 ");
			//System.out.println(" C : "+pointList2);			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return pointList2;
	}
	
	
	public int PointTotal(String id) {
		int result = 0;
		try {
			con = getConnect();
			// 3. sql 작성 & pstmt 객체
			sql = "select sum(r_user_point) as 'total' from reserve_info where u_id=?";
			pstmt = con.prepareStatement(sql);
			
			//???
			pstmt.setString(1, id);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			 if(rs.next()) {
	                result = rs.getInt("total");
	            }  
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return result;
	
	
	
	
	}
	
	
}