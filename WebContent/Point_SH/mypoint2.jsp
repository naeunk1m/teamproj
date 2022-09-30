<%@page import="com.cinemaw.point.db.PointDTO2"%>
<%@page import="com.cinemaw.point.db.PointDTO"%>
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
    <link rel="stylesheet" href="css/MyPoint.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <style type="text/css">
    #all1 { text-align: center; }
    #all2 { text-align: center; }
    #all3 { text-align: center, font-size:15px; }
    
    </style>
</head>
<body>
<%@ include file="/header.jsp" %>
	


	<div class="point" style="flex:1;">

		<%
			//1. 한글설정 & 변수생성
			request.setCharacterEncoding("UTF-8");
			List<PointDTO> pointList = (List<PointDTO>) request.getAttribute("pointList");
		%>
	<hr>
		<h2>포인트 적립내역</h2>

		<%if(pointList.size() == 0){ %>
			<div id="all2">적립된 포인트 내역이 존재하지 않습니다.</div><br><br><br><br><br>
		<%
		
		} else { %>

		<table>
		<thead style="font-weight: bold; color:#fff; background: #73685d;">
			<tr>
				<th>포인트타입</th>
				<th>포인트 금액</th>
				<th>포인트 지급일</th>
			</tr>
		</thead>

			<%
				for (int i = 0; i < pointList.size(); i++) {
					// DB -> DTO -> List
					PointDTO dto = pointList.get(i);
			%>

			<tr>
				<td><%=dto.getP_type()%></td>
				<td><%=dto.getPoint()%></td>
				<td><%=dto.getP_dt()%></td>
			</tr>
		</table>
			<% }
			List<PointDTO> PointTotal = (List<PointDTO>)request.getAttribute("pointList");
			
			
			int pointTotal = (Integer)request.getAttribute("pointTotal");
				%>
			<div id="all2"><td>💰 누적 포인트는 <%=pointTotal %> 포인트입니다 💰</td></div>   <br><br><br> 
			<%	
			}
		 
			%> 
 		

		</div>
	
	
	
	
		<div class="point">
		<%
			//1. 한글설정 & 변수생성
			request.setCharacterEncoding("UTF-8");
			List<PointDTO2> pointList2 = (List<PointDTO2>)request.getAttribute("pointList2");
		%>

		<h2>포인트 사용내역</h2>
		


			<% if(pointList2.size() == 0) { %>
				<div id="all2">사용한 포인트 내역이 존재하지 않습니다.</div>
			
			<%
				
			} else { %>
				<table>
				<thead style="font-weight: bold; color:#fff; background: #73685d;">	

			<tr>
				<th>예매 번호</th>
				<th>사용 금액</th>
			</tr>			
			</thead>
			<%
							
				for (int i = 0; i < pointList2.size(); i++) {
					// DB -> DTO -> List
					PointDTO2 dto2 = pointList2.get(i);
			%>

			<tr>
				<td><%=dto2.getR_id() %></td>
				<td><%=dto2.getR_user_point() %></td>
			</tr>
			<%
				} 
			%>
			</table>
			
			<% 
			List<PointDTO2> PointTotal1 = (List<PointDTO2>)request.getAttribute("pointList2");
			
 			int pointTotal2 = (Integer)request.getAttribute("pointTotal2");
				%>
			
			
			<div id="all2"><td>💰 사용 포인트는 <%=pointTotal2 %> 포인트입니다 💰</td></div> 
			
			<%	
			}	
			%>

			<% 
			List<PointDTO> PointTotal = (List<PointDTO>)request.getAttribute("pointList");
			int pointTotal = (Integer)request.getAttribute("pointTotal");
			List<PointDTO2> PointTotal1 = (List<PointDTO2>)request.getAttribute("pointList2");
 			int pointTotal2 = (Integer)request.getAttribute("pointTotal2");
			%>
 			<h2>💰사용가능한 포인트는 <%=pointTotal-pointTotal2 %> 입니다.<h2>  
 			<hr>

		
		
		
		


    

<script src="js/mypage.js"></script>
<%@ include file="/footer.jsp" %>
</body>

</html>		


