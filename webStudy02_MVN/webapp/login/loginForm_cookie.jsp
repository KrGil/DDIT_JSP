<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.error{
		color : red;
		font-weight : bold;
	}
</style>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
	<%
		String failed_id = (String)session.getAttribute("failedId");
		//flash attribute 방식으로 꺼내자마자 초기화시키기
		session.removeAttribute("failedId");
		String message = (String)request.getAttribute("message");
		if(message==null){
			message = (String)session.getAttribute("message");
			// flash attribute session에서 값 꺼내자마자 비워주는방식
			session.removeAttribute("message");
		}
		if(message!= null && !message.isEmpty()){
			%>
			<div class="error"><%=message %></div>
			<%
		}
	%>
	<%
		// 1, 2 -> LoginCheckServelt_cookie.java
		// 3. cookie 값 들고와서 변수 저장하기
		// 3_1. cookie 중 saveId(name)가 존재하면 value 저장하기
		// 4. 로딩 시 만약 존재한다면 아이디와 체크박스 채우기
		// 4_1 존재하지 않을 시 체크박스 풀고 아이디 공란.
		Cookie[] cookies = request.getCookies();
		String savedId = "";
		boolean checked = false;
		if(cookies!=null){
			for(Cookie tmp : cookies){
				String value = URLDecoder.decode(tmp.getValue(), "UTF-8");
				out.println(
					String.format("%s : %s\n", tmp.getName(), value)
				);
				if(tmp.getName().contains("saveId")){
					savedId = tmp.getValue(); 
					checked = true;
				}
			}
			
		}
	%>
	<form action="<%=request.getContextPath() %>/login/loginCheck.do" method="post">
		<input type="text" name="mem_id" placeholder="아이디" value = "<%=Objects.toString(failed_id, "")%>"/>
		<input type="text" name="mem_pass" placeholder="비밀번호"/>
		<input type="checkbox" name ="saveId" value = "saveId">아이디기억하기
		<!-- 1. 1주일간 상태 유지. 2. 체크가 되어있어야함. 3.체크하지 않는 상태 기억x 기억되고있어도 지우라. -->
		<input type="submit" value="로그인"/>
	</form>
	<script type="text/javascript">
		$(function(){
			var checked = <%= checked%>
			if(checked){
				console.log("여기 왜 실행이 안되냐");
				$("input[name='mem_id']").val(<%=savedId%>);
				$("input[name='saveId']").attr("checked", true);
			}
		})
	</script>
</body>
</html>