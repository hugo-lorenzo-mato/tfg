(function () {
    'use strict';

    angular.module('app')
    // Indicamos el estado, no la url, en este caso admin/prototexto
        .constant('ADMIN_PRINCIPAL', "admin/prototexto")
        // Nº de elementos por página en las tablas
        .constant('NG_TABLE_DEFAULT_PARAMS', {size: 20});
})();
