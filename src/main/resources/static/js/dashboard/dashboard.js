(function($) {
	var detailTable = $("#detailTable"), chart = $("#chart"), optSalesRep = $("#optSalesRep"), controller = {
		init : function() {
			// controller.reloadList();
			$(".select2").select2();
			this.changeChart('line');

		},
		reloadList : function() {
			var success = function(data, textStatus, jqXHR) {
				detailTable.html(data);
			}, fail = function(response) {
				alert("error");
			};
			$.get('/fn/demobill/list', success).fail(fail);
		},
		changeChart : function(type) {
			var data = [
					{
						name : '2015',
						data : [ 4723, 2934, 9182, 3223, 2912, 7732, 2345, 0,
								6936, 4674 ]
					},
					{
						name : '2016',
						data : [ 5480, 3246, 8290, 5006, 2865, 7379, 4874,
								3836, 7325, 5108 ]
					} ];
			if (type === 'line') {
				// -------------
				// - LINE CHART -
				// --------------
				chart
						.highcharts({
							title : {
								text : ''
							},
						    lang: {
						    	changeChartTitle: "Change chart"
						     },
							xAxis : {
								categories : [ 'Jan', 'Feb', 'Mar', 'Apr',
										'May', 'Jun', 'Jul', 'Aug', 'Sep',
										'Oct'/* , 'Nov', 'Dec' */]
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
							credits : {
								enabled : false
							},
							exporting : {
								buttons : {
									changeChart : {
										text : 'Columns',
										_titleKey: "changeChartTitle",
										align : "right",
										verticalAlign : "bottom",
										x : 0,
										onclick : function() {
											controller.changeChart('column');
										}
									}
								}
							},
							series : data
						});
			} else if (type === 'column') {
				chart
						.highcharts({
							chart : {
								type : 'column'
							},
							lang: {
						    	changeChartTitle: "Change chart"
						     },
							title : {
								text : ''
							},
							xAxis : {
								categories : [ 'Jan', 'Feb', 'Mar', 'Apr',
										'May', 'Jun', 'Jul', 'Aug', 'Sep',
										'Oct'/*
												 * , 'Nov', 'Dec'
												 */
								],
								crosshair : true
							},
							yAxis : {
								min : 0,
								title : {
									text : 'Sales'
								}
							},
							tooltip : {
								headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
								pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
										+ '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
								footerFormat : '</table>',
								shared : true,
								useHTML : true
							},
							plotOptions : {
								column : {
									pointPadding : 0.2,
									borderWidth : 0
								}
							},
							credits : {
								enabled : false
							},
							exporting : {
								buttons : {
									customButton : {
										text : 'Lines',
										align : "right",
										verticalAlign : "bottom",
										_titleKey: "changeChartTitle",
										x : 0,
										onclick : function() {
											controller.changeChart('line');
										}
									}
								}
							},
							series : data
						});
			}
		}
	};
	controller.init();
})(jQuery);
