(function () {
    'use strict';

    angular
        .module('app')
        .component('edicionFormAdmin', {
            templateUrl: 'app/admin/dominio/edicion/edicion-form.admin.html',
            controller: Controller,
            controllerAs: 'ctrl',
            bindings: {
                item: '<',
                previousParams: '<',
                agente: '<',
                ciudad: '<',
                pais: '<'
            }
        });

    /* @ngInject */
    function Controller($timeout, $state, ModalService, Ciudad, CIUDAD_FORM_ADMIN, Pais, PAIS_FORM_ADMIN, Agente, AGENTE_FORM_ADMIN, Funcion, FUNCION_FORM_ADMIN) {

        var vm = this;

        $timeout(function () {
            angular.element('.form-group:eq(0) input').focus();
        });

        vm.mode = $state.current.data.mode;
        vm.canSave = vm.mode === 'create' || vm.mode === 'edit';


        // Guardar formulario
        vm.save = function () {
            vm.item.$save().then(function () {
                ModalService.closeComponent(vm.item, 'admin/edicion/list', vm.previousParams);
            });
        };

        vm.openCiudadModal = function () {
            ModalService.openComponent('admin.ciudad.create.title', CIUDAD_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
                vm.item.ciudad = result;
            });
        };


        vm.openPaisModal = function () {
            ModalService.openComponent('admin.pais.create.title', PAIS_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
                // Asociamos el nuevo elemento a la entidad
                vm.item.pais = result;
            });
        };


        vm.openAgenteModal = function () {
            ModalService.openComponent('admin.agente.create.title', AGENTE_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
                // Asociamos el nuevo elemento a la entidad
                agente = result;
            });
        };

        /*
        vm.openFuncionModal = function () {
            ModalService.openComponent('admin.agente.create.title', FUNCION_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
                // Asociamos el nuevo elemento a la entidad
                funcion = result;
            });
        };
        */

        vm.paisService = Pais;
        vm.ciudadService = Ciudad;
        vm.agenteService = Agente;
        vm.funcionService = Funcion;


    }

})();
/**
JSON parse error: Can not deserialize instance of java.util.HashSet out of START_OBJECT token; nested exception is
com.fasterxml.jackson.databind.JsonMappingException: Can not deserialize instance of java.util.HashSet out of START_OBJECT
token at [Source: java.io.PushbackInputStream@444be3b0; line: 1, column: 522] (through reference chain:
es.enxenio.GGAL1701.model.dominio.edicion.Edicion["agentes"]->java.util.HashSet[0]->es.enxenio.GGAL1701.model.dominio.agente.Agente["funciones"])
**/
