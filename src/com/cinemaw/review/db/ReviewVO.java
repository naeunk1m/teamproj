package com.cinemaw.review.db;

public class ReviewVO {

	private Integer r_id;
	private Integer r_score;
	private String r_text;
	private String u_id;
	private String rvw_regdate;
	private String rvw_revise;
	private int m_id;
	private int reserveId; // 예약 아이디

	public Integer getR_id() {
		return r_id;
	}

	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}

	public Integer getR_score() {
		return r_score;
	}

	public void setR_score(Integer r_score) {
		this.r_score = r_score;
	}

	public String getR_text() {
		return r_text;
	}

	public void setR_text(String r_text) {
		this.r_text = r_text;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getRvw_revise() {
		return rvw_revise;
	}

	public void setRvw_revise(String rvw_revise) {
		this.rvw_revise = rvw_revise;
	}

	public String getRvw_regdate() {
		return rvw_regdate;
	}

	public void setRvw_regdate(String rvw_regdate) {
		this.rvw_regdate = rvw_regdate;
	}

	public int getM_id() {
		return m_id;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
	}

	public int getReserveId() {
		return reserveId;
	}

	@Override
	public String toString() {
		return "ReviewVO [r_id=" + r_id + ", r_score=" + r_score + ", r_text=" + r_text + ", u_id=" + u_id
				+ ", rvw_regdate=" + rvw_regdate + ", rvw_revise=" + rvw_revise + ", m_id=" + m_id + ", reserveId="
				+ reserveId + "]";
	}

}