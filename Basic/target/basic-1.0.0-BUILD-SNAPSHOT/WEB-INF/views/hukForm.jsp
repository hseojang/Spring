<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#id_disp {
	width:200px;
	height:200px;
	border:2px solid pink;
}
</style>
</head>
<body>
	<div id="id_disp">여기에 두수의 연산 결과가 나오도록 하세요</div>
	<form id="id_form" action="<%=request.getContextPath()%>/remind/huk2"
		method="post" enctype="multipart/form-data">
		첫번째 수 <input type="text" value="" name="nm_first" required><br>
		두번째 수 <input type="text" value="" name="nm_second" required><br>
		연산 <select name="nm_sel">
			<option value="+" selected>더하기</option>
			<option value="-">빼기</option>
			<option value="*">곱하기</option>
			<option value="/">나누기</option>
		</select><br>
		<input type="file" id="id_files" name="files" value=""><br>
		<!-- 	validaion은 클라이언트 단에서 해주기 -->
		<button id="id_submit">계산</button>
	</form>
	<script>
		const v_disp = document.querySelector("#id_disp");
		const v_form = document.querySelector("#id_form");
		const v_files = document.querySelector("#id_files");
		
		const f_DataString = ()=>{
			let retStr = "";
			// form 안의 사용자 input만 가져오려면?
			// form.elements는 form의 사용자 입력 관련한 것을 배열로 가져온다(버튼 포함)
			for (let i=0; i<v_form.elements.length; i++) { 
				if(v_form.elements[i].name) {
				retStr += v_form.elements[i].name + "=" 
						+ v_form.elements[i].value;
				retStr += "&";
				}
				// 이름=값& ... 형식으로 문자열 구성
				// 같은 내용의 제이쿼리 form serialize 메서드가 존재한다.
			}
			return retStr.substr(0, retStr.length-1) // 마지막 & 빼준 값을 반환
		}
		
		const f_submit1 = ()=>{ // 비동기식 전송 1 폼데이터
			event.preventDefault(); // 전송 안되도록 막기
			// FormData를 활용한 방법 = 자바의 multipart-formdata
			// php에서는 다이렉트로 가능한데 jsp는 안된다고함...
			// 서버측이 multipart parser를 가지고 있어야 함(web.xml에서 설정한다)
			let formData = new FormData(v_form);
			
			// 파일을 보낼 때는 파일 객체를 추가적으로 따로 담아줘야 한다 -> ajax인 경우?
			formData.append("uploadFile", v_files.files[0]);
					
					
					
/*			
			// formData.values()는 iterator를 반환한다
			// iterator는 for of 문으로 반복시킬 수 있다
			// 실제 검증 빼고는 사용할 일 잘 없음 = 들어있는 값 확인 용
			for (let value of formData.values()) {
				console.log(value);
			}
*/
			
			const xhr = new XMLHttpRequest();
			xhr.open("post", "<%=request.getContextPath()%>/remind/huk", true);
			// xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			// FormData는 RequestHeader를 세팅할 필요가 없음, 강제로 컨텐츠 타입을 바꾸면 값이 서버에 제대로 전달되지 않음
			// 쿼리스트링 방식일떄 urlencode를 이용한 것
			xhr.onreadystatechange = ()=>{
				if(xhr.readyState == 4 && xhr.status == 200) {
					v_disp.innerHTML = "서버에서 받은값? : " + xhr.responseText;
				}
			}
			xhr.send(formData);
		}
		
		const f_submit2 = ()=>{ // 비동기식 전송 2 쿼리스트링 구성
			event.preventDefault(); // 전송 안되도록 막기
			// form.elements를 사용한 방법
			let sendData = f_DataString();
			// sendData = encodeURIComponent(sendData);
			
			const xhr = new XMLHttpRequest();
			xhr.open("post", "<%=request.getContextPath()%>/remind/huk", true);
					// xhr.open(전송방식, url, 비동기 여부)
			
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			// send 하기 전 추가적으로 세팅(GET방식에는 필요없음 URL로 넘어가니까인듯)
			// 제이쿼리에서 위와 같은 설정이 기본으로 설정됨(그래서 이미지 전송시 임의로 false 처리해주는 식)
			xhr.onreadystatechange = ()=>{
				if(xhr.readyState == 4 && xhr.status == 200) {
					v_disp.innerHTML = "서버에서 받은 값은 : " + xhr.responseText;
				}
			}
			xhr.send(sendData); // 실행
			// GET 방식일때는 쿼리스트링을 삽입해 파라미터를 전송하므로 send에 매개변수를 삽입하지 않지만
			// POST 방식에서는 넘길 데이터를 send()에 넣어 보낸다
		}
		
		// 자바 부분은 컴파일 되어서 페이지를 생성할 때 값이 들어간 채로 만들어진다
		// form은 전송되기 직전에 onsubmit 이벤트를 발생시킴
		// v_form.addEventListener("submit", f_submit1);
	</script>
</body>
</html>