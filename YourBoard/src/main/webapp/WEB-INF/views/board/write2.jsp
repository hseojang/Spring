<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<spring:message code="myboard.title"/>
<h1>아주 볼품없는 게시글 작성 스프링 폼</h1>
<!-- Spring에서 제공하는 form 태그라이브러리 -->
<form:form action="${pageContext.request.contextPath}/board/write" method="post" modelAttribute="vo"> 
제목 <form:input path="boardTitle"/> 
<form:errors path="boardTitle"/><br>
작성자 <form:input path="boardWriter"/> 
<form:errors path="boardWriter"/><br>
내용<br>
<form:textarea path="boardContent" cols="60" rows="10"/>
<form:errors path="boardWriter"/><br>
<form:button>전송</form:button>
</form:form>

<!--  
위와 같은 내용임
<form action="${pageContext.request.contextPath}/board/write" method="post">
제목 <input type="text" name="boardTitle" value=""><br>
작성자 <input type="text" name="boardWriter" value=""><br>
내용 <br>
<textarea name="boardContent" value=""></textarea>
<input type="submit" value="등록">

</form>-->
</body>
</html>