$(function(){
    $.ajax({
        url:"busCheck.do",
        type:"post",
        dataType:"json",
        success:function(list){
        $.each(list,function(index,business){
            var s="<tr>"+
            "<td>"+business.id+"</td>"+
            "<td>"+business.businessName+"</td>"+
            "<td>"+business.name+"</td>"+
            "<td>"+business.phone+"</td>"+
            "<td>"+business.stuNum+"</td>"+
            "<td>"+business.major+"</td>"+
            "<td>"+business.className+"</td>"+
            "<td>"+business.email+"</td>"+
            "<td><a href=busOperate.do?op=1&&id="+business.id+">通过</a><a href=busOperate.do?op=2&&id="+business.id+">不通过</a></td></tr>";
            $("#body").append(s);
        })
        }
    })
})
