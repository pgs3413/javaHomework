$(function(){
    $.ajax({
        url:"recommend.do",
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
            "<td>"+goodsAndBusiness.business.businessName+"</td>";
            if(goodsAndBusiness.isRecommend=="true"){
                s+="<td>是</td>"+"<td><a href=recommendOperate.do?op=2&&id="+goodsAndBusiness.goods.id+">取消推荐</a></td></tr>";
            }else{
                s+="<td>否</td>"+"<td><a href=recommendOperate.do?op=1&&id="+goodsAndBusiness.goods.id+">设为推荐</a></td></tr>";
            }
            
            $("#body").append(s);
        })
        }
    })
})