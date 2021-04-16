<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
		<tr>
			<th>게시글 종류</th>
			<td>${board.bo_type eq 'NOTICE' ? '공지' : '일반'}</td>
		</tr>
		<tr>
			<th>게시글 번호</th>
			<td>${board.bo_no}</td>
		</tr>
		<tr>
			<th>게시글 제목</th>
			<td>${board.bo_title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.bo_writer}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.bo_content}</td>
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
			<td>${board.bo_rec}</td>
		</tr>
		<tr>
			<th>댓글수</th>
			<td>${board.bo_rep}</td>
		</tr>
		<tr>
			<th>비밀글 여부</th>
			<td>${board.bo_seq}</td>
		</tr>
	</table>
</body>
</html>