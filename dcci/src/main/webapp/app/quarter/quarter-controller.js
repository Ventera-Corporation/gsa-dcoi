(function(){
	'use strict';
	
	angular.module('dcciApp').controller('QuarterController', QuarterController);
	
	QuarterController.$inject = ['QuarterService', '$uibModal', '$filter'];
	
	function QuarterController(QuarterService, $uibModal, $filter){
		var qc = this;
		qc.tempData = {};
		qc.quarterData = {};
		qc.initQuarter = initQuarter;
		qc.createQuarter = createQuarter;
		qc.saveQuarter = saveQuarter;
		qc.submitQuarter = submitQuarter;
		qc.exportQuarter = exportQuarter;
		qc.addNewDataCenter = addNewDataCenter;
		qc.removeDataCenter = removeDataCenter;
		qc.initComponentTab = initComponentTab;
		qc.viewAudit = viewAudit;
		qc.validateCategory = validateCategory;

		initQuarter();
		
		function initQuarter(){
			qc.tempData.displayedRegionIdx = 0;
			qc.quarterData = QuarterService.initQuarter();
		}
		
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
			    backdrop: 'static'
			});
			modalInstance.result.then(function (dataCenterDto) {
				if(data !== 'cancel'){
					QuarterService.addNewDataCenter(dataCenterDto).then(function (data){
						if(!data.error){
							var region = $filter('filter')(qc.quarterData.regions, {"name":dataCenterDto.region.name})[0];
							region.dataCenters.push(dataCenterDto);
						}
					});
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
