package com.cinemaw.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinemaw.member.db.MemberDAO3;
import com.cinemaw.member.db.MemberVo;

public class myPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Action: myPageAction안의 execute() 실행됨");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String u_id = (String) session.getAttribute("loginId");
		System.out.println("u_id: " + u_id);

		ActionForward forward = new ActionForward();
		if (u_id == null) {

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로입니다');");
			out.println("history.back();");
			out.println("</script>");
			// forward.setRedirect(true); //주소가 바뀌니까 false가 아니라 true임
			return null; // 아이디가 없으면 return을 통해서 아래 코드는 실행되지않는다.
		}
		MemberDAO3 mdao = new MemberDAO3();
		MemberVo vo = mdao.getMember(u_id);

		request.setAttribute("memberVo", vo);
		forward.setPath("./Member_SH/myPage.jsp");
		forward.setRedirect(false);

		return forward;
	}
}
