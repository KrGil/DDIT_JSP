<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<script type="text/javascript" src="${cPath }/js/ckeditor/ckeditor.js"></script>
<!-- Content Header (Page header) -->
<section class="content-header">
   <div class="container-fluid">
     <div class="row mb-2">
       <div class="col-sm-6">
         <h1>상품 등록 및 수정</h1>
       </div>
       <div class="col-sm-6">
         <ol class="breadcrumb float-sm-right">
           <li class="breadcrumb-item"><a href="${cPath }">Home</a></li>
           <li class="breadcrumb-item"><a href="${cPath }/prod/prodList.do">상품관리</a></li>
         </ol>
       </div>
     </div>
   </div><!-- /.container-fluid -->
 </section>
<section class="content">
<form method="post" enctype="multipart/form-data">
	<input type="hidden" name="prod_id" value="${prod.prod_id }" />	
	<table class="table table-bordered">
		<tr>
			<th>상품명</th>
			<td><input type="text" name="prod_name" required
				class="form-control"
				value="${prod.prod_name }" />
				<span class="error">${errors["prod_name"] }</span>
			</td>
		</tr>
<!-- 		===================== -->
		<tr>
			<th>분류코드</th>
			<td>
				<select name="prod_lgu" class="form-control">
					<option value>상품분류</option>
					<c:forEach items="${lprodList }" var="lprod">
						<c:set var="selected" 
							value='${lprod.lprod_gu eq prod.prod_lgu ? "selected":"" }' />
						<option value="${lprod.lprod_gu }" ${selected }>
							${lprod.lprod_nm }
						</option>
					</c:forEach>
				</select>
				<span class="error">${errors["prod_lgu"] }</span>
			</td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>
			<select name="prod_buyer" class="form-control">
				<option value>거래처선택</option>
				<c:forEach items="${buyerList }" var="buyer">
					<c:set var="selected" 
						value="${buyer.buyer_id eq prod.prod_buyer ? 'selected':'' }" />
					<option class="${buyer.buyer_lgu }" value="${buyer.buyer_id }" ${selected }>
						${buyer.buyer_name }
					</option>
				</c:forEach>
			</select>
			<span class="error">${errors["prod_buyer"] }</span>
			</td>
		</tr>
<!-- 		===================== -->
		<tr>
			<th>구매가</th>
			<td>
				<input type="number" name="prod_cost" required
					class="form-control"
					value="${prod.prod_cost }" />
					<span class="error">${errors["prod_cost"] }</span>
			</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>
				<input type="number" name="prod_price" required
					class="form-control"
					value="${prod.prod_price }" />
				<span class="error">${errors["prod_price"] }</span>
			</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td>
				<input type="number" name="prod_sale" required
					class="form-control"
					value="${prod.prod_sale }" />
					<span class="error">${errors["prod_sale"] }</span>
			</td>
		</tr>
		<tr>
			<th>상품정보</th>
			<td>
				<input type="text" name="prod_outline" required
					class="form-control"
					value="${prod.prod_outline }" />
				<span class="error">${errors["prod_outline"] }</span>
				
				</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>
				<textarea name="prod_detail" id="prod_detail" >${prod.prod_detail }</textarea>
				<span class="error">${errors["prod_detail"] }</span>
			</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td>
				<input type="file" name="prod_image" class="form-control"/>
				<span class="error">${errors["prod_img"] }</span>
			</td>
		</tr>
		<tr>
			<th>재고</th>
			<td>
				<input type="number" name="prod_totalstock" required
					class="form-control"
					value="${prod.prod_totalstock }" />
				<span class="error">${errors["prod_totalstock"] }</span>
			</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>
				<input type="number" name="prod_properstock" required
					class="form-control"
					value="${prod.prod_properstock }" />
				<span class="error">${errors["prod_properstock"] }</span>
			</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>
				<input type="text" name="prod_size"
					class="form-control"
					value="${prod.prod_size }" />
				<span class="error">${errors["prod_size"] }</span>
			</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>
				<input type="text" name="prod_color"
					class="form-control"
					value="${prod.prod_color }" />
				<span class="error">${errors["prod_color"] }</span>
			</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>
				<input type="text" name="prod_delivery"
					class="form-control"
					value="${prod.prod_delivery }" />
				<span class="error">${errors["prod_delivery"] }</span>
			</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>
				<input type="text" name="prod_unit"
					class="form-control"
					value="${prod.prod_unit }" />
				<span class="error">${errors["prod_unit"] }</span>
			</td>
		</tr>
		<tr>
			<th>입고량</th>
			<td>
				<input type="number" name="prod_qtyin"
					class="form-control"
					value="${prod.prod_qtyin }" />
				<span class="error">${errors["prod_qtyin"] }</span>
			</td>
		</tr>
		<tr>
			<th>판매량</th>
			<td><input type="number" name="prod_qtysale"
					class="form-control"
					value="${prod.prod_qtysale }" />
				<span class="error">${errors["prod_qtysale"] }</span></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>
				<input type="number" name="prod_mileage"
					class="form-control"
					value="${prod.prod_mileage }" />
				<span class="error">${errors["prod_mileage"] }</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="전송" class="btn btn-primary"/>
				<input type="reset" value="취소" class="btn btn-warning"/>
				<input type="button" value="목록으로"
					class="btn btn-secondary goBtn"
					data-gopage="${cPath }/prod/prodList.do"
				/>
			</td>
		</tr>
	</table>
</form>	
</section>
	<script type="text/javascript">
		$(".goBtn").on("click", function(){
			let url = $(this).data("gopage");
			if(url)
				location.href = url;
		});
		CKEDITOR.replace("prod_detail");
		let prod_buyerTag =	$("[name='prod_buyer']").val("${prod.prod_buyer }");
		$("[name='prod_lgu']").on("change", function(){
			let selectedLgu = $(this).val();
			if(selectedLgu){
				prod_buyerTag.find("option").hide();
				prod_buyerTag.find("option."+selectedLgu).show();
			}else{
				prod_buyerTag.find("option").show();
			}
			prod_buyerTag.find("option:first").show();
		}).val("${prod.prod_lgu }");
	</script>











