<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
