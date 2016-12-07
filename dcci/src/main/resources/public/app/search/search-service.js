(function(){
	'use strict';
	
	angular.module('dcoiApp').factory('SearchService', SearchService);
	
	SearchService.$inject = ['$http'];
	
	function SearchService($http){
	    return {
			search : function(searchCriteria) {
				return $http({
					url: '/search',
					method: 'GET',
					params: {searchDto: searchCriteria}
				}).then(returnData);
			}
	    };
	    
	    function returnData(responseObj){
	    	return responseObj.data;
	    }
	}
})();
