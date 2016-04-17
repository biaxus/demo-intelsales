define([ 'angular' ], function(angular) {
	var RootController = function($rootScope, $cookies, auth) {
		$rootScope._csrf = $cookies.get("XSRF-TOKEN");
		auth.authenticate().success(function(successData) {
			$rootScope.authenticated = successData.authenticated;
		}).error(function(errorData) {
			$rootScope.authenticated = false;
		});
	};
	RootController.$inject = [ '$rootScope', '$cookies', "auth" ];
	angular.module("myApp.controllers").controller("RootController", RootController);
});