(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('AdminController', AdminController);
	
	AdminController.$inject = ['AdminService', 'usersOrMetrics'];
	
	function AdminController(AdminService, usersOrMetrics){
		var admin = this;
		admin.initAdminData = initAdminData;
		admin.metrics = usersOrMetrics;
		admin.allUsers = usersOrMetrics;
		admin.itemArray = [
		        {id: 1, name: 'first'},
		        {id: 2, name: 'second'},
		        {id: 3, name: 'third'},
		        {id: 4, name: 'fourth'},
		        {id: 5, name: 'fifth'},
		    ];
		admin.selected = { value: admin.itemArray[0] };
		
		function initAdminData() {
			AdminService.initAdmin().then(function (data){
				admin.allUsers = data;
			});
		}
	}
})();
