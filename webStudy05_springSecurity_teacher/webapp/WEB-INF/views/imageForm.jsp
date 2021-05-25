<%@page import="java.io.FilenameFilter"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
// 		$(document).on("ready", function(){
			
// 		});
		$(function(){
			const SRCPTRN = "%A?%N=%V";
			console.log($("form")[0]);
			const action = $("form")[0].action;
// 			console.log($("#image"));
			let select = $("#image").on("change", function(event){
				$("#imageArea").empty();
// 				console.log(this.value);
// 				console.log($(this).val());
// 				this.form.submit();
				// img 태그 생성
				var name = this.name; //$(this).attr("name")
				var values = $(this).val();
				var imgs = [];
				$(values).each(function(idx, value){
					var src = SRCPTRN.replace("%A", action)
									 .replace("%N", name)
									 .replace("%V", value);
					var img = $("<img>").attr("src", src);
					imgs.push(img);
				});
				// imageArea 에 img 태그를 innerHTML 로 삽입
				$("#imageArea").html(imgs);
				$.ajax({
					url:"<%=request.getContextPath() %>/07/cookieGenerate.do"
					, method:"post"
					, contentType:"application/json;charset=UTF-8"
					, data:JSON.stringify(values)
				});
			}); // change handler end
			
			<%
				String imageName = (String)request.getAttribute("imageCookie");
				if(imageName!=null){
			%>
				select.val(JSON.parse( '<%=imageName%>' ));
				select.trigger("change");
			<%
				}
			%>
		}); // ready end
	</script>
</head>
<body>
<h4><%=new Date() %></h4>
<form action='<%=request.getContextPath() %>/01/image.do' method="post">
<input name="_method" value="put" type="hidden">
<%
String[] children = (String[]) request.getAttribute("children");

StringBuffer options = new StringBuffer();

for(String child : children){
	options.append(String.format("<option>%s</option>", child));
}
%>
<select name="image" id="image" multiple>
<%=options %>
</select>
<input type="submit" value="전송" style="background-color: red;"/>
</form>
<div id="imageArea"></div>
<form method="post" enctype="multipart/form-data">
	<input type="file" name="uploadImage" />
	<input type="submit" value="업로드" />
</form>
</body>
</html>















    