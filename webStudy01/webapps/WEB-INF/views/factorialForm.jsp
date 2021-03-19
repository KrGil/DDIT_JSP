<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Factorial</title>
</head>
<body>
<h4>팩토리얼 연산 처리</h4>
<!-- 동기처리 연산 수행. -->
<!-- 비동기 처리 연산 수행(JSON, HTML) -->

<!-- form에 아무것도 없다면 submit시 주소상의 위치로 다시 가게된다. -->
<%--
	Map<String, Object>target = (Map) request.getAttribute("target");
--%>
<form method = "post">
	<input type = "number" name = "single" value = "${target.op}"/>
	<input type = "submit" value = "="/>
	${target["result"]}
</form>

</body>
</html>