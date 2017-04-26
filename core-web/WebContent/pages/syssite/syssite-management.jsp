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
        <div class="col-lg-4">
    		<label for="id">Id</label>
    		<input class="form-control mb-2 mr-sm-2 mb-sm-0" type="text" value="" id="id" name="id" placeholder="Enter Id">
        </div>
        <div class="col-lg-4">
    		<label for="name">Name</label>
    		<input class="form-control mb-2 mr-sm-2 mb-sm-0" type="text" value="" id="name" name="name" placeholder="Enter name">
       </div>
      </div>
      
<br>
      
<button type="button" class="btn btn-primary" id="btnQuery" onclick="queryGrid();">Query</button>
<button type="button" class="btn btn-primary" id="btnClear" onclick="queryClear();">Clear</button>

<br>
<br>


<div id="queryGridTable">
</div>
<script>

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


function queryGrid() {
	xhrSendParameter(
			'./core.sysSiteQueryGridJson.do', 
			{
				'parameter[sysId]'	: $("#id").val(),
				'parameter[name]'	: $("#name").val(),
				'select'			: 1,
				'showRow'			: 10
			}, 
			function(data) {
				if ( _qifu_success_flag != data.success) {
					parent.toastrInfo( data.message ); //parent.toastrWarning( data.message );
					return;
				}
				var str = '<table class="table">';
				str += '<thead class="thead-inverse">';
				str += '<tr>';
				var girdHead = getQueryGridHeader();
				for (var i=0; i<girdHead.length; i++) {
					str += '<th>' + girdHead[i].name + '</th>';
				}
				str += '</tr>';
				str += '</thead>';
				str += '<tbody>';
				
				for (var n=0; n<data.value.length; n++) {
					str += '<tr>';
					for (var i=0; i<girdHead.length; i++) {
						var f = girdHead[i].field;
						var val = data.value[n][f];		
						
						
						if ( !(typeof girdHead[i].formatter == 'undefined') && (typeof girdHead[i].formatter === 'function') ) {
							
							str += '<td>' + girdHead[i].formatter(val) + '</td>';
							continue;
						}
						
						
						if($.type(val) === "string") {
							str += '<td>' + val.replace(/</g, "&lt;").replace(/>/g, "&gt;"); + '</td>';
						} else {
							str += '<td>' + val + '</td>';
						}
					}			
					str += '</tr>';
				}
				
				str += '</tbody>';
				str += '</table>';
				$("#queryGridTable").html( str );
			}, 
			function(){
				
			}
	);
}
function queryClear() {
	$("#id").val('');
	$("#name").val('');
	$("#queryGridTable").html( '' );
}      	

</script>


</body>
  
  
</html>
