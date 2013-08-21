function GraphPresenter(context) {
	var templateProvider = context.getTemplateProvider();

	this.showGraphByIssuePriority = function(resultList) {
		var dataGraph = [];
		for (key in resultList) {
			var item = [];
			item.push(key + " [" + resultList[key] + "]");
			item.push(resultList[key]);
			dataGraph.push(item);
		}

		$('<div title="Issues by Priority"><div id="issuePriorityChart" style="height: 600px; width: 500px; margin: 0 auto;"></div></div>')
				.dialog({
					width : 600,
					height: context.getOptimalDialogHeight(),
					modal : true,
					open : function() {
						createPieChart('issuePriorityChart', dataGraph);
					},
					close : function() {
						$(this).remove();
					},
					buttons: {
						"Close": function(){
							$(this).dialog("close");
						}
					}
		});
	};
	
	this.showGraphByIssueType = function(resultList) {
		var dataGraph = [];
		for (key in resultList) {
			var item = [];
			item.push(key + " [" + resultList[key] + "]");
			item.push(resultList[key]);
			dataGraph.push(item);
		}

		$('<div title="Issues by Type"><div id="issueTypeChart" style="height: 600px; width: 500px; margin: 0 auto;"></div></div>')
				.dialog({
					width : 600,
					height: context.getOptimalDialogHeight(),
					modal : true,
					open : function() {
						createPieChart('issueTypeChart', dataGraph);
					},
					close : function() {
						$(this).remove();
					},
					buttons: {
						"Close": function(){
							$(this).dialog("close");
						}
					}
		});
	};
	
	this.showIssueStatisticGraph = function(reportList) {
		var dataGraph = [];
		for (key in reportList) {
			var tmp = []; 
			tmp.push(key);
			tmp.push(reportList[key]);
			dataGraph.push(tmp);
		}
		
		$('<div title="Issue Statistic"><div id="reportChart" style="height: 600px; width: 500px; margin: 0 auto;"></div></div>')
				.dialog({
					width : 600,
					height: context.getOptimalDialogHeight(),
					modal : true,
					open : function() {
						createIsueStatisticGraph('reportChart', dataGraph);
					},
					close : function() {
						$(this).remove();
					},
					buttons: {
						"Close": function(){
							$(this).dialog("close");
						}
					}
		});
	};
	
	
	var createPieChart = function(chartPlaceId, dataGraph){
		var plot1 = jQuery.jqplot(chartPlaceId, [ dataGraph ], {
			seriesDefaults : {
				renderer : jQuery.jqplot.PieRenderer,
				rendererOptions : {
					showDataLabels : true
				}
			},
			legend: {
	            show: true,
	            location: 's'
	        }
		});
	};
	
	var createIsueStatisticGraph = function(graphPlaceId, inputData){
		   $.jqplot.config.enablePlugins = true;
			 
			  plot1 = $.jqplot(graphPlaceId,[inputData],{
			     title: 'Issue Statistic',
			     axesDefaults: {
			         labelRenderer: $.jqplot.CanvasAxisLabelRenderer
			       },
			     seriesDefaults: {
			          rendererOptions: {
			              smooth: true
			          }
			      },
			     axes: {
			         xaxis: {
			        	 label: "Date",
			             renderer: $.jqplot.DateAxisRenderer,
			             rendererOptions:{
			                    tickRenderer:$.jqplot.CanvasAxisTickRenderer
			                },
			             tickOptions: {
			                 formatString: '%#d/%#m/%Y'
			             }
			         },
			         yaxis: {
			        	 label: "Issue",
			             tickOptions: {
			                 formatString: '%.2f'
			             }
			         }
			     },
			     highlighter: {
			         sizeAdjust: 10,
			         tooltipLocation: 'n',
			         tooltipAxes: 'y',
			         tooltipFormatString: '<b><i><span style="color:red;">Amount</span></i></b> %.2f',
			         useAxesFormatters: false
			     },
			     cursor: {
			         show: true,
			         zoom:true,
			         looseZoom: true
			     },
			     series:[
			             {
			               // Change our line width and use a diamond shaped marker.
			               lineWidth:2,
			               markerOptions: { style:'dimaond' }
			             },
			             {
			               // Don't show a line, just show markers.
			               // Make the markers 7 pixels with an 'x' style
			               lineWidth:2,
			               markerOptions: { size: 7, style:"x" }
			             }
			         ]	     
			  });
	};
}