<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>websocket</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<pre>
	서버  : wss//echo.websocket.org
	<input type="button" id = "connBtn" value="connect";/>
	<input type="button" id = "disconnBtn" value="disconnect";/>
</pre>
<input type="text" id="msgArea"/>
<input type="button" id="sendBtn" value="메시지 전송" />
<div id="messages"></div>
<script type="text/javascript">
	var server = "wss://localhost${cPath}/echo";
	var ws = null;
	$("#connBtn").on("click", function(){
		ws = new WebSocket(server);
		ws.onopen=function(event){
			let serverURL = event.target.url;
			console.log(serverURL+"와 연결수립");
		}
		ws.onerror=function(event){
			console.log("에러 발생");
		}
		ws.onclose=function(event){
			let serverURL = event.target.url;
			console.log(serverURL+"연결 종료");
		}
		ws.onmessage=function(event){
			let serverURL = event.target.url;
			let receiveMsg = event.data;
			console.log("메시지 수신"+receiveMsg);
			let pTag = $("<p>").html("receive message : "+receiveMsg);
			$("#messages").append(pTag);
		}
	});
	$("#disconnBtn").on("click", function(){
		if(ws==null) return;
		ws.close();
	});
	$("#sendBtn").on("click", function(){
		let message = $("#msgArea").val();
		if(!message) return;
		ws.send(message);
		let pTag = $("<p>").html("send message : "+message);
		$("#messages").append(pTag);
	});
</script>
</body>
</html>