<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type= "text/javascript" src = "https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type= "text/javascript" src = "<%=request.getContextPath() %>/js/myjQueryPlugIn.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.min.js"></script>
<script type="text/javascript">
	$.getContextPath=function(){
		return "<%=request.getContextPath()%>";
	}
</script>