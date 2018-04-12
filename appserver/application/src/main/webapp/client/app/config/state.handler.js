(function (angular) {
    'use strict';

    angular.module('app').factory('stateHandler', factory);

    /* @ngInject */
    function factory($state, $window, $timeout, $rootScope, $translate, Principal, Auth, AlertService) {
        var _states;

        return {
            stateExists: stateExists,
            initialize: initialize
        };

        function initialize() {
            $timeout(function () {
                _states = $state.get().map(function (item) {
                    return item.name;
                });
            }, 0);
            // in every HTML view we can check for a feature/state and prevent
            // showing a link if it's not enabled
            $rootScope.stateExists = stateExists;

            var deregistrationStateChangeStart = $rootScope.$on('$stateChangeStart',
                function (event, toState, toStateParams) {
                    AlertService.clearPermanent();

                    if (toState.redirectTo && navigator.onLine) {
                        event.preventDefault();
                        $state.go(toState.redirectTo, toStateParams);
                    }

                    $rootScope.toState = toState;
                    $rootScope.toStateParams = toStateParams;

                    if (Principal.isIdentityResolved()) {
                        Auth.authorize();
                    }
                }
            );
            $rootScope.$on('$destroy', deregistrationStateChangeStart);

            var deregistrationStateChangeSuccess = $rootScope.$on('$stateChangeSuccess',
                function (event, toState, toParams, fromState, fromParams) {
                    var titleKey = 'global.title';

                    if (!$rootScope.redirected && fromState.name !== 'logout' && fromState.name !== 'login') {
                        $rootScope.previousStateName = fromState.name;
                        $rootScope.previousStateParams = fromParams;
                    }

                    // Set the page title key to the one configured in state or use default one
                    if (toState.data.pageTitle) {
                        titleKey = toState.data.pageTitle;
                    }
                    $translate(titleKey).then(function (title) {
                        // Change window title with translated one
                        $window.document.title = $translate.instant('global.title') + " - " + title;
                    });
                    $("html, body").scrollTop(0);
                }
            );
            $rootScope.$on('$destroy', deregistrationStateChangeSuccess);

            $rootScope.back = function () {
                // If previous state is 'activate' or do not exist go to 'home'
                if ($rootScope.previousStateName === 'activate' || $state.get($rootScope.previousStateName) === null) {
                    $state.go('home');
                } else {
                    $state.go($rootScope.previousStateName, $rootScope.previousStateParams);
                }
            };
        }

        function stateExists(state) {
            return _states.indexOf(state) !== -1;
        }
    }
})(angular);
