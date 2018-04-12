(function () {
    'use strict';

    angular
        .module('app')
        .component('libroFormAdmin', {
            templateUrl: 'app/admin/dominio/libro/libro-form.admin.html',
            controller: Controller,
            controllerAs: 'ctrl',
            bindings: {
                item: '<',
                previousParams: '<',
                genero: '<',
                assunto:'<',
                ciudad: '<',
                pais: '<',
                tipos: '<',
                agente: '<',
                funcionOrganizacion: '<',
                funcionAgente: '<',
                organizacion: '<',
                tipologia: '<'

            }
        });

    /* @ngInject */
    function Controller($timeout, $state, RutasService, ModalService, CitaLibro, Ciudad, CIUDAD_FORM_ADMIN, Pais,
                        PAIS_FORM_ADMIN, Agente, AGENTE_FORM_ADMIN, Organizacion, ORGANIZACION_FORM_ADMIN,
                        FuncionAgente, FUNCIONAGENTE_FORM_ADMIN, FuncionOrganizacion, FUNCIONORGANIZACION_FORM_ADMIN,
                        Tipologia, TIPOLOGIA_FORM_ADMIN, SubidaLibro ) {
        var vm = this;

        $timeout(function () {
            angular.element('.form-group:eq(0) input').focus();
        });

        vm.mode = $state.current.data.mode;
        vm.canSave = vm.mode === 'create' || vm.mode === 'edit';

        /*Campos para subidas de archivos: imágenes, PDFs, audios, etc.*/

        vm.urlBaseCompleto = RutasService.getLibroCompletoUrl(vm.item.id);
        vm.urlBaseTranscripcion = RutasService.getLibroTranscripcionUrl(vm.item.id);
        vm.urlBaseGenetica = RutasService.getLibroGeneticaUrl(vm.item.id);
        vm.urlBaseImagen = RutasService.getLibroImagenUrl(vm.item.id);
        vm.urlBaseLibroOtros = RutasService.getLibroOtrosUrl(vm.item.id);

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
                ModalService.closeComponent(vm.item, 'admin/libro/list', vm.previousParams);
            });
        };

        vm.openCiudadModal = function () {
            ModalService.openComponent('admin.ciudad.create.title', CIUDAD_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
                // Asociamos el nuevo elemento a la entidad
                vm.item.ciudad = result;
            });
        };

        vm.openPaisModal = function () {
            ModalService.openComponent('admin.pais.create.title', PAIS_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
                // Asociamos el nuevo elemento a la entidad
                vm.item.pais = result;
            });
        };


        vm.openAgenteModal = function (agente) {
            ModalService.openComponent('admin.agente.create.title', AGENTE_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
                // Asociamos el nuevo elemento a la entidad
                vm.item.agente = result;
            });
        };

        vm.openOrganizacionModal = function () {
            ModalService.openComponent('admin.organizacion.create.title', ORGANIZACION_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
                // Asociamos el nuevo elemento a la entidad
                vm.item.organizacion = result;
            });
        };


        vm.openFuncionAgenteModal = function () {
            ModalService.openComponent('admin.funcionAgente.create.title', FUNCIONAGENTE_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
                // Asociamos el nuevo elemento a la entidad
                vm.item.funcionAgente = result;
            });
        };


        vm.openFuncionOrganizacionModal = function () {
            ModalService.openComponent('admin.funcionOrganizacion.create.title', FUNCIONORGANIZACION_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
                // Asociamos el nuevo elemento a la entidad
                vm.item.funcionOrganizacion = result;
            });
        };


        vm.openTipologiaModal = function () {
            ModalService.openComponent('admin.tipologia.create.title', TIPOLOGIA_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
                // Asociamos el nuevo elemento a la entidad
                vm.item.tipologia = result;
            });
        };

        vm.paisService = Pais;
        vm.ciudadService = Ciudad;
        vm.citaLibroService = CitaLibro;
        vm.agenteService = Agente;
        vm.organizacionService = Organizacion;
        vm.funcionAgenteService = FuncionAgente;
        vm.funcionOrganizacionService = FuncionOrganizacion;
        vm.tipologiaService = Tipologia;
        vm.subidaService = SubidaLibro;
    }

})();
