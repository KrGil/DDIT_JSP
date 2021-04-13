<%@page import="java.util.Set"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
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
			<th>프로필</th>
			<td>
				<img src="data:image/*;base64,<%=member.getBase64Image()%>">
			</td>
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
		<tr>
			<th>구매목록</th>
			<td>
				<table>
					<thead>
						<tr>
							<th>상품코드</th>
							<th>상품명</th>
							<th>상품분류명</th>
							<th>거래처명</th>
							<th>구매가</th>
							<th>판매가</th>
							<th>마일리지</th>
						</tr>
					</thead>
 					<tbody>
 						<%
 							Set<ProdVO> prodList = member.getProdList();
 							if(prodList.size()>0){
 								for(ProdVO prod : prodList){
 								%>
								<tr>
									<td><%=prod.getProd_id() %></td>
									<td><a href="<%=request.getContextPath() %>/prod/prodView.do?what=<%=prod.getProd_id()%>"><%=prod.getProd_name() %></a></td>
									<td><%=prod.getLprod_nm() %></td>
									<td><%=prod.getBuyer().getBuyer_name() %></td>
									<td><%=prod.getProd_cost() %></td>
									<td><%=prod.getProd_price() %></td>
									<td><%=prod.getProd_mileage() %></td>
								</tr> 								
 								<%
 								}
 							}else{
 								%>
 								<tr>
	 								<td colspan="7">
	 									구매기록이 없음.
	 								</td>
 								</tr>
							<%
 							}
 							 %>
 					</tbody>				
				</table>
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