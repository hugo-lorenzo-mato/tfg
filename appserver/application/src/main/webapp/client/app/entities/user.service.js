(function () {
    'use strict';

    angular.module('app')
        .factory('User', User);

    /* @ngInject */
    function User($resource, ParseLinks) {
        var urlAdmin = 'api/admin/user';

        return $resource(urlAdmin, {email: '@account.email'}, {
            'query': {
                method: 'GET',
                isArray: false,
                transformResponse: function (data, headers) {
                    return {
                        links: ParseLinks.parse(headers('link')),
                        list: angular.fromJson(data)
                    };
                }
            },
            'get': {
                method: 'GET',
                params: {
                    email: '@email'
                }
            },
            'getUser': {
                method: 'GET',
                url: urlAdmin + '/:login'
            },
            'findAll': {
                method: 'GET',
                url: urlAdmin + '/findAll'
            },
            'changePassword': {
                method: 'POST',
                url: urlAdmin + '/change_password'
            }
        });
    }
})();
