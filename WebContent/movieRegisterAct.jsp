<%@page import="admin.movies.MovieVO"%>
<%@page import="admin.movies.MovieDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="admin.*"%>


<%
	MovieDAO dao = new MovieDAO();
	MovieVO movieVO = new MovieVO();
	
	request.setCharacterEncoding("utf-8");
	String nm = request.getParameter("nm");
	String story = request.getParameter("story");
	String person = request.getParameter("person");
	String genre = request.getParameter("genre");
	String runtime = request.getParameter("runtime");
	String grade = request.getParameter("grade");
	String rlsdate = request.getParameter("rlsdate");
	String picture = request.getParameter("picture");
	String video = request.getParameter("video");
	String dtb = request.getParameter("dtb");


	movieVO.setNm(nm);
	movieVO.setStory(story);
	movieVO.setPerson(person);
	movieVO.setGenre(genre);
	movieVO.setRuntime(Integer.parseInt(runtime));	
	movieVO.setGrade(grade);
	movieVO.setRlsdate(rlsdate);
	movieVO.setPicture(picture);
	movieVO.setVideo(video);

	int result = dao.movieInsert(movieVO);

	if (result == 1) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('등록 처리 되었습니다.')");
		script.println("location.href='main.html'");
		script.println("</script>");
	} 
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>처리 중</title>
</head>
<body>

</body>
</html>