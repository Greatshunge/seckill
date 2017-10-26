/*
    上传JS
 */
//绑定文件上传按钮
$("#submit").on("click",function(){
    var url = "/seckill/file/upload.do";
    var fd = new FormData();
    fd.append("file",$('#file')[0].files[0]);
    $.ajax({
        type:"post",
        processData: false,
        url:url,
        data:fd,
        dataType:"json",
        contentType:false,
        success :function(data){
            if(null != data && true == data.success){
                var url = data.data;
                $("#img1").attr("src",url);
            }else{
                alert(data.error);
            }
        }
    });
});

$("#button").on("click",function(){
    var url = "/seckill/file/uploadFile.do";
    var fd = new FormData();
    fd.append("file",$('#file2')[0].files[0]);
    $.ajax({
        type:"post",
        processData: false,
        url:url,
        data:fd,
        dataType:"json",
        contentType:false,
        success :function(data){
            if(null != data && true == data.success){
                alert("上传成功!")
            }else{
                alert(data.error);
            }
        }
    });
});