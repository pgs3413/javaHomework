$(function(){
    $(".join").click(function(){
        $.ajax({
            url:"/trading/joinToCartCheck.do",
            type:"post",
            success:function(msg){
                if(msg=="false"){
                    alert("请先登录！");
                }else{
                    var str=window.location.search;
                    var num=str.indexOf("=");
                    var id=str.substr(num+1);
                    $.ajax({
                        url:"/trading/user/joinToCart.do",
                        type:"post",
                        data:{
                            goodsId:id
                        },
                        success:function(num){
                            $(".NumOfCar").text(num);
                            $(".join").text("已加入购物车");
                            $(".join").unbind();
                            $(".join").addClass("focus").css("pointer-events","none");
                        }
                    });
                }
            }
        });
    });
})