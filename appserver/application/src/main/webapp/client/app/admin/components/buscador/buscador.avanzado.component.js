(function () {
    'use strict';

    angular
        .module('app')
        .component('buscadorAvanzado', {
            templateUrl: 'app/admin/components/buscador/buscador.avanzado.html',
            controller: Controller,
            controllerAs: 'ctrl',
            bindings: {
            }
        });

    /* @ngInject */
    function Controller($window, $stateParams, ProduccionActiva, EnumService) {
        var vm = this;
        vm.filtro = $stateParams.filtro;

        vm.filter = {};

        vm.elementos = {};

        vm.search = function () {
            // vm.loading = true;
            // ProduccionActiva.findAll(vm.filtro).$promise.then(function (data) {
            //     vm.loading = false;
            //     goToResultados();
            //     angular.extend(vm.elementos, data);
            //
            // });
            angular.extend(vm.filter, vm.filtro);
        };

        function goToResultados() {
            if (!$("#resultados").size()) {
                $window.requestAnimationFrame(goToResultados);
            } else {
                $("body").animate({scrollTop: (angular.element('#resultados').offset().top - 60) + "px"}, 1000);
            }
        };

        vm.search();

        EnumService.get("genero").then(function (data) {
            vm.genero = data;
        });

        EnumService.get("assunto").then(function (data) {
            vm.assunto = data;
        });
    }

})();
