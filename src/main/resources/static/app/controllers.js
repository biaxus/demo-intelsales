(function(angular) {
  var AppController = function($scope, Enterprise) {
	  Enterprise.query(function(response) {
      $scope.enterprises = response ? response : [];
    });
    
    $scope.addEnterprise = function(description) {
      new Enterprise({
        description: description
      }).$save(function(enterprise) {
        $scope.enterprises.push(enterprise);
      });
      $scope.newEnterprise = "";
    };
    
    $scope.updateEnterprise = function(enterprise) {
    	enterprise.$update();
    };
    
    $scope.deleteEnterprise = function(enterprise) {
    	enterprise.$remove(function() {
        $scope.enterprises.splice($scope.enterprises.indexOf(enterprise), 1);
      });
    };
  };
  
  AppController.$inject = ['$scope', 'Enterprise'];
  angular.module("myApp.controllers").controller("AppController", AppController);
}(angular));