(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('AddNewDataCenterModalController', AddNewDataCenterModalController);
	
	AddNewDataCenterModalController.$inject = ['DataCenterService', '$uibModalInstance', 'initData'];
	
	function AddNewDataCenterModalController(DataCenterService, $uibModalInstance, initData){
		var andcc = this;
		andcc.dataCenter = initData.dataCenterData;
		andcc.regionRefValueList = initData.regionRefValueList;
		andcc.stateRefValueList = initData.stateRefValueList;
		andcc.cancel = cancel;
		andcc.initDataCenterData = initDataCenterData;
		andcc.selectedCloudRegion = selectedCloudRegion;
		andcc.add = add;
		
		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
		
		function initDataCenterData() {
			DataCenterService.initDataCenter().then(function (data){
				andcc.dataCenter = data.dataCenterData;
				andcc.regionRefValueList = data.regionRefValueList;
				andcc.stateRefValueList = data.stateRefValueList;
			});
		}
		
		function selectedCloudRegion(regionId){
			for(var idx=0; idx < andcc.regionRefValueList.length; idx++){
				if(andcc.regionRefValueList[idx].id === regionId){
					return andcc.regionRefValueList[idx].id === 12;
				}
			}
			return false;
		}
		
		function add() {
			$uibModalInstance.close(andcc.dataCenter);
		}
	}
})();
