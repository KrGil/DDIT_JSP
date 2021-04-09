<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	//flash attribute
	String message = (String) session.getAttribute("message");
	session.removeAttribute("message");
	out.println(Objects.toString(message, ""));
	if(message!=null && !message.isEmpty()){
		%>
		<script type = "text/javascript">
			alert("<%=message%>");
		</script>
		<% 
	}
%>
</head>
<body>
<form method = "post">
	<input type = "text" name = "mem_pass" required/>
	<input type = "submit" value = "인증"/>
</form>
</body>
</html>