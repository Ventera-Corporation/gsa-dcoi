(function(){
	'use strict';
	
	angular.module('dcoiApp').factory('QuarterService', QuarterService);
	
	QuarterService.$inject = ['$http'];
	
	function QuarterService($http){
	    return {
			initQuarter : function() {
				return $http({
					url: '/quarter/init',
					method: 'GET'
				}).then(returnData);
			},
			createQuarter : function(dueDate) {
				return $http({
					url: '/quarter/create',
					method: 'POST',
					data: dueDate
				}).then(returnData);
			},
			saveQuarter : function(editedDataCenters) {
				return $http({
					url: '/quarter/save',
					method: 'POST',
					data: editedDataCenters
				}).then(returnData);
			},
			viewQuarter : function(quarterId) {
				return $http({
					url: '/quarter/view',
					method: 'GET',
					params: {quarterId: quarterId}
				}).then(returnData);
			},
			addDataCenter : function(dataCenterData) {
				return $http({
					url: '/datacenter/add',
					method: 'POST',
					data:  dataCenterData
				}).then(returnData);
			},
			completeQuarter : function() {
				return $http({
					url: '/quarter/complete',
					method: 'POST'
				}).then(returnData);
			},
			exportQuarter : function(quarterId) {
				return $http({
					url: '/quarter/export',
					method: 'POST',
					data: quarterId,
					responseType: "arraybuffer"
				}).then(returnData);
			}
	    };
	    
	    function returnData(responseObj){
	    	return responseObj.data;
	    }
	}
})();
