(function() {
    'use strict';

    angular.module('dcoiApp').controller('CommonController', CommonController);
    CommonController.$inject = ['$rootScope', '$uibModalInstance', 'message'];
    function CommonController($rootScope, $uibModalInstance, message) {
    	var cc = this;
    	cc.ok = ok;
    	cc.cancel = cancel;
    	cc.tempData = {};
    	cc.tempData.message = message;
    	
    	function cancel() {
    		$uibModalInstance.dismiss('cancel');
    	};

    	function ok() {
    		$uibModalInstance.close();
    	};
    }
})();
