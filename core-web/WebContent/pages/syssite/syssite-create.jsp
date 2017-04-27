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

<jsp:include page="../common-f-inc.jsp"></jsp:include>

<style type="text/css">


</style>


<script type="text/javascript">

function save() {
	alert(123);
}
function clear() {
	
}

</script>


</head>

<body>

<q:toolBar 
	id="CORE_PROG001D0001C_toolbar" 
	refreshEnable="Y"
	refreshJsMethod="window.location=parent.getProgUrl('CORE_PROG001D0001A');" 
	createNewEnable="N"
	createNewJsMethod=""
	saveEnabel="Y" 
	saveJsMethod="save();" 	
	cancelEnable="Y" 
	cancelJsMethod="parent.closeTab('CORE_PROG001D0001A');" >
</q:toolBar>
<jsp:include page="../common-f-head.jsp"></jsp:include>

<div class="row">
	<div class="col-xs-6 col-md-6 col-lg-6">
		<q:textbox name="sysId" value="" id="sysId" label="Id" requiredFlag="Y" maxlength="10" placeholder="Enter Id (only normal character)"></q:textbox>
	</div>
</div>
<div class="row">
	<div class="col-xs-6 col-md-6 col-lg-6">
		<q:textbox name="name" value="" id="name" label="Name" requiredFlag="Y" maxlength="100" placeholder="Enter name"></q:textbox>
	</div>
</div>
<div class="row">
	<div class="col-xs-6 col-md-6 col-lg-6">
		<q:textbox name="host" value="" id="host" label="Host" requiredFlag="Y" maxlength="200" placeholder="Enter host e.g: 127.0.0.1:8080"></q:textbox>
	</div>
</div>
<div class="row">
	<div class="col-xs-6 col-md-6 col-lg-6">
		<q:textbox name="contextPath" value="" id="contextPath" label="Context path" requiredFlag="Y" maxlength="100" placeholder="Enter host e.g: demo-web"></q:textbox>
	</div>
</div>	
<div class="row">
	<div class="col-xs-6 col-md-6 col-lg-6">
		<q:select dataSource="iconDataMap" name="icon" id="icon" value="" label="Icon" requiredFlag="Y"></q:select>
	</div>
</div>  
<div class="row">
	<div class="col-xs-6 col-md-6 col-lg-6">
	<br>
	&nbsp;
		<label class="custom-control custom-checkbox">
			<input type="checkbox" class="custom-control-input" id="local" name="local">
		    <span class="custom-control-indicator"></span>
		    <span class="custom-control-description">Local</span>
		</label>
				
	</div>
</div>


<br>

<div class="row">
	<div class="col-xs-6 col-md-6 col-lg-6">
		<button type="button" class="btn btn-primary" id="btnSave" onclick="save();">Save</button>
		<button type="button" class="btn btn-primary" id="btnClear" onclick="clear();">Clear</button>		
	</div>
</div>
	
</body>
</html>
