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
			createQuarter : function(quarterData) {
				return $http({
					url: '/quarter/create',
					method: 'POST',
					params: {quarterDto: quarterData}
				}).then(returnData);
			},
			saveQuarter : function(quarterData) {
				return $http({
					url: '/quarter/save',
					method: 'POST',
					params: {quarterDto: quarterData}
				}).then(returnData);
			},
			submitQuarter : function(quarterData) {
				return $http({
					url: '',
					method: 'POST',
					params: {quarterData: quarterData}
				}).then(returnData);
			},
			exportQuarter : function(quarterData) {
				return $http({
					url: '',
					method: 'POST',
					params: {quarterData: quarterData}
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
			},
			removeDataCenter : function(dataCenterID) {
				return $http({
					url: '',
					method: 'POST',
					params: {dataCenterID: dataCenterID}
				});
			},
			viewAudit : function(categoryID) {
				return $http({
					url: '',
					method: 'POST',
					params: {categoryID: categoryID}
				}).then(returnData);
			},
			validateCategory : function(dataCenterID, categoryDto) {
				return $http({
					url: '',
					method: 'POST',
					params: {
						dataCenterID: dataCenterID,
						categoryDto: categoryDto
					}
				}).then(returnData);
			}
	    };
	    
	    function returnData(responseObj){
	    	return responseObj.data;
	    }
	}
})();
