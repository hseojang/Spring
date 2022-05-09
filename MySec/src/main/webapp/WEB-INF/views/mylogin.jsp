<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>나의 로그인 페이지</h1>
<h2>action과 URL 개념 잘 기억하기</h2>
<form action="<%=request.getContextPath()%>/login" method="post">
아이디 <input type="text" name="username" value=""><br>
빕번 <input type="password" name="password" value=""><br>
<button>로그인</button>
</form>

</body>
</html>