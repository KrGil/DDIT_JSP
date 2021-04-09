<%@page import="kr.or.krgil.enumpkg.OSType"%>
<%@page import="kr.or.krgil.enumpkg.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/userAgent.jsp</title>
</head>
<body>
	<h4>User Agent</h4>
	클라이언트의 브라우저를 확인.
	
	<%
// 		Map<String, String> browserMap = new LinkedHashMap<>(); // 해쉬맵 중에서도 순서가 있는 녀석 넣은 순서대로 저장된다.
// 		browserMap.put("EDG", "엣지");
// 		browserMap.put("TRIDENT", "크롬");
// 		browserMap.put("CHROME", "익스플로러");
// 		browserMap.put("OTHER", "기타등등");
		
		String agent = request.getHeader("User-Agent"); //대소문자 상관이 없는듯하다.
		
		String MSGPTRN = "당신의 브라우저는 %s이고, ";
		String MSGPTRN1 = "당신의 OS는 %s입니다.";
		
		String message = null;
		String OSMessage = null;
// 		String name = browserMap.get("OTHER");
// 		//entry - key와value 한쌍의 값을 가지고 있는 것
// 		for(Entry<String, String> entry : browserMap.entrySet()){
// 			String keyWord = entry.getKey();
// 			if(agent.contains(keyWord)){
// 				name = entry.getValue();
// 				break;
// 			}
// 		}
		
		message = String.format(MSGPTRN, BrowserType.getBrowserName(agent));
		OSMessage = String.format(MSGPTRN1, OSType.getOSName(agent));
	%>
	<p><%=agent%></p>
	
	<script>
		alert('<%=message%><%=OSMessage%>');
	</script>
	
</body>
</html>