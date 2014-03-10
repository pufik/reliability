<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/view/includes.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript"
	src="${contextPath}/script/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/jquery/jquery-ui-1.10.0.custom.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/jquery/plugin/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/jquery/plugin/jquery.blockUI.js"></script>

<script type="text/javascript"
	src="${contextPath}/script/util/handlebars-1.0.rc.1.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/util/handlebars-extend.js"></script>

<script type="text/javascript"
	src="${contextPath}/script/framework/application-context.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/framework/view/template-provider.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/framework/data/ajax-data-provider.js"></script>

<link href="${contextPath}/style/smoothness/jquery-ui-1.10.0.custom.css"
	rel="stylesheet" type="text/css" />
<!--<link href="${contextPath}/style/metro/jquery-ui.css"  rel="stylesheet" type="text/css" /> -->
<link href="${contextPath}/style/css/jquery.dataTables.css"
	rel="stylesheet" type="text/css" />
<link href="${contextPath}/style/css/jquery.jqplot.min.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${contextPath}/style/css/style.css"
	type="text/css" media="all" />
<link rel="shortcut icon"
	href="${contextPath}/style/css/images/favicon.ico" />

<decorator:head />
<script type="text/javascript">
    $(document).ready(function() {
    	  window.APPLICATION_CONTEXT = new ApplicationContext();
    	  window.APPLICATION_CONTEXT.contextPath = "${contextPath}";    	  
     });
    </script>
</head>
<body>
	<div class="shell">
		<div class="border">

			<div id="navigation">
				<ul>
					<li><a href="${contextPath}/action/project/main">Projects</a></li>
				</ul>
				<div class="cl">&nbsp;</div>
			</div>

			<div id="main">
				<decorator:body />
			</div>

		</div>
		<div id="footer">
			<p class="left">Copyright &copy; 2014, Reliability Team, Iurii
				Parfeniuk</p>
			<p class="right">
				<a href="http://iknit.lp.edu.ua/pz/en/" target="_blank">NU "Lviv
					Polytechnic" - Software Department</a>
			</p>
			<div class="cl"></div>
		</div>
	</div>
</body>
</html>