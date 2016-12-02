(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('QuarterController', QuarterController);
	
	QuarterController.$inject = ['QuarterService', '$uibModal', '$filter', 'quarterData'];
	
	function QuarterController(QuarterService, $uibModal, $filter, quarterData){
		var qc = this;
		qc.tempData = {};
		qc.tempData.editMode = false;
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
		qc.tempData.wasInEditMode = {
			dataCenterNames:[],
			dataCenterIds:[]
		};
		qc.quarterData = quarterData;
		qc.initQuarterData = initQuarterData;
		qc.initDefaultSelected = initDefaultSelected;
		qc.initDefaultPanelExpanded = initDefaultPanelExpanded;
		qc.editQuarter = editQuarter;
		qc.createQuarter = createQuarter;
		qc.saveQuarter = saveQuarter;
		qc.addNewDataCenterModal = addNewDataCenterModal;
		qc.addNewDataCenterFromModal = addNewDataCenterFromModal;
		
		function initQuarterData(){
			 QuarterService.initQuarter().then(function (data){
				 qc.quarterData = data.quarterData;
			 });
		}
		
		function initDefaultSelected(region, regionIdx){
			if(qc.tempData.selected.regionIdx === undefined && region.dataCenters.length) {
				qc.tempData.selected.regionIdx = regionIdx;
				qc.tempData.selected.dataCenterName = region.dataCenters[0].dataCenterName;
				qc.tempData.selected.expandCollapseRegions[region.code] = true;
			}
		}
		
		function initDefaultPanelExpanded(dataCenter){
			//only push a new panel if there isn't data for it already
			if(!qc.tempData.selected.expandCollapsePanels[dataCenter.dataCenterId]){
				var panel = {
					expanded: true,
					activeFieldOfficeTabIdx: 0,
					generalInfo: true,
					status: true,
					fieldOffices: []
				};
				var categories = {
					facilityInfo: true,
					serverInfo: true
				};
				angular.forEach(dataCenter.fieldOffices, function (){
					panel.fieldOffices.push(angular.copy(categories));
				});
				qc.tempData.selected.expandCollapsePanels[dataCenter.dataCenterId] = panel;
			}
			//keep track of which panels were visited in editMode so we can save later but don't repeat
			if(qc.tempData.editMode){
				if(qc.tempData.wasInEditMode.dataCenterNames.indexOf(dataCenter.dataCenterName) === -1){
					qc.tempData.wasInEditMode.dataCenterNames.push(dataCenter.dataCenterName);
				}
				if(qc.tempData.wasInEditMode.dataCenterIds.indexOf(dataCenter.dataCenterId) === -1){
					qc.tempData.wasInEditMode.dataCenterIds.push(dataCenter.dataCenterId);
				}
			}
		}
		
		function editQuarter(){
			qc.tempData.editMode = true;
			//need to keep track of which panels were visited where we started in editMode
			qc.tempData.wasInEditMode.dataCenterNames.push(qc.tempData.selected.dataCenterName);
			var currentlyDisplayedDataCenters = $filter('filter')(qc.quarterData.regions[qc.tempData.selected.regionIdx].dataCenters, 
					{'dataCenterName':qc.tempData.selected.dataCenterName});
			angular.forEach(currentlyDisplayedDataCenters, function(dataCenter){
				qc.tempData.wasInEditMode.dataCenterIds.push(dataCenter.dataCenterId);
			});
		}
		
		function createQuarter(){
			QuarterService.createQuarter(qc.quarterData.dueDate).then(function (data){
				if(data.error){
					//show errors
					qc.tempData.errorData = data;
				} else {
					//show success message
					qc.tempData.successData = data.successData;
				}
			});
		}
		
		function saveQuarter(){
			var editedDataCenters = [];
			angular.forEach(qc.tempData.wasInEditMode.dataCenterIds, function(dataCenterIdInEditMode){
				angular.forEach(qc.quarterData.regions, function(region){
					var foundDataCenter = $filter('filter')(region.dataCenters, {'dataCenterId':dataCenterIdInEditMode})[0];
					editedDataCenters.push(foundDataCenter);
				});
			});
//			QuarterService.saveQuarter(editedDataCenters).then(function (data){
//				if(data.error){
//					//show errors
//					qc.tempData.errorData = data;
//				} else {
//					//show success message
//					qc.tempData.successData = data.successData;
					//reset all of the edited panels
					qc.tempData.wasInEditMode.dataCenterNames = [];
					qc.tempData.wasInEditMode.dataCenterIds = [];
					qc.tempData.editMode = false;
//				}
//			});
		}
		
		function addNewDataCenterModal(){
			var modalInstance = $uibModal.open({
			    animation: true,
			    templateUrl: 'app/datacenter/datacenter.html',
			    controller: 'DataCenterController',
			    controllerAs: 'dcc',
			    backdrop: 'static',
			    resolve: {
					dataCenterData: function(){
//						return QuarterService.initQuarter().then(function (data){
//							return data.dataCenterData;
//						});
						return {
							dataCenterId: '',
							dataCenterName: '',
							dcoiDataCenterId: '',
							regionId: '',
							city: '',
							stateName: '',
							generalInfo: {},
							status: {},
							fieldOffices: [
								{
									name: 'PBS',
									facilityInfo: {},
									serverInfo: {}
								},
								{
									name: 'FAS',
									facilityInfo: {},
									serverInfo: {}
								},
								{
									name: 'OCIO',
									facilityInfo: {},
									serverInfo: {}
								}
							]
						};
					}
				}
			});
			var dataCenterData = {};//fixes browser console error that dataCenterData is not defined
			modalInstance.result.then(function(dataCenterData){
				if(dataCenterData !== 'cancel'){
					addNewDataCenterFromModal(dataCenterData);
				}
			});
		}
		
		function addDataCenterToRegion(dataCenterData){
			for(var i = 0; i < qc.quarterData.regions.length; i++){
				if(qc.quarterData.regions[i].regionId == dataCenterData.regionId){
					qc.quarterData.regions[i].dataCenters.push(dataCenterData);
				}
			}
		}
		
		function addNewDataCenterFromModal(dataCenterData){
//			QuarterService.addDataCenter(dataCenterData).then(function (data){
//				if(data.error){
//					//show errors
//					qc.tempData.errorData = data;
//				} else {
//					//show success message
//					qc.tempData.successData = data.successData;
//					addDataCenterToRegion(dataCenterData);
//				}
//			});
			addDataCenterToRegion(dataCenterData);
		}
	}
})();
