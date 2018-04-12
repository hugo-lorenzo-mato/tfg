(function () {
    'use strict';

    angular
        .module('app')

        /* @ngInject */
        .constant('CITAPERIODICO_FORM_ADMIN', function (modal) {
            var resolve = "$resolve.";
            if (modal) {
                resolve = "ctrl.resolve."
            }
            return {
                component: '<cita-periodico-form-admin ' +
                'item="' + resolve + 'item" ' +
                'previous-params="' + resolve + 'previousParams"></cita-periodico-form-admin>',
                resolve: {
                    /* @ngInject */
                    item: function (CitaPeriodico) {
                        return new CitaPeriodico();
                    }
                }
            }
        });
})();
