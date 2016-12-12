(function(){
	'use strict';
	
	angular.module('dcoiApp').factory('SearchService', SearchService);
	
	SearchService.$inject = ['$http'];
	
	function SearchService($http){
	    return {
			search : function() {
				return $http({
					url: '/search/results',
					method: 'GET',
					//params: {searchDto: searchCriteria}
				}).then(returnData);
			}
	    };
	    
	    function returnData(responseObj){
	    	return responseObj.data;
	    }
	}
})();
