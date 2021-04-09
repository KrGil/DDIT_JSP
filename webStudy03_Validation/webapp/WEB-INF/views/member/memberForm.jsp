<%@page import="java.util.Objects"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.error {
	color: red;
}
</style>
<jsp:include page="/includee/preScript.jsp" />
<%
	String message = (String) request.getAttribute("message");
	if(message!=null && !message.isEmpty()){
		%>
		<script type="text/javascript">
			alert("<%=message %>");
		</script>
		<%
	}
%>
</head>
<body>
	<h4>가입양식</h4>
	<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request" />
	<jsp:useBean id="errors" class="java.util.LinkedHashMap"
		scope="request" />
	<form method="post" id="memberForm">
		<table>
			<%
				String command = (String) request.getAttribute("command");
				if(!"update".equals(command)){
			%>
		
			<tr>
				<th>회원아이디</th>
				<td><input type="text" name="mem_id" 
					value="<%=Objects.toString(member.getMem_id(), "")%>" />
					<span class="error"><%=Objects.toString(errors.get("mem_id"), "")%></span>
					<button type="button" id="idCheck">아이디중복체크</button>
				</td>
			</tr>
			<%
			}
			%>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="mem_pass"  />
				<span class="error"><%=errors.get("mem_pass")%></span></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="mem_name" 
					value="<%=member.getMem_name()%>" />
				<span class="error"><%=errors.get("mem_name")%></span></td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td><input type="text" name="mem_regno1"
					value="<%=member.getMem_regno1()%>" />
				<span class="error"><%=errors.get("mem_regno1")%></span></td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="text" name="mem_regno2"
					value="<%=member.getMem_regno2()%>" />
				<span class="error"><%=errors.get("mem_regno2")%></span></td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="date" name="mem_bir"
					value="<%=member.getMem_bir()%>" />
				<span class="error"><%=errors.get("mem_bir")%></span></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="mem_zip" 
					value="<%=member.getMem_zip()%>" />
				<span class="error"><%=errors.get("mem_zip")%></span></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="text" name="mem_add1" 
					value="<%=member.getMem_add1()%>" />
				<span class="error"><%=errors.get("mem_add1")%></span></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" name="mem_add2" 
					value="<%=member.getMem_add2()%>" />
				<span class="error"><%=errors.get("mem_add2")%></span></td>
			</tr>
			<tr>
				<th>집전번</th>
				<td><input type="text" name="mem_hometel"
					value="<%=member.getMem_hometel()%>" />
				<span class="error"><%=errors.get("mem_hometel")%></span></td>
			</tr>
			<tr>
				<th>회사전번</th>
				<td><input type="text" name="mem_comtel"
					value="<%=member.getMem_comtel()%>" />
				<span class="error"><%=errors.get("mem_comtel")%></span></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><input type="text" name="mem_hp"
					value="<%=member.getMem_hp()%>" />
				<span class="error"><%=errors.get("mem_hp")%></span></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="mem_mail" 
					value="<%=member.getMem_mail()%>" />
				<span class="error"><%=errors.get("mem_mail")%></span></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" name="mem_job"
					value="<%=member.getMem_job()%>" />
				<span class="error"><%=errors.get("mem_job")%></span></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" name="mem_like"
					value="<%=member.getMem_like()%>" />
				<span class="error"><%=errors.get("mem_like")%></span></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="text" name="mem_memorial"
					value="<%=member.getMem_memorial()%>" />
				<span class="error"><%=errors.get("mem_memorial")%></span></td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><input type="date" name="mem_memorialday"
					value="<%=member.getMem_memorialday()%>" />
				<span class="error"><%=errors.get("mem_memorialday")%></span></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td>3000</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="저장" /></td>
			</tr>
		</table>
	</form>
<%
if(!"update".equals(command)){
%>	
<script type="text/javascript" src="<%=request.getContextPath() %>/js/member/memberForm.js"></script>
<%
}
%>
</body>
</html>












