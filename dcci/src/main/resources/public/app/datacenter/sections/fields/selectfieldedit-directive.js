(function(){
	'use strict';
	
	angular.module('dcoiApp').directive('selectfieldEdit', selectfieldEdit);
	
	function selectfieldEdit(){
		return {
			scope: {
				fieldName: "@",
				fieldId: "@",
				fieldRequired: "@",
				pastQuarterValue: "@",
				newQuarterModel: "=",
				refValueList: "=",
				errorData: "@",
				errorMessagesProp: "@"
			},
			templateUrl: "app/datacenter/sections/fields/selectfield-edit.html"
		}
	}
})();

