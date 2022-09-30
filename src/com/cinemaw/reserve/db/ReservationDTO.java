package com.cinemaw.reserve.db;

public class ReservationDTO {

	private int m_id;			// 영화 id
	private String m_nm;		// 영화 제목
	private int mv_runtime; 	// 러닝 타임
	private String mv_grade;	// 관람 등급

	private int t_num;			// 상영관 ID
	private String s_date;		// 상영 날짜
	private String s_time;		// 상영 시간
	private int seat_cnt;		// 좌석 수

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

	public int getT_num() {
		return t_num;
	}

	public void setT_num(int t_num) {
		this.t_num = t_num;
	}

	public String getS_date() {
		return s_date;
	}

	public void setS_date(String s_date) {
		this.s_date = s_date;
	}

	public String getS_time() {
		return s_time;
	}

	public void setS_time(String s_time) {
		this.s_time = s_time;
	}

	public int getSeat_cnt() {
		return seat_cnt;
	}

	public void setSeat_cnt(int seat_cnt) {
		this.seat_cnt = seat_cnt;
	}

	@Override
	public String toString() {
		return "ReservationDTO [m_id=" + m_id + ", m_nm=" + m_nm + ", mv_runtime=" + mv_runtime + ", mv_grade="
				+ mv_grade + ", t_num=" + t_num + ", s_date=" + s_date + ", s_time=" + s_time + ", seat_cnt=" + seat_cnt
				+ "]";
	}

}
