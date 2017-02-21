(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('DashboardController', DashboardController);
	
	DashboardController.$inject = ['DashboardService', '$location', '$filter', 'dashboardData'];
	
	function DashboardController(DashboardService, $location, $filter, dashboardData){
		var dc = this;
		dc.tempData = {};
		if($location.search().successData){
			dc.tempData.successData = JSON.parse(decodeURIComponent($location.search().successData));
		} else if($location.search().errorData){
			dc.tempData.errorData = JSON.parse(decodeURIComponent($location.search().errorData));
		}
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
