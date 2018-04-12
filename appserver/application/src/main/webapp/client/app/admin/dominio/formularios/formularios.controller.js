(function (angular) {
    'use strict';

    angular
        .module('app')
        .controller('FormulariosController', FormulariosController);

    /* @ngInject */
    function FormulariosController($state, $timeout, Auth) {}

})(angular);
