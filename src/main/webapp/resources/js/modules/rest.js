angular.module('restApp', ['ngResource'])
    .factory('kitRest', function ($resource) {
        return $resource('/relations-with-gradle/kits');
    })
    .factory('locusRest', function ($resource) {
        return $resource('/relations-with-gradle/locus?kit=:kit');
    })
    .factory('piRest', function ($resource) {
        return $resource('/relations-with-gradle/pi/:action', {}, {
            calculateParentsPi: {method: 'POST', params: { action: "parents" }, isArray: false },
            calculateOneParentPi: {method: 'POST', params: { action: "oneparent" }, isArray: false }
        });
    });