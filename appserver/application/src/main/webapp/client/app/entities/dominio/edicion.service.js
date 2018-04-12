(function () {
    'use strict';

    angular.module('app')
        .factory('Edicion', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        return ResourceHelper.getResource("edicion");
    }
})();
