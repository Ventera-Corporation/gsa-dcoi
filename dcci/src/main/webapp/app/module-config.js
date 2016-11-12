(function(){
	'use strict';
	angular.module('dcoiApp').config(config);
	
	config.$inject = ['$routeProvider'];
	
	function config($routeProvider){
		$routeProvider
			.when('/createQuarter', {
				templateUrl: 'app/quarter/quarter.html',
				controller: 'QuarterController',
				controllerAs: 'qc',
				resolve: {
					initQuarterData: function(QuarterService){
						return QuarterService.initQuarter();
					}
				}
			});
	}
})();