function GraphController(context) {
	var graphPresenter = new GraphPresenter(context);

	this.showIssueGraphByPriority = function() {
		var projectId = $("#projectIdHolder").val();
		
		var issues = context.getCache()["issue_" + projectId];
		var dataList = [];
		for(var i =0; i< issues.length; i++){
			var priority = issues[i].priority;
			if(!dataList[priority]){
				dataList[priority] = 0;
			}
			
			dataList[priority] +=  1;
		}
		
		graphPresenter.showGraphByIssuePriority(dataList);
	};
	
	this.showIssueGraphByType = function() {
		var projectId = $("#projectIdHolder").val();
		
		var issues = context.getCache()["issue_" + projectId];
		var dataList = [];
		for(var i =0; i< issues.length; i++){
			var type = issues[i].type;
			if(!dataList[type]){
				dataList[type] = 0;
			}
			
			dataList[type] +=  1;
		}
		
		graphPresenter.showGraphByIssueType(dataList);
	};
	
	this.showIssueStatistic = function() {
		var projectId = $("#projectIdHolder").val();
		
		var issues = context.getCache()["issue_" + projectId];
		var dataList = [];
		
		issues.sort(function(a, b){
			return (Date.parse(a.createDate) > Date.parse(b.createDate));
		});
		
		for(var i =0; i< issues.length; i++){
			var createDate = issues[i].createDate;
			var tmpDate = ($.datepicker.parseDate('dd/mm/yy', createDate));
			var curr_date = tmpDate.getDate();
		    var curr_month = tmpDate.getMonth() + 1; //Months are zero based
		    var curr_year = tmpDate.getFullYear();
			createDate = curr_year + "-" + curr_month + "-" + curr_date;
			
			if(!dataList[createDate]){
				dataList[createDate] = 0;
			}
			
			dataList[createDate] +=  1;
		}
				
		graphPresenter.showIssueStatisticGraph(dataList);
	};
}