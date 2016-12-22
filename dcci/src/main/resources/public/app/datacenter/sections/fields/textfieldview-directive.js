(function(){
	'use strict';
	
	angular.module('dcoiApp').directive('textfieldView', textfieldView);
	
	function textfieldView(){
		return {
			scope: {},
			bindToController: {
				fieldName: '@',
				pastDataCenters: '=',
				dataCenter: '=',
				totals: '@',
				sectionPropName: '@',
				fieldPropName: '@',
				newQuarterValue: '=',
				fieldFilter: '@',
				viewAddon: '@',
			},
			controller: 'FieldController',
			controllerAs: 'fc',
			templateUrl: 'app/datacenter/sections/fields/textfield-view.html'
		}
	}
})();

