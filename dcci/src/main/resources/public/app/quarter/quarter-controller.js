(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('QuarterController', QuarterController);
	
	QuarterController.$inject = ['QuarterService', '$uibModal', '$filter', 'initData'];
	
	function QuarterController(QuarterService, $uibModal, $filter, initData){
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
//		angular.foreach(initData.referenceValueLists.regionRefValueList, function(regionRefValue){
//			qc.tempData.selected.expandCollapseRegions[regionRefValue.code] = false;
//		});
		qc.tempData.wasInEditMode = {
			dataCenterNames:[],
			dataCenterIds:[]
		};
		qc.quarterData = initData.quarterData;
		qc.referenceValueLists = initData.referenceValueLists;
		qc.initQuarterData = initQuarterData;
		qc.initDefaultSelected = initDefaultSelected;
		qc.selectDataCenterName = selectDataCenterName;
		qc.initDefaultPanelExpanded = initDefaultPanelExpanded;
		qc.editQuarter = editQuarter;
		qc.createQuarter = createQuarter;
		qc.saveQuarter = saveQuarter;
		qc.getEditedDataCenters = getEditedDataCenters;
		qc.addNewDataCenterModal = addNewDataCenterModal;
		qc.addNewDataCenterFromModal = addNewDataCenterFromModal;
		qc.submitDataCenter = submitDataCenter;
		qc.rejectDataCenter = rejectDataCenter;
		qc.validateDataCenter = validateDataCenter;
		qc.allDataCentersValidated = allDataCentersValidated;
		qc.allDataCentersForDataCenterNameValidated = allDataCentersForDataCenterNameValidated;
		qc.completeQuarter = completeQuarter;
		qc.exportQuarter = exportQuarter;
		
		function initQuarterData(){
			QuarterService.initQuarter().then(function (data){
				qc.quarterData = data.quarterData;
				qc.referenceValueLists = data.referenceValueLists;
			});
		}
		
		function initDefaultSelected(region, regionIdx){
			if(qc.tempData.selected.regionIdx === undefined && region.dataCenters.length) {
				qc.tempData.selected.regionIdx = regionIdx;
				qc.tempData.selected.dataCenterName = region.dataCenters[0].dataCenterName;
				qc.tempData.selected.expandCollapseRegions[region.code] = true;
			}
		}
		
		function selectDataCenterName(regionIdx, dataCenterName){
			qc.tempData.selected.regionIdx = regionIdx;
			qc.tempData.selected.dataCenterName = dataCenterName;
		}
		
		function initDefaultPanelExpanded(dataCenter){
			//only push a new panel if there isn't data for it already
			if(!qc.tempData.selected.expandCollapsePanels[dataCenter.dataCenterId]){
				var panel = {
					expanded: true,
					activeFieldOfficeTabIdx: 0,
					generalInfo: true,
					status: true,
					fieldOffices: [],
					totals: {
						costCalc: true,
						facilityInfo: true,
						serverInfo: true	
					}
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
					$location.path('/dashboard');
				}
			});
		}
		
		function saveQuarter(isAdmin){
			var editedDataCenters = [];
			angular.forEach(qc.tempData.wasInEditMode.dataCenterIds, function(dataCenterIdInEditMode){
				angular.forEach(qc.quarterData.regions, function(region){
					var foundDataCenter = $filter('filter')(region.dataCenters, {'dataCenterId':dataCenterIdInEditMode}, true)[0];
					if(foundDataCenter){
						if(isAdmin){
							foundDataCenter.adminCompleteFlag = false;
						} else {
							foundDataCenter.ssoCompleteFlag = false;
						}
						editedDataCenters.push(foundDataCenter);
					}
				});
			});
			QuarterService.saveQuarter(qc.getEditedDataCenters()).then(function (data){
				if(data.error){
					//show errors
					qc.tempData.errorData = data;
				} else {
					//show success message
					qc.tempData.successData = data.successData;
					//reset all of the edited panels
					qc.tempData.wasInEditMode.dataCenterNames = [];
					qc.tempData.wasInEditMode.dataCenterIds = [];
					qc.tempData.editMode = false;
				}
			});
		}
		
		function getEditedDataCenters(){
			var editedDataCenters = [];
			angular.forEach(qc.tempData.wasInEditMode.dataCenterIds, function(dataCenterIdInEditMode){
				angular.forEach(qc.quarterData.regions, function(region){
					var foundDataCenter = $filter('filter')(region.dataCenters, {'dataCenterId':dataCenterIdInEditMode}, true)[0];
					if(foundDataCenter){
						editedDataCenters.push(foundDataCenter);
					}
				});
			});
			return editedDataCenters;
		}
		
		function addNewDataCenterModal(){
			var modalInstance = $uibModal.open({
			    animation: true,
			    templateUrl: 'app/datacenter/datacenter.html',
			    controller: 'DataCenterController',
			    controllerAs: 'dcc',
			    backdrop: 'static',
			    resolve: {
					initData: function(){
						return QuarterService.initDataCenter().then(function (data){
							return data;
						});
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
					return i;
				}
			}
			return -1;
		}
		
		function addNewDataCenterFromModal(dataCenterData){
			QuarterService.addDataCenter(dataCenterData).then(function (data){
				if(data.error){
					//show errors
					qc.tempData.errorData = data;
				} else {
					//show success message
					qc.tempData.successData = data.successData;
					dataCenterData.dataCenterId = data.successData.dataCenterId;
					var regionIdx = addDataCenterToRegion(dataCenterData);
					selectDataCenterName(regionIdx, dataCenterData.dataCenterName);
					qc.editQuarter();
				}
			});
		}
		
		function submitDataCenter(dataCenterId){
			QuarterService.submitDataCenter(dataCenterId).then(function (data){
				if(data.error){
					//show errors
					qc.tempData.errorData = data;
				} else {
					//show success message
					qc.tempData.successData = data.successData;
				}
			});
		}
		
		function rejectDataCenter(dataCenter){
			QuarterService.rejectDataCenter(dataCenter.dataCenterId).then(function (data){
				if(data.error){
					//show errors
					qc.tempData.errorData = data;
				} else {
					//show success message
					qc.tempData.successData = data.successData;
					dataCenter.ssoCompleteFlag = false;
					dataCenter.adminCompleteFlag = true;
				}
			});
		}
		
		function validateDataCenter(dataCenter){
			QuarterService.validateDataCenter(dataCenter.dataCenterId).then(function (data){
				if(data.error){
					//show errors
					qc.tempData.errorData = data;
				} else {
					//show success message
					qc.tempData.successData = data.successData;
					dataCenter.ssoCompleteFlag = true;
					dataCenter.adminCompleteFlag = true;
				}
			});
		}
		
		function allDataCentersValidated(){
			for(var regionIdx = 0; regionIdx < qc.quarterData.regions.length; regionIdx++){
				if(($filter('filter')(qc.quarterData.regions[regionIdx].dataCenters, {'ssoCompleteFlag':true, 'adminCompleteFlag':true}, true)).length
						!== qc.quarterData.regions[regionIdx].dataCenters.length){
					return false;
				}
			}
			return true;
		}
		
		function allDataCentersForDataCenterNameValidated(regionIdx, dataCenterName){
			var needAttention = ($filter('filter')(qc.quarterData.regions[regionIdx].dataCenters, 
					{'dataCenterName':dataCenterName, 'adminCompleteFlag':false}, true)).length;
			var areRejected = ($filter('filter')(qc.quarterData.regions[regionIdx].dataCenters, 
					{'dataCenterName':dataCenterName, 'ssoCompleteFlag':false}, true)).length;
			return !(needAttention || areRejected);
		}
		
		function completeQuarter(){
			QuarterService.completeQuarter().then(function (data){
				if(data.error){
					//show errors
					qc.tempData.errorData = data;
				} else {
					//show success message
					qc.tempData.successData = data.successData;
				}
			});
		}
		
		function exportQuarter(){
			QuarterService.exportQuarter(qc.quarterData.fiscalQuarterReport.quarterId).then(function (data){
				if(data.error){
					//show errors
					qc.tempData.errorData = data;
				} else {
					var blob = new Blob([data.successData], {type: 'application/xls'});
					window.navigator.msSaveOrOpenBlob(blob, 
							"Quarter" + qc.quarterData.fiscalQuarterReport.fiscalQuarter 
							+ qc.quarterData.fiscalQuarterReport.fiscalYear + ".xls");
				}
			});
		}
	}
})();

