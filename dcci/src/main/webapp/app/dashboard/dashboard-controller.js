(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('DashboardController', DashboardController);
	
	DashboardController.$inject = [];
	
	function DashboardController(){
		var dc = this;
		dc.initDashboard = initDashboard;
		dc.addNewQuarter = addNewQuarter;
		dc.viewQuarter = viewQuarter;
		dc.viewMoreQuarters = viewMoreQuarters;
		
		function initDashboard() {
			
		}
		
		function addNewQuarter() {
			
		}
		
		function viewQuarter() {
			
		}
		
		function viewMoreQuarters() {
			
		}
	}
})();
