(function () {
    'use strict';

    angular.module('app')
        .factory('Agente', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        return ResourceHelper.getResource("agente");
    }

})();
