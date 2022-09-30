package com.cinemaw.review.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import admin.DBConnection;

public class ReviewDAO {

	// 공통변수 (인스턴스 변수)
	private Connection con = null;			// 디비 연결정보 저장 객체
	private PreparedStatement pstmt = null; // 디비에 SQL 실행 처리 객체
	private ResultSet rs = null;			// select 실행 결과 저장 객체
	private String sql = "";				// SQL쿼리 구문 저장
	int count = 0;

	// mysql에 접속하는 부분
	public int movieInsert(ReviewVO vo) {
		// 생성자, 실행될때마다 자동으로 DB연결이 이루어지게함

		try {

			System.out.println(" ReviewVO         " + vo.toString());

			con = DBConnection.getInstance().getCon();
			// 1.트랜잭션 설정
			con.setAutoCommit(false);

			// String sq1="SELECT count(*) FROM reserve_info where u_id=? and
			// m_id=?";
			// pstmt = con.prepareStatement(sq1);
			// pstmt.setString(1, vo.getU_id());
			// pstmt.setInt(2, vo.getM_id());
			// rs=pstmt.executeQuery();
			// if(rs.next()){
			//
			// System.out.println("존재 함:" +rs.getInt(1));
			// if(rs.getInt(1)==0){
			// throw new Exception("예약하지 않았습니다.");
			// }
			// }
			//

			String sql = "INSERT INTO review_info " + " (r_score, r_text, u_id, rvw_regdate,  m_id , m_reserveId) "
					+ " VALUES(?, ?, ?, now(), ?, ?) ";

			System.out.println(con);
			System.out.println(sql);
			System.out.println("평점등록 정보 : " + vo.toString());

			pstmt = con.prepareStatement(sql);

			// pstmt.setInt(1, vo.getR_id());
			pstmt.setInt(1, vo.getR_score());
			pstmt.setString(2, vo.getR_text());
			pstmt.setString(3, vo.getU_id());
			pstmt.setInt(4, vo.getM_id());
			pstmt.setInt(5, vo.getReserveId());

			/*
			 * pstmt.setString(5, vo.getRvw_regdate()); pstmt.setString(6,
			 * vo.getRvw_revise());
			 */

			// 실행
			pstmt.executeUpdate();

			//
			// UPDATE review_info SET r_text='수정합니다.' , r_score=1,
			// rvw_revise=now() WHERE r_id=64
			// UPDATE review_info SET r_text=? , r_score=?, rvw_revise=now()
			// WHERE r_id=?

			sql = "SELECT  `u_id`, `point` FROM `point` WHERE u_id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getU_id());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {

				int point = rs.getInt(2);
				System.out.println("기존 포인트 값 : " + point);

				// 포인트 업데이트
				sql = "UPDATE `point` SET  `point`=?  WHERE u_id= ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, point + 100);
				pstmt.setString(2, vo.getU_id());

				pstmt.executeUpdate();

			} else {

				// 포인트 신규 등록
				sql = "INSERT INTO `point` (u_id, p_seq, p_type, `point`, p_dt)  VALUES(?,  ?, ?, 100, now()) ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, vo.getU_id());
				pstmt.setInt(2, 3); // p_seq
				pstmt.setString(3, "23"); // p_type
				pstmt.executeUpdate();

			}

			// 2. 트랜잭션 커밋
			con.commit();

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			try {
				// 3.트랜잭션 설정 오류시 롤백
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return 0;
		} finally {
			try {
				// 준비했던 DB연결문들 종료
				con.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<ReviewVO> reviewSelect(int m_id, String order) {
		// 생성자, 실행될때마다 자동으로 DB연결이 이루어지게 함

		ArrayList<ReviewVO> boardList = new ArrayList<>();
		try {

			con = DBConnection.getInstance().getCon();

			String sql = "select * from review_info where m_id=? order by r_id desc ";
			if(!order.equals("new")){
				sql="select * from review_info where m_id=? order by r_score  desc";
			}
			
			System.out.println(con);
			System.out.println(sql);

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				// 데이터 있을 때 DB에 저장된 정보를 DTO 저장 -> List 저장

				// DB -> DTO 저장
				ReviewVO vo = new ReviewVO();
				vo.setM_id(rs.getInt("m_id"));
				vo.setR_id(rs.getInt("r_id"));
				vo.setR_score(rs.getInt("r_score"));
				vo.setR_text(rs.getString("r_text"));
				vo.setRvw_regdate(rs.getString("rvw_regdate"));
				vo.setRvw_revise(rs.getString("rvw_revise"));
				vo.setU_id(rs.getString("u_id"));

				// DTO -> List
				boardList.add(vo);

			} // while

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				// 준비했던 DB연결문들 종료
				con.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return boardList;
	}
	
	public void updateReview(int r_id, int r_score, String r_text) {
		// 생성자, 실행될때마다 자동으로 DB연결이 이루어지게함
		try {

			System.out.println(" M :  UpdateReview 호출");
			
			con = DBConnection.getInstance().getCon();
			// 1.트랜잭션 설정
			con.setAutoCommit(false);

			// String sq1="SELECT count(*) FROM reserve_info where u_id=? and
			// m_id=?";
			// pstmt = con.prepareStatement(sq1);
			// pstmt.setString(1, vo.getU_id());
			// pstmt.setInt(2, vo.getM_id());
			// rs=pstmt.executeQuery();
			// if(rs.next()){
			//
			// System.out.println("존재 함:" +rs.getInt(1));
			// if(rs.getInt(1)==0){
			// throw new Exception("예약하지 않았습니다.");
			// }
			// }
			
			sql = "UPDATE review_info SET r_text=? , r_score=?, rvw_revise=now() WHERE r_id=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, r_text);
			pstmt.setInt(2, r_score);
			pstmt.setInt(3, r_id);

			/*
			 * pstmt.setString(5, vo.getRvw_regdate()); pstmt.setString(6,
			 * vo.getRvw_revise());
			 */

			// 실행
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				// 준비했던 DB연결문들 종료 
				con.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

}