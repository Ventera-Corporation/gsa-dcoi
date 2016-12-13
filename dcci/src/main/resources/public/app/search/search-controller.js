(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('SearchController', SearchController);
	
	SearchController.$inject = ['SearchService'];
	
	function SearchController(SearchService){
		var sc = this;
		SearchService.search().then(function (data){
			sc.searchResults = data.searchResults;
		});
	}
})();
