(function (angular) {
    'use strict';

    angular
        .module('app')
        .component('buscadorResultados', {
            templateUrl: 'app/admin/components/buscador/buscador.resultados.list.html',
            controller: Controller,
            controllerAs: 'ctrl',
            bindings: {
                elementos: '=' ,
                filter: '='
            }
        });

    /* @ngInject */
    function Controller($stateParams, NG_TABLE_DEFAULT_PARAMS, NgTableParams,
                        NgTableHelper, ProduccionActiva, ModalService,
                        Prototexto, Periodico, Libro) {
        var vm = this;

        vm.item = ProduccionActiva;
        vm.pagina = $stateParams.page ? $stateParams.page : 1;
        vm.elementosPorPagina = NG_TABLE_DEFAULT_PARAMS.size;
        vm.tableParams = new NgTableParams({
            count: vm.elementosPorPagina,
            page: vm.pagina,
            filter: vm.filter,
            sorting: {titulo: 'asc'}
        }, NgTableHelper.settings(vm));

        // Eliminar Prototexto
        vm.showRemoveConfirmationPrototexto = function (id) {
            ModalService.open({
                    templateUrl: 'app/components/form/delete/entity.delete.modal.html',
                    controller: 'EntityDeleteModalController',
                    controllerAs: 'ctrl',
                    resolve: {
                        /* @ngInject */
                        item: function (Prototexto) {
                            return Prototexto.get({id: id}).$promise;
                        },
                        params: function () {
                            return {
                                title: 'admin.tipo.delete.title',
                                body: 'admin.tipo.delete.confirm',
                                property: 'titulo'
                            };
                        }
                    }
                }
            ).result.then(function () {
                vm.tableParams.reload();
            });
        };
        // Eliminar Periodico
        vm.showRemoveConfirmationPeriodico = function (id) {
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
                                title: 'admin.tipo.delete.title',
                                body: 'admin.tipo.delete.confirm',
                                property: 'titulo'
                            };
                        }
                    }
                }
            ).result.then(function () {
                vm.tableParams.reload();
            });
        };
        // Eliminar Libro
        vm.showRemoveConfirmationLibro = function (id) {
            ModalService.open({
                    templateUrl: 'app/components/form/delete/entity.delete.modal.html',
                    controller: 'EntityDeleteModalController',
                    controllerAs: 'ctrl',
                    resolve: {
                        /* @ngInject */
                        item: function (Libro) {
                            return Libro.get({id: id}).$promise;
                        },
                        params: function () {
                            return {
                                title: 'admin.tipo.delete.title',
                                body: 'admin.tipo.delete.confirm',
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

})(angular);
