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


<table id="queryGridTableToolbar" width="100%" border="0" cellspacing="0" cellpadding="1" style="display:none; border:1px #ebeadb solid; background: linear-gradient(to top, #f1eee5 , #fafafa);">
  <tr>
  	
  	<td width="100px" style="background: linear-gradient(to top, #f1eee5 , #fafafa);" align="center" >
  		<font size="2">Total:&nbsp;<span id="rowCount"/></font>  	
  	</td>
  	<td width="10px" style="background: linear-gradient(to top, #f1eee5 , #fafafa);" align="center">&nbsp;</td>        
    <td width="20px" style="background: linear-gradient(to top, #f1eee5 , #fafafa);" align="right"><a href="javascript:changeQueryGridToFirst();"><img src="./icons/stock_first.png" border="0" alt="F" title="First page"/></a></td>
    <td width="20px" style="background: linear-gradient(to top, #f1eee5 , #fafafa);" align="right"><a href="javascript:changeQueryGridToPrev();"><img src="./icons/stock_left.png" border="0" alt="P" title="Previous"/></a></td>
    <td width="200px" style="background: linear-gradient(to top, #f1eee5 , #fafafa);" align="center">
    <!-- pageOf.size -->
    <input type="hidden" name="pageSize" id="pageSize" value="1"/>
    	<font size="2">Page</font>
    	<!-- pageOf.select -->
		<input id="select" name="select" maxlength="6" type="text" value="1" style="width: 60px;" onChange="changeQueryGridPageOfSelect();"></input>	
			
    	<font size="2">&nbsp;/&nbsp;<span id="sizeShow"/></font>
    </td>    
    <td width="20px" style="background: linear-gradient(to top, #f1eee5 , #fafafa);" align="left"><a href="javascript:changeQueryGridToNext();"><img src="./icons/stock_right.png" border="0" alt="N" title="Next"/></a></td>
    <td width="20px" style="background: linear-gradient(to top, #f1eee5 , #fafafa);" align="left"><a href="javascript:changeQueryGridToLast();"><img src="./icons/stock_last.png" border="0" alt="L" title="Last page"/></a></td>
    <td width="10px" style="background: linear-gradient(to top, #f1eee5 , #fafafa);" align="center">&nbsp;</td>
    <td width="200px" style="background: linear-gradient(to top, #f1eee5 , #fafafa);" align="center">
    	<font size="2">Row</font>
    	<!-- pageOf.showRow -->
    	<select name="showRow" id="showRow" style="width: 75px;"
    			onChange="changeQueryGridPageOfShowRow();" >
    			<option value="10">10</option>
    			<option value="20">20</option>
    			<option value="30">30</option>
    			<option value="50">50</option>
    			<option value="75">75</option>
    			<option value="100">100</option>
    	</select> 
		    	    	
    </td>    
    <td style="background: linear-gradient(to top, #f1eee5 , #fafafa);">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  </tr>
</table> 
<div id="queryGridTable">
</div>
<script>

var _before_select_page = 1;

function clearQueryGridTable() {
	$("#rowCount").html( '0' );
	$("#sizeShow").html( '1' );
	$("#pageSize").val( '1' );
	$("#showRow").val( '10' );
	$("#select").val( '1' );
	_before_select_page = 1;
	hiddenQueryGridToolBarTable();
	$("#queryGridTable").html( '' );	
}

/**
 * 不顯示換頁TABLE
 */
function hiddenQueryGridToolBarTable() {
	$("#queryGridTableToolbar").css( "display", "none" );
}

/**
 * 顯示換頁TABLE
 */
function showQueryGridToolBarTable() {
	$("#queryGridTableToolbar").css( "display", "" );
}

function changeQueryGridPageOfSelect() {
	if ( !( /^\+?(0|[1-9]\d*)$/.test( $("#select").val() ) ) ) { // not a page number
		$("#select").val("1");
	}
	var page = parseInt( $("#select").val() );
	if ( isNaN(page) || page <= 0 ) { // not a page number
		$("#select").val("1");
	}
	if (page>( parseInt( $("#pageSize").val(), 10) || 1 ) ) { // 頁面最小要是1
		page=( parseInt( $("#pageSize").val(), 10) || 1 );
		$("#select").val( page+'' );
	}
	// ----------------------------------------------------------------------------
	
	if ( _before_select_page != page ) {
		queryGrid();
	}		
	
}
function changeQueryGridPageOfShowRow() {
	$("#select").val("1");
	queryGrid();	
}
function getQueryGridShowRow() {
	return $("#showRow").val();
}
function getQueryGridSelect() {
	return $("#select").val();
}
/**
 * 到第1頁icon click
 */
function changeQueryGridToFirst() {	
	$("#select").val("1");
	queryGrid();
}

/**
 * 到最後1頁icon click
 */
function changeQueryGridToLast() {
	$("#select").val( $("#pageSize").val() );
	queryGrid();
}

/**
 * 到上1頁icon click
 */
function changeQueryGridToPrev() {
	var page=( parseInt( $("#select").val(), 10 ) || 0 )-1;
	if (page<=0) {
		page=1;
	}
	$("#select").val( page+'' );
	queryGrid();
}

/**
 * 到下1頁icon click
 */
function changeQueryGridToNext() {
	var page=( parseInt( $("#select").val(), 10) || 0 )+1;
	if (page>( parseInt( $("#pageSize").val(), 10) || 1 ) ) { // 頁面最小要是1
		page=( parseInt( $("#pageSize").val(), 10) || 1 );
	}
	$("#select").val( page+'' );
	queryGrid();
}

function queryGrid() {
	xhrSendParameter(
			'./core.sysSiteQueryGridJson.do', 
			{
				'parameter[sysId]'	: $("#id").val(),
				'parameter[name]'	: $("#name").val(),
				'select'			: getQueryGridSelect(),
				'showRow'			: getQueryGridShowRow()
			}, 
			function(data) {
				if ( _qifu_success_flag != data.success) {
					
					clearQueryGridTable();
					
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
				
				
				$("#rowCount").html( data.pageOfCountSize );
				$("#sizeShow").html( data.pageOfSize );
				$("#pageSize").val( data.pageOfSize );
				_before_select_page = data.pageOfSelect;
				
				showQueryGridToolBarTable();
				
				$("#queryGridTable").html( str );
			}, 
			function(){
				
			}
	);
}
function queryClear() {
	$("#id").val('');
	$("#name").val('');
	
	clearQueryGridTable();
	
}      	

</script>


</body>
  
  
</html>
