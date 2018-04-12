(function () {
    'use strict';

    angular.module('app')
        .factory('SubidaLibro', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        return ResourceHelper.getResource("subidaLibro");
    }

})();
