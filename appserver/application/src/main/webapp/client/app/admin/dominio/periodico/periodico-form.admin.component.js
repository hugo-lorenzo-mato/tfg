(function () {
    'use strict';

    angular
        .module('app')
        .component('periodicoFormAdmin', {
            templateUrl: 'app/admin/dominio/periodico/periodico-form.admin.html',
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
                funcionAgente: '<',
                funcionOrganizacion: '<',
                organizacion: '<',
                tipologia: '<',
            }
        });

    /* @ngInject */
    function Controller($timeout, $state, ModalService, RutasService, CitaPeriodico, Pais, PAIS_FORM_ADMIN, Ciudad, CIUDAD_FORM_ADMIN, Agente, AGENTE_FORM_ADMIN, FuncionAgente, FUNCIONAGENTE_FORM_ADMIN,
                       Organizacion, ORGANIZACION_FORM_ADMIN, FuncionOrganizacion, FUNCIONORGANIZACION_FORM_ADMIN, Tipologia, TIPOLOGIA_FORM_ADMIN, SubidaPeriodico ) {
        var vm = this;

        $timeout(function () {
            angular.element('.form-group:eq(0) input').focus();
        });

        vm.mode = $state.current.data.mode;
        vm.canSave = vm.mode === 'create' || vm.mode === 'edit';


        /*Campos para subidas de archivos: imágenes, PDFs, audios, etc.*/

        vm.urlBaseCompleto = RutasService.getPeriodicoCompletoUrl(vm.item.id);
        vm.urlBaseTranscripcion = RutasService.getPeriodicoTranscripcionUrl(vm.item.id);
        vm.urlBaseGenetica = RutasService.getPeriodicoGeneticaUrl(vm.item.id);
        vm.urlBaseImagen = RutasService.getPeriodicoImagenUrl(vm.item.id);
        vm.urlBasePeriodicoOtros = RutasService.getPeriodicoOtrosUrl(vm.item.id);

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
                ModalService.closeComponent(vm.item, 'admin/periodico/list', vm.previousParams);
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


        vm.openAgenteModal = function () {
            ModalService.openComponent('admin.agente.create.title', AGENTE_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
                // Asociamos el nuevo elemento a la entidad
                vm.item.agente = result;
            });
        };

        vm.openFuncionAgenteModal = function () {
            ModalService.openComponent('admin.funcionAgente.create.title', FUNCIONAGENTE_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
                // Asociamos el nuevo elemento a la entidad
                vm.item.funcionAgente = result;
            });
        };


        vm.openOrganizacionModal = function () {
            ModalService.openComponent('admin.organizacion.create.title', ORGANIZACION_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
                // Asociamos el nuevo elemento a la entidad
                vm.item.organizacion = result;
            });
        };


        vm.openFuncionOrganizacionModal = function () {
            ModalService.openComponent('admin.funcionOrganizacion.create.title', FUNCIONORGANIZACION_FORM_ADMIN, {size: 'lg'}).result.then(function (result) {
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
        vm.citaPeriodicoService = CitaPeriodico;
        vm.agenteService = Agente;
        vm.funcionAgenteService = FuncionAgente;
        vm.organizacionService = Organizacion;
        vm.funcionOrganizacionService = FuncionOrganizacion;
        vm.tipologiaService = Tipologia;
        vm.subidaLibroService = SubidaPeriodico;

    }

})();
