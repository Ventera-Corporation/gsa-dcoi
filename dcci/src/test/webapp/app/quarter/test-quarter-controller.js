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
							name: 'New England',
							code: 'newEngland',
							regionId: 0,
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
									]
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
									]
								}
							]
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
									]
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
									]
								}
							]
						},
						{
							name: "Mid-Atlantic",
							code: 'midAtlantic',
							dataCenters: {}
						},
						{
							name: "Southeast Sunbelt",
							code: 'southeastSunbelt',
							dataCenters: {}
						},
						{
							name: "Great Lakes",
							code: 'greatLakes',
							dataCenters: {}
						},
						{
							name: "Heartland",
							code: 'heartland',
							dataCenters: {}
						},
						{
							name: "Greater Southwest",
							code: 'greaterSouthwest',
							dataCenters: {}
						},
						{
							name: "Rocky Mountain",
							code: 'rockyMountain',
							dataCenters: {}
						},
						{
							name: "Pacific Rim",
							code: 'pacificRim',
							dataCenters: {}
						},
						{
							name: "Northwest/Arctic",
							code: 'northwestArctic',
							dataCenters: {}
						},
						{
							name: "National Capital",
							code: 'nationalCapital',
							dataCenters: {}
						},
						{
							name: "Cloud",
							code: 'cloud',
							dataCenters: {}
						}
					]
				}
			});
			httpBackend.when("POST", "/quarter/save?quarterDto=%7B%22fiscalQuarterReport%22:%7B%22fiscalQuarter%22:%22Q3%22,%22fiscalYear%22:%222016%22,%22quarterInProgressFlag%22:true,%22quarterActiveFlag%22:false%7D,%22regions%22:%5B%7B%22name%22:%22New+England%22,%22code%22:%22newEngland%22,%22regionId%22:0,%22dataCenters%22:%5B%7B%22name%22:%22Data+Center+1+Name%22,%22id%22:%22Data+Center+1+ID%22,%22city%22:%22Data+Center+1+City%22,%22stateName%22:%22Data+Center+1+State%22,%22components%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D,%7B%22name%22:%22Data+Center+2+Name%22,%22id%22:%22Data+Center+2+ID%22,%22city%22:%22Data+Center+2+City%22,%22stateName%22:%22Data+Center+2+State%22,%22components%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D%5D%7D,%7B%22name%22:%22Northeast+and+Caribbean%22,%22code%22:%22northeastAndCaribbean%22,%22dataCenters%22:%5B%7B%22name%22:%22Data+Center+3+Name%22,%22id%22:%22Data+Center+3+ID%22,%22city%22:%22Data+Center+3+City%22,%22stateName%22:%22Data+Center+3+State%22,%22components%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22OICO%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D,%7B%22name%22:%22Data+Center+4+Name%22,%22id%22:%22Data+Center+4+ID%22,%22city%22:%22Data+Center+4+City%22,%22stateName%22:%22Data+Center+4+State%22,%22components%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D%5D%7D,%7B%22name%22:%22Mid-Atlantic%22,%22code%22:%22midAtlantic%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Southeast+Sunbelt%22,%22code%22:%22southeastSunbelt%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Great+Lakes%22,%22code%22:%22greatLakes%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Heartland%22,%22code%22:%22heartland%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Greater+Southwest%22,%22code%22:%22greaterSouthwest%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Rocky+Mountain%22,%22code%22:%22rockyMountain%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Pacific+Rim%22,%22code%22:%22pacificRim%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Northwest%2FArctic%22,%22code%22:%22northwestArctic%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22National+Capital%22,%22code%22:%22nationalCapital%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Cloud%22,%22code%22:%22cloud%22,%22dataCenters%22:%7B%7D%7D%5D%7D").respond({
			    'fiscalQuarterReport': {
			    	fiscalQuarter: 'Q3',
					fiscalYear: '2016',
					quarterInProgressFlag: true,
					quarterActiveFlag: false
				},
				'successData': {
					message: 'Saved'
				}
			});
			httpBackend.when("POST", "/quarter/create?quarterDto=%7B%22fiscalQuarterReport%22:%7B%22fiscalQuarter%22:%22Q3%22,%22fiscalYear%22:%222016%22,%22quarterInProgressFlag%22:false,%22quarterActiveFlag%22:true%7D,%22regions%22:%5B%7B%22name%22:%22New+England%22,%22code%22:%22newEngland%22,%22regionId%22:0,%22dataCenters%22:%5B%7B%22name%22:%22Data+Center+1+Name%22,%22id%22:%22Data+Center+1+ID%22,%22city%22:%22Data+Center+1+City%22,%22stateName%22:%22Data+Center+1+State%22,%22components%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D,%7B%22name%22:%22Data+Center+2+Name%22,%22id%22:%22Data+Center+2+ID%22,%22city%22:%22Data+Center+2+City%22,%22stateName%22:%22Data+Center+2+State%22,%22components%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D%5D%7D,%7B%22name%22:%22Northeast+and+Caribbean%22,%22code%22:%22northeastAndCaribbean%22,%22dataCenters%22:%5B%7B%22name%22:%22Data+Center+3+Name%22,%22id%22:%22Data+Center+3+ID%22,%22city%22:%22Data+Center+3+City%22,%22stateName%22:%22Data+Center+3+State%22,%22components%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22OICO%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D,%7B%22name%22:%22Data+Center+4+Name%22,%22id%22:%22Data+Center+4+ID%22,%22city%22:%22Data+Center+4+City%22,%22stateName%22:%22Data+Center+4+State%22,%22components%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D%5D%7D,%7B%22name%22:%22Mid-Atlantic%22,%22code%22:%22midAtlantic%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Southeast+Sunbelt%22,%22code%22:%22southeastSunbelt%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Great+Lakes%22,%22code%22:%22greatLakes%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Heartland%22,%22code%22:%22heartland%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Greater+Southwest%22,%22code%22:%22greaterSouthwest%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Rocky+Mountain%22,%22code%22:%22rockyMountain%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Pacific+Rim%22,%22code%22:%22pacificRim%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Northwest%2FArctic%22,%22code%22:%22northwestArctic%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22National+Capital%22,%22code%22:%22nationalCapital%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Cloud%22,%22code%22:%22cloud%22,%22dataCenters%22:%7B%7D%7D%5D%7D").respond({
			    'fiscalQuarterReport': {
			    	fiscalQuarter: 'Q3',
					fiscalYear: '2016',
					quarterInProgressFlag: false,
					quarterActiveFlag: true
				},
				'successData': {
					message: 'Created'
				}
			});
			httpBackend.when("POST", "/datacenter/add?dataCenterDto=%7B%22dataCenterId%22:%22%22,%22dataCenterName%22:%22Test%22,%22dcoiDataCenterId%22:%22%22,%22regionId%22:0,%22city%22:%22%22,%22stateName%22:%22%22,%22fieldOffices%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22OCIO%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D").respond({
				'successData': {
					message: 'Added'
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
			expect(qc.quarterData.fiscalQuarterReport.fiscalQuarter).toBe('Q3');
			expect(qc.quarterData.fiscalQuarterReport.fiscalYear).toBe('2016');
			expect(qc.quarterData.fiscalQuarterReport.quarterInProgressFlag).toBe(true);
	    });
	    
	    it('verify regions data', function() {
			expect(Object.keys(qc.quarterData.regions).length).toBe(12);
	    });
	    
	    xit('verify save quarter', function() {
			httpBackend.expectPOST('/quarter/save?quarterDto=%7B%22fiscalQuarterReport%22:%7B%22fiscalQuarter%22:%22Q3%22,%22fiscalYear%22:%222016%22,%22quarterInProgressFlag%22:true,%22quarterActiveFlag%22:false%7D,%22regions%22:%5B%7B%22name%22:%22New+England%22,%22code%22:%22newEngland%22,%22regionId%22:0,%22dataCenters%22:%5B%7B%22name%22:%22Data+Center+1+Name%22,%22id%22:%22Data+Center+1+ID%22,%22city%22:%22Data+Center+1+City%22,%22stateName%22:%22Data+Center+1+State%22,%22components%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D,%7B%22name%22:%22Data+Center+2+Name%22,%22id%22:%22Data+Center+2+ID%22,%22city%22:%22Data+Center+2+City%22,%22stateName%22:%22Data+Center+2+State%22,%22components%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D%5D%7D,%7B%22name%22:%22Northeast+and+Caribbean%22,%22code%22:%22northeastAndCaribbean%22,%22dataCenters%22:%5B%7B%22name%22:%22Data+Center+3+Name%22,%22id%22:%22Data+Center+3+ID%22,%22city%22:%22Data+Center+3+City%22,%22stateName%22:%22Data+Center+3+State%22,%22components%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22OICO%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D,%7B%22name%22:%22Data+Center+4+Name%22,%22id%22:%22Data+Center+4+ID%22,%22city%22:%22Data+Center+4+City%22,%22stateName%22:%22Data+Center+4+State%22,%22components%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D%5D%7D,%7B%22name%22:%22Mid-Atlantic%22,%22code%22:%22midAtlantic%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Southeast+Sunbelt%22,%22code%22:%22southeastSunbelt%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Great+Lakes%22,%22code%22:%22greatLakes%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Heartland%22,%22code%22:%22heartland%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Greater+Southwest%22,%22code%22:%22greaterSouthwest%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Rocky+Mountain%22,%22code%22:%22rockyMountain%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Pacific+Rim%22,%22code%22:%22pacificRim%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Northwest%2FArctic%22,%22code%22:%22northwestArctic%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22National+Capital%22,%22code%22:%22nationalCapital%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Cloud%22,%22code%22:%22cloud%22,%22dataCenters%22:%7B%7D%7D%5D%7D');
			qc.saveQuarter();
	    	httpBackend.flush();
			expect(qc.tempData.successData.message).toBe('Saved');
	    });

	    xit('verify create quarter', function() {
			httpBackend.expectPOST('/quarter/create?quarterDto=%7B%22fiscalQuarterReport%22:%7B%22fiscalQuarter%22:%22Q3%22,%22fiscalYear%22:%222016%22,%22quarterInProgressFlag%22:false,%22quarterActiveFlag%22:true%7D,%22regions%22:%5B%7B%22name%22:%22New+England%22,%22code%22:%22newEngland%22,%22regionId%22:0,%22dataCenters%22:%5B%7B%22name%22:%22Data+Center+1+Name%22,%22id%22:%22Data+Center+1+ID%22,%22city%22:%22Data+Center+1+City%22,%22stateName%22:%22Data+Center+1+State%22,%22components%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D,%7B%22name%22:%22Data+Center+2+Name%22,%22id%22:%22Data+Center+2+ID%22,%22city%22:%22Data+Center+2+City%22,%22stateName%22:%22Data+Center+2+State%22,%22components%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D%5D%7D,%7B%22name%22:%22Northeast+and+Caribbean%22,%22code%22:%22northeastAndCaribbean%22,%22dataCenters%22:%5B%7B%22name%22:%22Data+Center+3+Name%22,%22id%22:%22Data+Center+3+ID%22,%22city%22:%22Data+Center+3+City%22,%22stateName%22:%22Data+Center+3+State%22,%22components%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22OICO%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D,%7B%22name%22:%22Data+Center+4+Name%22,%22id%22:%22Data+Center+4+ID%22,%22city%22:%22Data+Center+4+City%22,%22stateName%22:%22Data+Center+4+State%22,%22components%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D%5D%7D,%7B%22name%22:%22Mid-Atlantic%22,%22code%22:%22midAtlantic%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Southeast+Sunbelt%22,%22code%22:%22southeastSunbelt%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Great+Lakes%22,%22code%22:%22greatLakes%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Heartland%22,%22code%22:%22heartland%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Greater+Southwest%22,%22code%22:%22greaterSouthwest%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Rocky+Mountain%22,%22code%22:%22rockyMountain%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Pacific+Rim%22,%22code%22:%22pacificRim%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Northwest%2FArctic%22,%22code%22:%22northwestArctic%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22National+Capital%22,%22code%22:%22nationalCapital%22,%22dataCenters%22:%7B%7D%7D,%7B%22name%22:%22Cloud%22,%22code%22:%22cloud%22,%22dataCenters%22:%7B%7D%7D%5D%7D');
			qc.createQuarter();
	    	httpBackend.flush();
			expect(qc.tempData.successData.message).toBe('Created');
	    });
	    
	    
	    xit('verify add datacenter', function() {
	    	var numOfDataCenters = qc.quarterData.regions[0].dataCenters.length;
			httpBackend.expectPOST('/datacenter/add?dataCenterDto=%7B%22dataCenterId%22:%22%22,%22dataCenterName%22:%22Test%22,%22dcoiDataCenterId%22:%22%22,%22regionId%22:0,%22city%22:%22%22,%22stateName%22:%22%22,%22fieldOffices%22:%5B%7B%22name%22:%22PBS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22FAS%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D,%7B%22name%22:%22OCIO%22,%22generalInfo%22:%7B%7D,%22status%22:%7B%7D,%22facilityInfo%22:%7B%7D,%22serverInfo%22:%7B%7D%7D%5D%7D');
			qc.addNewDataCenterFromModal({
				dataCenterId: '',
				dataCenterName: 'Test',
				dcoiDataCenterId: '',
				regionId: 0,
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
			});
	    	httpBackend.flush();

			expect(qc.quarterData.regions[0].dataCenters.length).toBe(numOfDataCenters+1);
			expect(qc.tempData.successData.message).toBe('Added');
	    });
	});
});
