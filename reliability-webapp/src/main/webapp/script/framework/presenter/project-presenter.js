function ProjectPresenter(context) {
	var templateProvider = context.getTemplateProvider();
	
	var metricDesc = [];
	metricDesc["coverage"] = "Coverage";
	metricDesc["lines"] = "Lines";
	metricDesc["violations"] = "Violations";
	metricDesc["comment_lines_density"] = "Comments";
	metricDesc["comment_lines"] = "Comment lines";
	metricDesc["ncloc"] = "Lines of code";
	
	this.showAllView = function(projects) {
		templateProvider.getContent("/project/project-list.tpl", {items: projects}, this.showAllViewRender);
	};

	this.showAllViewRender = function(content) {		
		$("#main").children().remove();
		$("#main").append(content);
		
		var oTable = $('#projectList').dataTable({
			"bJQueryUI": true,
			"sPaginationType": "full_numbers",
	        "bLengthChange": true,
	        "bFilter": true,
	        "bSort": true,
	        "bInfo": true,		        
	        "bAutoWidth": false,
	        "aaSorting": [[ 0, "asc" ]]
		});
		
		$(".buttonCreate").button({ icons: {primary:'ui-icon-plusthick'}});
		
		 $("#projectList tbody").click(function(event) {
				$(oTable.fnSettings().aoData).each(function (){
					$(this.nTr).removeClass('row_selected');
				});
				$(event.target.parentNode).addClass('row_selected');
			});
		 
		 $("#projectList tbody").dblclick(function(event) {
			 	var id = $("#projectList .row_selected").find("input[name='projectId']").val();
			 	getContext().getProjectController().getDetails(id);		
		});
	};
	
	

	this.showDetailView = function(request) {
		templateProvider.getContent("/project/project-info.tpl", request, this.showDetailViewRender);
	};

	this.showDetailViewRender = function(content) {		
		$("#main").children().remove();
		$("#main").append(content);
		
		$("#projectTabs").tabs({
			beforeActivate: function(event, ui ) {
				var tabIndex = ui.newTab.index();
				
				if(tabIndex == 1){
					var projectId = $("#projectIdHolder").val();
					getContext().getProjectController().getBugs(projectId);	
				}else if(tabIndex == 2){
					var projectId = $("#projectIdHolder").val();
					getContext().getProjectController().getMetrics(projectId);	
				}
			}
		});
		
		$(".saveSonarSettings").button({ icons: {primary:'ui-icon-disk'}});
		$(".clearSonarSettings").button({ icons: {primary:'ui-icon-shuffle'}});
		$(".saveJiraSettings").button({ icons: {primary:'ui-icon-disk'}});
		$(".clearJiraSettings").button({ icons: {primary:'ui-icon-shuffle'}});
	};
	
	this.showBugsView = function(issues) {
		var projectId = $("#projectIdHolder").val();
		templateProvider.getContent(
				"/project/issue-list.tpl",
				{projectId: projectId, contextPath: context.contextPath,  items: issues},
				this.showBugsViewRender
				);
	};

	this.showBugsViewRender = function(content) {		
		$("#bugTab").children().remove();
		$("#bugTab").append(content);
		
		$(".buttonCreate").button({ icons: {primary:'ui-icon-plusthick'}});
		$(".showTypeGraph").button({ icons: {primary:'ui-icon-shuffle'}});
		$(".showPriorityGraph").button({ icons: {primary:'ui-icon-shuffle'}});
		$(".buttonCreateReport").button({ icons: {primary:'ui-icon-document'}});
		$(".buttonImportIssue").button({ icons: {primary:'ui-icon-document'}});
		
		$('#issueList').dataTable({
			"bJQueryUI": true,
	        "bSort": true,
	        "bInfo": true,		        
	        "bAutoWidth": true,
			"sPaginationType": "full_numbers",
	        "aaSorting": [[ 0, "asc" ]]
		});
	};
	
	this.showMetricsView = function(metrics) {
		for(var i =0; i<metrics.length; i++){
			metrics[i].name = metricDesc[metrics[i].name];
		}
		
		templateProvider.getContent("/project/metric-list.tpl", {items: metrics}, this.showMetricsViewRender);
	};
	
	this.showMetricsViewRender = function(content) {		
		$("#metricTab").children().remove();
		$("#metricTab").append(content);
		
		$(".buttonCreate").button({ icons: {primary:'ui-icon-plusthick'}});
		$(".showTypeGraph").button({ icons: {primary:'ui-icon-shuffle'}});
		$(".showPriorityGraph").button({ icons: {primary:'ui-icon-shuffle'}});
	};
	
	this.showImportIssueDialog = function() {		
		$(".importIssueFormDialog").dialog({
			width : 600,
			height: 200,
			modal : true,
			close : function() {
			},
			buttons: {
				"Close": function(){
					$(this).dialog("close");
				}
			}
		});
	};
	
	this.showProjectCreateDialog = function() {		
		templateProvider.getContent("/project/project-add-dialog.tpl", {}, this.showProjectCreateDialogViewRender);
	};
	
	this.showProjectCreateDialogViewRender = function(content) {		
		$(content).dialog({
			width : 480,
			height: 300,
			modal : true,
			close : function() {
				$(this).remove();
			},
			buttons: {
				"Create": function(){
					$("#projectCreateForm").submit();
				},
				"Close": function(){
					$(this).dialog("close");
				}
			}
		});
	};
}