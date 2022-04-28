<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>초간단 채팅</title>
<style>
	#id_chatwin {
/* 	위치는 %로 상대위치 가능하지만 크기는 %가 안됨, 대신 뷰포인트 기준의 단위를 쓴다 */
		width:95vw;
		height:50vh;
		background-color:black;
		border:1px solid pink;
		color:yellow;
		padding:2vh;
		margin:1vh;
	}
	
	#id_myChat {
		text-align: right;
		color:white;
	}
</style>
</head>
<body>
	<h1>간단히 대화라도 할깡</h1>
	<input type="text" id="id_my" value="" placeholder="내 아이디 입력"> 
	<input type="button" id="id_con" value="연껼">
	<hr>
	<div>
		<div id="id_chatwin"></div>
		<input type="text" id="id_message" placeholder="메시지 입력" /> 
		
		<input type="button" id="id_send" value="떤쏭"> 

		<input type="button" id="id_exit" value="나갈령">
	</div>
</body>
<script>
		// 클라이언트쪽 기본 틀, 훨씬 고급API도 많은데 
		// 온전히 제대로 하려면 Message 서버도 추가적으로 필요함
	    const c_alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		const f_ranID=()=>{
			let ranID ="";
			for(let i=1; i< (Math.ceil(Math.random()*5)+4); i++){
				ranID += c_alpha[Math.floor(Math.random()*c_alpha.length)];
			}
			return ranID;
		}

		
		const c_chatWin = document.querySelector("#id_chatwin");
		const c_message = document.querySelector("#id_message");		
		const c_send = document.querySelector("#id_send");
		const c_exit = document.querySelector("#id_exit");
		const c_con = document.querySelector("#id_con");
		const c_id = document.querySelector("#id_my");
		
		let webSocket;
		let myId = "";
		
		
		c_send.addEventListener("click", ()=>{
			send();
		});
		// 연결 끊깅
		c_exit.addEventListener("click", function () {
			disconnect();
		});

		//연결
		function connect() {
			if(webSocket) { // 연결 소켓이 중첩해서 생성되는 걸 막아줌
				return;
			} // readyState 속성으로도 할 수 있을 것 같은데...
			
			webSocket = new WebSocket("ws://192.168.145.23:8405/basic/ws-chat"); // End Point
			//이벤트에 이벤트핸들러 뜽록 
			webSocket.onopen = onOpen;
			webSocket.onmessage = onMessage;
		}

		//연결 시
		function onOpen() {
			myId = c_id.value;
			webSocket.send(myId + "님 이프짱.");
		} 
		function send() {
			let msg = c_message.value;
			webSocket.send(myId + ":" + msg);
			c_message.value = "";
		}
		function onMessage() {
			let data = event.data; // 서버에서 보내준 데이터가 event.data에 담겨옴(낮설탕?)
			let newMsg = document.createElement("div");
			if(data.indexOf(myId)!=-1) {
				newMsg.id="id_myChat";
			}
			c_chatWin.appendChild(newMsg);
			newMsg.innerHTML = data+"<br/>";
		}
		function disconnect() {
			webSocket.send(myId + "님이 뛰쳐나갔쪙");
			webSocket.close();
			webSocket = "";
		}
		
		c_con.addEventListener("click", ()=>{
			connect();
		});
		
		
</script>
</body>
</html>