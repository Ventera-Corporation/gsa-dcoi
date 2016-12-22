(function(){
	'use strict';
	
	angular.module('dcoiApp').directive('selectfieldEdit', selectfieldEdit);
	
	function selectfieldEdit(){
		return {
			scope: {},
			bindToController: {
				fieldName: '@',
				fieldRequired: '@',
				pastDataCenters: '=',
				dataCenter: '=',
				totals: '@',
				sectionPropName: '@',
				fieldPropName: '@',
				newQuarterValue: '=',
				refValueList: '=',
				errorMessage: '='
			},
			controller: 'FieldController',
			controllerAs: 'fc',
			templateUrl: 'app/datacenter/sections/fields/selectfield-edit.html'
		}
	}
})();

