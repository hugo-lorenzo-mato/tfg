(function () {
    'use strict';

    angular
        .module('app')
        .config(appConfig);

    /* @ngInject */
    function appConfig($translateProvider, cfpLoadingBarProvider, $compileProvider, $breadcrumbProvider, $qProvider, $localStorageProvider) {
        $translateProvider.useSanitizeValueStrategy('escapeParameters');

        // Configuración del idioma
        $translateProvider.useStaticFilesLoader({
            files: [{
                prefix: 'i18n/',
                suffix: '/admin.json'
            }, {
                prefix: 'i18n/',
                suffix: '/error.json'
            }, {
                prefix: 'i18n/',
                suffix: '/public.json'
            }, {
                prefix: 'i18n/',
                suffix: '/componentes.json'
            }, {
                prefix: 'i18n/',
                suffix: '/enum.json'
            }]
        });

        // i18n
        var lang = $localStorageProvider.get('lang');
        $translateProvider.preferredLanguage('br');
        if (!lang || (lang !== 'es' && lang !== 'en' && lang !== 'br')) {
            $translateProvider.preferredLanguage('br');
            $localStorageProvider.set('lang', 'br');
        } else {
            $translateProvider.preferredLanguage(lang);
        }
        $translateProvider.preferredLanguage('br');
        // Configuración de la barra de carga
        cfpLoadingBarProvider.latencyThreshold = 150;
        cfpLoadingBarProvider.includeSpinner = false;

        // Configuración Breadcrumb
        $breadcrumbProvider.setOptions({
            templateUrl: 'app/components/breadcrumb/breadcrumb.html'
        });

        // TODO: disable debug data on prod profile to improve performance. Por ahora siempre en modo debug
        $compileProvider.debugInfoEnabled(true);

        $compileProvider.preAssignBindingsEnabled(true);
        $qProvider.errorOnUnhandledRejections(false);
    }
})();
