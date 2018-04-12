(function () {
    'use strict';

    angular.module('app')
        .factory('Periodico', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        return ResourceHelper.getResource("periodico");
    }

})();
