<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/star.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CinemaWill</title>
</head>
<body>
	<div style="width: 920px; margin: 0 auto;">
		<h1>movieContent.jsp : 영화정보 상세페이지 ${dto.m_id }</h1>
	
		<%
			//  request.setAttribute("dto", dto);
			// 	request.setAttribute("pageNum", pageNum); //출력할때 사용
		%>
	
		<table border="1" style="width: 920px; margin: 0 auto;">
			<tr>
				<td colspan="5">
					<iframe width="910" height="480"
						src="https://www.youtube.com/embed/${dto.mv_video}"
						title="YouTube video player" frameborder="0"
						allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
						allowfullscreen>
					</iframe>
				</td>
			</tr>
		</table>
		<br>
		
		<table border="2" style="width: 920px; margin: 0 auto;">
			<tr>
				<td rowspan="3" style="width: 350px;">
					
				<c:choose>
				  <c:when test="${fn:contains(fn:toLowerCase(dto.mv_picture), 'jpg') 
					or  fn:contains(fn:toLowerCase(dto.mv_picture), 'png')
					or  fn:contains(fn:toLowerCase(dto.mv_picture), 'gif')
		 			or fn:contains(fn:toLowerCase(dto.mv_picture), 'jpeg') }">
					
						<input type="image" src="./image/${dto.mv_picture}" alt=${dto.m_nm } width="350px" height="480px">
					</c:when>
					<c:otherwise>
						<input type="image" src="./moviePoster/${dto.mv_picture}.jpg" alt=${dto.m_nm } width="350px" height="480px">
					</c:otherwise>
					
				</c:choose>
				
				</td>
				<td>제목</td><td>${dto.m_nm }</td>
				<td>등급</td><td>${dto.mv_grade }</td>
			</tr>
			<tr>
				<td colspan="4">${dto.m_person }</td>
			</tr>
			<tr>
				<td>장르</td><td>${dto.mv_genre }</td>
				<td>상영시간</td><td>${dto.mv_runtime}분</td>
			</tr>
			<tr>
				<td>
					<a href="#" style="display: block;">
					<img src="image/reservation_btn.jpg" alt="" style="width: 350px;" height="50px">
					</a>
				</td>
				<td>개봉일</td>
				<td colspan="3">${fn:substring(dto.mv_rlsdate, 0, 4)}-${fn:substring(dto.mv_rlsdate, 5, 7)}-${fn:substring(dto.mv_rlsdate, 8, 10)}
				</td>
			</tr>
			<tr>
				<td colspan="5">${dto.m_story }</td>
			</tr>
		</table>
		<div align="right" style="margin-top: 5px;">
			<input type="button" value="목록" onclick="location.href='./MovieList.mo'">
		</div>
		
		<div style="display: block; margin: 0 auto; width: 920px;">
			<h2>평점 및 한줄평</h2>
			<!-- 
				form 태그에서 특정 페이지로 데이터를 전송하려면
				가장 간단한 방법으로 action속성을 적용
				action="/insertReviewData" 이런식
				그래야 아래 버튼을 만들어서 전송기능을 구현하기 편함
				
				<form action="여기에 서블릿 경로." class="mb-3" name="myform" id="myform" method="post"> 
				서블릿 작업 후에 추가하면 된다
			 -->
			<form class="mb-3" name="myform" id="myform" method="post" action="./InsertReview.re">
			<input type= "hidden" name="m_id" value="${dto.m_id }">
			<input type= "hidden" name="u_id" value="${sessionID }">
			
			
			
			
				<fieldset>
					<span class="text-bold">별점 및 한줄평 작성시 100포인트 지급</span> 
					<input type="radio" name="r_score" value="5" id="rate1"><label for="rate1">★</label>
					<input type="radio" name="r_score" value="4" id="rate2"><label for="rate2">★</label>
					<input type="radio" name="r_score" value="3" id="rate3"><label for="rate3">★</label> 
					<input type="radio" name="r_score" value="2" id="rate4"><label for="rate4">★</label>
					<input type="radio" name="r_score" value="1" id="rate5"><label for="rate5">★</label>
				</fieldset>
				<div>
					<textarea class="col-auto form-control" name="r_text" id="reviewContents"
						placeholder="평점 및 영화 관람평을 작성해주세요. 
						주제와 무관한 리뷰 또는 스포일러는 표시제한 또는 삭제될 수 있습니다. 작성하신 평점 및 관람평은 CinemaWill에 도움이 됩니다."></textarea>
				</div>
				<div align="right" style="margin-top: 5px;">
				
					<input type="submit" value="작성" >
				</div>
			</form>
			<a href="#">최신순</a> <a href="#">별점순</a>
			<table border="1" style="width: 920px;">
				<tbody>
					<!-- 이 부분은 나중에 db에서 읽어온 내용을 전달해줄 수 있도록 작업? 맞는지? -->
					<!-- 여기부터 -->
					<!-- sql 쿼리 -->
					<tr>
						<td style="width: 15%;">작성자 ID</td>
						<td style="width: 15%;">
							<div class="star-ratings">
								<div class="star-ratings-fill space-x-2 text-lg" :style="{ width: ratingToPercent + '%' }" >
									<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
								</div>
								<div class="star-ratings-base space-x-2 text-lg">
									<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
								</div>
							</div>
						</td>
						<td>리뷰내용</td>
						<td style="width: 20%;">작성일시</td>
						<td style="width: 20%;">수정일시</td>
					</tr>
					<!-- 여기까지 -->
				</tbody>
			</table>
		</div>
	
	
	
	</div>
</body>
</html>