package com.cinemaw.reserve.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RefreshAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('결제 시간을 초과하였습니다.');");
		out.println("location.href = './Reservation.res'");
		out.println("</script>");
		out.close();

		return null;

	}

}