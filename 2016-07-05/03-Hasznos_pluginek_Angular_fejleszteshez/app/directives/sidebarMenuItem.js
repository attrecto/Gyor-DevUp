'use strict';
  
module.exports = [function(){
   return {
      restrict: 'E',
      require: '^^sidebarMenu',
      scope: {
        'click':'&onClick',
        'label':'@label'
      },
     template:`
       <md-list-item ng-click="click()">
         {{label}}
       </md-list-item>
       <md-divider></md-divider>
     `
   }
 }];
