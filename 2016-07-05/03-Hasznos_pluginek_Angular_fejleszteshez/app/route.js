'use strict';

const angular  = require('angular');

angular.module('appRoute', ['ui.router'])
  .config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('main', {
        templateUrl: 'pages/main.html',
        title: 'Dashboard',
        controller: 'MainController',
        controllerAs: 'mainCtrl',
        login: true
      })
      .state('main.calculator', {
          url: '/calculator',
          templateUrl: 'pages/main.calculator.html',
          title: 'Calculator',
          subtitle: null,
          controller: 'CalculatorController',
          controllerAs: 'calculatorCtrl'
      })
      .state('main.textsearch', {
          url: '/textsearch',
          templateUrl: 'pages/main.search.html',
          title: 'Get Words',
          subtitle: null,
          controller: 'TextSearchController',
          controllerAs: 'textSearchCtrl'
      })
    $urlRouterProvider.otherwise('/textsearch');
  }])
  .run(['$rootScope', function ($rootScope) {
    $rootScope.$on('$stateChangeSuccess', () => {  
        return;      
    });
  }]);
