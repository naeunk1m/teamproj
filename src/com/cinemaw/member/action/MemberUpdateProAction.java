package com.cinemaw.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinemaw.member.db.MemberDAO3;
import com.cinemaw.member.db.MemberVo;

public class MemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

		MemberVo mb = new MemberVo();
		mb.setU_id(request.getParameter("u_id"));
		mb.setU_pw(request.getParameter("u_pw"));
		mb.setU_nm(request.getParameter("u_nm"));
		mb.setBirth(request.getParameter("birth"));
		mb.setEmail(request.getParameter("email1") + "@" + request.getParameter("email2"));
		mb.setPhone(request.getParameter("phone"));
		mb.setFavorit(request.getParameter("favorit"));
		mb.setMailing(request.getParameter("mailing"));

		System.out.println("u_id mupa : " + mb.getU_id());

		MemberDAO3 dao = new MemberDAO3();
		int result = dao.updateMember(mb);

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
			ActionForward forward = new ActionForward();
			PrintWriter out = response.getWriter();
			out.println("alert('회원정보수정 성공 !');");
			forward.setPath("./Main.mo");
			forward.setRedirect(true);
			return forward;
		}

	}

}
