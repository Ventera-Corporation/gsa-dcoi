describe('dcoiApp', function() {
    	beforeEach(angular.mock.module('dcoiApp'));
	describe("SearchController", function() {
	    var sc, httpBackend;
	    
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
			
			httpBackend.when("GET", "/search").respond({
			    'searchResults': [
	                {
	                    "year": "2016",
	                    "quarter": "Q1",
	                    "dataCenterName": "DC-A1",
	                    "dataCenterId": "DC-A1-021",
	                    "location": "Edinburgh",
	                    "component": "FAS",
	                    "streetAddress": "12 New Road",
	                    "city": "Redwood City",
	                    "state": "New Jersey",
	                    "zipCode": "20192",
	                    "recordStatus": "Inactive",
	                    "ownershipType": "Corporation",
	                    "issProvider": "World Com",
	                    "dataCenterTier": "1",
	                    "automatedMonitoring": "Yes",
	                    "electricityIsMetered": "Yes",
	                    "avgElectricityUsage": "10,812 kWh",
	                    "grossFloorArea": "8,750 Pi. Ca.",
	                    "rackCount": "124",
	                    "totalMainframes": "27"
		            },
		            {
	                    "year": "2013",
	                    "quarter": "Q2",
	                    "dataCenterName": "DC-A2",
	                    "dataCenterId": "DC-A2-021",
	                    "location": "Tokyo",
	                    "component": "OCIO",
	                    "streetAddress": "12 New Road",
	                    "city": "Redwood City",
	                    "state": "New Jersey",
	                    "zipCode": "20192",
	                    "recordStatus": "Active",
	                    "ownershipType": "Corporation",
	                    "issProvider": "World Com",
	                    "dataCenterTier": "1",
	                    "automatedMonitoring": "Yes",
	                    "electricityIsMetered": "Yes",
	                    "avgElectricityUsage": "10,812 kWh",
	                    "grossFloorArea": "8,750 Pi. Ca.",
	                    "rackCount": "124",
	                    "totalMainframes": "21"
		            },
		            {
	                    "year": "2016",
	                    "quarter": "Q2",
	                    "dataCenterName": "DC-A1",
	                    "dataCenterId": "DC-A1-021",
	                    "location": "Edinburgh",
	                    "component": "OCIO",
	                    "streetAddress": "12 New Road",
	                    "city": "Redwood City",
	                    "state": "New Jersey",
	                    "zipCode": "20192",
	                    "recordStatus": "Inactive",
	                    "ownershipType": "Corporation",
	                    "issProvider": "World Com",
	                    "dataCenterTier": "1",
	                    "automatedMonitoring": "Yes",
	                    "electricityIsMetered": "Yes",
	                    "avgElectricityUsage": "10,812 kWh",
	                    "grossFloorArea": "8,750 Pi. Ca.",
	                    "rackCount": "124",
	                    "totalMainframes": "27"
		            },
		            {
	                    "year": "2016",
	                    "quarter": "Q2",
	                    "dataCenterName": "DC-A1",
	                    "dataCenterId": "DC-A1-021",
	                    "location": "Edinburgh",
	                    "component": "OCIO",
	                    "streetAddress": "12 New Road",
	                    "city": "Redwood City",
	                    "state": "Virginia",
	                    "zipCode": "20192",
	                    "recordStatus": "Inactive",
	                    "ownershipType": "Corporation",
	                    "issProvider": "World Com",
	                    "dataCenterTier": "1",
	                    "automatedMonitoring": "Yes",
	                    "electricityIsMetered": "Yes",
	                    "avgElectricityUsage": "10,812 kWh",
	                    "grossFloorArea": "8,750 Pi. Ca.",
	                    "rackCount": "124",
	                    "totalMainframes": "27"
		            },
		            {
	                    "year": "2016",
	                    "quarter": "Q2",
	                    "dataCenterName": "DC-A1",
	                    "dataCenterId": "DC-A1-021",
	                    "location": "Edinburgh",
	                    "component": "OCIO",
	                    "streetAddress": "12 New Road",
	                    "city": "Redwood City",
	                    "state": "Virginia",
	                    "zipCode": "20192",
	                    "recordStatus": "Inactive",
	                    "ownershipType": "Corporation",
	                    "issProvider": "World Com",
	                    "dataCenterTier": "1",
	                    "automatedMonitoring": "Yes",
	                    "electricityIsMetered": "Yes",
	                    "avgElectricityUsage": "10,812 kWh",
	                    "grossFloorArea": "8,750 Pi. Ca.",
	                    "rackCount": "124",
	                    "totalMainframes": "27"
		            },
		            {
	                    "year": "2002",
	                    "quarter": "Q2",
	                    "dataCenterName": "DC-A1",
	                    "dataCenterId": "DC-A1-021",
	                    "location": "Edinburgh",
	                    "component": "OCIO",
	                    "streetAddress": "12 New Road",
	                    "city": "Redwood City",
	                    "state": "Virginia",
	                    "zipCode": "20192",
	                    "recordStatus": "Inactive",
	                    "ownershipType": "Corporation",
	                    "issProvider": "World Com",
	                    "dataCenterTier": "1",
	                    "automatedMonitoring": "Yes",
	                    "electricityIsMetered": "Yes",
	                    "avgElectricityUsage": "10,812 kWh",
	                    "grossFloorArea": "8,750 Pi. Ca.",
	                    "rackCount": "124",
	                    "totalMainframes": "27"
		            },
		            {
	                    "year": "2008",
	                    "quarter": "Q2",
	                    "dataCenterName": "DC-A1",
	                    "dataCenterId": "DC-A1-021",
	                    "location": "Edinburgh",
	                    "component": "PBS",
	                    "streetAddress": "12 New Road",
	                    "city": "Redwood City",
	                    "state": "New York",
	                    "zipCode": "20192",
	                    "recordStatus": "Inactive",
	                    "ownershipType": "Corporation",
	                    "issProvider": "World Com",
	                    "dataCenterTier": "1",
	                    "automatedMonitoring": "Yes",
	                    "electricityIsMetered": "Yes",
	                    "avgElectricityUsage": "10,812 kWh",
	                    "grossFloorArea": "8,750 Pi. Ca.",
	                    "rackCount": "124",
	                    "totalMainframes": "27"
	            	},
	            	{
	                    "year": "2006",
	                    "quarter": "Q3",
	                    "dataCenterName": "DC-A1",
	                    "dataCenterId": "DC-A1-021",
	                    "location": "Edinburgh",
	                    "component": "PBS",
	                    "streetAddress": "12 New Road",
	                    "city": "Redwood City",
	                    "state": "New York",
	                    "zipCode": "20192",
	                    "recordStatus": "Inactive",
	                    "ownershipType": "Corporation",
	                    "issProvider": "World Com",
	                    "dataCenterTier": "1",
	                    "automatedMonitoring": "Yes",
	                    "electricityIsMetered": "Yes",
	                    "avgElectricityUsage": "10,812 kWh",
	                    "grossFloorArea": "8,750 Pi. Ca.",
	                    "rackCount": "124",
	                    "totalMainframes": "27"
	            	},
	            	{
	                    "year": "2009",
	                    "quarter": "Q3",
	                    "dataCenterName": "DC-A1",
	                    "dataCenterId": "DC-A1-021",
	                    "location": "Edinburgh",
	                    "component": "PBS",
	                    "streetAddress": "12 New Road",
	                    "city": "Redwood City",
	                    "state": "New York",
	                    "zipCode": "20192",
	                    "recordStatus": "Inactive",
	                    "ownershipType": "Corporation",
	                    "issProvider": "World Com",
	                    "dataCenterTier": "1",
	                    "automatedMonitoring": "Yes",
	                    "electricityIsMetered": "Yes",
	                    "avgElectricityUsage": "10,812 kWh",
	                    "grossFloorArea": "8,750 Pi. Ca.",
	                    "rackCount": "124",
	                    "totalMainframes": "27"
	            	},
	            	{
	                    "year": "2014",
	                    "quarter": "Q3",
	                    "dataCenterName": "DC-A1",
	                    "dataCenterId": "DC-A1-021",
	                    "location": "Edinburgh",
	                    "component": "PBS",
	                    "streetAddress": "12 New Road",
	                    "city": "Redwood City",
	                    "state": "New York",
	                    "zipCode": "20192",
	                    "recordStatus": "Inactive",
	                    "ownershipType": "Corporation",
	                    "issProvider": "World Com",
	                    "dataCenterTier": "1",
	                    "automatedMonitoring": "Yes",
	                    "electricityIsMetered": "Yes",
	                    "avgElectricityUsage": "10,812 kWh",
	                    "grossFloorArea": "8,750 Pi. Ca.",
	                    "rackCount": "124",
	                    "totalMainframes": "27"
	            	},
	            	{
	                    "year": "2013",
	                    "quarter": "Q3",
	                    "dataCenterName": "DC-A1",
	                    "dataCenterId": "DC-A1-021",
	                    "location": "Edinburgh",
	                    "component": "PBS",
	                    "streetAddress": "12 New Road",
	                    "city": "Redwood City",
	                    "state": "Main",
	                    "zipCode": "20192",
	                    "recordStatus": "Inactive",
	                    "ownershipType": "Corporation",
	                    "issProvider": "World Com",
	                    "dataCenterTier": "1",
	                    "automatedMonitoring": "Yes",
	                    "electricityIsMetered": "Yes",
	                    "avgElectricityUsage": "10,812 kWh",
	                    "grossFloorArea": "8,750 Pi. Ca.",
	                    "rackCount": "124",
	                    "totalMainframes": "27"
	            	},
	            	{
	                    "year": "2010",
	                    "quarter": "Q3",
	                    "dataCenterName": "DC-A1",
	                    "dataCenterId": "DC-A1-021",
	                    "location": "Edinburgh",
	                    "component": "PBS",
	                    "streetAddress": "12 New Road",
	                    "city": "Redwood City",
	                    "state": "Main",
	                    "zipCode": "20192",
	                    "recordStatus": "Inactive",
	                    "ownershipType": "Corporation",
	                    "issProvider": "World Com",
	                    "dataCenterTier": "1",
	                    "automatedMonitoring": "Yes",
	                    "electricityIsMetered": "Yes",
	                    "avgElectricityUsage": "10,812 kWh",
	                    "grossFloorArea": "8,750 Pi. Ca.",
	                    "rackCount": "124",
	                    "totalMainframes": "27"
	            	},
	            	{
	                    "year": "2011",
	                    "quarter": "Q3",
	                    "dataCenterName": "DC-A1",
	                    "dataCenterId": "DC-A1-021",
	                    "location": "Edinburgh",
	                    "component": "PBS",
	                    "streetAddress": "12 New Road",
	                    "city": "Redwood City",
	                    "state": "Main",
	                    "zipCode": "20192",
	                    "recordStatus": "Inactive",
	                    "ownershipType": "Corporation",
	                    "issProvider": "World Com",
	                    "dataCenterTier": "1",
	                    "automatedMonitoring": "Yes",
	                    "electricityIsMetered": "Yes",
	                    "avgElectricityUsage": "10,812 kWh",
	                    "grossFloorArea": "8,750 Pi. Ca.",
	                    "rackCount": "124",
	                    "totalMainframes": "27"
	            	},
	            	{
	                    "year": "2007",
	                    "quarter": "Q4",
	                    "dataCenterName": "DC-A1",
	                    "dataCenterId": "DC-A1-021",
	                    "location": "Edinburgh",
	                    "component": "PBS",
	                    "streetAddress": "12 New Road",
	                    "city": "Redwood City",
	                    "state": "Florida",
	                    "zipCode": "20192",
	                    "recordStatus": "Inactive",
	                    "ownershipType": "Corporation",
	                    "issProvider": "World Com",
	                    "dataCenterTier": "1",
	                    "automatedMonitoring": "Yes",
	                    "electricityIsMetered": "Yes",
	                    "avgElectricityUsage": "10,812 kWh",
	                    "grossFloorArea": "8,750 Pi. Ca.",
	                    "rackCount": "124",
	                    "totalMainframes": "27"
	            	},
	            	{
	                    "year": "2009",
	                    "quarter": "Q4",
	                    "dataCenterName": "DC-A1",
	                    "dataCenterId": "DC-A1-021",
	                    "location": "Edinburgh",
	                    "component": "FAS",
	                    "streetAddress": "12 New Road",
	                    "city": "Redwood City",
	                    "state": "Florida",
	                    "zipCode": "20192",
	                    "recordStatus": "Inactive",
	                    "ownershipType": "Corporation",
	                    "issProvider": "World Com",
	                    "dataCenterTier": "1",
	                    "automatedMonitoring": "Yes",
	                    "electricityIsMetered": "Yes",
	                    "avgElectricityUsage": "10,812 kWh",
	                    "grossFloorArea": "8,750 Pi. Ca.",
	                    "rackCount": "124",
	                    "totalMainframes": "27"
	            	},
	            	{
	                    "year": "2010",
	                    "quarter": "Q4",
	                    "dataCenterName": "DC-A1",
	                    "dataCenterId": "DC-A1-021",
	                    "location": "Edinburgh",
	                    "component": "FAS",
	                    "streetAddress": "12 New Road",
	                    "city": "Redwood City",
	                    "state": "Florida",
	                    "zipCode": "20192",
	                    "recordStatus": "Inactive",
	                    "ownershipType": "Corporation",
	                    "issProvider": "World Com",
	                    "dataCenterTier": "1",
	                    "automatedMonitoring": "Yes",
	                    "electricityIsMetered": "Yes",
	                    "avgElectricityUsage": "10,812 kWh",
	                    "grossFloorArea": "8,750 Pi. Ca.",
	                    "rackCount": "124",
	                    "totalMainframes": "27"
	            	},
	            	{
	                    "year": "2012",
	                    "quarter": "Q1",
	                    "dataCenterName": "DC-A1",
	                    "dataCenterId": "DC-A1-021",
	                    "location": "Edinburgh",
	                    "component": "FAS",
	                    "streetAddress": "12 New Road",
	                    "city": "Redwood City",
	                    "state": "Florida",
	                    "zipCode": "20192",
	                    "recordStatus": "Inactive",
	                    "ownershipType": "Corporation",
	                    "issProvider": "World Com",
	                    "dataCenterTier": "1",
	                    "automatedMonitoring": "Yes",
	                    "electricityIsMetered": "Yes",
	                    "avgElectricityUsage": "10,812 kWh",
	                    "grossFloorArea": "8,750 Pi. Ca.",
	                    "rackCount": "124",
	                    "totalMainframes": "27"
	            	}
	            ]
			});
			
	    	sc = $controller("SearchController", {
	    		advancedSearchMode: false
	    	});
	    }));
	    
	    
	    it('verify search results', function() {
	    	sc.search();
			expect(sc.searchResults).toBeDefined();
			expect(sc.showResults).toBe(true);
	    });

	});
});