<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="http://localhost:9092/myproject/resources/js/jquery.min.js"></script>
<body>
	123123  
	<br>
	<a href="javascript:void(0);" onclick="connectServer();">connect webSocket</a>
	<br>
	<input type="text" id="msg" name="msg"/>
	<a href="javascript:void(0);" onclick="writeMsg();">write msg</a>
</body>

<script type="text/javascript">

	var  wsServer = 'ws://localhost:9094/myproject/admin/wbsocket-server/start'; 
	function connectServer(){
		var  websocket = new WebSocket(wsServer); 
		websocket.onopen = function (evt) { onOpen(evt) }; 
		websocket.onclose = function (evt) { onClose(evt) }; 
		websocket.onmessage = function (evt) { onMessage(evt) }; 
		websocket.onerror = function (evt) { onError(evt) }; 
	}
	function onOpen(evt) { 
	console.log("Connected to WebSocket server."); 
	} 
	function onClose(evt) { 
	console.log("Disconnected"); 
	} 
	function onMessage(evt) { 
	var msg = evt.data;
	msg = new String(msg)
	console.log('Retrieved data from server: ' + msg); 
	} 
	function onError(evt) { 
	console.log('Error occured: ' + evt.data); 
	}
	
	function writeMsg(){
		var msg = $("#msg").val();
		$.get("http://localhost:9092/myproject/admin/wbsocket-server/write?msg="+msg,function(data,status){
		    alert("Data: " + data + "\nStatus: " + status);
		 });
	}
</script>
</html>