(function() {
	'use strict';

	angular.module('dcoiApp').controller('AdminSettingsController', AdminSettingsController);

	AdminSettingsController.$inject = ['AdminService', '$uibModal', 'initData'];

	function AdminSettingsController(AdminService, $uibModal, initData) {
		var asc = this;
		asc.tempData = {};
		asc.allUsers = initData.allUsers;
		asc.editUserInfo = editUserInfo;
		asc.updateUserFromModal = updateUserFromModal;
		
		function editUserInfo(user){
			var modalInstance = $uibModal.open({
			    animation: true,
			    templateUrl: 'app/admin/edituserinfo-modal.html',
			    controller: 'EditUserInfoModalController',
			    controllerAs: 'euimc',
			    backdrop: 'static',
			    resolve: {
					initData: function(){
						return {
							userData: angular.copy(user),
							roleRefValueList: initData.roleRefValueList,
							componentRefValueList: initData.componentRefValueList
						};
					}
				}
			});
			var userData = {};//fixes browser console error that userData is not defined
			modalInstance.result.then(function(userData){
				if(userData !== 'cancel'){
					asc.updateUserFromModal(userData);
				}
			});
		}
		
		function updateUserInList(userData){
			for(var i = 0; i < asc.allUsers.length; i++){
				if(asc.allUsers[i].dcoiUserId === userData.dcoiUserId){
					asc.allUsers[i] = userData;
				}
			}
		}
		
		function updateUserFromModal(userData){
			AdminService.saveUserInfo(userData).then(function (data){
				if(data.error){
					//show errors
					asc.tempData.errorData = data;
				} else {
					//hide errors
					asc.tempData.errorData = null;
					//show success message
					asc.tempData.successData = data.successData;
					updateUserInList(userData);
				}
			});
		}
	}
})();
