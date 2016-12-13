(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('SearchController', SearchController);
	
	SearchController.$inject = ['SearchService', 'advancedSearchMode'];
	
	function SearchController(SearchService, advancedSearchMode){
		var sc = this;
		sc.search = search();
		function search(){
			SearchService.search().then(function (data){
				sc.searchResults = data.searchResults;
			});
		}
	}
})();
