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
			},
			viewQuarter : function(quarterId) {
				return $http({
					url: '/dashboard/view',
					method: 'GET',
					params: {quarterId: quarterId}
				}).then(returnData);
			}
	    };
	    
	    function returnData(responseObj){
	    	return responseObj.data;
	    }
	}
})();
