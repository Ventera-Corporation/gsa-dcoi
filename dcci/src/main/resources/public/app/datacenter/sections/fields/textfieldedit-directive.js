(function(){
	'use strict';
	
	angular.module('dcoiApp').directive('textfieldEdit', textfieldEdit);
	
	function textfieldEdit(){
		return {
			scope: {
				fieldName: "@",
				fieldId: "@",
				fieldRequired: "@",
				pastQuarterValue: "@",
				newQuarterModel: "=",
				inputAddon: "@",
				errorData: "@",
				errorMessagesProp: "@"
			},
			templateUrl: "app/datacenter/sections/fields/textfield-edit.html"
		}
	}
})();

