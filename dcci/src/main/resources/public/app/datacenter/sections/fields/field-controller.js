(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('FieldController', FieldController);

	FieldController.$inject = ['$filter'];
	
	function FieldController($filter){
		var fc = this;
		fc.findPastValueForDataCenter = findPastValueForDataCenter;
		fc.getValueFromRefValueListForValueId = getValueFromRefValueListForValueId;
		
		fc.findPastValueForDataCenter();

		function findPastValueForDataCenter(){
			var foundDataCenter = $filter('filter')(fc.pastDataCenters, 
					{'dataCenterId':fc.dataCenter.dataCenterId}, true)[0];
			if(foundDataCenter){
				if(fc.fieldOffice){
					var foundFieldOffice = $filter('filter')(foundDataCenter.fieldOffices,
							{'fieldOfficeName':fc.fieldOffice.fieldOfficeName}, true)[0];
					if(foundFieldOffice){
						fc.pastQuarterValue = foundFieldOffice[fc.sectionPropName][fc.fieldPropName];
					}
				} else if(fc.totals === 'true'){
					fc.pastQuarterValue = foundDataCenter.totals[fc.sectionPropName][fc.fieldPropName];
				} else {
					fc.pastQuarterValue = foundDataCenter[fc.sectionPropName][fc.fieldPropName];
				}
				
				if(fc.fieldFilter){
					fc.pastQuarterValue = $filter(fc.fieldFilter)(fc.pastQuarterValue);
				}
			}
		}
		
		function getValueFromRefValueListForValueId(id){
			if(id){
				var foundRefValue = $filter('filter')(fc.refValueList, {'id':id}, true);
				if(foundRefValue){
					return foundRefValue[0].value;
				}
			}
			return "None";
		}
	}
})();