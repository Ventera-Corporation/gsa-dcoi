(function(){
	'use strict';
	
	angular.module('dcciApp').factory('QuarterService', QuarterService);
	
	QuarterService.$inject = ['$http'];
	
	function QuarterService($http){
	    return {
			initQuarter : function(quarterID) {
				return $http({
					url: '',
					method: 'GET',
					params: {quarterID: quarterID}
				}).then(returnData);
			},
			createQuarter : function(quarterData) {
				return $http({
					url: '',
					method: 'POST',
					params: {quarterData: quarterData}
				}).then(returnData);
			},
			saveQuarter : function(quarterData) {
				return $http({
					url: '',
					method: 'POST',
					params: {quarterData: quarterData}
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
			addNewDataCenter : function(dataCenterDto) {
				return $http({
					url: '',
					method: 'POST',
					params: {dataCenterDto: dataCenterDto}
				}).then(returnData);
			},
			initDataCenterList : function(quarterID, regionID) {
				return $http({
					url: '',
					method: 'GET',
					params: {quarterID: quarterID,
						regionID: regionID
					}
				}).then(returnData);
			},
			removeDataCenter : function(dataCenterID) {
				return $http({
					url: '',
					method: 'POST',
					params: {dataCenterID: dataCenterID}
				}).then(returnData);
			},
			initComponentTab : function(quarterID, componentTabID) {
				return $http({
					url: '',
					method: 'POST',
					params: {quarterID: quarterID,
						componentTabID: componentTabID
					}
				}).then(returnData);
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
	    
	    function returnData(data){
	    	return data;
	    }
	}
})();
