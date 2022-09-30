<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../css/recent_movie.css" />
<link rel="stylesheet" href="../css/global.css" />
<link rel="stylesheet" href="../css/login.css" />
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script src="../js/main_script.js"></script>
<title>CinemaWill</title>
 <link rel="stylesheet" href="css/main.css">
</head>

<body>
	<%@ include file="/header.jsp" %>
	<div id="wrapper" style="background: white; width:650px; margin: 0 auto; border-radius: 12px;">
		<article style="margin-bottom:300px">
			<h1 style="text-align: center; font-size: 36px; margin-top:50px; "><em style="margin-top:20px;">영화 등록</em> </h1>
			<form action="${pageContext.request.contextPath}/MovieWriteAction.mo" 
			method="post" name="frm1" enctype="multipart/form-data">
				<table class="loginpage1" style="margin: 0 auto; margin-top: 15px;">
					

					<tbody>
						<tr>
							<td>제목 :</td><br>
							<td colspan="2"><input type="text" name="nm" class="inputinfo" style="border-radius: 12px;" ></td>								
						</tr>	
						<tr>
							<td>줄거리 :</td>
							<td colspan="2">
							<textarea rows="10" cols="10" name="story" style="width: 376px; height: 162px;resize:none; border-radius: 12px;"></textarea>
							
							</td>								
						</tr>
						
						<tr>
							<td>인물정보 :</td>
							<td colspan="2">
							<textarea rows="2" cols="2" name="person" style="width: 376px; height:50px; resize:none; border-radius: 12px; "></textarea>
							</td>								
						</tr>
							
						<tr>
							<td>장르 :</td>
							<td colspan="2">
							
			<select id="genre" name="genre" size="1" onchange="test(this.value)" class="inputinfo" style="border-radius: 12px;">
				<option value="액션">액션</option>
				<option value="코미디">코미디</option>
				<option value="애니메이션" >애니메이션</option>
				<option value="스릴러">스릴러</option>
				<option value="멜로/로맨스">멜로/로맨스</option>
				<option value="범죄">범죄</option>
				<option value="드라마">드라마</option>
				<option value="다큐멘터리" >다큐멘터리</option>
				<option value="미스터리" >미스터리</option>
				<option value="모험" >모험</option>
				<option value="공포" >공포</option>
				<option value="판타지">판타지</option>
				<option value="뮤지컬">뮤지컬</option>
				<option value="공상과학" >공상과학</option>
			</select>
							
							</td>								
						</tr>

						<tr>
							<td>상영시간 :</td>
							<td colspan="2"><input type="number" name="runtime" class="inputinfo" style="border-radius: 12px;"></td>								
						</tr>
						
						<tr>
					   	<td>관람등급 :</td>
							<td colspan="2">
							<select id="grade" name="grade" size="1" onchange="test(this.value)" class="inputinfo" style="border-radius: 12px;">
				<option value="전체관람가">전체관람가</option>
				<option value="12세 이상">12세 이상</option>
				<option value="15세 이상" >15세 이상</option>
				<option value="18세 이상" >18세 이상</option>
			</select></td></tr>			
						
						<tr>
							<td>개봉일 :</td>
							<td colspan="2"><input type="date" name="rlsdate" class="inputinfo" style="border-radius: 12px;"></td>								
						</tr>

						<tr>
							<td>포스터 :</td>
							<td colspan="2">
							<input type="file" name="picture" class="inputinfo">
							</td>								
						</tr>
		

						<tr>
							<td>동영상 :</td>
							<td colspan="2"><input type="text" name="video" class="inputinfo" style="border-radius: 12px;"></td>								
						</tr>		
	

						<tr class="buttonSet">
							<td><input type="button" value="영화 등록" onclick="writeAction()"
								style="font-size: 36px; width: 100%; margin-top:20px;"></td>
						</tr>
					</tbody>

				</table>
			</form>
			
			<script>
			
			function writeAction(){
				const  nm=document.frm1.nm.value;
				const  story=document.frm1.story.value;
 				const  person=document.frm1.person.value;
				const  runtime=document.frm1.runtime.value;
				const  grade=document.frm1.grade.value;
				const  picture=document.frm1.picture.value;
				const  video=document.frm1.video.value; 
				if(!nm){
					alert("이름을 입력해 주세요.");
					document.frm1.nm.focus();
					return;
				}
				if(!story){
					alert("줄거리을 입력해 주세요.");
					document.frm1.story.focus();
					return;
				}
				if(!person){
					alert("주인공을 입력해 주세요.");
					document.frm1.person.focus();
					return;
				}
				if(!runtime){
					alert("상영시간을 입력해 주세요.");
					document.frm1.runtime.focus();
					return;
				}
				if(!grade){
					alert("관람등급을 입력해 주세요.");
					document.frm1.grade.focus();
					return;
				}
				if(!picture){
					alert("포스터를 입력해 주세요.");
					document.frm1.picture.focus();
					return;
				}
				if(!video){
					alert("동영상을 입력해 주세요.");
					document.frm1.video.focus();
					return;
				}
				 
				document.frm1.submit();
				
			}
			
			const write='${param.write}';
			if(write=="ok"){
				alert("등록 처리 되었습니다.")
				history.replaceState({}, null, location.pathname);
			}
			</script>
			
		</article>
	</div>
   	<%@ include file="/footer.jsp" %>
</body>

</html>
</body>

</html>