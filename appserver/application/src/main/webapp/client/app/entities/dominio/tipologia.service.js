(function () {
    'use strict';

    angular.module('app')
        .factory('Tipologia', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        return ResourceHelper.getResource("tipologia");
    }

})();
