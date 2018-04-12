(function () {
    'use strict';

    angular.module('app')
        .factory('Ciudad', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        return ResourceHelper.getResource("ciudad");
    }

})();
