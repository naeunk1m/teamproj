package com.cinemaw.rsv.db;

public class ReserveDTO {

	private String s_date;
	private String m_nm;
	private String r_pay_type;
	private int r_pay_price;
	private int r_id;

	public String getS_date() {
		return s_date;
	}

	public void setS_date(String s_date) {
		this.s_date = s_date;
	}

	public String getM_nm() {
		return m_nm;
	}

	public void setM_nm(String m_nm) {
		this.m_nm = m_nm;
	}

	public String getR_pay_type() {
		return r_pay_type;
	}

	public void setR_pay_type(String r_pay_type) {
		this.r_pay_type = r_pay_type;
	}

	public int getR_pay_price() {
		return r_pay_price;
	}

	public void setR_pay_price(int r_pay_price) {
		this.r_pay_price = r_pay_price;
	}

	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	@Override
	public String toString() {
		return "ReserveDTO [s_date=" + s_date + ", m_nm=" + m_nm + ", r_pay_type=" + r_pay_type + ", r_pay_price="
				+ r_pay_price + ", r_id=" + r_id + "]";
	}

}
