'use strict';

require("babel-polyfill");

const angular = require('angular');

require('angular-ui-router');
require('angular-aria');
require('angular-animate');
require('angular-material');
require('angular-material-icons');
require('satellizer');


require('./controllers');
require('./directives');
require('./services');
require('./templates');
require('./route');

const AngularPluginsDemo = angular.module('APD', [
  'APD.Controllers',
  'APD.Directives',
  'APD.Services', 
  'templates',
  'appRoute',
  'ngMaterial',
  'ngMdIcons',
  'satellizer'
]);

require('./config');

Window.APD = AngularPluginsDemo;
