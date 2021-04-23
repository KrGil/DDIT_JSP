<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<h4>${alba.alName }의 정보</h4>
<table class="table table-bordered">
	<tr>
		<th>아이디</th>
		<td>${alba.alId }</td>
	</tr>
	<tr>
		<th>이름</th>
		<td>${alba.alName }</td>
	</tr>
	<tr>
		<th>나이</th>
		<td>${alba.alAge }</td>
	</tr>
	<tr>
		<th>사진</th>
		<td>
			<c:if test="${not empty alba.alImg }">
				<img style="width: 100px; height: 100px;" src="${pageContext.request.contextPath }/profiles/${alba.alImg}" />
			</c:if>
			<c:if test="${empty alba.alImg }">
				<img style="width: 100px; height: 100px;" src="${pageContext.request.contextPath }/images/noImage.png" />
			</c:if>
		</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td>${alba.alZip }</td>
	</tr>
	<tr>
		<th>주소</th>
		<td>${alba.alAdd1 } ${alba.alAdd2 }</td>
	</tr>
	<tr>
		<th>휴대폰</th>
		<td>${alba.alHp }</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>${alba.alMail }</td>
	</tr>
	<tr>
		<th>성별</th>
		<td>${"M" eq alba.alGen ? "남":"여" }</td>
	</tr>
	<tr>
		<th>최종학력</th>
		<td>${alba.grName }</td>
	</tr>
	<tr>
		<th>자격증</th>
		<td class="form-inline">
			<span>자격증명을 클릭하면 사본을 확인할 수 있습니다.</span>
			<div>
			<c:forEach items="${alba.licenseList }" var="licAlba" varStatus="vs">
				<input type="button" class="btn btn-success mr-3 viewImage" value="${licAlba.licName }" 
					data-alid="${licAlba.alId }" 
					data-code="${licAlba.licCode }" 
					data-name="${licAlba.licName }"
					data-date="${licAlba.licDate }"
				/>
			</c:forEach>
			</div>
		</td>
	</tr>
	
	<tr>
		<th>특기사항</th>
		<td>${alba.alSpec }</td>
	</tr>
	<tr>
		<th>자기소개</th>
		<td>${alba.alDesc }</td>
	</tr>
	<tr>
		<th>경력사항</th>
		<td>${alba.alCareer }</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" value="수정" class="btn btn-primary" id="updateBtn" />
			<input type="button" value="삭제"  class="btn btn-danger" id="deleteBtn" />
			<button type="button" onclick="location.href='<c:url value="/alba/albaList.do"/>';"  class="btn btn-info">목록으로</button>	
			<button type="button" onclick="history.back();"  class="btn btn-secondary">뒤로가기</button>	
		</td>
	</tr>
</table>
<div class="modal fade" id="imageViewModal" tabindex="-1" aria-labelledby="imageViewModalTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="imageViewModalTitle">자격증 사본</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<c:url value="/alba/albaUpdate.do" var="updateURL"/>
<c:url value="/alba/albaDelete.do" var="deleteURL"/>
<form method="get" id="commonForm" data-update="${updateURL }" data-delete="${deleteURL }">
	<input type="hidden" name="who" value="${alba.alId }" />
</form>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/alba/albaView.js?${System.currentTimeMillis() }"></script>











