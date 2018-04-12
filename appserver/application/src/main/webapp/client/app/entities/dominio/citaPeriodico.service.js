(function () {
    'use strict';

    angular.module('app')
        .factory('CitaPeriodico', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        return ResourceHelper.getResource("citaPeriodico");
    }

})();
