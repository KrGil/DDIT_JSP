<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${cPath }/theme/AdminLTE-3.1.0/plugins/fontawesome-free/css/all.min.css">
  <!-- overlayScrollbars -->
  <link rel="stylesheet" href="${cPath }/theme/AdminLTE-3.1.0/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${cPath }/theme/AdminLTE-3.1.0/dist/css/adminlte.min.css">
	<style type="text/css">
	.error {
		color: red;
	}
	</style>    
<script type="text/javascript" src="${cPath }/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${cPath }/js/myjQueryPlugin.js"></script>
<script type="text/javascript" src="${cPath }/js/jquery.form.min.js"></script>
<script type="text/javascript">
	$.getContextPath=function(){
		return "${cPath}";
	}
</script>
