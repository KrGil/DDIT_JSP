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
		color : red;
		font-weight : bold;
	}
</style>
</head>
<body>
	<%
		String failed_id = (String)session.getAttribute("failedId");
		//flash attribute 방식으로 꺼내자마자 초기화시키기
		session.removeAttribute("failedId");
		String message = (String)request.getAttribute("message");
		if(message==null){
			message = (String)session.getAttribute("message");
			// flash attribute session에서 값 꺼내자마자 비워주는방식
			session.removeAttribute("message");
		}
		if(message!= null && !message.isEmpty()){
			%>
			<div class="error"><%=message %></div>
			<%
		}
	%>
	<form action="<%=request.getContextPath() %>/login/loginCheck.do" method="post">
		<input type="text" name="mem_id" placeholder="아이디" value = "<%=Objects.toString(failed_id, "")%>"/>
		<input type="text" name="mem_pass" placeholder="비밀번호"/>
		<input type="checkbox" name ="saveId" value = "saveId">아이디기억하기
		<!-- 1. 1주일간 상태 유지. 2. 체크가 되어있어야함. 3.체크하지 않는 상태 기억x 기억되고있어도 지우라. -->
		<input type="submit" value="로그인"/>
	</form>
	
</body>
</html>