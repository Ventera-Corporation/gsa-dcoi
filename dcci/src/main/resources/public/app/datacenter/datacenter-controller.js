(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('DataCenterController', DataCenterController);
	
	DataCenterController.$inject = ['QuarterService', '$uibModalInstance', 'dataCenterData'];
	
	function DataCenterController(QuarterService, $uibModalInstance, dataCenterData){
		var dcc = this;
		dcc.dataCenter = dataCenterData;
		dcc.cancel = cancel;
		dcc.initDataCenterData = initDataCenterData;
		dcc.add = add;
		
		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
		
		function initDataCenterData() {
			QuarterService.initDataCenter().then(function (data){
				dcc.dataCenter = data.dataCenterData;
			});
		}
		
		function add() {
			$uibModalInstance.close(dcc.dataCenter);
		}
	}
})();
