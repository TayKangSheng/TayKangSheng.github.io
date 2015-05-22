
var dashBoard = {
  hof: document.getElementById("messages"),
  value : document.getElementById("inputField"),
  fragment: document.createDocumentFragment(),
  element: undefined,

  welcomeMessage: function(){
    this.element = document.createElement('div');
    this.element.appendChild(document.createTextNode("------------------------------------------------------------------------------------------------"));
    this.fragment.appendChild(this.element);
    this.hof.insertBefore(this.fragment,this.hof.childNodes[0]);
    this.value.value="";
  },

  addSavedMessageToDashBoard: function (savedmessage){
      this.element = document.createElement('div');
      this.element.appendChild(document.createTextNode("$ "+savedmessage));
      this.fragment.appendChild(this.element);
      this.hof.insertBefore(this.fragment,this.hof.childNodes[0]);
      this.value.value="";
  },

  addMessage: function (){
    if (this.value.value.length===0){

    } else{
      this.element = document.createElement('div');
      this.element.appendChild(document.createTextNode("$ "+this.value.value));
      this.fragment.appendChild(this.element);
      this.hof.insertBefore(this.fragment,this.hof.childNodes[0]);
      if (this.value.value == "down"){
        GAME.down();
      } else if (this.value.value == "up"){
        GAME.up();
      } else if (this.value.value == "right"){
        GAME.toRight();
      } else if (this.value.value == "left"){
        GAME.toLeft();
      }
      GAME.setVariables();
      this.value.value="";
      this.welcomeMessage();
    }
  }
}
var y;
y = dashBoard.addSavedMessageToDashBoard("Input 'up', 'down', 'left', 'right' or press arrow keys to get boxes 1 to 9 in order!");
y = dashBoard.addSavedMessageToDashBoard("INSTRUCTIONS: ");
y = dashBoard.addSavedMessageToDashBoard("May the force be with you.");
y = dashBoard.welcomeMessage();

// Game variables and function here

var GAME = {
  gameCells : [-1,-1,-1,-1,-1,-1,-1,-1,-1], 
  gameValues : [1,2,3,4,5,6,7,8],

  randomGenerator : function () {
    return parseInt(Math.floor((Math.random() * 10)),10);
  },

  setVariables : function(){
    if (this.gameCells[0]!== "x") document.getElementById("1").innerHTML = this.gameCells[0]; else document.getElementById("1").innerHTML = " ";
    if (this.gameCells[1]!== "x") document.getElementById("2").innerHTML = this.gameCells[1]; else document.getElementById("2").innerHTML = " ";
    if (this.gameCells[2]!== "x") document.getElementById("3").innerHTML = this.gameCells[2]; else document.getElementById("3").innerHTML = " ";
    if (this.gameCells[3]!== "x") document.getElementById("4").innerHTML = this.gameCells[3]; else document.getElementById("4").innerHTML = " ";
    if (this.gameCells[4]!== "x") document.getElementById("5").innerHTML = this.gameCells[4]; else document.getElementById("5").innerHTML = " ";
    if (this.gameCells[5]!== "x") document.getElementById("6").innerHTML = this.gameCells[5]; else document.getElementById("6").innerHTML = " ";
    if (this.gameCells[6]!== "x") document.getElementById("7").innerHTML = this.gameCells[6]; else document.getElementById("7").innerHTML = " ";
    if (this.gameCells[7]!== "x") document.getElementById("8").innerHTML = this.gameCells[7]; else document.getElementById("8").innerHTML = " ";
    if (this.gameCells[8]!== "x") document.getElementById("9").innerHTML = this.gameCells[8]; else document.getElementById("9").innerHTML = " ";
  },

  toLeft : function(){
    console.log("left");
    for (var i=0 ; i<1 ; i++){
      for (var k=2 ; k<9 ;k+=3){
        if (this.gameCells[k-1]==="x"){
          console.log("found!");
          this.gameCells[k-1]=this.gameCells[k];
          this.gameCells[k] = "x";
        }
      }
      for (var j=1 ; j<9 ;j+=3){
        if (this.gameCells[j-1]==="x"){
          console.log("found!");
          this.gameCells[j-1]=this.gameCells[j];
          this.gameCells[j] = "x";
        }
      }

    }
  },

  toRight : function(){
    console.log("right");
    for (var i=0 ; i<1 ; i++){
      for (var j=0 ; j<9 ;j=(j+3)){
        if (this.gameCells[j+1]=="x"){
          console.log("found!");
          this.gameCells[j+1]=this.gameCells[j];
          this.gameCells[j] = "x";
        }
      }
      for (var k=1 ; k<9 ;k+=3){
        if (this.gameCells[k+1]=="x"){
          console.log("found!");
          this.gameCells[k+1]=this.gameCells[k];
          this.gameCells[k] = "x";
        }
      }
    }
  },

  up : function(){
    console.log("up");
    for (var i=0 ; i<1 ; i++){
      for (var j=8 ; j>2 ; j--){
        if (this.gameCells[j-3]=="x"){
          this.gameCells[j-3]=this.gameCells[j];
          this.gameCells[j] = "x";
        }
      }
    }
  },

  down : function(){
    console.log("down");
    for (var i=0 ; i<1 ; i++){
      for (var j=0 ; j<6 ; j++){
        if (this.gameCells[j+3]=="x"){
          this.gameCells[j+3]=this.gameCells[j];
          this.gameCells[j] = "x";
        }
      }
    }
  },

  init : function(){
    var temp, match;
    for (var i=0 ; i<8 ; i++){
      while(this.gameCells[i]===-1){
        temp = this.randomGenerator();
        match = false;
        for (var j=0 ; j<8 ; j++){
          if (this.gameCells[j] === temp){
            match = true;
          }
        }
        if (temp === 10 || temp === 0 || temp === 9){
          match = true;
        }
        if (!match){
          this.gameCells[i] = temp;
        }
      }
    }
    this.gameCells[8] = "x";
  }
}

GAME.init();
GAME.setVariables();
console.log(GAME.gameCells.toString());

document.onkeydown = checkKey;

function checkKey(e) {

    e = e || window.event;

    if (e.keyCode == '38') {
        GAME.up();
    }
    else if (e.keyCode == '40') {
        GAME.down();
    }
    else if (e.keyCode == '37') {
       GAME.toLeft();
    }
    else if (e.keyCode == '39') {
       GAME.toRight();
    }
    GAME.setVariables();

}