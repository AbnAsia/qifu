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
<script>
function queryGrid() {
	
}
function queryClear() {
	$("#id").val('');
	$("#name").val('');
}      	
</script>

<br>
<br>

<div id="queryGridTable">
<table class="table">
  <thead class="thead-inverse">
    <tr>
      <th>#</th>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Username</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>Larry</td>
      <td>the Bird</td>
      <td>@twitter</td>
    </tr>
  </tbody>
</table>
</div>


</body>
  
  
</html>
