describe('dcoiApp', function() {
    	beforeEach(angular.mock.module('dcoiApp'));
	describe("DashboardController", function() {
	    var dc, scope, httpBackend;
	    
	    beforeEach(angular.mock.inject(function($controller, $rootScope, $httpBackend) {
	    	scope = $rootScope.$new();
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
			
			httpBackend.when("GET", "/dashboard/init").respond({
			    'dashboardData': {
					years: ['2016', '2015', '2014'],
					quarters: [
						{
					    	fiscalQuarter: 'Q4',
							fiscalYear: '2016',
							totalNumDataCenters: 26,
							quarterInProgressFlag: false,
							quarterActiveFlag: false,
							quarterCompleteFlag: false
						},
						{
					    	fiscalQuarter: 'Q3',
							fiscalYear: '2016',
							totalNumDataCenters: 26,
							quarterInProgressFlag: false,
							quarterActiveFlag: false,
							quarterCompleteFlag: true
						},
						{
					    	fiscalQuarter: 'Q2',
							fiscalYear: '2016',
							totalNumDataCenters: 24,
							quarterInProgressFlag: false,
							quarterActiveFlag: false,
							quarterCompleteFlag: true
						},
						{
					    	fiscalQuarter: 'Q1',
							fiscalYear: '2016',
							totalNumDataCenters: 24,
							quarterInProgressFlag: false,
							quarterActiveFlag: false,
							quarterCompleteFlag: true
						},
						{
					    	fiscalQuarter: 'Q4',
							fiscalYear: '2015',
							totalNumDataCenters: 24,
							quarterInProgressFlag: false,
							quarterActiveFlag: false,
							quarterCompleteFlag: true
						},
						{
					    	fiscalQuarter: 'Q3',
							fiscalYear: '2015',
							totalNumDataCenters: 23,
							quarterInProgressFlag: false,
							quarterActiveFlag: false,
							quarterCompleteFlag: true
						},
						{
					    	fiscalQuarter: 'Q2',
							fiscalYear: '2015',
							totalNumDataCenters: 23,
							quarterInProgressFlag: false,
							quarterActiveFlag: false,
							quarterCompleteFlag: true
						},
						{
					    	fiscalQuarter: 'Q1',
							fiscalYear: '2015',
							totalNumDataCenters: 23,
							quarterInProgressFlag: false,
							quarterActiveFlag: false,
							quarterCompleteFlag: true
						},
						{
					    	fiscalQuarter: 'Q4',
							fiscalYear: '2014',
							totalNumDataCenters: 23,
							quarterInProgressFlag: false,
							quarterActiveFlag: false,
							quarterCompleteFlag: true
						},
						{
					    	fiscalQuarter: 'Q3',
							fiscalYear: '2014',
							totalNumDataCenters: 23,
							quarterInProgressFlag: false,
							quarterActiveFlag: false,
							quarterCompleteFlag: true
						},
						{
					    	fiscalQuarter: 'Q2',
							fiscalYear: '2014',
							totalNumDataCenters: 22,
							quarterInProgressFlag: false,
							quarterActiveFlag: false,
							quarterCompleteFlag: true
						},
						{
					    	fiscalQuarter: 'Q1',
							fiscalYear: '2014',
							totalNumDataCenters: 22,
							quarterInProgressFlag: false,
							quarterActiveFlag: false,
							quarterCompleteFlag: true
						}
					]
			    }
			});
			
	    	dc = $controller("DashboardController", {
	    		dashboardData: {},
	    		$scope: scope
	    	});
			httpBackend.expectGET('/dashboard/init');
			dc.initDashboardData();
	    	httpBackend.flush();
	    }));
	    
	    xit('verify dashboard data', function() {
			expect(dc.dashboardData.years.length).toBe(3);
			expect(dc.dashboardData.quarters.length).toBe(12);
	    });
	    
	    xit('verify quarter already in progress check', function() {
			expect(dc.isAlreadyInProgressQuarter()).toBe(0);
	    });
	});
});
