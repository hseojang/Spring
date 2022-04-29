<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/resources/ckeditor/ckeditor.js"></script>
<script>
</script>
</head>
<body>
	<textarea name="nm_ta" id="id_ta"></textarea>
	<script>
		CKEDITOR.replace("nm_ta",{
			 enterMode : CKEDITOR.ENTER_BR,
			 filebrowserUploadMethod : "form",  // 빠트려면 당황스런 상황
			 filebrowserUploadUrl: "<%=request.getContextPath()%>/ckUpload"
		})
	</script>
</body>
</html>