<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
<script>
</script>
</head>
<body>
	<textarea name="nm_ta" id="id_ta"></textarea>
	<input type="button" value="CKEditor 값 가져오기" id="changil">
	<div id="id_disp"></div>
	<script>
		CKEDITOR.replace("nm_ta",{
			 enterMode : CKEDITOR.ENTER_BR,
			 filebrowserUploadMethod : "form",  // 빠트려면 당황스런 상황
			 // 대부분의 예제들이 윗부분 한줄을 빼는데, 이상하게 작동할 수 있음
			 filebrowserUploadUrl: "<%=request.getContextPath()%>/ckUpload"
		})
		
		const c_changil = document.querySelector("#changil");
		const c_disp = document.querySelector("#id_disp");
		// ckEditor에 작성한 내용 가져오기
		const f_click = function() {
			// let c_ckContents = document.querySelector("#cke_1_contents .cke_wysiwyg_frame").contentWindow.document;
			
			// 분석해서 가져오는 방법
			const c_ifrm = document.querySelector("iframe");
			c_disp.innerHTML = c_ifrm.contentWindow.document.body.innerHTML;
			// c_ifrm.contentWindow.document.body.innerHTML;
			// = c_ifrm.contentDocument.body.innerHTML;
			
			// 제공되는 API
			CKEDITOR.instances.id_ta.getData();
			CKEDITOR.instances.id_ta.setData("<h1>임의로 메시지 삽입</h1>"); // 기존 내용을 교체함
		}                         
		
		c_changil.onclick = f_click;
	</script>
</body>
</html>