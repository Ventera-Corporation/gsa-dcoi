(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('DataCenterController', DataCenterController);
	
	DataCenterController.$inject = ['$uibModalInstance', 'initDataCenterData'];
	
	function DataCenterController($uibModalInstance, initDataCenterData){
		var dcc = this;
		dcc.dataCenterDto = initDataCenterData;
		dcc.cancel = cancel;
		dcc.add = add;
		function cancel() {
			$uibModalInstance.dismiss('cancel');
		};
		function add() {
			$uibModalInstance.close(dcc.dataCenterDto);
		};
	}
})();
