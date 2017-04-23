<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="q" uri="http://www.qifu.org/controller/tag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE html>
<html>
<head>
<title>qifu</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<script type="text/javascript" src="./jquery/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="./bootstrap-4.0.0-alpha.6/css/bootstrap.css" crossorigin="anonymous">
<script src="./bootstrap-4.0.0-alpha.6/js/bootstrap.js" crossorigin="anonymous"></script>
<script src="./bootbox/bootbox.js" crossorigin="anonymous"></script>

<style type="text/css">


</style>


<script type="text/javascript">


</script>


</head>


<body>

<q:toolBar 
	id="CORE_PROG001D0001Q_toolbar" 
	refreshEnable="Y"
	refreshJsMethod="window.location=parent.getProgUrl('CORE_PROG001D0001Q');" 
	createNewEnable="Y"
	createNewJsMethod="parent.addTab('CORE_PROG001D0001A', null);"
	saveEnabel="N" 
	saveJsMethod="" 	
	cancelEnable="Y" 
	cancelJsMethod="parent.closeTab('CORE_PROG001D0001Q');" >
</q:toolBar>


</body>
  
  
</html>
