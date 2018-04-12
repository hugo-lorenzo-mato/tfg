(function () {
    'use strict';

    angular
        .module('app')
        .component('organizacionFormAdmin', {
            templateUrl: 'app/admin/dominio/organizacion/organizacion-form.admin.html',
            controller: Controller,
            controllerAs: 'ctrl',
            bindings: {
                item: '<',
                tipologias: '<',
                previousParams: '<'
            }
        });

    /* @ngInject */
    function Controller($timeout, $state, ModalService, Tipologia, TIPOLOGIA_FORM_ADMIN) {

        var vm = this;

        $timeout(function () {
            angular.element('.form-group:eq(0) input').focus();
        });

        vm.mode = $state.current.data.mode;
        vm.canSave = vm.mode === 'create' || vm.mode === 'edit';


        // Guardar formulario
        vm.save = function () {
            vm.item.$save().then(function () {
                ModalService.closeComponent(vm.item, 'admin/organizacion/list', vm.previousParams);
            });
        };

        vm.openTipologiaModal = function () {
            ModalService.openComponent('admin.tipologia.create.title', TIPOLOGIA_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
                // Asociamos el nuevo elemento a la entidad
                vm.item.tipologia = result;
            });
        };

        vm.tipologiaService = Tipologia;
    }

})();
