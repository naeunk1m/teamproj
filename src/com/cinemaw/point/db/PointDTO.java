package com.cinemaw.point.db;

import java.sql.Date;

public class PointDTO {
	private String p_type; //포인트 유형
	private int point; //포인트
	private Date p_dt; //포인트 지급 날짜
	
	private int total; //포인트 합계
	
	
	
	public String getP_type() {
		return p_type;
	}
	public void setP_type(String p_type) {
		this.p_type = p_type;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Date getP_dt() {
		return p_dt;
	}
	public void setP_dt(Date p_dt) {
		this.p_dt = p_dt;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		return "PointDTO [p_type=" + p_type + ", point=" + point + ", p_dt=" + p_dt + ", total=" + total + "]";
	}
	

	
	
	
	
	
}
