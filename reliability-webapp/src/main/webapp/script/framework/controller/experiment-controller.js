function ExperimentController(context) {
	var experimentDao = new ExperimentDAO(context);
	var experimentPresenter = new ExperimentPresenter(context);

	this.getByProject = function(projectId) {
		experimentDao.getByProject(this.getAllResponseHandler, projectId);
	};

	this.getAllResponseHandler = function(responseData) {
		experimentPresenter.showAllView(responseData);
	};

	this.getExperimentDetails = function(experimentId) {
		experimentDao.getById(this.getExperimentDetailsResponseHandler, experimentId);
	};

	this.getExperimentDetailsResponseHandler = function(experiment) {
		experimentPresenter.showDetailsView(experiment);
	};
	
	this.recalculateExperiment = function(experimentId) {
		experimentDao.recalculate(this.recalculateExperimentResponseHandler, experimentId);
	};

	this.recalculateExperimentResponseHandler = function(experiment) {
		experimentPresenter.showDetailsView(experiment);
	};
}