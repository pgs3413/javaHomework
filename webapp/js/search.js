$(function(){
    var str=window.location.search;
    var num=str.indexOf("=");
    var s=str.substr(num+1);
    $.ajax({
        url:"goodsSearch.do",
        type:"post",
        data:{
            s:s
        },
        dataType:"json",
        success:function(list){

            $(".result").text(decodeURI(s));
            $.each(list,function(index,goods){
                var s="<li><div class=goods>"+
                                "<div class=goods-img>"+
                                    "<img src="+"/trading/images/"+goods.photo+" alt=\"商品图片\" width=260px height=220px>"+
                                "</div>"+
                            "<div class=goods-des>"+
                            "<p><a href="+"/trading/goods/details.do?id="+goods.id+">"+goods.title+"<br>"+goods.details+"</a></p>"+
                            "</div>"+
                            "<div class=goods-price>"+
                                "<p>"+goods.price+"元</p>"+
                            "</div>"+
                        "</div></li>";
                $(".searchList").append(s);
            })
        }
    });
})