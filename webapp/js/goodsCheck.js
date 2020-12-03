$(function(){
    $.ajax({
        url:"goodsCheck.do",
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
            "<td>"+goodsAndBusiness.business.phone+"</td>"+
            "<td><a href=goodsOperate.do?op=1&&id="+goodsAndBusiness.goods.id+">通过</a><a href=goodsOperate.do?op=2&&id="+goodsAndBusiness.goods.id+">不通过</a></td></tr>";
            $("#body").append(s);
        })
        }
    })
})
