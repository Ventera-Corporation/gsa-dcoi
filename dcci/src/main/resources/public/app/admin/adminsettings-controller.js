(function() {
	'use strict';

	angular.module('dcoiApp').controller('AdminSettingsController', AdminSettingsController);

	AdminSettingsController.$inject = ['initData'];

	function AdminSettingsController(initData) {
		var asc = this;
		asc.allUsers = initData;
	}
})();
