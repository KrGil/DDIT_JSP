<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>Welcome Page~</h4>
	<%	// 인증 시 무조건post방식으로 
		String authId = (String)session.getAttribute("authId");
		if(authId != null && !authId.isEmpty()){
	%>
			<form name = "logoutForm" method = "post" action = "<%=request.getContextPath() %>/login/logout.do"></form>
			<%=authId %>님 
			<a href="#" onclick = "clickHandler(event);">로그아웃</a>
			<script type="text/javascript">
				function clickHandler(event){
					event.preventDefault();
					document.logoutForm.submit();
					return false;
				}
			</script>
	<%
		}else{
	%>
			<a href="<%=request.getContextPath() %>/login/loginForm_cookie.jsp">로그인</a>
	<%
		}
	%>
</body>
</html>