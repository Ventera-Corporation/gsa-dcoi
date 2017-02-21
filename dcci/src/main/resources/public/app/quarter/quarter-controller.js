(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('QuarterController', QuarterController);
	
	QuarterController.$inject = ['QuarterService', '$uibModal', '$filter', '$routeParams', '$location', 'initData'];
	
	function QuarterController(QuarterService, $uibModal, $filter, $routeParams, $location, initData){
		var qc = this;
		qc.tempData = {};
		qc.tempData.editMode = false;
		qc.tempData.selected = {
			//default
			expandCollapseSidebar: true,
			expandCollapseRegions: {},
			expandCollapsePanels: {}
		};
		angular.forEach(initData.referenceValueLists.regionRefValueList, function(regionRefValue){
			qc.tempData.selected.expandCollapseRegions[regionRefValue.code] = false;
		});
		qc.tempData.wasInEditMode = {
			//names and ids will each have properties that indicate saved for edit symbol
			// ex. {'1R-MA0153':false, '3R-EU9996':true}
			dataCenterNames:{},
			// ex. {168:false, 133:true}
			dataCenterIds:{}
		};
		qc.pastQuarterData = initData.pastQuarterData;
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
		qc.updateEditedDataCentersWithSaveFlag = updateEditedDataCentersWithSaveFlag;
		qc.updateDataCenterIdTotalsTabs = updateDataCenterIdTotalsTabs;
		qc.addNewDataCenterModal = addNewDataCenterModal;
		qc.addNewDataCenterFromModal = addNewDataCenterFromModal;
		qc.allDataCentersValidated = allDataCentersValidated;
		qc.completeQuarter = completeQuarter;
		qc.exportQuarter = exportQuarter;
		
		function initQuarterData(){
			QuarterService.initQuarter().then(function (data){
				qc.quarterData = data.quarterData;
				qc.referenceValueLists = data.referenceValueLists;
			});
		}
		
		function initDefaultSelected(region, regionIdx){
			if(!$routeParams.defaultDataCenterId && qc.tempData.selected.regionIdx === undefined && region.dataCenters.length) {
				qc.tempData.selected.regionIdx = regionIdx;
				qc.tempData.selected.dataCenterName = region.dataCenters[0].dataCenterName;
				qc.tempData.selected.expandCollapseRegions[region.code] = true;
			} else if($routeParams.defaultDataCenterId && qc.tempData.selected.regionIdx === undefined && region.dataCenters.length){
				var foundDataCenter = $filter('filter')(region.dataCenters, {'dataCenterId':$routeParams.defaultDataCenterId}, true)[0];
				if(foundDataCenter){
					qc.tempData.selected.regionIdx = regionIdx;
					qc.tempData.selected.dataCenterName = foundDataCenter.dataCenterName;
					qc.tempData.selected.expandCollapseRegions[region.code] = true;
				}
			}
		}
		
		function selectDataCenterName(regionIdx, dataCenterName){
			qc.tempData.selected.regionIdx = regionIdx;
			qc.tempData.selected.dataCenterName = dataCenterName;
			//remove errors and success message
			qc.tempData.errorData = null;
			qc.tempData.successData = null;
		}
		
		function initDefaultPanelExpanded(dataCenter){
			//only push a new panel if there isn't data for it already
			if(!qc.tempData.selected.expandCollapsePanels[dataCenter.dataCenterId]){
				var panel = {
					expanded: true,
					activeFieldOfficeTabIdx: 0,
					generalInfo: true,
					status: true,
					facilityInfo: true,
					fieldOffices: [],
					totals: {
						serverInfo: true,
						costCalc: true,
						otherCalc: true
					}
				};
				var categories = {
					serverInfo: true
				};
				angular.forEach(dataCenter.fieldOffices, function (){
					panel.fieldOffices.push(angular.copy(categories));
				});
				qc.tempData.selected.expandCollapsePanels[dataCenter.dataCenterId] = panel;
			}
		}
		
		function editQuarter(errorData, successData){
			//update errors and success message
			qc.tempData.errorData = errorData;
			qc.tempData.successData = successData;
			qc.tempData.editMode = true;
			//need to keep track of which panels were edited so set the saved indicator to false
			qc.tempData.wasInEditMode.dataCenterNames[qc.tempData.selected.dataCenterName] = false;
			var currentlyDisplayedDataCenters = $filter('filter')(qc.quarterData.regions[qc.tempData.selected.regionIdx].dataCenters, 
					{'dataCenterName':qc.tempData.selected.dataCenterName}, true);
			angular.forEach(currentlyDisplayedDataCenters, function(dataCenter){
				qc.tempData.wasInEditMode.dataCenterIds[dataCenter.dataCenterId] = false;
			});
		}
		
		function createQuarter(){
			var modalInstance = $uibModal.open({
			    animation : true,
			    templateUrl : 'app/common/confirm.html',
			    backdrop : 'static',
			    controller : 'CommonController',
			    controllerAs : 'cc',
			    resolve : {
			    	message: function () {
			    		return 	"Once created, you will no longer be able to change the Due Date or add a New Data Center. "
			    				+ "Are you sure you want to create this Quarter?";
			        }
			    }
			});
	     	modalInstance.result.then(function () {
				QuarterService.createQuarter(qc.quarterData.fiscalQuarterReport.quarterDueDate).then(function (data){
					if(data.error){
						//show errors
						qc.tempData.errorData = data;
						//hide success message
						qc.tempData.successData = null;
					} else {
						//hide errors
						qc.tempData.errorData = null;
						//show success message
						qc.tempData.successData = data.successData;
						//navigate the admin back to the dashboard with a showing success message
						$location.path('/dashboard').search('successData', encodeURIComponent(JSON.stringify(qc.tempData.successData)));
					}
				});
			});
		}
		
		function saveQuarter(isAdmin){
			var editedDataCenters = qc.getEditedDataCenters(isAdmin);
			QuarterService.saveQuarter(editedDataCenters).then(function (data){
				if(data.error){
					//show errors
					qc.tempData.errorData = data;
					//hide success message
					qc.tempData.successData = null;
				} else {
					//hide errors
					qc.tempData.errorData = null;
					//show success message
					qc.tempData.successData = data.successData;
					//update save flag on the edited panels
					qc.updateEditedDataCentersWithSaveFlag(editedDataCenters);
					//update totals tab on the edited panels
					qc.updateDataCenterIdTotalsTabs(qc.tempData.successData.dataCenterIdTotalsPairs);
					qc.tempData.editMode = false;
				}
			});
		}
		
		function getEditedDataCenters(isAdmin){
			var editedDataCenters = [];
			var editedDataCentersIds = [];
			//first need to get dataCenterIds that have been edited and have false indicating not saved
			for(var dataCenterId in qc.tempData.wasInEditMode.dataCenterIds){
				if(qc.tempData.wasInEditMode.dataCenterIds[dataCenterId] === false){
					editedDataCentersIds.push(parseInt(dataCenterId));
				}
			}
			angular.forEach(editedDataCentersIds, function(dataCenterIdInEditMode){
				angular.forEach(qc.quarterData.regions, function(region){
					var foundDataCenter = $filter('filter')(region.dataCenters, {'dataCenterId':dataCenterIdInEditMode}, true);
					if(foundDataCenter.length){
						if(isAdmin){
							foundDataCenter[0].adminCompleteFlag = 0;
						} else {
							foundDataCenter[0].ssoCompleteFlag = 0;
						}
						editedDataCenters.push(foundDataCenter[0]);
					}
				});
			});
			return editedDataCenters;
		}
		
		function updateEditedDataCentersWithSaveFlag(savedDataCenters){
			angular.forEach(savedDataCenters, function(savedDataCenter){
				for(var dataCenterName in qc.tempData.wasInEditMode.dataCenterNames){
					if(dataCenterName === savedDataCenter.dataCenterName){
						qc.tempData.wasInEditMode.dataCenterNames[dataCenterName] = true;
					}
				}
				for(var dataCenterId in qc.tempData.wasInEditMode.dataCenterIds){
					//need soft compare here for string on the left and integer on the right
					if(dataCenterId == savedDataCenter.dataCenterId){
						qc.tempData.wasInEditMode.dataCenterIds[dataCenterId] = true;
					}
				}
			});
		}
		
		function updateDataCenterIdTotalsTabs(dataCenterIdTotalsPairs){
			//update the totals tab for each datacenterIdTotalsPair
			angular.forEach(dataCenterIdTotalsPairs, function(dataCenterIdTotalsPair){
				angular.forEach(qc.quarterData.regions, function(region){
					var foundDataCenter = $filter('filter')(region.dataCenters, {'dataCenterId':dataCenterIdTotalsPair.dataCenterId}, true)[0];
					if(foundDataCenter){
						foundDataCenter.totals = dataCenterIdTotalsPair.totals;
					}
				});
			});
		}
		
		function addNewDataCenterModal(){
			var modalInstance = $uibModal.open({
			    animation: true,
			    templateUrl: 'app/datacenter/addnewdatacenter-modal.html',
			    controller: 'AddNewDataCenterModalController',
			    controllerAs: 'andcc',
			    backdrop: 'static',
			    resolve: {
					initData: function(DataCenterService){
						return DataCenterService.initDataCenter().then(function (data){
							return data;
						});
					}
				}
			});
			var dataCenterData = {};//fixes browser console error that dataCenterData is not defined
			modalInstance.result.then(function(dataCenterData){
				if(dataCenterData !== 'cancel'){
					qc.addNewDataCenterFromModal(dataCenterData);
				}
			});
		}
		
		function addDataCenterToRegion(dataCenterData){
			for(var i = 0; i < qc.quarterData.regions.length; i++){
				if(qc.quarterData.regions[i].regionId === dataCenterData.regionId){
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
					//hide success message
					qc.tempData.successData = null;
				} else {
					var newdataCenterData = data.dataCenterData;
					var regionIdx = addDataCenterToRegion(newdataCenterData);
					selectDataCenterName(regionIdx, newdataCenterData.dataCenterName);
					//hide errors and show success message
					qc.editQuarter(null, data.successData);
				}
			});
		}
		
		function allDataCentersValidated(){
			for(var regionIdx = 0; regionIdx < qc.quarterData.regions.length; regionIdx++){
				var numDataCentersAreValidated = ($filter('filter')(qc.quarterData.regions[regionIdx].dataCenters, 
						{'ssoCompleteFlag':1, 'adminCompleteFlag':1}, true)).length;
				if(qc.quarterData.regions[regionIdx].dataCenters.length !== numDataCentersAreValidated){
					return false;
				}
			}
			return true;
		}
		
		function completeQuarter(){
			var modalInstance = $uibModal.open({
			    animation : true,
			    templateUrl : 'app/common/confirm.html',
			    backdrop : 'static',
			    controller : 'CommonController',
			    controllerAs : 'cc',
			    resolve : {
			    	message: function () {
			    		return 	"Once completed, you will no longer be able to change any Quarter information. "
			    				+ "Are you sure you want to complete this Quarter?";
			        }
			    }
			});
	     	modalInstance.result.then(function () {
				QuarterService.completeQuarter().then(function (data){
					if(data.error){
						//show errors
						qc.tempData.errorData = data;
						//hide success message
						qc.tempData.successData = null;
					} else {
						//hide errors
						qc.tempData.errorData = null;
						//show success message
						qc.tempData.successData = data.successData;
						//navigate the admin back to the dashboard with a showing success message
						$location.path('/dashboard').search('successData', encodeURIComponent(JSON.stringify(qc.tempData.successData)));
					}
				});
			});
		}
		
		function exportQuarter(){
			QuarterService.exportQuarter(qc.quarterData.fiscalQuarterReport.quarterId).then(function (data){
				if(data.error){
					//show errors
					qc.tempData.errorData = data;
					//hide success message
					qc.tempData.successData = null;
				} else {
					//hide errors
					qc.tempData.errorData = null;
					var file = new Blob([data], {type: 'text/csv;charset=utf-8'});
					var fileName = "Quarter" + qc.quarterData.fiscalQuarterReport.fiscalQuarter 
							+ qc.quarterData.fiscalQuarterReport.fiscalYear + ".csv";
					try {
						window.navigator.msSaveOrOpenBlob(file, fileName);
					} catch(err) {
						var fileURL = URL.createObjectURL(file);
					    var a         = document.createElement('a');
					    a.href        = fileURL; 
					    a.target      = '_blank';
					    a.download    = fileName;
					    document.body.appendChild(a);
					    a.click();
					}
				}
			});
		}
	}
})();

