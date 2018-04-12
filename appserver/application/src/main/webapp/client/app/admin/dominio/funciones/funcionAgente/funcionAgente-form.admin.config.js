(function () {
    'use strict';

    angular
        .module('app')

        /* @ngInject */
        .constant('FUNCIONAGENTE_FORM_ADMIN', function (modal) {
            var resolve = "$resolve.";
            if (modal) {
                resolve = "ctrl.resolve."
            }
            return {
                component: '<funcion-agente-form-admin' +
                ' item="' + resolve + 'item"' +
                ' previous-params="' + resolve + 'previousParams"></funcion-agente-form-admin>',
                resolve: {
                    /* @ngInject */
                    item: function (FuncionAgente) {
                        return new FuncionAgente();
                    }
                }
            }
        });
})();
