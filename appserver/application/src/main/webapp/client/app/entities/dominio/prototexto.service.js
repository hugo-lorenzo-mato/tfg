(function () {
    'use strict';

    angular.module('app')
        .factory('Prototexto', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        return ResourceHelper.getResource("prototexto");
    }

})();
