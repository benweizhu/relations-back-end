angular.module('oneParentApp', [])
    .factory('oneParentService', function () {
        return {
            getPi: function () {
                var pi = null;
                return pi;
            }
        }
    })
    .controller('oneParentController', function ($scope, oneParentService) {
        $scope.locuses = [];
        $scope.locusCodes = oneParentService.getLocusCodes();

        $scope.add = function () {
            $scope.locuses.push(
                {
                    code: $scope.code,
                    af1: $scope.af1,
                    af2: $scope.af2,
                    c1: $scope.c1,
                    c2: $scope.c2,
                    pi: oneParentService.getPi()
                }
            );
        }
    });