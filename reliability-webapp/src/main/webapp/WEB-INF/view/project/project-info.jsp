<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/view/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Projects</title>
<script type="text/javascript"
	src="${contextPath}/script/framework/model/project-model.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/framework/controller/project-controller.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/framework/data/project-dao.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/framework/presenter/project-presenter.js"></script>

<script type="text/javascript"
	src="${contextPath}/script/framework/controller/graph-controller.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/framework/presenter/graph-presenter.js"></script>

<script type="text/javascript"
	src="${contextPath}/script/jquery/plugin/jquery.jqplot.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/jquery/plugin/jqplot/jqplot.cursor.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/jquery/plugin/jqplot/jqplot.dateAxisRenderer.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/jquery/plugin/jqplot/jqplot.highlighter.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/jquery/plugin/jqplot/jqplot.barRenderer.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/jquery/plugin/jqplot/jqplot.categoryAxisRenderer.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/jquery/plugin/jqplot/jqplot.dragable.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/jquery/plugin/jqplot/jqplot.trendline.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/jquery/plugin/jqplot/jqplot.canvasAxisLabelRenderer.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/jquery/plugin/jqplot/jqplot.canvasTextRenderer.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/jquery/plugin/jqplot/jqplot.pieRenderer.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/script/jquery/plugin/jqplot/jqplot.donutRenderer.min.js"></script>
</head>
<body>
	<script type="text/javascript">
$(function(){
	getContext().getProjectController().getAll();
});
</script>
</body>
</html>