(function () {
    'use strict';

    angular
        .module('app')
        .component('funcionAgenteListAdmin', {
            templateUrl: 'app/admin/dominio/funciones/funcionAgente/funcionAgente-list.admin.html',
            controller: Controller,
            controllerAs: 'ctrl'
        });

    /* @ngInject */
    function Controller($stateParams, NG_TABLE_DEFAULT_PARAMS, NgTableParams, NgTableHelper, ModalService, FuncionAgente) {
        var vm = this;

        vm.item = FuncionAgente;
        vm.pagina = $stateParams.page ? $stateParams.page : 1;
        vm.elementosPorPagina = NG_TABLE_DEFAULT_PARAMS.size;
        vm.filter = {
            key: undefined
        };
        vm.tableParams = new NgTableParams({
            count: vm.elementosPorPagina,
            page: vm.pagina,
            filter: vm.filter,
            sorting: {nombre: 'asc'}
        }, NgTableHelper.settings(vm));

        // Eliminar
        vm.showRemoveConfirmation = function (id) {
            ModalService.open({
                    templateUrl: 'app/components/form/delete/entity.delete.modal.html',
                    controller: 'EntityDeleteModalController',
                    controllerAs: 'ctrl',
                    resolve: {
                        /* @ngInject */
                        item: function (FuncionAgente) {
                            return FuncionAgente.get({id: id}).$promise;
                        },
                        params: function () {
                            return {
                                title: 'admin.funcionAgente.delete.title',
                                body: 'admin.funcionAgente.delete.confirm',
                                property: 'nombre'
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
