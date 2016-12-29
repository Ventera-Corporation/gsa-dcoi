(function(){
	'use strict';
	angular.module('dcoiApp', [ 'ngRoute', 'ngResource', 'ngSanitize', 'ui.bootstrap', 'ui.select', 'http-auth-interceptor','datatables']);
	
	angular.module('dcoiApp').constant('USER_ROLES', {
	    all: '*',
	    admin: 'ADMIN',
	    user: 'USER',
	    facility: 'FACILITY',
	    server: 'SERVER'
	});
	
	
	angular.module('dcoiApp').run(function ($rootScope, $location, $http, AuthenticationService, Session, $q, $timeout, DTDefaultOptions) {

	    $rootScope.settingsCollapse = false;
	    $rootScope.$on('$routeChangeStart', function (event, next) {

	        if(next.originalPath === "/login" && $rootScope.authenticated) {
	            event.preventDefault();
	        } else if (next.access && next.access.loginRequired && !$rootScope.authenticated) {
	            event.preventDefault();
	            $rootScope.$broadcast("event:auth-loginRequired", {});
	        } else if (next.access && !AuthenticationService.isAuthorized(next.access.authorizedRoles)) {
	            event.preventDefault();
	            $rootScope.$broadcast("event:auth-forbidden", {});
	        }
	    });

	    $rootScope.$on('$routeChangeSuccess', function (scope, next, current) {
	        $rootScope.$evalAsync(function () {
	        });
	    });

	    // Call when the the client is confirmed
	    $rootScope.$on('event:auth-loginConfirmed', function (event, data) {
	        $rootScope.loadingAccount = false;
	        var nextLocation = ($rootScope.requestedUrl ? $rootScope.requestedUrl : "/dashboard");
	        var delay = ($location.path() === "/dashboard" ? 1000 : 0);

	        $timeout(function () {
	            Session.create(data);
	            $rootScope.account = Session;
	            $rootScope.authenticated = true;
	            $location.path(nextLocation).replace();
	        }, delay);

	    });

	    // Call when the 401 response is returned by the server
	    $rootScope.$on('event:auth-loginRequired', function (event, data) {
	        if ($rootScope.loadingAccount && data.status !== 401) {
	            $rootScope.requestedUrl = $location.path()
	            $location.path('/dashboard');
	        } else {
	            Session.invalidate();
	            $rootScope.authenticated = false;
	            $rootScope.loadingAccount = false;
	            $location.path('/login');
	        }
	    });

	    // Call when the 403 response is returned by the server
	    $rootScope.$on('event:auth-forbidden', function (rejection) {
	        $rootScope.$evalAsync(function () {
	            $location.path('/error/403').replace();
	        });
	    });

	    // Call when the user logs out
	    $rootScope.$on('event:auth-loginCancelled', function () {
	        $location.path('/login').replace();
	    });

	    // Get already authenticated user account
	    AuthenticationService.getAccount();
	    
	    // Default DataTable Options
	    DTDefaultOptions.setOption("deferRender", true);
	    DTDefaultOptions.setOption("scrollX", true);
	    // Pagination
	    DTDefaultOptions.setOption("paging", true);
	    DTDefaultOptions.setOption("paginationType", "full_numbers");
	    DTDefaultOptions.setOption("displayLength", 10);
	    DTDefaultOptions.setOption("order", [[ 0, "desc" ]]);
	    // Labels
	    var language = {
            "sEmptyTable":     "No available data",
            "sInfo":           "Showing _START_ to _END_ of _TOTAL_ records",
            "sInfoEmpty":      "0 records",
            "sInfoFiltered":   "(filtered from _MAX_ total records)",
            "sInfoPostFix":    "",
            "sInfoThousands":  ",",
            "sLengthMenu":     "Records per page:_MENU_",
            "sLoadingRecords": "Loading...",
            "sProcessing":     "Processing...",
            "sSearch":         "Query:",
            "sZeroRecords":    "No records found",

        }
	    DTDefaultOptions.setOption("language", language);
	});
})();
