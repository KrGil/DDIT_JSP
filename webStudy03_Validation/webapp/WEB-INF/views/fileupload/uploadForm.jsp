<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method = "post" enctype="multipart/form-data">
	<input type = "text" name = "uploader" placeholder="업로더"/>
	<input type = "file" name = "uploadFile1" accept="image/*"/>
	<input type = "file" name = "uploadFile1" accept="image/*"/>
	<input type = "file" name = "uploadFile2" accept="image/*"/>
	<input type = "file" name = "uploadFile2" accept="image/*"/>
	<button type="submit">업로드</button>
</form>

<img src="<%=request.getContextPath() %><%=session.getAttribute("uploadFile1") %>">
<img src="<%=request.getContextPath() %><%=session.getAttribute("uploadFile2") %>">
<%
	session.removeAttribute("uploadFile1");
	session.removeAttribute("uploadFile2");
%>
</body>
</html>