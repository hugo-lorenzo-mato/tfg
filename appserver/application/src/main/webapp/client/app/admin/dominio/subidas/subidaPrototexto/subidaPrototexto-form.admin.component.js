/*
(function () {
    'use strict';

    angular
        .module('app')
        .component('subidaPrototextoFormAdmin', {
            templateUrl: 'app/admin/dominio/subidas/subidaPrototexto/subidaPrototexto-form.admin.html',
            controller: Controller,
            controllerAs: 'ctrl',
            bindings: {
                item: '<',
                previousParams: '<'
            }
        });

*/    /* @ngInject */
/*    function Controller($timeout, $state, ModalService, RutasService, SubidaPrototexto) {
        var vm = this;

        vm.urlBasePrototextoOtros = RutasService.getBasePrototextoOtrosUrl(vm.item.id);

        $timeout(function () {
            angular.element('.form-group:eq(0) input').focus();
        });

        vm.subidaPrototextService = SubidaPrototexto;

        vm.urlBasePrototextoOtros = RutasService.getBasePrototextoOtrosUrl(vm.item.id);

        vm.mode = $state.current.data.mode;
        vm.canSave = vm.mode === 'create' || vm.mode === 'edit';

        // Guardar formulario
        vm.save = function () {
            vm.item.$save().then(function () {
                ModalService.closeComponent(vm.item, 'admin/subidas/subidaPrototexto/list', vm.previousParams);
            });
        };
        vm.subidaService = SubidaPrototexto;

    }


})();
*/
