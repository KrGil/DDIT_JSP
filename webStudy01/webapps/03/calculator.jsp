<%@page import="kr.or.ddit.enumpkg.MimeType"%>
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
		var functionMap = {
			json : function(resp){ // object라서 . 해서 가져오면됨
				resultArea.text( resp.expression );
			},
			xml : function(resp){ //document 타입이라서 $로 <result>찾아서 가져오면됨
				resultArea.text($(resp).find("expression").text());
			},
			html : function(resp){
				resultArea.html(resp)
			},
			plain : function(resp){
				resultArea.text(resp);
			}
		}
		var mimeKind = $("#mimeKind");
		var resultArea = $("#resultArea"); // 여기 놔두면 클릭 시마다 다시 불러오지 않아도 된다.
		var calForm = $("#calForm").on("submit", function(e){
			var dataType = mimeKind.val();
			if(!dataType){ // null, length
				return true;				
			}
			e.preventDefault();
			var action = this.action;
			var method = this.method;
			var data = $(this).serialize(); //queryString이 만들어짐. &으로 나눈 것을 하나의 section이라 한다. 
			var options = { //ajax에 있는걸 여기로 옮기기
				url : action
				, method : method
				, data : data
				, error:function(xhr, error, msg){
					console.log(xhr);
					console.log(error);
					console.log(msg);
				}
			}
			console.log(data);
			options.dataType = dataType; // 
			options.success = functionMap[options.dataType]; 
			$.ajax(options);
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
<select id="mimeKind">
	<option value>dataType 선택</option>
	<%
		for(MimeType tmp : MimeType.values()){
	%>
			<option value="<%=tmp.name().toLowerCase()%>"><%=tmp.name() %></option>	
	<%
		}
	%>
</select>

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