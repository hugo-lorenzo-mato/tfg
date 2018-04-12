/*(function () {
    'use strict';

    angular
        .module('app')
        .component('subidaPrototextoListAdmin', {
            templateUrl: 'app/admin/dominio/subidas/subidaPrototexto/subidaPrototexto-list.admin.html',
            controller: Controller,
            controllerAs: 'ctrl'
        });

*/    /* @ngInject */
/*    function Controller($stateParams, NG_TABLE_DEFAULT_PARAMS, NgTableParams, NgTableHelper,
                        ModalService) {
        var vm = this;

        vm.item = SubidaPrototexto;
        vm.pagina = $stateParams.page ? $stateParams.page : 1;
        vm.elementosPorPagina = NG_TABLE_DEFAULT_PARAMS.size;
        vm.filter = {
            key: undefined
        };
        vm.tableParams = new NgTableParams({
            count: vm.elementosPorPagina,
            page: vm.pagina,
            filter: vm.filter,
            sorting: {otroPath: 'asc'}
        }, NgTableHelper.settings(vm));

        // Eliminar
        vm.showRemoveConfirmation = function (id) {
            ModalService.open({
                    templateUrl: 'app/components/form/delete/entity.delete.modal.html',
                    controller: 'EntityDeleteModalController',
                    controllerAs: 'ctrl',
                    resolve: {
 */                       /* @ngInject */
 /*                       item: function (SubidaPrototexto) {
                            return SubidaPrototexto.get({id: id}).$promise;
                        },
                        params: function () {
                            return {
                                title: 'admin.subidas.delete.title',
                                body: 'admin.subidas.delete.confirm',
                                property: 'otroPath'
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

*/
