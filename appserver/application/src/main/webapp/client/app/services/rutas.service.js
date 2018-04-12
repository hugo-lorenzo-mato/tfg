(function () {
    'use strict';

    angular.module('app')
        .factory('RutasService', RutasService);

    /* @ngInject */
    function RutasService($location, DEBUG_INFO_ENABLED) {
        var cacheUrl = "";

        return {
            getBaseUrl: getBaseUrl,
            // Prototexto
            getPrototextoCompletoUrl: getPrototextoCompletoUrl,
            getPrototextoTranscripcionUrl: getPrototextoTranscripcionUrl,
            getPrototextoGeneticaUrl: getPrototextoGeneticaUrl,
            getPrototextoImagenUrl: getPrototextoImagenUrl,
            getPrototextoOtrosUrl: getPrototextoOtrosUrl,
            //Libro
            getLibroCompletoUrl: getLibroCompletoUrl,
            getLibroTranscripcionUrl: getLibroTranscripcionUrl,
            getLibroGeneticaUrl: getLibroGeneticaUrl,
            getLibroImagenUrl: getLibroImagenUrl,
            getLibroOtrosUrl: getLibroOtrosUrl,
            // Periodico
            getPeriodicoCompletoUrl: getPeriodicoCompletoUrl,
            getPeriodicoTranscripcionUrl: getPeriodicoTranscripcionUrl,
            getPeriodicoGeneticaUrl: getPeriodicoGeneticaUrl,
            getPeriodicoImagenUrl: getPeriodicoImagenUrl,
            getPeriodicoOtrosUrl: getPeriodicoOtrosUrl,
        };

        function getBaseUrl() {
            // En desarrollo utilizamos la de pre porque si no hay problemas al compartir con Facebook
            if (!cacheUrl) {
                if (DEBUG_INFO_ENABLED) {
                    cacheUrl = "http://localhost:8080";
                } else {
                    cacheUrl = $location.protocol() + "://" + $location.host();
                    if ($location.port() !== 80) {
                        cacheUrl = cacheUrl + ":" + $location.port();
                    }
                }
            }
            return cacheUrl;
        }

        //Diferencio por tipo de producción pasiva para almacenar en diferentes carpetas

        // Prototextos
        function getPrototextoCompletoUrl(id) {
            return '/upload/prototextos/' + id + '/digital-completo/';
        }

        function getPrototextoTranscripcionUrl(id) {
            return '/upload/prototextos/' + id + '/transcripcion/';
        }

        function getPrototextoGeneticaUrl(id) {
            return '/upload/prototextos/' + id + '/critica-genetica/';
        }

        function getPrototextoImagenUrl(id) {
            return '/upload/prototextos/' + id + '/imagen/';
        }

        function getPrototextoOtrosUrl(id) {
            return '/upload/prototextos/' + id + '/otros/';
        }

        // Libros
        function getLibroCompletoUrl(id) {
            return '/upload/libros/' + id + '/digital-completo/';
        }

        function getLibroTranscripcionUrl(id) {
            return '/upload/libros/' + id + '/transcripcion/';
        }

        function getLibroGeneticaUrl(id) {
            return '/upload/libros/' + id + '/critica-genetica/';
        }

        function getLibroImagenUrl(id) {
            return '/upload/libros/' + id + '/imagen/';
        }

        function getLibroOtrosUrl(id) {
            return '/upload/libros/' + id + '/otros/';
        }

        // Periodicos
        function getPeriodicoCompletoUrl(id) {
            return '/upload/periodicos/' + id + '/digital-completo/';
        }

        function getPeriodicoTranscripcionUrl(id) {
            return '/upload/periodicos/' + id + '/transcripcion/';
        }

        function getPeriodicoGeneticaUrl(id) {
            return '/upload/periodicos/' + id + '/critica-genetica/';
        }

        function getPeriodicoImagenUrl(id) {
            return '/upload/periodicos/' + id + '/imagen/';
        }

        function getPeriodicoOtrosUrl(id) {
            return '/upload/periodicos/' + id + '/otros/';
        }
    }

})();
