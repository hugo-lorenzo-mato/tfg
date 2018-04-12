(function () {
    'use strict';

    angular
        .module('app')

        /* @ngInject */
        .constant('CITA_FORM_ADMIN', function (modal) {
            var resolve = "$resolve.";
            if (modal) {
                resolve = "ctrl.resolve."
            }
            return {
                component: '<citaPrototexto-form-admin ' +
                'item="' + resolve + 'item" ' +
                'previous-params="' + resolve + 'previousParams"></citaPrototexto-form-admin>',
                resolve: {
                    /* @ngInject */
                    item: function (Cita) {
                        return new Cita();
                    }
                }
            }
        });
})();
