<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section class="content pt-3">    
<form action="${cPath }/board/authenticate.do" method="post">
	<input type="hidden" name="bo_no" value="${search.bo_no }"/>
	<input type="text" name="bo_pass" />
	<input type="submit" value="전송" />
</form>
</section>