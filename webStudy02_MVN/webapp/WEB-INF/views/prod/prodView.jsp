<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<%
		ProdVO prod = (ProdVO) request.getAttribute("prod");
	%>
	<form  action="<%=request.getContextPath() %>/prod/prodUpdate.do" >
			<input type="hidden" name="prod_id"value ="<%=prod.getProd_id()%>"/>
	</form>
	<table>
		<tr>
			<th>상품코드</th>
			<td><%=prod.getProd_id()%></td>
		</tr>
		
		<tr>
			<th>상품명</th>
			<td><%=prod.getProd_name()%></td>
		</tr>
		<tr>
			<th>분류명</th>
			<td><%=prod.getLprod_nm()%></td>
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
							<%
								BuyerVO buyer = prod.getBuyer();
							%>
							<td><%=buyer.getBuyer_name() %></td>
							<td><%=buyer.getBuyer_charger() %></td>
							<td><%=buyer.getBuyer_comtel() %></td>
							<td><%=buyer.getBuyer_add1() %></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td><%=prod.getProd_cost()%></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><%=prod.getProd_price()%></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><%=prod.getProd_sale()%></td>
		</tr>
		<tr>
			<th>상품정보</th>
			<td><%=prod.getProd_outline()%></td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><%=prod.getProd_detail()%></td>
		</tr>
		<tr>
			<th>이미지</th>
			<td><%=prod.getProd_img()%></td>
		</tr>
		<tr>
			<th>재고</th>
			<td><%=prod.getProd_totalstock()%></td>
		</tr>
		<tr>
			<th>입고일</th>
			<td><%=prod.getProd_insdate()%></td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td><%=prod.getProd_properstock()%></td>
		</tr>
		<tr>
			<th>크기</th>
			<td><%=prod.getProd_size()%></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><%=prod.getProd_color()%></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><%=prod.getProd_delivery()%></td>
		</tr>
		<tr>
			<th>단위</th>
			<td><%=prod.getProd_unit()%></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><%=prod.getProd_qtyin()%></td>
		</tr>
		<tr>
			<th>판매량</th>
			<td><%=prod.getProd_qtysale()%></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><%=prod.getProd_mileage()%></td>
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
				location.href="<%=request.getContextPath()%>/prod/prodUpdate.do?what=<%=prod.getProd_id()%>";
			}
		});
	</script>
</body>
</html>











