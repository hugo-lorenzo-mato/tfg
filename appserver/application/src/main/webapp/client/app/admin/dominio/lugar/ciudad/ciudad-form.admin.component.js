(function () {
    'use strict';

    angular
        .module('app')
        .component('ciudadFormAdmin', {
            templateUrl: 'app/admin/dominio/lugar/ciudad/ciudad-form.admin.html',
            controller: Controller,
            controllerAs: 'ctrl',
            bindings: {
                item: '<',
                previousParams: '<',
                tipos: '<',
                paises: '<'
            }
        });

    /* @ngInject */
    function Controller($timeout, $state, ModalService, PAIS_FORM_ADMIN) {
        var vm = this;

        $timeout(function () {
            angular.element('.form-group:eq(0) input').focus();
        });

        vm.mode = $state.current.data.mode;
        vm.canSave = vm.mode === 'create' || vm.mode === 'edit';

        // Guardar formulario
        vm.save = function () {
            vm.item.$save().then(function () {
                ModalService.closeComponent(vm.item, 'admin/ciudad/list', vm.previousParams);
            });
        };

        vm.openPaisModal = function () {
            ModalService.openComponent('admin.pais.create.title', PAIS_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
                // Añadimos el nuevo elemento al select
                vm.paises.push(result);
                // Asociamos el nuevo elemento a la entidad
                vm.item.pais = result;
            });
        };
    }

})();
