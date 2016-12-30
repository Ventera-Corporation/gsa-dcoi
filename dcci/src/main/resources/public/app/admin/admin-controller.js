(function() {
	'use strict';

	angular.module('dcoiApp').controller('AdminController', AdminController);

	AdminController.$inject = [ 'AdminService', '$location' ];

	function AdminController(AdminService, $location) {
		var admin = this;
		
		if($location.path() === "/settings"){
			initAdminUserData();
		} else if($location.path() ==="/metrics"){
			initAdminMetricsData();
		}

		function initAdminUserData() {
			AdminService.initAdmin().then(function(data) {
				admin.allUsers = data;
			});
		}

		function initAdminMetricsData() {
			AdminService.metrics().then(function(data) {
				admin.metrics = data;
			});
		}
	}
})();
