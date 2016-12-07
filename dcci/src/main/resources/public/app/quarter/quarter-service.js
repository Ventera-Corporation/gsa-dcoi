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
					params: {dueDate: dueDate}
				}).then(returnData);
			},
			saveQuarter : function(editedDataCenters) {
				return $http({
					url: '/quarter/save',
					method: 'POST',
					params: {dataCenterDtos: editedDataCenters}
				}).then(returnData);
			},
			initDataCenter : function() {
				return $http({
					url: '/datacenter/init',
					method: 'GET'
				}).then(returnData);
			},
			addDataCenter : function(dataCenterData) {
				return $http({
					url: '/datacenter/add',
					method: 'POST',
					params: {dataCenterDto: dataCenterData}
				}).then(returnData);
			}
	    };
	    
	    function returnData(responseObj){
	    	return responseObj.data;
	    }
	}
})();
