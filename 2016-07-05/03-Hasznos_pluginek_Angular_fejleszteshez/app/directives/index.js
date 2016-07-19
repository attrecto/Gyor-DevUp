'use strict';

const angular = require('angular');

angular.module('APD.Directives', [])
   .directive('sidebarMenu', require('./sidebarMenu'))
   .directive('sidebarMenuItem', require('./sidebarMenuItem'))
   .directive('getWords', require('./getWords'));
 
