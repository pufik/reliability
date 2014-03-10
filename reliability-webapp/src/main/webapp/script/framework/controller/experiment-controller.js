function ExperimentController(context) {
	var experimentDao = new ExperimentDAO(context);
	var experimentPresenter = new ExperimentPresenter(context);

	this.getByProject = function(projectId) {
		experimentDao.getByProject(this.getAllResponseHandler, projectId);
	};

	this.getAllResponseHandler = function(responseData) {
		experimentPresenter.showAllView(responseData);
	};
}