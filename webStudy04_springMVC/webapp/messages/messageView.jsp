<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	블라인드 처리 사유 : <%=session.getAttribute("reason") %>
	<%
		session.removeAttribute("reason");
	%>
</body>
</html>