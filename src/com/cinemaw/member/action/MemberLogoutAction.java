package com.cinemaw.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션값 초기화
		HttpSession session = request.getSession();
		session.invalidate();

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('로그아웃 성공!');");
		out.println("location.href='./Main.mo'");
		out.println("</script>");
		out.close();

		return null;

		// 결과 출력
		// response.setContentType("text/html; charset=UTF-8");
		// PrintWriter out=response.getWriter();
		// out.println("<script>");
		// out.println("alert('로그아웃 완료')");
		// out.println("</script>");
		// out.close();
		//
		// 알럿 창을 띄우면 아래 포워드 설정한거 처럼 이동하지 않고 그대로 멈춤

		//
		// //페이지 이동정보 저장(리턴)
		// // /Main.mo 이동
		// ActionForward forward = new ActionForward();
		// forward.setPath("./Main.mo");
		// forward.setRedirect(true);
		// return forward;
	}

}
