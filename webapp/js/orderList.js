$(function(){
    $.ajax({
        url:"orderList.do",
        type:"post",
        dataType:"json",
        success:function(list){
        $.each(list,function(index,order){
            var s="<tr>"+
            "<td>"+order.goods.id+"</td>"+
            "<td>"+order.goods.type+"</td>"+
            "<td>"+order.goods.title+"</td>"+
            "<td>"+order.goods.details+"</td>"+
            "<td>"+order.goods.price+"</td>"+
            "<td>"+order.user.userName+"</td>"+
            "<td>"+order.user.phone+"</td>"+
            "<td>"+order.trade.userAddress+"</td>";
            if(order.trade.status==0){
                s+="<td><a href=orderFinish.do?id="+order.trade.id+">点击确认发货</a></td>";
            }else if(order.trade.status==1){
                s+="<td>已发货</td>";
            }
            s+="</tr>";
            $("#body").append(s);
        })
        }
    })
})
