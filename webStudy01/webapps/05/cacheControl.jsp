<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/cacheControl</title>
</head>
<body>
<h4>캐시 제어</h4>
<pre>
	캐시 제어를 위한 응답 헤더
	1) Cache-Control(Http 1.1) : public / 라우터에 저장, private / 개인컴퓨터에 저장, no-cache(no-store)
								: max-age(초 단위) 
	2) Pragma(Http 1.0) : public / 라우터에 저장, private / 개인컴퓨터에 저장, no-cache(no-store)
	3) Expires : 캐시 만료 시점(date)
	<%
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");// add
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Pragma", "no-store");// add
		response.setDateHeader("Expires", 0);
	%>
</pre>
<img alt="" src="<%=application.getContextPath() %>/images/ccc.jpg"/>
</body>
</html>