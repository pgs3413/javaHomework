$(function(){
    $("#btn").click(function(){
        var data=new FormData();
        data.append("title",$(".inTitle").val());
        data.append("details",$(".inDes").val());
        data.append("price",$(".inPri").val());
        data.append("type",$('input:radio:checked').val());
        data.append("photo",$(".inPhoto").get(0).files[0]);
        $.ajax({
            url:"addGoods.do",
            type:"post",
            contentType:false,
            data:data,
            processData:false,
            success:function(msg){
                alert(msg);
            }
        });
    });
})