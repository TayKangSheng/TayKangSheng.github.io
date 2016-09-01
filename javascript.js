function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}

function topnav(nav){
	// console.log("topnav: "+nav);
	var divs = document.getElementsByTagName('div');
	for (var i=0 ; i<divs.length; i++){
		console.log(divs[i].id)
		if (divs[i].id === nav){
			divs[i].style.display = "block";
		} else {
			divs[i].style.display = "none";
		}
	}	
}

function onload(){
	var hash = window.location.hash.substring(1);
	// console.log(hash);
	if (!hash){
		topnav("home");
	} else {
		topnav(hash);
	}
}