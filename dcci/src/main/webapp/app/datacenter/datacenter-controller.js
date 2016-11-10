(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('DataCenterController', DataCenterController);
	
	DataCenterController.$inject = ['$uibModalInstance'];
	
	function DataCenterController($uibModalInstance){
		var dcc = this;
		dcc.dataCenterDto = {};
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
