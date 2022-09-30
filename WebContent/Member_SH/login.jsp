<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/login.css" />
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script src="js/main_script.js"></script>
<title>Cinemaw</title>
</head>

<body>
<%@ include file="/header.jsp" %>
    <section id=background>
        <form action="MemberLoginAction.me" method="post">
        <div class="wrap">
        <br>
        <br>
        <br>
            <div class="login" style="margin-left:35%;">
                <h1 style="margin-top:-100px; margin-bottom:35px;">로그인</h1>
                
                <div class="login_id">
                    <h4>아이디</h4>
                    <input type="text" name="loginId" id="" placeholder="아이디를 입력해주세요.">
                </div>
                <div class="login_pw">
                    <h4>비밀번호</h4>
                    <input type="password" name="loginPw" id="" placeholder="비밀번호를 입력해주세요.">
                </div>
    
                <div class="submit">
                    <input type="submit" value="submit"> 
                </div>
            </div>
        </div>
        </form>
        
    </section>
	<%@ include file="/footer.jsp" %>
</body>

</html>
</body>

</html>