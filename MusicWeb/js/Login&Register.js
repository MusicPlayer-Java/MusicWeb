$(".hover").eq(0).css({
    "width":$(window).width()+"px",
    "height":$(window).height()+"px"
})

$("#register").click(function () {
    visible(0);
})

$(".hover").eq(0).click(function () {
    $(".hover").eq(0).css("display","none");
    $(".login").eq(0).css("display","none");
    $(".login").eq(1).css("display","none");
    $(".login").eq(2).css("display","none");
});

$("#login-person").click(function () {
    visible(1);
});

$("#addSheet").eq(0).click(function () {
    visible(2);
})

function visible(i) {
    var login =$(".login").eq(i);
    var h = $(window).height();
    var w = $(window).width();
    //$("body").appendChild()
    login.css({
        "display":"block",
        "margin-top":(h-560)/2+"px",
        "margin-left":(w-450)/2+"px"
    })
    $(".hover").eq(0).css("display","block");
}
// $(".box-register").eq(2).mouseleave(function () {
//     if ($(".box-register").eq(2).val()!=(".box-register").eq(1).val())
//         alert("前后两次密码不一样！");
// });
