(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('QuarterController', QuarterController);
	
	QuarterController.$inject = ['QuarterService', '$uibModal', 'quarterData'];
	
	function QuarterController(QuarterService, $uibModal, quarterData){
		var qc = this;
		qc.tempData = {};
		qc.tempData.selected = {
			//default
			expandCollapseSidebar: true,
			expandCollapseRegions: {
				newEngland: false,
				northeastAndCaribbean: false,
				midAtlantic: false,
				southeastSunbelt: false,
				greatLakes: false,
				heartland: false,
				greaterSouthwest: false,
				rockyMountain: false,
				pacificRim: false,
				northwestArctic: false,
				nationalCapital: false,
				cloud: false
			},
			expandCollapsePanels: {}
		};
		qc.quarterData = quarterData;
		qc.initQuarterData = initQuarterData;
		qc.initDefaultSelected = initDefaultSelected;
		qc.initDefaultPanelExpanded = initDefaultPanelExpanded;
		qc.createQuarter = createQuarter;
		qc.saveQuarter = saveQuarter;
		qc.submitQuarter = submitQuarter;
		qc.exportQuarter = exportQuarter;
		qc.addNewDataCenter = addNewDataCenter;
		qc.removeDataCenter = removeDataCenter;
		qc.initComponentTab = initComponentTab;
		qc.viewAudit = viewAudit;
		qc.validateCategory = validateCategory;
		
		function initQuarterData(){
			 QuarterService.initQuarter().then(function (data){
				 qc.quarterData = data.quarterData;
			 });
		}
		
		function initDefaultSelected(region, regionIdx){
			if(qc.tempData.selected.regionIdx == null && region.dataCenters.length > 0) {
				qc.tempData.selected.regionIdx = regionIdx;
				qc.tempData.selected.dataCenterName = region.dataCenters[0].name;
				qc.tempData.selected.expandCollapseRegions[region.code] = true;
			}
		}
		
		function initDefaultPanelExpanded(dataCenter){
			//only push a new panel if there isn't data for it already
			if(!qc.tempData.selected.expandCollapsePanels[dataCenter.id]){
				var panel = {
					expanded: true,
					activeComponentTabIdx: 0,
					components: []
				};
				var category = {
					generalInfo: true,
					status: true,
					facilityInfo: true,
					serverInfo: true
				};
				angular.forEach(dataCenter.components, function (){
					panel.components.push(angular.copy(category));
				});
				qc.tempData.selected.expandCollapsePanels[dataCenter.id] = panel;
			}
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
			    backdrop: 'static',
			    resolve: {
					initDataCenterData: function(){
						return QuarterService.initDataCenter();
					}
				}
			});
			modalInstance.result.then(function (dataCenterData) {
				if(dataCenterData !== 'cancel'){
					qc.quarterData.regions[dataCenterData.regionProp].dataCenters.push(dataCenterData.dataCenter);
				}
			});
		}
		
		function removeDataCenter(){

		}
		
		function initComponentTab(){
			
		}
		
		function viewAudit(){
			
		}
		
		function validateCategory(){
			
		}
	}
})();
