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

function sysChange() {
	
	$('#folderProgOid').find('option').remove().end();
	
	var sysOid = $("#sysOid").val();
	xhrSendParameter(
			'./core.getCommonProgramFolderJson.do', 
			{ 'oid' : sysOid }, 
			function(data) {
				if ( _qifu_success_flag != data.success ) {
					
					$('#folderProgOid')
				    .find('option')
				    .remove()
				    .end()
				    .append('<option value="' + _qifu_please_select_id + '">' + _qifu_please_select_name + '</option>')
				    .val( _qifu_please_select_id );
					
					return;
				}
				for (var n in data.value) {
					$('#folderProgOid').append($('<option>', {
					    value: n,
					    text: data.value[n]
					}));
				}
				
			}, 
			function() {
				
			}
	);	
}

</script>

<body>

<q:toolBar 
	id="CORE_PROG001D0003Q_toolbar" 
	refreshEnable="Y"
	refreshJsMethod="window.location=parent.getProgUrl('CORE_PROG001D0003Q');" 
	createNewEnable="N"
	createNewJsMethod=""
	saveEnabel="N" 
	saveJsMethod="" 	
	cancelEnable="Y" 
	cancelJsMethod="parent.closeTab('CORE_PROG001D0003Q');" >
</q:toolBar>
<jsp:include page="../common-f-head.jsp"></jsp:include>

<div class="form-group" id="form-group1">
	<div class="row">
		<div class="col-xs-6 col-md-6 col-lg-6">
			<q:select dataSource="sysMap" name="sysOid" id="sysOid" value="" label="System" requiredFlag="Y" onchange="sysChange();"></q:select>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-6 col-md-6 col-lg-6">
			<q:select dataSource="folderProgMap" name="folderProgOid" id="folderProgOid" value="" label="Program folder" requiredFlag="Y"></q:select>
		</div>
	</div>		
</div>	

</body>
</html>