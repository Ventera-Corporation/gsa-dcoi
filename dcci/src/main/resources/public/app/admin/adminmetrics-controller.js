(function() {
	'use strict';

	angular.module('dcoiApp').controller('AdminMetricsController', AdminMetricsController);

	AdminMetricsController.$inject = ['initData'];

	function AdminMetricsController(initData) {
		var amc = this;
		amc.metrics = initData;
	}
})();
