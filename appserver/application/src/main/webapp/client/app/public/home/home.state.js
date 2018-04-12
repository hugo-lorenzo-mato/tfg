(function () {
    'use strict';

    angular
        .module('app')
        .config(stateConfig);

    /* @ngInject */
    function stateConfig($stateProvider) {
        $stateProvider.state('home', {
            redirectTo:'login',
            parent: 'admin',
            url: '/',
            data: {
                pageTitle: 'global.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/public/home/home.html',
                    controller: 'HomeController',
                    controllerAs: 'ctrl'
                }
            }
        });
    }
})();
