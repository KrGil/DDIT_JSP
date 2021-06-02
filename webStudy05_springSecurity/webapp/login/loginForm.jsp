<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<style type="text/css">
	.error{
		color: red;
		font-weight: bold;
	}
</style>
</head>
<body>
${sessionScope }
<c:if test="${not empty message }">
	<span class="error">${message }</span>
</c:if>
<form action="${cPath }/login/loginCheck.do" method="post" class="form-inline">
	<input type="text" name="mem_id" placeholder="아이디"
		class="form-control mr-2"
		value="${cookie.idCookie.value}"
	/>
	<input type="text" name="mem_pass" placeholder="비밀번호"
		class="form-control mr-2"
	/>
	<div class="form-check">
	  <input class="form-check-input" type="checkbox" 
	  	name="saveId" id="saveId" value="saveId">
	  <label class="form-check-label"  for="saveId">
	    아이디 기억하기
	  </label>
	</div>
	<input type="submit" value="로그인" class="btn btn-primary"/>
</form>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>












