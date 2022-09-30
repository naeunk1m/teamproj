<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CinemaWill</title>
</head>
<body>
	<h1>test</h1>
</body>
</html>
<body>
	<!-- index.jsp -->
	<%
		// MVC 프로젝트 실행 시작페이지

		//** MVC 프로젝트에서는 index.jsp 페이지 외 절대로 .jsp페이지 실행금지
		// => 주소창에 .jsp 주소가 보이면 MVC패턴 깨짐

		// response.sendRedirect("./Test.bo");

		// 글쓰기 페이지 이동
		//response.sendRedirect("./BoardWrite.bo");

		// 글 리스트 페이지 이동
		response.sendRedirect("../MovieList.mo");

		// 상대주소

		// 가상 주소 사용시
		// java(서블릿:컨트롤러) :  ./Test.bo
		//  . (프로젝트명)

		// 실제 주소 사용시
		// jsp(view) :  ./Test.jsp
		//  . (WebContent)
	%>


</body>
</html>