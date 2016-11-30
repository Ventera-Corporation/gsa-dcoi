(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('DashboardController', DashboardController);
	
	DashboardController.$inject = ['DashboardService', '$scope', '$location', '$filter', '$uibModal', 'dashboardData'];
	
	function DashboardController(DashboardService, $scope, $location, $filter, $uibModal, dashboardData){
		var dc = this;
		dc.dashboardData = dashboardData;
		dc.initDashboardData = initDashboardData;
		dc.isAlreadyInProgressQuarter = isAlreadyInProgressQuarter;
		dc.addNewQuarter = addNewQuarter;
		dc.viewQuarter = viewQuarter;
		dc.cancel = cancel;
		dc.loadMoreQuarters = loadMoreQuarters;
		
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
		
		var modalInstance = null;
		function viewQuarter(quarterId) {
//			DashboardService.viewQuarter(quarterId).then(function (data){
//				dc.quarterData = data.quarterData;
//			});
			modalInstance = $uibModal.open({
			    animation: true,
			    scope: $scope,
			    templateUrl: 'app/dashboard/viewquarter.html',
			    size: 'lg',
			    backdrop: 'static'
			});
		}
		
	    function cancel() {
	        modalInstance.dismiss('cancel');
	    }
		
		function loadMoreQuarters() {
			
		}
	}
})();
