<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>결제페이지</title>
    <link rel="icon" href="favicon.ico" type="image/x-icon">
    <script src="https://kit.fontawesome.com/daddbc6c0e.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/pay.css">
<!-- <script src="//code.jquery.com/jquery-3.3.1.min.js"></script> -->
<script src="./reservation/jquery-3.6.0.js"></script>
<script type="text/javascript">
/* 포인트 사용하면 바로 반영되게 */
$( document ).ready( function() {
    $('#r_user_point').change( function() {
      var a = $( '#r_user_point' ).val();
      var b = $('#point').val();
      var totalPoint = Number(b) - Number(a)
      $( '#pointS' ).text( totalPoint );
      $( '#use' ).text( -Number(a) );
      
      var x = $( '#r_adult' ).val();
      var y = $( '#r_teenager' ).val();
      var z = $( '#r_elderly' ).val();
      var totalPay = Number(x)*15000 + Number(y)*11000 + Number(z)*5000 - Number(a);
      $( '#r_pay_price' ).text( totalPay );    
      
    });

});
</script>
</head>
<body>
<!-- 로그인 제어하기 넣어야함 // 일단 이렇게 적어놓는다 -->
<%

	request.setCharacterEncoding("UTF-8");
	//세션영역에 있는 로그인 아이디 정보를 가져오기
	String id =	(String)session.getAttribute("loginId");
	if(id == null){
		// 로그인을 안했다
		System.out.println("로그인 정보 없음");
		response.sendRedirect("./MemberLogin.me");
	}
	//결제페이지에서 10분이 지나면 영화선택 페이지로 이동한다
	response.addHeader("Refresh", "60; url=./RefreshPage.res");
%>
	<!-- jQuery -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
	
	
	<script type="text/javascript">
	
	 	var IMP = window.IMP; // 생략 가능
	    IMP.init("imp10247484"); // 예: imp00000000
	    
	    function requestPay() {
	        IMP.request_pay({ // param
	            pg: "html5_inicis",
	            pay_method: "card",
	          	merchant_uid: new Date().getTime()+"-"+"${r_id}",
	            //merchant_uid: "12412412412222",
	            name: "영화",
	          	amount: ${param.r_adult*15000 + param.r_teenager*11000 + param.r_elderly*5000 } - Number($( '#r_user_point' ).val()),
	            //amount:100, //혹시결제 테스트하고 싶으면
	          	//buyer_email: "gildong@gmail.com",
	            buyer_name: "${dtoR.u_id}",
	            //buyer_tel: "010-4242-4242",
	            //buyer_addr: "서울특별시 강남구 신사동",
	            //buyer_postcode: "01181"
	        }, function (rsp) { // callback
	            if (rsp.success) {
	                // 결제 성공 시 로직,
	                alert("결제가 완료되었습니다.");
	                $('#fr').attr('action', './PayAction.res').submit();
	            } else {
	                // 결제 실패 시 로직,
	                alert("결제에 실패하였습니다.");
	                history.back();
	            }
	        });
	      }
	
	
	</script>
	
	
	<%@ include file="/header.jsp" %>

    <section id="background">
    <form action="./PayAction.res" id="fr" method="post">  
        <h1>영화 예매</h1>
        <div id="wrap">
            <div id="check2" style="width: 1200px; height: 400px; ">
                <div class="step1" style="background: white;" >
                    <input type="hidden" name="r_id" id="r_id" value="${r_id }">
                    <h2>예매확인</h2>
                    <h3 style="text-align: center; margin-top:20px;">< ${dtoM.m_nm } ></h3>
                    <div class="mtext" style="text-align: center;">
                        <span>상영관 : ${dtoR.t_id }관</span>&nbsp;&nbsp;<span>상영시간 : ${dtoM.mv_runtime } 분</span>
                        <p>날짜 : ${dtoR.s_date } &nbsp;&nbsp;<span>시간 : ${dtoR.s_time }</span></p>
                        <p>선택좌석 : 
                     		<c:forEach var="seat" items="${paramValues.seat }">
								${seat } &nbsp;
							</c:forEach>
                        </p>

                        <br>
                        <br>
                        <br>                
                        <p>일반 : ${param.r_adult }</p>
                        <p>청소년 : ${param.r_teenager }</p>
                        <p>우대 : ${param.r_elderly }</p>
                        <br>
                        <hr><br>
                        <p><b>총 인원수 : ${param.r_adult + param.r_teenager + param.r_elderly }</b></p>
                        <input type="hidden" name="r_adult" id="r_adult" value="${param.r_adult }">
						<input type="hidden" name="r_teenager" id="r_teenager" value="${param.r_teenager }">
						<input type="hidden" name="r_elderly" id="r_elderly" value="${param.r_elderly }">
		
                        <c:forEach var="seatss" items="${paramValues.seat }">
                            <input type="hidden" name="r_seat" id="r_seat" value="${seatss }">
                        </c:forEach>
                    </div>
                </div>


                <div class="step2"  style="background: white; ">
                    <h2>포인트사용</h2>
                    <h3 style="margin-left: 12px; margin-top:20px; text-align: center;">포인트 결제</h3><br>
                    <p style="margin-left: 12px; text-align: center;"><span>사용 가능 포인트 :</span>&nbsp;<span id="pointS" >${dtoP.point } 포인트 입니다.</span></p>
                    <input type="hidden" name="point" id="point" value="${dtoP.point }">
                    <br>
                    <br>
                    <br>
                    <p style="text-align: center;">
                    <span style="margin-left: 12px;">포인트 사용 : 
                    <input type="number" style="margin-left: 12px" name="r_user_point" id="r_user_point" max="${dtoP.point }" min="0" step="100" value="0"> P
                    <input type="button" class="btn3" value="사용하기" /></span>
                    <br><small style="margin-left: 12px; text-align: center; ">(포인트는 100 단위로 사용가능합니다.)</small>
                    </p>
                    <br>
                    <br>
                    <br>
                </div>


                <div class="step3"  style="background: white;">
                    <h2>최종결제</h2>
                    <div class="pcalc" style="margin-top: 24px; margin-left: 12px;">
                        <span style="font-size: 24px;">결제금액 </span><br>
                        <span><b>${param.r_adult*15000 + param.r_teenager*11000 + param.r_elderly*5000 }(원)</b></span><br>
                        <span><b>-</b></span><br>
                        <span style="font-size: 24px;">할인금액 </span><br>
                        <span><b> 할인 금액 출력(원)</b></span>
                </div>
                <div class="pcheck"  style="margin-top: 24px; margin-left: 12px;">
                    <span><h3>최종 결제금액</h3></span>
                    <p ><span id="r_pay_price">${param.r_adult*15000 + param.r_teenager*11000 + param.r_elderly*5000 }(원)</span></p>
                    <input type="hidden" name="r_pay_type" value="card">
                </div> 
               
                </div>
                
            </div>
            
        </div>
        <div id="button" style="float: left;">
            <input type="button" onclick="requestPay();"class="button" value="결제하기">
            <input type="button" class="button" value="결제취소" onclick="location.href='./Main.mo';">
        </div>
    </form> 
    </section>
    
    <%@ include file="/footer.jsp" %>




</body>

</html>
</body>
</html>