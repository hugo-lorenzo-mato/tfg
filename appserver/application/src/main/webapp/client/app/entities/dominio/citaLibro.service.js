(function () {
    'use strict';

    angular.module('app')
        .factory('CitaLibro', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        return ResourceHelper.getResource("citaLibro");
    }

})();
