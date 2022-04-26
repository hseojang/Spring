<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/gangkuk/inputForm6"
		method="post" enctype="multipart/form-data">
		이름 <input type="text" name="nm_name" id="id_name" value="민혁"><br>
		특기 <input type="text" name="nm_special" id="id_special" value="스프링"><br>
		여친 <input type="password" name="nm_girl" id="id_girl" value="개발자였으면 좋겠어요"><br>
		이미지들 <input type="file" name="files" value="" accept=".jpg,.png,.gif" multiple><br>
		<input type="submit" value="누르세요">
		<input type="button" value="누르세요2" id="id_btn">
		<input type="button" value="누르세요3" id="id_btn2">
	</form>
	<script>

	const c_btn = document.querySelector("#id_btn");
	const c_btn2 = document.querySelector("#id_btn2");
	
	const c_name = document.querySelector("#id_name");
	const c_special = document.querySelector("#id_special");
	const c_girl = document.querySelector("#id_girl");
	
	const c_btnClick = ()=>{
		const c_xhr = new XMLHttpRequest();
		c_xhr.open("post", "<%= request.getContextPath()%>/gangkuk/inputForm4", true);
		c_xhr.onreadystatechange = ()=>{
			if (c_xhr.readyState == 4 && c_xhr.status == 200) {
				alert(c_xhr.responseText);
			}
		}
		let l_data = {
				nm_name : c_name.value,
				nm_special: c_special.value,
				nm_girl: c_girl.value
			}
		// Header 세팅은 항상 send 전에
		// 문자열화 했지만 형식이 기본 URL Encoding 방식(ex: name=value)가 아니고 JSON 문법임을 알려줘야 함
		c_xhr.setRequestHeader("Content-Type", "application/json");
		c_xhr.send(JSON.stringify(l_data)); // 그냥 JSON 객체로는 전송이 안됨
	}
	c_btn.addEventListener("click", c_btnClick);
	
	const c_btnClick2 = ()=>{
		const c_xhr = new XMLHttpRequest();
		c_xhr.open("post", "<%= request.getContextPath()%>/gangkuk/inputForm5", true);
		c_xhr.onreadystatechange = ()=>{
			if (c_xhr.readyState == 4 && c_xhr.status == 200) {
				console.log(JSON.parse(c_xhr.responseText));
			}
		}
		let l_data = {
				nm_name : ["a", "b", "c"],
				nm_special: ["aa", "bb", "cc"],
				nm_girl: ["1", "2", "3"]
			}
		
		c_xhr.setRequestHeader("Content-Type", "application/json");
		// 잘못 설정하면 415에러 발생함 : 없는 속성에 값을 할당하거나 속성에 잘못된 값을 할당하는 경우
		// "Context-Type", "application/json"으로 지정하면 요청 헤더에 Context-Type 항목이 새로 생김(당연히 동작은 X)
		c_xhr.send(JSON.stringify(l_data));
	}
	c_btn2.addEventListener("click", c_btnClick2);
	</script>
</body>
</html>