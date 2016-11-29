(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('DashboardController', DashboardController);
	
	DashboardController.$inject = ['DashboardService', '$location', '$filter', '$uibModal', 'dashboardData'];
	
	function DashboardController(DashboardService, $location, $filter, $uibModal, dashboardData){
		var dc = this;
		dc.dashboardData = dashboardData;
		dc.initDashboardData = initDashboardData;
		dc.isAlreadyInProgressQuarter = isAlreadyInProgressQuarter;
		dc.addNewQuarter = addNewQuarter;
		dc.viewQuarter = viewQuarter;
		dc.viewMoreQuarters = viewMoreQuarters;
		
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
//			DashboardService.viewQuarter(quarterId).then(function (data){
//				dc.quarterData = data.quarterData;
//			});
		}
		
		function viewMoreQuarters() {
			
		}
	}
})();
