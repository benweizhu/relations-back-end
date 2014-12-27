angular.module("parentsApp", [])
    .controller("parentsController", function ($scope) {
        $scope.locuses = [];
        $scope.locusCodes = [];
        $scope.add = function () {
            $scope.locuses.push({code: $scope.code, af1: $scope.af1, af2: $scope.af2,
                m1: $scope.m1, m2: $scope.m2, c1: $scope.c1, c2: $scope.c2, pi: ''});
        }
    });