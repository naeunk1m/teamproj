package com.cinemaw.movie.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MovieDAO {
	// => 디비에 관한 모든 작업을 처리

	// DAO (Data Access Object) : 데이터 처리 객체

	// 공통변수 (인스턴스 변수)
	private Connection con = null;			// 디비 연결정보 저장 객체
	private PreparedStatement pstmt = null; // 디비에 SQL 실행 처리 객체
	private ResultSet rs = null;			// select 실행 결과 저장 객체
	private String sql = "";				// SQL쿼리 구문 저장
	private Statement stmt = null;

	public MovieDAO() {
		System.out.println(" DAO : DB연결에 관한 모든정보를 준비 완료! ");
	}

	// 디비 연결
	private Connection getConnect() throws Exception {
		// 디비 연결정보 - context.xml

		// 프로젝트 정보를 초기화
		Context initCTX = new InitialContext();
		// 초기화된 프로젝트 정보 중 데이터 불러오기
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/model2");
		// 연결된 정보를 바탕으로 connection 정보를 리턴
		con = ds.getConnection();

		return con;
	}

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

	// 글 목록 조회 - getMovieList()
	public List<MovieDTO> getMovieList(int startRow, int pageSize, String genre, boolean isNow) {
		System.out.println("\n DAO : getMovieList() 호출");

		// 글정보 모두를 저장하는 배열(가변길이)
		// ArrayList<MovieDTO> movieList = new ArrayList<MovieDTO>();
		List<MovieDTO> movieList = new ArrayList<MovieDTO>();

		try {
			// 1. 드라이버로드
			// 2. 디비연결
			con = getConnect();
			// 3. sql 작성 & pstmt 객체
			// sql = "select * from itwill_board"; (x)

			// limit 시작행-1,개수 : 시작 지점부터 해당 개수만큼 잘라오기
			// 정렬 : re_ref 내림차순, re_seq 오름차순

			sql = " select distinct a.*, count(b.r_id)						"
					+ " from movie_master a left outer join reserve_info b    "
					+ " on a.m_id = b.m_id                                    "
					+ " where 1=1                                             ";
			// 현재상영작
			if (isNow) {
				sql += " and mv_rlsdate <= DATE_FORMAT(now(), '%Y%m%d')		";
			} else { // 상영예정작
				sql += " and mv_rlsdate > DATE_FORMAT(now(), '%Y%m%d')		";
			}
			// 장르별
			if (null != genre && !"".equals(genre)) {
				sql += " and mv_genre = ? ";
			}
			sql += " group by a.m_id                                    	"
					+ " order by count(b.r_id) desc                       	" + // 예매순
																				// 내림차순
					"limit ?,?";

			pstmt = con.prepareStatement(sql);

			// ????
			if (null != genre && !"".equals(genre)) {
				pstmt.setString(1, genre); // 장르
				pstmt.setInt(2, startRow - 1); // 시작행 - 1
				pstmt.setInt(3, pageSize);
			} else {
				pstmt.setInt(1, startRow - 1); // 시작행 - 1
				pstmt.setInt(2, pageSize);
			}

			// 4. sql 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			while (rs.next()) {
				// 데이터 있을때 DB에 저장된 정보를 DTO저장 -> List 저장

				// DB -> DTO 저장
				MovieDTO dto = new MovieDTO();
				dto.setM_id(rs.getInt("m_id"));
				dto.setM_nm(rs.getString("m_nm"));
				dto.setM_story(rs.getString("m_story"));
				dto.setM_person(rs.getString("m_person"));
				dto.setMv_genre(rs.getString("mv_genre"));
				dto.setMv_runtime(rs.getInt("mv_runtime"));
				dto.setMv_grade(rs.getString("mv_grade"));
				dto.setMv_rlsdate(rs.getString("mv_rlsdate"));
				dto.setMv_picture(rs.getString("mv_picture"));
				dto.setMv_video(rs.getString("mv_video"));

				// DTO -> List
				movieList.add(dto);

			} // while

			System.out.println(" DAO : 영화 목록 모두 저장완료 ");
			// System.out.println(" C : "+ movieList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return movieList;
	}

	public void movieInsert() throws Exception {
		MovieDTO dto = new MovieDTO();
		dto.setM_id(1);
		dto.setM_nm("알라딘");
		movieInsert(dto);

		// ???
		// pstmt.setInt(1, 1);
		// pstmt.setString(2, "알라딘");
		pstmt.setString(3, "머나먼 사막 속 신비의 아그라바 왕국의 시대. " + "좀도둑 ‘알라딘’은 마법사 ‘자파’의 의뢰로 마법 램프를 찾아 나섰다가 "
				+ "주인에게 세 가지 소원을 들어주는 지니를 만나게 되고, " + "자스민 공주의 마음을 얻으려다 생각도 못했던 모험에 휘말리게 되는데…");
		pstmt.setString(4, "가이 리치, 메나 마수드, 윌 스미스, 나오미 스콧");
		pstmt.setString(5, "어드벤처, 가족, 환타지");
		pstmt.setInt(6, 128);
		pstmt.setString(7, "All");
		pstmt.setString(8, "2022-09-07");
		pstmt.setString(9, "81478_320");
		pstmt.setString(10, "https://www.youtube.com/watch?v=KrM3vS5sy2w");

		// 4. SQL 실행
		pstmt.executeUpdate();
	}

	public int movieInsert(MovieDTO dto) {
		String DBURL = "jdbc:mysql://itwillbs8.cafe24.com:3306/itwillbs8";
		String DBID = "itwillbs8";
		String DBPW = "itwillbs8030909";

		System.out.println("등록전 데이터 값 : " + dto.toString());
		try {
			// 1. 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. 디비연결
			con = DriverManager.getConnection(DBURL, DBID, DBPW);
			// 3. SQL 작성 & pstmt 생성
			String sql = " " + " INSERT INTO movie_master "
					+ " (m_nm, m_story, m_person, mv_genre, mv_runtime, mv_grade, mv_rlsdate, mv_picture, mv_video) "
					+ " VALUES(?, ?, ?, ?, ?, ?, now(), ?, ?) ";

			System.out.println(con);
			System.out.println(sql);
			System.out.println("등록  :" + dto.toString());

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getM_nm());
			pstmt.setString(2, dto.getM_story());
			pstmt.setString(3, dto.getM_person());
			pstmt.setString(4, dto.getMv_genre());
			pstmt.setInt(5, dto.getMv_runtime());
			pstmt.setString(6, dto.getMv_grade());
			pstmt.setString(7, dto.getMv_picture());
			pstmt.setString(8, dto.getMv_video());

			// 4. SQL 실행
			int id = pstmt.executeUpdate();
			pstmt.close();

			int insertId = 0;
			if (id == 1) {
				pstmt = con.prepareStatement("select max(m_id) as id  from movie_master  ");
				rs = pstmt.executeQuery();

				if (rs.next()) {
					insertId = rs.getInt("id");
					System.out.println(" 등록한  inertId 값은 : " + insertId);
				}

			}

			return insertId;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				stmt.close();
				con.close();
				pstmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int getMovieCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	// 특정글 1개의 정보 조회 - getBoard(bno)
	public MovieDTO getMovie(int m_id, String u_id) {
		System.out.println(" DAO : getMovie(m_id) 호출 ");
		MovieDTO dto = null;

		try {
			// 1.2. 디비연결
			con = getConnect();
			// 3. sql작성(select) & pstmt 객체

			System.out.println("getMovie  : " + u_id);
			if (u_id == null)
				u_id = "";

			// sql = "select * from movie_master where m_id=?";

			sql = "select  * , (SELECT  count(r_id)  FROM reserve_info  where m_id =? and u_id=? ) as cmtAble , "
					+ " (SELECT  count(r_id)  FROM reserve_info  where m_id =?  and u_id=? ) as reserveId , "
					+ " (select count(r_id)  from review_info  where m_id =?  and u_id=? ) as reviewCnt"
					+ " from movie_master mm where m_id=?";

			System.out.println("영화 상세 정보 가져오기 sql : " + sql);

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, m_id);
			pstmt.setString(2, u_id);
			pstmt.setInt(3, m_id);
			pstmt.setString(4, u_id);
			pstmt.setInt(5, m_id);
			pstmt.setString(6, u_id);
			pstmt.setInt(7, m_id);

			// 4. sql 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				// DB에 특정 번호의 글번호를 저장

				// DB -> DTO
				dto = new MovieDTO();
				dto.setM_id(rs.getInt("m_id"));
				dto.setM_nm(rs.getString("m_nm"));
				dto.setM_story(rs.getString("m_story"));
				dto.setM_person(rs.getString("m_person"));
				dto.setMv_genre(rs.getString("mv_genre"));
				dto.setMv_runtime(rs.getInt("mv_runtime"));
				dto.setMv_grade(rs.getString("mv_grade"));
				dto.setMv_rlsdate(rs.getString("mv_rlsdate"));
				dto.setMv_picture(rs.getString("mv_picture"));
				dto.setMv_video(rs.getString("mv_video"));
				dto.setCmtAble(rs.getInt("cmtAble"));
				dto.setReserveId(rs.getInt("reserveId"));
				dto.setReviewCnt(rs.getInt("reviewCnt"));

			} // if

			System.out.println(" DAO : " + m_id + "번 게시글 정보 저장 완료");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return dto;
	}
}