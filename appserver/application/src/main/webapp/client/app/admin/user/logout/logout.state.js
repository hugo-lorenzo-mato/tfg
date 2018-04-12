(function () {
    'use strict';

    angular
        .module('app')
        .config(stateConfig);

    /* @ngInject */
    function stateConfig($stateProvider) {
        $stateProvider.state('logout', {
            parent: 'public',
            data: {
                authorities: ['ROLE_AUTHENTICATED']
            },
            views: {
                'content@': {
                    controller: 'LogoutController'
                }
            }
        });
    }
})();
