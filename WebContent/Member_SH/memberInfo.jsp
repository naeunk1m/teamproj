<%@page import="com.cinemaw.member.db.MemberVo"%>
<%@page import="com.cinemaw.member.db.MemberDAO3"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//1. 한글설정 & 변수생성
String u_id = (String) session.getAttribute("u_id");//object이기에 형변환필수
//1-1.id값확인 -없으면 로그인페이지로 이동
if(u_id == null){
	response.sendRedirect("./login.jsp");
}
//2. 멤버DAO객체 생성 -> 회원정보 가져오는 메서드생성
MemberDAO3 dao = new MemberDAO3();
MemberVo vo = dao.getMember(u_id);

//3. 데이터처리 : 테이블로 표현
if(vo != null){
%>
<h2>마이페이지</h2>
<table border="1">
	<tr>
		<td>아이디</td>
		<td><%=vo.getU_id() %></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><%=vo.getU_nm() %></td>
	</tr>
	<tr>
		<td>생년월일</td>
		<td><%=vo.getBirth() %></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><%=vo.getEmail() %></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><%=vo.getPhone() %></td>
	</tr>
	<tr>
		<td>회원가입일</td>
		<td><%=vo.getU_join_dt() %></td>
	</tr> 
</table>
<% 
} 
%>
<hr>
<input type="button" value="뒤로가기" onclick="location.href='./Main.mo'">
</body>
</html>