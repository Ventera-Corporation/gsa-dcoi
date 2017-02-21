'use strict';

angular.module('dcoiApp').service('AuthenticationService', function($rootScope, $http, $resource, authService, Session) {
    return {
		login : function(userName, password, rememberMe) {
		    var config = {
				params : {
				    username : userName,
				    password : password,
				    rememberme : rememberMe
				},
				ignoreAuthModule : 'ignoreAuthModule'
		    };
		    
		    $http.post('authenticate', '', config)
			    .success(function(data, status, headers, config) {
			    	authService.loginConfirmed(data);
			    }).error(function(data, status, headers, config) {
					$rootScope.authenticationError = true;
					Session.invalidate();
			    });
		},
		
		getAccount : function() {
		    $rootScope.loadingAccount = true;
		    $http.get('/security/account').then(function(response) {
		    	authService.loginConfirmed(response.data);
		    });
		},
		
		isAuthorized : function(authorizedRoles) {
		    if (!_.isArray(authorizedRoles)) {
		    	authorizedRoles = [ authorizedRoles ];
		    }
		    
		    if (_(authorizedRoles).includes('*')) {
		    	return true
		    }
		    
		    var intersection = _.intersection(Session.userRoles, authorizedRoles) 

		    return intersection.length > 0;
		},
		
		logout : function() {
		    $rootScope.authenticationError = false;
		    $rootScope.authenticated = false;
		    $rootScope.account = null;
		    $http.get('/security/logout');
		    Session.invalidate();
		    authService.loginCancelled();
		}
    };
});

angular.module('dcoiApp').service('Session', function() {
	this.create = function(data) {
		this.id = data.id;
		this.userName = data.userName;
		this.firstName = data.firstName;
		this.lastName = data.lastName;
		this.email = data.email;
		this.iconUrl = data.iconUrl;
		this.userRoles = [];
		angular.forEach(data.roles, function(value) {
			this.push(value.roleName);
		}, this.userRoles);
		this.userFieldOffices = [];
		angular.forEach(data.userFieldOffices, function(value) {
			this.push(value);
		}, this.userFieldOffices);
	};
	this.invalidate = function() {
		this.id = null;
		this.login = null;
		this.firstName = null;
		this.lastName = null;
		this.email = null;
		this.iconUrl = null;
		this.userRoles = null;
	};
	return this;
});
