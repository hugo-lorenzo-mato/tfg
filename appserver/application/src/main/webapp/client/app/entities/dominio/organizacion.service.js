(function () {
    'use strict';

    angular.module('app')
        .factory('Organizacion', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        return ResourceHelper.getResource("organizacion");
    }

})();
