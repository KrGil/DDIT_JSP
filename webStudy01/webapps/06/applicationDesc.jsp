<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>ServletContext</h4>
<pre>
	: 하나의 웹 어플리케이션과 해당 앱이 운영되는 서버에 대한 정보를 가진 객체
	
	1. 서버의 정보 확인
		<%=application.getServerInfo() %>
		<%=application.getMajorVersion()+"."+application.getMinorVersion() %>
	2. 서버와의 통신
		<%=application.getMimeType("test.jpg") %>
	3. 컨텍스트 파라미터 확보
		<%=application.getInitParameter("contentFolder") %>
		ServletContext's hashcode : <%=application.hashCode() %>, <%=getServletContext().hashCode() %>
	4. 로그 기록
		<%
			application.log("로그 메시지 기록");
		%>	
	5. ***** 웹 리소스 확보
		file system path  = getRealPath(resource URL-server side 방식)
		URL = getResource(resource URL-server side 방식)
		InputStream = getResourceAsStream(resource URL-server side 방식)
	application은 이 목적을 위해 많이 사용한다.
	
</pre>
</body>
</html>