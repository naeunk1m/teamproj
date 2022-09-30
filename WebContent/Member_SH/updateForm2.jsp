<%@page import="com.cinemaw.member.db.MemberVo"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.cinemaw.member.db.MemberDAO3"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    
    <%@ include file="/header.jsp" %>
    
    
<%

MemberVo mb = (MemberVo) request.getAttribute("mb");

System.out.println("뷰 : 액션페이지에서 정보 전달받음 " +mb);

%>

<section>
<legend><h1>회원정보수정</h1></legend>
<form action="./MemberUpdatePro.me" method="post" name="fr">
		<h4>아이디</h4>
		<div class="textForm">
		<input type="text" name="u_id" value="<%=mb.getU_id() %>"readonly>
		</div>
		<h4>비밀번호</h4>
		<div class="textForm">
		<input type="password" name="u_pw" placeholder="비밀번호를 입력하세요" required>
		<div class="textForm">
		<h4>이름</h4>
		<div class="textForm">
		<input type="text" name="u_nm" value="<%=mb.getU_nm() %>">
		</div>
		<h4>생년월일</h4>
		<div class="textForm">
		<input type="date" name="birth" value="<%=mb.getBirth() %>"readonly><br>
		</div>
		<h4>이메일</h4>
		<div class="textForm">
		<input name="email1" type="text" class="email" placeholder="이메일"> @ <input name="email2" class="email2" list="domains"
                        placeholder="주소 선택">
                        <datalist id="domains">
                            <option value="직접입력"></option>
                            <option value="naver.com"></option>
                            <option value="google.com"></option>
                            <option value="nate.com"></option>
                            <option value="yahoo.co.kr"></option>
                        </datalist>
		</div>
		<div class="textForm">
		<h4>전화번호</h4>
		<input type="tel" name="phone" value="<%=mb.getPhone() %>">
		</div>
		<div class="textForm">
        <h4>즐겨보는 장르를 선택해주세요.</h4><br>
		<input type="checkbox" name="favorit" value="스릴러" >스릴러
		<input type="checkbox" name="favorit" value="미스테리">미스테리
		<input type="checkbox" name="favorit" value="멜로">멜로
		<input type="checkbox" name="favorit" value="로맨틱코미디">로맨틱 코미디
		<input type="checkbox" name="favorit" value="어드벤처">어드벤처
		<input type="checkbox" name="favorit" value="정치">정치
		<input type="checkbox" name="favorit" value="판타지">판타지<br>
		<input type="checkbox" name="favorit" value="애니메이션">애니메이션
		<input type="checkbox" name="favorit" value="다큐멘터리">다큐멘터리
		<input type="checkbox" name="favorit" value="느와르">느와르
		<input type="checkbox" name="favorit" value="공상과학">공상과학
		<input type="checkbox" name="favorit" value="액션">액션
		<input type="checkbox" name="favorit" value="코미디">코미디 
		</div>
            <div class="textForm">
                <h4>뉴스레터 및 (장르)관련 소식을 메일로 받으시겠습니까 ?</h4><br>
		<input type="radio" name="mailing" value="동의">동의
	    <input type="radio" name="mailing" value="동의안함">동의안함
	    </div>
		<br>	
		<input type="submit" class="btn" value="회원정보수정하기">
	</form>
</section>

<button onclick="location.href='main.jsp'">뒤로가기</button>

<input type="button" value="회원탈퇴" onclick="location.href='./MemberDelete.me'">
<%@ include file="/footer.jsp" %>
</body>
</html>