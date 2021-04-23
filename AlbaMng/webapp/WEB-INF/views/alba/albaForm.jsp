<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h4>등록 및 수정 양식</h4>
<form method="post" id="albaForm" enctype="multipart/form-data" class="form-inline">
<input type="hidden" name="alId" value="${alba.alId }" />
	<table class="table table-bordered">
			<tr>
				<th>이름</th>
				<td>
				<div class="input-group">
				<input type="text" name="alName" required class="form-control" maxlength="20" value="${alba.alName }" />
				<span class="error form-control-plaintext">${errors["alName"] }</span></div></td>
			</tr>
			<tr>
				<th>나이</th>
				<td>
				<div class="input-group">
				<input type="number" name="alAge" required class="form-control" value="${alba.alAge }" />
				<span class="error form-control-plaintext">${errors["alAge"] }</span></div></td>
			</tr>
			<tr>
				<th>사진</th>
				<td>
					<input type="file" name="alImage" id="alImage" class="border-0" />
					<c:if test="${empty alba.alImg }">
						<img style="width: 100px; height: 100px; display: none" class="alImage"/>
					</c:if>
					<c:if test="${not empty alba.alImg }">
						<img src="${pageContext.request.contextPath }/profiles/${alba.alImg }" class="alImage thumbnail" />
					</c:if>
					<span class="error form-control-plaintext">${errors["alImage"] }</span>
				</td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td>
					<div class="input-group">
						<input class="form-control" type="text" required name="alZip" value="${alba.alZip }" 
								maxLength="7" pattern="[0-9]{3}-[0-9]{3}" readonly
								data-msg-required="우편번호 필수" data-msg-pattern="형식확인"/>
						<input type="button" id="zipSearchBtn" class="btn btn-info ml-3" value="우편번호 검색" />
						<span class='error form-control-plaintext'>${errors["alZip"] }</span>
					</div>
				</td>
			</tr>
			<tr>
				<th>주소1</th>
				<td>
					<div class="input-group">
						<input type="text" class="form-control" required name="alAdd1" value="${alba.alZip  }" 
								maxLength="200" readonly  data-msg="주소 필수" />
						<span class='error form-control-plaintext'>${errors["alAdd1"] }</span>
					</div>		
				</td>
			</tr>
			<tr>
				<th>주소2</th>
				<td>
					<div class="input-group">
						<input type="text" class="form-control" required name="alAdd2" value="${alba.alAdd2 }" 
								maxLength="200" readonly  data-msg="주소 필수" />
						<span class='error form-control-plaintext'>${errors["alAdd2"] }</span>
					</div>
				</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><div class="input-group"><input type="text" name="alHp" required class="form-control" maxlength="15" value="${alba.alHp }" />
				<span class="error form-control-plaintext">${errors["alHp"] }</span></div></td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
				<div class="input-group">
				<div class="form-check form-check-inline">
					<input type="radio" name="alGen" id="alGen_m" value="M" class="form-check-input" maxlength="1" ${empty alGen or "M" eq alba.alGen?"checked":"" } />
					<label for="alGen_m"  class="form-check-label">남</label>
				</div>			
				<div class="form-check form-check-inline">
					<input type="radio" name="alGen" id="alGen_f" value="F" class="form-check-input" maxlength="1" ${"F" eq alba.alGen ? "checked":"" } /><label for="alGen_f" class="form-check-label">여</label>
				</div>
				<span class="error">${errors["alGen"] }</span></td>
				</div>
			</tr>
			<tr>
				<th>이메일</th>
				<td><div class="input-group"><input type="text" name="alMail" required  class="form-control" maxlength="100" value="${alba.alMail }" />
				<span class="error form-control-plaintext">${errors["alMail"] }</span></div></td>
			</tr>
			<tr>
				<th>최종학력</th>
				<td><div class="input-group">
					<select name="grCode"  class="form-control">
						<option value="">학력선택</option>
						<c:forEach items="${grades }" var="grade">
							<option value="${grade.grCode }" ${grade.grCode eq alba.grCode ? "selected":"" }>${grade.grName }</option>
						</c:forEach>
					</select>
					<script>
						$("[name='grCode']").val("${alba.grCode }");
					</script>
				<span class="error form-control-plaintext">${errors["grCode"] }</span></div></td>
			</tr>
			<tr>
				<th>자격증</th>
				<td>
				<div class="input-group mb-3">
					<c:forEach items="${alba.licenseList }" var="licAlba" varStatus="vs">
						&nbsp;
						<c:if test="${not empty licAlba.licCode }">
							<span id="${licAlba.licCode }"  class="input-group-text">
								${licAlba.licName } &nbsp;
								<input type="button" class="btn btn-danger delBtn" value="삭제" 
										data-code="${licAlba.licCode }"/>
							</span>
						</c:if>
					</c:forEach>
				</div>	
				<div id="licenseArea">	
					<div class="input-group">
						<select name="licCodes" class="form-control" multiple size="6">
							<c:forEach items="${licenses }" var="license">
								<c:set var="matched" value="${false }"/>
								<c:forEach items="${alba.licenseList }" var="licAlba">
									<c:if test="${licAlba.licCode eq license.licCode }">
										<c:set var="matched" value="${true }"/>
									</c:if>
								</c:forEach>
								<option value="${license.licCode }" class="${matched?'matched':'normal' }">${license.licName }</option>
							</c:forEach>
						</select>
						<span class="error form-control-plaintext">${errors["licCodes"] }</span>
					</div>
				</div>
				</td>
			</tr>
			<tr>
				<th>경력사항</th>
				<td><div class="input-group"><textarea  name="alCareer"  class="form-control" maxlength="200">${alba.alCareer }</textarea>
				<span class="error form-control-plaintext">${errors["alCareer"] }</span></div></td>
			</tr>
			<tr>
				<th>특기사항</th>
				<td><div class="input-group"><textarea  name="alSpec"  class="form-control" maxlength="500">${alba.alSpec }</textarea>
				<span class="error form-control-plaintext">${errors["alSpec"] }</span></div></td>
			</tr>
			<tr>
				<th>비고</th>
				<td><div class="input-group"><textarea  name="alDesc"  class="form-control" maxlength="500">${alba.alDesc }</textarea>
				<span class="error form-control-plaintext">${errors["alDesc"] }</span></div></td>
			</tr>
			<tr>
			<td colspan="2">
				<button type="submit" class="btn btn-primary">등록</button>
				<button type="reset" class="btn btn-warning">취소</button>
				<button type="button" onclick="location.href='<c:url value="/alba/albaList.do"/>';" class="btn btn-info">목록으로</button>
				<button type="button" onclick="history.back();" class="btn btn-secondary">뒤로가기</button>
			</td>
		</tr>
	</table>
</form>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/DataTables/datatables.min.css"> 
<script src="${pageContext.request.contextPath }/js/DataTables/datatables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/alba/albaForm.js?${System.currentTimeMillis() }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/commons/searchZip.js?${System.currentTimeMillis() }"></script>
<script type="text/javascript">
	$("#zipSearchBtn").searchZip({
		url:"${pageContext.request.contextPath }/commons/searchZip.do",
		zipCodeTag : "[name='alZip']",
		add1Tag : "[name='alAdd1']",
		add2Tag : "[name='alAdd2']"
	});
</script>



















