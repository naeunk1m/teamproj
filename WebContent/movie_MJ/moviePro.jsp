<%@page import="com.cinemaw.movie.db.MovieDAO"%>
<%@page import="com.cinemaw.movie.db.MovieDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CinemaWill</title>
</head>
<body>
	<h1>moviePro.jsp : 영화정보 기본페이지</h1>

	<%
		//한글처리
		request.setCharacterEncoding("UTF-8");

		// 전달정보 저장(m_id, m_nm, m_story...)
		int m_id = Integer.parseInt(request.getParameter("m_id"));
		String m_nm = request.getParameter("m_nm");
		String m_story = request.getParameter("m_story");
		String m_person = request.getParameter("m_person");
		String mv_genre = request.getParameter("mv_genre");
		int mv_runtime = Integer.parseInt(request.getParameter("mv_runtime"));
		String mv_grade = request.getParameter("mv_grade");
		String mv_rlsdate = request.getParameter("mv_rlsdate");
		String mv_picture = request.getParameter("mv_picture");
		String mv_video = request.getParameter("mv_video");

		// => DTO 객체로 한번에 저장
		MovieDTO dto = new MovieDTO();
		//dto.setM_id(m_id);
		dto.setM_id(m_id);
		//dto.setM_nm("m_nm");
		dto.setM_nm(request.getParameter("m_nm"));
		//dto.setM_story("m_story");
		dto.setM_story(request.getParameter("m_story"));
		//dto.setM_person("m_person");
		dto.setM_person(request.getParameter("m_person"));
		//dto.setMv_genre("mv_genre");
		dto.setMv_genre(request.getParameter("mv_genre"));
		//dto.setMv_runtime("mv_runtime");
		dto.setMv_runtime(Integer.parseInt(request.getParameter("mv_runtime")));
		//dto.setMv_grade("mv_grade");
		dto.setMv_grade(request.getParameter("mv_grade"));
		//dto.setMv_rlsdate("mv_rlsdate");
		dto.setMv_rlsdate(request.getParameter("mv_rlsdate"));
		//dto.setMv_picture("mv_picture");
		dto.setMv_picture(request.getParameter("mv_picture"));
		//dto.setMv_video("mv_video");
		dto.setMv_video(request.getParameter("mv_video"));
	%>

	<h2>액션태그</h2>
	<%-- <jsp:useBean id="객체명" class="객체의 위치"/> --%>
	MovieDTO dto = new MovieDTO();
	<jsp:useBean id="dto2" class="com.cinemaw.movie.db.MovieDTO" />

	<!-- 객체에 정보 저장가능 -->
	dto.setM_id(request.getParameter("m_id"));
	<%-- <jsp:setProperty property="객체의 변수명" name="객체명" param="파라메터명"/> --%>
	<%-- <jsp:setProperty property="id" name="dto2" param="id"/> --%>
	<!-- property, param 값이 동일하면 param속성값을 생략가능 -->
	<%-- <jsp:setProperty property="id" name="dto2"/> --%>

	<%-- <jsp:setProperty property="*" name="dto2" param="*"/> --%>
	전달되는 모든 파라메터의 값을 모든 변수에 저장(알아서)
	<jsp:setProperty property="*" name="dto2" />

	<%=dto2%>
	<hr>
	전달된 정보를 DTO 객체에 저장(한번에 저장)

	<%
		// DAO 객체 생성
		MovieDAO dao = new MovieDAO();
	%>
















</body>
</html>