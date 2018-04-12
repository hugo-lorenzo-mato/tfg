(function () {
    'use strict';

    angular
        .module('app')

        /* @ngInject */
        .constant('FUNCIONORGANIZACION_FORM_ADMIN', function (modal) {
            var resolve = "$resolve.";
            if (modal) {
                resolve = "ctrl.resolve."
            }
            return {
                component: '<funcion-organizacion-form-admin' +
                ' item="' + resolve + 'item"' +
                ' previous-params="' + resolve + 'previousParams"></funcion-organizacion-form-admin>',
                resolve: {
                    /* @ngInject */
                    item: function (FuncionOrganizacion) {
                        return new FuncionOrganizacion();
                    }
                }
            }
        });
})();
