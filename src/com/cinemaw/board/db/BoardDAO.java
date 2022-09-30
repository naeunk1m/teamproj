package com.cinemaw.board.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.mysql.cj.xdevapi.Session;

public class BoardDAO {
	// DAO (Data Access Object) : 데이터 처리 객체

	// 공통변수 (인스턴스 변수)
	private Connection con = null;			// 디비 연결정보 저장 객체
	private PreparedStatement pstmt = null; // 디비에 SQL 실행 처리 객체
	private ResultSet rs = null;			// select 실행 결과 저장 객체
	private String sql = "";				// SQL쿼리 구문 저장

	public BoardDAO() {
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

	public List<BoardDTO> getBoardList(String id) {
		System.out.println("\n DAO : getBoardList() 호출");

		List<BoardDTO> boardList = new ArrayList<BoardDTO>();

		try {
			// 1. 드라이버로드
			// 2. 디비연결
			con = getConnect();
			// 3. sql 작성 & pstmt 객체
			sql = "select r_id,r_score,r_text,u_id,rvw_regdate,rvw_revise from review_info where u_id =?";
			pstmt = con.prepareStatement(sql);

			// ???
			pstmt.setString(1, id);
			// 4. sql 실행

			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			while (rs.next()) {
				// 데이터 있을때 DB에 저장된 정보를 DTO저장 -> List 저장

				// DB -> DTO 저장
				BoardDTO dto = new BoardDTO();

				dto.setR_id(rs.getInt("r_id"));
				dto.setR_score(rs.getInt("r_score"));
				dto.setR_text(rs.getString("r_text"));
				dto.setU_id(rs.getString("u_id"));
				dto.setRvw_regdate(rs.getDate("rvw_regdate"));
				dto.setRvw_revise(rs.getDate("rvw_revise"));

				// DTO -> List
				boardList.add(dto);

			} // while

			System.out.println(" DAO : 게시판 목록 모두 저장완료 ");
			// System.out.println(" C : "+boardList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return boardList;
	}

}