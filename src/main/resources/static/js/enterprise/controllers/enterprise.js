(function(angular, $) {
	var EnterpriseController = function($scope, $state, $http) {
		var submit = function(data) {
			$http.post('/fn/enterprise', data).then(function(succesResponse) {
				$('#form-container').html(succesResponse.data);
			}, function(failResponse) {
				
			});
		};
		$scope.addEnterprise = function() {
			if ($scope.enterpriseForm.$valid)
				submit($scope.enterprise);
		};
		$scope.descriptionInvalid = function() {
			return enterpriseForm.description.$invalid
					&& !enterpriseForm.description.$pristine;
		};
	};
	EnterpriseController.$inject = [ '$scope', '$state', '$http' ];
	angular.module("abm.controllers").controller("EnterpriseController",
			EnterpriseController);
})(angular, jQuery);