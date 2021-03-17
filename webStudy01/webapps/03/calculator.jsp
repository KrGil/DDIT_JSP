<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calculator</title>
<script type= "text/javascript" src = "https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#calForm").on("submit", function(e){
			e.preventDefault();
			var action = this.action;
			var method = this.method;
			var data = $(this).serialize(); //queryString이 만들어짐. &으로 나눈 것을 하나의 section이라 한다. 
			console.log(data);
			$.ajax({
				url : action
				, method : method
				, data : data
				, dataType: "json" // Accept : text/plain[html, javascript], application/json[xml] mime결정용 상수
				, success:function(resp){ // unmarchalling을 하여 javascript로 반환 
					$("#resultArea").html(resp.result);
				}, error:function(xhr, error, msg){
					console.log(xhr);
					console.log(error);
					console.log(msg);
				}
			});
			return false;
		});
	});
</script>
</head>
<body>
<!-- 	1. left, right의 피연선자와 operator 이름의 연산자를 포함한 필수 파라미터 입력. -->
<!-- 	2. 연산의 종류, 4칙연산 지원 -->
<!-- 	3. 입력 데이터는 실수형. -->
<!-- 	4. 파리미터 전송 : /03/calculator의 방향으로 전송(전송 데이터는 비노출). -->
<!-- 	5. 연산의 결과 : ex) 3 * 4 = 12 와 같은 형태로 제공 -->
<form id = "calForm" action = "<%=request.getContextPath()%>/03/calculator" method = "post">
	<input type = "number" name = "left" step="any">
	<%
		for(OperatorType tmp : OperatorType.values()){
	%>
		<label><input type="radio" name="operator" value="<%=tmp.name()%>"/><%=tmp.getSign()%></label>
	<%
		}
	%>
	<input type = "number" name = "right" step="any" >
	<input type = "submit" value = "submit" id ="i">
</form>
<div id = "resultArea"></div>
</body>
</html>