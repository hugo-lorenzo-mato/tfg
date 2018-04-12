(function () {
    'use strict';

    angular
        .module('app')

        /* @ngInject */
        .constant('CIUDAD_FORM_ADMIN', function (modal) {
            var resolve = "$resolve.";
            if (modal) {
                resolve = "ctrl.resolve."
            }
            return {
                component: '<ciudad-form-admin ' +
                'item="' + resolve + 'item" ' +
                'previous-params="' + resolve + 'previousParams"' +
                ' paises="' + resolve + 'paises"></ciudad-form-admin>',
                resolve: {
                    /* @ngInject */
                    item: function (Ciudad) {
                        return new Ciudad();
                    },
                    /* @ngInject */
                    paises: function (Pais) {
                        return Pais.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
                            return data.content;
                        });
                    }
                }
            }
        });
})();
