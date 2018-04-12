(function () {
    'use strict';

    angular.module('app')
        .controller('LogoutController', LogoutController);

    /* @ngInject */
    function LogoutController($state, Auth) {
        Auth.logout();

        $state.go('login');
    }
})();
