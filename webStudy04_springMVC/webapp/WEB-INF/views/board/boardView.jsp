<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/js/ckeditor/ckeditor.js"></script>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
</head>
<body>
	<table class="table table-bordered">
		<tr>
			<th>게시판종류</th>
			<td>${board.bo_type}</td>
		</tr>
		<tr>
			<th>글번호</th>
			<td>${board.bo_no}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.bo_title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.bo_writer}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${board.bo_date}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.bo_hit}</td>
		</tr>
		<tr>
			<th>추천수</th>
			<td id="rcmdArea">${board.bo_rec}</td>
		</tr>
		<tr>
			<th>신고수</th>
			<td id="rptArea">${board.bo_rep}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:if test="${not empty board.attatchList }">
					<c:forEach items="${board.attatchList }" var="attatch">
						<c:url value="/board/download.do" var="downloadURL">
							<c:param name="what" value="${attatch.att_no }" />
						</c:url>
						<a href="${downloadURL }"><span>${attatch.att_filename }</span></a>
					</c:forEach>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2">
<!-- 			/board/recommend.do 비동기 요청 발생 -->
<!-- 			what 이름의 필수 파리미터 전달 -->
<!-- 			Json 응답(success, recommend, message) -->
				<input id="rcmdBtn" type="button" value="추천하기" class="btn btn-primary"/>
				<input id="rptBtn" type="button" value="신고하기" class="btn btn-danger"/>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.bo_content}</td>
		</tr>
		
		<tr>
			<th>덧글작성</th>
			<td>
			<form id="replyForm">
				<table>
					<tr>
						<th>작성자</th>
						<td><input type="text"></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="text"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea class="form-control" rows="5" cols="100" name="re_content" 
								id="re_content"></textarea>
						</td>
					</tr>
					<tr>
						<th></th>
						<td style="float: right;">
							<input id="repWriteBtn" onclick="repWriteBtn()" type="button" value="작성하기" class="btn btn-primary">
							
						</td>
					</tr>
				</table>
			</form>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:url value="/board/boardList.do" var="listURL" />
				<button class="goBtn btn btn-primary" type="button" 
					data-gopage="${listURL }">목록으로</button>
				<a class="btn btn-info mr-2" href="${cPath }/board/noticeList.do">공지글목록</a>
				<c:url value="/board/boardInsert.do" var="insertURL">
					<c:param name="parent" value="${board.bo_no }" />
				</c:url>	
				<a class="btn btn-secondary mr-2" href="${insertURL }">답글쓰기</a>
				<c:url value="/board/boardUpdate.do" var="updateURL">
					<c:param name="what" value="${board.bo_no }" />
				</c:url>
				<a class="btn btn-success mr-2" href="${updateURL }">수정하기</a>
				<a class="btn btn-warning" href="#"
					data-toggle="modal" data-target="#deleteFormModal"
				>삭제하기</a>
			</td>
		</tr>
	</table>
<!-- Modal -->
<div class="modal fade" id="deleteFormModal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="deleteFormModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="deleteFormModalLabel">게시글 삭제</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
   	  <form action="${cPath }/board/boardDelete.do" method="post">
		  <input type="hidden" name="bo_no" value="${board.bo_no }"/>
	      <div class="modal-body">
	      	<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="basic-addon1">비밀번호 : </span>
			  </div>
			  <input type="text" class="form-control" placeholder="Password"
			  	aria-label="Password" aria-describedby="basic-addon1"
			  	name="bo_pass"
			  >
			</div>	
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-danger">삭제</button>
	      </div>
	</form>
    </div>
  </div>
</div>
				<table>
					<tr>
						<th>내용</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>컨트롤러</th>
					</tr>
					<c:if test="${not empty reply }">
						<c:forEach items="${reply }" var="reply">
							<tr>
								<th>작성자</th>
								<td>${reply.rep_writer }</td>
							</tr>
							<tr>
								<th>작성일</th>
								<td>${reply.rep_date }</td>
							</tr>
							<tr>
								<th>내용</th>
								<td>${reply.rep_content }</td>
							</tr>
							<tr>
								<td>
									<input id="repUpdateBtn" onclick="repUpdateBtn()" type="button" value="수정하기" class="btn btn-primary">
									<input id="repDelBtn" onclick="repDelBtn()" type="button" value="삭제하기" class="btn btn-primary">
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
<script type="text/javascript">
	CKEDITOR.replace("re_content", {
		filebrowserImageUploadUrl : '${cPath}/board/boardImage.do?type=Images'
	})
	
	// reply
	function repWriteBtn(){
		$("#replyForm").ajaxForm({
			url : "${cPath}/reply/${board.bo_no}.do",
			method : post,
			dataType: "json",
			success:function(resp){
				alert("성공");
			}, 
			error:function(xhr, error, msg){
				console.log(xhr);
				console.log(error);
				console.log(msg);
			}
		alert("덧글작성");
		$("#replyForm").submit() ;
	}
	function repUpdateBtn(){
		$.ajax({
			url : "${cPath}/reply/${board.bo_no}/${re}.do",
			method : ,
			data : ,
			dataType: "",
			success:function(resp){
				
			}, 
			error:function(xhr, error, msg){
				console.log(xhr);
				console.log(error);
				console.log(msg);
			}
		});
	}
	function repDelBtn(){
		alert("덧글삭제");
	}
	
	$("#deleteFormModal").on("hidden.bs.modal", function(){
		$(this).find("[name='bo_pass']").val("");
	});
	$(".goBtn").on("click", function(){
		let url = $(this).data("gopage");
		if(url)
			location.href = url;
	});
	$("#rcmdBtn").on("click", function(){
		$.ajax({
			url : "${cPath}/board/recommend.do",
			data : {
				what : ${board.bo_no}
			},
			dataType : "json", // Accept/Content-Type
			success : function(resp) {
				if(resp.success){
					$("#rcmdArea").html(resp.recommend);
				}else{
					alert(resp.message);
				}
			},
			error : function(xhr, error, msg) {
				console.log(xhr);
				console.log(error);
				console.log(msg);
			}
		});
	});
	$("#rptBtn").on("click", function(){
		$.ajax({
			url : "${cPath}/board/report.do",
			data : {
				what:${board.bo_no}
			},
			dataType : "json",
			success : function(resp) {
				if(resp.success){
					$("#rptArea").html(resp.report);
				}else{
					alert(resp.message);
				}
			},
			error : function(xhr, error, msg) {
				console.log(xhr);
				console.log(error);
				console.log(msg);
			}
		});
	});
</script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>















