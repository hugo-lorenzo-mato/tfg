(function () {
    'use strict';

    angular
        .module('app')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('formularios', {
            parent: 'admin',
            url: '/admin/formularios',
            data: {
                authorities: ['ROLE_ADMIN', 'ROLE_CATALOGADOR'],
                pageTitle: 'formularios.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/dominio/formularios/formularios.html',
                    controller: 'FormulariosController',
                    controllerAs: 'ctrl'
                }
            },
            ncyBreadcrumb: {
                skip: true
            }
        });
    }
})();
