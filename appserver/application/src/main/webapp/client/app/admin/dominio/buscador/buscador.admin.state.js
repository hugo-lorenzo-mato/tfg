(function () {
    'use strict';

    angular
        .module('app')
        .config(stateConfig);

    /* @ngInject */
    function stateConfig($stateProvider, NG_LIST_DEFAULT_PARAMS_PUBLIC) {
        $stateProvider.state('buscador', {
            parent: 'admin',
            url: '/admin/buscador',
            redirectTo: 'buscador/avanzado',
            data: {
                authorities: ['ROLE_ADMIN', 'ROLE_CATALOGADOR'],
                pageTitle: 'buscador.title'
            },
        }).state('buscador/avanzado', {
            parent: 'buscador',
            url: '/avanzado/p/:page',
            data: {
                pageTitle: 'buscador.title'
            },
            params: {
                page: '1',
                filtro: {size: NG_LIST_DEFAULT_PARAMS_PUBLIC.size}
            },
            views: {
                'content@': {
                    template: '<buscador-avanzado></buscador-avanzado>'
                }
            },
            ncyBreadcrumb: {
                skip: true
            }
        });
    }
})();

