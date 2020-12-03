$(function(){
    $.ajax({
        url:"/trading/torBar.do",
        type:"post",
        success:function(msg){
            if(msg=="noBody"){
               var s="<a href=/trading/login.html>登录</a>"+
               "<span>|</span>"+
               "<a href=/trading/register.html>注册</a>";
                $(".operation").html(s);
            }else{
                var s="<a href=/trading/user/person.html>"+msg+"</a>";
                $(".operation").html(s);
                $(".cartHref").attr("href","/trading/user/shopCar.html");
                $.ajax({
                    url:"/trading/user/getCartNum.do",
                    type:"post",
                    success:function(num){
                        $(".NumOfCar").text(num);
                    }
                });
            }
        }
    });
});