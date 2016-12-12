(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('SearchController', SearchController);
	
	SearchController.$inject = ['SearchService', 'advancedSearchMode'];
	
	function SearchController(SearchService, advancedSearchMode){
		var sc = this;
		sc.advancedSearchMode = advancedSearchMode;
		sc.search = search;
		sc.search();
		
		// Temp Pagination Code
		sc.currentPageNum = 1;
		sc.pageInfoStartNumber = 1;
		sc.pageInfoEndNumber = 10;
		pageInfoStartNumber();
		pageInfoEndNumber();
		
		function search(){
			SearchService.search().then(function (data){
				sc.searchResults = data.searchResults;
			});
			sc.showResults = true;
		}

		function pageInfoStartNumber() {
			if (sc.currentPageNum == 1)
				return 1;
			return ((sc.currentPageNum - 1) * sc.resultsPerPage) + 1;
		}
		
		function pageInfoEndNumber() {
			var temp = sc.currentPageNum * sc.resultsPerPage;
			if (temp > sc.resultsTotalNum) {
				temp = sc.resultsTotalNum;
			}
			return temp;
		}
	}
})();
