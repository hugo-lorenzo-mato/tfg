(function () {
    'use strict';

    angular
        .module('app')

        /* @ngInject */
        .constant('PAIS_FORM_ADMIN', function (modal) {
            var resolve = "$resolve.";
            if (modal) {
                resolve = "ctrl.resolve."
            }
            return {
                component: '<pais-form-admin' +
                ' item="' + resolve + 'item"' +
                ' previous-params="' + resolve + 'previousParams"></pais-form-admin>',
                resolve: {
                    /* @ngInject */
                    item: function (Pais) {
                        return new Pais();
                    }
                }
            }
        });
})();
