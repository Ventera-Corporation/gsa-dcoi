(function(){
	'use strict';
	angular.module('dcoiApp').config(config);
	
	config.$inject = ['$routeProvider'];
	
	function config($routeProvider){
		$routeProvider
			.when('/createQuarter', {
				templateUrl: 'app/quarter/quarter.html',
				controller: 'QuarterController',
				controllerAs: 'qc',
				resolve: {
					quarterData: function(QuarterService){
//						return QuarterService.initQuarter().then(function (data){
//							return data.quarterData;
//						});
						return {
							fiscalQuarterReport: {
						    	fiscalQuarter: 'Q3',
								fiscalYear: '2016',
							},
							regions: [
								{
									name: 'New England',
									code: 'newEngland',
									dataCenters: [
										{
											name: 'Data Center 1 Name',
											id: 'Data Center 1 ID',
											city: 'Data Center 1 City',
											stateName: 'Data Center 1 State',
											components: [
												{
													name: 'PBS',
													generalInfo: {},
													status: {},
													facilityInfo: {},
													serverInfo: {}
												},
												{
													name: 'FAS',
													generalInfo: {},
													status: {},
													facilityInfo: {},
													serverInfo: {}
												}
											],
										},
										{
											name: 'Data Center 2 Name',
											id: 'Data Center 2 ID',
											city: 'Data Center 2 City',
											stateName: 'Data Center 2 State',
											components: [
												{
													name: 'PBS',
													generalInfo: {},
													status: {},
													facilityInfo: {},
													serverInfo: {}
												},
												{
													name: 'FAS',
													generalInfo: {},
													status: {},
													facilityInfo: {},
													serverInfo: {}
												}
											],
										}
									],
								},
								{
									name: 'Northeast and Caribbean',
									code: 'northeastAndCaribbean',
									dataCenters: [
										{
											name: 'Data Center 3 Name',
											id: 'Data Center 3 ID',
											city: 'Data Center 3 City',
											stateName: 'Data Center 3 State',
											components: [
												{
													name: 'PBS',
													generalInfo: {},
													status: {},
													facilityInfo: {},
													serverInfo: {}
												},
												{
													name: 'FAS',
													generalInfo: {},
													status: {},
													facilityInfo: {},
													serverInfo: {}
												},
												{
													name: 'OICO',
													generalInfo: {},
													status: {},
													facilityInfo: {},
													serverInfo: {}
												}
											],
										},
										{
											name: 'Data Center 4 Name',
											id: 'Data Center 4 ID',
											city: 'Data Center 4 City',
											stateName: 'Data Center 4 State',
											components: [
												{
													name: 'PBS',
													generalInfo: {},
													status: {},
													facilityInfo: {},
													serverInfo: {}
												},
												{
													name: 'FAS',
													generalInfo: {},
													status: {},
													facilityInfo: {},
													serverInfo: {}
												}
											],
										}
									],
								},
								{
									name: "Mid-Atlantic",
									code: 'midAtlantic',
									dataCenters: {},
								},
								{
									name: "Southeast Sunbelt",
									code: 'southeastSunbelt',
									dataCenters: {},
								},
								{
									name: "Great Lakes",
									code: 'greatLakes',
									dataCenters: {},
								},
								{
									name: "Heartland",
									code: 'heartland',
									dataCenters: {},
								},
								{
									name: "Greater Southwest",
									code: 'greaterSouthwest',
									dataCenters: {},
								},
								{
									name: "Rocky Mountain",
									code: 'rockyMountain',
									dataCenters: {},
								},
								{
									name: "Pacific Rim",
									code: 'pacificRim',
									dataCenters: {},
								},
								{
									name: "Northwest/Arctic",
									code: 'northwestArctic',
									dataCenters: {},
								},
								{
									name: "National Capital",
									code: 'nationalCapital',
									dataCenters: {},
								},
								{
									name: "Cloud",
									code: 'cloud',
									dataCenters: {},
								}
							]
						};
					}
				}
			});
	}
})();