(function () {
    'use strict';

    angular.module('app')
        .factory('CitaPrototexto', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        return ResourceHelper.getResource("citaPrototexto");
    }

})();
