(function($) {
	var detailTable = $("#detailTable"), controller = {
		init : function() {
			// controller.reloadList();

			var areaChartData = {
				labels : [ "January", "February", "March", "April", "May",
						"June", "July", "August", "September", "October" ],
				datasets : [
						{
							label : "My First dataset",
							fillColor : "rgba(220,220,220,0.2)",
							strokeColor : "rgba(220,220,220,1)",
							pointColor : "rgba(220,220,220,1)",
							pointStrokeColor : "#fff",
							pointHighlightFill : "#fff",
							pointHighlightStroke : "rgba(220,220,220,1)",
							data : [ 4723, 2934, 9182, 3223, 2912, 7732,
									2345, 0, 6936, 4674 ]
						},
						{
							label: "My Second dataset",
				            fillColor: "rgba(151,187,205,0.2)",
				            strokeColor: "rgba(151,187,205,1)",
				            pointColor: "rgba(151,187,205,1)",
				            pointStrokeColor: "#fff",
				            pointHighlightFill: "#fff",
				            pointHighlightStroke: "rgba(151,187,205,1)",
							data : [ 5480, 3246, 8290, 5006, 2865, 7379,
									4874, 3836, 7325, 5108 ]
						} ]
			};

			var areaChartOptions = {
				// Boolean - If we should show the scale at all
				showScale : true,
				// Boolean - Whether grid lines are shown across the chart
				scaleShowGridLines : false,
				// String - Colour of the grid lines
				scaleGridLineColor : "rgba(0,0,0,.05)",
				// Number - Width of the grid lines
				scaleGridLineWidth : 1,
				// Boolean - Whether to show horizontal lines (except X axis)
				scaleShowHorizontalLines : true,
				// Boolean - Whether to show vertical lines (except Y axis)
				scaleShowVerticalLines : true,
				// Boolean - Whether the line is curved between points
				bezierCurve : true,
				// Number - Tension of the bezier curve between points
				bezierCurveTension : 0.3,
				// Boolean - Whether to show a dot for each point
				pointDot : true,
				// Number - Radius of each point dot in pixels
				pointDotRadius : 4,
				// Number - Pixel width of point dot stroke
				pointDotStrokeWidth : 1,
				// Number - amount extra to add to the radius to cater for hit
				// detection outside the drawn point
				pointHitDetectionRadius : 20,
				// Boolean - Whether to show a stroke for datasets
				datasetStroke : true,
				// Number - Pixel width of dataset stroke
				datasetStrokeWidth : 2,
				// Boolean - Whether to fill the dataset with a color
				datasetFill : true,
				// String - A legend template
				legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].lineColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>",
				// Boolean - whether to maintain the starting aspect ratio or
				// not when responsive, if set to false, will take up entire
				// container
				maintainAspectRatio : true,
				// Boolean - whether to make the chart responsive to window
				// resizing
				responsive : true
			};

			// -------------
			// - LINE CHART -
			// --------------
			var lineChartCanvas = $("#lineChart").get(0).getContext("2d");
			var lineChart = new Chart(lineChartCanvas);
			var lineChartOptions = areaChartOptions;
			lineChartOptions.datasetFill = false;
			lineChart.Line(areaChartData, lineChartOptions);
		},
		reloadList : function() {
			var success = function(data, textStatus, jqXHR) {
				detailTable.html(data);
			}, fail = function(response) {
				alert("error");
			};
			$.get('/fn/demobill/list', success).fail(fail);
		}
	};
	controller.init();
})(jQuery);
