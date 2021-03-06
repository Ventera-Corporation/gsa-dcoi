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
				loginRequired : false,
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
				dashboardData : function(DashboardService) {
					return DashboardService.initDashboard().then(function (data){
						return data.dashboardData;
					});
				}
			}
		}).when('/createQuarter', {
			templateUrl : 'app/quarter/quarter.html',
			controller : 'QuarterController',
			controllerAs : 'qc',
			resolve : {
				initData : function(QuarterService) {
					return QuarterService.initQuarter().then(function(data) {
						return data;
					});
				}
			}
		}).when('/viewQuarter/:quarterId/:defaultDataCenterId?', {
			templateUrl : 'app/quarter/quarter.html',
			controller : 'QuarterController',
			controllerAs : 'qc',
			resolve : {
				initData : function(QuarterService, $route){
					return QuarterService.viewQuarter($route.current.params.quarterId).then(function(data) {
						return data;
					});
				}
			}
		}).when('/search/searchResults', {
			templateUrl : 'app/search/search.html',
			controller : 'SearchController',
			controllerAs : 'sc',
			resolve : {}
		})
		.when('/settings', {
			templateUrl : 'app/admin/admin-settings.html',
			controller : 'AdminSettingsController',
			controllerAs : 'asc',
			resolve : {
				initData : function(AdminService){
					return AdminService.initAdmin().then(function(data) {
						return data;
					});
				}
			}
		})
		.when('/metrics', {
			templateUrl : 'app/admin/admin-metrics.html',
			controller : 'AdminMetricsController',
			controllerAs : 'amc',
			resolve : {
				initData : function(AdminService){
					return AdminService.getMetrics().then(function(data) {
						return data;
					});
				}
			}
		})
		.when('/cost', {
			templateUrl : 'app/admin/admin-cost.html',
			controller : 'AdminCostController',
			controllerAs : 'acc',
			resolve : {
				initData : function(AdminService){
					return AdminService.getCostCalculationInformation().then(function(data) {
						return data;
					});
				}
			}
		});
	}
})();
