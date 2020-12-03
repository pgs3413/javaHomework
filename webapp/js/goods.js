$(function(){
    var str=window.location.search;
    var num=str.indexOf("=");
    var id=str.substr(num+1);
    $.ajax({
        url:"goodsContent.do",
        type:"post",
        data:{
            goodsId:id
        },
        dataType:"json",
        success:function(goodsAndBusiness){
            $(".bussName").text(goodsAndBusiness.business.businessName);
            $(".name").text(goodsAndBusiness.business.name);
            $(".stuNum").text(goodsAndBusiness.business.stuNum);
            $(".major").text(goodsAndBusiness.business.major);
            $(".className").text(goodsAndBusiness.business.className);
            $(".phone").text(goodsAndBusiness.business.phone);
            $(".email").text(goodsAndBusiness.business.email);
            $(".goodsTitle").text(goodsAndBusiness.goods.title);
            $(".price").text(goodsAndBusiness.goods.price);
            $(".goodsDetails").text(goodsAndBusiness.goods.details);
            $('#goodsImg').attr("src", "/trading/images/"+goodsAndBusiness.goods.photo);
        }
    });
    $.ajax({
        url:"/trading/joinToCartCheck.do",
        type:"post",
        success:function(msg){
            if(msg=="true"){
                $.ajax({
                    url:"/trading/user/btnCheck.do",
                    type:"post",
                    data:{
                        goodsId:id
                    },
                    success:function(str){
                        if(str=="true"){
                        $(".join").text("已加入购物车");
                        $(".join").unbind();
                        $(".join").addClass("focus").css("pointer-events","none");
                        }
                    }
                });
            }
        }

    });
})