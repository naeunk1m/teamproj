package com.cinemaw.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	// execute실행하면 ActionForward가 생김 = 페이지 이동 주소, 방식을 저장하는 객체
	// 어디로 어떻게 갈지 하는 정보가 생김

}
