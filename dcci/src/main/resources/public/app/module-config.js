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
				quarterData : function(QuarterService) {
					return QuarterService.initQuarter().then(function(data) {
						return data.quarterData;
					});
				}
			}
		}).when('/viewQuarter/:quarterId', {
			templateUrl : 'app/quarter/quarter.html',
			controller : 'QuarterController',
			controllerAs : 'qc',
			resolve : {
				quarterData : function(QuarterService, $route){
					return QuarterService.viewQuarter($route.current.params.quarterId).then(function(data) {
						return data.quarterData;
					});
				}
			}
		}).when('/search/searchResults', {
			templateUrl : 'app/search/search.html',
			controller : 'SearchController',
			controllerAs : 'sc',
			resolve : {
				advancedSearchMode : function() {
					return false;
				}
			}
		});
	}
})();
