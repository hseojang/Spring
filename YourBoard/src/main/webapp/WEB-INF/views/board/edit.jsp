<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>아주 볼품없는 게시글 작성</h1>
<form action="${pageContext.request.contextPath}/board/edit" method="post">
<input type="hidden" name="boardNo" value="${vo.boardNo}">
제목 <input type="text" name="boardTitle" value="${vo.boardTitle}" required><br>
작성자 <input type="text" name="boardWriter" value="${vo.boardWriter}" required><br>
내용 <br>
<textarea name="boardContent" required><c:out value="${vo.boardContent}" /></textarea>
<input type="submit" value="등록">
</form>
</body>
</html>