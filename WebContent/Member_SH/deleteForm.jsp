<%@page import="com.cinemaw.member.db.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
	<%@ include file="/header.jsp" %>
<%
// 로그인 처리 -> 로그인 x (로그인페이지 이동)
String u_id = (String) session.getAttribute("loginId");
if( u_id == null ){
	 response.sendRedirect("./MemberLogin.me");
}
// 회원 비밀번호만 입력받아서 deletePro.jsp페이지 이동후 삭제
// 비밀번호와 db 비밀번호 일치 -> 삭제하면안됨 비번이 같은 회원이 있을 수 있음
// 따라서 
%> 
<fieldset>
	<legend>회원탈퇴</legend>
	<form action="./MemberDeleteAction.me" method="post">
	<!-- input타입중 hidden은 화면에 있는 해당 input태그를 숨겨서 정보 전달   -->
		아이디 : <input type="text" name="u_id" value="<%=u_id %>" readonly><br>
		비밀번호 : <input type="password" name="u_pw"><br>

		<input type="submit" class="btn" value="탈퇴하기">
		<input type="button" class="btn" value="뒤로가기" onclick="location.href='./Main.mo'">
	</form>
</fieldset>
	<%@ include file="/footer.jsp" %>
</body>
</html>
</body>
</html>