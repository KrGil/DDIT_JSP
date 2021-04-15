<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Locale" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/jstlDesc.jsp</title>
<style>
	.green {
		background-color:green;
	}
	.blue {
		background-color:blue;
	}
</style>
</head>
<body>
<h4>JSTL(JSp Standart Tag Library)</h4>
<select onchange="location.href='?lang='+this.value;">
<c:forEach items="${Locale.getAvailableLocales() }" var="tmp">
		<option value="${tmp.toLanguageTag() }">${tmp.displayLanguage }[${tmp.displayCountry }]</option>
</c:forEach>
</select>
<pre>
	: 커스텀 태그 라이브러리(server side)
	taglib 지시자로 커스텀 태그 로딩 후 사용.
	<prefix:tagname attributes/>
	1. core
		1) EL 변수(속성) 지원 : set, remove
			커스텀태그는 java코드다. serverside코드 페이지 소스검사에서 코드가 출력되지 않음.
			var로 선언 하면 자동으로 pageScope에 들어간다.
			<c:set var = "test" value = "테스트" scope="request"/>
			${requestScope.test}, ${requestScope["test"] }
			<c:remove var="test" scope="request"/> -> 범위 미지정 시 모든 스코프를 뒤져 다 지워버림.	
			${requestScope.test}, ${requestScope["test"] }
		2) 흐름 제어 :
			조건문 : if, choose~when~otherwise
				<c:if test = "${not empty test }">
					test 없음.
				</c:if>
				<c:choose>
					<c:when test="${empty test }">없다</c:when>
					<c:when test="${not empty test }">있다</c:when>
				</c:choose>
			${empty test ? "없다" : "있다" }
			반복문 : forEach, forTokens
				for(block variable:collection) : items, var
				for(선언절;조건절;증감절) : var, begin, end, step(>0, 1 생략 가능)감소가 없다.
				for(int i =1; i <=3; i++)
				<c:forEach var="i" begin="1" end="3" step="1">
					${i }
				</c:forEach>
				홀수만 출력하기.
				LoopTagStatus 프라퍼티 : index(var라고 선언한 녀석의 현재의 값.), count
										 first, last
				token : 문장의 구성요소 중 의미를 부여할 수 있는 최소 단위
				<c:forTokens items="아버지 가방에 들어가신다" delims=" " var="token">
					${token }
				</c:forTokens>
				<c:forTokens items="100,200,300" delims="," var="number">
					${number*3 }
				</c:forTokens>
				
				<c:forEach var="i" begin="1" end="5" step="2" varStatus="vs">
					${i }- ${vs.count } 번째 반복, ${vs.first }, ${vs.last }
				</c:forEach>
		3) URL 재처리 : url
			client상에서 페이지 이동의 절대경로를 만들어줌.
			<c:url var="memberList" value="/member/memberList.do">
				<c:param name="page" value="2"/>
			</c:url>
			<a href = "${memberList }">ㅎ회원목록의 2page</a>
			
		4) 기타 : import, out
			include는 같은 어플리케이션에서만 쓸 수 있다. import는 제한이 없다.
			<c:import url="https://www.naver.com" var="naver"/>
			escapeXml이 true라서 text 그대로 출력(소스보기와 동일하게표시)
<%-- 			<c:out value="${naver }" escapeXml="false"></c:out> --%>
	2. fmt (locale 지원)
		1) 언어 처리 : setLocale, message
			- 언어 결정(한글, 영문)
			- 메시지 번들 작성(properties)
			- locale 결정
			
			표현식을 이렇게 사용할 수 있으면 3.x el태그도 사용 가능
			${Locales.getAvailableLocales() } <%=Locale.getAvailableLocales() %>
			<c:if test="${empty param.lang }">
				<c:set var="locale" value="${pageContext.request.locale}"></c:set>
			</c:if>
			<c:if test="${not empty param.lang }">
				<c:set var="locale" value="${param.lang}"></c:set>
			</c:if>
			<fmt:setLocale value="${locale}"/>
			- 메시지 출력 : 번들 로딩, 메시지 사용.
			<fmt:bundle basename="kr.or.ddit.messages.message">
				<fmt:message key="bow"></fmt:message>
			</fmt:bundle>
			
		2) 메시지 형식 처리
			parsing : partseNumber, parseDate - 문자열을 특정 타입으로
			formatting : formatNumber, formatDate -  특정 타입을 일정한 형식으로
			<fmt:formatNumber value="30000" type="currency" var="money"/>
			<fmt:parseNumber value="${money }" type="currency" var="number"/>
			${number * 10 }
			
			<fmt:formatDate value="<%=new Date()%>" 
								type="both" dateStyle="short" var="datestr"/>
			${datestr }
			<fmt:parseDate value="${datestr }" type="both" dateStyle="short" var="dateObj"/>
			${dateObj.time }
	3. fn
		${fn:indexOf("abc", "a") }
			<c:set var = "array" value = '${fn:split("test1,test2", ",") }'/>
		${fn:join(array, "|") }
		
		
</pre>
<!--  2_9 단까지 구구단 출력 -->
<c:set var="result" value="0"/>

<table>
	<c:forEach var="dan" begin="2" end="9" step="2" varStatus="vs1">
		<tr class="${vs1.count eq 3 ? 'green':'normal' }">
		<c:forEach var="mul" begin="1" end="9" varStatus="vs2">
			<c:choose>
				<c:when test="${vs2.first or vs2.last }">
					<c:set var="clz" value="blue" />
				</c:when>
				<c:otherwise>
					<c:set var="clz" value="normal" />
				</c:otherwise>
			</c:choose>
			<td class="${clz }">${dan }*${mul }=${dan*mul }</td>
		</c:forEach>
		</tr>
	</c:forEach>
</table>
  
</table>
</body>
</html>