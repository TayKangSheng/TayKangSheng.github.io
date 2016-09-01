window.onload = function() {
	// console.log("onload");
	var container = document.getElementById("container");
	// console.log(container);
	var hash = window.location.hash.substring(1);
	if (!hash){
		document.getElementById("container").getElementsByTagName("div")[0].style.display = "inline-block";
	} else{
		document.getElementById(hash).style.display = "inline-block";
	}
}

function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}

window.onhashchange = hashchange;
function hashchange(){
	var divs = document.getElementById("container").getElementsByTagName("div");
	for (var i=0 ; i<divs.length ; i++){ divs[i].style.display="none"; }
	var hash = window.location.hash.substring(1);
	document.getElementById(hash).style.display = "inline-block";
}