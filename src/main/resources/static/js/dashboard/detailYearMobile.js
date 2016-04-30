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
						data : [ 2988,	3001,	3222,	3576,	3855,	3291,	2932,	3543,	3810,	4214,	3845,	3984 ]
					},
					{
						name : '2016',
						data : [ 3483,	3225,	4002,	3834,	3645,	2114,	4728,	4776,	2902,	2561,	4189,	3425]
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
								categories : [ 'May-15', 'Jun-15', 'Jul-15', 'Aug-15',
										'Sep-15', 'Oct-15', 'Nov-15', 'Dec-15', 'Jan-16',
										'Feb-16' , 'Mar-16', 'Apr-16' ]
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
								categories : [ 'May-15', 'Jun-15', 'Jul-15', 'Aug-15',
												'Sep-15', 'Oct-15', 'Nov-15', 'Dec-15', 'Jan-16',
												'Feb-16' , 'Mar-16', 'Apr-16' ],
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
