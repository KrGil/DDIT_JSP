<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Content Header (Page header) -->
<section class="content-header">
   <div class="container-fluid">
     <div class="row mb-2">
       <div class="col-sm-6">
         <h1>상품 목록 조회</h1>
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
<form id="searchForm" class="form-inline d-flex justify-content-center">
	<select name="prod_lgu" class="form-control mr-3">
		<option value>상품분류</option>
		<c:forEach items="${lprodList }" var="lprod">
			<option value="${lprod.lprod_gu }">
				${lprod.lprod_nm }
			</option>
		</c:forEach>
	</select>
	<select name="prod_buyer" class="form-control mr-3">
		<option value>거래처선택</option>
		<c:forEach items="${buyerList }" var="buyer">
			<option class="${buyer.buyer_lgu }" value="${buyer.buyer_id }">
				${buyer.buyer_name }
			</option>
		</c:forEach>
	</select>
	<input type="text" name="prod_name"  class="form-control mr-2"/>
	<input type="hidden" name="page" />
	<input type="submit" value="검색" class="btn btn-primary mr-2"/>
	<input type="button" value="신규등록" id="newBtn" class="btn btn-success"/>
</form>
<table class="table table-bordered table-hover mt-3">
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
		<td colspan="8">
			<div class="d-flex justify-content-center" id="pagingArea">
			</div>
		</td>
	</tr>
</tfoot>
</table>
</section>
<script type="text/javascript" src="${cPath }/js/prod/prodList.js"></script>













