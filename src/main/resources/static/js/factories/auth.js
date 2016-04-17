define([ 'angular' ], function(angular) {
	var AuthFactory = function($http) {
		var auth = {
			authenticate : function() {
				return $http.get('/rest/user');
			}
		};
		return auth;
	};
	AuthFactory.$inject = [ '$http' ];
	angular.module("myApp.factories").factory("auth", AuthFactory);
});