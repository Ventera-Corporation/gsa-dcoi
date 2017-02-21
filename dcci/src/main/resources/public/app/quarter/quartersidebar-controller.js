(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('QuarterSidebarController', QuarterSidebarController);
	
	QuarterSidebarController.$inject = ['$filter'];
	
	function QuarterSidebarController($filter){
		var qsbc = this;
		qsbc.removeDupes = removeDupes;
		qsbc.displayStatusSymbolForDataCenterName = displayStatusSymbolForDataCenterName;
		qsbc.numDataCentersNeedAttentionForDataCenterName = numDataCentersNeedAttentionForDataCenterName;
		qsbc.numDataCentersRejectedForDataCenterName = numDataCentersRejectedForDataCenterName;
		qsbc.allDataCentersForDataCenterNameValidated = allDataCentersForDataCenterNameValidated;
		
		function removeDupes(dataCenters){
			var uniqueDataCenterNames = [];
			angular.forEach(dataCenters, function(dataCenter){
				if(($filter('filter')(uniqueDataCenterNames, dataCenter.dataCenterName, true)).length === 0){
					uniqueDataCenterNames.push(dataCenter.dataCenterName);
				}
			});
			return uniqueDataCenterNames;
		}
		
		function displayStatusSymbolForDataCenterName(isAdmin, region, wasInEditModeDataCenterNames, dataCenterName){
			//only these symbols are for admin
			if(isAdmin){
				//wasRejected
				if(!qsbc.numDataCentersNeedAttentionForDataCenterName(region, dataCenterName) 
						&& qsbc.numDataCentersRejectedForDataCenterName(region, dataCenterName)){
					return 1;
				}
				//allValidated
				if(qsbc.allDataCentersForDataCenterNameValidated(region, dataCenterName) 
						&& !qsbc.numDataCentersNeedAttentionForDataCenterName(region, dataCenterName) 
						&& !qsbc.numDataCentersRejectedForDataCenterName(region, dataCenterName)){
					return 2;
				}
				//needsAttention
				if(wasInEditModeDataCenterNames[dataCenterName] === undefined
						&& qsbc.numDataCentersNeedAttentionForDataCenterName(region, dataCenterName)){
					return 3;
				}
			}
			//wasEdited
			if(wasInEditModeDataCenterNames[dataCenterName] !== undefined){
				return 4;
			}
			return 0;
		}
		
		function numDataCentersNeedAttentionForDataCenterName(region, dataCenterName){
			return ($filter('filter')(region.dataCenters, 
					{'dataCenterName':dataCenterName, 'ssoCompleteFlag':1, 'adminCompleteFlag':0}, true)).length;
		}
		
		function numDataCentersRejectedForDataCenterName(region, dataCenterName){
			return ($filter('filter')(region.dataCenters, 
					{'dataCenterName':dataCenterName, 'ssoCompleteFlag':0, 'adminCompleteFlag':1}, true)).length;
		}
		
		function allDataCentersForDataCenterNameValidated(region, dataCenterName){
			var numDataCentersWithDataCenterName = ($filter('filter')(region.dataCenters, 
					{'dataCenterName':dataCenterName}, true)).length;
			var numDataCentersAreValidated = ($filter('filter')(region.dataCenters, 
					{'dataCenterName':dataCenterName, 'ssoCompleteFlag':1, 'adminCompleteFlag':1}, true)).length;
			return numDataCentersWithDataCenterName === numDataCentersAreValidated;
		}
	}
})();

