package com.cinemaw.member.db;

import java.sql.Date;
import java.sql.Timestamp;

public class MemberVo {

	private String u_id;
	private String u_pw;
	private String u_nm;
	private Timestamp u_join_dt;
	private String birth;
	private String email;
	private String phone;
	private String snstype;
	private String snsid;
	private String favorit;
	private String mailing;

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getU_pw() {
		return u_pw;
	}

	public void setU_pw(String u_pw) {
		this.u_pw = u_pw;
	}

	public String getU_nm() {
		return u_nm;
	}

	public void setU_nm(String u_nm) {
		this.u_nm = u_nm;
	}

	public Timestamp getU_join_dt() {
		return u_join_dt;
	}

	public void setU_join_dt(Timestamp u_join_dt) {
		this.u_join_dt = u_join_dt;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSnstype() {
		return snstype;
	}

	public void setSnstype(String snstype) {
		this.snstype = snstype;
	}

	public String getSnsid() {
		return snsid;
	}

	public void setSnsid(String snsid) {
		this.snsid = snsid;
	}

	public String getFavorit() {
		return favorit;
	}

	public void setFavorit(String favorit) {
		this.favorit = favorit;
	}

	public String getMailing() {
		return mailing;
	}

	public void setMailing(String mailing) {
		this.mailing = mailing;
	}

	@Override
	public String toString() {
		return "MemberVo [u_id=" + u_id + ", u_pw=" + u_pw + ", u_nm=" + u_nm + ", u_join_dt=" + u_join_dt + ", birth="
				+ birth + ", email=" + email + ", phone=" + phone + ", snstype=" + snstype + ", snsid=" + snsid
				+ ", favorit=" + favorit + ", mailing=" + mailing + "]";
	}

}
