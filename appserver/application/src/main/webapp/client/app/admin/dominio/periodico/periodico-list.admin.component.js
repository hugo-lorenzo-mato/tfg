(function (){
    'use strict';

    angular
        .module('app')
        .component('periodicoListAdmin', {
            templateUrl: 'app/admin/dominio/periodico/periodico-list.admin.html',
            controller: Controller,
            controllerAs: 'ctrl'
        });

    /* @ngInject */
    function Controller($stateParams, NG_TABLE_DEFAULT_PARAMS, NgTableParams, NgTableHelper,
                        ModalService, Periodico) {
        var vm = this;

        vm.item = Periodico;
        vm.pagina = $stateParams.page ? $stateParams.page : 1;
        vm.elementosPorPagina = NG_TABLE_DEFAULT_PARAMS.size;
        vm.filter = {
            key: undefined,
            concluido: undefined,
            revisado: undefined
        };
        vm.tableParams = new NgTableParams({
            count: vm.elementosPorPagina,
            page: vm.pagina,
            filter: vm.filter,
            sorting: {titulo: 'asc'}
        }, NgTableHelper.settings(vm));

        // Eliminar
        vm.showRemoveConfirmation = function (id) {
            ModalService.open({
                    templateUrl: 'app/components/form/delete/entity.delete.modal.html',
                    controller: 'EntityDeleteModalController',
                    controllerAs: 'ctrl',
                    resolve: {
                        /* @ngInject */
                        item: function (Periodico) {
                            return Periodico.get({id: id}).$promise;
                        },
                        params: function () {
                            return {
                                title: 'admin.Periodico.delete.title',
                                body: 'admin.Periodico.delete.confirm',
                                property: 'titulo'
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
