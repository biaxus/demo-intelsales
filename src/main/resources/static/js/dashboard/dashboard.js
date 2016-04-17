(function($) {
	var detailTable = $("#detailTable"), lineChart = $("#lineChart"), controller = {
		init : function() {
			// controller.reloadList();
	
			// -------------
			// - LINE CHART -
			// --------------
			lineChart.highcharts({
				title : {
					text : ''
				// center
				},
				xAxis : {
					categories : [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
							'Jul', 'Aug', 'Sep', 'Oct'/*, 'Nov', 'Dec'*/]
				},
				yAxis : {
					title : {
						text : 'Sales'
					},
					plotLines : [ {
						value : 0,
						width : 1,
						color : '#808080'
					} ]
				},
				tooltip : {
					valueSuffix : ''
				},
				legend : {
					layout : 'vertical',
					align : 'right',
					verticalAlign : 'middle',
					borderWidth : 0
				},
				credits: {
					enabled: false
				},
				series : [
						{
							name : '2015',
							data : [ 4723, 2934, 9182, 3223, 2912, 7732, 2345, 0, 6936, 4674 ]
						},
						{
							name : '2016',
							data: [ 5480, 3246, 8290, 5006, 2865, 7379, 4874, 3836, 7325, 5108 ]
						}]
			});
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
