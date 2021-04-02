<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
	<%
		MemberVO info = (MemberVO) request.getAttribute("memberInfo");
	%>
	<!-- custom태그 serverside코드 -->
	<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request"></jsp:useBean>
	<h4><%=member.getMem_name()%>님의 마이페이지
	</h4>
	<table>
		<tr>
			<th>회원이름</th>
			<td id = "id"><%=member.getMem_id()%></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><%=member.getMem_pass()%></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=member.getMem_name()%></td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td><%=member.getMem_regno1()%></td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td><%=member.getMem_regno2()%></td>
		</tr>
		<tr>
			<th>생일</th>
			<td><%=member.getMem_bir()%></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><%=member.getMem_zip()%></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><%=member.getMem_add1()%></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><%=member.getMem_add2()%></td>
		</tr>
		<tr>
			<th>집전번</th>
			<td><%=member.getMem_hometel()%></td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td><%=member.getMem_comtel()%></td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td><%=member.getMem_hp()%></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=member.getMem_mail()%></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><%=member.getMem_job()%></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><%=member.getMem_like()%></td>
		</tr>
		<tr>
			<th>기념일</th>
			<td><%=member.getMem_memorial()%></td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td><%=member.getMem_memorialday()%></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><%=member.getMem_mileage()%></td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td><%=member.getMem_delete()%></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" class = "controlBtn" id="updateBtn">
				<button type="button" class="controlBtn" id="deleteBtn">탈퇴</button>
			</td>
		</tr>
	</table>
	<form id = "deleteForm" action = "<%=request.getContextPath() %>/member/memberDelete.do" method="post">
		<input type = "hidden" name = "password"/>
		<input type = "hidden" name = "id"/>
	</form>
	<script type="text/javascript">
		let deleteForm = $("#deleteForm")
		$(".controlBtn").on("click", function(){
			let btnId = $(this).prop("id");
			this.id;
			if(btnId=="updateBtn"){
				location.href="<%=request.getContextPath()%>/member/memberUpdate.do";
			}else if(btnId == "deleteBtn"){
				let password = prompt("비번 입력");
				if(!password){
					return;
				}else{
					deleteForm.find("[name='password']").val(password);
					deleteForm.find("[name='id']").val(id);
					deleteForm.submit();
				}
			}
// 			/member/memberUpdate.do
		})
	</script>
</body>
</html>