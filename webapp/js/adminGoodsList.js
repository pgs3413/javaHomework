$(function(){
    $.ajax({
        url:"goodsList.do",
        type:"post",
        dataType:"json",
        success:function(list){
        $.each(list,function(index,goodsAndBusiness){
            var s="<tr>"+
            "<td>"+goodsAndBusiness.goods.id+"</td>"+
            "<td>"+goodsAndBusiness.goods.type+"</td>"+
            "<td>"+goodsAndBusiness.goods.title+"</td>"+
            "<td>"+goodsAndBusiness.goods.details+"</td>"+
            "<td>"+goodsAndBusiness.goods.price+"</td>"+
            "<td>"+goodsAndBusiness.business.businessName+"</td>"+
            "<td>"+goodsAndBusiness.business.name+"</td>"+
            "<td>"+goodsAndBusiness.business.phone+"</td>";
            if(goodsAndBusiness.goods.status==0){
                s+="<td>审核中</td>";
            }else if(goodsAndBusiness.goods.status==1){
                s+="<td>出售中</td>";
            }else if(goodsAndBusiness.goods.status==2){
                s+="<td>已下架</td>";
            }else if(goodsAndBusiness.goods.status==-1){
                s+="<td>审核失败</td>";
            }
            s+="</tr>";
            $("#body").append(s);
        })
        }
    })
})