<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageContext</title>
</head>
<body>
<h4>PageContext</h4>
<pre>
	: 하나의 JSP에 대한 모든 정보를 가진 객체.
	: 가장 먼저 생성되는 기본 객체로 나머지 기본객체에 대한 참조를 소유함.
	<%=request==pageContext.getRequest() %>
	<%= pageContext.getRequest() instanceof HttpServletRequest%>
	<%=((HttpServletRequest)pageContext.getRequest()).getRequestURI() %>
	<%=session==pageContext.getSession() %>
	<%=application == pageContext.getServletContext()%>
	${pageContext.request.contextPath }
	
	1. 영역(scope) 제어  / request session 등 세가지 scope를 건드릴 수 있다.
	2. 에러 데이터 확보
	3. 페이지 이동(dispatche 방식의 이동)
	<%
		String relativeUrlPath = "/04/localeDesc.jsp";
// 		pageContext.forward(relativeUrlPath);
		pageContext.include(relativeUrlPath);
// 		request.getRequestDispatcher(relativeUrlPath).include(request, response);
	%>
	인클루드 이후의 텍스트
</pre>
</body>
</html>