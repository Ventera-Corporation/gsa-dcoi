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
									regionId: 0,
									dataCenters: [
										{
											dataCenterName: 'Data Center 1 Name',
											dataCenterId: 'Data Center 1 ID',
											dcoiDataCenterId: 'Data Center 1 DCOI ID',
											city: 'Data Center 1 City',
											stateName: 'Data Center 1 State',
											fieldOffices: [
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
											dataCenterName: 'Data Center 2 Name',
											dataCenterId: 'Data Center 2 ID',
											dcoiDataCenterId: 'Data Center 2 DCOI ID',
											city: 'Data Center 2 City',
											stateName: 'Data Center 2 State',
											fieldOffices: [
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
									regionId: 1,
									dataCenters: [
										{
											dataCenterName: 'Data Center 4 Name',
											dataCenterId: 'Data Center 4 ID',
											dcoiDataCenterId: 'Data Center 4 DCOI ID',
											city: 'Data Center 4 City',
											stateName: 'Data Center 4 State',
											fieldOffices: [
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
													name: 'OCIO',
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
									regionId: 2,
									dataCenters: [],
								},
								{
									name: "Southeast Sunbelt",
									code: 'southeastSunbelt',
									regionId: 3,
									dataCenters: [],
								},
								{
									name: "Great Lakes",
									code: 'greatLakes',
									regionId: 4,
									dataCenters: [],
								},
								{
									name: "Heartland",
									code: 'heartland',
									regionId: 5,
									dataCenters: [],
								},
								{
									name: "Greater Southwest",
									code: 'greaterSouthwest',
									regionId: 6,
									dataCenters: [],
								},
								{
									name: "Rocky Mountain",
									code: 'rockyMountain',
									regionId: 7,
									dataCenters: [],
								},
								{
									name: "Pacific Rim",
									code: 'pacificRim',
									regionId: 8,
									dataCenters: [],
								},
								{
									name: "Northwest/Arctic",
									code: 'northwestArctic',
									regionId: 9,
									dataCenters: [],
								},
								{
									name: "National Capital",
									code: 'nationalCapital',
									regionId: 10,
									dataCenters: [],
								},
								{
									name: "Cloud",
									code: 'cloud',
									regionId: 11,
									dataCenters: [],
								}
							]
						};
					}
				}
			});
	}
})();