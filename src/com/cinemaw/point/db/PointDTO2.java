package com.cinemaw.point.db;

import java.sql.Date;

public class PointDTO2 {
	private int r_id;
	private int r_user_point;
	//private int total; 추가함, 안되면 삭제 ㄱ
	private int total;
	
	
	
	
	

	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public int getR_user_point() {
		return r_user_point;
	}
	public void setR_user_point(int r_user_point) {
		this.r_user_point = r_user_point;
	}
	
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "PointDTO2 [r_id=" + r_id + ", r_user_point=" + r_user_point + ", total=" + total + "]";
	}
	


	
	
	
}
