(function () {
    'use strict';

    // item: Elemento del que se muestra información
    // params: JSON de la forma {title: '', body: '', property: ''}
    // tipo: Resource que tiene la función
    // funcion: String con el nombre de la funcion
    // parametros: Parameteros que se enviará la a función de confirmación

    angular.module('app')
        .controller('ConfirmModalController', Controller);

    /* @ngInject */
    function Controller(item, params, tipo, funcion, parametros, ModalService) {
        var vm = this;

        vm.dismiss = ModalService.dismiss;
        vm.item = item;
        vm.params = params;

        vm.confirm = function () {
            tipo[funcion](parametros).$promise.then(function () {
                ModalService.close(vm.item);
            });
        };
    }
})();
