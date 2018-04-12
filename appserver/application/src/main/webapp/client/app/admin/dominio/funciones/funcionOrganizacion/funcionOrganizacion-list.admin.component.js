(function () {
    'use strict';

    angular
        .module('app')
        .component('funcionOrganizacionListAdmin', {
            templateUrl: 'app/admin/dominio/funciones/funcionOrganizacion/funcionOrganizacion-list.admin.html',
            controller: Controller,
            controllerAs: 'ctrl'
        });

    /* @ngInject */
    function Controller($stateParams, NG_TABLE_DEFAULT_PARAMS, NgTableParams, NgTableHelper, ModalService, FuncionOrganizacion) {
        var vm = this;

        vm.item = FuncionOrganizacion;
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
                        item: function (FuncionOrganizacion) {
                            return FuncionOrganizacion.get({id: id}).$promise;
                        },
                        params: function () {
                            return {
                                title: 'admin.funcionOrganizacion.delete.title',
                                body: 'admin.funcionOrganizacion.delete.confirm',
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
