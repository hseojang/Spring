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
<c:set var="curPage" value="${pageDTO.pageCondDTO.pageNum}"></c:set>
<c:set var="pageSize" value="${pageDTO.pageCondDTO.pageSize}"></c:set>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<h1>아주 볼품없는 게시판 목록</h1>
<p>페이지 정보 : ${curPage}</p>
	<table border="1">
		<tr>
			<th>순번</th>
			<th>글번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일자</th>
		</tr>
		<c:forEach items="${boardList}" var="b" varStatus="status" >
			<tr id="id_tr" onmouseover="f_hover(this)" onmouseout="f_out(this)">
				<td><c:out value="${status.index}" /></td>
				<td><c:out value="${b.boardNo}" /></td>
				<td><c:out value="${b.boardWriter}" /></td>
				<td>
				<a href="${contextPath}/board/content?boardNo=${b.boardNo}">
				<c:out value="${b.boardTitle}" /></a></td>
				<fmt:formatDate value="${b.boardRegdate}" var="formattedDate"  pattern="yyyy-MM-dd" />  
				<td><c:out value="${formattedDate}" /></td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${pageDTO.isPrevious()}">
		<input type="button" value="◀◀"" onclick="f_sendPage(${pageDTO.startPage - 1}, ${pageSize})">
	</c:if>
	<c:if test="${!pageDTO.isPrevious()}">
		<input type="button" value="◁◁"" onclick="f_sendPage(${pageDTO.startPage - 1}, ${pageSize})" disabled>
	</c:if>
	<!-- 페이지 출력 시작 -->
	<c:forEach begin="${pageDTO.startPage}" end="${pageDTO.endPage}" step="1" var="page">
	<c:choose>
		<c:when test="${page==curPage}">
			<a href="#" style="background-color:yellow;">${page}</a>&nbsp;
		</c:when>
		<c:otherwise>
			<a href="#" onclick="f_sendPage(${page}, ${pageSize})">${page}</a>&nbsp;
		</c:otherwise>
	</c:choose>
	
<%-- 	<c:if test="${page != curPage}"> --%>
<%-- 			<a href="${pageContext.request.contextPath}/board/list?pageNum=${page}">${page}</a>&nbsp; --%>
<%-- 	</c:if> --%>
	</c:forEach>
	<!-- 페이지 출력 끝 -->
	<c:if test="${pageDTO.isNext()}">
		<input type="button" value="▶▶"" onclick="f_sendPage(${pageDTO.endPage + 1}, ${pageSize})">
	</c:if>
	<c:if test="${!pageDTO.isNext()}">
		<input type="button" value="▷▷"" onclick="f_sendPage(${pageDTO.endPage + 1}, ${pageSize})" disabled>
	</c:if>
	<br><br>
	<input type="button" value="글 작성" onclick="location.href='${contextPath}/board/write'">
	<form id="id_pageForm" action="${contextPath}/board/list" method="get">
	<input type="hidden" name="pageNum" value="">
	<input type="hidden" name="pageSize" value="">
	</form>
	<script>
		// var v_tr = document.querySelectorAll("#id_tr");
		var f_hover = function(p_tr) {
			p_tr.style.backgroundColor = "lightgray";
		}
		
		var f_out = function(p_tr) {
			p_tr.style.backgroundColor = "";
		}	
		var pageForm = document.querySelector("#id_pageForm");
		var f_sendPage = function(p_pageNum, p_pageSize) {
			event.preventDefault();
			document.querySelector("input[name=pageNum]").value = p_pageNum;
			document.querySelector("input[name=pageSize]").value = p_pageSize;
			pageForm.submit();
		}
	</script>
</body>
</html>