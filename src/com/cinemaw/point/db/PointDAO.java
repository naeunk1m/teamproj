package com.cinemaw.point.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cinemaw.board.db.BoardDTO;

public class PointDAO {
	// DAO (Data Access Object) : 데이터 처리 객체

	// 공통변수 (인스턴스 변수)
	private Connection con = null;			// 디비 연결정보 저장 객체
	private PreparedStatement pstmt = null; // 디비에 SQL 실행 처리 객체
	private ResultSet rs = null;			// select 실행 결과 저장 객체
	private String sql = "";				// SQL쿼리 구문 저장

	public PointDAO() {
		System.out.println(" DAO : DB연결에 관한 모든정보를 준비 완료! ");

	}

	// 디비 연결
	private Connection getConnect() throws Exception {
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

	// 디비 연결

	// 자원 해제
	public void closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

			System.out.println(" DAO : 자원해제 성공! ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<PointDTO> getPointList(String u_id){
		System.out.println("\n DAO : getPointList() 호출");
		
		List<PointDTO> pointList = new ArrayList<PointDTO>();
		
		
		try {
			// 1. 드라이버로드
			// 2. 디비연결
			con = getConnect();
			// 3. sql 작성 & pstmt 객체
			sql = "select p_type,point,p_dt from point where u_id=?";
			
			pstmt = con.prepareStatement(sql);
			
			//???
			pstmt.setString(1, u_id);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			
			// 5. 데이터 처리
			while(rs.next()){
				// 데이터 있을때 DB에 저장된 정보를 DTO저장 -> List 저장
				
				// DB -> DTO 저장
				PointDTO dto = new PointDTO();
				
				
				dto.setP_type(rs.getString("p_type"));
				dto.setPoint(rs.getInt("point"));
				dto.setP_dt(rs.getDate("p_dt"));

				// DTO -> List
				pointList.add(dto);
				
				
			}// while
			
			System.out.println(" DAO : 포인트 목록 모두 저장완료 ");
			//System.out.println(" C : "+pointList);			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return pointList;
		}
	}