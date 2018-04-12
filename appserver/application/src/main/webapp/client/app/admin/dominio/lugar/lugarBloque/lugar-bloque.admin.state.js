(function () {
    'use strict';

    angular
        .module('app')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('lugarBloqueParent', {
            parent: 'admin',
            data: {
                authorities: ['ROLE_ADMIN', 'ROLE_CATALOGADOR'],
                pageTitle: 'lugarBloque.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/dominio/lugar/lugarBloque/lugar-bloque.admin.html',
                    controller: 'lugarBloqueController',
                    controllerAs: 'ctrl'
                }
            },
            ncyBreadcrumb: {
                skip: true
            }
        });
    }
})();
