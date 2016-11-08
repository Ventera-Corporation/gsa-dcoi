(function(){
	'use strict';
	
	angular.module('dcciApp').controller('DataCenterController', DataCenterController);
	
	DataCenterController.$inject = ['$uibModalInstance'];
	
	function DataCenterController($uibModalInstance){
		var dcc = this;
		dcc.dataCenterDto = {};
		dcc.cancel = cancel;
		dcc.save = save;
		function cancel() {
			$uibModalInstance.dismiss('cancel');
		};
		function save() {
			$uibModalInstance.close(dcc.dataCenterDto);
		};
	}
})();
