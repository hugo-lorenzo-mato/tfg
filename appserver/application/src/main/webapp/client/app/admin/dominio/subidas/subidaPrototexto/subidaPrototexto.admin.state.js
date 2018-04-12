
/*(function () {
    'use strict';

    angular
        .module('app')
        .config(stateConfig);

*/    /* @ngInject */
/*    function stateConfig($stateProvider, STATE_HELPER, SUBIDAPROTEXTO_FORM_ADMIN) {
        var config = SUBIDAPROTEXTO_FORM_ADMIN();
        var base = "admin/subidaPrototexto";
        var params = {
            base: base,
            baseUrl: "/" + base,
            translateBase: "admin.subidaPrototexto.",
            templateList: '<subida-prototexto-list-admin></subida-prototexto-list-admin>',
            templateForm: config.component
        };

        var state = STATE_HELPER(params);
        // Estado Padre
        angular.merge(state.parent, {
            data: {
                authorities: ['ROLE_ADMIN', 'ROLE_CATALOGADOR']
            }
        });
        // Listar
        angular.merge(state.list, {});
        // Crear
        angular.merge(state.create, {
            resolve: config.resolve
        });
        // Editar
        angular.merge(state.edit, {
            resolve: angular.merge(config.resolve, {
*/              /* @ngInject */
/*                item: function (SubidaPrototexto, $stateParams) {
                    return SubidaPrototexto.get({id: $stateParams.id}).$promise;
                }
            })
        });

        // Se definen los estados
        $stateProvider
            .state(params.base, state.parent)
            .state(state.list.name, state.list)
            .state(state.create.name, state.create)
            .state(state.edit.name, state.edit);
    }
})();
*/
