(function(){
	'use strict';
	
	angular.module('dcoiApp').directive('messages', messages);
	
	function messages(){
		return {
			scope: {
				successData: '=',
				errorData: '=',
			},
			controller: function ($scope){
				$scope.remove = function(messageList, index){
					messageList.splice(index, 1);
				};
			},
			templateUrl: 'app/common/messages.html'
		}
	}
})();

