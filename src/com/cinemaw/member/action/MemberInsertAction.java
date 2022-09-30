package com.cinemaw.member.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaw.member.db.MemberDAO;
import com.cinemaw.member.db.MemberDAO2;
import com.cinemaw.member.db.MemberVo;

public class MemberInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		String u_id = request.getParameter("u_id");
		String u_pw = request.getParameter("u_pw");
		String u_nm = request.getParameter("u_nm");
		String birth = request.getParameter("birth");
		String email = request.getParameter("email1") + "@" + request.getParameter("email2");
		// String phone =
		// request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3");
		String phone = request.getParameter("phone");

		String[] favorit = request.getParameterValues("favorit");
		String mailing = request.getParameter("mailing");

		// System.out.println(mailing);

		MemberVo vo = new MemberVo();
		vo.setU_id(u_id);
		vo.setU_pw(u_pw);
		vo.setU_nm(u_nm);
		vo.setBirth(birth);
		vo.setEmail(email);
		vo.setPhone(phone);
		vo.setFavorit(Arrays.toString(favorit));
		vo.setMailing(mailing);

		vo.setU_join_dt(new Timestamp(System.currentTimeMillis()));

		MemberDAO dao = new MemberDAO();
		dao.MemberInsert(vo);
		// response.sendRedirect("main.jsp");
		//
		// response.setContentType("text/html; charset=UTF-8");
		// PrintWriter out = response.getWriter();
		//
		// out.println("<script>");
		// out.println("alert('회원가입 완료')");
		// out.println("</script>");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('회원가입완료!');");
		out.println("location.href='./MemberLogin.me'");
		out.println("</script>");
		out.close();

		return null;

		// ActionForward forward = new ActionForward();
		// forward.setPath("./MemberLogin.me");
		// forward.setRedirect(true);
		//
		// return forward;

	}

}