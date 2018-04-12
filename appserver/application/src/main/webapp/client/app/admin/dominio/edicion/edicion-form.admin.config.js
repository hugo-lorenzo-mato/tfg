(function () {
    'use strict';

    angular
        .module('app')

        /* @ngInject */
        .constant('EDICION_FORM_ADMIN', function (modal) {
            var resolve = "$resolve.";
            if (modal) {
                resolve = "ctrl.resolve."
            }
            return {
                component: '<edicion-form-admin' +
                ' item="' + resolve + 'item"' +
                ' previous-params="' + resolve + 'previousParams"' +
                ' pais="' + resolve + 'pais"'+
                ' ciudad="' + resolve + 'ciudad"' +
                ' funcion="' + resolve + 'funcion"' +
                ' agente="' + resolve + 'agente"></edicion-form-admin>',
                resolve:{

                    /* @ngInject */
                    item: function (Edicion) {
                        return new Edicion();
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

                    /* @ngInject */
                    agente: function (Agente) {
                        return Agente.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
                            return data.content;
                        });
                    },

                    /* @ngInject */
                    funcion: function (Funcion) {
                        return Funcion.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
                            return data.content;
                        });
                    }
                }
            }
        });

})();
