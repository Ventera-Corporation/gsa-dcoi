(function(){
	'use strict';
	
	angular.module('dcoiApp').factory('QuarterService', QuarterService);
	
	QuarterService.$inject = ['$http'];
	
	function QuarterService($http){
	    return {
			initQuarter : function() {
//				return $http({
//					url: '/newQuarter/init',
//					method: 'GET'
//				});
				return {
					fiscalQuarter: 'Q3',
					fiscalYear: '2016',
					regions: [
						{
							name: 'Region 1 Name',
							dataCenters: [
								{
									name: 'Data Center 1 Name',
									city: 'Data Center 1 City',
									state: 'Data Center 1 State',
									categories: [
										{
											name: 'Category 1',
											fields: [],
											expanded: true
										},
										{
											name: 'Category 2',
											fields: [],
											expanded: true
										}
									],
									expanded: true
								},
								{
									name: 'Data Center 2 Name',
									city: 'Data Center 2 City',
									state: 'Data Center 2 State',
									categories: [
										{
											name: 'Category 1',
											fields: [],
											expanded: true
										},
										{
											name: 'Category 2',
											fields: [],
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
							dataCenters: [
								{
									name: 'Data Center 3 Name',
									city: 'Data Center 3 City',
									state: 'Data Center 3 State',
									categories: [
										{
											name: 'Category 1',
											fields: [],
											expanded: true
										},
										{
											name: 'Category 2',
											fields: [],
											expanded: true
										}
									],
									expanded: false
								},
								{
									name: 'Data Center 4 Name',
									city: 'Data Center 4 City',
									state: 'Data Center 4 State',
									categories: [
										{
											name: 'Category 1',
											fields: [],
											expanded: true
										},
										{
											name: 'Category 2',
											fields: [],
											expanded: true
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
					url: '/newQuarter/create',
					method: 'POST',
					params: {fiscalQuarterReport: quarterData}
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
			initDataCenter : function() {
//				return $http({
//					url: '/newDataCenter/init',
//					method: 'GET'
//				});
				return {
					name: '',
					city: '',
					state: '',
					region: '',
					categories: [],
					expanded: true
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
	}
})();
