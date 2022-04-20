<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>타이틀 안썽</title>
</head>
<body>
<!-- 사용자 입력폼에 name 속성을 빠트리면 큰 죄악 -->
	<form action="<%=request.getContextPath()%>/remind/minhuk"  method="post">
		이름: <input type="text" name="nm_name" value=""><br>
		노래: <input type="text" name="nm_song" value="" required><br>
		<button  type="submit">쩐쏭</button>
		<!--  button은 form태그안에 들어가면 자동으로 type이 submit -->
	</form>
</body>
</html>