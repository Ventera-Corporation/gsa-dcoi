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
			var modalInstance = $uibModal.open({
			    animation : true,
			    templateUrl : 'app/common/confirm.html',
			    backdrop : 'static',
			    controller : 'CommonController',
			    controllerAs : 'cc',
			    resolve : {
			    	message: function () {
			    		return 	"By submitting this Data Center, you claim all information entered by every Component to be complete and accurate. "
			    				+ "Any edits made will require this Data Center to be resubmitted. "
			    				+ "Are you sure you want to submit this Data Center?";
			        }
			    }
			});
	     	modalInstance.result.then(function () {
				DataCenterService.submitDataCenter(dataCenter.dataCenterId).then(function (data){
					if(data.error){
						//show errors
						dcc.tempData.errorData = data;
					} else {
						//hide errors
						dcc.tempData.errorData = null;
						//show success message
						dcc.tempData.successData = data.successData;
						dataCenter.ssoCompleteFlag = 1;
					}
				});
			});
		}
		
		function rejectDataCenter(dataCenter){
			var modalInstance = $uibModal.open({
			    animation : true,
			    templateUrl : 'app/common/confirm.html',
			    backdrop : 'static',
			    controller : 'CommonController',
			    controllerAs : 'cc',
			    resolve : {
			    	message: function () {
			    		return 	"By rejecting this Data Center, you will need to contact the assigned SSO to modify the information and resubmit it again. "
			    				+ "Are you sure you want to reject this Data Center?";
			        }
			    }
			});
	     	modalInstance.result.then(function () {
				DataCenterService.rejectDataCenter(dataCenter.dataCenterId).then(function (data){
					if(data.error){
						//show errors
						dcc.tempData.errorData = data;
					} else {
						//hide errors
						dcc.tempData.errorData = null;
						//show success message
						dcc.tempData.successData = data.successData;
						dataCenter.ssoCompleteFlag = 0;
						dataCenter.adminCompleteFlag = 1;
					}
				});
			});
		}
		
		function validateDataCenter(dataCenter){
			var modalInstance = $uibModal.open({
			    animation : true,
			    templateUrl : 'app/common/confirm.html',
			    backdrop : 'static',
			    controller : 'CommonController',
			    controllerAs : 'cc',
			    resolve : {
			    	message: function () {
			    		return 	"By validating this Data Center, you claim all information entered to be complete and accurate. "
	    						+ "Any edits made will require this Data Center to be validated again. "
			    				+ "Are you sure you want to validate this Data Center?";
			        }
			    }
			});
	     	modalInstance.result.then(function () {
				DataCenterService.validateDataCenter(dataCenter.dataCenterId).then(function (data){
					if(data.error){
						//show errors
						dcc.tempData.errorData = data;
					} else {
						//hide errors
						dcc.tempData.errorData = null;
						//show success message
						dcc.tempData.successData = data.successData;
						dataCenter.ssoCompleteFlag = 1;
						dataCenter.adminCompleteFlag = 1;
					}
				});
			});
		}
	}
})();

