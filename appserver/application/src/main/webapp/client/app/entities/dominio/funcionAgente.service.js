(function () {
    'use strict';

    angular.module('app')
        .factory('FuncionAgente', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        return ResourceHelper.getResource("funcionAgente");
    }

})();
