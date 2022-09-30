<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="./css/star.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CinemaWill</title>
<link rel="stylesheet" href="css/reviewWrite.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
   <%@ include file="/header.jsp" %>
    <figure>
        <iframe width="100%" height="100%" src="https://www.youtube.com/embed/${dto.mv_video}"
        frameborder="0" autoplay=1 mute=1;></iframe>
 
    </figure>
   <section>
        <div id="movieInformation">
            <div class="box1">
               <c:choose>
               <c:when test="${fn:contains(fn:toLowerCase(dto.mv_picture), 'jpg') 
                        or  fn:contains(fn:toLowerCase(dto.mv_picture), 'png')
                        or  fn:contains(fn:toLowerCase(dto.mv_picture), 'gif')
                         or fn:contains(fn:toLowerCase(dto.mv_picture), 'jpeg') }">
                  <img src="./image/${dto.mv_picture}" alt="${dto.m_nm }">
               </c:when>
               <c:otherwise>
                  <img src="./moviePoster/${dto.mv_picture}.jpg" alt="${dto.m_nm }"  >
               </c:otherwise>
            </c:choose>
            </div>
            <div class="moInfo">
                <h2>${dto.m_nm }</h2>
                <span>${dto.mv_grade }</span><span>${fn:substring(dto.mv_rlsdate, 0, 4)}-${fn:substring(dto.mv_rlsdate, 5, 7)}-${fn:substring(dto.mv_rlsdate, 8, 10)}</span>
                <p>인물정보 : ${dto.m_person }</p>
                <p>장르: ${dto.mv_genre }</p>
                <p>상영시간 : ${dto.mv_runtime}분</p>
            </div>
        </div>
            <div class="moInfo2">
                <h4>영화 줄거리</h4>
                <p>${dto.m_story }</p>
            </div>
    </section>
    <!-- 민정 원본 -->
<!--    <div style="width: 920px; margin: 0 auto;"> -->
<!--       <table border="1" style="width: 920px; margin: 0 auto;"> -->
<!--          <tr> -->
<!--             <td colspan="5"> -->
<!--                <iframe width="910" height="480" -->
<%--                   src="https://www.youtube.com/embed/${dto.mv_video}" --%>
<!--                   title="YouTube video player" frameborder="0" -->
<!--                   allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" -->
<!--                   allowfullscreen> -->
<!--                </iframe> -->
<!--             </td> -->
<!--          </tr> -->
<!--       </table> -->
<!--       <br> -->
      
<!--       <table border="2" style="width: 920px; margin: 0 auto;"> -->
<!--          <tr> -->
<!--             <td rowspan="3" style="width: 350px;"> -->
<%--             <c:choose> --%>
<%--             <c:when test="${fn:contains(fn:toLowerCase(dto.mv_picture), 'jpg') or  fn:contains(fn:toLowerCase(dto.mv_picture), 'gif') or fn:contains(fn:toLowerCase(dto.mv_picture), 'jpeg') }"> --%>
<%--                   <input type="image" src="./image/${dto.mv_picture}" alt=${dto.m_nm } width="350px" height="480px"> --%>
<%--                </c:when> --%>
<%--                <c:otherwise> --%>
<%--                   <input type="image" src="./moviePoster/${dto.mv_picture}.jpg" alt=${dto.m_nm } width="350px" height="480px"> --%>
<%--                </c:otherwise> --%>
<%--             </c:choose> --%>
<!--             </td> -->
<%--             <td>제목</td><td>${dto.m_nm }</td> --%>
<%--             <td>등급</td><td>${dto.mv_grade }</td> --%>
<!--          </tr> -->
<!--          <tr> -->
<%--             <td colspan="4">${dto.m_person }</td> --%>
<!--          </tr> -->
<!--          <tr> -->
<%--             <td>장르</td><td>${dto.mv_genre }</td> --%>
<%--             <td>상영시간</td><td>${dto.mv_runtime}분</td> --%>
<!--          </tr> -->
<!--          <tr> -->
<!--             <td> -->
<!--                <a href="#" style="display: block;"> -->
<!--                   <img src="image/reservation_btn.jpg" alt="" style="width: 350px;" height="50px"> -->
<!--                </a> -->
<!--             </td> -->
<!--             <td>개봉일</td> -->
<%--             <td colspan="3">${fn:substring(dto.mv_rlsdate, 0, 4)}-${fn:substring(dto.mv_rlsdate, 5, 7)}-${fn:substring(dto.mv_rlsdate, 8, 10)} --%>
<!--             </td> -->
<!--          </tr> -->
<!--          <tr> -->
<%--             <td colspan="5">${dto.m_story }</td> --%>
<!--          </tr> -->
<!--       </table> -->
      <!-- 민정 원본 끝 -->
      <div align="right" style="margin-top: 5px;">
         <input type="button" value="목록" onclick="location.href='./MovieList.mo'">
      </div>
      
      <div style="display: block; margin: 0 auto; width: 920px; ">
         <h2>평점 및 한줄평 </h2>
         <!-- 
            form 태그에서 특정 페이지로 데이터를 전송하려면
            가장 간단한 방법으로 action속성을 적용
            action="/insertReviewData" 이런식
            그래야 아래 버튼을 만들어서 전송기능을 구현하기 편함
            
            <form action="여기에 서블릿 경로." class="mb-3" name="myform" id="myform" method="post"> 
            서블릿 작업 후에 추가하면 된다                 
          -->
          
       <form class="mb-3" name="myform" id="myform" method="post" action="./InsertReview.re">
         <input type= "hidden" name="m_id" value="${dto.m_id }">
         <input type= "hidden" name="u_id" value="${sessionScope.loginId }">
         <input type= "hidden" name="pageNum" value="${param.pageNum }">
         <input type="hidden" name="cmtAble" value="${dto.cmtAble}">
         <input type="hidden" name="reserveId" value="${dto.reserveId}">
         <input type="hidden" name="reviewCnt" value="${dto.reviewCnt}">
         
         
               <!--    
         
         ㅣReviewVO [id=null, score=null, text=null, userid=, rvw_regdate=null, rvw_revise=null, m_id=6]
          -->
            <fieldset>
               <span class="text-bold">별점 및 한줄평 작성시 100포인트 지급</span> 
               <input type="radio" name="r_score" value="5" id="rate1"><label for="rate1">★</label>
               <input type="radio" name="r_score" value="4" id="rate2"><label for="rate2">★</label>
               <input type="radio" name="r_score" value="3" id="rate3"><label for="rate3">★</label> 
               <input type="radio" name="r_score" value="2" id="rate4"><label for="rate4">★</label>
               <input type="radio" name="r_score" value="1" id="rate5"><label for="rate5">★</label>
            </fieldset>
            <div>
               <textarea class="col-auto form-control" name="r_text" id="reviewContents"
                  placeholder="평점 및 영화 관람평을 작성해주세요. 주제와 무관한 리뷰 또는 스포일러는 표시제한 또는 삭제될 수 있습니다. 작성하신 평점 및 관람평은 CinemaWill에 도움이 됩니다."></textarea>
            </div>
            <div class="submit">
                    <input type="button" value="리뷰등록" onclick="reviewSubmit()"> 
                </div>
                <!-- 민정 원본 -->
<!--             <div align="right" style="margin-top: 5px;"> -->
<!--                <input type="button" value="작성" onclick="reviewSubmit()"> -->
<!--             </div> -->
                <!-- 민정 원본 끝-->
         </form>
         <script>
            function reviewSubmit(){
               const  mid=   document.myform.m_id.value;
               const  uid=   document.myform.u_id.value;
               const  score=document.myform.r_score.value;
               const  rtext=document.myform.r_text.value;
               const cmtAble=document.myform.cmtAble.value;
               const reviewCnt=document.myform.reviewCnt.value;
               
               if(!mid){
                  alert("영화 아이디를 입력해 주세요.");
                  return ;
               }
               if(!uid){
                  alert("로그인을 해 주세요.");
                  return ;
               }
               if(!score){
                  alert("평점을 선택 해 주세요.");
                  return ;
               }
               if(!rtext){
                  alert("내용을 입력해 주세요.");
                  return ;
               }
               
               if(cmtAble==0){
                  alert("영화를 관람한 사람만 등록 가능합니다.");
                  return;
               }
               
               if(reviewCnt==1){
                  alert("이미 평점을 등록 하였습니다.");
                  return;
               }
               // alert(score);
               

               
               
               document.myform.submit();
               
            }
            
            const write='${param.write}';
            window.onload=function(){
               setTimeout(() => {                  
                  if(write=="ok"){
                     alert("등록 처리 되었습니다.");
                     //파라미터 값 지우기
                     history.replaceState({}, null, location.pathname);

                  }                  
               }, 500);
            }
            

         </script>
         
         
         <div style="margin-bottom: 50px;"></div>
         
         <a href="MovieContent.mo?m_id=${param.m_id}&order=new&page=1" class="btn-new">최신순</a> 
         <a href="MovieContent.mo?m_id=${param.m_id}&order=like&page=1"  class="btn-like">공감순</a>  
         <a  href="#" class="reviewBtn">리뷰 수정하기</a>
         <table border="1" style="width: 920px;">
            <thead>
               <!-- 이 부분은 나중에 db에서 읽어온 내용을 전달해줄 수 있도록 작업? 맞는지? -->
               <!-- 여기부터 -->
               <!-- sql 쿼리 -->
               
               <tr>
                  <td style="width: 15%;">작성자</td>
                  <td style="width: 15%;">별점</td>
                  <td>내용</td>
                  <td style="width: 20%;">작성일시</td>
                  <td style="width: 20%;">수정일시</td>
               </tr>
               

 



               <!-- 여기까지 -->
            </thead>
            <tbody>
               <c:forEach var="row" items="${reviewlist}">
                 <c:if test="${row.u_id eq sessionScope.loginId }">
                    <tr id="myReviewForm" style="display:none">
                       <td colspan="5">
                       
                     <form class="mb-4" name="updateRe" id="updateRe" method="post" action="./UpdateReview.re">

                     <h3>리뷰수정하기  댓글 번호</h3>
                     <input type= "hidden" name="r_id" value="${row.r_id  }">
                     <input type= "hidden" name="m_id" value="${row.m_id }">
                     <input type= "hidden" name="u_id" value="${sessionScope.loginId }">
                     <input type= "hidden" name="pageNum" value="${param.pageNum }">            
                     
                     <fieldset>
                        <span class="text-bold">별점 및 한줄평 작성시 100포인트 지급</span> 
                        <input type="radio" name="r_score" value="1" id="rate2-1" ${ row.r_score >= 1 ?'checked':'' } >
                        <label for="rate2-1" id="label-rate2-1"   value="1"   class="${ row.r_score >= 1 ?'on':'' }" >★</label>
                        
                        <input type="radio" name="r_score" value="2" id="rate2-2" ${ row.r_score >=2 ?'checked':'' }>
                        <label for="rate2-2" id="label-rate2-2"  value="2"  class="${ row.r_score >= 2 ?'on':'' }">★</label>
                        
                        <input type="radio" name="r_score" value="3" id="rate2-3" ${ row.r_score >= 3 ?'checked':'' }>
                        <label for="rate2-3" id="label-rate2-3" value="3" class="${ row.r_score >= 3 ?'on':'' }">★</label> 
                        
                        <input type="radio" name="r_score" value="4" id="rate2-4" ${ row.r_score >=3 ?'checked':'' }>
                        <label for="rate2-4" id="label-rate2-4"  value="4" class="${ row.r_score >= 4 ?'on':'' }">★</label>
                        
                        <input type="radio" name="r_score" value="5" id="rate2-5" ${ row.r_score >= 5 ?'checked':'' }>
                        <label for="rate2-5" id="label-rate2-5" value="5" class="${ row.r_score >= 5 ?'on':'' }">★</label>
                     </fieldset>

                     <script>
                        $(function(){
                           $("#updateRe label").on("click", function(evt){   
                              const num=Number($(this).attr("value"));
                              $("#updateRe label").removeClass("on");
                              
                              let label=document.querySelectorAll("#updateRe label");
                                             
                               label.forEach(item=>{
                                  let itemNumber= Number($(item).attr("value"));
                                                                    
                                   if(itemNumber <= num ){
                                      console.log("itemNumber : ", itemNumber);
                                      $(item).addClass("on");
                                  }else{
                                     return;
                                  }
                                 
                              })
                                                                     
                           });
                           
                           $(".reviewBtn").on("click", function(e){
                              e.preventDefault();
                              
                              const display=$("#myReviewForm").css("display");
                              console.log(display);
                              if(display=="none"){
                                 $("#myReviewForm").show();
                                 return;
                              }
                              
                              $("#myReviewForm").hide();
                           })
                        })                  
                     </script>
                     <div>
                        <textarea class="col-auto form-control" name="r_text" id="reviewContents"
                           placeholder="평점 및 영화 관람평을 작성해주세요. 주제와 무관한 리뷰 또는 스포일러는 표시제한 또는 삭제될 수 있습니다. 작성하신 평점 및 관람평은 CinemaWill에 도움이 됩니다.">${row.r_text }</textarea>   
                                             </div>
                     <div class="submit">
<!--                              <input type="button" value="리뷰 수정하기" onclick="./UpdateReview.re?r_score=r_score">  -->
                             <input type="button" value="리뷰 수정하기" onclick="updateReview()"> 
                         </div>
         
                  </form>
   
                  
                  <script>
                  
            function updateReview(){
               
               //alert('check');
               /* const  mid=   document.updateRe.m_id.value; */
               const  uid=   document.updateRe.u_id.value;
               const  score=   document.updateRe.r_score.value;
               const  rtext=   document.updateRe.r_text.value;
               /* const cmtAble=document.updateRe.cmtAble.value;
               const reviewCnt=document.updateRe.reviewCnt.value; */
               // 없는 데이터를 찾고 ,  r_score 입력여부 확인   // jQuery 써보는게 더 간단할 것!
               //alert(score);
               
         /*       if(!mid){
                  alert("영화 아이디를 입력해 주세요.");
                  return ;
               } */
               if(!uid){
                  alert("로그인을 해 주세요.");
                  return ;
               }
               if(!score){
                  alert("평점을 선택 해 주세요.");
                  return ;
               }
               if(!rtext){
                  alert("내용을 입력해 주세요.");
                  document.updateRe.r_text.focus();
                  return ;
               }
               
            /*    if(cmtAble==0){
                  alert("영화를 관람한 사람만 등록 가능합니다.");
                  return;
               }
               
               if(reviewCnt==1){
                  alert("이미 평점을 등록 하였습니다.");
                  return;
               }
 */
               
               document.updateRe.submit();
               
            }
            
            
            

         </script>
                  
                  
                  
                  


                  </td>
                    </tr>
                 </c:if>
                 
                 
                  <tr>
                     <td>${row.u_id }</td>
                     <td>
                        <div class="star-ratings">
                           <div class="star-ratings-fill space-x-2 text-lg" :style="{ width: ratingToPercent + '%' }" >
                              <c:forEach var="rating" varStatus="status" begin="1" end="${ row.r_score }"><span>★</span></c:forEach>
                           </div>
                           <div class="star-ratings-base space-x-2 text-lg">
                              <c:forEach var="rating" varStatus="status" begin="1" end="${ row.r_score }"><span>★</span></c:forEach>
                           </div>
                        </div>
                     </td>
                     <td>${row.r_text }</td>
                     <td>${row.rvw_regdate }</td>
                     <td>${row.rvw_revise }</td>                     
                  </tr>
            
               </c:forEach>
            </tbody>
         </table>
      </div>
   
      <div style="margin-bottom: 50px"></div>
   
   
   <%@ include file="/footer.jsp" %>
</body>
</html>