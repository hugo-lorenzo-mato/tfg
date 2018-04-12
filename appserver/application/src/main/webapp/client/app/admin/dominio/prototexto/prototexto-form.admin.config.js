(function () {
    'use strict';

    angular
        .module('app')

        /* @ngInject */
        .constant('PROTOTEXTO_FORM_ADMIN', function (modal) {
            var resolve = "$resolve.";
            if (modal) {
                resolve = "ctrl.resolve."
            }
            return {
                component: '<prototexto-form-admin' +
                ' item="' + resolve + 'item"' +
                ' previous-params="' + resolve + 'previousParams"' +
                ' tipos="' + resolve + 'tipos"' +
                ' pais="' + resolve + 'pais"'+
                ' ciudad="' + resolve + 'ciudad"' +
                ' genero="' + resolve + 'genero"'+
                ' assunto="' + resolve + 'assunto"'+
                ' folha="' + resolve + 'folha"' +
                ' tipo-soporte="' + resolve + 'tipoSoporte"' +
                ' tipo-soporte-reducido="' + resolve + 'tipoSoporteReducido"' +
                ' usuario="' + resolve + 'usuario"></prototexto-form-admin>',
                resolve:{
                    /* @ngInject */
                    item: function (Prototexto) {
                        return new Prototexto();
                    },
                    /* @ngInject */
                    tipos: function (EnumService) {
                        return EnumService.get("tipo-prototexto");
                    },
                    /* @ngInject */
                    tipoSoporte: function (EnumService) {
                        return EnumService.get("tipo-sampliado");
                    },

                    /* @ngInject */
                    tipoSoporteReducido: function (EnumService) {
                        return EnumService.get("tipo-sreducido");
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
                    folha: function (EnumService) {
                        return EnumService.get("folha-prototexto");
                    },

                    /* @ngInject */
                    pais: function (Pais) {
                        return Pais.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
                            return data.content;
                        });
                    },

                    /* @ngInject */
                    ciudad: function (Ciudad) {
                        return Ciudad.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
                            return data.content;
                        });
                    },

                }
            }
        });

})();
