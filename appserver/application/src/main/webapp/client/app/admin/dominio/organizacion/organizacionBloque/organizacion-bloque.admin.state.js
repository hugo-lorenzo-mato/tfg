(function () {
    'use strict';

    angular
        .module('app')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('organizacionBloqueParent', {
            parent: 'admin',
            data: {
                authorities: ['ROLE_ADMIN', 'ROLE_CATALOGADOR'],
                pageTitle: 'organizacionBloque.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/dominio/organizacion/organizacionBloque/organizacion-bloque.admin.html',
                    controller: 'organizacionBloqueController',
                    controllerAs: 'ctrl'
                }
            },
            ncyBreadcrumb: {
                skip: true
            }
        });
    }
})();
