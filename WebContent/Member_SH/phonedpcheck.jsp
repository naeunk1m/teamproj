<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!static final String DRIVER = "com.mysql.cj.jdbc.Driver";
   static final String DBURL = "jdbc:mysql://itwillbs8.cafe24.com:3306/itwillbs8";
   static final String DBID = "itwillbs8";
   static final String DBPW = "itwillbs8030909";%>
<%

String phone = request.getParameter("phone");
//1. 드라이버 로드(생략)
Class.forName(DRIVER);
//2. 디비연결 
Connection con = DriverManager.getConnection(DBURL,DBID,DBPW);
//3. SQL 작성(select) & pstmt객체
String sql = "select * from user_master where phone=?";
PreparedStatement pstmt = con.prepareStatement(sql);
// ???
pstmt.setString(1, phone);   
//4. SQL 실행
ResultSet rs = pstmt.executeQuery();
//5. 데이터처리
String result="";
int result3=0;
if(rs.next()){
   //휴대폰번호 있음 => 휴대폰번호 중복
//          result="휴대폰번호 중복";
         result3=1;
               
} else {
   //휴대폰번호 없음 => 휴대폰번호 사용 가능
//          result="휴대폰번호 사용가능";
         result3=0;
}
%><%=result3 %>