<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!-- Content Header (Page header) -->
<section class="content-header">
   <div class="container-fluid">
     <div class="row mb-2">
       <div class="col-sm-6">
         <h1>게시글 목록 조회</h1>
       </div>
       <div class="col-sm-6">
         <ol class="breadcrumb float-sm-right">
           <li class="breadcrumb-item"><a href="${cPath }">Home</a></li>
           <li class="breadcrumb-item"><a href="${cPath }/board/boardList.do">게시글목록</a></li>
         </ol>
       </div>
     </div>
   </div><!-- /.container-fluid -->
 </section>
<section class="content">    
	<table class="table table-bordered">
		<tr>
			<th>게시판종류 및 글번호</th>
			<td>${board.bo_type}, ${board.bo_no}</td>
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
			<th>수치 데이터들</th>
			<td>
			조회수 : <span>${board.bo_hit}</span>
			추천수 : <span id="rcmdArea">${board.bo_rec}</span>
			신고수 : <span id="rptArea">${board.bo_rep}</span>
			</td>
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

<!-- 덧글 등록용 form -->
<form method="post" class="form-inline" id="replyInsertForm"
	action="${cPath }/reply/${board.bo_no }">
	<input type="hidden" name="rep_no" />
	<input type="hidden" name="bo_no" value="${board.bo_no }"/>
	<table class="col-md-10">
		<tr>
			<td class="d-flex justify-content-around">
				<input type="text" class="form-control mb-2" name="rep_writer" placeholder="작성자" maxlength="15"/>
				<input type="text" class="form-control mb-2" name="rep_pass" placeholder="비밀번호"/>
			</td>
		</tr>
		<tr>
			<td>
				<div class="input-group">
				<textarea class="form-control mb-2 mr-2" rows="2" placeholder="내용 200자 이내"maxlength="200" name="rep_content"></textarea>
				</div>
			</td>
			<td colspan="3">
				<input type="submit" value="전송" class="btn btn-primary" />
			</td>
		</tr>
	</table>
</form>
<table id="replyTable" class="table table-bordered">
	<thead class="table-dark">
		<tr class="d-flex">	
			<th class="text-center col-6">내용</th>
			<th class="text-center col-2">작성자</th>
			<th class="text-center col-2">작성일</th>
			<th class="text-center col-2">컨트롤버튼</th>
		</tr>
	</thead>
	<tbody id="listBody">
	
	</tbody>
</table>

<div id="pagingArea"></div>

<!-- 덧글 페이징 처리용 form -->
<form id="searchForm" action="${cPath }/reply/${board.bo_no }" method="get">
	<input type="hidden" name="what" value="${board.bo_no }" />
	<input type="hidden" name="page"  />
</form>

<!-- 덧글 수정용 modal form -->
<div class="modal fade" id="replyModal" tabindex="-1" aria-labelledby="replyModalLabel" aria-hidden="true">
 <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="replyModalLabel">댓글 수정</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form data-action="${cPath }/reply/${board.bo_no}" method="post">
      	<input type="hidden" name="_method" value="put">
      	<input type="hidden" name="rep_no" required/>
      	<input type="hidden" name="bo_no"  required value="${board.bo_no }"/>
	      <div class="modal-body">
	      	<table class="table form-inline">
	      		<tr>
	      			<td>
	      				<input type="text" required name="rep_writer" class="form-control" placeholder="작성자" />
	      			</td>
	      			<td>
	      				<input type="text" required name="rep_pass" class="form-control" placeholder="비밀번호"/>
	      			</td>
	      		</tr>
	      		<tr>
	      			<td colspan="2">
						<div class="input-group">
						<textarea class="form-control mb-2 mr-2" rows="2" placeholder="내용 200자 이내"maxlength="200" name="rep_content"></textarea>
						</div>
					</td>
	      		</tr>
	      	</table>
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-primary">수정</button>
	        <button type="reset" class="btn btn-warning" data-bs-dismiss="modal">취소</button>
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	      </div>
      </form>
    </div>
  </div>
</div>

<!-- 덧글 삭제용 form -->
<form id="replyDeleteForm" data-action="${cPath }/reply/${board.bo_no}" method="post">
	<input type="hidden" name="_method" value="delete" />
	<input type="hidden" name="rep_no" required/>
   	<input type="hidden" name="bo_no"  required value="${board.bo_no }"/>
   	<input type="hidden" name="rep_pass"  required/>
</form>	
</section>
<script type="text/javascript" src="${cPath }/js/board/reply.js"></script>
<script type="text/javascript">
$(".goBtn").on("click", function(){
	let url = $(this).data("gopage");
	if(url)
		location.href = url;
});
$("#deleteFormModal").on("hidden.bs.modal", function(){
	$(this).find("[name='bo_pass']").val("");
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














