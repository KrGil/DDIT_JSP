<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/parameterDesc.jsp</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<!-- enctype="application/x-www-form-urlencoded" 바디가 있을때만 쓰인다. -->
<form enctype="application/x-www-form-urlencoded" id = "paramForm"
		 action ="<%=request.getContextPath() %>/06/parameters"method = "post">
	<pre>
		<input type = "hidden" name = "param1" value = "hiddenParam"/>
		<input type = "text" name = "param1" placeholder = "param1"/>
		<input type = "text" name = "param1" placeholder= "param1"/>
		<input type = "text" name = "param2" placeholder = "param2"/>
		<select name = "param2">
			<option>option1</option>
			<option>option2</option>
			<option>option3</option>
		</select>
		<select name = "param3" multiple>
			<option>option1</option>
			<option>option2</option>
			
			<option>option3</option>
		</select>
		<input type = "checkbox" name = "param4" value = "1">CHECK1
		<input type = "checkbox" name = "param4" value = "2">CHECK2
		<input type = "checkbox" name = "param4" value = "3">CHECK3
		<input type = "radio" name = 'param5' value = "RADIO01">RADIO1
		<input type = "radio" name = 'param5' value = "RADIO02">RADIO2
		<input type = "button" value = "버튼"/>
		<input type = "submit" value = "전송"/>
		<input type = "reset" value = "취소"/>
	</pre>
</form>
<script>
	$.fn.serializeJson = function(){
		let tagName = this.prop("tagName").toLowerCase();
		if (this.length != 1 || 	tagName != "form") 
					throw "form 에 대해서만 호출 가능한 함수";
		let array = this.serializeArray();
// 		console.log(array);
		let obj = {}
// 		obj.param1 = ["value1", "value2"]
		$(array).each(function(){
			console.log(this);
			let name = this.name;
			let value = this.value;
			if(obj[name] && Array.isArray(obj[name])){
				value = obj[name].concat(value);
			}else if(obj[name] || (typeof obj[name] == "string" && 	obj[name].trim()=="")){
				let tmpArray = [obj[name]];
				tmpArray.push(value);
				value = tmpArray;
			}// if~else end
				
			obj[name] = value;
			console.log(obj);
		}); // each end
		return obj;
		
	}

	
	$("#paramForm").on("submit", function(e){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
// 		let enctype = this.enctype;
// 		let data = $(this).serialize(); json이 아닐때
		let data = $(this).serializeArray();
		// json 을 만들때 한 세트
		let enctype = "application/json;charset=utf-8";
		let data = $(this).serializeJson();
		data = JSON.stringify(data);
		
		console.log(data);12
		
		$.ajax({
			url : url,
			method : method,
			contentType : enctype,
			data : data
		})
		return false;
	})
</script>
</body>
</html>






