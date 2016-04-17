define([ 'angular' ], function(angular) {
	var NavigationController = function($scope, modules) {
		$scope.test = "asdasda";
		$scope.views = modules;
	};
	NavigationController.$inject = [ '$scope', 'modules' ];
	angular.module("myApp.controllers").controller("NavigationController", NavigationController);
});