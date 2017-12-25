var mp3;
var isPlay;
var tSet;

$(document).ready(function () {
    mp3 =  $(".audio-play")[0];
    isPlay = false;
})

$("#stop-begin-button").click(function () {
    if(isPlay){
        isPlay = false;
        $("#stop-begin-button").css("background","url('images/begin.png')");
        mp3.pause();
        clearInterval(tSet);
    }
    else {
        isPlay = true;
        $("#stop-begin-button").css("background","url('images/turn-stop.png')");
        mp3.play();
        tSet = setInterval(function () {
            var width =  mp3.currentTime / mp3.duration * 1473;
            $(".progress-blue").css({"width":width+"px"});
            $(".progress-arc").css("left",(width - 10) + "px");
            console.log(width);
        },1000);
    }
});



