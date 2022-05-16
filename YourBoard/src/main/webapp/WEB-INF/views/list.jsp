<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	border: 1px solid black;
}
</style>
</head>
<body>
<h1>아주 볼품없는 게시판 목록</h1>
	<table border="1">
		<tr>
			<th>글번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일자</th>
		</tr>
		<c:forEach items="${boardList}" var="p">
			<tr>
				<td><c:out value="${p.boardNo}" /></td>
				<td><c:out value="${p.boardWriter}" /></td>
				<td>
				<a href="${pageContext.request.contextPath}/board/content?boardNo=${p.boardNo}">
				<c:out value="${p.boardTitle}" /></a></td>
				<fmt:formatDate value="${p.boardRegdate}" var="formattedDate"  pattern="yyyy-MM-dd" />  
				<td><c:out value="${formattedDate}" /></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<input type="button" value="글 작성" onclick="location.href='${pageContext.request.contextPath}/board/write'">
</body>
</html>