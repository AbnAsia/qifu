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

$( document ).ready(function() {
	
	
});

function getQueryGridFormatter(value) {
	var str = '';
	str += '<img alt="edit" title="Edit" src="./images/edit.png" onclick="editPage(\'' + value + '\');"/>';
	str += '&nbsp;&nbsp;';		
	str += '<img alt="expression" title="Expression" src="./images/alert.png" onclick="editExpressionPage(\'' + value + '\');"/>';	
	str += '&nbsp;&nbsp;';		
	str += '<img alt="delete" title="Delete" src="./images/delete.png" onclick="deleteRecord(\'' + value + '\');"/>';
	return str;
}
function getQueryGridHeader() {
	return [
		{ name: "#", 			field: "oid", 	formatter: getQueryGridFormatter },
		{ name: "System", 		field: "system"			},
		{ name: "Bean", 		field: "beanId"			},
		{ name: "Method",		field: "method"			},
		{ name: "Enable",		field: "enableFlag"		},
		{ name: "Description",	field: "description"	}
	];
}

function queryClear() {
	$("#systemOid").val( _qifu_please_select_id );
	$("#beanId").val('');
	
	clearQueryGridTable();
	
}  

function editPage(oid) {
	parent.addTab('CORE_PROG003D0003E', parent.getProgUrlForOid('CORE_PROG003D0003E', oid) );
}

function editExpressionPage(oid) {
	parent.addTab('CORE_PROG003D0003S01Q', parent.getProgUrlForOid('CORE_PROG003D0003S01Q', oid) );
}

function deleteRecord(oid) {
	parent.bootbox.confirm(
			"Delete?", 
			function(result) { 
				if (!result) {
					return;
				}
				xhrSendParameter(
						'./core.sysBeanSupportDeleteJson.do', 
						{ 'oid' : oid }, 
						function(data) {
							if ( _qifu_success_flag != data.success ) {
								parent.toastrWarning( data.message );
							}
							if ( _qifu_success_flag == data.success ) {
								parent.toastrInfo( data.message );
							}
							queryGrid();
						}, 
						function() {
							
						},
						_qifu_defaultSelfPleaseWaitShow
				);
			}
	);	
}

</script>

</head>

<body>

<q:toolBar 
	id="CORE_PROG003D0003Q_toolbar" 
	refreshEnable="Y"
	refreshJsMethod="window.location=parent.getProgUrl('CORE_PROG003D0003Q');" 
	createNewEnable="Y"
	createNewJsMethod="parent.addTab('CORE_PROG003D0003A', null);"
	saveEnabel="N" 
	saveJsMethod="" 	
	cancelEnable="Y" 
	cancelJsMethod="parent.closeTab('CORE_PROG003D0003Q');" >
</q:toolBar>
<jsp:include page="../common-f-head.jsp"></jsp:include>

      <div class="row">     
        <div class="col-xs-6 col-md-6 col-lg-6">
        	<q:select dataSource="sysMap" name="systemOid" id="systemOid" value="" label="System"></q:select>
        </div>
        <div class="col-xs-6 col-md-6 col-lg-6">
        	<q:textbox name="beanId" value="" id="beanId" label="Bean Id" placeholder="Enter Service/Bean Id" maxlength="255"></q:textbox>
        </div>
      </div>
      
<br>
      
<button type="button" class="btn btn-primary" id="btnQuery" onclick="queryGrid();">Query</button>
<button type="button" class="btn btn-primary" id="btnClear" onclick="queryClear();">Clear</button>

<br>
<br>

<q:grid gridFieldStructure="getQueryGridHeader()" 
	xhrParameter="
	{
		'parameter[systemOid]'	: $('#systemOid').val(),
		'parameter[beanId]'		: $('#beanId').val(),
		'select'				: getQueryGridSelect(),
		'showRow'				: getQueryGridShowRow()	
	}
	"
	xhrUrl="./core.sysBeanSupportQueryGridJson.do" 
	id="CORE_PROG003D0003Q_grid"
	queryFunction="queryGrid()"
	clearFunction="clearQueryGridTable()">
</q:grid>

</body>
</html>