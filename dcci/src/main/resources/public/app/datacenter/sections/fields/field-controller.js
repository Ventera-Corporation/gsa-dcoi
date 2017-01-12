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
					{'dataCenterId':fc.dataCenter.dataCenterId}, true);
			if(foundDataCenter.length){
				//try to retrieve the past data center if it can't just say the value is null
				try {
					if(fc.fieldOffice){
						var foundFieldOffice = $filter('filter')(foundDataCenter.fieldOffices,
								{'fieldOfficeName':fc.fieldOffice.fieldOfficeName}, true);
						if(foundFieldOffice.length){
							fc.pastQuarterValue = foundFieldOffice[0][fc.sectionPropName][fc.fieldPropName];
						}
					} else if(fc.totalsName){
						fc.pastQuarterValue = foundDataCenter[0][fc.totalsName][fc.sectionPropName][fc.fieldPropName];
					} else {
						fc.pastQuarterValue = foundDataCenter[0][fc.sectionPropName][fc.fieldPropName];
					}
					
					if(fc.fieldFilter){
						fc.pastQuarterValue = $filter(fc.fieldFilter)(fc.pastQuarterValue);
					}
				} catch(err){
					fc.pastQuarterValue = undefined;
				}
			}
		}
		
		function getValueFromRefValueListForValueId(id){
			if(id > -1){
				var foundRefValue = $filter('filter')(fc.refValueList, {'id':id}, true);
				if(foundRefValue.length){
					return foundRefValue[0].value;
				}
			}
			return "None";
		}
	}
})();