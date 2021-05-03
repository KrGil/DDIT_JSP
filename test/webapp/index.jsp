<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="${cPath }/js/bootstrap-4.6.0-dist/css/bootstrap.min.css">

<script type= "text/javascript" src = "${cPath }/js/jquery-3.6.0.min.js"></script>
<script type= "text/javascript" src = "${cPath }/js/myjQueryPlugIn.js"></script>
<script type="text/javascript" src="${cPath }/js/jquery.form.min.js"></script>
<script type="text/javascript">
	$.getContextPath=function(){
		return "${cPath }";
	}
</script>
<c:if test="${not empty cookie}">
	<c:set value="checekd" var="checked"/>
</c:if>
<body>
	<!-- ajax 사용 -->
	<div>
		Sign in to start your session
	</div>
	<div>
		<table border='1'>
		<tr>
			<th><label class="" for="inputId">ID</label></th>
			<td><input type="text" class="" id="inputId" name="employee_id" value="${cookie.user_check.value}" placeholder="input your ID"></td>
		</tr>
		<tr>
			<th><label class="" for="inputPass">Password</label></th>
			<td><input type="text" class="" id="inputPw" name="employee_pwd" placeholder="input your Password"></td>
		</tr>
		<tr>
			<td colspan=2>
				<span id="spanLoginCheck"></span>
			</td>
		</tr>
		<tr>
			<td colspan=2>
				<span id="spanLoginPwdCheck"></span>
			</td>
		</tr>
		</table>
		<div>
			<input type="checkbox" id="remember_me" name="remember_userId" ${checked}/>Remember Me
			<button id="loginBtn" type="button" >Login</button>
		</div>
		<div>
			<a href="${cPath }">Forget Id/Password</a>
		</div>
	</div>
</body>
<script type = "text/javascript" src="${cPath }/js/bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
	$(document).on("keyup", "input[name=employee_id]", function() {
		leng = $(this).val().length
// 		$(this).val( $(this).val().replace(/[^a-z0-9]/gi,""));
		if(leng < 8 || leng > 13){
			console.log($("#spanLoginCheck").val());
			$("#spanLoginCheck").text("아이디는 8-13자리 사이로 작성해야합니다.");
		}else{
			$("#spanLoginCheck").text("");
		}
	});
	$(document).on("keyup", "input[name=employee_pwd]", function() {
		leng = $(this).val().length
// 		$(this).val( $(this).val().replace(/[^a-z0-9]/gi,""));
		if(leng < 8 || leng > 13){
			console.log($("#spanLoginPwdCheck").val());
			$("#spanLoginPwdCheck").text("비밀번호는 영문자, 특수분자 숫자 조합 8-13자리 사이로 작성해야합니다.");
		}else{
			$("#spanLoginPwdCheck").text("");
		}
	});
	
	$("#loginBtn").click(function(){
		var id = $("#inputId").val();
		var pw = $("#inputPw").val();
		var remember_me = $("#remember_me").is("checked");
		$.ajax({
			url : "${cPath}/login/loginCheck.do",
			method : "post",
			data : {
				employee_id : id,
				employee_pwd : pw,
				remember_userId : remember_me
			},
			dataType: "json",
			success:function(resp){
				console.log(resp);
				if('OK'.equals(resp)){
					alert('성공');
// 					location.href("${cPath}/memberList.do");
				}else{
					$("#spanLoginCheck").text('로그인정보를 제대로 입력해 주세요.');
				}
			}, 
			error:function(xhr, error, msg){
				console.log(xhr);
				console.log(error);
				console.log(msg);
			}
		});
	});
	
</script>
</html>