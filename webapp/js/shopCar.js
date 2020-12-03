$(function(){
    $.ajax({
        url:"shopCar.do",
        type:"post",
        dataType:"json",
        success:function(list){
        $.each(list,function(index,goods){
            var s="<tr>"+
            "<td>"+goods.title+"</td>"+
            "<td>"+goods.details+"</td>"+
            "<td>"+goods.type+"</td>"+
            "<td>"+goods.price+"</td>"+
            "<td><a href="+"/trading/user/cancelCart.do?id="+goods.id+">删除</a></td><td><input id="+goods.title+" value="+goods.price+" name="+goods.id+" class=cb onchange=cbChange() type=checkbox ></td>";
            s+="</tr>";
            $("#body").append(s);
        });
        }
    });
    $(".pay").click(function(){
        var total=$(".total").text();
        if(total==0) alert("请先选择商品");
        else{
            var flag=false;
            var count=0;
            var num=$("input[type='checkbox']:checked").length;
            $(".cb").each(function(){
                if($(this).prop("checked")==true){
                    var title=$(this).prop("id");
                    var id=$(this).prop("name");
                    $.ajax({
                        url:"/trading/user/goodsCheck.do",
                        type:"post",
                        data:{
                            goodsId:id
                        },
                        success:function(msg){
                            if(msg=="true"){
                                alert(title+"已下架,请从购物车中删除！");
                                flag=true;
                            }
                            count+=1;
                            if(count>=num){
                                if(flag==false){
                                    var address=prompt("请输入交易地点");
                                    if(address!=null){
                                        var pay=confirm("支付"+$(".total").text()+"元，点击确认支付");
                                        if(pay==true){
                                            var c=0;
                                            $(".cb").each(function(){
                                                var id=$(this).prop("name");
                                                var title=$(this).prop("id");
                                                if($(this).prop("checked")==true){
                                                    $.ajax({
                                                        url:"/trading/user/pay.do",
                                                        type:"post",
                                                        data:{
                                                            goodsId:id,
                                                            address:address
                                                        },
                                                        success:function(msg){
                                                            if(msg=="false") alert(title+"支付失败");
                                                            c++;
                                                            if(c>=num) alert("支付成功！");
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    }
                                    
                                    

                                }
                            }
                        }
                    });    
                }                
            });            
        }
    });
})
