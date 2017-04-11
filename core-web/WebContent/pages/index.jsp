<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE html>
<html>
<head>
<title>TEST</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<script type="text/javascript" src="./jquery/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="./bootstrap-4.0.0-alpha.6/css/bootstrap.css" crossorigin="anonymous">
<script src="./bootstrap-4.0.0-alpha.6/js/bootstrap.js" crossorigin="anonymous"></script>
<script src="./bootbox/bootbox.js" crossorigin="anonymous"></script>

<style type="text/css">

iframe {
	width: 100%;
    height: 100%;
    border: none;
}

.tab-content {
	height: 1080px;
	background: #ffffff;
}
	  

/*
 * Base structure
 */

/* Move down content because we have a fixed navbar that is 50px tall */
body {
  padding-top: 50px;
  height:100%;
}

/*
 * Typography
 */

h1 {
  margin-bottom: 20px;
  padding-bottom: 9px;
  border-bottom: 1px solid #eee;
}

/*
 * Sidebar
 */

.sidebar {
  position: fixed;
  top: 51px;
  bottom: 0;
  left: 0;
  z-index: 1000;
  padding: 20px;
  overflow-x: hidden;
  overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
  border-right: 1px solid #eee;
}

/* Sidebar navigation */
.sidebar {
  padding-left: 0;
  padding-right: 0;
}

.sidebar .nav {
  margin-bottom: 20px;
}

.sidebar .nav-item {
  width: 100%;
}

.sidebar .nav-item + .nav-item {
  margin-left: 0;
}

.sidebar .nav-link {
  border-radius: 0;
}

/*
 * Dashboard
 */

 /* Placeholders */
.placeholders {
  padding-bottom: 3rem;
}

.placeholder img {
  padding-top: 1.5rem;
  padding-bottom: 1.5rem;
}

</style>


<script type="text/javascript">

var _m_PAGE_CHANGE_URL_PARAM = 'isPageChange';

var _prog = [];

_prog.push({"id" : "CORE_PROGD01", "folder" : "Y", "name" : "A. Report", "icon" : "./icons/report.png", "url" : ""});
_prog.push({"id" : "CORE_PROG001", "folder" : "N", "name" : "Settings", "icon" : "./icons/system-run.png", "url" : "./e.html"});
_prog.push({"id" : "CORE_PROG999", "folder" : "N", "name" : "About", "icon" : "./icons/help-about.png", "url" : "./about.html"});
_prog.push({"id" : "CORE_PROG002", "folder" : "N", "name" : "01. Analytics", "icon" : "./icons/chart-graph-2d-1.png", "url" : "./f.html"});

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


function addTab( tabId, srcUrl ) {
	for (var i=0; _tabData != null && i< _tabData.length; i++) {
		if ( _tabData[i].tabId == tabId ) {
			//alert(123);
			activaTab(tabId);
			return;
			//closeTab(tabId);
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


</script>


</head>


<body>
    <nav class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
      <button class="navbar-toggler navbar-toggler-right hidden-lg-up" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
	  <a class="navbar-brand" href="#"><img alt="祈-qífú" src="./images/logo3.png" border="0"></a>
      

      <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
		  <li class="nav-item dropdown active">
			<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			  <b>Application</b>
			</a>
			<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
			  
			  <img src="./icons/chart-pie.png" broder="0">&nbsp;<font color="#848484"><b>A. Reports</b></font>
			  
			  
			  <a class="dropdown-item" href="#" onclick="addTab('CORE_PROG002', null);"><img src="./icons/chart-graph-2d-1.png" broder="0">&nbsp;&nbsp;01. Analytics</a>
			  <a class="dropdown-item" href="#"><img src="./icons/document-export.png" broder="0">&nbsp;&nbsp;02. Export</a>
			  
			  <div class="dropdown-divider"></div>
			  
			  <img src="./icons/computer.png" broder="0">&nbsp;<font color="#848484"><b>B. CORE</b></font>
			  	  
			  
			  <a class="dropdown-item" href="#"><img src="./icons/emblem-people.png" broder="0">&nbsp;&nbsp;01. Account</a>
			  <a class="dropdown-item" href="#"><img src="./icons/star.png" broder="0">&nbsp;&nbsp;02. TEST</a>
			  <a class="dropdown-item" href="#"><img src="./icons/star.png" broder="0">&nbsp;&nbsp;03. CCC</a>
			  
			  <div class="dropdown-divider"></div>
			  
			  <a class="dropdown-item" href="./index.do"><img src="./icons/view-refresh.png" broder="0">&nbsp;&nbsp;<b>Refresh</b></a>
			  
			</div>
		  </li>
          <li class="nav-item">
            <a class="nav-link" href="#" onclick="addTab('CORE_PROG001', null);">Settings</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" onclick="addTab('CORE_PROG999', null);">About</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" onclick="logoutEvent();">Logout</a>
          </li>		  
        </ul>
        <form class="form-inline mt-2 mt-md-0">
          <input class="form-control mr-sm-2" type="text" placeholder="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <nav class="col-sm-3 col-md-2 hidden-xs-down bg-faded sidebar">
		
          <ul class="nav nav-pills flex-column">

            <li class="nav-item">
              <img src="./icons/chart-pie.png" broder="0">&nbsp;<font color="#848484"><b>A. Reports</b></font>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" onclick="addTab('CORE_PROG002', null);"><img src="./icons/chart-graph-2d-1.png" broder="0">&nbsp;&nbsp;01. Analytics</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><img src="./icons/document-export.png" broder="0">&nbsp;&nbsp;02. Export</a>
            </li>
          </ul>

          <ul class="nav nav-pills flex-column">

            <li class="nav-item">
              <img src="./icons/computer.png" broder="0">&nbsp;<font color="#848484"><b>B. CORE</b></font>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><img src="./icons/emblem-people.png" broder="0">&nbsp;&nbsp;01. Account</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><img src="./icons/star.png" broder="0">&nbsp;&nbsp;02. TEST</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><img src="./icons/star.png" broder="0">&nbsp;&nbsp;03. CCC</a>
            </li>		

          </ul>
		  
		  
        </nav>

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
		
			<ul class="nav nav-tabs" id="myTab" role="tablist">

			</ul>

			<!-- Tab panes -->
			<div class="tab-content" id="myTabContent">
				

			</div>
			
			
        </main>
      </div>
    </div>

	
<script>
$('#myTab').bind('show', function(e) {  
    var paneID = $(e.target).attr('href');
    var src = $(paneID).attr('data-src');
    // if the iframe hasn't already been loaded once
    if($(paneID+" iframe").attr("src")=="")
    {
        $(paneID+" iframe").attr("src",src);
    }
});

// for TEST
addTab('CORE_PROG001', null);

</script>	

</body>
  
  
</html>
