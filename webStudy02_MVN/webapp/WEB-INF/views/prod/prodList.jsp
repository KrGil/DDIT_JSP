<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<form id="searchForm">
	<select name="prod_lgu">
		<option value>상품분류</option>
		<% 
			List<Map<String,Object>> lprodList 
				= (List)request.getAttribute("lprodList");
			for(Map<String,Object> lprod : lprodList){
				%>
				<option value="<%=lprod.get("lprod_gu")%>">
					<%=lprod.get("lprod_nm") %>
				</option>
				<%
			}
		%>
		
	</select>
	<select name="prod_buyer">
		<option value>거래처선택</option>
		<%
			List<BuyerVO> buyerList 
				=(List) request.getAttribute("buyerList");
			for(BuyerVO buyer : buyerList){
				%>
				<option value="<%=buyer.getBuyer_id() %>" class="<%=buyer.getBuyer_lgu() %>">
					<%=buyer.getBuyer_name() %>
				</option>
				<%
			}
		%>
	</select>
	<input type="text" name="prod_name" />
	<input type="text" name="page" />
	<input type="submit" value="검색" />
	<input type="button" value="신규등록" id="newBtn"/>
</form>
<table>
	<thead>
		<tr>
			<th>No.</th>
			<th>상품코드</th>
			<th>상품분류명</th>
			<th>상품명</th>
			<th>거래처명</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody id="listBody">

	</tbody>
<tfoot>
	<tr>
		<td colspan="8" id="pagingArea">
			
		</td>
	</tr>
</tfoot>
</table>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/prod/prodList.js"></script>
</body>
</html>













