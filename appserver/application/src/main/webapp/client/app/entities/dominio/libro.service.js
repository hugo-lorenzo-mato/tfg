(function () {
    'use strict';

    angular.module('app')
        .factory('Libro', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        return ResourceHelper.getResource("libro");
    }

})();
