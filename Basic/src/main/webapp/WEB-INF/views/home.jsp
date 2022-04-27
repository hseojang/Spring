<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mystyle.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/x2js/1.2.0/xml2json.min.js"></script>
</head>
<body>
<h1>
	Hello world 조현!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<input type="text" name="schWord" id="id_sch"><button id="id_search">검색</button><br>
<div id="id_disp"></div>
<form action="/basic/blackpink/jenni" method="post">
   이름<input type="text" name="nm_name" value="배트맨"><br>
   별명<input type="text" name="nm_alias" value="박쥐"><br>
   <input type="submit" value="쩐송">
</form>
<input type="button" value="서버우회아작스" id="id_btn">
<input type="button" value="구글뉴스XML" id="id_btn2">
<input type="button" value="오후엔 졸려" id="id_btn3">
<input type="button" value="유튜브영상검색" id="id_btn4">
<script>


	const c_btn3 = document.querySelector("#id_btn3");
	const f_btnClick3 = ()=>{
		let schWord = c_sch.value;
		const xhr = new XMLHttpRequest();
        let url = "<%=request.getContextPath()%>/bypass/googleNews?schWord=" + schWord;
        xhr.open("get",url,true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
            	/* 	X2JS : 
            		xml 문자열을 JSON으로 변환시켜주는 클라이언트 쪽 스크립트
            		서버쪽(Jackson)보다 클라이언트 쪽에서 처리하는 것이 일반적으로 유리함
            	*/
            	var x2js = new X2JS();
            	var jsonObj = x2js.xml_str2json(xhr.responseText);
                console.log(jsonObj); // console에 json 객체로 찍힘
                // console.log(xhr.responseText); // xml 형식 텍스트
            }
        }
        xhr.send();
	}
	c_btn3.addEventListener("click",f_btnClick3);
	

	const c_disp = document.querySelector("#id_disp");
	const c_search = document.querySelector("#id_search");
	const c_sch = document.querySelector("#id_sch");
	const f_search = ()=>{
		
		let schWord = c_sch.value;
		
		const xhr = new XMLHttpRequest();
		let url = "<%=request.getContextPath()%>/bypass/google3?schWord=" + schWord;
		xhr.open("get", url, true);
		xhr.onreadystatechange = ()=>{
			if(xhr.readyState == 4 && xhr.status == 200) {
				
				let recvDatas = JSON.parse(xhr.responseText).channel.item;
				let tblStr = "<table border=1>";
				tblStr += "<tr><th>순번</th><th>타이틀</th><th>링크</th><th>발행일</th></tr>"
				for(let i=0; i<recvDatas.length; i++) {
					tblStr += "<tr>";
					tblStr += "<td>" + (i+1) + "</td>";
					tblStr += "<td>" + recvDatas[i].title + "</td>";
					tblStr += `<td><a href=\${recvDatas[i].link}>보기</a></td>`;
					tblStr += "<td>" + recvDatas[i].pubDate + "</td>";
					tblStr += "</tr>";
					
					/*
					let l_tr = document.createElement('tr');
					for ( var text in recvDatas[i]) {
						if(text=="guid"||text=="source") {continue;}
						let l_td = document.createElement('td');
						l_td.innerHTML = recvDatas[i][text];
						l_tr.appendChild(l_td);
						// title	link	guid	pubDate	description	source
					}
					c_table.appendChild(l_tr);
					*/
				}
				tblStr += "</table>"
				c_disp.innerHTML = tblStr;
			}
		}
		xhr.send();
	}
	c_search.addEventListener("click", f_search);



	const c_btn = document.querySelector("#id_btn");
	const f_btnClick = ()=>{
		const xhr = new XMLHttpRequest();
        let url = "<%=request.getContextPath()%>/bypass/googleNews";
		// 직접 다른 서버에 요청을 하면 거부되지만 내 서버에서 다른 서버에 요청한 것을 브라우저에서 정보를 받은 내 서버로 요청하면 잘 됨
		// CORS는 브라우저 엔진에서 크로스 오리진으로 요청 할 수 없다는 규약이고, 서버간 통신은 막지 않음
        xhr.open("get",url,true);
        
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                alert("잘 오나", xhr.responseText);
            }
        }
        xhr.send();
	}
	c_btn.addEventListener("click",f_btnClick);
	
	
	
</script>
</body>
</html>
