<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${cPath }/board/authenticate.do" method="post">
	<input type="hidden" name="bo_no" value="${search.bo_no}"/>
	<input type="text" name="bo_pass"/>
	<input type="submit" value="전송"/>
</form>
</body>
</html>