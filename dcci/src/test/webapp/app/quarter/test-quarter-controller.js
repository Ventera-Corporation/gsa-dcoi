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
						quarterActiveFlag: false
					},
					regions: [
						{
							name: "Cloud",
							code: 'cloud',
							regionId: 0,
							dataCenters: {}
						},
						{
							name: 'New England',
							code: 'newEngland',
							regionId: 1,
							dataCenters: [
								{
									name: 'Data Center 1 Name',
									id: 'Data Center 1 ID',
									city: 'Data Center 1 City',
									stateName: 'Data Center 1 State',
									generalInfo: {},
									status: {},
									components: [
										{
											name: 'PBS',
											facilityInfo: {},
											serverInfo: {}
										},
										{
											name: 'FAS',
											facilityInfo: {},
											serverInfo: {}
										}
									]
								},
								{
									name: 'Data Center 2 Name',
									id: 'Data Center 2 ID',
									city: 'Data Center 2 City',
									stateName: 'Data Center 2 State',
									generalInfo: {},
									status: {},
									components: [
										{
											name: 'PBS',
											facilityInfo: {},
											serverInfo: {}
										},
										{
											name: 'FAS',
											facilityInfo: {},
											serverInfo: {}
										}
									]
								}
							]
						},
						{
							name: 'Northeast and Caribbean',
							code: 'northeastAndCaribbean',
							regionId: 2,
							dataCenters: [
								{
									name: 'Data Center 3 Name',
									id: 'Data Center 3 ID',
									city: 'Data Center 3 City',
									stateName: 'Data Center 3 State',
									generalInfo: {},
									status: {},
									components: [
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
											name: 'OICO',
											facilityInfo: {},
											serverInfo: {}
										}
									]
								},
								{
									name: 'Data Center 4 Name',
									id: 'Data Center 4 ID',
									city: 'Data Center 4 City',
									stateName: 'Data Center 4 State',
									generalInfo: {},
									status: {},
									components: [
										{
											name: 'PBS',
											facilityInfo: {},
											serverInfo: {}
										},
										{
											name: 'FAS',
											facilityInfo: {},
											serverInfo: {}
										}
									]
								}
							]
						}
					]
				}
			});
			httpBackend.when("POST", "/quarter/save?dataCenterDtos=").respond({
				'successData': {
					message: 'Saved'
				}
			});
			httpBackend.when("POST", "/quarter/create?dueDate=").respond({
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
			httpBackend.when("POST", "/datacenter/add?dataCenterDto=").respond({
				'successData': {
					message: 'Added',
					dataCenterId: 1234
				}
			});
			
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
	    
	    it('verify add and save datacenter', function() {
	    	var numOfDataCenters = qc.quarterData.regions[1].dataCenters.length;
	    	var newDataCenter = {
					dataCenterId: '',
					dataCenterName: 'Test Name',
					dcoiDataCenterId: 'DCOI-Test-ID',
					regionId: 1,
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
			httpBackend.expectPOST('/datacenter/add?dataCenterDto='+JSON.stringify(newDataCenter));
			qc.addNewDataCenterFromModal(newDataCenter);
	    	httpBackend.flush();

	    	expect(qc.editMode).toBe(true);
			expect(qc.quarterData.regions[1].dataCenters.length).toBe(numOfDataCenters+1);
			expect(qc.tempData.successData.message).toBe('Added');
			expect(qc.tempData.successData.dataCenterId).toBe(1234);
			
			for(var i = 0; i < qc.quarterData.regions[1].length; i++){
				if(qc.quarterData.regions[1].dataCenters[i].dataCenterName == newDataCenter.dataCenterName){
					expect(qc.quarterData.regions[1].dataCenters[i].dataCenterId).toBe(1234);
				}
			}
			
			httpBackend.expectPOST('/quarter/save?dataCenterDtos='+JSON.stringify(newDataCenter));
			qc.saveQuarter();
	    	httpBackend.flush();
			expect(qc.tempData.successData.message).toBe('Saved');
			expect(qc.editMode).toBe(false);
	    });

	    it('verify create quarter', function() {
	    	qc.quarterData.dueDate = '08/08/2016';
			httpBackend.expectPOST('/quarter/create?dueDate=08/08/2016');
			qc.createQuarter();
	    	httpBackend.flush();
			expect(qc.tempData.successData.message).toBe('Created');
	    });
	});
});
