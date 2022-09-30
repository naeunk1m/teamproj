package com.cinemaw.movie.db;

public class MovieDTO {
	// => DB테이블의 정보를 저장해서 이동하기 위한 객체

	private int m_id;			// 영화코드
	private String m_nm;		// 영화제목
	private String m_story;		// 줄거리
	private String m_person;	// 인물정보
	private String mv_genre;	// 영화장르
	private int mv_runtime;		// 상영시간
	private String mv_grade;	// 관람등급
	private String mv_rlsdate;	// 개봉일
	private String mv_picture;	// 포스터 이미지
	private String mv_video;	// 영화소개 영상
	private int cmtAble;		// 댓글 작성 가능여부
	private int reserveId;		// 예약 아이디
	private int reviewCnt;		// 댓글 작성 카운트

	// alt shfit s + r
	public int getM_id() {
		return m_id;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public String getM_nm() {
		return m_nm;
	}

	public void setM_nm(String m_nm) {
		this.m_nm = m_nm;
	}

	public String getM_story() {
		return m_story;
	}

	public void setM_story(String m_story) {
		this.m_story = m_story;
	}

	public String getM_person() {
		return m_person;
	}

	public void setM_person(String m_person) {
		this.m_person = m_person;
	}

	public String getMv_genre() {
		return mv_genre;
	}

	public void setMv_genre(String mv_genre) {
		this.mv_genre = mv_genre;
	}

	public int getMv_runtime() {
		return mv_runtime;
	}

	public void setMv_runtime(int mv_runtime) {
		this.mv_runtime = mv_runtime;
	}

	public String getMv_grade() {
		return mv_grade;
	}

	public void setMv_grade(String mv_grade) {
		this.mv_grade = mv_grade;
	}

	public String getMv_rlsdate() {
		return mv_rlsdate;
	}

	public void setMv_rlsdate(String mv_rlsdate) {
		this.mv_rlsdate = mv_rlsdate;
	}

	public String getMv_picture() {
		return mv_picture;
	}

	public void setMv_picture(String mv_picture) {
		this.mv_picture = mv_picture;
	}

	public void rserverId(String mv_picture) {
		this.mv_picture = mv_picture;
	}

	public String getMv_video() {
		return mv_video;
	}

	public void setMv_video(String mv_video) {
		this.mv_video = mv_video;
	}

	public void setCmtAble(int cmtAble) {
		this.cmtAble = cmtAble;
	}

	public int getCmtAble() {
		return cmtAble;
	}

	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
	}

	public int getReserveId() {
		return reserveId;
	}

	public int getReviewCnt() {
		return reviewCnt;
	}

	public void setReviewCnt(int reviewCnt) {
		this.reviewCnt = reviewCnt;
	}

	@Override
	public String toString() {
		return "MovieDTO [m_id=" + m_id + ", m_nm=" + m_nm + ", m_story=" + m_story + ", m_person=" + m_person
				+ ", mv_genre=" + mv_genre + ", mv_runtime=" + mv_runtime + ", mv_grade=" + mv_grade + ", mv_rlsdate="
				+ mv_rlsdate + ", mv_picture=" + mv_picture + ", mv_video=" + mv_video + ", cmtAble=" + cmtAble
				+ ", reserveId=" + reserveId + ", reviewCnt=" + reviewCnt + "]";
	}

	public void setName(String parameter) {
		// TODO Auto-generated method stub

	}

}
