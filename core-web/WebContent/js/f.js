function xhrSendParameter(xhrUrl, jsonParam, successFn, errorFn) {
	parent.showPleaseWait();
	$.ajax({
		type : 'POST',
	    url : xhrUrl,
	    timeout: 300000,
	    dataType : 'json',
	    data : jsonParam,
	    cache: false,
	    async: true,
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
		type : 'POST',
	    url : xhrUrl,
	    timeout: 300000,
	    dataType : 'json',
	    data : $("#"+formId).serialize(),
	    cache: false,
	    async: true,
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

