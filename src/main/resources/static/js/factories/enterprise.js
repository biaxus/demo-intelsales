define([ 'angular' ], function(angular) {
	var EnterpriseFactory = function($resource) {
		return $resource('/enterprise/:id', {
			id : '@id'
		}, {
			update : {
				method : "PUT"
			},
			remove : {
				method : "DELETE"
			}
		});
	};

	EnterpriseFactory.$inject = [ '$resource' ];
	angular.module("myApp.factories").factory("Enterprise", EnterpriseFactory);
});