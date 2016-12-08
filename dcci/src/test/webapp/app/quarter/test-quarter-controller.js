describe('dcoiApp', function() {
    	beforeEach(angular.mock.module('dcoiApp'));
	describe("QuarterController", function() {
	    var qc, httpBackend;
	    
	    beforeEach(angular.mock.inject(function($controller, $httpBackend) {
			httpBackend = $httpBackend;
			
			httpBackend.when("GET", "security/account").respond({
				"dcoiUserId":2,
				"firstName":"admin",
				"lastName":"user",
				"password":null,
				"emailAddress":"admin.user@gsa.gov",
				"activeFlag":true,
				"roles":[
				         {
				        	 "dcoiUserRoleId":0,
				        	 "dcoiUserId":0,
				        	 "dcoiRoleId":0,
				        	 "roleName":"ADMIN"
				         },
				         {
				        	 "dcoiUserRoleId":0,
				        	 "dcoiUserId":0,
				        	 "dcoiRoleId":0,
				        	 "roleName":"USER"
				         }
				        ]
			});
			
			httpBackend.when("GET", "/quarter/init").respond({
			    'quarterData': {
					fiscalQuarterReport: {
				    	fiscalQuarter: 'Q3',
						fiscalYear: '2016',
						quarterInProgressFlag: true,
						quarterActiveFlag: false,
						quarterCompleteFlag: false
					},
					regions: [
						{
							name: "Cloud",
							code: 'cloud',
							regionId: 0,
							dataCenters: []
						},
						{
							name: 'New England',
							code: 'newEngland',
							regionId: 1,
							dataCenters: [
								{
									dataCenterName: 'Data Center 1 Name',
									dataCenterId: 'Data Center 1 ID',
									dcoiDataCenterId: 'Data Center 1 DCOI ID',
									city: 'Data Center 1 City',
									stateName: 'Data Center 1 State',
									generalInfo: {},
									status: {},
									fieldOffices: [
										{
											fieldOfficeName: 'PBS',
											facilityInfo: {},
											serverInfo: {}
										},
										{
											fieldOfficeName: 'FAS',
											facilityInfo: {},
											serverInfo: {}
										}
									],
									ssoCompleteFlag: false,
									adminCompleteFlag: false
								},
								{
									dataCenterName: 'Data Center 2 Name',
									dataCenterId: 'Data Center 2 ID',
									dcoiDataCenterId: 'Data Center 2 DCOI ID',
									city: 'Data Center 2 City',
									stateName: 'Data Center 2 State',
									generalInfo: {},
									status: {},
									fieldOffices: [
										{
											fieldOfficeName: 'PBS',
											facilityInfo: {},
											serverInfo: {}
										},
										{
											fieldOfficeName: 'FAS',
											facilityInfo: {},
											serverInfo: {}
										}
									],
									ssoCompleteFlag: false,
									adminCompleteFlag: false
								}
							]
						},
						{
							name: 'Northeast and Caribbean',
							code: 'northeastAndCaribbean',
							regionId: 2,
							dataCenters: [
								{
									dataCenterName: 'Data Center 3 Name',
									dataCenterId: 'Data Center 3 ID',
									dcoiDataCenterId: 'Data Center 3 DCOI ID',
									city: 'Data Center 3 City',
									stateName: 'Data Center 3 State',
									generalInfo: {},
									status: {},
									fieldOffices: [
										{
											fieldOfficeName: 'PBS',
											facilityInfo: {},
											serverInfo: {}
										},
										{
											fieldOfficeName: 'FAS',
											facilityInfo: {},
											serverInfo: {}
										},
										{
											fieldOfficeName: 'OICO',
											facilityInfo: {},
											serverInfo: {}
										}
									],
									ssoCompleteFlag: false,
									adminCompleteFlag: false
								},
								{
									dataCenterName: 'Data Center 4 Name',
									dataCenterId: 'Data Center 4 ID',
									dcoiDataCenterId: 'Data Center 4 DCOI ID',
									city: 'Data Center 4 City',
									stateName: 'Data Center 4 State',
									generalInfo: {},
									status: {},
									fieldOffices: [
										{
											fieldOfficeName: 'PBS',
											facilityInfo: {},
											serverInfo: {}
										},
										{
											fieldOfficeName: 'FAS',
											facilityInfo: {},
											serverInfo: {}
										}
									],
									ssoCompleteFlag: false,
									adminCompleteFlag: false
								}
							]
						},
						{
							name: "Mid-Atlantic",
							code: 'midAtlantic',
							regionId: 3,
							dataCenters: [],
						},
						{
							name: "Southeast Sunbelt",
							code: 'southeastSunbelt',
							regionId: 4,
							dataCenters: [],
						},
						{
							name: "Great Lakes",
							code: 'greatLakes',
							regionId: 5,
							dataCenters: [],
						},
						{
							name: "Heartland",
							code: 'heartland',
							regionId: 6,
							dataCenters: [],
						},
						{
							name: "Greater Southwest",
							code: 'greaterSouthwest',
							regionId: 7,
							dataCenters: [],
						},
						{
							name: "Rocky Mountain",
							code: 'rockyMountain',
							regionId: 8,
							dataCenters: [],
						},
						{
							name: "Pacific Rim",
							code: 'pacificRim',
							regionId: 9,
							dataCenters: [],
						},
						{
							name: "Northwest/Arctic",
							code: 'northwestArctic',
							regionId: 10,
							dataCenters: [],
						},
						{
							name: "National Capital",
							code: 'nationalCapital',
							regionId: 11,
							dataCenters: [],
						}
					]
				}
			});
			httpBackend.when("POST", "/quarter/save?dataCenterDtos=%7B%22dataCenterId%22:1234,%22dataCenterName%22:%22Test+Name1%22,%22dcoiDataCenterId%22:%22DCOI-Test-ID1%22,%22regionId%22:1,%22city%22:%22%22,%22stateName%22:%22%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22fieldOffices%22:%5B%7B%22name%22:%22PBS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22OCIO%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D").respond({
				'successData': {
					message: 'Saved'
				}
			});
			httpBackend.when("POST", "/quarter/save?dataCenterDtos=%7B%22dataCenterId%22:1234,%22dataCenterName%22:%22Test+Name1%22,%22dcoiDataCenterId%22:%22DCOI-Test-ID1%22,%22regionId%22:1,%22city%22:%22%22,%22stateName%22:%22%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22fieldOffices%22:%5B%7B%22name%22:%22PBS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22OCIO%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D&dataCenterDtos=%7B%22dataCenterId%22:1235,%22dataCenterName%22:%22Test+Name2%22,%22dcoiDataCenterId%22:%22DCOI-Test-ID2%22,%22regionId%22:1,%22city%22:%22%22,%22stateName%22:%22%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22fieldOffices%22:%5B%7B%22name%22:%22PBS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22OCIO%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D&dataCenterDtos=%7B%22dataCenterId%22:1236,%22dataCenterName%22:%22Test+Name3%22,%22dcoiDataCenterId%22:%22DCOI-Test-ID3%22,%22regionId%22:1,%22city%22:%22%22,%22stateName%22:%22%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22fieldOffices%22:%5B%7B%22name%22:%22PBS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22OCIO%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D").respond({
				'successData': {
					message: 'Saved'
				}
			});
			httpBackend.when("POST", "/quarter/create?dueDate=08%2F08%2F2016").respond({
				fiscalQuarterReport: {
			    	fiscalQuarter: 'Q3',
					fiscalYear: '2016',
					quarterInProgressFlag: false,
					quarterActiveFlag: true
				},
				'successData': {
					message: 'Created'
				}
			});
	    	var testDataCenterId = 1234;
			for(var addedDataCenterIdx = 0; addedDataCenterIdx < 3; addedDataCenterIdx++){
				var displayIdx = addedDataCenterIdx + 1;
				var expectPostStr = "/datacenter/add?dataCenterDto=%7B%22dataCenterId%22:%22%22,%22dataCenterName%22:%22Test+Name" + displayIdx
				+ "%22,%22dcoiDataCenterId%22:%22DCOI-Test-ID" + displayIdx 
				+ "%22,%22regionId%22:1,%22city%22:%22%22,%22stateName%22:%22%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22fieldOffices%22:%5B%7B%22name%22:%22PBS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22OCIO%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D";
				httpBackend.when("POST", expectPostStr).respond({
					'successData': {
						message: 'Added',
						dataCenterId:  testDataCenterId + addedDataCenterIdx
					}
				});
			}
			
	    	qc = $controller("QuarterController", {
	    		quarterData: {}
	    	});
			httpBackend.expectGET('/quarter/init');
			qc.initQuarterData();
	    	httpBackend.flush();
	    }));
	    
	    
	    it('verify fiscal quarter report data for in progress quarter', function() {
			expect(qc.quarterData.fiscalQuarterReport.fiscalQuarter).toBeDefined();
			expect(qc.quarterData.fiscalQuarterReport.fiscalYear).toBeDefined();
			expect(qc.quarterData.fiscalQuarterReport.quarterInProgressFlag).toBe(true);
	    });
	    
	    it('verify regions data', function() {
			expect(qc.quarterData.regions.length).toBeGreaterThan(0);
	    });
	    
	    xit('verify add and save 1 new datacenter', function() {
	    	var testRegionIdx = 1;
	    	var baseTestDataCenterId = 1234;
	    	
	    	var baseNumOfDataCenters = qc.quarterData.regions[testRegionIdx].dataCenters.length;
	    	var numOfAddedDataCenters = 1;
	    	
	    	for(var addedDataCenterIdx = 0; addedDataCenterIdx < numOfAddedDataCenters; addedDataCenterIdx++){
	    		var displayIdx = addedDataCenterIdx + 1;
	    		var testDataCenterId = baseTestDataCenterId + addedDataCenterIdx;
		    	var newDataCenter = {
						dataCenterId: '',
						dataCenterName: 'Test Name' + displayIdx,
						dcoiDataCenterId: 'DCOI-Test-ID' + displayIdx,
						regionId: testRegionIdx,
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
				httpBackend.expectPOST('/datacenter/add?dataCenterDto=%7B%22dataCenterId%22:%22%22,%22dataCenterName%22:%22Test+Name' + displayIdx
						+ '%22,%22dcoiDataCenterId%22:%22DCOI-Test-ID' + displayIdx
						+ '%22,%22regionId%22:1,%22city%22:%22%22,%22stateName%22:%22%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22fieldOffices%22:%5B%7B%22name%22:%22PBS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22OCIO%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D');
				qc.addNewDataCenterFromModal(newDataCenter);
		    	httpBackend.flush();

	    		var numOfDataCenters = baseNumOfDataCenters + displayIdx;
				expect(qc.quarterData.regions[testRegionIdx].dataCenters.length).toBe(numOfDataCenters);
				expect(qc.tempData.successData.message).toBe('Added');
				expect(qc.tempData.successData.dataCenterId).toBe(testDataCenterId);
				
				for(var i = 0; i < qc.quarterData.regions[testRegionIdx].dataCenters.length; i++){
					if(qc.quarterData.regions[testRegionIdx].dataCenters[i].dataCenterName == newDataCenter.dataCenterName){
						expect(qc.quarterData.regions[testRegionIdx].dataCenters[i].dataCenterId).toBe(testDataCenterId);
					}
				}
	
				expect(qc.tempData.selected.regionIdx).toBe(testRegionIdx);
				expect(qc.tempData.selected.dataCenterName).toBe(newDataCenter.dataCenterName);
		    	expect(qc.tempData.editMode).toBe(true);
				
		    	qc.initDefaultPanelExpanded(newDataCenter);
		    	expect(qc.tempData.wasInEditMode.dataCenterIds.length).toEqual(numOfAddedDataCenters);
		    	expect(qc.tempData.wasInEditMode.dataCenterIds.indexOf(newDataCenter.dataCenterId)).toBeGreaterThan(-1);
		    }
	    	
	    	var editedDataCenters = qc.getEditedDataCenters();
	    	expect(editedDataCenters.length).toEqual(numOfAddedDataCenters);
	    	
			httpBackend.expectPOST("/quarter/save?dataCenterDtos=%7B%22dataCenterId%22:1234,%22dataCenterName%22:%22Test+Name1%22,%22dcoiDataCenterId%22:%22DCOI-Test-ID1%22,%22regionId%22:1,%22city%22:%22%22,%22stateName%22:%22%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22fieldOffices%22:%5B%7B%22name%22:%22PBS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22OCIO%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D");
			qc.saveQuarter();
	    	httpBackend.flush();
	    	
			expect(qc.tempData.successData.message).toBe('Saved');
	    	expect(qc.tempData.wasInEditMode.dataCenterIds.length).toEqual(0);
	    	expect(qc.tempData.wasInEditMode.dataCenterIds.indexOf(newDataCenter.dataCenterId)).toEqual(-1);
			expect(qc.tempData.editMode).toBe(false);
	    });
	    
	    xit('verify add and save 3 new datacenters', function() {
	    	var testRegionIdx = 1;
	    	var baseTestDataCenterId = 1234;
	    	
	    	var baseNumOfDataCenters = qc.quarterData.regions[testRegionIdx].dataCenters.length;
	    	var numOfAddedDataCenters = 3;
	    	
	    	for(var addedDataCenterIdx = 0; addedDataCenterIdx < numOfAddedDataCenters; addedDataCenterIdx++){
	    		var displayIdx = addedDataCenterIdx + 1;
	    		var testDataCenterId = baseTestDataCenterId + addedDataCenterIdx;
		    	var newDataCenter = {
						dataCenterId: '',
						dataCenterName: 'Test Name' + displayIdx,
						dcoiDataCenterId: 'DCOI-Test-ID' + displayIdx,
						regionId: testRegionIdx,
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
				httpBackend.expectPOST('/datacenter/add?dataCenterDto=%7B%22dataCenterId%22:%22%22,%22dataCenterName%22:%22Test+Name' + displayIdx
						+ '%22,%22dcoiDataCenterId%22:%22DCOI-Test-ID' + displayIdx
						+ '%22,%22regionId%22:1,%22city%22:%22%22,%22stateName%22:%22%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22fieldOffices%22:%5B%7B%22name%22:%22PBS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22OCIO%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D');
				qc.addNewDataCenterFromModal(newDataCenter);
		    	httpBackend.flush();

	    		var numOfDataCenters = baseNumOfDataCenters + displayIdx;
				expect(qc.quarterData.regions[testRegionIdx].dataCenters.length).toBe(numOfDataCenters);
				expect(qc.tempData.successData.message).toBe('Added');
				expect(qc.tempData.successData.dataCenterId).toBe(testDataCenterId);
				
				for(var i = 0; i < qc.quarterData.regions[testRegionIdx].dataCenters.length; i++){
					if(qc.quarterData.regions[testRegionIdx].dataCenters[i].dataCenterName == newDataCenter.dataCenterName){
						expect(qc.quarterData.regions[testRegionIdx].dataCenters[i].dataCenterId).toBe(testDataCenterId);
					}
				}
	
				expect(qc.tempData.selected.regionIdx).toBe(testRegionIdx);
				expect(qc.tempData.selected.dataCenterName).toBe(newDataCenter.dataCenterName);
		    	expect(qc.tempData.editMode).toBe(true);
				
		    	qc.initDefaultPanelExpanded(newDataCenter);
		    	expect(qc.tempData.wasInEditMode.dataCenterIds.length).toEqual(displayIdx);
		    	expect(qc.tempData.wasInEditMode.dataCenterIds.indexOf(newDataCenter.dataCenterId)).toBeGreaterThan(-1);
		    }
	    	
	    	var editedDataCenters = qc.getEditedDataCenters();
	    	expect(editedDataCenters.length).toEqual(numOfAddedDataCenters);

			httpBackend.expectPOST("/quarter/save?dataCenterDtos=%7B%22dataCenterId%22:1234,%22dataCenterName%22:%22Test+Name1%22,%22dcoiDataCenterId%22:%22DCOI-Test-ID1%22,%22regionId%22:1,%22city%22:%22%22,%22stateName%22:%22%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22fieldOffices%22:%5B%7B%22name%22:%22PBS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22OCIO%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D&dataCenterDtos=%7B%22dataCenterId%22:1235,%22dataCenterName%22:%22Test+Name2%22,%22dcoiDataCenterId%22:%22DCOI-Test-ID2%22,%22regionId%22:1,%22city%22:%22%22,%22stateName%22:%22%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22fieldOffices%22:%5B%7B%22name%22:%22PBS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22OCIO%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D&dataCenterDtos=%7B%22dataCenterId%22:1236,%22dataCenterName%22:%22Test+Name3%22,%22dcoiDataCenterId%22:%22DCOI-Test-ID3%22,%22regionId%22:1,%22city%22:%22%22,%22stateName%22:%22%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22fieldOffices%22:%5B%7B%22name%22:%22PBS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22OCIO%22,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D");
			qc.saveQuarter();
	    	httpBackend.flush();
	    	
			expect(qc.tempData.successData.message).toBe('Saved');
	    	expect(qc.tempData.wasInEditMode.dataCenterIds.length).toEqual(0);
	    	expect(qc.tempData.wasInEditMode.dataCenterIds.indexOf(newDataCenter.dataCenterId)).toEqual(-1);
			expect(qc.tempData.editMode).toBe(false);
	    });

	    xit('verify create quarter', function() {
	    	qc.quarterData.dueDate = '08/08/2016';
			httpBackend.expectPOST('/quarter/create?dueDate=08%2F08%2F2016');
			qc.createQuarter();
	    	httpBackend.flush();
			expect(qc.tempData.successData.message).toBe('Created');
	    });
	});
});
