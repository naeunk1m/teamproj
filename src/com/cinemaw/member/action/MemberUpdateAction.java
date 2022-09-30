package com.cinemaw.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinemaw.member.db.MemberDAO3;
import com.cinemaw.member.db.MemberVo;

public class MemberUpdateAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String u_id = (String) session.getAttribute("loginId");

		System.out.println("u_id mua: " + u_id);

		MemberDAO3 dao = new MemberDAO3();
		MemberVo mb = dao.getMember(u_id);
		request.setAttribute("mb", mb);

		System.out.println("mb : " + mb);

		ActionForward forward = new ActionForward();
		forward.setPath("./Member_SH/updateForm.jsp");
		forward.setRedirect(false);
		return forward;
	}

}