<%@page import="java.util.ArrayList"%>
<%@page import="com.cinemaw.reserve.db.ReserveDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>예매페이지</title>
    <link rel="icon" href="favicon.ico" type="image/x-icon">
    <script src="https://kit.fontawesome.com/daddbc6c0e.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/MandS.css">
    
<script src="./reservation/jquery-3.6.0.js"></script>
<script type="text/javascript">

/* 인원수 고르면 금액/총인원수 반영하기 */
$( document ).ready( function() {
    $('#r_adult, #r_teenager, #r_elderly').change( function() {
      var a = $( '#r_adult' ).val();
      var b = $( '#r_teenager' ).val();
      var c = $( '#r_elderly' ).val();
      var totalNum = Number(a) + Number(b) + Number(c);
      $( '#totalNum' ).text( totalNum );
      var totalPay = Number(a)*15000 + Number(b)*11000 + Number(c)*5000;
      $( '#r_pay_price' ).text( totalPay );
    });
    
     $('#submit').click(function(){
    	var selectamount = $('input:checkbox[name="seat"]:checked').length;
    	var a = $( '#r_adult' ).val();
        var b = $( '#r_teenager' ).val();
        var c = $( '#r_elderly' ).val();
        var totalNum = Number(a) + Number(b) + Number(c);
   		
   		if(totalNum == 0){
   			alert('인원수를 선택해주세요');
   			return false;
   		}else if(totalNum > 4){
   			alert('최대 예매 인원수는 4명 입니다');
   			return false;
   		}
   		else if(selectamount > totalNum){ 
    		alert('선택한 인원수 보다 많은 좌석수입니다.');
   			return false;
   		}
   		else if(selectamount < totalNum){ 
    		alert('선택한 인원수 보다 적은 좌석수입니다.');
   			return false;
   		}
    	
    }); 
});//제이쿼리
//키보드 제어
document.addEventListener('keydown', function(event) {
	  
	    event.preventDefault();
	  
	}, true);
</script>

<!-- 예매된 좌석 가져와서 제어하기 -->
<%
	List s1 = (ArrayList) session.getAttribute("seat_1");
	List s2 = (ArrayList) session.getAttribute("seat_2");
	List s3 = (ArrayList) session.getAttribute("seat_3");
	List s4 = (ArrayList) session.getAttribute("seat_4");
	String sss1 = "";
	String sss2 = "";
	String sss3 = "";
	String sss4 = "";
	for (int i = 0; i < s1.size(); i++) {
		sss1 = (String) s1.get(i);
		sss2 = (String) s2.get(i);
		sss3 = (String) s3.get(i);
		sss4 = (String) s4.get(i);
%>
<script type="text/javascript">
$( document ).ready( function() {
	var se1 = "<%=sss1%>";
	var se2 = "<%=sss2%>";
	var se3 = "<%=sss3%>";
	var se4 = "<%=sss4%>";
		$('#' + se1 + '').prop('disabled', true);
		$('#' + se2 + '').prop('disabled', true);
		$('#' + se3 + '').prop('disabled', true);
		$('#' + se4 + '').prop('disabled', true);
	});
</script>

<%
	}
%>


</head>

<body>
<%
	//세션영역에 있는 로그인 아이디 정보를 가져오기
	String id =	(String)session.getAttribute("loginId");
	if(id == null){
		// 로그인을 안했다
		System.out.println("로그인 정보 없음");
		response.sendRedirect("./MemberLogin.me");
	}
%>

	<%@ include file="/header.jsp" %>
<section id="background">
        
        <h1 style=" font-size: 30px; font-weight: 500; margin-bottom: 20px;margin-left: 270px; color:white;"><em>인원 및 좌석 선택</em></h1> 
        <form action="PayView.res" method="POST" class="seatView">
        <div id="wrap">
            <div id="check2" style="width: 1200px; height: 100%;  ">
               <div class="step1">
                    <div class="poster" style="width:60%; margin: 0 auto;">
                      <img src="./moviePoster/${dtoM.mv_picture }.jpg" style="width: 230px; height:300px; ">
                    </div>
                    <div class="member" style="padding-left:10px; padding-top:10px; background-color: white; width: 230px; height: 33%; margin-left:63px; border-radius: 12px; justify-content: center;">
                        <h4>영화 제목 : ${dtoM.m_nm }</h4>
                        <p><label for="tentacles">성인 :</label>&nbsp;&nbsp;<input type="number" name="r_adult" id="r_adult" min="0" max="4" value="0"></p>
                        <p><label for="tentacles">청소년 :</label>&nbsp;&nbsp;<input type="number" name="r_teenager" id="r_teenager" min="0" max="4" value="0"></p>
                        <p><label for="tentacles">우대 :</label>&nbsp;&nbsp;<input type="number" name="r_elderly" id="r_elderly" min="0" max="4" value="0"></p>
                        <p><label for="tentacles">총 인원수 :</label><b><span id="totalNum"></span>명</b></p>
                        <p><label for="tentacles">가격 :</label><b><span id="r_pay_price"></span></b></p>
                        <small>(최대 예매 인원수는 4명 입니다)</small>
                    </div>
                    
               </div>
               <div class="step2" style="background: rgb(26, 60, 73); height:471.200px;">
               
               <div class="screen" style="background: white; width:80%; height:20%; text-align:center; margin: 0 auto; border-radius: 15px; margin-top:5px; font-size: 24px;"><br>SCREEN</div>
					<div class="seat" style="margin-top:40px; padding-left:20%">
						<c:forEach var="j" begin="1" end="8">
		                  <small style="color:white;">${j }열&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</small>
		                  
		                  <c:forEach var="i" begin="1" end="12">
		                    <input type="checkbox" style="zoom:2.5;" name="seat" id="${j }_${i}" value="${j }_${i}">
		                  </c:forEach>
		              
		                  <br>
		                
		                  <c:if test="${j == 4 }">
		                    <br>
		                  </c:if>
		                
		                </c:forEach>
					</div>
               </div>
            </div>
            <div id="button" >
                <input type="submit" id="submit" class="button" value="결제하기">
                <input type="reset" class="button" value="다시 선택하기">
            </div>
        </div>
      </form>    

    </section>
    
    
    
    
    
    
    
    
	<script src="js/seat.js"></script>
	<%@ include file="/footer.jsp" %>
</body>

</html>   








