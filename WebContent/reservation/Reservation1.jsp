<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>예매하기</title>
    <link rel="icon" href="favicon.ico" type="image/x-icon">
    <script src="https://kit.fontawesome.com/daddbc6c0e.js" crossorigin="anonymous"></script>
   <!--  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> -->
   <script type="text/javascript" src="./reservation/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="css/step1.css">
<script type="text/javascript">
$(document).ready(function(){
   

    $('#selectboxMovie').change(function(){
    // alert("성공");
       $('#selectboxDate').html("");
       // alert($(this).val());
       //alert($(this).val());
       $.ajax({
          url:'./ReservationDate.res',
          dataType:'JSON',
          data:{'m_id':$(this).val()},
          success:function(rdata){
             
             console.log(rdata);
             $.each(rdata,function(index,item){
             console.log(item);
             console.log(item.s_time);
                $('#selectboxDate').append('<option value="'+item.s_date+'">'+item.s_date+'</option>');
                // $('#selectboxTime').append('<option value="${dto.s_time }">'+item.s_time+'</option>');
                // $('#divdate').append('<label><input type="radio" name="s_date" value="'+item.s_date+'"><input type="hidden" name="h_m_id" value="'+item.m_id+'">'+item.s_date+'</label><br>');
             });
          }  
       });  
    });
   
    // $(document).on('click', 'input[name=s_date]', function(){
       $('#selectboxDate').change(function(){
          // alert($(this).val());
          // alert($(this).next().val());
         
          console.log($("#selectboxDate").next().val());
         
          $('#selectboxTime').html("");
          $.ajax({
             url:'./ReservationTime.res',
             dataType:'JSON',
             data:{'m_id':$("#selectboxMovie").val(),
                   's_date':$("#selectboxDate").val()},
                   
             success:function(rdata){
                //alert("성공");
             
                $.each(rdata,function(index,item){
               
                 $('#selectboxTime').append('<option value="'+item.s_time+'">'+item.s_time+'</option>');
                // $('#divtime').append('<label><input type="radio" name="time" value="'+item.s_time+'"><input type="hidden" name="h_m_id" value="'+item.m_id+'">'+item.s_time+'</label><br>');
                });
             }
          });
       });
         
          /////////////////////////////////////////////////////////////////////
         
         
   
    //영화 날짜 시간 선택하지 않으면 넘어가지 않게
 

    $('#submit').click(function(){
       // var m_id = $('input[name=m_id]:selected').val();
       // var s_date = $('input[name=s_date]:selected').val();
       // var s_time = $('input[name=time]:selected').val();
       
       var m_id = $('#selectboxMovie option:selected').val();
       var s_date = $('#selectboxDate option:selected').val();
       var s_time = $('#selectboxTime option:selected').val();
       if(m_id == ""){
          alert("영화를 선택하세요");
          return false;
       } else if(s_date == ""){
          alert("날짜를 선택하세요");
          return false;
       } else if(s_time == ""){
          alert("시간을 선택하세요");
          return false;
       }
    });
 

});

</script>


</head>
<body>

   <%@ include file="/header.jsp" %>

         <section id="background">
         <form action="./SeatSelect.res" method="post">
        <h1 style=" font-size: 30px; font-weight: 500; margin-bottom: 20px; "><em>영화 예매하기</em></h1>
        <div id="wrap">
            <div id="check2" style="width: 1200px; height: 400px; ">
                <div class="step1" style="background: white;" >
                   
                    <select name="m_id" id="selectboxMovie" >
                        <option value="" >영화</option>
                        <c:forEach var="dto" items="${reservationList }">
                           <option value="${dto.m_id }">${dto.m_nm }</option>
                        </c:forEach>
                     </select>
                </div>
                <div class="step2"  style="background: white;">
                    <select name="s_date" id="selectboxDate" >
                        <option value="">날짜</option>
                     </select>
                   
                </div>
                <div class="step3"  style="background: white;">
                    <select name="time" id="selectboxTime" >
                        <option value="">시간</option>
                    </select>
                </div>
            </div>

            <div id="button" >
                <input type="submit" id="submit" class="button" value="인원/좌석 선택하기">
                <input type="reset" class="button" value="다시 선택하기">
            </div>
   
           
        </div>
       
   </form>
    </section>
   
   <%@ include file="/footer.jsp" %>
</body>

</html>