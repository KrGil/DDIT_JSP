<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />

<c:if test="${not empty message }">
	<script type="text/javascript">
			alert("${message}");
	</script>
</c:if>

</head>
<body>
<form method="post" encType ="multipart/form-data">
	<input type="hidden" name="prod_id" value="${prod.prod_id}" />	
	<table>
		<tr>
			<th>상품명</th>
			<td><input type="text" name="prod_name" required
				value="${prod.prod_name}" />
				<span class="error">${errors["prod_name"]}</span></td>
		</tr>
<!-- 		===================== -->
		<tr>
			<th>분류코드</th>
			<td>
				<select name="prod_lgu">
					<option value>상품분류</option>
					<c:forEach items="${lprodList }" var="lprod">
					<c:set var="selected" value='${lprod.lprod_gu eq prod.prod_lgu ? "selected":""}'/>
						<option value="${lprod.lprod_gu }" ${selected }>
							${lprod.lprod_nm }
						</option>
					</c:forEach>
				</select>
				<span class="error">${errors["prod_lgu"]}</span></td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>
			<select name="prod_buyer">
				<option value>거래처선택</option>
				<c:forEach items="${buyerList }" var="buyer">
					<c:set var="selected" value="${buyer.buyer_id eq prod.prod_buyer ? 'selected':'' }"/>
					<option class="${buyer.buyer_lgu }"value="${buyer.buyer_id }" ${selected }>
						${buyer.buyer_name }
					</option>
				</c:forEach>
			</select>
				<span class="error">${errors["prod_buyer"]}</span></td>
		</tr>
<!-- 		===================== -->
		<tr>
			<th>구매가</th>
			<td><input type="number" name="prod_cost" required
				value="${prod.prod_cost}" />
				<span class="error">${errors["prod_cost"]}</span></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><input type="number" name="prod_price" required
				value="${prod.prod_price}" />
				<span class="error">${errors["prod_price"]}</span></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><input type="number" name="prod_sale" required
				value="${prod.prod_sale}" />
				<span class="error">${errors["prod_sale"]}</span></td>
		</tr>
		<tr>
			<th>상품정보</th>
			<td><input type="text" name="prod_outline" required
				value="${prod.prod_outline}" />
				<span class="error">${errors["prod_outline"]}</span></td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><input type="text" name="prod_detail"
				value="${prod.prod_detail}" />
				<span class="error">${errors["prod_detail"]}</span></td>
		</tr>
		<tr>
			<th>이미지</th>
			<td> <input type="file" name="prod_image" accept="image/*"/>
				<span class="error">${errors["prod_img"]}</span>
			</td>
		</tr>
		<tr>
			<th>재고</th>
			<td><input type="number" name="prod_totalstock" required
				value="${prod.prod_totalstock}" />
				<span class="error">${errors["prod_totalstock"]}</span></td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td><input type="number" name="prod_properstock" required
				value="${prod.prod_properstock}" />
				<span class="error">${errors["prod_properstock"]}</span></td>
		</tr>
		<tr>
			<th>크기</th>
			<td><input type="text" name="prod_size"
				value="${prod.prod_size}" />
				<span class="error">${errors["prod_size"]}</span></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><input type="text" name="prod_color"
				value="${prod.prod_color}" />
				<span class="error">${errors["prod_color"]}</span></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><input type="text" name="prod_delivery"
				value="${prod.prod_delivery}" />
				<span class="error">${errors["prod_delivery"]}</span></td>
		</tr>
		<tr>
			<th>단위</th>
			<td><input type="text" name="prod_unit"
				value="${prod.prod_unit}" />
				<span class="error">${errors["prod_unit"]}</span></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><input type="number" name="prod_qtyin"
				value="${prod.prod_qtyin}" />
				<span class="error">${errors["prod_qtyin"]}</span></td>
		</tr>
		<tr>
			<th>판매량</th>
			<td><input type="number" name="prod_qtysale"
				value="${prod.prod_qtysale}" />
				<span class="error">${errors["prod_qtysale"]}</span></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><input type="number" name="prod_mileage"
				value="${prod.prod_mileage}" />
				<span class="error">${errors["prod_mileage"]}</span></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="전송" />
				<input type="reset" value="취소" />
			</td>
		</tr>
	</table>
</form>	
	<script type="text/javascript">
		let prod_buyerTag =	$("[name='prod_buyer']").val("${prod.prod_buyer}");
		$("[name='prod_lgu']").on("change", function(){
			let selectedLgu = $(this).val();
			if(selectedLgu){
				prod_buyerTag.find("option").hide();
				prod_buyerTag.find("option."+selectedLgu).show();
			}else{
				prod_buyerTag.find("option").show();
			}
			prod_buyerTag.find("option:first").show();
		}).val("${prod.prod_lgu}");
	</script>
</body>
</html>











