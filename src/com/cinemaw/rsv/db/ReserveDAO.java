package com.cinemaw.rsv.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReserveDAO {
	// DAO (Data Access Object) : 데이터 처리 객체

	// 공통변수 (인스턴스 변수)
	private Connection con = null;			// 디비 연결정보 저장 객체
	private PreparedStatement pstmt = null; // 디비에 SQL 실행 처리 객체
	private ResultSet rs = null;			// select 실행 결과 저장 객체
	private String sql = "";				// SQL쿼리 구문 저장

	public ReserveDAO() {
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

	public List<ReserveDTO> getReserveList(String u_id){
		System.out.println("\n DAO : getBoardList() 호출");
		
		// 글정보 모두를 저장하는 배열(가변길이)
//		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		List<ReserveDTO> reserveList = new ArrayList<ReserveDTO>();
		
		try {
			// 1. 드라이버로드
			// 2. 디비연결
			con = getConnect();
			// 3. sql 작성 & pstmt 객체
			sql = "select r.s_date, m.m_nm, r.r_pay_type, r.r_pay_price, r.r_id from reserve_info r join movie_master m on r.m_id = m.m_id where u_id =?";
			pstmt = con.prepareStatement(sql);
			
			//???
			pstmt.setString(1, u_id);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			while(rs.next()){
				// 데이터 있을때 DB에 저장된 정보를 DTO저장 -> List 저장
				
				// DB -> DTO 저장
				ReserveDTO dto = new ReserveDTO();
				
				dto.setS_date(rs.getString("s_date"));
				dto.setM_nm(rs.getString("m_nm"));
				dto.setR_pay_type(rs.getString("r_pay_type"));
				dto.setR_pay_price(rs.getInt("r_pay_price"));
				dto.setR_id(rs.getInt("r_id"));
				
				
				// DTO -> List
				reserveList.add(dto);
				
			}// while
			
			System.out.println(" DAO : 예내내역 모두 저장완료 ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return reserveList;
	}
	
}