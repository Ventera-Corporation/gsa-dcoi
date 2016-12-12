(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('DashboardController', DashboardController);
	
	DashboardController.$inject = ['DashboardService', '$location', '$filter', 'dashboardData'];
	
	function DashboardController(DashboardService, $location, $filter, dashboardData){
		var dc = this;
		dc.dashboardData = dashboardData;
		dc.initDashboardData = initDashboardData;
		dc.isAlreadyInProgressQuarter = isAlreadyInProgressQuarter;
		dc.addNewQuarter = addNewQuarter;
		dc.viewQuarter = viewQuarter;
		
		function initDashboardData() {
			DashboardService.initDashboard().then(function (data){
				dc.dashboardData = data.dashboardData;
			});
		}
		
		function isAlreadyInProgressQuarter(){
			return $filter('filter')(dc.dashboardData.quarters, {'quarterInProgressFlag':true}).length;
		}
		
		function addNewQuarter() {
			$location.path('/createQuarter');
		}
		
		function viewQuarter(quarterId) {
			$location.path('/viewQuarter/' + quarterId);
		}
	}
})();
