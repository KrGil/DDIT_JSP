<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	iframe{
	    width: 100%;
	    height: 100%;
	}
</style>
</head>
<body>
<h4>Text file view</h4>
<select id = "videoFile">
	<%	
		ArrayList<String> options = (ArrayList<String>)request.getAttribute("options_arr");
		for(int i =0; i < options.size(); i++){
			System.out.println(options.get(i));
			out.println(options.get(i));
		}
	%>
	
</select>
	<iframe id = "viewer" src = ""></iframe>
	<script type= "text/javascript" src = "https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		var select = document.querySelector("#videoFile");
		select.onchange = function(event){
			var iframe = document.querySelector("#viewer");
			console.log(select.value)
			iframe.setAttribute("src", "Video_servlet.do?videoFile="+select.value);
		}
	</script>
</body>
</html>