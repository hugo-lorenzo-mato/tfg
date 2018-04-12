(function () {
    'use strict';

    // id: Identificador del select.
    // label: etiqueta
    // model: Campo que se modificará.
    // disabled
    // required
    // options: Opciones del select.
    // base: Clave base para los enumerados.
    // display: Propiedad del objeto que se usará para internacionalizar el contenido.
    // empty: Clave de mensaje para mostrar en la opción vacía

    // Nota: Para utilizar como un campo booleano (Null/Sí/No), pasar como options: [true, false].

    angular
        .module('app')
        .component('enumModificadoSelect', {
            templateUrl: 'app/components/form/enumModificado/enumModificado-select.html',
            bindings: {
                id: '@',
                label: '@',
                model: '=',
                change: '=',
                display: '@',
                empty: '@',
                disabled: '=',
                required: '=',
                options: '=',
                base: '@'
            },
            controllerAs: 'ctrl',
            /* @ngInject */
            controller: function () {
                var vm = this;

                vm.onChange = function (item) {
                    // Si el valor es "", se pone a null
                    if (!item) {
                        vm.model = null;
                    }

                    if (vm.change) {
                        vm.change(item);
                    }
                }
            }
        });

})();
