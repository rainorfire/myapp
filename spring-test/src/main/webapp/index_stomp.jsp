<html>
<body>
	123123
	<button onclick="sendMsg()">SendMSG</button>
</body>

<script type="text/javascript" src="http://localhost:8080/myproject/resources/js/sockjs.min.js"></script>
<script type="text/javascript" src="http://localhost:8080/myproject/resources/js/stomp.min.js"></script>

<script type="text/javascript">
	var sock = new SockJS('http://localhost:8080/myproject/ws_stomp');
	var stompClient = Stomp.over(sock);
	stompClient.connect({}, function(frame) {
		
		stompClient.subscribe("/stomp-test/test",
            function( message ) {
                alert( message );
            },
            { priority: 9 }
        );
	});
	
	/* sock.send('test');
	sock.close(); */ 
	
	function sendMsg(){
		stompClient.send("http://localhost:8080/myproject/stomp/stomp-test/test",{},JSON.stringify({"name":"guagua"}));
	}
</script>
</html>