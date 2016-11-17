(function(){
	'use strict';
	
	angular.module('dcoiApp').factory('QuarterService', QuarterService);
	
	QuarterService.$inject = ['$http'];
	
	function QuarterService($http){
	    return {
			initQuarter : function() {
				return $http({
					url: '/newQuarter/init',
					method: 'GET'
				}).then(returnData);
			},
			createQuarter : function(quarterData) {
				return $http({
					url: '/newQuarter/create',
					method: 'POST',
					params: {quarterDto: quarterData}
				}).then(returnData);
			},
			saveQuarter : function(quarterData) {
				return $http({
					url: '/newQuarter/save',
					method: 'POST',
					params: {quarterDto: quarterData}
				}).then(returnData);
			},
			submitQuarter : function(quarterData) {
				return $http({
					url: '',
					method: 'POST',
					params: {quarterData: quarterData}
				});
			},
			exportQuarter : function(quarterData) {
				return $http({
					url: '',
					method: 'POST',
					params: {quarterData: quarterData}
				});
			},
			initDataCenter : function() {
//				return $http({
//					url: '/newDataCenter/init',
//					method: 'GET'
//				});
				return {
					name: '',
					id: '',
					city: '',
					state: '',
					components: [
						{
							name: 'PBS',
							categories: {
								generalInfo: {},
								status: {},
								facilityInfo: {},
								serverInfo: {}
							}
						},
						{
							name: 'FAS',
							categories: {
								generalInfo: {},
								status: {},
								facilityInfo: {},
								serverInfo: {}
							}
						},
						{
							name: 'OCIO',
							categories: {
								generalInfo: {},
								status: {},
								facilityInfo: {},
								serverInfo: {}
							}
						}
					],
				};
			},
			removeDataCenter : function(dataCenterID) {
				return $http({
					url: '',
					method: 'POST',
					params: {dataCenterID: dataCenterID}
				});
			},
			initComponentTab : function(quarterID, componentTabID) {
				return $http({
					url: '',
					method: 'POST',
					params: {quarterID: quarterID,
						componentTabID: componentTabID
					}
				});
			},
			viewAudit : function(categoryID) {
				return $http({
					url: '',
					method: 'POST',
					params: {categoryID: categoryID}
				});
			},
			validateCategory : function(dataCenterID, categoryDto) {
				return $http({
					url: '',
					method: 'POST',
					params: {
						dataCenterID: dataCenterID,
						categoryDto: categoryDto
					}
				});
			}
	    };
	    
	    function returnData(responseObj){
	    	return responseObj.data;
	    }
	}
})();
