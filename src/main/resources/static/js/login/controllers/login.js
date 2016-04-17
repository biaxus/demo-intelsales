define([ 'angular' ], function(angular) {
	var LoginController = function($scope, $location) {
		$scope.error = $location.search().fail;
		$scope.sessionAuthenticationError = $location.search().sessionAuthenticationError;
		$scope.invalidSession = $location.search().invalidSession;
	};
	LoginController.$inject = [ '$scope', "$location" ];
	angular.module("myApp.controllers").controller("LoginController", LoginController);
});