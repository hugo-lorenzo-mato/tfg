(function () {
    'use strict';

    angular
        .module('app')
        .component('citaPeriodicoListAdmin', {
            templateUrl: 'app/admin/dominio/citas/citaPeriodico/citaPrototexto-list.admin.html',
            controller: Controller,
            controllerAs: 'ctrl'
        });

    /* @ngInject */
    function Controller($stateParams, NG_TABLE_DEFAULT_PARAMS, NgTableParams, NgTableHelper,
                        ModalService, CitaPeriodico) {
        var vm = this;

        vm.item = CitaPeriodico;
        vm.pagina = $stateParams.page ? $stateParams.page : 1;
        vm.elementosPorPagina = NG_TABLE_DEFAULT_PARAMS.size;
        vm.filter = {
            key: undefined
        };
        vm.tableParams = new NgTableParams({
            count: vm.elementosPorPagina,
            page: vm.pagina,
            filter: vm.filter,
            sorting: {referenciaPasivo: 'asc'}
        }, NgTableHelper.settings(vm));

        // Eliminar
        vm.showRemoveConfirmation = function (id) {
            ModalService.open({
                    templateUrl: 'app/components/form/delete/entity.delete.modal.html',
                    controller: 'EntityDeleteModalController',
                    controllerAs: 'ctrl',
                    resolve: {
                        /* @ngInject */
                        item: function (CitaPeriodico) {
                            return CitaPeriodico.get({id: id}).$promise;
                        },
                        params: function () {
                            return {
                                title: 'admin.citaPrototexto.delete.title',
                                body: 'admin.citaPrototexto.delete.confirm',
                                property: 'referenciaPasivo'
                            };
                        }
                    }
                }
            ).result.then(function () {
                vm.tableParams.reload();
            });
        };
    }

})();
