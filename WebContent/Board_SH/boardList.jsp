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
    <style type="text/css">
    #all1 { text-align: center; }
    </style>
</head>

<body>

<hr>

	<%@ include file="/header.jsp" %>
	

    <section>
        

        
            <h2> 내가 쓴 리뷰 </h2>
            
            <% 
          //1. 한글설정 & 변수생성
   		        request.setCharacterEncoding("UTF-8");
				List<BoardDTO> boardList = (List<BoardDTO>)request.getAttribute("boardList");
			%>	
					
			<% if(boardList.size() == 0) { %>
			<br><br><br><br>
				<div id="all1">작성한 리뷰가 존재하지 않습니다.</div><br><br><br><br>
			
			<%
				
			} else { %>
	<div class="point">
	<style>
		@media all and (max-width:768px){
			table, thead, tbody, th, td, tr{
				display:block;
			}
			th{ text-align:right;}
			table{position: relative; padding-bottom: 0; border: none; box-shadow: 0 0 10px rgba(0,0,0.2);}
			thead{float:left; white-space: nowrap;}
  			tbody {overflow-x:auto; overflow-y:hidden; position:relative; white-space:nowrap;}
  			tr {display: inline-block;vertical-align: top;}
			th {border-bottom: 1px solid #a39485;}
			td {border-bottom: 1px solid #e5e5e5;}
		}
	</style>
            <table style="border: 1px #a39485 solid; font-size: .9em; box-shadow: 0 2px 5px rgba(0,0,0,.25); width:60%; border-collapse: collapse; border-radius: 5px; overflow: hidden;">
                <style> .td, th{padding: 1em .5em; vertical-align: middle;}</style>
                <thead style="font-weight: bold; color:#fff; background: #73685d; height:25px;">
                    <tr>
						<td>예매번호</td>
						<td>별점</td>
						<td>한줄평</td>
						<td>작성자</td>
						<td>작성일</td>
						<td>수정일시</td>
					</tr>
                  </thead>
                  <tbody>
                  		<% for(int i=0;i<boardList.size();i++){ 
          				 // DB -> DTO -> List
         				 BoardDTO dto = boardList.get(i);
   						  %>

						<tr>
							<td><%=dto.getR_id()%></td>
							<td><%=dto.getR_score()%></td>
							<td><%=dto.getR_text()%></td>
							<td><%=dto.getU_id()%></td>
							<td><%=dto.getRvw_regdate()%></td>
							<td><%=dto.getRvw_revise()%></td>
						</tr>
                  </tbody>
						<% }
						}
						%>


			</table>
        </div> 
        
        
<script src="js/mypage.js"></script>
<%@ include file="/footer.jsp" %>
</body>

</html>