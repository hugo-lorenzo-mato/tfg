(function () {
    'use strict';

    angular
        .module('app')

        /* @ngInject */
        .constant('TIPOLOGIA_FORM_ADMIN', function (modal) {
            var resolve = "$resolve.";
            if (modal) {
                resolve = "ctrl.resolve."
            }
            return {
                component: '<tipologia-form-admin' +
                ' item="' + resolve + 'item"' +
                ' previous-params="' + resolve + 'previousParams"></tipologia-form-admin>',
                resolve: {
                    /* @ngInject */
                    item: function (Tipologia) {
                        return new Tipologia();
                    }
                }
            }
        });
})();
