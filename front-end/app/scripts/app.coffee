'use strict'

angular
.module('myAppApp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
  ])
.config ($routeProvider, $locationProvider) ->
  $routeProvider
  .when '/',
    templateUrl: 'views/main.html'
    #
    # controller: 'MainCtrl'
  .when '/about',
    templateUrl: 'views/main.html'
  .when '/contact',
    templateUrl: 'views/main.html'
  .otherwise
      redirectTo: '/'

  $locationProvider.html5Mode(true);

