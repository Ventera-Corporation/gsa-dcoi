(function(){
	'use strict';
	
	angular.module('dcoiApp').directive('textfieldEdit', textfieldEdit);
	
	function textfieldEdit(){
		return {
			scope: {
				fieldName: "@",
				fieldId: "@",
				fieldRequired: "@",
				fieldType: "@",
				pastQuarterValue: "@",
				newQuarterModel: "=",
				placeholderText: "@",
				inputAddonFront: "@",
				inputAddonBack: "@",
				errorMessage: "="
			},
			templateUrl: "app/datacenter/sections/fields/textfield-edit.html"
		}
	}
})();

