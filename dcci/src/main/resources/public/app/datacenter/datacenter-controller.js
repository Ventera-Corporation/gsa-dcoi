(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('DataCenterController', DataCenterController);
	
	DataCenterController.$inject = ['QuarterService', '$uibModalInstance', 'initData'];
	
	function DataCenterController(QuarterService, $uibModalInstance, initData){
		var dcc = this;
		dcc.dataCenter = initData.dataCenterData;
		dcc.regionRefValueList = initData.regionRefValueList;
		dcc.stateRefValueList = initData.stateRefValueList;
		dcc.cancel = cancel;
		dcc.initDataCenterData = initDataCenterData;
		dcc.add = add;
		
		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
		
		function initDataCenterData() {
			QuarterService.initDataCenter().then(function (data){
				dcc.dataCenter = data.dataCenterData;
				dcc.regionRefValueList = data.regionRefValueList;
				dcc.stateRefValueList = data.stateRefValueList;
			});
		}
		
		function add() {
			$uibModalInstance.close(dcc.dataCenter);
		}
	}
})();
