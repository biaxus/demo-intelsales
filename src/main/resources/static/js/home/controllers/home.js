define([ 'angular' ], function(angular) {
	var HomeController = function($scope, $http, $location) {
		$scope.logout = $location.search().logout;
		$scope.error = $location.search().error;
		$scope.error |= $location.search()['404'];
		$scope.error |= $location.search()['500'];
		$http.get('/rest/enterprise').success(function(data) {
			$scope.greeting = data;
		});
	};

	HomeController.$inject = [ '$scope', '$http', "$location" ];
	angular.module("myApp.controllers").controller("HomeController", HomeController);
});