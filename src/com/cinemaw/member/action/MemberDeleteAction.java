package com.cinemaw.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinemaw.member.db.MemberDAO3;
import com.cinemaw.member.db.MemberVo;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("@@@@ Action: MemberDeleteAction안의 execute() 실행됨");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String u_id = request.getParameter("u_id");
		String u_pw = request.getParameter("u_pw");

		System.out.println("u_id 전달 : " + u_id);
		System.out.println("u_pw 전달 : " + u_pw);

		ActionForward forward = new ActionForward();
		if (u_id == null) {
			forward.setPath("/MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}

		MemberVo vo = new MemberVo();
		MemberDAO3 dao = new MemberDAO3();
		int result = dao.deleteMember(u_id, u_pw);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (result == 0) { // 로그인 비번오류
			out.print("<script>");
			out.print("alert('비밀번호오류');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null; // null이란 Controller에서 페이지 이동하지않겠다는 의미
		} else if (result == -1) {
			out.print("<script>");
			out.print("alert('존재하지 않는 아이디입니다');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null; // null이란 Controller에서 페이지 이동하지않겠다는 의미
		}

		session.invalidate();
		System.out.println("Action : 회원탈퇴성공 후 페이지이동 ");
		out.print("<script>");
		out.print("alert('정상적으로 회원탈퇴되었습니다');");
		out.print("location.href='./Main.mo';");
		out.print("</script>");
		out.close();
		return null;
	}
}
