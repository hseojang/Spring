<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>멤버가 접속하는 페이징</h1>
<h2>${accessUser} 가 접속 중</h2>
<a href="<%=request.getContextPath()%>/logout">로그아웃</a>
</body>
</html>