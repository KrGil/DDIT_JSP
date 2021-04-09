<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.or.krgil.db.ConnectionFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>성능 체크</h4>
<pre>
	소요(반응)시간 = processing time + latency time
	<!-- processing time은 거의 영향을 주지 않는다. -->
	<a href = "oneConnOneProcess.jsp">한번 연결하고 한번 처리한 소요시간</a> : 9ms 
	<a href = "100Conn100Process.jsp">백번 연결하고 백번 처리한 소요시간</a> : 701ms 
	<a href = "oneConn100Process.jsp">한번 연결하고 백번 처리한 소요시간</a> : 14ms 
	
	<a href = "oneConn100Process.jsp">한번 연결하고 한번 처리한 소요시간</a> : 0ms 
	<a href = "oneConn100Process.jsp">백번 연결하고 백번 처리한 소요시간</a> : 11ms 
	<a href = "oneConn100Process.jsp">한번 연결하고 백번 처리한 소요시간</a> : 6ms 
</pre>

</body>
</html>