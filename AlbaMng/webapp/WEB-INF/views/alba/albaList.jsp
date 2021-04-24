<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-striped table-bordered">
	<thead class="table-header thead-dark">
		<tr>
			<th>이름</th>
			<th>나이</th>
			<th>학력</th>
			<th>거주지</th>
			<th>휴대폰</th>
			<th>메일</th>
		</tr>
	</thead>
	<tbody id="listBody" data-view="${pageContext.request.contextPath }/alba/albaView.do">
		<c:set var="dataList" value="${pagingVO.dataList }" />
		<c:if test="${not empty dataList }">
			<c:forEach items="${dataList }" var="alba">
				<c:url value="/alba/albaView.do" var="albaViewURL">
					<c:param name="who" value="${alba.alId }" />
				</c:url>
				<tr>
					<td><a href="${albaViewURL }">${alba.alName }(${alba.alGen })</a></td>
					<td>${alba.alAge }</td>
					<td>${alba.grName }</td>
					<td>${alba.alAdd1 }</td>
					<td>${alba.alHp }</td>
					<td>${alba.alMail }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty dataList }">
			<tr>
				<td colspan="6">검색된 데이터가 없음.</td>
			</tr>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<form id="searchForm" action="${pageContext.request.contextPath }/alba/albaList.do" class="form-inline mb-3 justify-content-center">
					<input type="hidden" name="page" />
					<input type="hidden" name="searchType" />
					<input type="hidden" name="searchWord" />
					<input type="hidden" name="grCode" />
					<input type="hidden" name="licCodes" />
					<input type="hidden" name="alGen" />
				</form>
				<div id="inputUI" class="row mb-3 justify-content-center">
					<div class="col-auto">
						<select name="grCode" class="form-control mr-2" onchange="$(this.form).submit();">
							<option value>전체</option>
						</select>
					</div>
					<div class="col-auto">	
						<select name="licCodes" class="form-control mr-2" onchange="$(this.form).submit();">
							<option value>전체</option>
						</select>
					</div>
					<div class="col-auto">	
						<div class="form-check form-check-inline">
							<input type="radio" name="alGen" id="alGen_a" value="" class="form-check-input" checked onchange="$(this.form).submit();"/>
							<label for="alGen_a"  class="form-check-label">전체</label>
						</div>			
						<div class="form-check form-check-inline">
							<input type="radio" name="alGen" id="alGen_m" value="M" class="form-check-input" onchange="$(this.form).submit();"/>
							<label for="alGen_m"  class="form-check-label">남</label>
						</div>			
						<div class="form-check form-check-inline">
							<input type="radio" name="alGen" id="alGen_f" value="F" class="form-check-input" onchange="$(this.form).submit();" />
							<label for="alGen_f" class="form-check-label">여</label>
						</div>
					</div>
					<div class="col-auto">
						<select name="searchType" class="form-control mr-2">
							<option value="name">이름</option>
							<option value="address">거주지</option>
							<option value="career">경력사항</option>
						</select>
					</div>
					<div class="col-auto">	
						<input type="text" name="searchWord"  class="form-control mr-2"
							value="${param.searchWord }"
						/>
					</div>	
					<div class="col-auto">
						<input type="button" value="검색" id="searchBtn" class="btn btn-primary mr-2"/>
						<a class="btn btn-primary" href="<c:url value='/alba/albaInsert.do'/>">신규등록</a>
					</div>
				</div>	
				<div class="d-flex justify-content-center" aria-label="Pagination" id="pagingArea">
					${pagingVO.pagingHTML }
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/alba/albaList.js?${System.currentTimeMillis() }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/alba/codeSelect.js?${System.currentTimeMillis() }"></script>
