

var dashBoard = {
  hof: document.getElementById("messages"),
  value : document.getElementById("inputField"),
  fragment: document.createDocumentFragment(),
  element: undefined,

  welcomeMessage: function(){
    this.element = document.createElement('div');
    this.element.appendChild(document.createTextNode("---------------------------------------------------------------------------------------------------"));
    this.fragment.appendChild(this.element);
    this.hof.insertBefore(this.fragment,this.hof.childNodes[0]);
  },

  addMessage: function (){
    if (this.value.value.length===0){

    } else{
      this.element = document.createElement('div');
      this.element.appendChild(document.createTextNode("$ "+this.value.value));
      this.fragment.appendChild(this.element);
      this.hof.insertBefore(this.fragment,this.hof.childNodes[0]);
      this.value.value="";
      this.welcomeMessage();
    }
  }
}

var y = dashBoard.welcomeMessage();