function ExperimentPresenter(context) {
	var templateProvider = context.getTemplateProvider();
	
	this.showAllView = function(experiments) {
		templateProvider.getContent("/project/experiment-list.tpl", {items: experiments}, this.showAllViewRender);
	};

	this.showAllViewRender = function(content) {
		$(".experimentPlace").children().remove();
		$(".experimentPlace").append(content);
		
		$('#experimentList').dataTable({
			"bJQueryUI": true,
	        "bSort": true,
	        "bInfo": true,		        
	        "bAutoWidth": true,
			"sPaginationType": "full_numbers",
	        "aaSorting": [[ 0, "asc" ]]
		});
		
		$(".buttonCreate").button({ icons: {primary:'ui-icon-plusthick'}});
		$(".showMyuGraph").button({ icons: {primary:'ui-icon-shuffle'}});
		$(".showLambdaGraph").button({ icons: {primary:'ui-icon-shuffle'}});
	};
}