requirejs.config({
	baseUrl : 'app',
	paths : {
		'lib' : '../bower_components',
		'angular' : '../bower_components/angular/angular',
		'jquery' : '../bower_components/jquery/dist/jquery',
		'angular-route' : '../bower_components/angular-route/angular-route',
		'angular-resource' : '../bower_components/angular-resource/angular-resource',
		'angular-cookies' : '../bower_components/angular-cookies/angular-cookies',
		'angular-bootstrap' : '../bower_components/angular-cookies/angular-bootstrap'
	},
	shim : {
		'angular' : {
			exports : 'angular'
		},
		'jquery' : {
			exports : 'jQuery'
		},
		'angular-route' : [ 'angular' ],
		'angular-cookies' : [ 'angular' ],
		'angular-resource' : [ 'angular' ],
		'angular-animate' : [ 'angular' ],
		'angular-touch' : [ 'angular' ],
		'angular-bootstrap' : [ 'angular' ]
	}
});
