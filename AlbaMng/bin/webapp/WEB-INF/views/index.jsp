<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>웰컴 페이지(${pageContext.session.id })</h4>
<c:if test="${not empty sessionScope.authMember }">
	<form name="logoutForm" action="${pageContext.request.contextPath }/login/logout.do" method="post"></form>
	현재 로그인 유저 : <a href="${pageContext.request.contextPath }/mypage.do">${authMember.mem_name }</a>
	<a href="#" onclick="document.logoutForm.submit();">로그아웃</a>

</c:if>
<c:if test="${empty sessionScope.authMember }">
	<a href="${pageContext.request.contextPath }/login/loginForm.jsp">로그인하기</a>
	<a href="${pageContext.request.contextPath }/registMember.do">회원가입</a>

</c:if>
</body>
</html>














