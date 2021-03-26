<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>Cookie</h4>
	<pre>
	: Http의 stateless 특성을 보완하기 위해 최소한의 상태 정보를 저장하는 개념.
		1. session : 상태 정보를 서버사이드에서 유지.
		2. cookie : 상태 정보를 클라이언트 사이드에서 유지.
		
		** 쿠키 사용 방법(포츈쿠키(header))
		<%
		Cookie sampleCookie = new Cookie("sampleCookie", "CookieValue");
		response.addCookie(sampleCookie);

		// 8.5 버전 이전에는 이렇게 인코딩이 필요함.
		String koreanValue = URLEncoder.encode("한글쿠키", "utf-8");
		Cookie koreanCookie = new Cookie("koreanCookie", koreanValue);
		response.addCookie(koreanCookie);

		// tomcat 8.5 버전에서는 이러한 방법을 쓸 수 없다.
		// 			Cookie allDomain = new Cookie("allDomainCookie", "all domain");
		// 			allDomain.setDomain(".cbg.com");
		// 			response.addCookie(allDomain);

		Cookie allPathCookie = new Cookie("allPathCookie", "All~Path~");
		// 			allPathCookie.setPath(request.getContextPath());
		allPathCookie.setPath("/");
		response.addCookie(allPathCookie);

		Cookie longLiveCookie = new Cookie("longLiveCookie", "Long~~");
		// 			longLiveCookie.setMaxAge(60*60*24*3);
		longLiveCookie.setMaxAge(-1);
		longLiveCookie.setPath(request.getContextPath());
		response.addCookie(longLiveCookie);
	%>
		
		1. 쿠키 생성(서버사이드(jsp))
		2. 응답에 쿠키를 포함하여 전송(header) //서버에서 하는 일
		
		3. 쿠키가 브라우저가 가진 저장소에 저장.
		4. 저장되어잇던 쿠키가 다음번 요청에 실려 재전송.(header) // 클라이언트가 하는일
		
		5. 요청에 포함된 쿠키를 꺼내서 상태 복원 : 
			<a href="cookieView.jsp">쿠키 확인하기(동일경로)</a>
			<a href="../08/cookieView.jsp">쿠키 확인하기(다른경로)</a>
		
		** 쿠키 속성의 종류
		1. name(required)
		2. value(required) : 특수문자를 포함하는 경우, RFC2396 규약에 따라 url encoding 방식을 사용함.
		3. domain(host) : 쿠키의 재전송을 결정하는 조건.(첫번째)
			생략시, 쿠키 생성 도메인이 기본값으로 반영됨.
			ex) .naver.com : host에 상관없이 재전송 가능.
		4. path : 쿠키의 재전송을 결정하는 조건.(두번째)
			다음번 요청이 설정된 경로 이하로 발생할 때만 재전송.
			생략 시, 쿠키 생성시의 경로가 기본값으로 반영됨.
		5. maxAge(expires) : 쿠키의 만료 시점 (second)
			생략 시, 기본값으로 세션과 동일한 만료 시점이 사용.
			0 : 쿠키 삭제(쿠키의 모든 조건이 동일한 경우)
			-1 : 브라우저 종료 시
		6. httpOnly : 
		7. secure

</pre>

</body>
</html>























