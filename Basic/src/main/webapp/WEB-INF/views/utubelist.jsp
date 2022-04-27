<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>유튜브</title>
	</head>

	<body>
		<input type="text" id="id_sch" value="">
		<input type="button" id="id_btn" value="검색">
		<div id="id_disp">
		</div>
		<script>
			const c_sch = document.querySelector("#id_sch");
			const c_btn = document.querySelector("#id_btn");
			const c_disp = document.querySelector("#id_disp");

			const f_getTitle = function(p_code) {
				const xhr = new XMLHttpRequest();
				let url = "<%=request.getContextPath()%>/bypass/utubeTitle?schCode=" + p_code;
				xhr.open("get", url, true);
				xhr.onreadystatechange = function() {
					if(xhr.readyState==4 && xhr.status==200) {
						console.log(xhr.responseText);
						/* 숙제 :
						타이틀 클릭하면 해당 영상이 열리도록 하기
						순번 	타이틀
						1	제목1
						2	제목2
						3	제목3
						*/
						const readBody = xhr.responseText;
						let startIndex = readBody.indexOf("<title>"+7);
						let endIndex = readBody.indexOf("</title>");
						let title = readBody.substring(startIndex, endIndex);
						console.log(p_code, "==>", title)
						return title;
					}
				}
				xhr.send();
			}
			
			const f_btnClick = ()=>{
				console.log("clicked")
				const xhr = new XMLHttpRequest();
				let schWord = c_sch.value;
				let url = "<%=request.getContextPath()%>/bypass/utube?" + schWord;
				xhr.open("get", url, true);
				xhr.onreadystatechange = function () {
					if (xhr.readyState == 4 && xhr.status == 200) {
						// console.log(xhr.responseText);
						let str = xhr.responseText;
						let vidSet = new Set();
						let idx = 0;
						let link = "";
						while (str.indexOf("watch?") != -1) {
							idx = str.indexOf("watch?");
							link = str.substr(idx, 19);
							vidSet.add(link);
							// console.log(link);
							str = str.slice(idx + 19); // idx는 "/watch?"의 시작점이므로 배열에 담아준 문자열(20자리) 뒤를 잘라야 함
						}
						console.log(vidSet);
						f_getList(vidSet);
					}
				}
				xhr.send();
			}
			
		
			const f_getList = (vidSet) => {
				const vidArr = Array.from(vidSet);
				let tblStr = "<table border=1>";
				tblStr += "<th><td>순번</td><td>제목</td><td>링크</td></th>";
				for (let i=0; i<vidArr.length; i++) {
					tblStr +="<tr>";
					tblStr +="<td>"+i+"</td>";
					tblStr +="<td>"+vidArr[i]+"</td>";
					tblStr +="<td>"+f_getTitle(vidArr[i])+"</td>";
					tblStr +="</tr>";
				}
				tblStr = "</table>"	
				c_disp.innerHTML = tblStr;
			}
			
			c_btn.addEventListener("click", f_btnClick);

		</script>

	</body>

	</html>