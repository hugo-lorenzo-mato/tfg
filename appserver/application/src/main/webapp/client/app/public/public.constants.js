(function () {
    'use strict';
    angular
        .module('app')
        // Nº de elementos por página en las tablas
        .constant('NG_TABLE_DEFAULT_PARAMS_PUBLIC', {size: 20})
        .constant('NG_LIST_DEFAULT_PARAMS_PUBLIC', {size: 20})

    ;
})();
