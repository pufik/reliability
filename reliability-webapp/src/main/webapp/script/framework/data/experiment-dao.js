function ExperimentDAO(context) {

	var getAllUrl = context.contextPath + "/action/experiment/project";
	var getByIdUrl = context.contextPath + "/action/experiment/";

	this.getByProject = function(responseHandler, projectId) {
		getDataByAjax({
			pageUrl : getAllUrl + "/" + projectId,
			handler : responseHandler
		});
	};

	this.getById = function(responseHandler, experimentId) {
		getDataByAjax({
			pageUrl : getByIdUrl + experimentId,
			handler : responseHandler
		});
	};
	
	this.recalculate = function(responseHandler, experimentId) {
		getDataByAjax({
			pageUrl : getByIdUrl + experimentId + "/recalculate",
			handler : responseHandler
		});
	};
}
