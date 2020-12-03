$(function(){
    $.ajax({
        url:"order.do",
        type:"post",
        dataType:"json",
        success:function(list){
        $.each(list,function(index,order){
            var s="<tr>"+
            "<td>"+order.goods.title+"</td>"+
            "<td>"+order.goods.details+"</td>"+
            "<td>"+order.goods.type+"</td>"+
            "<td>"+order.goods.price+"</td>"+
            "<td>"+order.trade.userAddress+"</td>";
            if(order.trade.status==0){
                s+="<td>未发货</td>";
            }else if(order.trade.status==1){
                s+="<td>已发货</td>";
            }
            s+="</tr>";
            $("#body").append(s);
        })
        }
    })
})
