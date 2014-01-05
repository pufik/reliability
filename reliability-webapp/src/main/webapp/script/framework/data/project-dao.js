function ProjectDAO(context) {
	
	var getAllUrl = context.contextPath + "/action/project/all";
	var getDetailsUrl = context.contextPath + "/action/project/info/";
	var getBugsUrl = context.contextPath + "/action/issue/project/";
	var getMetricsUrl = context.contextPath + "/action/metrics/project/";
	var createProjectUrl = context.contextPath + "/action/project/";
	
	this.getAll = function(responseHandler) {		 
		getDataByAjax({pageUrl : getAllUrl, handler : responseHandler});
	};	
	
	this.getDetails = function(responseHandler, id) {	
		var params = {};		 
		getDataByAjax({pageUrl : (getDetailsUrl  + id), handler : responseHandler, reqData: params});
	};
	
	this.getBugs = function(responseHandler, id) {	
		var params = {};		 
		getDataByAjax({pageUrl : (getBugsUrl+ id), handler : responseHandler, reqData: params});
	};
	
	this.getMetrics = function(responseHandler, id) {	
		var params = {};		 
		getDataByAjax({pageUrl : (getMetricsUrl+ id), handler : responseHandler, reqData: params});
	};
	
	this.createProject = function(project, responseHandler) {	
		getDataByAjaxJSON({pageUrl : createProjectUrl, reqType: "POST",  handler : responseHandler, dataObject: project});
	};
}