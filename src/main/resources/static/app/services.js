(function(angular) {
  var EnterpriseFactory = function($resource) {
    return $resource('/enterprise/:id', {
      id: '@id'
    }, {
      update: {
        method: "PUT"
      },
      remove: {
        method: "DELETE"
      }
    });
  };
  
  EnterpriseFactory.$inject = ['$resource'];
  angular.module("myApp.services").factory("Enterprise", EnterpriseFactory);
}(angular));