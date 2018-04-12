(function () {
    'use strict';

    angular.module('app')
        .factory('Pais', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        return ResourceHelper.getResource("pais");
    }

})();
