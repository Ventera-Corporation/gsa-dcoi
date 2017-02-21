(function(){
	'use strict';
	
	angular.module('dcoiApp').controller('EditUserInfoModalController', EditUserInfoModalController);
	
	EditUserInfoModalController.$inject = ['$uibModalInstance', 'initData'];
	
	function EditUserInfoModalController($uibModalInstance, initData){
		var euimc = this;
		euimc.user = initData.userData;
		euimc.roleRefValueList = initData.roleRefValueList;
		euimc.componentRefValueList = initData.componentRefValueList;
		euimc.initRoles = initRoles;
		euimc.initFieldOffices = initFieldOffices;
		euimc.cancel = cancel;
		euimc.checkUncheckRole = checkUncheckRole;
		euimc.checkUncheckFieldOffice = checkUncheckFieldOffice;
		euimc.removeNullRoleValues = removeNullRoleValues;
		euimc.removeNullFieldOfficeValues = removeNullFieldOfficeValues;
		euimc.save = save;
		
		function initRoles(activeRefRoles){
			euimc.tempRoles = [];
			
			angular.forEach(activeRefRoles, function(role){
				var isCurrentRole = false;
				angular.forEach(euimc.user.roles, function(userRole){
					if(userRole.dcoiRoleId === role.id){
						isCurrentRole = true;
					}
				});
				if(isCurrentRole){
					euimc.tempRoles.push({
						dcoiRoleId: role.id, 
						roleName: role.value
					});
				} else {
					euimc.tempRoles.push({
						dcoiRoleId: null, 
						roleName: null
					});
				}
			});
		}
		
		function initFieldOffices(activeRefFieldOffices){
			euimc.tempFieldOffices = [];
			angular.forEach(activeRefFieldOffices, function(fieldOffice){
				if(euimc.user.userFieldOffices.indexOf(fieldOffice.value) > -1){
					euimc.tempFieldOffices.push({
						componentId: fieldOffice.id, 
						fieldOfficeName: fieldOffice.value
					});
				} else {
					euimc.tempFieldOffices.push({
						componentId: null, 
						fieldOfficeName: null
					});
				}
			});
		}
		
		function cancel() {
			$uibModalInstance.dismiss('cancel');
		}
		
		function checkUncheckRole(index, roleName){
			euimc.tempRoles[index]['roleName'] === roleName
					? euimc.tempRoles[index]['roleName'] = null
					: euimc.tempRoles[index]['roleName'] = roleName;
		}
		
		function checkUncheckFieldOffice(index, fieldOfficeName){
			euimc.tempFieldOffices[index]['fieldOfficeName'] === fieldOfficeName
					? euimc.tempFieldOffices[index]['fieldOfficeName'] = null
					: euimc.tempFieldOffices[index]['fieldOfficeName'] = fieldOfficeName;
		}

		function removeNullRoleValues(roles){
			var cleanRoles = [];
			angular.forEach(roles, function(role){
				if(role.dcoiRoleId !== null && role.roleName !== null){
					cleanRoles.push(role);
				}
			});
			return cleanRoles;
		}

		function removeNullFieldOfficeValues(fieldOffices){
			var cleanFieldOffices = [];
			angular.forEach(fieldOffices, function(fieldOffice){
				if(fieldOffice.componentId !== null && fieldOffice.fieldOfficeName !== null){
					cleanFieldOffices.push(fieldOffice);
				}
			});
			return cleanFieldOffices;
		}
		
		function save() {
			euimc.user.roles = [];
			angular.forEach(euimc.removeNullRoleValues(euimc.tempRoles), function(role){
				euimc.user.roles.push(role);
			});
			euimc.user.userFieldOffices = [];
			angular.forEach(euimc.removeNullFieldOfficeValues(euimc.tempFieldOffices), function(fieldOffice){
				euimc.user.userFieldOffices.push(fieldOffice.fieldOfficeName);
			});
			$uibModalInstance.close(euimc.user);
		}
	}
})();
