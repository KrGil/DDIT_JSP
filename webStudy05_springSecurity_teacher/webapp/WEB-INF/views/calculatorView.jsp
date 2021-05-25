<%@page import="kr.or.ddit.vo.CalculateVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	CalculateVO vo = (CalculateVO) request.getAttribute("vo");
%>    
<p><%=vo.getExpression() %></p>