(function(){
	'use strict';
	
	angular.module('dcciApp').factory('QuarterService', QuarterService);
	
	QuarterService.$inject = ['$http'];
	
	function QuarterService($http){
	    return {
			initQuarter : function() {
//				return $http({
//					url: '',
//					method: 'GET'
//				});
				return {
					quarterNumber: 'Q3',
					quarterYear: '2016',
					regions: [
						{
							name: 'Region 1 Name',
							id: 'Region 1 ID',
							dataCenters: [
								{
									name: 'Data Center 1 Name',
									id: 'Data Center 1 ID',
									city: 'Data Center 1 City',
									state: 'Data Center 1 State',
									categories: [
										{
											id: 'Category 1',
											expanded: true
										},
										{
											id: 'Category 2',
											expanded: true
										}
									],
									expanded: true
								},
								{
									name: 'Data Center 2 Name',
									id: 'Data Center 2 ID',
									city: 'Data Center 2 City',
									state: 'Data Center 2 State',
									categories: [
										{
											id: 'Category 1',
											expanded: true
										},
										{
											id: 'Category 2',
											expanded: true
										}
									],
									expanded: true
								}
							],
							expanded: true
						},
						{
							name: 'Region 2 Name',
							id: 'Region 2 ID',
							dataCenters: [
								{
									name: 'Data Center 3 Name',
									id: 'Data Center 3 ID',
									city: 'Data Center 3 City',
									state: 'Data Center 3 State',
									categories: [
										{
											id: 'Category 1',
											expanded: false
										},
										{
											id: 'Category 2',
											expanded: false
										}
									],
									expanded: false
								},
								{
									name: 'Data Center 4 Name',
									id: 'Data Center 4 ID',
									city: 'Data Center 4 City',
									state: 'Data Center 4 State',
									categories: [
										{
											id: 'Category 1',
											expanded: false
										},
										{
											id: 'Category 2',
											expanded: false
										}
									],
									expanded: false
								}
							],
							expanded: false
						},
					]
				};
			},
			createQuarter : function(quarterData) {
				return $http({
					url: '',
					method: 'POST',
					params: {quarterData: quarterData}
				});
			},
			saveQuarter : function(quarterData) {
				return $http({
					url: '',
					method: 'POST',
					params: {quarterData: quarterData}
				});
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
			addNewDataCenter : function(dataCenterDto) {
				return $http({
					url: '',
					method: 'POST',
					params: {dataCenterDto: dataCenterDto}
				});
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
	}
})();
