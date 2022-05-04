<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/user/login" method="post">
	<input type="text" name="nm_admin" value="admin">
	<input type="submit" value="전송">
</form>
</body>
</html>