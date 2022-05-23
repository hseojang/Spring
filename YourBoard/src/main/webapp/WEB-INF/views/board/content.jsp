<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

.boardInput {
background-color: rgba(255, 255, 255, 0);
border: 0;
}

#id_replylist {
	display: flex;
	flex-direction: column;
	padding:3px;
	background-color: lightgray;
		align-content: space-around;
	justify-content: space-evenly;
}

.containerHor {
	display: flex;
	flex-direction: row;
	align-items: stretch;
	align-content: space-around;
	justify-content: space-evenly;
}


.containerVer {
	display: flex;
	flex-direction: column;
}

.reply {
padding : 5px;
	flex-direction: row;
	background-color: lightgray;
	height: 2.3em;
}

.replyContent {

	background-color: lightgray;
}

.replyFormTable {
	width: 1em;
}

.replyFormRead {
	background-color: rgba(255, 255, 255, 0.5);
	border: 0;
}

.replyFormWriter {
	display: inline-block;
	text-align: center;
	width: 8em;
	font-weight: bold;
}

.replyFormContent {
	display:inline-block;
	width: 40em;
}

.replyFormTitle {
	display:inline-block;
	width: 40em;
	font-weight: bold;
	
}

.msg {
	color: red;
}

.hiddenBtn {
	display: none;
}

.VisibleBtn {
	display: inline-block; 
}

</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
	<c:set var="replyPath" value="${pageContext.request.contextPath}/reply"></c:set>
	<h3>게시글</h3>
	<form id="id_boardForm">
	<table border="1">
		<tr>
			<td>글번호</td>
			<td><input type="text" value="${vo.boardNo}" readonly="readonly" class="boardInput" name="boardNo"></td>
			<td>작성자</td>
			<td><input type="text" value="${vo.boardWriter}" readonly="readonly" class="boardInput" name="boardWriter"></td>
		</tr>
		<tr>
			<td>작성일</td>
			<td><input type="text" value="${vo.boardRegdate}" readonly="readonly" class="boardInput" name="boardRegdate"></td>
			<td>수정일</td>
			<td><input type="text" value="${vo.boardUpdate}" readonly="readonly" class="boardInput" name="boardUpdate"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td colspan="3"><input type="text" value="${vo.boardTitle}" readonly="readonly" class="boardInput" name="boardTitle"></td>
		</tr>
		<tr>
			<td colspan="4">내용</td>
		</tr>
		<tr>
			<td colspan="4"><input type="text" value="${vo.boardContent}" readonly="readonly" class="boardInput" name="boardContent"></td>
		</tr>

	</table>
	</form>
	<h3>댓글</h3>
	<div id="id_replylist" width="50em"></div>
	<div id="id_replyFormDiv">
		<form action="" method="" id="id_replyForm">
			<input type="hidden" value="${vo.boardNo}" name="boardNo"> <input
				type="text" placeholder="작성자" name="replyWriter" value=""> <input
				type="text" placeholder="제목" name="replyTitle" value=""> <input
				type="text" placeholder="내용" name="replyContent" value=""> <input
				type="button" value="작성" id="id_btnSendReply">
		</form>
	</div>

	<input type="button" value="목록으로" id="id_btnList"
		onclick="location.href='${contextPath}/board/list'">
	<input type="button" value="수정" id="id_btnEdit"
		onclick="location.href='${contextPath}/board/edit?boardNo=${vo.boardNo}'">
	<input type="button" value="삭제" id="id_btnDelete">
	<script>
		var v_btnDelete = document.querySelector("#id_btnDelete");
		var f_delete = function() {
			var v_boardForm = document.querySelector("#id_boardForm");
			v_boardForm.action = "${contextPath}/board/delete";
			v_boardForm.method = "post";
			if (confirm("진짜 지워요?")) {
				v_boardForm.submit();
			}
		}

		v_btnDelete.onclick = f_delete;
		var v_replylist = document.querySelector("#id_replylist");

		
		var f_ajax = function(p_json) {
			var xhr = new XMLHttpRequest();
			xhr.open(p_json.method, p_json.url, p_json.async);
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					if (p_json.dataType == "json") {
						p_json.success(JSON.parse(xhr.responseText));
						return;
					}
					p_json.success(xhr.responseText);
				}
			}
			if (p_json.contentType) {
				console.log("Content-Type 설정됨")
				xhr.setRequestHeader("Content-Type", p_json.contentType);
			}

			if (p_json.method == "post") {
				// xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				console.log("data: " + p_json.data);
				console.log("url: " + p_json.url);
				xhr.send(p_json.data);
				// 여기서 리턴하면 success가 실행 안될 것 같아서 일단 else 사용
			} else {
				xhr.send();
			}
		}

		var v_btnSendReply = document.querySelector("#id_btnSendReply");
		var v_replyForm = document.querySelector("#id_replyForm");
		var f_sendReply = function() {
			event.preventDefault(); // 이벤트 막기
			var v_formData = new FormData(v_replyForm);
			//console.log(v_replyForm);
			//console.log(v_replyForm.elements);
			console.log("insert 불림")

			var v_json = {
				method : "post",
				url : "${replyPath}/insert",
				async : true,
				data : v_formData,
				success : function(p_data) {
					if (p_data) {
						alert(p_data);
					}
					f_getReply();
				}
			}

			f_ajax(v_json);

		};
		v_btnSendReply.onclick = f_sendReply;

		var f_deleteReply = function(p_replyNo) {
			// get으로 하면 간단하지만 보안이 좋지 않음
			v_data = {
				"replyNo" : p_replyNo
			};
			console.log(p_replyNo);
			var v_json = {
				method : "post",
				url : "${replyPath}/delete",
				async : true,
				data : JSON.stringify(v_data),
				contentType : "application/json; charset=utf-8",
				success : function(p_data) {
					if (p_data) {
						alert(p_data);
					}
					f_getReply();
				}
			}

			f_ajax(v_json);
		};

		var f_editReplyOn = function(p_replyNo) {
			var targetInputSelector = "#id_replyNo" + p_replyNo
					+ " input.replyFormRead";
			var targetBtnSelector = "#id_replyNo" + p_replyNo
			+ " input[type='button']";
			var targetInputArr = document.querySelectorAll(targetInputSelector);
			var targetInputBtn = document.querySelectorAll(targetBtnSelector);
			for (var i=0; i<targetInputArr.length; i++) {
				targetInputArr[i].readOnly = "";
				targetInputArr[i].style.backgroundColor = "rgba(255, 255, 255, 0.8)";
			}
			for (var i=0; i<targetInputBtn.length; i++) {
				targetInputBtn[i].style.visibility = "hidden";

			}
			var submitBtn = document.querySelector("#id_btnSubmitNo"+p_replyNo);
			submitBtn.setAttribute("class", "visibleBtn");
			submitBtn.onclick = f_editReplySubmit.bind(document, p_replyNo);
			console.log(targetInputBtn);
		}

		var f_editReplySubmit = function(p_replyNo) {
			event.preventDefault();
			console.log("f_editReplySubmit 불림");
			var targetFormId = "#id_replyNo" + p_replyNo;
			var targetForm = document.querySelector(targetFormId);
			var v_formData = new FormData(targetForm);
			console.log(targetForm);
			var v_json = {
				method : "post",
				url : "${replyPath}/edit",
				async : true,
				data : v_formData,
				success : function(p_data) {
					if (p_data) {
						alert(p_data);
					}
					f_getReply();
				}
			}
			f_ajax(v_json);
		};
		
		var f_getReply = function() {
			v_replylist.innerHTML = "";
			var v_json = {
					method : "get",
					url : "${replyPath}/list?boardNo=${vo.boardNo}",
					async : true,
					dataType : "json",
					success : function(p_data) {
						if (p_data) {			
							var replyArr = p_data;
							console.log(replyArr);
							if (!replyArr.length) {
								var v_replyDiv = document.createElement("div");
								v_replyDiv.setAttribute("class", "msg");
								v_replyDiv.innerHTML = "댓글이 아직 없습니다."
								v_replylist.appendChild(v_replyDiv);
							}

							for (var i = 0; i < replyArr.length; i++) {
								var v_replyDiv = document.createElement("div");
								v_replyDiv.setAttribute("class", "reply");
								v_replyDiv.innerHTML = "<form id='id_replyNo"+replyArr[i].replyNo
										+"' action=''><div class='containerHor'>"
										+ "<input type='hidden' name='replyNo' value='" + replyArr[i].replyNo + "'>"
										+ "<input type='text' name='replyWriter' class='replyFormRead replyFormWriter' value='" 
										+ replyArr[i].replyWriter + "' readonly='readonly'><div class='containerVer'>"
										+ "<input type='text' name='replyTitle' class='replyFormRead replyFormTitle' value='"
										+ replyArr[i].replyTitle + "' readonly='readonly'>"
										+ "<input type='text' name='replyContent' class='replyFormRead replyFormContent' value='"
										+ replyArr[i].replyContent + "' readonly='readonly'></div>"
										+ "<input type='button' value='삭제' id='id_btnDeleteReply' onclick='f_deleteReply("
										+ replyArr[i].replyNo
										+ ")'>"
										+ "<input type='button' value='수정' id='id_btnEditReply' onclick='f_editReplyOn("
										+ replyArr[i].replyNo + ")'><button class='hiddenBtn' id='id_btnSubmitNo"
										+ replyArr[i].replyNo + "'>확인</button></div></form>";

								v_replylist.appendChild(v_replyDiv);
							}
						}
					}
				}
			
				f_ajax(v_json);
			
			
			
			/* v_replylist.innerHTML = "";
			var xhr = new XMLHttpRequest();
			// 이것은 get 방식
			// xhr.open("get", "${replyPath}/list?boardNo=${vo.boardNo}", true);

			// 이것은 post 컨트롤러가 받기는 했지만 get 방식
			// xhr.open("post", "${replyPath}/list?boardNo=${vo.boardNo}", true);
			// 방식만 post고 실제로 post로 데이터를 보내지 않았음(데이터를 쿼리스트링에 붙여서 보냄)

			// 이게 진짜 post
			xhr.open("post", "${replyPath}/list", true);
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var replyArr = JSON.parse(xhr.responseText);

					if (!replyArr.length) {
						var v_replyDiv = document.createElement("div");
						v_replyDiv.setAttribute("class", "msg");
						v_replyDiv.innerHTML = "댓글이 아직 없습니다."
						v_replylist.appendChild(v_replyDiv);
					}

					for (var i = 0; i < replyArr.length; i++) {
						var v_replyDiv = document.createElement("div");
						v_replyDiv.setAttribute("class", "reply");
						v_replyDiv.innerHTML = "<form id='id_replyNo"+replyArr[i].replyNo
								+"' action=''><div class='containerHor'>"
								+ "<input type='hidden' name='replyNo' value='" + replyArr[i].replyNo + "'>"
								+ "<input type='text' name='replyWriter' class='replyFormRead replyFormWriter' value='" 
								+ replyArr[i].replyWriter + "' readonly='readonly'><div class='containerVer'>"
								+ "<input type='text' name='replyTitle' class='replyFormRead replyFormTitle' value='"
								+ replyArr[i].replyTitle + "' readonly='readonly'>"
								+ "<input type='text' name='replyContent' class='replyFormRead replyFormContent' value='"
								+ replyArr[i].replyContent + "' readonly='readonly'></div>"
								+ "<input type='button' value='삭제' id='id_btnDeleteReply' onclick='f_deleteReply("
								+ replyArr[i].replyNo
								+ ")'>"
								+ "<input type='button' value='수정' id='id_btnEditReply' onclick='f_editReplyOn("
								+ replyArr[i].replyNo + ")'><button class='hiddenBtn' id='id_btnSubmitNo"
								+ replyArr[i].replyNo + "'>확인</button></div></form>";

						v_replylist.appendChild(v_replyDiv);
					}
				}
			}
			//xhr.send(); // get 방식 보내기일땐 send()에 매개변수를 보내지 않음

			// post 전송은 (텍스트 전송 시) form-urlencoded 형식으로 컨텐츠타입을 지정해준다
			// get 전송에서는 쿼리스트링형식으로 파라미터를 전달하다보니 urlencoding이 된 상태로 값을 보내서 별도의 컨텐츠 타입지정이 필요하지 않음
			// jquery에서는 아래 방식이 디폴트로 설정되어있어 다른 컨텐츠 타입을 지정해야 할 때 별도의 disable 처리가 필요함
			// 첨부 파일을 보낼 때는 multipart-formdata로 보냄
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr.send("boardNo=${vo.boardNo}"); // post는 넘길 값을 매개변수로 담아 보내줌
			// CSRF는 same-origin이라 필요하지 않은 것 같다
			*/
		};

		f_getReply();

	</script>
</body>
</html>