<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	// 200개의 요청이 가능하도록
		long start = System.currentTimeMillis();
		try(
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			
		){
			for(int i=1; i<=100; i++){
				String sql = "SELECT MEM_NAME FROM MEMBER WHERE MEM_ID='a001'";
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					out.println(rs.getString(1));
				}
			}
		}
		long end = System.currentTimeMillis();
	%>
	<%=end-start %>ms
</body>
</html>