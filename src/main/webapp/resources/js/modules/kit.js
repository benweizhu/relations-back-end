angular.module('kitApp', [])
    .factory("kitService", function () {
        return {
            getKits: function () {
                return ['there', 'is', 'something', 'from', 'sub', 'module'];
            }
        };
    });