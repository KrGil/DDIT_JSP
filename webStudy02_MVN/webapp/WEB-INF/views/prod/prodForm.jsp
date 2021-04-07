<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<%
	String message = (String) request.getAttribute("message");
	if(StringUtils.isNotBlank(message)){
		%>
		<script type="text/javascript">
			alert("<%=message %>");
		</script>
		<%
	}
%>
</head>
<body>
<jsp:useBean id="prod" class="kr.or.ddit.vo.ProdVO" scope="request" />
<jsp:useBean id="errors" class="java.util.LinkedHashMap" scope="request" />
<form method="post">	
	<table>
		
		<tr>
			<th>상품명</th>
			<td><input type="text" name="prod_name" required
				value="<%=prod.getProd_name()%>" />
				<span class="error"><%=errors.get("prod_name")%></span></td>
		</tr>
<!-- 		===================== -->
		<tr>
			<th>분류코드</th>
			<td>
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
				<span class="error"><%=errors.get("prod_lgu")%></span></td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>
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
				<span class="error"><%=errors.get("prod_buyer")%></span></td>
		</tr>
<!-- 		===================== -->
		<tr>
			<th>구매가</th>
			<td><input type="number" name="prod_cost" required
				value="<%=prod.getProd_cost()%>" />
				<span class="error"><%=errors.get("prod_cost")%></span></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><input type="number" name="prod_price" required
				value="<%=prod.getProd_price()%>" />
				<span class="error"><%=errors.get("prod_price")%></span></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><input type="number" name="prod_sale" required
				value="<%=prod.getProd_sale()%>" />
				<span class="error"><%=errors.get("prod_sale")%></span></td>
		</tr>
		<tr>
			<th>상품정보</th>
			<td><input type="text" name="prod_outline" required
				value="<%=prod.getProd_outline()%>" />
				<span class="error"><%=errors.get("prod_outline")%></span></td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><input type="text" name="prod_detail"
				value="<%=prod.getProd_detail()%>" />
				<span class="error"><%=errors.get("prod_detail")%></span></td>
		</tr>
		<tr>
			<th>이미지</th>
			<td><input type="text" name="prod_img" required
				value="<%=prod.getProd_img()%>" />
				<span class="error"><%=errors.get("prod_img")%></span></td>
		</tr>
		<tr>
			<th>재고</th>
			<td><input type="number" name="prod_totalstock" required
				value="<%=prod.getProd_totalstock()%>" />
				<span class="error"><%=errors.get("prod_totalstock")%></span></td>
		</tr>
		<tr>
			<th>입고일</th>
			<td><input type="date" name="prod_insdate"
				value="<%=prod.getProd_insdate()%>" />
				<span class="error"><%=errors.get("prod_insdate")%></span></td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td><input type="number" name="prod_properstock" required
				value="<%=prod.getProd_properstock()%>" />
				<span class="error"><%=errors.get("prod_properstock")%></span></td>
		</tr>
		<tr>
			<th>크기</th>
			<td><input type="text" name="prod_size"
				value="<%=prod.getProd_size()%>" />
				<span class="error"><%=errors.get("prod_size")%></span></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><input type="text" name="prod_color"
				value="<%=prod.getProd_color()%>" />
				<span class="error"><%=errors.get("prod_color")%></span></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><input type="text" name="prod_delivery"
				value="<%=prod.getProd_delivery()%>" />
				<span class="error"><%=errors.get("prod_delivery")%></span></td>
		</tr>
		<tr>
			<th>단위</th>
			<td><input type="text" name="prod_unit"
				value="<%=prod.getProd_unit()%>" />
				<span class="error"><%=errors.get("prod_unit")%></span></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><input type="number" name="prod_qtyin"
				value="<%=prod.getProd_qtyin()%>" />
				<span class="error"><%=errors.get("prod_qtyin")%></span></td>
		</tr>
		<tr>
			<th>판매량</th>
			<td><input type="number" name="prod_qtysale"
				value="<%=prod.getProd_qtysale()%>" />
				<span class="error"><%=errors.get("prod_qtysale")%></span></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><input type="number" name="prod_mileage"
				value="<%=prod.getProd_mileage()%>" />
				<span class="error"><%=errors.get("prod_mileage")%></span></td>
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
		let prod_buyerTag =	$("[name='prod_buyer']");
		$("[name='prod_lgu']").on("change", function(){
			let selectedLgu = $(this).val();
			if(selectedLgu){
				prod_buyerTag.find("option").hide();
				prod_buyerTag.find("option."+selectedLgu).show();
			}else{
				prod_buyerTag.find("option").show();
			}
			prod_buyerTag.find("option:first").show();
		});
	</script>
</body>
</html>



