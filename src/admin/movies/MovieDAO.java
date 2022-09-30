package admin.movies;

import java.sql.*;

import admin.DBConnection;

public class MovieDAO {

	// 공통변수 (인스턴스 변수)
	private Connection con = null;			// 디비 연결정보 저장 객체
	private PreparedStatement pstmt = null; // 디비에 SQL 실행 처리 객체
	private ResultSet rs = null;			// select 실행 결과 저장 객체
	private String sql = "";				// SQL쿼리 구문 저장
	int count = 0;

	// mysql에 접속하는 부분
	public int movieInsert(MovieVO vo) {
		// 생성자, 실행될때마다 자동으로 DB연결이 이루어지게함

		try {

			con = DBConnection.getInstance().getCon();

			String sql = "INSERT INTO movie_master "
					+ " (m_nm, m_story, m_person, mv_genre, mv_runtime, mv_grade, mv_rlsdate, mv_picture, mv_video) "
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

			System.out.println(con);
			System.out.println(sql);
			System.out.println(vo.toString());

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, vo.getNm());
			pstmt.setString(2, vo.getStory());
			pstmt.setString(3, vo.getPerson());
			pstmt.setString(4, vo.getGenre());
			pstmt.setInt(5, vo.getRuntime());
			pstmt.setString(6, vo.getGrade());
			pstmt.setString(7, vo.getRlsdate());
			pstmt.setString(8, vo.getPicture());
			pstmt.setString(9, vo.getVideo());


			// 실행
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

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

}
