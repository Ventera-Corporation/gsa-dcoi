(function(){
	'use strict';
	
	angular.module('dcoiApp').factory('DashboardService', DashboardService);
	
	DashboardService.$inject = ['$http'];
	
	function DashboardService($http){
	    return {
			initDashboard : function() {
				return $http({
					url: '/dashboard/init',
					method: 'GET'
				}).then(returnData);
			}
	    };
	    
	    function returnData(responseObj){
	    	return responseObj.data;
	    }
	}
})();
