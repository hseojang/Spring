<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/board/write" method="post">
<sec:csrfInput/>
작성자  <input type="text" name="bdUsername"><br>
비밀번호 <input type="text" name="bdPassword"> 비밀번호 확인 <input type="text" name="ckPassword"><hr>
제목 <input type="text" name="bdTitle"> 카테고리 <input type="text" name="bdCategory"><hr>
내용<br><textarea type="textarea" name="bdContent" style="width:600px; height:400px;"></textarea><br>
<input type="submit" value="작성">
</form>


</body>
</html>