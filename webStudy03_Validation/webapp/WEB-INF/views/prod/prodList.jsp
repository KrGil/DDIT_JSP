<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
		
		<c:forEach items="${lprodList}" var="lprod" >
			<option value="${lprod.lprod_gu}">
				${lprod.lprod_nm}
			</option>
		</c:forEach>
		
	</select>
	<select name="prod_buyer">
		<option value>거래처선택</option>
		<c:forEach items="${buyerList }" var="buyer">
			<option value="${buyer.buyer_id}" class="${buyer.buyer_lgu}">
					${buyer.buyer_name }
			</option>
		</c:forEach>
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
<script type="text/javascript" src="${cPath }/js/prod/prodList.js"></script>

</body>
</html>













