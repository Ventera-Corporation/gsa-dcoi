(function() {
	'use strict';
	angular.module('dcoiApp').config(config);

	config.$inject = [ '$routeProvider', 'USER_ROLES' ];

	function config($routeProvider, USER_ROLES) {
		$routeProvider.otherwise({
			redirectTo : '/login'
		}).when("/error/:code", {
			templateUrl : "app/common/error.html",
			controller : "ErrorController",
			controllerAs : 'ec',
			access : {
				loginRequired : true,
				authorizedRoles : [ USER_ROLES.all ]
			}
		}).when('/login', {
			templateUrl : 'app/login/login.html',
			controller : 'LoginController',
			controllerAs : 'lc',
			access : {
				loginRequired : false,
				authorizedRoles : [ USER_ROLES.all ]
			}
		}).when('/dashboard', {
			templateUrl : 'app/dashboard/dashboard.html',
			controller : 'DashboardController',
			controllerAs : 'dc',
			resolve : {
				dashboardData : function(QuarterService) {
//					return QuarterService.initDashboard().then(function (data){
//						return data.dashboardData;
//					});
					return {
						years : [ '2016', '2015', '2014' ],
						quarters : [ {
							fiscalQuarter : 'Q4',
							fiscalYear : '2016',
							totalNumDataCenters : 26,
							quarterInProgressFlag : false,
							quarterActiveFlag : false,
							quarterCompleteFlag : false
						}, {
							fiscalQuarter : 'Q3',
							fiscalYear : '2016',
							totalNumDataCenters : 26,
							quarterInProgressFlag : false,
							quarterActiveFlag : true,
							fieldOfficesInProgressFlags : [ 'PBS', 'FAS' ],
							fieldOfficesCompleteFlags : [ 'OCIO' ],
							quarterCompleteFlag : false
						}, {
							fiscalQuarter : 'Q2',
							fiscalYear : '2016',
							totalNumDataCenters : 24,
							quarterInProgressFlag : false,
							quarterActiveFlag : false,
							quarterCompleteFlag : true
						}, {
							fiscalQuarter : 'Q1',
							fiscalYear : '2016',
							totalNumDataCenters : 24,
							quarterInProgressFlag : false,
							quarterActiveFlag : false,
							quarterCompleteFlag : true
						}, {
							fiscalQuarter : 'Q4',
							fiscalYear : '2015',
							totalNumDataCenters : 24,
							quarterInProgressFlag : false,
							quarterActiveFlag : false,
							quarterCompleteFlag : true
						}, {
							fiscalQuarter : 'Q3',
							fiscalYear : '2015',
							totalNumDataCenters : 23,
							quarterInProgressFlag : false,
							quarterActiveFlag : false,
							quarterCompleteFlag : true
						}, {
							fiscalQuarter : 'Q2',
							fiscalYear : '2015',
							totalNumDataCenters : 23,
							quarterInProgressFlag : false,
							quarterActiveFlag : false,
							quarterCompleteFlag : true
						}, {
							fiscalQuarter : 'Q1',
							fiscalYear : '2015',
							totalNumDataCenters : 23,
							quarterInProgressFlag : false,
							quarterActiveFlag : false,
							quarterCompleteFlag : true
						}, {
							fiscalQuarter : 'Q4',
							fiscalYear : '2014',
							totalNumDataCenters : 23,
							quarterInProgressFlag : false,
							quarterActiveFlag : false,
							quarterCompleteFlag : true
						}, {
							fiscalQuarter : 'Q3',
							fiscalYear : '2014',
							totalNumDataCenters : 23,
							quarterInProgressFlag : false,
							quarterActiveFlag : false,
							quarterCompleteFlag : true
						}, {
							fiscalQuarter : 'Q2',
							fiscalYear : '2014',
							totalNumDataCenters : 22,
							quarterInProgressFlag : false,
							quarterActiveFlag : false,
							quarterCompleteFlag : true
						}, {
							fiscalQuarter : 'Q1',
							fiscalYear : '2014',
							totalNumDataCenters : 22,
							quarterInProgressFlag : false,
							quarterActiveFlag : false,
							quarterCompleteFlag : true
						} ]
					};
				}
			}
		}).when('/createQuarter', {
			templateUrl : 'app/quarter/quarter.html',
			controller : 'QuarterController',
			controllerAs : 'qc',
			resolve : {
				quarterData : function(QuarterService) {
					return QuarterService.initQuarter().then(function(data) {
						return data.quarterData;
					});
					/**return {
						fiscalQuarterReport: {
					    	fiscalQuarter: 'Q3',
							fiscalYear: '2016',
							quarterInProgressFlag: true,
							quarterActiveFlag: false
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
					};**/
				}
			}
		}).when('/searchResults', {
			templateUrl : 'app/search/search.html',
			controller : 'SearchController',
			controllerAs : 'sc',
			resolve : {
				advancedSearchMode : function() {
					return false;
				}
			}
		}).when('/queryData', {
			templateUrl : 'app/search/search.html',
			controller : 'SearchController',
			controllerAs : 'sc',
			resolve : {
				advancedSearchMode : function() {
					return true;
				}
			}
		});
	}
})();