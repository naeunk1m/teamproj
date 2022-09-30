<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>CinemaWill</title>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
 <link rel="stylesheet" href="css/main.css">
</head>

<body>
<style>
@font-face {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
</style>
	<%@ include file="/header.jsp" %> 
   <figure style="width: 100%;height: calc(100vh - 140px); background: #000;position: relative;overflow: hidden;">
    	<c:forEach var="dto" items="${requestScope.movieList }" begin="0" end="0">
        	<iframe style="width: 100%;height: 100%;position: absolute;top: 0px;left: 0px;" src="https://www.youtube.com/embed/${dto.mv_video}?frameborder=0&autoplay=1&mute=1"></iframe>
 		</c:forEach>
    </figure>
    <section id=background>
    <style>
    #background{background: linear-gradient(-45deg, #1A364E, #1A364E,#475e71,#8c9aa6,#5e7283,#0f202e); background-size: 400% 400%; animation: gradient 15s ease infinite; height: 100vh;
	}
	@keyframes gradient {
	  0% { background-position: 0% 50%;}
	  50% { background-position: 100% 50%; }
	  100% { background-position: 0% 50%;}
	}
    
    </style>
        <div class="inner" style="font-family: 'GmarketSansMedium';">
            <h1 style="color:black; font-size:48px;">MOVIE CHART</h1>
            <div class="wrap">
            	<c:forEach var="dto" items="${requestScope.movieList }">
	                <article>
	                    <div class="pic">
	                    	<a href="./MovieContent.mo?m_id=${dto.m_id }&pageNum=${requestScope.pageNum }">
		                        <c:choose>
									<c:when test="${fn:contains(fn:toLowerCase(dto.mv_picture), 'jpg') 
									or  fn:contains(fn:toLowerCase(dto.mv_picture), 'png')
									or  fn:contains(fn:toLowerCase(dto.mv_picture), 'gif')
						 			or fn:contains(fn:toLowerCase(dto.mv_picture), 'jpeg') }">
										<img src = "./image/${dto.mv_picture}"  alt=${dto.m_nm }>
									</c:when>
									<c:otherwise>
										<img src = "./moviePoster/${dto.mv_picture}.jpg"  alt=${dto.m_nm }>
									</c:otherwise>
								</c:choose>
							</a>
	                    </div>
	                    <div class="reservebtn" style=" width:155px; height:35px; margin: 0 auto; background:#a3aeb8; border-radius: 10px;">
	                    	<h2 style="text-align: center; margin:0 auto; background:#a3aeb8;"><a style="color:white;" href="./Reservation.res">예매하기</a></h2>
	                    </div>
	                </article>
               	</c:forEach>
            </div>
        </div>
    </section>
   	<%@ include file="/footer.jsp" %>
</body>

</html>