<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- WEB-INF 폴더는 브라우져에서 다이렉트로 접속이 안됨(기억) 
     webapp/resources 폴더는 사용자에게 열려있음
     css,js,image등을 넣어두면 좋겠네용
-->
<% for(int i=1; i<=5; i++){ %>
 <h1>405호 짜짠</h1>
<% } %>
</body>
</html>