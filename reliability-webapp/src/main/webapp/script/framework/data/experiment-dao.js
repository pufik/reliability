function ExperimentDAO(context) {

	var getAllUrl = context.contextPath + "/action/experiment/project";

	this.getByProject = function(responseHandler, projectId) {
		getDataByAjax({
			pageUrl : getAllUrl + "/" + projectId,
			handler : responseHandler
		});
	};
}
