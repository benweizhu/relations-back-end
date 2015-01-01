<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>parents</title>
    <script src="resources/js/angularjs/angular.min.js"></script>
    <script src="resources/js/angularjs/angular-resource.min.js"></script>
    <script src="resources/js/modules/rest.js"></script>
    <script src="resources/js/modules/one_parent.js"></script>
</head>
<body ng-app="oneParentApp">
<div ng-controller="oneParentController">
    试剂盒: <select ng-model="kit" ng-options="kit for kit in kits" ng-change="selectKit()"></select>
    <table>
        <thead>
        <th>基因座</th>
        <th>AF1</th>
        <th>AF2</th>
        <th>C1</th>
        <th>C2</th>
        <th>PI</th>
        <th>操作</th>
        </thead>
        <tbody>
        <tr ng-repeat="locus in locuses">
            <td>{{locus.code}}</td>
            <td>{{locus.af1}}</td>
            <td>{{locus.af2}}</td>
            <td>{{locus.c1}}</td>
            <td>{{locus.c2}}</td>
            <td>{{locus.pi}}</td>
            <td><input type="button" value="删除"></td>
        </tr>
        <tr>
            <td><select ng-model="code" ng-options="locusCode for locusCode in locusCodes"></select></td>
            <td><input type="text" ng-model="af1"></td>
            <td><input type="text" ng-model="af2"></td>
            <td><input type="text" ng-model="c1"></td>
            <td><input type="text" ng-model="c2"></td>
        </tr>
        </tbody>
    </table>
    <input type="button" value="添加" ng-click="add()">
</div>
</body>
</html>