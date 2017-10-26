/**
 * index.html的js
 */

var projId = 0; //项目Id
var prodId = 0; //产品Id


//展示产品
showProduct();

function showProduct() {
    $.ajax({
        type:"get",
        url:"/gwtd/listProduct.do",
        dataType:"json",
        success:function(result){
            if(result.success && result.data.length>0){
                var listProduct = result.data;
                var ng_scope = $("#ng_scope");
                ng_scope.empty();
                for(var i=0;i<listProduct.length;i++){
                    var product = listProduct[i];
                    var sidebar_nav = $("<div>").addClass("sidebar_nav").attr("id","prod"+product.prodId);
                    var sidebar_title = $("<div>").addClass("sidebar_title")
                                                .append($("<div>").addClass("sidebar_title_inner")
                                                                .append($("<span>").addClass("sidebar_title_icon"))
                                                                .append($("<span>").addClass("sidebar_span").text(product.prodName)));
                    sidebar_nav.append(sidebar_title);
                    ng_scope.append(sidebar_nav);

                    $(sidebar_nav).children().on("click",function () {
                        prodId = $(this).parent().attr("id").substr(4);
                        if(prodId>0){
                            $("#addProject").show();
                        }
                        $.ajax({
                            type:"post",
                            url:"/gwtd/"+prodId+"/listProject.do",
                            dataType:"json",
                            data:{},
                            success:function(result){
                                if(result.success && result.data.length>0){
                                    var listProject = result.data;
                                    if(listProject != undefined && listProject.length>0){
                                        if($("#prod"+listProject[0].prodId+" ul").html()==undefined){
                                            for(var j = 0;j<listProject.length;j++){
                                                var project = listProject[j];
                                                var $ul = $("<ul>").addClass("sidebar_trans");

                                                var $li = $("<li>").addClass("nav_item").attr("id","proj"+project.projId)
                                                    .append($("<a>").addClass("sidebar_trans").attr("href","javascript:;")
                                                        .append($("<div>").addClass("nav_icon")
                                                            .append($("<span>").addClass("icon_ecs").text(project.projName))));

                                                $ul.append($li);
                                                $("#prod"+project.prodId).append($ul);

                                                $($li).on("click",function () {
                                                    projId = $(this).attr("id").substr(4);
                                                    showServer();
                                                    showVersion();
                                                });
                                            }

                                            $('.nav_item').on("mouseover",function () {
                                                $(this).addClass("box_on");
                                            });
                                            $('.nav_item').on("mouseout",function () {
                                                $(this).removeClass("box_on");
                                            });
                                        }else{
                                            var $sidebar_title = $("#prod"+listProject[0].prodId).children()[0];
                                            $($sidebar_title).siblings().remove();
                                            $('.sidebar_title_inner').on("mouseover",function () {
                                                $(this).addClass("box_on");
                                            });
                                            $('.sidebar_title_inner').on("mouseout",function () {
                                                $(this).removeClass("box_on");
                                            });
                                            /*$('.nav_item').on("mouseover",function () {
                                                $(this).addClass("box_on");
                                            });
                                            $('.nav_item').on("mouseout",function () {
                                                $(this).removeClass("box_on");
                                            });*/
                                        }
                                    }
                                }
                            }
                        })

                    })
                }

                $('.sidebar_title_inner').on("mouseover",function () {
                    $(this).addClass("box_on");
                });
                $('.sidebar_title_inner').on("mouseout",function () {
                    $(this).removeClass("box_on");
                });
            }else{
                alert(result.error);
            }

        }
    })

    $(".sidebar_title").eq(0).click();
}

/*展示新增产品弹框*/
$("#addProduct").on("click",function () {
    $('#add_product').modal();
})

/*展示新增项目弹框*/
$("#btn_pro_add").on("click",function () {
   $('#add_project').modal();
});

/*新增产品事件*/
$("#btn_prod_submit").on("click",function () {
   var prodName = $.trim($("#prod_name").val());
   var prodDesc = $.trim($("#prod_desc").val());

   if(prodName==undefined || prodName ==''){
       alert("请输入产品名");
       return false;
   }

    var gwtdProduct = {
        "prodName":prodName,
        "prodDesc":prodDesc
    };

    $.ajax({
        type:"post",
        url:"/gwtd/addProduct.do",
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        dataType:"json",
        data:JSON.stringify(gwtdProduct),
        success:function(result){
            if(result.success){
                alert("保存成功!")
                $(".btn-default").click();
                $("#prod_name").val("");
                $("#prod_desc").val("");
                showProduct();
            }else{
                alert(result.error);
            }
        }
    })

});

/*新增项目事件*/
$("#btn_proj_submit").on("click",function () {
    var projName = $.trim($("#proj_name").val());
    var projDesc = $.trim($('#proj_desc').val());

    if(projName==undefined || projName ==''){
        alert("请输入项目名");
        return false;
    }

    var gwtdProduct = {
        "prodId":prodId,
        "projName":projName,
        "projDesc":projDesc
    };

    $.ajax({
        type:"post",
        url:"/gwtd/addProject.do",
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        dataType:"json",
        data:JSON.stringify(gwtdProduct),
        success:function(result){
            if(result.success){
                alert("保存成功!")
                $(".btn-default").click();
                $("#proj_name").val("");
                $("#proj_desc").val("");
                //showProduct();
            }else{
                alert(result.error);
            }
        }
    })
})

//查询展示服务器内容
function showServer() {
    $.ajax({
        type:"post",
        url:"/gwtd/"+projId+"/listServer.do",
        dataType:"json",
        data:{},
        success:function(result){
            if(result.success && result.data.length>0){
                var listSerAndVer = result.data;
                var $tbody = $("#tbody_ser");
                $tbody.empty();
                for(var i=0;i<listSerAndVer.length;i++){
                    var serAndVer = listSerAndVer[i];
                    var tr = $("<tr>").append($("<td>").text(serAndVer.servName))
                                        .append($("<td>").text(serAndVer.servIp))
                                        .append($("<td>").text(serAndVer.servVerPort))
                                        .append($("<td>").text(serAndVer.verName))
                                        .append($("<td>").html(clockonwea(serAndVer.servVerUtime)))
                                        .append($("<td>").text(serAndVer.servDesc))
                                        .append($("<td>").html(clockonwea(serAndVer.servCtime)))
                                        .append($("<td>").append($("<span>").addClass("elrspancxxxx suspenhand elrspanb spanboxxaa")
                                                                            .attr("serId",serAndVer.servId)
                                                                            .attr("serName",serAndVer.servName)
                                                                            .attr("servIp",serAndVer.servIp)
                                                                            .attr("servVerPort",serAndVer.servVerPort)));
                    $tbody.append(tr);
                }
                //模拟复选框
                $('.elrspancxxxx').on("click", function() {
                    $(this).toggleClass('elrspanb');
                });
            }else{
                $("#tbody_ser").empty();
            }
        }
    })
}

//版本信息
var listVersion;

//查询展示版本内容列表
function showVersion() {
    $.ajax({
        type:"post",
        url:"/gwtd/"+projId+"/listVersion.do",
        dataType:"json",
        data:{},
        success:function(result){
            if(result.success && result.data.length>0){
                listVersion = result.data;
                var $tbody = $("#tbody_ver");
                $tbody.empty();
                for(var i=0;i<listVersion.length;i++){
                    var version = listVersion[i];
                    var tr = $("<tr>").append($("<td>").text(version.verName))
                        .append($("<td>").text(version.verUrl))
                        .append($("<td>").text(version.verDesc))
                        .append($("<td>").html(clockonwea(version.verCtime)));
                    $tbody.append(tr);
                }
            }else{
                $("#tbody_ver").empty();
            }
        }
    })
}

/*上传版本*/
$("#uploadFile").on("click",function(){
    var url = "/gwtd/"+projId+"/file/uploadFile.do";
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

//新增按钮的事件
$("#btn_add").click(function () {
    if (listVersion && listVersion.length>0){
        $('#add_myModal').modal();
        $("#checkbox_ver").empty();
        $("#checkbox_ver").append($("<option>").val("-1").text("请选择版本"))
        for(var i=0;i<listVersion.length;i++){
            var version = listVersion[i];
            $("#checkbox_ver").append($("<option>").val(version.verId).text(version.verName));
        }
    }else{
        alert("请选择项目!");
    }
});

/*存储更新的服务相关信息*/
var serArray = new Array();

//更新按钮事件
$("#btn_update").click(function () {
    if (listVersion && listVersion.length>0){
        $.each($("#tbody_ser").find("span"), function(index,value){
            if(!$(this).hasClass("elrspanb")){
                var serV = {
                    "serId": $(this).attr("serId"),
                    "serName": $(this).attr("serName"),
                    "servIp": $(this).attr("servIp"),
                    "servVerPort": $(this).attr("servVerPort")
                };
                serArray.push(serV);
            }
        })
        if(serArray.length<=0){
            alert("请勾选更新的服务器!");
            return false;
        }else {
            $('#update_myModal').modal();
            for (var i in serArray){
                var sers = serArray[i];
                $("#sel_serv_name").append($("<span>").text(sers.serName+"   |   "));
            }
            $("#update_ver").empty();
            $("#update_ver").append($("<option>").val("-1").text("请选择版本"))
            for(var i=0;i<listVersion.length;i++){
                var version = listVersion[i];
                $("#update_ver").append($("<option>").val(version.verId).text(version.verName));
            }
        }

    }else {
        alert("请选择服务器!");
    }

});


//取消更新
$("#btn_update_cancel").click(function () {
    $("#sel_serv_name").empty();
    serArray.splice(0,serArray.length);
});

//保存服务资料
$("#btn_add_submit").on("click",function () {
    var name = $.trim($("#txt_name").val());
    var ip = $.trim($("#txt_ip").val());
    var port = $.trim($("#txt_port").val());
    var ver = $("#checkbox_ver").val();
    var desc = $.trim($("#txt_desc").val());

    if(name==undefined||name == ''){
        alert("请输入服务名!");
        return false;
    }
    if(ip==undefined||ip == ''){
        alert("请输入服务ip!");
        return false;
    }
    if(port==undefined||port == ''){
        alert("请输入服务端口号!");
        return false;
    }
    if(ver<0){
        alert("请选择版本!");
        return false;
    }
    if (projId<=0){
        alert("请选择项目");
        return false;
    }

    var gwtdServer = {
        "projId":projId,
        "servName":name,
        "servIp":ip,
        "servVerPort":port,
        "verId":ver,
        "servDesc":desc
    };

    $.ajax({
        type:"post",
        url:"/gwtd/addServer.do",
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        dataType:"json",
        data:JSON.stringify(gwtdServer),
        success:function(result){
            if(result.success){
                alert("保存成功!")
                $(".btn-default").click();
                showServer();
                $("#txt_name").val("");
                $("#txt_ip").val("");
                $("#txt_port").val("");
                $("#checkbox_ver").val("-1");
                $("#txt_desc").val("");
            }else{
                alert(result.error);
            }
        }
    })


});

/*关闭时清除文本数据*/
$(".btn-default,.close").click(function () {


    $("#prod_name").val("");
    $("#prod_desc").val("");

    $("#proj_name").val("");
    $("#proj_desc").val("");

    $("#txt_name").val("");
    $("#txt_ip").val("");
    $("#txt_port").val("");
    $("#checkbox_ver").val("-1");
    $("#txt_desc").val("");
});


$("#btn_update_submit").on("click",function () {
    $("#sel_serv_name").text("无");
    serArray.splice(0,serArray.length);
});


//日期转换
function clockonwea(hisCTime) {
    var now = new Date(hisCTime);
    var year = now.getFullYear();
    var month = now.getMonth();
    var date = now.getDate();
    var day = now.getDay();
    var hour = now.getHours();
    var minu = now.getMinutes();
    var sec = now.getSeconds();
    var week;
    month = month + 1;
    if (month < 10) month = "0" + month;
    if (date < 10) date = "0" + date;
    if (hour < 10) hour = "0" + hour;
    if (minu < 10) minu = "0" + minu;
    if (sec < 10) sec = "0" + sec;
    var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
    week = arr_week[day];
    var time =  year + "-" + month + "-" + date  + " &nbsp;&nbsp;      " + hour + ":" + minu;

    return time;
}
