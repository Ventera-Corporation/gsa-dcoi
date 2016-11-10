(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('QuarterController', QuarterController);
	
	QuarterController.$inject = ['QuarterService', '$uibModal', '$filter', 'initQuarterData'];
	
	function QuarterController(QuarterService, $uibModal, $filter, initQuarterData){
		var qc = this;
		qc.tempData = {};
		qc.tempData.displayedRegionIdx = initQuarterData.defaultDisplayedRegionIdx;
		qc.quarterData = initQuarterData.quarterData;
		qc.createQuarter = createQuarter;
		qc.saveQuarter = saveQuarter;
		qc.submitQuarter = submitQuarter;
		qc.exportQuarter = exportQuarter;
		qc.addNewDataCenter = addNewDataCenter;
		qc.removeDataCenter = removeDataCenter;
		qc.initComponentTab = initComponentTab;
		qc.viewAudit = viewAudit;
		qc.validateCategory = validateCategory;
		
		function createQuarter(){
			QuarterService.createQuarter(qc.quarterData).then(function (data){
				if(data.error){
					//show errors
					qc.tempData.errorData = data;
				} else {
					//show success message
					qc.tempData.successData = data;
				}
			});
		}
		
		function saveQuarter(){
			
		}
		
		function submitQuarter(){
			
		}
		
		function exportQuarter(){
			
		}
		
		function addNewDataCenter(){
			var modalInstance = $uibModal.open({
			    animation: true,
			    templateUrl: 'app/datacenter/datacenter.html',
			    controller: 'DataCenterController',
			    controllerAs: 'dcc',
			    backdrop: 'static',
			    resolve: {
					initDataCenterData: function(){
						return QuarterService.initDataCenter();
					}
				}
			});
			modalInstance.result.then(function (dataCenterDto) {
				if(dataCenterDto !== 'cancel'){
					var region = $filter('filter')(qc.quarterData.regions, {"name":dataCenterDto.region})[0];
					region.dataCenters.push(dataCenterDto);
				}
			});
		}
		
		function removeDataCenter(dataCenterID, regionIdx, dataCenterIdx){
			QuarterService.removeDataCenter(dataCenterID).then(function (data){
				if(!data.error){
					qc.quarterData.regions[regionIdx].dataCenters.splice(dataCenterIdx, 1);
				}
			});
		}
		
		function initComponentTab(){
			
		}
		
		function viewAudit(){
			
		}
		
		function validateCategory(){
			
		}
	}
})();
