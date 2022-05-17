<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>글번호</td>
			<td><c:out value="${vo.boardNo}" /></td>
			<td>작성자</td>
			<td><c:out value="${vo.boardWriter}" /></td>
		</tr>
		<tr>
			<td>작성일</td>
			<td><c:out value="${vo.boardRegdate}" /></td>
			<td>수정일</td>
			<td><c:out value="${vo.boardUpdate}" /></td>
		</tr>
		<tr>
			<td>제목</td>
			<td colspan="3"><c:out value="${vo.boardTitle}" /></td>
		</tr>
		<tr>
			<td colspan="4">내용</td>
		</tr>
		<tr>
			<td colspan="4"><c:out value="${vo.boardContent}" /></td>
		</tr>

	</table>
	<input type="button" value="목록으로" id="id_btnList" onclick="location.href='${pageContext.request.contextPath}/board/list'">	
	<input type="button" value="수정" id="id_btnEdit" onclick="location.href='${pageContext.request.contextPath}/board/edit?boardNo=${vo.boardNo}'">
	<input type="button" value="삭제" id="id_btnDelete">
<script>
var v_btnDelete = document.querySelector("#id_btnDelete");
var f_delete = function() {
	if(confirm("진짜 지워요?")){
		location.href="${pageContext.request.contextPath}/board/delete?boardNo=${vo.boardNo}"
	}
}

v_btnDelete.onclick = f_delete;

</script>
</body>
</html>