<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>서버사이드 이벤트 처리 방법</h4>
<pre>
	** 서버사이드 이벤트 종류
	lifecycle event(init/destroy) : request, session, application	
	attribute event(add/remove/change) : request, session, application
	
	1. 이벤트 대상(target) 식별 : button, application
	2. 처리할 이벤트(event) 식별 : click
	3. 처리할 내용을 가진 핸들러 구현 : function, listener
	4. 이벤트 대상에게 핸들러를 부착 : onclick, web.xml(listener)
	
	<button type = "button" id = "clickBtn">클릭</button>
	<script type="text/javascript">
		let button = document.getElementById("clickBtn");
		function clickHandler(){
			alert("클릭!");
		}
		button.onclick=clickHandler;
	</script>
</pre>
</body>
</html>