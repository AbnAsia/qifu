var _tabData = [];


function getPageUrl(url) {
	if ( url.indexOf(_m_PAGE_CHANGE_URL_PARAM) > -1 ) {
		return url;
	}
	if ( url.indexOf("?") > -1 ) {
		url += '&' + _m_PAGE_CHANGE_URL_PARAM + '=Y&isPageRefresh=' + guid();
	} else {
		url += '?' + _m_PAGE_CHANGE_URL_PARAM + '=Y&isPageRefresh=' + guid();
	}	
	return url;
}


function getProgUrl(progId) {
	var pUrl = '';
	for (var i=0; pUrl == '' && i< _prog.length; i++) {
		if ( _prog[i].id == progId ) {
			pUrl = _prog[i].url;
		}
	}	
	return getPageUrl(pUrl);
}


function addTab( tabId, srcUrl ) {
	for (var i=0; _tabData != null && i< _tabData.length; i++) {
		if ( _tabData[i].tabId == tabId ) {
			activaTab(tabId);
			return;
		}
	}
	var progName = 'unknown';
	var progIcon = './unknown.png';
	var progUrl = '';
	for (var i=0; _prog != null && i< _prog.length; i++) {
		if ( _prog[i].id == tabId ) {
			progName = _prog[i].name;
			progIcon = _prog[i].icon;
			progUrl = _prog[i].url;
		}
	}
	if ('' == progUrl || null == progUrl) {
		alert('PROG_ID: ' + tabId + ' no url, cannot call addTab');
		return;
	}
	if ( '' == srcUrl || null == srcUrl) {
		srcUrl = progUrl;
	}
	
	srcUrl = getPageUrl(srcUrl);
	
	$('#myTab').append('<li class="nav-item" id="_tab_' + tabId + '"><a class="nav-link" data-toggle="tab" href="#' + tabId + '" role="tab" aria-controls="' + tabId + '"><img src="' + progIcon + '" broder="0">&nbsp;' + progName + '&nbsp;<span class="close" onclick="closeTab(\'' + tabId + '\');">×</span></a></li>');
	$('#myTabContent').append('<div class="tab-pane" style="height: 100%;" id="' + tabId + '" data-src="' + srcUrl + '"><iframe src="' + srcUrl + '" ></iframe></div>');
	
	
	_tabData.push({"tabId": tabId});
	
	activaTab(tabId);
	
}


/*
function closeTab(tab_li, tabId) {
	
    var tabContentId = $(tab_li).parent().attr("href");
    var li_list = $(tab_li).parent().parent().parent();
    $(tab_li).parent().parent().remove(); //remove li of tab
    if ($(tabContentId).is(":visible")) {
        li_list.find("a").eq(0).tab('show'); // Select first tab
    }

    $(tabContentId).remove(); //remove respective tab content
	
	for (var i=0; _tabData != null && i< _tabData.length; i++) {
		if ( _tabData[i].tabId == tabId ) {
			_tabData.splice(i, 1);
			return;
		}
	}
	
}
*/


function closeTab(tabId) {
	
	$("#_tab_" + tabId).remove();
	$("#" + tabId).remove();
	
	var prevTabId = '';
	for (var i=0; _tabData != null && i< _tabData.length; i++) {
		if ( _tabData[i].tabId == tabId ) {
			_tabData.splice(i, 1);
			if ( '' != prevTabId && prevTabId != tabId ) {
				activaTab( prevTabId );
			} else {
				$(".nav-tabs a:last").tab('show');
			}
			i = _tabData.length;
		}
		if ( i < _tabData.length) {
			prevTabId = _tabData[i].tabId;
		}
	}
	
}


function activaTab(tab){
    $('.nav-tabs a[href="#' + tab + '"]').tab('show');
};


/* 
 * http://stackoverflow.com/questions/105034/create-guid-uuid-in-javascript 
 */
function guid() {
	
	  function s4() {
	    return Math.floor((1 + Math.random()) * 0x10000)
	      .toString(16)
	      .substring(1);
	  }
	  
	  return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
	    s4() + '-' + s4() + s4() + s4();
	  
}


function logoutEvent() {
	bootbox.confirm(
			"Logout! are you sure?", 
			function(result){ 
				if (!result) {
					return;
				}
				window.location = "./logout.do";
			}
	);	
}


function showPleaseWait() {
	$('#myPleaseWait').modal('show');
}
function hidePleaseWait() {
	$('#myPleaseWait').modal('hide');
}

function showPleaseWaitForQueryGrid() {
	$('#myPleaseWaitForQueryGrid').modal('show');
}
function hidePleaseWaitForQueryGrid() {
	$('#myPleaseWaitForQueryGrid').modal('hide');
}


function toastrInfo(message) {
	toastr.info( message.replace(/\n/gi, "<br>").replace("/\r\n", "<br>") );
}
function toastrWarning(message) {
	toastr.warning( message.replace(/\n/gi, "<br>").replace("/\r\n", "<br>") );
}
