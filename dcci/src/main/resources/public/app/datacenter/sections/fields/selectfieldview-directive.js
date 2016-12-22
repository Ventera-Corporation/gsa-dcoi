(function(){
	'use strict';
	
	angular.module('dcoiApp').directive('selectfieldView', selectfieldView);
	
	function selectfieldView(){
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
				viewAddon: '@',
				refValueList: '=',
			},
			controller: 'FieldController',
			controllerAs: 'fc',
			templateUrl: 'app/datacenter/sections/fields/selectfield-view.html'
		}
	}
})();

