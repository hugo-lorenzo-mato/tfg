(function (angular) {
    'use strict';

    angular
        .module('app')
        .controller('lugarBloqueController', lugarBloqueController);

    /* @ngInject */
    function lugarBloqueController($state, $timeout, Auth) {}

})(angular);
