(function () {
    'use strict';

    angular
        .module('app')

        /* @ngInject */
        .constant('AGENTE_FORM_ADMIN', function (modal) {
            var resolve = "$resolve.";
            if (modal) {
                resolve = "ctrl.resolve."
            }
            return {
                component: '<agente-form-admin' +
                ' item="' + resolve + 'item"' +
                ' previous-params="' + resolve + 'previousParams"></agente-form-admin>',
                resolve:{

                    /* @ngInject */
                    item: function (Agente) {
                        return new Agente();
                    },
                }
            }
        });

})();
