(function () {
    'use strict';

    angular
        .module('app')

        /* @ngInject */
        .constant('PERIODICO_FORM_ADMIN', function (modal) {
            var resolve = "$resolve.";
            if (modal) {
                resolve = "ctrl.resolve."
            }
            return {
                component: '<periodico-form-admin' +
                ' item="' + resolve + 'item"' +
                ' previous-params="' + resolve + 'previousParams"' +
                ' tipos="' + resolve + 'tipos"' +
                ' ciudad="' + resolve + 'ciudad"' +
                ' pais="' + resolve + 'pais"' +
                ' genero="' + resolve + 'genero"'+
                ' agente="' + resolve + 'agente"' +
                ' funcionAgente="' + resolve + 'funcionAgente"'+
                //' funcionOrganizacion="' + resolve + 'funcionOrganizacion"'+
                //' organizacion="' + resolve + 'organizacion"'+
                //' tipologia="' + resolve + 'tipologia"'+
                ' assunto="' + resolve + 'assunto"></periodico-form-admin>',
                resolve: {

                    /* @ngInject */
                    item: function (Periodico) {
                        return new Periodico();
                    },

                    /* @ngInject */
                    tipos: function (EnumService) {
                        return EnumService.get("tipo-periodico");
                    },

                    /* @ngInject */
                    genero: function (EnumService) {
                        return EnumService.get("genero");
                    },

                    /* @ngInject */
                    assunto: function (EnumService) {
                        return EnumService.get("assunto");
                    },

                    /* @ngInject */
                    ciudad: function (Ciudad) {
                        return Ciudad.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
                            return data.content;
                        });
                    },

                    /* @ngInject */
                    agente: function (Agente) {
                        return Agente.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
                            return data.content;
                        });
                    },

                    /* @ngInject */
                    funcionAgente: function (FuncionAgente) {
                        return FuncionAgente.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
                            return data.content;
                        });
                    },

                    /* @ngInject */
                    /*
                    funcionOrganizacion: function (FuncionOrganizacion) {
                        return FuncionOrganizacion.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
                            return data.content;
                        });
                    },
                    */
                    /* @ngInject */
                    /*
                    organizacion: function (Organizacion) {
                        return Organizacion.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
                            return data.content;
                        });
                    },
                    */

                    /* @ngInject */
                    /*
                    tipologia: function (Tipologia) {
                        return Tipologia.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
                            return data.content;
                        });
                    },
                    */


                    /* @ngInject */
                    pais: function (Pais) {
                        return Pais.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
                            return data.content;
                        });
                    }
                }
            }
        });
})();
