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
<script type="text/javascript" src="<%=mainBasePath%>/jquery/jquery-3.2.1.min.js?ver=${jsVerBuild}"></script>
<link rel="stylesheet" href="<%=mainBasePath%>/bootstrap-4/css/bootstrap.css?ver=${jsVerBuild}" crossorigin="anonymous">
<link href="<%=mainBasePath%>/font-awesome/css/font-awesome.min.css?ver=${jsVerBuild}" rel="stylesheet" type="text/css" />
<script src="<%=mainBasePath%>/bootstrap-4/js/bootstrap.js?ver=${jsVerBuild}" crossorigin="anonymous"></script>
<script src="<%=mainBasePath%>/configJs.do?ver=${jsVerBuild}" crossorigin="anonymous"></script>
<script src="<%=mainBasePath%>/js/f.js?ver=${jsVerBuild}" crossorigin="anonymous"></script>

<style>
/* =================================== 2018-01-24 add =================================== */
/* OLD css for Form/FormGroup input warning info */
/* =================================== 2018-01-24 add =================================== */
.form-control-success,
.form-control-warning,
.form-control-danger {
  padding-right: 2.25rem;
  background-repeat: no-repeat;
  background-position: center right 0.5625rem;
  -webkit-background-size: 1.125rem 1.125rem;
          background-size: 1.125rem 1.125rem;
}

.has-success .form-control-feedback,
.has-success .form-control-label,
.has-success .col-form-label,
.has-success .form-check-label,
.has-success .custom-control {
  color: #5cb85c;
}

.has-success .form-control {
  border-color: #5cb85c;
}

.has-success .input-group-addon {
  color: #5cb85c;
  border-color: #5cb85c;
  background-color: #eaf6ea;
}

.has-success .form-control-success {
  background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 8 8'%3E%3Cpath fill='%235cb85c' d='M2.3 6.73L.6 4.53c-.4-1.04.46-1.4 1.1-.8l1.1 1.4 3.4-3.8c.6-.63 1.6-.27 1.2.7l-4 4.6c-.43.5-.8.4-1.1.1z'/%3E%3C/svg%3E");
}

.has-warning .form-control-feedback,
.has-warning .form-control-label,
.has-warning .col-form-label,
.has-warning .form-check-label,
.has-warning .custom-control {
  color: #f0ad4e;
}

.has-warning .form-control {
  border-color: #f0ad4e;
}

.has-warning .input-group-addon {
  color: #f0ad4e;
  border-color: #f0ad4e;
  background-color: white;
}

.has-warning .form-control-warning {
  background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 8 8'%3E%3Cpath fill='%23f0ad4e' d='M4.4 5.324h-.8v-2.46h.8zm0 1.42h-.8V5.89h.8zM3.76.63L.04 7.075c-.115.2.016.425.26.426h7.397c.242 0 .372-.226.258-.426C6.726 4.924 5.47 2.79 4.253.63c-.113-.174-.39-.174-.494 0z'/%3E%3C/svg%3E");
}

.has-danger .form-control-feedback,
.has-danger .form-control-label,
.has-danger .col-form-label,
.has-danger .form-check-label,
.has-danger .custom-control {
  color: #d9534f;
}

.has-danger .form-control {
  border-color: #d9534f;
}

.has-danger .input-group-addon {
  color: #d9534f;
  border-color: #d9534f;
  background-color: #fdf7f7;
}

.has-danger .form-control-danger {
  background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23d9534f' viewBox='-2 -2 7 7'%3E%3Cpath stroke='%23d9534f' d='M0 0l3 3m0-3L0 3'/%3E%3Ccircle r='.5'/%3E%3Ccircle cx='3' r='.5'/%3E%3Ccircle cy='3' r='.5'/%3E%3Ccircle cx='3' cy='3' r='.5'/%3E%3C/svg%3E");
}
/* =================================== 2018-01-24 add =================================== */
</style>
