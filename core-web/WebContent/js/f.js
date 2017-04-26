function xhrSendParameter(xhrUrl, jsonParam, successFn, errorFn) {
	parent.showPleaseWait();
	$.ajax({
		type : _qifu_jqXhrType,
	    url : xhrUrl,
	    timeout: _qifu_jqXhrTimeout,
	    dataType : 'json',
	    data : jsonParam,
	    cache: _qifu_jqXhrCache,
	    async: _qifu_jqXhrAsync,
	    success : function(data, textStatus) {
	    	parent.hidePleaseWait();  	    	
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
			successFn(data);
	    },
	    error : function(jqXHR, textStatus, errorThrown) {
	    	parent.hidePleaseWait(); 	    	
	        alert(textStatus);
	        errorFn();
	    }
	});
}

function xhrSendForm(xhrUrl, formId, successFn, errorFn) {
	parent.showPleaseWait();
	$.ajax({
		type : _qifu_jqXhrType,
	    url : xhrUrl,
	    timeout: _qifu_jqXhrTimeout,
	    dataType : 'json',
	    data : $("#"+formId).serialize(),
	    cache: _qifu_jqXhrCache,
	    async: _qifu_jqXhrAsync,
	    success : function(data, textStatus) {
	    	parent.hidePleaseWait();  	    	
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
			successFn(data);
	    },
	    error : function(jqXHR, textStatus, errorThrown) {
	    	parent.hidePleaseWait(); 	    	
	        alert(textStatus);
	        errorFn();
	    }
	});
}

