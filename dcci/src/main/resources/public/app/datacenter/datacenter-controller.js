(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('DataCenterController', DataCenterController);
	
	DataCenterController.$inject = ['DataCenterService'];
	
	function DataCenterController(DataCenterService){
		var dcc = this;
		dcc.submitDataCenter = submitDataCenter;
		dcc.rejectDataCenter = rejectDataCenter;
		dcc.validateDataCenter = validateDataCenter;
		
		function submitDataCenter(dataCenter){
			DataCenterService.submitDataCenter(dataCenter.dataCenterId).then(function (data){
				if(data.error){
					//show errors
				} else {
					//show success message
					dataCenter.ssoCompleteFlag = 1;
				}
			});
		}
		
		function rejectDataCenter(dataCenter){
			DataCenterService.rejectDataCenter(dataCenter.dataCenterId).then(function (data){
				if(data.error){
					//show errors
				} else {
					//show success message
					dataCenter.ssoCompleteFlag = 0;
					dataCenter.adminCompleteFlag = 1;
				}
			});
		}
		
		function validateDataCenter(dataCenter){
			DataCenterService.validateDataCenter(dataCenter.dataCenterId).then(function (data){
				if(data.error){
					//show errors
				} else {
					//show success message
					dataCenter.ssoCompleteFlag = 1;
					dataCenter.adminCompleteFlag = 1;
				}
			});
		}
	}
})();

