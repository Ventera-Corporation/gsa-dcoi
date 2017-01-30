(function(){
	'use strict';
	
	angular.module('dcoiApp').factory('DataCenterService', DataCenterService);
	
	DataCenterService.$inject = ['$http'];
	
	function DataCenterService($http){
	    return {
			initDataCenter : function() {
				return $http({
					url: '/datacenter/init',
					method: 'GET'
				}).then(returnData);
			},
			submitDataCenter : function(dataCenterId) {
				return $http({
					url: '/datacenter/submit',
					method: 'POST',
					data:  dataCenterId
				}).then(returnData);
			},
			rejectDataCenter : function(dataCenterId) {
				return $http({
					url: '/datacenter/reject',
					method: 'POST',
					data:  dataCenterId
				}).then(returnData);
			},
			validateDataCenter : function(dataCenter) {
				return $http({
					url: '/datacenter/validate',
					method: 'POST',
					data: dataCenter
				}).then(returnData);
			}
	    };
	    
	    function returnData(responseObj){
	    	return responseObj.data;
	    }
	}
})();
