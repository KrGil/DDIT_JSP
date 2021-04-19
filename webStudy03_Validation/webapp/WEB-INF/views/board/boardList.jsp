<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page = "/includee/preScript.jsp" />
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
	<c:remove var="message" scope="session"/>
</c:if>

</head>
<body>
<h4>게시글 목록 조회</h4>

<table class = "tagble table-bordered">
	<thead>
		<tr>
			<th>게시글종류</th>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>추천수</th>
		</tr>
	</thead>
	<tbody id="listBody">
	<c:choose>
		<c:when test="${not empty pagingVO.dataList }">
			<c:forEach  items="${pagingVO.dataList }" var="board">
				<tr>
					<td>${board.bo_type eq 'NOTICE'? '공지':'일반' }</td>
					<td>${board.bo_no} </td>
					<td>
						<c:url value="/board/boardView.do" var="viewURL">
							<c:param name="what" value="${board.bo_no }"></c:param>
						</c:url>
						<c:choose>
							<c:when test="${board.bo_sec eq 'Y' }">
								<a class="secret" href="${viewURL }" >
									${board.bo_title}
								</a>
							</c:when>
							<c:otherwise>
								<a class = 'nonsecret' href="${viewURL }" title = ${board.bo_title} data-toggle="popover" >
									${board.bo_title}
								</a>
							</c:otherwise>
						</c:choose>
					</td>
					<td>${board.bo_writer}</td>
					<td>${board.bo_date}</td>
					<td>${board.bo_hit}</td>
					<td>
						${board.bo_rec}
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="5">
					등록된 데이터 없음.
				</td>
			</tr>
		</c:otherwise>
	</c:choose>	
	
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				<form id = "searchForm">
					<input type = "hidden" name = "searchType" value="${pagingVO.searchMap.searchType}"/>
					<input type = "hidden" name = "searchWord" value="${pagingVO.searchMap.searchWord}"/>
					<input type = "hidden" name = "minDate"  value="${pagingVO.searchMap.minDate }" />
					<input type = "hidden" name = "maxDate"  value="${pagingVO.searchMap.maxDate}" />
					<input type = "hidden" name = "page" />
					
				</form>
				<div id="searchUI" class="form-inline d-flex justify-content-center">
					<select name="searchType" class="form-control mr-2">
						<option >전체</option>
						<option value="title">제목</option>
						<option value="writer">작성자</option>
						<option value="content">내용</option>
						<option value="type">게시판종류</option>
					</select>
					<input class="form-control mr-2" type="text" name="searchWord" value="${pagingVO.searchMap.searchWord }"/>
					<input class="form-control mr-2" type="date" name="minDate" value="${pagingVO.searchMap.minDate }" />
					<input class="form-control mr-2" type="date" name="maxDate" value="${pagingVO.searchMap.maxDate }"/>
					<input class="btn btn-primary" id="searchBtn" type="button" value="검색" />
					<button	class="btn btn-light" type="button" value="새글작성" >
						<a href="${cPath }/board/boardInsert.do">새글작성</a>
					</button>
				</div>
				<div id="pagingArea" class="d-flex justify-content-center">
					${pagingVO.pagingHTMLBS }
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<script type = "text/javascript">
	let searchForm = $("#searchForm");
	let searchUI = $("#searchUI");
	searchUI.find("[name='searchType']").val("${pagingVO.searchMap.searchType }");
	$("#searchBtn").on("click", function(){
		let inputs = searchUI.find(":input[name]");
		$(inputs).each(function(idx, input){
			let name = $(this).attr("name");
			let sameInput = searchForm.find("[name='"+name+"']")
			$(sameInput).val($(this).val());
		});
		searchForm.submit();
	});
	
	$("#pagingArea").on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		if(page){
			searchForm.find("[name='page']").val(page);
			searchForm.submit();
		}
		return false;
	});
	var titleValue = "a";
	$(function () {
		$("#listBody a.nonsecret").hover(function(){
			$(this).popover({
				html:true, 
				content:function(){
//		 			초기화 후 토글
					let url = this.href;
					let retValue = null;
					$.ajax({
						url : url,
						dataType: "text",
						success:function(resp){
							console.log($(this));
							retValue = resp;
						},
						// 비동기 요청은 순서가 없어서 
						// 동기요청으로 바꾸겠다. 이 밑으로 응답 데이터가 나오기 전에 보내지 않겠다. 
						async : false,
						cache : true,
						error:function(xhr, error, msg){
							console.log(xhr);
							console.log(error);
							console.log(msg);
						}
					});
					console.log(2);
					return retValue;
				}
			}).popover('toggle');	
		});
	})
</script>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>