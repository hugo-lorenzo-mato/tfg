(function () {
    'use strict';

    angular
        .module('app')
        .factory('authExpiredInterceptor', authExpiredInterceptor);


    authExpiredInterceptor.$inject = ['$q', '$injector', '$localStorage', '$sessionStorage'];

    function authExpiredInterceptor($q, $injector, $localStorage, $sessionStorage) {
        var service = {
            responseError: responseError
        };

        return service;

        function responseError(response) {
            if (response.status === 401) {
                delete $localStorage.authenticationToken;
                delete $sessionStorage.authenticationToken;
                var Principal = $injector.get('Principal');
                if (Principal.isAuthenticated()) {
                    var Auth = $injector.get('Auth');
                    Auth.authorize(true);
                }
            }
            return $q.reject(response);
        }
    }
})();
