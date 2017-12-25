$(".otherlist-item").each(function () {
    $(this).hover(function () {
        $(this).children().eq(0).css("display","block");
        },function () {
        $(this).children().eq(0).css("display","none");
    })
});
$(".mylist-other").each(function () {
    $(this).hover(function () {
        $(this).children().eq(0).css("display","block");
    },function () {
        $(this).children().eq(0).css("display","none");
    })
})

