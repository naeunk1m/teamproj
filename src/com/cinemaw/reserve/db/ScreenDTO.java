package com.cinemaw.reserve.db;

public class ScreenDTO {
	private int t_num;
	private String d_date;
	private String s_time;
	private int m_id;
	private int seat_cnt;

	public int getT_num() {
		return t_num;
	}

	public void setT_num(int t_num) {
		this.t_num = t_num;
	}

	public String getD_date() {
		return d_date;
	}

	public void setD_date(String d_date) {
		this.d_date = d_date;
	}

	public String getS_time() {
		return s_time;
	}

	public void setS_time(String s_time) {
		this.s_time = s_time;
	}

	public int getM_id() {
		return m_id;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public int getSeat_cnt() {
		return seat_cnt;
	}

	public void setSeat_cnt(int seat_cnt) {
		this.seat_cnt = seat_cnt;
	}

	@Override
	public String toString() {
		return "ScreenDTO [t_num=" + t_num + ", d_date=" + d_date + ", s_time=" + s_time + ", m_id=" + m_id
				+ ", seat_cnt=" + seat_cnt + "]";
	}

}
