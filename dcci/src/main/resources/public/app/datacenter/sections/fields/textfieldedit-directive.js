(function(){
	'use strict';
	
	angular.module('dcoiApp').directive('textfieldEdit', textfieldEdit);
	
	function textfieldEdit(){
		return {
			scope: {},
			bindToController: {
				fieldName: '@',
				fieldRequired: '@',
				pastDataCenters: '=',
				dataCenter: '=',
				fieldOffice: '=',
				totals: '@',
				sectionPropName: '@',
				fieldPropName: '@',
				newQuarterValue: '=',
				placeholderText: '@',
				inputAddonFront: '@',
				inputAddonBack: '@',
				errorMessage: '='
			},
			controller: 'FieldController',
			controllerAs: 'fc',
			templateUrl: 'app/datacenter/sections/fields/textfield-edit.html'
		}
	}
})();

