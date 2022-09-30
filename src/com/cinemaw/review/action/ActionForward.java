package com.cinemaw.review.action;

public class ActionForward {
	// 페이지 이동을 위한 정보를 저장하는 객체, = 통행권

	private String path;
	private boolean isRedirect;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	// true = sendRedricet()
	// false = forward()

}
