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
				errorMessage: "="
			},
			templateUrl: "app/datacenter/sections/fields/selectfield-edit.html"
		}
	}
})();

