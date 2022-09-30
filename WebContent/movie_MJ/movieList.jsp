<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.cinemaw.movie.db.MovieDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
 <link rel="stylesheet" href="css/main.css">
</head>
<body>
	<%@ include file="/header.jsp" %>
	<section>
        <div class="inner">
        	<% if(!"N".equals(isNow)){ %> 
        		<h1>현재상영작</h1>
        	<%}else{ %>
        		<h1>개봉예정작</h1>
            <%} %>
         	<input type="hidden" id="isNow" value="<%=isNow%>"> <!-- 현재상영작을 보여줄지 여부 -->
			<a href="./MovieList.mo?isNow=Y" style="margin-left:90px; color:black; background:#bfbfbf; padding:7px; border-radius: 12px; ">현재상영작</a>
			<a href="./MovieList.mo?isNow=N" style="margin-left:6px; color:black; background:#bfbfbf; padding:7px; border-radius: 12px;">개봉예정작</a>
			<select id="genre" name="genre" size="1" onchange="test(this.value)" style="margin-left:6px; background:white; padding:7px; border-radius: 12px; ">
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
            <div class="wrap">
				<c:forEach var="dto" items="${requestScope.movieList }">
	                <article>
	                	<div class="pic">
							<a href="./MovieContent.mo?m_id=${dto.m_id }&pageNum=${requestScope.pageNum }">
								<c:choose>
									<c:when test="${fn:contains(fn:toLowerCase(dto.mv_picture), 'jpg') or  fn:contains(fn:toLowerCase(dto.mv_picture), 'gif') or fn:contains(fn:toLowerCase(dto.mv_picture), 'jpeg') }">
										<img src = "./image/${dto.mv_picture}"  alt=${dto.m_nm }>
									</c:when>
									<c:otherwise>
										<img src = "./moviePoster/${dto.mv_picture}.jpg"  alt=${dto.m_nm }>
									</c:otherwise>
								</c:choose>
							</a>
	                	</div>
						<div class="reservebtn" style=" width:155px; height:35px; margin: 0 auto;  margin-bottom:18px;background:#a3aeb8; border-radius: 10px;">
	                    	<h2 style="text-align: center; margin:0 auto; background:#a3aeb8;"><a style="color:white; " href="./Reservation.res">예매하기</a></h2>
	                    </div>
                	</article>
				</c:forEach>
			</div>
		</div>
	</section>
	<%@ include file="/footer.jsp" %>
</body>
</html>