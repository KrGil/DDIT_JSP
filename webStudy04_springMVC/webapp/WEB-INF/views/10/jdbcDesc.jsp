<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/jdbcDesc.jsp</title>
</head>
<body>
<h4>JDBC(Java DataBase Connectivity)</h4>
<pre>
	Facade 패턴 활용
	
	1. Driver를 찾아서, 필드 패스에 추가
	2. Driver 로딩(oracle.jdbc.driver.OracleDriver)
	3. Connection 생성
	4. 쿼리 객체 생성.
		Statement
		PreparedStatement (선컴파일된 쿼리 객체)
		CallableStatement (Procedure/Function 일련의 코드 집합을 호출)
	5. 쿼리 실행 : DML(select/insert,update,delete)
		ResultSet excuteQuery
		int excuteUpdate
	6. 자원 해제(close)
	
</pre>
<form >
	검색할 프로퍼티명 : <input type = "text" name="property_name" value = "${param.property_name}"/>
	<input type="submit" value="검색"/>
</form>
<%
	String[] headers = (String[])request.getAttribute("headers");
	List<Map<String, Object>> dataList = (List) request.getAttribute("dataList");
%>
<table>
	<thead>
		<tr>
			<%
				for(String header : headers){
					%>
					<th><%=header %></th>
					
					<%
				}
			%>
		</tr>
	</thead>
	<tbody>
		<%
			for(Map<String,Object> record: dataList){
				%>
				<tr>
					<%
						for(Entry<String, Object> entry : record.entrySet()){
					%>
							<td><%=entry.getValue() %>
					<%
						}
					%>
				</tr>
				<%
			}
		%>
	</tbody>
</table>
</body>
</html>