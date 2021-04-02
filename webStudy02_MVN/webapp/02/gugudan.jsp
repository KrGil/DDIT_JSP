<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type ="text/css">
	th, td{
		border : 1px solid balck;
	}
	table {
		border-collapse: collapse;
	}
	.red{
		background-color : red;	
	}
	.green{
		background-color : green;
	}
</style>
</head>
<body>
<%!
	private String gugudanText(int dan, int mul, String clz){
		return String.format("<td class = '%s'>%d * %d = %d</td>"
				,clz, dan, mul, (dan*mul));
	}
%>
<!-- 2단부터 9단까지의 구구단을 table 태그를 이용하여 출력 -->
<!-- 하나의 row에 하나의 답이 출력되도록. -->
<%
	String minDanStr = request.getParameter("minDan");
	String maxDanStr = request.getParameter("maxDan");
	int minDan = 2;
	int maxDan = 9;
	if(minDanStr != null && minDanStr.matches("[2-9]")){ // serverSide 검증 무조건!!! 1. clientSide 검증 2. serverSide 검증!
		minDan = Integer.parseInt(minDanStr);
	}
	if(maxDanStr != null && maxDanStr.matches("[2-9]")){ // serverSide 검증 무조건!!! 1. clientSide 검증 2. serverSide 검증!
		maxDan = Integer.parseInt(maxDanStr);
	}
	// 검증이 필요한다. 1. null인지 2. int로 변환시킬수 있는 값인지.
%>
<form>
	<input type = "number" placeholder = "최소단" name = "minDan" min="2" max = "9" value = "<%=minDan %>">
	<select name ="maxDan" required>
		<option value>최대단</option>
		<%
			String OPTPTRN = "<option %2$s value='%1$d'>%1$d단</option>";
			StringBuffer options = new StringBuffer();
			for(int dan = 2; dan <=9; dan++){
				options.append(
					String.format(OPTPTRN, dan, dan==maxDan ? "selected" : "")
				);
			}
			out.println(options);
		%>
	</select>
	<input type = "submit" value = "전송" />
</form>
<table border ="1" >
	<%
		int rowcnt = 1;
		for(int dan = minDan; dan <= maxDan; dan++){
			String clz = "normal";
			if(rowcnt++ == 3){
				clz = "red";
			}
			out.println(String.format("<tr class='%s'>", clz));
			for(int mul = 2; mul <=9; mul++){
				if(mul == 4) clz = "green";
				else clz = "normal";
				out.println(
						gugudanText(dan, mul, clz)
				);
			}
			out.println("</tr>");
		}
	%>
</table>

</body>
</html>