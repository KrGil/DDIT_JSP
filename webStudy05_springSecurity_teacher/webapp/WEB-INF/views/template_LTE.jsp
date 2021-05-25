<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title><tiles:getAsString name="title" /></title>
  
  <tiles:insertAttribute name="preScript" />

</head>
<body class="hold-transition sidebar-mini layout-fixed">
<!-- Site wrapper -->
<div class="wrapper">
  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
 	 <tiles:insertAttribute name="headerMenu" />
  </nav>
  <!-- /.navbar -->
  <!-- Main Sidebar Container -->
  <aside class="main-sidebar main-sidebar-custom sidebar-dark-primary elevation-4">
 	<tiles:insertAttribute name="leftMenu" />
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
 	<tiles:insertAttribute name="content" />
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
	<tiles:insertAttribute name="footer" />
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->
	<tiles:insertAttribute name="postScript" />
</body>
</html>
