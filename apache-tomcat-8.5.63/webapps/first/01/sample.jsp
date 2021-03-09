<%@ page import="java.util.Date"%>
<html>
<body>
<h2>Welcome_</h2>
<h4>access time : <span id = "timerArea"></span></span></h4>
<h4>server response time : <%=new Date() %></h4>

<script type = "text/javascript">
	var today = new Date();
	var area = document.getElementById("timerArea");
	area.innerHTML = today;
</script>
<html>
<body>