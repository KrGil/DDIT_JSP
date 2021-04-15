
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page = "/includee/preScript.jsp" />
</head>
<body>
<h4>회원 목록 조회</h4>

<table border= '1'>
	<thead>
		<tr>
			<th>No.</th>
			<th>회원아이디</th>
			<th>회원명</th>
			<th>이메일</th>
			<th>휴대폰</th>
			<th>마일리지</th>
			<th>탈퇴여부</th>
			<th>지역</th>
		</tr>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${not empty pagingVO.dataList }">
			<c:forEach  items="${pagingVO.dataList }" var="member">
				<tr>
					<td>${member.rnum} </td>
					<td>${member.mem_id}</td>
					<td>
					<c:url value="/member/memberView.do" var="viewURL">
						<c:param name="who" value="${member.mem_id }"></c:param>
					</c:url>
						<a href="${viewURL}">${member.mem_name}</a>
					</td>
					<td>${member.mem_mail}</td>
					<td>${member.mem_hp}</td>
					<td>${member.mem_mileage}</td>
					<td>
						${"Y" eq member.mem_delete ? "탈퇴" : ""}
					</td>
					<td>${member.mem_add1}</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="5">
					등록된 데이터 없음.
				</td>
			</tr>
		</c:otherwise>
	</c:choose>	
	
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
					<form id = "searchForm">
						<input type = "text" name = "searchType" value="${pagingVO.simpleSearch.searchType }"/>
						<input type = "text" name = "searchWord" value="${pagingVO.simpleSearch.searchWord }"/>
						<input type = "text" name = "page" />
					</form>
					<div id = "searchUI">
						<select name = "searchType">
							<option >전체</option>
							<option value="name">이름</option>
							<option value="address">지역</option>
						</select>
						<input type = "text" name = "searchWord" value="${pagingVO.simpleSearch.searchWord }" />
						<input id = "searchBtn" type = "button" value = "검색" />
					</div>
				<div id = "pagingArea">
					%{pagingVO.pagingHTML}
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<script type = "text/javascript">
	let searchForm = $("#searchForm");
	let searchUI = $("#searchUI");
	searchUI.find("[name='searchType']").val("${pagingVO.simpleSearch.searchType }");
	$("#searchBtn").on("click", function(){
		let inputs = searchUI.find(":input[name]");
		$(inputs).each(function(idx, input){
			let name = $(this).attr("name");
			let sameInput = searchForm.find("[name='"+name+"']")
			$(sameInput).val($(this).val());
		});
		searchForm.submit();
	});
	
	$("#pagingArea").on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		if(page){
			searchForm.find("[name='page']").val(page);
			searchForm.submit();
		}
		return false;
	});
</script>

</body>
</html>