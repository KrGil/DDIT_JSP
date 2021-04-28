<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.Constants"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<h4>Welcome Page~</h4>
<pre>
	누적 방문자수 : <%=application.getAttribute(Constants.SESSIONCOUNTATTRNAME) %>
</pre>
<ul>
	<c:forEach items="${userList }" var="user">
		<li>${user.mem_name }</li>
	</c:forEach>
</ul>
<%	// 인증 시 무조건post방식으로 
	MemberVO authMember = (MemberVO)session.getAttribute("authMember");
	if(authMember != null){
%>
		<form name = "logoutForm" method = "post" action = "<%=request.getContextPath() %>/login/logout.do"></form>
		<a href="<%=request.getContextPath()%>/mypage.do"><%=authMember.getMem_name() %></a>님 [<%=authMember.getMem_role() %>]
		<a href="#" onclick = "clickHandler(event);">로그아웃</a>
		<script type="text/javascript">
			function clickHandler(event){
				event.preventDefault();
				document.logoutForm.submit();
				return false;
			}
		</script>
<%
	}else{
%>
		<a href="<%=request.getContextPath() %>/login/loginForm.jsp">로그인</a>
		<a href="<%=request.getContextPath() %>/member/memberInsert.do">회원가입</a>
<%
	}
%>
