<%@page import="org.qifu.base.model.YesNo"%>
<%@page import="org.qifu.base.Constants"%>
<%@page import="org.qifu.util.ApplicationSiteUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String mainBasePath = basePath;
if (!Constants.getMainSystem().equals( Constants.getSystem()) ) {
	mainBasePath = ApplicationSiteUtils.getBasePath(Constants.getMainSystem(), request);
}

String commonUploadEnable = request.getParameter("commonUploadEnable");

%>
<table border="0" width="100%" cellspacing="2" cellpadding="2">
	<tr valign="top" align="left">
		<td align="left" width="100%">
			<span class="badge badge-default">${programName}</span> &nbsp; <font color="#ffffff" size="2">ID:&nbsp;${programId}</font>
		</td>
	</tr>	
</table>

<!-- 主要給 modal 模式的表單處理 xhr submit 出現 please wait 用的 -->
<!-- Modal Start here -->
<div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel-${programId}" aria-hidden="true" id="myPleaseWait-${programId}" data-keyboard="false" data-backdrop="static">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="mySmallModalLabel-${programId}">Please wait!</h4>
      </div>
      <div class="modal-body">
        <img alt="loading" src="<%=basePath%>/images/loadingAnimation.gif" border="0">
      </div>
    </div>
  </div>
</div>
<!-- Modal ends Here -->

<!-- 主要給 modal 模式的表單處理 xhr submit 出現 please wait 用的 -->
<!-- Modal Start here for page query grid -->
<div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallQueryGridModalLabel-${programId}" aria-hidden="true" id="myPleaseWaitForQueryGrid-${programId}" data-keyboard="false" data-backdrop="static">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="mySmallQueryGridModalLabel-${programId}">Please wait!</h4>
      </div>
      <div class="modal-body">
        <img alt="loading" src="<%=basePath%>/images/loadingAnimation.gif" border="0">
      </div>
    </div>
  </div>
</div>
<!-- Modal ends Here for page query grid -->

<script>
function showPleaseWait() {
	$('#myPleaseWait-${programId}').modal('show');
}
function hidePleaseWait() {
	$('#myPleaseWait-${programId}').modal('hide');
}

function showPleaseWaitForQueryGrid() {
	$('#myPleaseWaitForQueryGrid-${programId}').modal('show');
}
function hidePleaseWaitForQueryGrid() {
	$('#myPleaseWaitForQueryGrid-${programId}').modal('hide');
}
</script>

<%
// =================================================================================================================
if (YesNo.YES.equals(commonUploadEnable)) {
%>
<!-- 上傳 modal -->
<div class="modal fade" role="dialog" aria-labelledby="modalLabel-upload-${programId}" aria-hidden="true" id="modal-upload-${programId}">
  <div class="modal-dialog modal-lg" >
    <div class="modal-content" style="width:400px; height:250px;" >
    
      <div class="modal-header">     
        	<h4 class="modal-title" id="modalLabel-upload-${programId}">Upload</h4>
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>		
      </div>
      
      <div class="modal-body">
		<div class="container-fluid">
			<div class="row" style="width:370px; height:90px;"  >
				<form method="post" action="core.commonUploadFileAction.action" name="commonUploadForm-${programId}" id="commonUploadForm-${programId}" enctype="multipart/form-data" >				
					<label id="upload-label" for="upload"><img border="0" alt="help-icon" src="./icons/help-about.png"/>&nbsp;<font size='2'><b>Drag file to color Box.</b>&nbsp;(max size ${maxUploadSizeMb}mb)</font></label>
					<input type="file" style="width: 360px; height: 65px;  border: 2px dotted #FFAD1C;  background: #FFEFD0; border-radius: 4px;" name="commonUploadFile" id="commonUploadFile" draggable="true" title="Drag file there." onchange="commonUploadDataEvent();"/>		
					<input type="hidden" id="commonUploadFileType" name="commonUploadFileType" value="tmp" />
					<input type="hidden" id="commonUploadFileIsFileMode" name="commonUploadFileIsFileMode" value="N" />
				</form>		
			</div>
		</div>
      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

<script>
var _commonUploadFieldId = '';
function commonUploadDataEvent() {
	
	if (document.getElementById('commonUploadFile').value == '' ) {
		parent.toastrWarning( "Please select an file!" );
		return;
	}
	if (document.getElementById('commonUploadFile').files[0].size > _qifu_maxUploadSize ) {
		parent.toastrWarning( "File exceeded upload size!" );
		return;		
	}	
	
	var form = document.forms.namedItem("commonUploadForm-${programId}")
	var oData = new FormData(form);
	hiddenCommonUploadModal();
	showPleaseWait();
	$.ajax({
		type : 'POST',
	    url : '<%=mainBasePath%>/core.commonUploadFileJson.do',
	    timeout: _qifu_jqXhrTimeout,
	    processData: false,  // tell jQuery not to process the data
	    contentType: false,  // tell jQuery not to set contentType
	    data : oData,
	    cache: false,
	    async: true,
	    success : function(data, textStatus) {
	    	hidePleaseWait();
			if (data==null || (typeof data=='undefined') ) {
				alert('Unexpected error!');
				return;
			}    			
			if ( _qifu_success_flag != data.login ) {
				alert("Please try login again!");
				return;
			}       
			if ( _qifu_success_flag != data.isAuthorize ) {
				alert("No permission!");
				return;        				
			}
			parent.toastrInfo( data.message );
			$("#" + _commonUploadFieldId).val( data.value );
			
	    },
	    error : function(jqXHR, textStatus, errorThrown) {
	    	hidePleaseWait();
	        alert(textStatus);
	        _commonUploadFieldId = '';
	    }
	});	
	
}

function showCommonUploadModal(field, fileType, isFileMode) {
	_commonUploadFieldId = field;
	$("#commonUploadFileType").val( fileType );
	$("#commonUploadFileIsFileMode").val( isFileMode );	
	$('#modal-upload-${programId}').modal('show');
}
function hiddenCommonUploadModal() {
	$('#modal-upload-${programId}').modal('hide');
}
</script>
<%
} // end YesNo.YES.equals(commonUploadEnable)
//=================================================================================================================
%>
