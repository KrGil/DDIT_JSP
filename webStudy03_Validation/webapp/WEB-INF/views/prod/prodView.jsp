<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<form  action="${cPath }/prod/prodUpdate.do" >
		<input type="hidden" name="prod_id"value ="${prod.prod_id}"/>
	</form>
	<table>
		<tr>
			<th>상품코드</th>
			<td>${prod.prod_id}</td>
		</tr>
		
		<tr>
			<th>상품명</th>
			<td>${prod.prod_name}</td>
		</tr>
		<tr>
			<th>분류명</th>
			<td>${prod.lprod_nm}</td>
		</tr>
		<tr>
			<th>거래처 정보</th>
			<td>
				<table>
					<thead>
						<tr>
							<th>거래처명</th>
							<th>담당자명</th>
							<th>연락처</th>
							<th>주소1</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${buyer.buyer_name}</td>
							<td>${buyer.buyer_charger}</td>
							<td>${buyer.buyer_comtel}</td>
							<td>${buyer.buyer_add1}</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td>${prod.prod_cost}</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>${prod.prod_price}</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td>${prod.prod_sale}</td>
		</tr>
		<tr>
			<th>상품정보</th>
			<td>${prod.prod_outline}</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>${prod.prod_detail}</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td>
				<img src="${cPath }/prodImages/${prod.prod_img}"/>
			</td>
		</tr>
		<tr>
			<th>재고</th>
			<td>${prod.prod_totalstock}</td>
		</tr>
		<tr>
			<th>입고일</th>
			<td>${prod.prod_insdate}</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>${prod.prod_properstock}</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>${prod.prod_size}</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>${prod.prod_color}</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>${prod.prod_delivery}</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>${prod.prod_unit}</td>
		</tr>
		<tr>
			<th>입고량</th>
			<td>${prod.prod_qtyin}</td>
		</tr>
		<tr>
			<th>판매량</th>
			<td>${prod.prod_qtysale}</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${prod.prod_mileage}</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button">상품목록으로</button>
				<input type="button" value="수정" class="controlBtn" id="updateBtn">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				
			</td>
		</tr>
	</table>
	<jsp:include page="/includee/preScript.jsp" />
	<script type="text/javascript">
		$(".controlBtn").on("click", function(){
			let btnId = $(this).prop("id");
			if(btnId=="updateBtn"){
				location.href="${cPath}/prod/prodUpdate.do?what=${prod.prod_id}";
			}
		});
	</script>
</body>
</html>











