<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.qifu.base.Constants"%>
<%@page import="org.qifu.util.ApplicationSiteUtils"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String mainBasePath = basePath;
if (!Constants.getMainSystem().equals( Constants.getSystem() ) ) {
	mainBasePath = ApplicationSiteUtils.getBasePath(Constants.getMainSystem(), request);
}

%>
<link rel="stylesheet" href="<%=mainBasePath%>/tether/tether.min.css?ver=${jsVerBuild}" crossorigin="anonymous">
<script type="text/javascript" src="<%=mainBasePath%>/tether/tether.min.js?ver=${jsVerBuild}"></script>
<script type="text/javascript" src="<%=mainBasePath%>/popper-js/umd/popper.min.js?ver=${jsVerBuild}"></script>
<script type="text/javascript" src="<%=mainBasePath%>/jquery/jquery-3.1.1.min.js?ver=${jsVerBuild}"></script>
<link rel="stylesheet" href="<%=mainBasePath%>/bootstrap-4/css/bootstrap.css?ver=${jsVerBuild}" crossorigin="anonymous">
<link href="<%=mainBasePath%>/font-awesome/css/font-awesome.min.css?ver=${jsVerBuild}" rel="stylesheet" type="text/css" />
<script src="<%=mainBasePath%>/bootstrap-4/js/bootstrap.js?ver=${jsVerBuild}" crossorigin="anonymous"></script>
<script src="<%=mainBasePath%>/configJs.do?ver=${jsVerBuild}" crossorigin="anonymous"></script>
<script src="<%=mainBasePath%>/js/f.js?ver=${jsVerBuild}" crossorigin="anonymous"></script>
