(function () {
    'use strict';

    angular
        .module('app')
        .component('prototextoFormAdmin', {
            templateUrl: 'app/admin/dominio/prototexto/prototexto-form.admin.html',
            controller: Controller,
            controllerAs: 'ctrl',
            bindings: {
                item: '<',
                previousParams: '<',
                tipos: '<',
                tipoSoporte: '<',
                tipoSoporteReducido: '<',
                genero: '<',
                assunto:'<',
                ciudad: '<',
                pais: '<',
                folha: '<',
            }
        });

    /* @ngInject */
    function Controller($timeout, $state, ModalService, RutasService, Ciudad, CitaPrototexto, Pais, PAIS_FORM_ADMIN, CIUDAD_FORM_ADMIN, CITA_FORM_ADMIN, SubidaPrototexto) {

        var vm = this;

        $timeout(function () {
            angular.element('.form-group:eq(0) input').focus();
        });

        vm.mode = $state.current.data.mode;
        vm.canSave = vm.mode === 'create' || vm.mode === 'edit';

        /*Campos para subidas de archivos: imágenes, PDFs, audios, etc.*/

        vm.urlBaseCompleto = RutasService.getPrototextoCompletoUrl(vm.item.id);
        vm.urlBaseTranscripcion = RutasService.getPrototextoTranscripcionUrl(vm.item.id);
        vm.urlBaseGenetica = RutasService.getPrototextoGeneticaUrl(vm.item.id);
        vm.urlBaseImagen = RutasService.getPrototextoImagenUrl(vm.item.id);
        vm.urlBasePrototextoOtros = RutasService.getPrototextoOtrosUrl(vm.item.id);

        // Imagen
        function initImagen() {
            if (!vm.item.imagen) {
                vm.item.imagen = {};
            }
            if (vm.item.imagenPath) {
                vm.item.imagen.path = vm.item.imagenPath;
            }
        }

        initImagen();


        // Guardar formulario
        vm.save = function () {
            vm.item.$save().then(function () {
                ModalService.closeComponent(vm.item, 'admin/prototexto/list', vm.previousParams);
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


        vm.openCitaModal = function (cita) {
            ModalService.openComponent('admin.citaPrototexto.create.title', CITA_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
                // Asociamos el nuevo elemento a la entidad
                cita = result;
            });
        };

        vm.paisService = Pais;
        vm.ciudadService = Ciudad;
        vm.citaPrototextoService = CitaPrototexto;
        vm.subidaService = SubidaPrototexto;

    }

})();
