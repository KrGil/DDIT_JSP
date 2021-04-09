<%@page import="kr.or.ddit.vo.SearchVO"%>
<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<%
		PagingVO<MemberVO> pagingVO = (PagingVO) request.getAttribute("pagingVO");
		List<MemberVO> memberList = pagingVO.getDataList();
		if(memberList.size()>0){
			for(MemberVO member : memberList){
				%>
				<tr>
					<td><%=member.getRnum() %></td>
					<td><%=member.getMem_id() %></td>
					<td><%=member.getMem_name() %></td>
					<td><%=member.getMem_mail() %></td>
					<td><%=member.getMem_hp() %></td>
					<td><%=member.getMem_mileage()%></td>
					<td>
						<%="Y".equals(member.getMem_delete())?"탈퇴" : "" %>
					</td>
					<td><%=member.getMem_add1()%></td>
				</tr>
				<%
			}
		}else{
			%>
			<tr>
				<td colspan="5">
					등록된 데이터 없음.
			<%
		}
			%>
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
							<option value>전체</option>
							<option value="name">이름</option>
							<option value="address">지역</option>
						</select>
						<input type = "text" name = "searchWord" value="${pagingVO.simpleSearch.searchWord }" />
						<input id = "searchBtn" type = "button" value = "검색" />
					</div>
				<div id = "pagingArea">
					<%=pagingVO.getPagingHTML() %>
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