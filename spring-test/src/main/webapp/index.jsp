<html>
<body>
	123123
	<br>
	<a href="https://test.crm.daojia.com:8443/cas/logout?service=http://localhost:8090/admin/test">LogOut</a>
</body>

<script type="text/javascript" src="http://test.crm.daojia.com/js/sockjs.min.js"></script>

<script type="text/javascript">
	/* var sock = new SockJS("http://localhost:8090/admin/webSocketHandler",null, {server:"12"}); */
	var sock = new SockJS("http://localhost:8090/admin/webSocketHandler",null, {server:"12"});
	sock.onopen = function() {
	    console.log('open');
	};
	sock.onmessage = function(e) {
	    console.log('message', e.data);
	    alert(e.data);
	};
	sock.onclose = function() {
	    console.log('close');
	};
	
	/* sock.send('test');
	sock.close(); */
</script>
</html>