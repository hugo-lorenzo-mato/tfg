(function () {
    'use strict';

    angular.module('app')
        .factory('SubidaPeriodico', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        return ResourceHelper.getResource("subidaPeriodico");
    }

})();
