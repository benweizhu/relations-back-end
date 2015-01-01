angular.module('restApp', ['ngResource'])
    .factory('kitRest', function ($resource) {
        return $resource('/relations-with-gradle/kits');
    })
    .factory('locusRest', function ($resource) {
        return $resource("/relations-with-gradle/locus?kit=:kit");
    });