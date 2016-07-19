'use strict';
  
module.exports = [function() {
   return {
      restrict: 'E',
      transclude: true,
      templateUrl:'templates/getWords.html',
      scope: {
        inputText: "="
      },
      link: function($scope){     
        $scope.searchedText = ''; 
        $scope.getWords = () => {
          var words = $scope.inputText.split(' ');
         
          if($scope.searchedText.length < 1){
            return [];
          }

          var result = words.filter((item)=>{
            return item.indexOf($scope.searchedText)>-1
          });

          return result;
        };        
      }
    }
  }];
