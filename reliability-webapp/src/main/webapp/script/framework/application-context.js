function ApplicationContext(){
	var controller = {};
	var cache = {};
	var templateProvider = new TemplateProvider(this);
	
	this.getTemplateContext = function(){		
		return {prefix: this.contextPath + "/script/framework/view/tpl", sufix: ".htm" };
	};
		
	this.getTemplateProvider = function(){	
		return templateProvider;
	};
		
	this.getProjectController = function(){		
		var projectController = controller.project;
		
		if(!projectController){
			projectController = new ProjectController(this);
			controller.project = projectController;
		}
		
		return projectController;
	};
	
	this.getExperimentController = function(){		
		
		if(!controller.experiment){
			controller.experiment = new ExperimentController(this);
		}
		
		return controller.experiment;
	};
	
	this.getGraphController = function(){
		var graphController = controller.graph;
		
		if(!graphController){
			graphController = new GraphController(this);
			controller.graph = graphController;
		}
		
		return graphController;
	};
	
	this.getCache = function(){
		return cache;
	};
	
	this.addToCache = function(key, value){
		cache[key] = value;
	};
	
	this.getOptimalDialogHeight= function(){
		return 600;
	};
}

function getContext(){		
	return APPLICATION_CONTEXT;
}

