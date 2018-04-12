(function () {
    'use strict';

    angular
        .module('app')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('agenteBloqueParent', {
            parent: 'admin',
            data: {
                authorities: ['ROLE_ADMIN', 'ROLE_CATALOGADOR'],
                pageTitle: 'agenteBloque.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/dominio/agente/agente-bloque.admin.html',
                    controller: 'AgenteBloqueController',
                    controllerAs: 'ctrl'
                }
            },
            ncyBreadcrumb: {
                skip: true
            }
        });
    }
})();
