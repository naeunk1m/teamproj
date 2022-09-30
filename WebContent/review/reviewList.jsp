<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.cinemaw.movie.db.MovieDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CinemaWill</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
<script>
	function test(genre){
		location.href='./MovieList.mo?genre='+genre+'&isNow='+$("#isNow").val();
	}
</script>
<%
	String isNow = request.getParameter("isNow");
	String genre = request.getParameter("genre"); 
%>
</head>
<body>
	<div id="contents" style="width: 920px; margin: 0 auto;">
		<h1>movieList.jsp : 영화정보 기본페이지</h1>
		<!-- 현재상영작, 개봉예정작, 장르별로 영화포스터를 볼 수 있고 예매하기로 연결 -->
	
<!-- 		<form id="form" action="./MovieList.mo" method="post"> -->
			<input type="hidden" id="isNow" value="<%=isNow%>">
			
			<input type="submit" id="sendData" value="현재상영작" onclick="location.href='./MovieList.mo?isNow=Y';" <% if("Y".equals(isNow)){ %> style="background-color: gold;"<%} %>>
			<input type="submit" id="sendData2" value="개봉예정작" onclick="location.href='./MovieList.mo?isNow=N';"<% if("N".equals(isNow)){ %> style="background-color: gold;"<%} %>>
			<select id="genre" name="genre" size="1" onchange="test(this.value)">
				<option value="">장르별</option>
				<option value="액션" <% if("액션".equals(genre)){ %> selected="selected"<%} %>>액션</option>
				<option value="코미디" <% if("코미디".equals(genre)){ %> selected="selected"<%} %>>코미디</option>
				<option value="애니메이션" <% if("애니메이션".equals(genre)){ %> selected="selected"<%} %>>애니메이션</option>
				<option value="스릴러" <% if("스릴러".equals(genre)){ %> selected="selected"<%} %>>스릴러</option>
				<option value="멜로/로맨스" <% if("멜로/로맨스".equals(genre)){ %> selected="selected"<%} %>>멜로/로맨스</option>
				<option value="범죄" <% if("범죄".equals(genre)){ %> selected="selected"<%} %>>범죄</option>
				<option value="드라마" <% if("드라마".equals(genre)){ %> selected="selected"<%} %>>드라마</option>
				<option value="다큐멘터리" <% if("다큐멘터리".equals(genre)){ %> selected="selected"<%} %>>다큐멘터리</option>
				<option value="미스터리" <% if("미스터리".equals(genre)){ %> selected="selected"<%} %>>미스터리</option>
				<option value="모험" <% if("모험".equals(genre)){ %> selected="selected"<%} %>>모험</option>
				<option value="공포" <% if("공포".equals(genre)){ %> selected="selected"<%} %>>공포</option>
				<option value="판타지" <% if("판타지".equals(genre)){ %> selected="selected"<%} %>>판타지</option>
				<option value="뮤지컬" <% if("뮤지컬".equals(genre)){ %> selected="selected"<%} %>>뮤지컬</option>
				<option value="공상과학" <% if("공상과학".equals(genre)){ %> selected="selected"<%} %>>공상과학</option>
			</select>
<!-- 		</form> -->
		<hr>
		
		<c:forEach var="dto" items="${requestScope.movieList }">
			<div style="display: inline-block;">
				<a href="./MovieContent.mo?m_id=${dto.m_id }&pageNum=${requestScope.pageNum }">
					<input type="image" src="./moviePoster/${dto.mv_picture}.jpg" alt=${dto.m_nm } width="300px" height="450px">
				</a>
				<a href="#" style="display: block; padding: 5px 0 0 0;">
					<img src="image/reservation_btn.jpg" alt="" style="width: 300px;">
				</a>
			</div>
		</c:forEach>
		<hr>
		
	</div>
</body>
</html>