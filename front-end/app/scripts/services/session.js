'use strict';

angular.module('yeomanFullstackJadeApp')
  .factory('Session', function ($resource) {
    return $resource('/api/session/');
  });
