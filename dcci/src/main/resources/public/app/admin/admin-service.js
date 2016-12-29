(function(){
	'use strict';
	
	angular.module('dcoiApp').factory('AdminService', AdminService);
	
	AdminService.$inject = ['$http'];
	
	function AdminService($http){
	    return {
			initAdmin : function() {
				return $http({
					url: '/security/admin',
					method: 'GET',
				}).then(returnData);
			},
			metrics : function(){
				return $http({
					url : '/admin/metrics',
					method : 'GET',
				}).then(returnData);
			}
	    };
	    
	    function returnData(responseObj){
	    	return responseObj.data;
	    }
	}
})();
