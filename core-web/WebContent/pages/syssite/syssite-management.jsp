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

function getQueryGridFormatter(value) {
	var str = '';
	str += '<img alt="edit" title="Edit" src="./images/edit.png" onclick="eee("' + value + '");"/>';
	str += '&nbsp;&nbsp;';
	str += '<img alt="delete" title="Delete" src="./images/delete.png" onclick="ddd("' + value + '");"/>';
	return str;
}
function getQueryGridHeader() {
	return [
		{ name: "#", 	field: "oid", 	formatter: getQueryGridFormatter },
		{ name: "Id", 	field: "sysId"					},
		{ name: "Name", field: "name"					},
		{ name: "Host", field: "host"					},
		{ name: "Context path", field: "contextPath"	},
		{ name: "Local", field: "isLocal"				}
	];
}

function queryClear() {
	$("#id").val('');
	$("#name").val('');
	
	clearQueryGridTable();
	
}  

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
<jsp:include page="../common-f-head.jsp"></jsp:include>



      <div class="row">
        <div class="col-md-6 col-lg-6">
        	<q:textbox name="id" value="" id="id" label="Id" placeholder="Enter Id" maxlength="10"></q:textbox>
        </div>
        <div class="col-md-6 col-lg-6">
        	<q:textbox name="name" value="" id="name" label="Name" placeholder="Enter name" maxlength="100"></q:textbox>
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
		'parameter[sysId]'	: $('#id').val(),
		'parameter[name]'	: $('#name').val(),
		'select'			: getQueryGridSelect(),
		'showRow'			: getQueryGridShowRow()	
	}
	"
	xhrUrl="./core.sysSiteQueryGridJson.do" 
	id="CORE_PROG001D0001Q_grid"
	queryFunction="queryGrid()"
	clearFunction="clearQueryGridTable()">
</q:grid>
	
</body>
  
  
</html>
