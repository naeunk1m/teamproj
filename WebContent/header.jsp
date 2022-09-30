<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://kit.fontawesome.com/daddbc6c0e.js" crossorigin="anonymous"></script>
<style>
@charset 'utf-8';
* {margin: 0px; padding: 0px; box-sizing: border-box;}
@font-face {
  font-family: 'SANGJUGyeongcheonIsland';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2112@1.0/SANGJUGyeongcheonIsland.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}


/* header */
.header {
  background-color:#BFBFBF; padding: 30px; text-align: center;width: 100%;  }
.header .util {position: absolute; top: 10px;right: 30px;margin: auto;}
.header .util li {float: left; text-align: center; list-style: none;}
.header .util li a {text-align: center;  color: #000;padding: 0px 10px; text-decoration: none;}
.header h1{text-align: center; font-family: 'SANGJUGyeongcheonIsland'; font-size: 6vw; padding: 15px 0; }
#navbar { z-index:100; width:100%; display: flex; margin: 0 auto; overflow: hidden; background-color:#3B4850; text-align: center;}
#navbar a { display: inline-block; color: #f2f2f2; text-align: center; margin: 0 auto; padding: 14px 16px; text-decoration: none; font-size: 17px; width: 15%;}
#navbar a:hover { background-color: #ddd; color: black; }
.sticky {position: fixed; top: 0; width: 100%;}
.sticky + .content { padding-top: 60px;}

</style>
</head>
<body style="background:#bfbfbf">
<%
   boolean isAdmin = false;
   String loginId = null;
   if(null != session.getAttribute("isAdmin"))
      isAdmin = (boolean)session.getAttribute("isAdmin");
   if(null != session.getAttribute("loginId"))
      loginId = (String)session.getAttribute("loginId");
%>

      <div class="header">
          <ul class="util">
              <%if(loginId == null){%>
                 <li><a href="./MemberInsert.me">Join</a></li>
              <%} %>
             <%if(loginId == null){%>
                <li><a href="./MemberLogin.me">Login</a></li>
             <%} %>
              <%if(loginId != null){%>
                 <p style="text-align: center;" id="afterlogin">
                  <b id="userID"><%=loginId %>
                  ${sessionScope.loginId eq 'admin' ? '<a href="AdminMovieWrite.am">(관리자)</a>' :'' }
                  </b>님 환영합니다.               
                              
               </p>
               <li><a href="./MemberLogout.me">Logout</a></li>
            <%} %>
              <%if(loginId != null){%>
                 <li><a href="./myPage.me">MyPage</a></li>
              <%} %>
              <%if(isAdmin == true){%>
                 <li><a id='admin' href='./AdminMovieWrite.am'>영화등록</a></li>
              <%} %>
          </ul>
          <h1><a href="Main.mo" style="color: black; letter-spacing: 24px;">CINEMAWILL</a></h1>
      </div>
      <div id="navbar" >
          <a href="MovieList.mo" style="flex:1;">영화</a> 
          <a href="Main.mo" style="flex:1;">HOME</a> 
          <a href="Reservation.res" style="flex:1;">예매</a> 
      </div>
      
      
          <script>
        window.onscroll = function() {myFunction()};
        
        var navbar = document.getElementById("navbar");
        var sticky = navbar.offsetTop;
        
        function myFunction() {
          if (window.pageYOffset >= sticky) {
            navbar.classList.add("sticky")
          } else {
            navbar.classList.remove("sticky");
          }
        }
        </script> 
</body>
</html>