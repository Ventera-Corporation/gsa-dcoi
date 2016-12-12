describe('dcoiApp', function() {
    	beforeEach(angular.mock.module('dcoiApp'));
	describe("DataCenterController", function() {
	    var dcc, httpBackend;
	    
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
			
			httpBackend.when("GET", "/datacenter/init").respond({
			    'dataCenterData': {
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
				}
			});
			var modalInstance = {// Create a mock object using spies
		        close: jasmine.createSpy('modalInstance.close'),
		        dismiss: jasmine.createSpy('modalInstance.dismiss'),
		        result: {
		          then: jasmine.createSpy('modalInstance.result.then')
		        }
			};
			
	    	dcc = $controller("DataCenterController", {
	    		$uibModalInstance: modalInstance,
	    		dataCenterData: {}
	    	});
			httpBackend.expectGET('/datacenter/init');
			dcc.initDataCenterData();
	    	httpBackend.flush();
	    }));
	    
	    
	    it('verify new datacenter data', function() {
			expect(dcc.dataCenter.dataCenterId).toBe('');
			expect(dcc.dataCenter.dataCenterName).toBe('');
			expect(dcc.dataCenter.dcoiDataCenterId).toBe('');
			expect(dcc.dataCenter.regionId).toBe('');
			expect(dcc.dataCenter.city).toBe('');
			expect(dcc.dataCenter.stateName).toBe('');
			expect(dcc.dataCenter.generalInfo).toBeDefined();
			expect(dcc.dataCenter.status).toBeDefined();
			expect(dcc.dataCenter.fieldOffices.length).toBeGreaterThan(0);
	    });

	});
});