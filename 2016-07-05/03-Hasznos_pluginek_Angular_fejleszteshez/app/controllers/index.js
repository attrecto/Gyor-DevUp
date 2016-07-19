'use strict';

const angular = require('angular');

angular.module('APD.Controllers', [])
  .controller('MainController', require('./MainController'))
  .controller('CalculatorController', require('./CalculatorController'))
  .controller('TextSearchController', require('./TextSearchController'));
