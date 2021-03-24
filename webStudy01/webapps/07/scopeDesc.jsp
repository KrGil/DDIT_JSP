<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/scopeDesc.jsp</title>
</head>
<body>
<h4>영역( Scope )</h4>
<pre>
	: 서블릿이나 JSP의 인스턴스의 관리 권한을 컨테이너가 가짐.
	전역변수를 통한 데이터 공유가 불가능.
	웹 어플리케이션 내에서 데이터를 공유할 목적으로 운영되는 저장 공간.
	영역(scope-Map)을 통해 공유되는 데이터 : 속성(attribure-Entry)
	(entry key-value 형식)
	각 영역에 대한 참조를 소유한 기본 객체와 동일한 사용범위(생명주기) 가짐
	
	4개의 기본객체의 map들이 scope다.
	
	1. page scope(pageContext) - 해당 페이지로 사용 범위가 제한
	2. request(HttpServletRequest) - 하나의 요청이 살아있는 한 사용범위가 유지됨.
	3. session(HttpSession) - 한 세션동안 사용가능한 영역(사용자 각각)
	4. application(ServletContext) - 어플리케이션과 생명주기 동일(모든 사용자가 공유한다)
	
	각 객체들의생명주기는 기본객체의 생명주기와 동읽하다.
	
</pre>

</body>
</html>