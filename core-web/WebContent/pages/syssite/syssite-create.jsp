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

function saveSuccess(data) {
	if ( _qifu_success_flag != data.success ) {
		parent.toastrWarning( data.message );
		return;
	}
	parent.toastrInfo( data.message );
	clearSave();
}

function clearSave() {
	$("#sysId").val( '' );
	$("#name").val( '' );
	$("#host").val( '' );
	$("#contextPath").val( '' );
	$("#icon").val( '${firstIconKey}' );
	$("#local").prop('checked', false);
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
	saveJsMethod="btnSave();" 	
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
		<q:button id="btnSave" label="Save"
			xhrUrl="./core.sysSiteSaveJson.do"
			xhrParameter="
			{
				'sysId'			:	$('#sysId').val(),
				'name'			:	$('#name').val(),
				'host'			:	$('#host').val(),
				'contextPath'	:	$('#contextPath').val(),
				'icon'			:	$('#icon').val(),		
				'isLocal'		:	( $('#local').is(':checked') ? 'Y' : 'N' )
			}
			"
			onclick="btnSave();"
			loadFunction="saveSuccess(data);"
			errorFunction="clearSave();">
		</q:button>
		<q:button id="btnClear" label="Clear" onclick="clearSave();"></q:button>
	</div>
</div>
	
</body>
</html>
