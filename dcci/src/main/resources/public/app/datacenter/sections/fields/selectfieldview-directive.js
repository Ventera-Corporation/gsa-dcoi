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
				fieldOffice: '=',
				totals: '@',
				sectionPropName: '@',
				fieldPropName: '@',
				newQuarterValue: '=',
				refValueList: '=',
			},
			controller: 'FieldController',
			controllerAs: 'fc',
			templateUrl: 'app/datacenter/sections/fields/selectfield-view.html'
		}
	}
})();

