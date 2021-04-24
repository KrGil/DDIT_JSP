<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
	<h4>${member.mem_name}님의 마이페이지
	</h4>
	<table>
		<tr>
			<th>회원이름</th>
			<td id = "id">${member.mem_id}</td>
		</tr>
		<tr>
			<th>프로필</th>
			<td>
				<img src="data:image/*;base64,${member.base64Image}">
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${member.mem_name}</td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td>${member.mem_regno1}</td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td>${member.mem_regno2}</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>${member.mem_bir}</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${member.mem_zip}</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>${member.mem_add1}</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>${member.mem_add2}</td>
		</tr>
		<tr>
			<th>집전번</th>
			<td>${member.mem_hometel}</td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td>${member.mem_comtel}</td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td>${member.mem_hp}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${member.mem_mail}</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>${member.mem_job}</td>
		</tr>
		<tr>
			<th>취미</th>
			<td>${member.mem_like}</td>
		</tr>
		<tr>
			<th>기념일</th>
			<td>${member.mem_memorial}</td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td>${member.mem_memorialday}</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${member.mem_mileage}</td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td>${member.mem_delete}</td>
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
 						<c:set var="prodList" value="${member.prodList }"/>
 						<c:choose>
							<c:when test="${not empty prodList}">
		 						<c:forEach items="${prodList}" var="prod">
	 								<tr>
									<td>${prod.prod_id}</td>
									<td><a href="${cPath}/prod/prodView.do?what=${prod.prod_id}">${prod.prod_name}</a></td>
									<td>${prod.lprod_nm}</td>
									<td>${prod.buyer.buyer_name}</td>
									<td>${prod.prod_cost}</td>
									<td>${prod.prod_price}</td>
									<td>${prod.prod_mileage}</td>
								</tr> 		
	 							</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
	 								<td colspan="7">
	 									구매기록이 없음.
	 								</td>
 								</tr>
							</c:otherwise>
 						</c:choose>
 					</tbody>				
				</table>
			</td>
		</tr>
	</table>
	<form id = "deleteForm" action = "${cPath }/member/memberDelete.do" method="post">
		<input type = "hidden" name = "password"/>
		<input type = "hidden" name = "id"/>
	</form>
	<script type="text/javascript">
		let deleteForm = $("#deleteForm")
		$(".controlBtn").on("click", function(){
			let btnId = $(this).prop("id");
			this.id;
			if(btnId=="updateBtn"){
				location.href="${cPath}/member/memberUpdate.do";
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