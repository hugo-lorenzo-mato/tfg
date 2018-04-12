(function () {
    'use strict';

    angular.module('app')
        .factory('ProduccionActiva', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        var entidad = "produccionActiva";
        return ResourceHelper.getResource(entidad, {}, {
            'findAll': {
                method: 'POST',
                url: 'api/public/' + entidad + '/findAll',
                isArray: false
            }
        });
    }

})();
