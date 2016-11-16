describe('dcoiApp', function() {
    	beforeEach(angular.mock.module('dcoiApp'));
	describe("QuarterController", function() {
	    var qc, httpBackend;
	    
	    beforeEach(angular.mock.inject(function($controller, $httpBackend) {
			httpBackend = $httpBackend;
			httpBackend.when("GET", "/newQuarter/init").respond({
			    'quarterData': {
					fiscalQuarterReport: {
				    	fiscalQuarter: 'Q3',
						fiscalYear: '2016',
					},
					regions: [
						{
							name: 'New England',
							code: 'newEngland',
							dataCenters: [
								{
									name: 'Data Center 1 Name',
									id: 'Data Center 1 ID',
									city: 'Data Center 1 City',
									stateName: 'Data Center 1 State',
									components: [
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
										}
									],
								},
								{
									name: 'Data Center 2 Name',
									id: 'Data Center 2 ID',
									city: 'Data Center 2 City',
									stateName: 'Data Center 2 State',
									components: [
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
										}
									],
								}
							],
						},
						{
							name: 'Northeast and Caribbean',
							code: 'northeastAndCaribbean',
							dataCenters: [
								{
									name: 'Data Center 3 Name',
									id: 'Data Center 3 ID',
									city: 'Data Center 3 City',
									stateName: 'Data Center 3 State',
									components: [
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
											name: 'OICO',
											generalInfo: {},
											status: {},
											facilityInfo: {},
											serverInfo: {}
										}
									],
								},
								{
									name: 'Data Center 4 Name',
									id: 'Data Center 4 ID',
									city: 'Data Center 4 City',
									stateName: 'Data Center 4 State',
									components: [
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
										}
									],
								}
							],
						},
						{
							name: "Mid-Atlantic",
							code: 'midAtlantic',
							dataCenters: {},
						},
						{
							name: "Southeast Sunbelt",
							code: 'southeastSunbelt',
							dataCenters: {},
						},
						{
							name: "Great Lakes",
							code: 'greatLakes',
							dataCenters: {},
						},
						{
							name: "Heartland",
							code: 'heartland',
							dataCenters: {},
						},
						{
							name: "Greater Southwest",
							code: 'greaterSouthwest',
							dataCenters: {},
						},
						{
							name: "Rocky Mountain",
							code: 'rockyMountain',
							dataCenters: {},
						},
						{
							name: "Pacific Rim",
							code: 'pacificRim',
							dataCenters: {},
						},
						{
							name: "Northwest/Arctic",
							code: 'northwestArctic',
							dataCenters: {},
						},
						{
							name: "National Capital",
							code: 'nationalCapital',
							dataCenters: {},
						},
						{
							name: "Cloud",
							code: 'cloud',
							dataCenters: {},
						}
					]
				}
			});
			
	    	qc = $controller("QuarterController", {
	    		quarterData: {}
	    	});
			httpBackend.expectGET('/newQuarter/init');
			qc.initQuarterData();
	    	httpBackend.flush();
	    }));
	    
	    
	    it('verify fiscal quarter report data', function() {
			expect(qc.quarterData.fiscalQuarterReport.fiscalQuarter).toBe('Q3');
			expect(qc.quarterData.fiscalQuarterReport.fiscalYear).toBe('2016');
	    });
	    
	    it('verify region data', function() {
			expect(Object.keys(qc.quarterData.regions).length).toBe(12);
	    });

	});
});