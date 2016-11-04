(function(){
	'use strict';
	
	angular.module('dcciApp').controller('QuarterController', QuarterController);
	
	QuarterController.$inject = [];
	
	function QuarterController(){
		var qc = this;
		qc.tempData = {};
		qc.quarterData = {};
		qc.initQuarter = initQuarter;
		qc.createQuarter = createQuarter;
		qc.saveQuarter = saveQuarter;
		qc.submitQuarter = submitQuarter;
		qc.exportQuarter = exportQuarter;
		qc.addNewDataCenter = addNewDataCenter;
		qc.initDataCenterList = initDataCenterList;
		qc.removeDataCenter = removeDataCenter;
		qc.initComponentTab = initComponentTab;
		qc.viewAudit = viewAudit;
		qc.validateCategory = validateCategory;

		initQuarter();
		
		function initQuarter(){
			qc.tempData.displayedRegionIdx = 0;
			qc.quarterData = {
				regions: [
					{
						name: 'Region 1 Name',
						id: 'Region 1 ID',
						dataCenters: [
							{
								name: 'Data Center 1 Name',
								id: 'Data Center 1 ID',
								city: 'Data Center 1 City',
								state: 'Data Center 1 State',
								categories: [
									{
										id: 'Category 1',
										expanded: true
									},
									{
										id: 'Category 2',
										expanded: true
									}
								],
								expanded: true
							},
							{
								name: 'Data Center 2 Name',
								id: 'Data Center 2 ID',
								city: 'Data Center 2 City',
								state: 'Data Center 2 State',
								categories: [
									{
										id: 'Category 1',
										expanded: true
									},
									{
										id: 'Category 2',
										expanded: true
									}
								],
								expanded: true
							}
						],
						expanded: true
					},
					{
						name: 'Region 2 Name',
						id: 'Region 2 ID',
						dataCenters: [
							{
								name: 'Data Center 3 Name',
								id: 'Data Center 3 ID',
								city: 'Data Center 3 City',
								state: 'Data Center 3 State',
								categories: [
									{
										id: 'Category 1',
										expanded: false
									},
									{
										id: 'Category 2',
										expanded: false
									}
								],
								expanded: false
							},
							{
								name: 'Data Center 4 Name',
								id: 'Data Center 4 ID',
								city: 'Data Center 4 City',
								state: 'Data Center 4 State',
								categories: [
									{
										id: 'Category 1',
										expanded: false
									},
									{
										id: 'Category 2',
										expanded: false
									}
								],
								expanded: false
							}
						],
						expanded: false
					},
				]
			};
		}
		
		function createQuarter(){
			
		}
		
		function saveQuarter(){
			
		}
		
		function submitQuarter(){
			
		}
		
		function exportQuarter(){
			
		}
		
		function addNewDataCenter(){
			
		}
		
		function initDataCenterList(){
			
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
