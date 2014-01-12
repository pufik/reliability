function ProjectController(context) {
	var projectDao = new ProjectDAO(context);
	var projectPresenter = new ProjectPresenter(context);
	var tmpId = "";

	this.getAll = function() {
		projectDao.getAll(this.getAllResponseHandler);
	};

	this.getAllResponseHandler = function(responseData) {
		projectPresenter.showAllView(responseData);
	};

	this.getDetails = function(id) {
		projectDao.getDetails(this.getDetailsResponseHandler, id);
	};

	this.getDetailsResponseHandler = function(responseData) {
		projectPresenter.showDetailView(responseData);
	};

	this.getBugs = function(id) {
		tmpId = id;
		if (context.getCache()["issue_" + id]) {
			this.getBugsResponseHandler(context.getCache()["issue_" + id]);
		} else {
			projectDao.getBugs(this.getBugsResponseHandler, id);
		}
	};

	this.getBugsResponseHandler = function(responseData) {
		context.addToCache("issue_" + tmpId, responseData);
		projectPresenter.showBugsView(responseData);
	};
	
	this.getMetrics = function(id) {
		projectDao.getMetrics(this.getMetricsResponseHandler, id);
	};

	this.getMetricsResponseHandler = function(responseData) {
		projectPresenter.showMetricsView(responseData);
	};
	
	this.generateReport = function(){
		var projectId = $("#projectIdHolder").val();
		var url = context.contextPath + "/action/issue/report/project/" + projectId + "/issue_report.xsl";
		window.open(url, "_blank");
	};
	
	this.importIssues = function(){
		projectPresenter.showImportIssueDialog();
	};
	
	this.getCreateProjectDialog = function(){
		projectPresenter.showProjectCreateDialog();
	};
	
	this.createProject = function(){
		var project = new Project();
		console.log("Create project request");
		
		project.name = $("#projectName").val();
		project.description = $("#projectDescription").val();
		projectDao.createProject(project, this.createProjectResponseHandler);
		
		return false;
	};
	
	this.createProjectResponseHandler = function(message){
		if(message.type == "INFO"){
			$("#projectCreateDialog").remove();
			context.getProjectController().getAll();
		}else{
			$("#projectCreateError").text(message.message);
			$("#projectCreateError").show();
		}
	};
	
	this.saveJiraSettings = function(){
		var jiraSettings = {};
		jiraSettings.id = $("#jiraSettingsId").val();
		jiraSettings.userName = $("#jiraUserName").val();
		jiraSettings.password = $("#jiraUserPassword").val();
		jiraSettings.projectUid = $("#jiraProjectUid").val();
		jiraSettings.url = $("#jiraUrl").val();
		
		console.log(jiraSettings);
	};
}