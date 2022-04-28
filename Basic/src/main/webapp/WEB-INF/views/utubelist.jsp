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
			let vLinkArr = [];
			let vTitleArr = [];

			const f_getTitle = function(p_code) {
				const xhr = new XMLHttpRequest();
				let url = "<%=request.getContextPath()%>/bypass/utubeTitle?schCode=" + p_code;
				xhr.open("get", url, true);
				xhr.onreadystatechange = function() {
					if(xhr.readyState==4 && xhr.status==200) {
						
						let readBody = xhr.responseText;
						let startIndex = readBody.indexOf("<title>")+7;
						let endIndex = readBody.indexOf("</title>");
						let title = readBody.substring(startIndex, endIndex);
						//console.log(p_code, "==>", title)
						vTitleArr.push(title);
						let videoVO = {};
						videoVO.code = p_code;
						videoVO.title = title;
						f_listDisp(vLinkArr,vTitleArr);
						return title;
					}
				}
				xhr.send();
			}
			
			const f_btnClick = ()=>{
				c_disp.innerHTML = "";
				console.log("clicked")
				const xhr = new XMLHttpRequest();
				let schWord = c_sch.value;
				let url = "<%=request.getContextPath()%>/bypass/utube?schWord=" + schWord;
				xhr.open("get", url, true);
				xhr.onreadystatechange = function () {
					if (xhr.readyState == 4 && xhr.status == 200) {
						let str = xhr.responseText;
						let linkArr = [];
						let idx = 0;
						let link = "";
						while (str.indexOf("watch?") != -1) {
							idx = str.indexOf("watch?");
							link = str.substr(idx+8, 11);
							for (i=0; i<linkArr.length; i++) {
								if(link==linkArr[i]) {
									linkArr.splice(i, 1);
								}
							}
							linkArr.push(link);
							str = str.slice(idx + 19); // idx는 "/watch?"의 시작점이므로 배열에 담아준 문자열(20자리) 뒤를 잘라야 함
						}
						vLinkArr = linkArr; // 전역변수에 담기
						f_getTitleList(linkArr);
					}
				}
				xhr.send();
			}
			
			const f_getTitleList = async (p_arr) => {
				let title = "";
				let titleList = [];
				for (let i=0; i<p_arr.length; i++) {
					titleList.push(await f_getTitle(p_arr[i]));
				}
				console.log("async 테스트:",titleList); // await가 작동하지 않는 것 같다....
				
			}
		
			const f_listDisp = (p_arr1, p_arr2) => {
				let tblStr = "<table border=1>";
				tblStr += "<tr><th>순번</th><th>제목</th><th>링크</th></tr>";
				for (let i=0; i<p_arr1.length; i++) {
					tblStr +="<tr>";
					tblStr +="<td>"+i+"</td>";
					//tblStr +="<td>"+p_arr1[i]+"</td>";
					tblStr +="<td><a href='https://www.youtube.com/watch?v="+p_arr1[i]+"'>"+p_arr2[i]+"</a></td>";
					tblStr +="</tr>";
				}
				tblStr += "</table>"	
				c_disp.innerHTML = tblStr;
			}
			
			c_btn.addEventListener("click", f_btnClick);

		</script>

	</body>

	</html>