<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form  method = "post">
		<select id = "member" name = "member">
	<%
		Map<String, String> member = (Map) application.getAttribute("bts");
		for(Entry<String, String> entry :member.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
	%>
			<option value = "<%=key %>"><%=value %></option>			
	<%
		}
	%>
		</select>
	</form>
<script type= "text/javascript" src = "https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	var select = $("#member").val();
	$("#member").change(function(){
		console.log(select)
		$("form").submit();
	})
</script>
</body>
</html>