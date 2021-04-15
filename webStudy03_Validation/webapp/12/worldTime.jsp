<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.ddit.or.kr" prefix="my"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>
1. java의 모든 timezone을 select로 선택 가능하도록.
2. 선택한 시간대에 맞춰 시간 출력
3. java의 모든 locale을 select로 선택 가능하도록.
4. 선택한 locale에 맞춰 출력 메시지 형식 결정
</pre>

<form>
	<select name="loc">
		<c:forEach items="${Locale.getAvailableLocales() }" var="tmp">
			<option value="${tmp.toLanguageTag()}"}>${tmp.displayLanguage }[${tmp.displayCountry }]</option>
		</c:forEach>
	</select>
	<select name="zone">
		<c:forEach items="${my:getAvailableIDs() }" var="tz">
			<option value="${tz}">${my:getTimeZone(tz).displayName }</option>
		</c:forEach>
	</select>
</form>
<%-- <c:set var="now" value="<%=new Date() %>" ></c:set> --%>
<c:set var="now" value="${my:getNow() }" ></c:set>
${now }
<fmt:setLocale value="${not empty param.loc ? param.loc : pageContext.request.locale }"/>
<c:choose>
	<c:when test="${empty param.zone }">
		<c:set var="timeZone" value="${my:getDefaultTimeZone() }"/>
	</c:when>
	<c:otherwise>
		<c:set var="timeZone" value="${my:getTimeZone(param.zone) }" />
	</c:otherwise>
</c:choose>	
<fmt:formatDate value="${now }" type="both" timeZone="${timeZone }"/>

<script type="text/javascript">
	function chageHandler(event){
		let select = event.target;
		select.form.submit();
	}
	let selects = document.getElementsByTagName("select");
	for(let i=0; i<selects.length; i++){
		selects[i].onchange = chageHandler;
	}
	
	document.forms[0].loc.value="${param.loc}";
	document.forms[0].zone.value="${param.zone}";
</script>
</body>
</html>