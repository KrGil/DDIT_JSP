<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<select id = "blood">
	<%	// init에 넣어서 쓸 수 있다. 왜냐하면 공통된 데이터니까.
		Map<String, String> bloodMap = (Map)application.getAttribute("bloodMap");
		for(Entry<String, String> entry : bloodMap.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
	%>
		<option value = "<%=key%>"><%=value %></option>
	<%
		}
	%>
	</select>
	<script type="text/javascript">
		var select = document.querySelector("#blood");
		select.onchange = function(event){
			let blood = event.target.value;
			location.href = "<%=request.getContextPath()%>/blood/getContent.do?blood="+blood;
		}
	</script>
</body>
</html>