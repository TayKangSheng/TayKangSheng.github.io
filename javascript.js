window.onload = function() {
	var container = document.getElementById("container");
	var hash = window.location.hash.substring(1);
	if (!hash){
		document.getElementById("container").getElementsByTagName("div")[0].style.display = "inline-block";
	} else{
		document.getElementById(hash).style.display = "inline-block";
	}
	setTimeout(showPage, 500);
}

function showPage(){
	document.getElementById("loader").style.display = "none";
  	document.getElementById("container").style.display = "block";
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
	if (!hash){
		document.getElementById("container").getElementsByTagName("div")[0].style.display = "inline-block";
	}else {
		document.getElementById(hash).style.display = "inline-block";
	}
}