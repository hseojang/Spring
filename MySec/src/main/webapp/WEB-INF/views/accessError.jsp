<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>접근 권한이 없습니다</title>
</head>
<body>
<h1>${SPRING_SECURITY_403_EXCEPTION.getMessage()}</h1>
<h1>${msg}</h1>
</body>
</html>