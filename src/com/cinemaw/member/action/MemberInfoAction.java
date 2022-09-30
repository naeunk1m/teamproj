package com.cinemaw.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinemaw.member.db.MemberDAO3;
import com.cinemaw.member.db.MemberVo;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String u_id = (String) session.getAttribute("u_id");
		System.out.println("u_id: " + u_id);

		ActionForward forward = new ActionForward();
		if (u_id == null) {

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로입니다');");
			out.println("history.back();");
			out.println("</script>");
			return null;
		}
		MemberDAO3 mdao = new MemberDAO3();
		MemberVo vo = mdao.getMember(u_id);

		request.setAttribute("memberVo", vo);
		forward.setPath("./memberInfo.jsp");
		forward.setRedirect(false);

		return forward;
	}
}
