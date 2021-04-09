<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

	int res = 0;
	if(request.getAttribute("res")!=null){
 		res = (Integer)request.getAttribute("res");
 		System.out.println(res);
	}
%>
<h4>팩토리얼 연산 처리</h4>
<!-- 동기처리 연산 수행.
비동기 처리 연산 수행(결과값 - JSON, HTML) : 마샬링, 직렬화.. -->
<form method="post">
  <input type="number" name="single" />
  <input type="submit" value="=" /> <!-- factorial은 단항연산자 > submit하면 팩토리얼 연산 처리 -->
  <!-- 얘 WEB-INF에 있어서 얘만 있어서는 아무것도 못함 -->
</form>
<div id="result"><%=res %></div>
</body>
</html>