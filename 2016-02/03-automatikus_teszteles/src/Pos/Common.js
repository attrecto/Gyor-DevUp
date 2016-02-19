var Common = function (){
  this. getElementStateAsync = function (ele){
      return ele.getAttribute('class');
  };
};
module.exports = new Common ();
