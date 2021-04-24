<%@page import="kr.or.ddit.utils.CookieUtils"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.error{
		color: red;
		font-weight: bold;
	}
</style>
</head>
<body>
<%
	CookieUtils utils = new CookieUtils(request);
	String idCookie = null;
	if(utils.exists("idCookie")){
		idCookie = utils.getCookieValue("idCookie");
	}
	
	String message = (String) request.getAttribute("message");
	if(message==null){
		message = (String) session.getAttribute("message");
		// flash attribute
		session.removeAttribute("message");
	}
	if(message!=null && !message.isEmpty()){
		%>
		<div class="error"><%=message %></div>
		<%
	}
%>
<form action="<%=request.getContextPath() %>/login/loginCheck.do" method="post">
	<input type="text" name="mem_id" placeholder="아이디"
		value="<%=Objects.toString(idCookie, "") %>"
	/>
	<input type="text" name="mem_pass" placeholder="비밀번호"/>
	<input type="checkbox" name="saveId" 
		value="saveId" <%=idCookie!=null?"checked":"" %>>아이디기억하기
	<input type="submit" value="로그인" />
</form>
</body>
</html>












