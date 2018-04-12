(function () {
    'use strict';

    angular
        .module('app')
        .factory('Principal', Principal);

    /* @ngInject */
    function Principal($q, Account, $rootScope) {
        var _identity,
            _authenticated = false;

        var service = {
            authenticate: authenticate,
            hasAnyAuthority: hasAnyAuthority,
            hasAuthority: hasAuthority,
            identity: identity,
            isAuthenticated: isAuthenticated,
            isIdentityResolved: isIdentityResolved
        };

        return service;

        function authenticate(identity) {
            _identity = identity;
            _authenticated = identity !== null;
            $rootScope.$broadcast('changedIdentity');
        }

        function hasAnyAuthority(authorities) {
            if (authorities.indexOf("ROLE_ANONYMOUS") >= 0 && !_authenticated && !_identity) {
                return true;
            }

            if (!_authenticated || !_identity || !_identity.authorities) {
                return false;
            }

            if (authorities.indexOf("ROLE_AUTHENTICATED") >= 0
                && _identity.authorities && _identity.authorities.length > 0) {
                return true;
            }

            for (var i = 0; i < authorities.length; i++) {
                if (_identity.authorities.indexOf(authorities[i]) !== -1) {
                    return true;
                }
            }

            return false;
        }

        function hasAuthority(authority) {
            if (!_authenticated) {
                return $q.when(false);
            }

            return this.identity().then(function (_id) {
                return _id.authorities && _id.authorities.indexOf(authority) !== -1;
            }, function () {
                return false;
            });
        }

        function identity(force, login) {
            var deferred = $q.defer();

            if (force === true) {
                _identity = undefined;
            }

            // check and see if we have retrieved the identity data from the server.
            // if we have, reuse it by immediately resolving
            if (angular.isDefined(_identity)) {
                deferred.resolve(_identity);

                return deferred.promise;
            }

            // retrieve the identity data from the server, update the identity object, and then resolve.
            Account.get({login: login}).$promise
                .then(getAccountThen)
                .catch(getAccountCatch);

            return deferred.promise;

            function getAccountThen(account) {
                _identity = account.data;
                _authenticated = true;
                deferred.resolve(_identity);
                $rootScope.$broadcast('changedIdentity');
            }

            function getAccountCatch() {
                _identity = null;
                _authenticated = false;
                deferred.resolve(_identity);
                $rootScope.$broadcast('changedIdentity');
            }
        }

        function isAuthenticated() {
            return _authenticated;
        }

        function isIdentityResolved() {
            return angular.isDefined(_identity);
        }
    }
})();
