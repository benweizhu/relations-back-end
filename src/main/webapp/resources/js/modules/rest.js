angular.module('restApp', ['ngResource'])
    .factory("kitRest", function ($resource) {
        return $resource("/relations-with-gradle/kits");
    });