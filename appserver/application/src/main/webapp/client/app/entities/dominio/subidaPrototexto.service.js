(function () {
    'use strict';

    angular.module('app')
        .factory('SubidaPrototexto', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        return ResourceHelper.getResource("subidaPrototexto");
    }

})();
