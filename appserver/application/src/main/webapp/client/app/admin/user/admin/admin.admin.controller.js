(function (angular) {
    'use strict';

    angular
        .module('app')
        .controller('AdminAdminController', AdminAdminController);

    /* @ngInject */
    function AdminAdminController($timeout, $stateParams, NG_TABLE_DEFAULT_PARAMS, NgTableParams, NgTableHelper,
                                  ModalService, User) {
        var vm = this;

        $timeout(function () {
            angular.element('.form-group:eq(0)>input').focus();
        });

        vm.item = User;
        vm.pagina = $stateParams.page ? $stateParams.page : 1;
        vm.elementosPorPagina = NG_TABLE_DEFAULT_PARAMS.size;
        vm.filter = {};
        vm.tableParams = new NgTableParams({
            count: vm.elementosPorPagina,
            page: vm.pagina,
            filter: vm.filter,
            sorting: {login: 'asc'}
        }, NgTableHelper.settings(vm));

        // Eliminar
        vm.showRemoveConfirmation = function (user) {
            ModalService.open({
                templateUrl: 'app/components/form/delete/entity.delete.modal.html',
                controller: 'EntityDeleteModalController',
                controllerAs: 'ctrl',
                resolve: {
                    item: function () {
                        return User.getUser({login: user.login}).$promise;
                    },
                    params: function () {
                        return {
                            title: 'admin.list-users.delete.title',
                            body: 'admin.list-users.delete.confirm',
                            property: 'login'
                        };
                    }
                }
            }).result.then(function () {
                vm.tableParams.reload();
            });
        };

    }

})(angular);
