(function () {
    'use strict';

    angular.module('app')
        .config(stateConfig);

    /* @ngInject */
    function stateConfig($stateProvider) {
        $stateProvider.state('public', {
            abstract: true,
            parent: 'site',
            views: {
                'header@': {
                    templateUrl: 'app/layouts/header/header.html',
                    controller: "HeaderController",
                    controllerAs: 'ctrl'
                },
                'navbar@': {
                    template: '<mi-menu menu="$ctrl.menu" nav-collapsed="$ctrl.navCollapsed" modal="$ctrl.modal(id)"></mi-menu>',
                    controller: ['menuData', '$window', 'StaticModal', function (menuData, $window) {
                        this.menu = menuData;
                        this.navCollapsed = $window.innerWidth < 992;
                    }],
                    controllerAs: '$ctrl',
                    resolve: {
                        menuData: ['Menu',
                            function (Menu) {
                                return Menu.getMenu('menu-main');
                            }
                        ]
                    }
                },
                'footer@': {
                    templateUrl: 'app/layouts/footer/footer.html'
                }
            },
            resolve: {
                /* @ngInject */
                isAdmin: function ($rootScope) {
                    $rootScope.isAdmin = false;
                }
            }
        });
    }
})();
