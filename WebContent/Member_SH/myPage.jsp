<%@page import="java.sql.Timestamp"%>
<%@page import="com.cinemaw.point.db.PointDTO"%>
<%@page import="com.cinemaw.member.db.MemberVo"%>
<%@page import="com.cinemaw.board.db.BoardDTO"%>
<%@page import="com.cinemaw.member.db.MemberDAO3"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인페이지</title>
    <link rel="icon" href="favicon.ico" type="image/x-icon">
    <script src="https://kit.fontawesome.com/daddbc6c0e.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./css/MyPage.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    
</head>

<body>
<%@ include file="/header.jsp" %>
<%
	//1. 한글설정 & 변수생성
request.setCharacterEncoding("UTF-8");
String u_id = (String) session.getAttribute("loginId");
String u_nm = request.getParameter("u_nm");

MemberDAO3 dao = new MemberDAO3();
MemberVo vo = dao.getMember(u_id);

//3. 데이터처리 : 테이블로 표현
if(vo != null){
%>

		
<% 
} 
%>
<hr>


	
	

    <section>
        <div class="text-area">
            <h2 class="greeting"> 💙MyPage💙 </h2>
        </div>
        
		<hr class="hr-how" />
        <div class="btn1">
        	<input type="button" class="btn" value="예매내역" onclick="location.href='./ReserveList.rl'"/><br>
            <input type="button" class="btn" value="마이리뷰" onclick="location.href='./BoardList.bo'"/><br>
        	<input type="button" class="btn" value="포인트 적립/사용 내역" onclick="location.href='./mypoint.po'"/><br>
        	<input type="button" value="회원정보수정" class="btn" onclick="location.href='./MemberUpdate.me';"><br>
        </div>
        <br>
        
        

        
        <br>
        
        

    

<script src="js/mypage.js"></script>
<%@ include file="/footer.jsp" %>
</body>

</html>