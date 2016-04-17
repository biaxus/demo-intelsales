require([ 'angular', //
          'jquery', 
          'angular-cookies', 
          'angular-resource', 
          'angular-route',
          'angular-animate',
          'angular-touch',
          'angular-bootstrap' ], function(angular, $) {
	angular.module("myApp.controllers", []);
	angular.module("myApp.factories", []);
	angular.module("myApp.providers", []);
	var dependencies = [ "ngResource", "ngRoute", "ngCookies", "ui.bootstrap","myApp.controllers", "myApp.factories", "myApp.providers" ];
	$.get('/rest/routes').done(function(routes) {
		$.each(routes, function(v, i) {
			console.log(v)
		});
		angular.module("myApp", dependencies)//
		.value("modules", routes)//
		.config(function($routeProvider, $httpProvider, $locationProvider) {
			$.each(routes, function(k, v) {
				if (!v.url || v.url === "")
					return true;
				$routeProvider.when(v.url, {
					templateUrl : v.templateUrl,
					controller : v.controller
				});
			});
			$routeProvider.otherwise('/');
			$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
			$locationProvider.html5Mode(true);
		});
		var _dependencies = [ 'controllers/navigation', 'controllers/root', 'factories/auth' ];
		$.each(routes, function(i, v) {
			if (!v.jsUrl)
				return false;
			_dependencies.push(v.jsUrl);
		});
		require(_dependencies, function() {
			angular.bootstrap(document, [ "myApp" ]);
		});
	}).fail(function(failData) {
		console.log("error");
		console.log("fail: " + failData);
	}).always(function(alwaysData) {
		console.log("finished");
		console.log("always: " + alwaysData);
	});
});