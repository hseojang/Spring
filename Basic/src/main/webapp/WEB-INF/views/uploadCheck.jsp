<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.6.0.min.js"></script>
</head>
<body>
	<form action="<%=request.getContextPath()%>/gUpload">
		<input type="file" id="id_file" value="" accept=".jpg, .jpeg, .png"><br>
		<input type="text" name="nm_aaa" value="merong"><br>
		<input type="submit" value="바닐라JS전송"> 
		<input type="submit" value="jquery전송">
	</form>

	<script>
		const c_form = document.forms[0];
		const c_file = document.querySelector("#id_file");
		const f_submit = ()=>{ // jQuery ajax 사용 파일 업로드
			event.preventDefault();
			
			console.log(c_file.files);
			
			
			// let formData = new FormData(); // FormData 객체를 사용하면  multipart/form-data로 전송(자동으로 url-encoding에서 방식 바뀜)
			let formData = new FormData(c_form); // FormData 객체 생성과 동시에 form 태그 안의 input 값을 전부 가져오기
												// 단!! input type="file"의 경우 파일의 정보만을 가져가므로 데이터를 보내려면 append 필요 
			formData.append("upload", c_file.files[0]); // name, value : 컨트롤러에서 받을 변수이름, 붙일 데이터
			$.ajax({
				type: "post",
				url: "<%=request.getContextPath()%>/gUpload",
				contentType: false, // 전송방식을 application/c-www-form-urlencoded로 강제로 바꾸지 못하도록
				processData: false, // 처리방식도 application/c-www-form-urlencoded로 바꾸지 못하도록
				data: formData,
				// dataType: "json",
				success: function(p_rslt) {
					console.log(p_rslt); 
				}, 
				error: function(p_rslt) {
					console.log(p_rslt, " error")
					// jquery는 내부적으로 데이터 타입을 처리하는 과정을 거침
					// 요청이 성공적으로 끝나도 데이터 처리 과정이 실패하면 error로 식별
					// 데이터 타입을 지정해주는 것과 무관하게 실패할 수 있음
				}
					
			});
		};
		
		const f_submit2 = ()=>{ // 바닐라 JS 비동기 파일 업로드
			event.preventDefault();
			
			console.log(c_file.files);
			let formData = new FormData();
			formData.append("upload", c_file.files[0]);
			
			const c_xhr = new XMLHttpRequest();
			c_xhr.open("post", "<%=request.getContextPath()%>/gUpload", true);
			c_xhr.onreadystatechange = ()=>{
				if(c_xhr.status==200 && c_xhr.readyState==4) {
					let l_img = document.createElement("img");
					l_img.src = c_xhr.responseText; // responseText로 파일 URL이 반환됨
					document.body.appendChild(l_img);
					console.log(c_xhr.responseText);
				}
			};
			c_xhr.send(formData);
		}
		c_form.addEventListener("submit", f_submit2);
		
		/* jQuery 내부에 내장된 데이터 처리 과정 때문에 다양한 데이터를 주고받기에는 순수 자바스크립트가 더 유리함 */
	
	</script>
</body>
</html>