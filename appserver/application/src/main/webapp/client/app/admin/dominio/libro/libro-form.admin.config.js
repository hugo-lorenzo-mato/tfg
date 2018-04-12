(function () {
    'use strict';

    angular
        .module('app')

        /* @ngInject */
        .constant('LIBRO_FORM_ADMIN', function (modal) {
            var resolve = "$resolve.";
            if (modal) {
                resolve = "ctrl.resolve."
            }
            return {
                component: '<libro-form-admin' +
                ' item="' + resolve + 'item"' +
                ' previous-params="' + resolve + 'previousParams"' +
                ' genero="' + resolve + 'genero"'+
                ' assunto="' + resolve + 'assunto"'+
                ' ciudad="' + resolve + 'ciudad"' +
                ' pais="' + resolve + 'pais"'+
                ' tipos="' + resolve + 'tipos"'+
                ' funcionOrganizacion="' + resolve + 'funcionOrganizacion"'+
                ' funcionAgente="' + resolve + 'funcionAgente"'+
                ' organizacion="' + resolve + 'organizacion"'+
                ' tipologia="' + resolve + 'tipologia"'+
                ' agente="' + resolve + 'agente"></libro-form-admin>',
                resolve: {
                    /* @ngInject */
                    item: function (Libro) {
                        return new Libro();
                    },
                    /* @ngInject */
                    pais: function (Pais) {
                        return Pais.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
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
                    funcionOrganizacion: function (FuncionOrganizacion) {
                        return FuncionOrganizacion.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
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
                    organizacion: function (Organizacion) {
                        return Organizacion.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
                            return data.content;
                        });
                    },


                    /* @ngInject */
                    tipologia: function (Tipologia) {
                        return Tipologia.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
                            return data.content;
                        });
                    },

                    /* @ngInject */
                    ciudad: function (Ciudad) {
                        return Ciudad.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
                            return data.content;
                        });
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
                    tipos: function (EnumService) {
                        return EnumService.get("tipo-libro");
                    }
                }
            }
        });
})();
