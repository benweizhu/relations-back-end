angular.module('oneParentApp', ['restApp'])
    .controller('oneParentController', ['$scope', 'locusRest', 'kitRest', 'piRest',
        function ($scope, locusRest, kitRest, piRest) {

            $scope.locuses = [];

            kitRest.get({}, function (data) {
                $scope.kits = data.kits;
            });

            $scope.selectKit = function () {
                locusRest.get({kit: $scope.kit}, function (data) {
                    $scope.locusCodes = data.codes;
                });
            };

            $scope.remove = function(index){
                $scope.locuses.splice(index, 1);
            }

            $scope.add = function () {

                var req = {kit: $scope.kit, locus: $scope.code, af1: $scope.af1, af2: $scope.af2,
                    c1: $scope.c1, c2: $scope.c2};

                piRest.calculateOneParentPi(req, function (resp) {
                    console.log(resp.value);
                    $scope.locuses.push({code: $scope.code, af1: $scope.af1, af2: $scope.af2,
                        c1: $scope.c1, c2: $scope.c2, pi: resp.value});
                });

            };

        }]);