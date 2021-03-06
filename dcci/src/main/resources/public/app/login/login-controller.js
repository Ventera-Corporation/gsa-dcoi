(function() {
    'use strict';
    
    angular.module('dcoiApp').controller('ErrorController', ErrorController);
    ErrorController.$inject = ['$routeParams'];
    function ErrorController($routeParams) {
    	var ec = this;
        ec.code = $routeParams.code;
        ec.message = "";
        switch (ec.code) {
    	    case "403":
    	    	ec.message = "Oops! you have come to unauthorized page."
    	    	break;
    	    case "404":
    	    	ec.message = "Page not found."
    			break;
    	    default:
    			ec.code = 500;
    	    	ec.message = "Oops! unexpected error. Please contact System Administrator"
        }

    }

    angular.module('dcoiApp').controller('LoginController', LoginController);
    LoginController.$inject = ['$rootScope', '$uibModal', 'AuthenticationService', 'Session', 'USER_ROLES'];
    function LoginController($rootScope, $uibModal, AuthenticationService, Session, USER_ROLES) {
    	var lcc = this;
    	lcc.hideShowPassword = hideShowPassword;
    	lcc.submitLogin = login;
    	lcc.rememberMe = true;
    	lcc.loginPasswordType = "password";
    	lcc.logout = logout;
    	lcc.isAdmin = isAdmin;
    	lcc.isFacilityUser = isFacilityUser;
    	lcc.isServerUser = isServerUser;
    	lcc.isFieldOfficeUser = isFieldOfficeUser;
    	lcc.isFacilityAndServerUser = isFacilityAndServerUser;
    	lcc.hasFacilityInfoAccess = hasFacilityInfoAccess;
    	lcc.hasServerInfoAccess = hasServerInfoAccess;
    	lcc.hasFieldOfficeServerInfoAccess = hasFieldOfficeServerInfoAccess;

    	function hideShowPassword() {
    		lcc.loginPasswordType = lcc.loginPasswordType === "password" ? "text" : "password";
    	}
    	
    	function login() {
    		$rootScope.logoutSuccess = null;
    		$rootScope.authenticationError = false;
    		AuthenticationService.login(
    			lcc.username,
    	        lcc.password,
    	        lcc.rememberMe
    		);
    	}
    	
    	function logout() {
			var modalInstance = $uibModal.open({
			    animation : true,
			    templateUrl : 'app/common/confirm.html',
			    backdrop : 'static',
			    controller : 'CommonController',
			    controllerAs : 'cc',
			    resolve : {
			    	message: function () {
			    		return 	"Are you sure you want to logout?";
			        }
			    }
			});
			
	     	modalInstance.result.then(function () {
	     		AuthenticationService.logout();
	     		var win = window.open(" ", "_self", '');
	     		win.close();
	     		$rootScope.logoutSuccess = true;
			});
		}

    	function isAdmin() {
        	return _.includes(Session.userRoles, USER_ROLES.admin);
        }


    	function isFacilityUser() {
        	return _.includes(Session.userRoles, USER_ROLES.facility);
        }

    	function isServerUser() {
        	return _.includes(Session.userRoles, USER_ROLES.server);
        }
    	
    	function isFieldOfficeUser(fieldOfficeName){
    		return _.includes(Session.userFieldOffices, fieldOfficeName);
    	}

    	function isFacilityAndServerUser() {
        	return lcc.isFacilityUser() && lcc.isServerUser();
        }

    	function hasFacilityInfoAccess() {
        	return lcc.isAdmin() || lcc.isFacilityUser() || lcc.isFacilityAndServerUser();
        }

    	function hasServerInfoAccess() {
        	return lcc.isAdmin() || lcc.isServerUser() || lcc.isFacilityAndServerUser();
        }

    	function hasFieldOfficeServerInfoAccess(fieldOfficeName) {
        	return lcc.isAdmin() || 
        			((lcc.isServerUser() || lcc.isFacilityAndServerUser()) && lcc.isFieldOfficeUser(fieldOfficeName));
        }
    }
})();
