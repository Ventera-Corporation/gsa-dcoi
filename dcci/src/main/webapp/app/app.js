var dcciApp_module = angular.module("dcciApp", []);

dcciApp_module.controller("MainController", function ($scope) {

    $scope.loginPage = true;

});


dcciApp_module.controller("LoginController", function ($scope) {

    $scope.loginPage = false;

    $scope.initLoginPage = function () {
        $("#login-button-id").on("click", function () {

            // If either of the login fields are empty, then error message is shown, if it's not already shown.
            if (document.getElementById("password-id").value == "" || document.getElementById("user-id").value == "") {

                // If the error message is hidden, then show it
                if ($("#failed-login-notice-id").hasClass("hidden")) {

                    // Show it
                    $("#failed-login-notice-id").removeClass("hidden");
                }
            } else {
                //window.location = "module-selection-page.html";
            }
        });

        // Hide error message on focus of the password-id
        $("#password-id").focus(function () {

            // If the error message is shown
            if (!$("#failed-login-notice-id").hasClass("hidden")) {

                // Hide it
                $("#failed-login-notice-id").addClass("hidden");
            }
        });

        // Hide error message on focus of the user-id
        $("#user-id").focus(function () {

            // If the error message is shown
            if (!$("#failed-login-notice-id").hasClass("hidden")) {

                // Hide it
                $("#failed-login-notice-id").addClass("hidden");
            }
        });
    };

    $scope.initLoginPage();


    /* 
    */


    // Set the default value of inputType
    $scope.loginPasswordType = 'password';

    // Hide & show password function
    $scope.hideShowPassword = function () {
        if ($scope.loginPasswordType == 'password') {
            $scope.loginPasswordType = 'text';
        } else {
            $scope.loginPasswordType = 'password';
        }
    };


});