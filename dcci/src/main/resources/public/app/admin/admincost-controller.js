(function() {
	'use strict';

	angular.module('dcoiApp').controller('AdminCostController', AdminCostController);

	AdminCostController.$inject = ['initData'];

	function AdminCostController(initData) {
		var acc = this;
		acc.costCalc = initData;
	}
})();
