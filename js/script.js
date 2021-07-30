window.onload = function(){
    var start_time = Date.now();
    var seconds_elapsed = 0;

    var dots = [
        document.getElementById("dot-one"),
        document.getElementById("dot-two"),
        document.getElementById("dot-three"),
        document.getElementById("dot-four"),
    ];

    var update_loader_loop = setInterval(function(){
        update_loader();
    }, 500);

    function update_loader(){
        var millis = Date.now() - start_time;

        seconds_elapsed = Math.floor(millis/1000);

        switch(seconds_elapsed % 5) {
            case 0:
                dots[0].className = "hidden";
                dots[1].className = "hidden";
                dots[2].className = "hidden";
                dots[3].className = "hidden";
                break;
            case 1:
                dots[0].className = "visible";
                dots[1].className = "hidden";
                dots[2].className = "hidden";
                dots[3].className = "hidden";
                break;
            case 2:
                dots[0].className = "visible";
                dots[1].className = "visible";
                dots[2].className = "hidden";
                dots[3].className = "hidden";
                break;
            case 3:
                dots[0].className = "visible";
                dots[1].className = "visible";
                dots[2].className = "visible";
                dots[3].className = "hidden";
                break;
            case 4:
                dots[0].className = "visible";
                dots[1].className = "visible";
                dots[2].className = "visible";
                dots[3].className = "visible";
                break;
        }
    }
};
