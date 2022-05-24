<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>아주 볼품없는 게시글 작성</h1>
<!-- Spring에서 제공하는 form 태그라이브러리 -->
<form action="${pageContext.request.contextPath}/board/write" method="post">
제목 <input type="text" name="boardTitle"><br>
작성자 <input type="text" name="boardWriter"><br>
내용 <br>
<textarea name="boardContent" cols="60" rows="10"></textarea><br>
<input type="submit" value="등록">
</form>

</body>
</html>