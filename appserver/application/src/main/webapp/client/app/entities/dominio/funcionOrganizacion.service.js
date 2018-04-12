(function () {
    'use strict';

    angular.module('app')
        .factory('FuncionOrganizacion', Service);

    /* @ngInject */
    function Service(ResourceHelper) {
        return ResourceHelper.getResource("funcionOrganizacion");
    }

})();
