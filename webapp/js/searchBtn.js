$(function(){
    $(".searchSubmit").click(function(){
        var str=$(".searchBox").val();
        var add="/trading/goods/search.do?s="+str;
        $(location).attr('href', add);
    });
})