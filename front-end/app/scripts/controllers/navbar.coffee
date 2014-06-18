'use strict'

angular.module('myAppApp')
.controller 'NavbarCtrl', ($scope, $location) ->

  $scope.tab = 1

  $scope.setTab = (activeTab) ->
    $scope.tab = activeTab

  $scope.isActive = (route) ->
    return route is $location.path()

  $scope.getLocation = ->
    return $location.path()

