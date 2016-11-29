describe('dcoiApp', function() {
    	beforeEach(angular.mock.module('dcoiApp'));
	describe("DataCenterController", function() {
	    var dcc, httpBackend;
	    
	    beforeEach(angular.mock.inject(function($controller, $httpBackend) {
			httpBackend = $httpBackend;
			httpBackend.when("GET", "/datacenter/init").respond({
			    'dataCenterData': {
					dataCenterId: '',
					dataCenterName: '',
					dcoiDataCenterId: '',
					regionId: '',
					city: '',
					stateName: '',
					fieldOffices: [
						{
							name: 'PBS',
							generalInfo: {},
							status: {},
							facilityInfo: {},
							serverInfo: {}
						},
						{
							name: 'FAS',
							generalInfo: {},
							status: {},
							facilityInfo: {},
							serverInfo: {}
						},
						{
							name: 'OCIO',
							generalInfo: {},
							status: {},
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
			expect(dcc.dataCenter.fieldOffices.length).toBe(3);
	    });

	});
});