(function(){
	'use strict';
	
	angular.module('dcoiApp').directive('fieldView', fieldView);
	
	function fieldView(){
		return {
			scope: {
				fieldName: "@",
				pastQuarterValue: "@",
				newQuarterValue: "@",
				viewAddon: "@"
			},
			templateUrl: "app/datacenter/sections/fields/field-view.html"
		}
	}
})();

