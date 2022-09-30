<%@page import="com.cinemaw.rsv.db.ReserveDTO"%>
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
    <style type="text/css">
    #all1 { text-align: center; }
    </style>
</head>

<body>
<%@ include file="/header.jsp" %> 

	 
	
	<div class="text-area">
            <h2 class="greeting">📽예매내역 </h2>
        </div>

	<% 
	List<ReserveDTO> reserveList = (List<ReserveDTO>)request.getAttribute("reserveList");
	%>
	
	
	<% if(reserveList.size() == 0) { %>
			<br><br><br><br>
				<div id="all1">예매 내역이 존재하지 않습니다.</div><br><br><br>
			
			<%
				
			} else { %>
		
	<div class="point">
	<style>
		@media all and (max-width:768px){
			table, thead, th, td, tr{
				display:block;
			}
			th{ text-align:right;}
			table{position: relative; padding-bottom: 0; border: none; box-shadow: 0 0 10px rgba(0,0,0.2);}
			thead{float:left; white-space: nowrap;}
  			tr {display: inline-block;vertical-align: top; color:white;}
			th {border-bottom: 1px solid #a39485;}
			td {border-bottom: 1px solid #e5e5e5;}
		}
	</style>
	<table style="border: 1px #a39485 solid; font-size: .9em; box-shadow: 0 2px 5px rgba(0,0,0,.25); width:60%; border-collapse: collapse; border-radius: 5px; overflow: hidden;">
	<style> .td, th{padding: 1em .5em; vertical-align: middle;}</style>
	<thead style="font-weight: bold; color:#fff; background: #73685d;">
	<tr>
		<th>관람일</th>
		<th>영화</th>
		<th>결제유형</th>
		<th>결제금액</th>
		<th>예매번호</th>
	</tr>
	</thead>
	
 <% for(int i=0;i<reserveList.size();i++){ 
           // DB -> DTO -> List
          ReserveDTO dto = reserveList.get(i);
      %>
		
	<tr>
		<td><%=dto.getS_date() %></td>
		<td><%=dto.getM_nm() %></td>
		<td><%=dto.getR_pay_type() %></td>
		<td><%=dto.getR_pay_price() %></td>
		<td><%=dto.getR_id() %></td>
	</tr>
	<% 
	 }
 }	
	%>
	</table>
	</div>
    

<script src="js/mypage.js"></script>
<%@ include file="/footer.jsp" %>
</body>

</html>