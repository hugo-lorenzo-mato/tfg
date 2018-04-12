(function () {
    'use strict';

    angular
        .module('app')
        .component('citaPeriodicoFormAdmin', {
            templateUrl: 'app/admin/dominio/citas/citaPeriodico/citaPrototexto-form.admin.html',
            controller: Controller,
            controllerAs: 'ctrl',
            bindings: {
                item: '<',
                previousParams: '<'
            }
        });

    /* @ngInject */
    function Controller($timeout, $state, ModalService) {
        var vm = this;

        $timeout(function () {
            angular.element('.form-group:eq(0) input').focus();
        });

        vm.mode = $state.current.data.mode;
        vm.canSave = vm.mode === 'create' || vm.mode === 'edit';

        // Guardar formulario
        vm.save = function () {
            vm.item.$save().then(function () {
                ModalService.closeComponent(vm.item, 'admin/citaPrototexto-periodico/list', vm.previousParams);
            });
        };
    }

})();
