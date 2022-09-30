package com.cinemaw.board.db;

import java.sql.Date;

public class BoardDTO {

	private int r_id;
	private int r_score;
	private String r_text;
	private String u_id;
	private Date rvw_regdate;
	private Date rvw_revise;

	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public int getR_score() {
		return r_score;
	}

	public void setR_score(int r_score) {
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

	public Date getRvw_regdate() {
		return rvw_regdate;
	}

	public void setRvw_regdate(Date rvw_regdate) {
		this.rvw_regdate = rvw_regdate;
	}

	public Date getRvw_revise() {
		return rvw_revise;
	}

	public void setRvw_revise(Date rvw_revise) {
		this.rvw_revise = rvw_revise;
	}

	@Override
	public String toString() {
		return "BoardDTO [r_id=" + r_id + ", r_score=" + r_score + ", r_text=" + r_text + ", u_id=" + u_id
				+ ", rvw_regdate=" + rvw_regdate + ", rvw_revise=" + rvw_revise + "]";
	}

}
