<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>나의 로그아웃 페이지</h1>
<h2>${guest}님 안녕히 가세요</h2>
<form id="id_form" action="<%=request.getContextPath()%>/mylogout" method="post">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<br>
<a href="#" id="id_logout">로그아웃</a>
<script>
	const c_a = document.querySelector("#id_logout");
	const c_form = document.querySelector("#id_form");
	const f_submit = ()=>{
		event.preventDefault(); // href="#"은 최소한의 안전장치
		// c_form.action="", c_form.method=""으로 설정해서 보내는 것도 가능
		c_form.submit();
	}
	
	//c_a.addEventListener("click", f_submit);
	c_a.onclick = f_submit;

</script>
</body>
</html>