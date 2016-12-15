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
		dcc.selectedCloudRegion = selectedCloudRegion;
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
		
		function selectedCloudRegion(regionId){
			for(var idx=0; idx < dcc.regionRefValueList.length; idx++){
				if(dcc.regionRefValueList[idx].id === regionId){
					return dcc.regionRefValueList[idx].code === 'r0';
				}
			}
			return false;
		}
		
		function add() {
			$uibModalInstance.close(dcc.dataCenter);
		}
	}
})();
