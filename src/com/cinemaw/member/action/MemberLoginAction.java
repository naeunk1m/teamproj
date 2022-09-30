package com.cinemaw.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinemaw.member.db.MemberDAO2;
import com.cinemaw.member.db.MemberVo;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// MemberVo 객체 생성
		MemberVo vo = new MemberVo();
		vo.setU_id(request.getParameter("loginId"));
		vo.setU_pw(request.getParameter("loginPw"));

		MemberDAO2 dao = new MemberDAO2();
		int result = dao.loginMember(vo);

		if (result == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 오류!');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		} else if (result == -1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디 오류!');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		} else {
			// 아이디 비밀번호 일치
			// 세션값 생성
			HttpSession session = request.getSession();
			session.setAttribute("loginId", vo.getU_id());
			if ("admin".equals(vo.getU_id())) {
				session.setAttribute("isAdmin", true);
			}

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인성공!');");
			out.println("location.href='./Main.mo'");
			out.println("</script>");
			out.close();

			return null;

			// //페이지 이동정보 저장(리턴)
			// ActionForward forward = new ActionForward();
			// forward.setPath("./Main.mo");
			// forward.setRedirect(true);
			// return forward;
		}
	}

}
