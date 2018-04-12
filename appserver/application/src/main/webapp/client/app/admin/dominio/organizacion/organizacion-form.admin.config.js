(function () {
    'use strict';

    angular
        .module('app')

        /* @ngInject */
        .constant('ORGANIZACION_FORM_ADMIN', function (modal) {
            var resolve = "$resolve.";
            if (modal) {
                resolve = "ctrl.resolve."
            }
            return {
                component: '<organizacion-form-admin' +
                ' item="' + resolve + 'item"' +
                ' tipologias="' + resolve + 'tipologias"' +
                ' previous-params="' + resolve + 'previousParams"></organizacion-form-admin>',
                resolve:{

                    /* @ngInject */
                    item: function (Organizacion) {
                        return new Organizacion();
                    },
                    /* @ngInject */
                    tipologias: function (Tipologia) {
                        return Tipologia.findAll({sortProperty: 'nombre'}).$promise.then(function (data) {
                            return data.content;
                        });
                    }
                }
            }
        });

})();
