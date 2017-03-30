/**
 * Created by jason on 2017/2/18.
 */

var adminExportSearchUrl = "/home/admin/export/search";
var updateStudentsXhUrl = "/home/admin/export/xh/update";

$(document).ready(function () {
    search();
    updateStudentsXH();
});

//搜索按钮事件
function search(){
    //talbe_wrap   search_btn   school  year  apply_status   student_type  realName
    $("#search_btn").click(function(){
        var wrapLocation = "#admin_export_table_wrap";
        $.ajax({
            type: "POST",
            url: adminExportSearchUrl,
            contentType: "application/json",
            data: JSON.stringify({
                "applyYear":$("#apply_year").val(),
                "applyStatus":$("#apply_status").val(),
                "studentType":$("#student_type").val(),
                "department":$("#department").val(),
            }),

            beforeSend: function(XMLHttpRequest){
                $(wrapLocation).empty();
            },

            success: function(data){
                $(wrapLocation).html(data);
            },

            error: function(XMLHttpRequest, textStatus) {
            },

            complete: function(XMLHttpRequest, textStatus){
            }

        }); //ajax
    }) //click
}

function updateStudentsXH(){
    //1.检查年份是否为空
    //2.弹出确认框
    //3.发送数据给后台处理
    //4.弹出浮动进度框
    //5.显示最后处理结果

    $("#update_students_xh_btn").click(function () {
        //1.检查年份是否为空
        var applyYear = $("#apply_year").val();
        if(applyYear == ""){
            model_tip_show('model_tip','model_tip_content','请选择学生的申请年份');
            return;
        }

        //2.弹出确认框
        var confirmMsg = "确认是否更新"+applyYear+"年所有通过审核学生的序号";
        model_ok_show("model_ok","model_ok_content",confirmMsg,"model_ok_btn",updateStudentsXH1);
    })

}

function updateStudentsXH1(){
    closeModal("model_ok");
    //3.发送数据给后台处理
    $.ajax({
        type: "POST",
        url: updateStudentsXhUrl,
        contentType: "application/json",

        data: JSON.stringify({
            "applyYear":$("#apply_year").val(),
        }),

        beforeSend: function(XMLHttpRequest){
            //4.弹出浮动进度框
            openModalWindow("model_update_xh");
        },

        success: function(data){
            var status = data.code;
            var msg = data.msg;
            closeModal("model_update_xh");
            if(status == "200"){
                model_tip_show('model_tip','model_tip_content','更新学生序号成功');
            }
            else if(status == "500")  //服务器原因失败
                model_tip_show('model_tip','model_tip_content','服务器繁忙，请稍后再试');
            else
                model_tip_show('model_tip','model_tip_content','服务器繁忙，请稍后再试');
        },

        error: function(XMLHttpRequest, textStatus) {
        },

        complete: function(XMLHttpRequest, textStatus){
        }

    });

}




