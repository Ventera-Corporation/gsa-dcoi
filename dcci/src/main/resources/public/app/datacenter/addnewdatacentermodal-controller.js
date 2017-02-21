(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('AddNewDataCenterModalController', AddNewDataCenterModalController);
	
	AddNewDataCenterModalController.$inject = ['DataCenterService', '$uibModalInstance', 'initData'];
	
	function AddNewDataCenterModalController(DataCenterService, $uibModalInstance, initData){
		var andcc = this;
		andcc.CLOUD_REGION_ID = 12;
		andcc.dataCenter = initData.dataCenterData;
		andcc.fieldOffice = initData.fieldOfficeData;
		andcc.tempFieldOffices = [];
		andcc.regionRefValueList = initData.regionRefValueList;
		andcc.stateRefValueList = initData.stateRefValueList;
		andcc.componentRefValueList = initData.componentRefValueList;
		andcc.cancel = cancel;
		andcc.initDataCenterData = initDataCenterData;
		andcc.selectedCloudRegion = selectedCloudRegion;
		andcc.checkUncheckItem = checkUncheckItem;
		andcc.removeNullValues = removeNullValues;
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
					return andcc.regionRefValueList[idx].id === andcc.CLOUD_REGION_ID;
				}
			}
			return false;
		}
		
		function checkUncheckItem(index, fieldOfficeName){
			andcc.tempFieldOffices[index]['fieldOfficeName'] === fieldOfficeName
					? andcc.tempFieldOffices[index]['fieldOfficeName'] = null
					: andcc.tempFieldOffices[index]['fieldOfficeName'] = fieldOfficeName;
		}

		function removeNullValues(fieldOffices){
			var cleanFieldOffices = [];
			angular.forEach(fieldOffices, function(fieldOffice){
				if(fieldOffice.componentId !== null && fieldOffice.fieldOfficeName !== null){
					cleanFieldOffices.push(fieldOffice);
				}
			});
			return cleanFieldOffices;
		}
		
		function add() {
			andcc.dataCenter.fieldOffices = [];
			angular.forEach(andcc.removeNullValues(andcc.tempFieldOffices), function(fieldOffice){
				var newFieldOffice = angular.copy(andcc.fieldOffice);
				newFieldOffice.componentId = fieldOffice.componentId;
				newFieldOffice.fieldOfficeName = fieldOffice.fieldOfficeName;
				andcc.dataCenter.fieldOffices.push(newFieldOffice);
			});
			$uibModalInstance.close(andcc.dataCenter);
		}
	}
})();
