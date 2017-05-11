function xhrSendParameterForQueryGrid(xhrUrl, jsonParam, successFn, errorFn, selfPleaseWaitShow) {
	if (null == selfPleaseWaitShow || _qifu_success_flag != selfPleaseWaitShow) {
		parent.showPleaseWaitForQueryGrid();
	} else {
		showPleaseWaitForQueryGrid();
	}
	$.ajax({
		type : _qifu_jqXhrType,
	    url : xhrUrl,
	    timeout: _qifu_jqXhrTimeout,
	    dataType : 'json',
	    data : jsonParam,
	    cache: _qifu_jqXhrCache,
	    async: _qifu_jqXhrAsync,
	    success : function(data, textStatus) {
	    	if (null == selfPleaseWaitShow || _qifu_success_flag != selfPleaseWaitShow) {
	    		parent.hidePleaseWaitForQueryGrid();
	    	} else {
	    		hidePleaseWaitForQueryGrid();
	    	}
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
			successFn(data, textStatus);
	    },
	    error : function(jqXHR, textStatus, errorThrown) {
	    	parent.hidePleaseWaitForQueryGrid(); 	    	
	        alert(textStatus);
	        errorFn(jqXHR, textStatus, errorThrown);
	    }
	});
}

function xhrSendParameter(xhrUrl, jsonParam, successFn, errorFn, selfPleaseWaitShow) {
	if (null == selfPleaseWaitShow || _qifu_success_flag != selfPleaseWaitShow) {
		parent.showPleaseWait();
	} else {
		showPleaseWait();
	}
	$.ajax({
		type : _qifu_jqXhrType,
	    url : xhrUrl,
	    timeout: _qifu_jqXhrTimeout,
	    dataType : 'json',
	    data : jsonParam,
	    cache: _qifu_jqXhrCache,
	    async: _qifu_jqXhrAsync,
	    success : function(data, textStatus) {
	    	if (null == selfPleaseWaitShow || _qifu_success_flag != selfPleaseWaitShow) {
	    		parent.hidePleaseWait();
	    	} else {
	    		hidePleaseWait();
	    	}
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
			successFn(data, textStatus);
	    },
	    error : function(jqXHR, textStatus, errorThrown) {
	    	if (null == selfPleaseWaitShow || _qifu_success_flag != selfPleaseWaitShow) {
	    		parent.hidePleaseWait();
	    	} else {
	    		hidePleaseWait();
	    	}
	        alert(textStatus);
	        errorFn(jqXHR, textStatus, errorThrown);
	    }
	});
}

function xhrSendForm(xhrUrl, formId, successFn, errorFn, selfPleaseWaitShow) {
	if (null == selfPleaseWaitShow || _qifu_success_flag != selfPleaseWaitShow) {
		parent.showPleaseWait();
	} else {
		showPleaseWait();
	}
	$.ajax({
		type : _qifu_jqXhrType,
	    url : xhrUrl,
	    timeout: _qifu_jqXhrTimeout,
	    dataType : 'json',
	    data : $("#"+formId).serialize(),
	    cache: _qifu_jqXhrCache,
	    async: _qifu_jqXhrAsync,
	    success : function(data, textStatus) {
	    	if (null == selfPleaseWaitShow || _qifu_success_flag != selfPleaseWaitShow) {
	    		parent.hidePleaseWait();
	    	} else {
	    		hidePleaseWait();
	    	}
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
			successFn(data, textStatus);
	    },
	    error : function(jqXHR, textStatus, errorThrown) {
	    	if (null == selfPleaseWaitShow || _qifu_success_flag != selfPleaseWaitShow) {
	    		parent.hidePleaseWait();
	    	} else {
	    		hidePleaseWait();
	    	}
	        alert(textStatus);
	        errorFn(jqXHR, textStatus, errorThrown);
	    }
	});
}

function xhrSendParameterNoPleaseWait(xhrUrl, jsonParam, successFn, errorFn) {
	$.ajax({
		type : _qifu_jqXhrType,
	    url : xhrUrl,
	    timeout: _qifu_jqXhrTimeout,
	    dataType : 'json',
	    data : jsonParam,
	    cache: _qifu_jqXhrCache,
	    async: false, // _qifu_jqXhrAsync
	    success : function(data, textStatus) { 	    	
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
			successFn(data, textStatus);
	    },
	    error : function(jqXHR, textStatus, errorThrown) {    	
	        alert(textStatus);
	        errorFn(jqXHR, textStatus, errorThrown);
	    }
	});
}

function xhrSendFormNoPleaseWait(xhrUrl, formId, successFn, errorFn) {
	$.ajax({
		type : _qifu_jqXhrType,
	    url : xhrUrl,
	    timeout: _qifu_jqXhrTimeout,
	    dataType : 'json',
	    data : $("#"+formId).serialize(),
	    cache: _qifu_jqXhrCache,
	    async: false, // _qifu_jqXhrAsync
	    success : function(data, textStatus) {   	
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
			successFn(data, textStatus);
	    },
	    error : function(jqXHR, textStatus, errorThrown) {	    	
	        alert(textStatus);
	        errorFn(jqXHR, textStatus, errorThrown);
	    }
	});
}

function setWarningMessageField(formGroups, fields, checkFields) {
	if (null == fields) {
		return;
	}
	for (var k in fields) {
		var idKey = '';
		var msgContent = '';
		var formGroupId = '';
		for (var d in checkFields) {			
			if ( k == d ) {
				idKey = fields[k];
				msgContent = checkFields[d];
			}
		}
		for (var g in formGroups) {
			if ( fields[k] == g ) {
				formGroupId = formGroups[g]; 
			}
		}
		if (null == idKey || idKey == '') {
			continue;
		}
		$("#"+idKey+"-feedback").html( msgContent );
		$("#"+idKey).addClass( "form-control-warning" );
		if (null != formGroupId && formGroupId != '') {
			$("#"+formGroupId).addClass( "has-warning" );
		}
	}
}
function clearWarningMessageField(formGroups, fields) {
	for (var k in fields) {
		var idKey = fields[k];
		$("#"+idKey+"-feedback").html( "" );
		$("#"+idKey).removeClass( "form-control-warning" );		
	}
	for (var g in formGroups) {
		$("#"+formGroups[g]).removeClass( "has-warning" );
	}
}
