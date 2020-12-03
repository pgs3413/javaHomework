$(function(){
    $.ajax({
        url:"goodsList.do",
        type:"post",
        dataType:"json",
        success:function(list){
        $.each(list,function(index,goods){
            var s="<tr>"+
            "<td>"+goods.id+"</td>"+
            "<td>"+goods.type+"</td>"+
            "<td>"+goods.title+"</td>"+
            "<td>"+goods.details+"</td>"+
            "<td>"+goods.price+"</td>";         
            if(goods.status==0){
                s+="<td>审核中</td>";
            }else if(goods.status==1){
                s+="<td>出售中</td>";
            }else if(goods.status==2){
                s+="<td>已下架</td>";
            }else if(goods.status==-1){
                s+="<td>审核失败</td>";
            }
            s+="</tr>";
            $("#body").append(s);
        })
        }
    })
})