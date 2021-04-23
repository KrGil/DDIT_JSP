<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body class="vh-100">
	<div class="d-flex flex-column h-100">
	<div class="container-fluid flex-grow-1 overflow-auto">
		<div class="row h-100">
			<main>
				<jsp:include page="/WEB-INF/views/${viewName }.jsp"/>
			</main>
		</div>
	</div>
	<jsp:include page="/includee/footer.jsp" />
	<c:if test="${not empty message }">
		<script type="text/javascript">
			alert("${message }");
		</script>
	</c:if>
	</div>
</body>
</html>









