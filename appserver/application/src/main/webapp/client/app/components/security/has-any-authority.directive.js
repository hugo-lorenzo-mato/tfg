(function () {
    'use strict';

    angular
        .module('app')
        .directive('hasAnyAuthority', hasAnyAuthority);

    function hasAnyAuthority(Principal) {
        var directive = {
            restrict: 'A',
            link: linkFunc
        };

        return directive;

        function linkFunc(scope, element, attrs) {
            if (attrs.hasAnyAuthority === "") {
                return;
            }

            var authorities = attrs.hasAnyAuthority.replace(/\s+/g, '').split(',');

            var setVisible = function () {
                    element.removeClass('hidden');
                },
                setHidden = function () {
                    element.addClass('hidden');
                },
                defineVisibility = function (reset) {
                    var result;
                    if (reset) {
                        setVisible();
                    }

                    result = Principal.hasAnyAuthority(authorities);
                    if (result) {
                        setVisible();
                    } else {
                        setHidden();
                    }
                };

            if (authorities.length > 0) {
                defineVisibility(true);

                scope.$watch(function () {
                    return Principal.isAuthenticated();
                }, function () {
                    defineVisibility(true);
                });
            }
        }
    }
})();
