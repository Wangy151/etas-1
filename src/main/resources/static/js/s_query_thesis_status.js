/**
 * Created by Administrator on 2017/1/5.
 */
$(document).ready(function () {
        basicInfoCreateBtn();
        basicInfoEditBtn();
        basicInfoViewBtn();
        tjbCreateBtn();
        tjbEditBtn();
        tjbViewBtn();
        deleteApplyBtn();
        submitApplyBtn();
    });

// var basicInfoStatus = $("#basicInfoStatus").text();
// var tjbStatus = $("#tjbStatus").text();
// var applyStatus = $("#applyStatus").text();


////   basicTable_create_btn  basicTable_edit_btn   basicTable_view_btn
///     tjb_create_btn   tjb_edit_btn    tjb_view_btn
/////   deleteApply_btn    submitApply_btn

function basicInfoCreateBtn(){
    $("#basicTable_create_btn").click(function () {
        //1.判断是否可以新增
        var basicInfoStatus = $("#basicInfoStatus").text();
        if(basicInfoStatus == "未完成")
            refreshToBasicInfoCreatePage(getMyUserIdFromPage());
        else if(basicInfoStatus == "完成")
            model_tip_show('model_tip','model_tip_content','基本信息表已经存在，不能再新增');
        else
            alert("System inner error :"+" basicInfoStatus "+basicInfoStatus);
    })
}

function basicInfoEditBtn(){
    $("#basicTable_edit_btn").click(function () {
        //1.判断是否可以编辑
        var basicInfoStatus = $("#basicInfoStatus").text();
        if(basicInfoStatus == "完成")
            refreshToBasicInfoEditPage(getMyUserIdFromPage());
        else if(basicInfoStatus == "未完成")
            model_tip_show('model_tip','model_tip_content','基本信息表不存在，请先新增');
        else
            alert("System inner error :"+" basicInfoStatus "+basicInfoStatus);
    })
}

function basicInfoViewBtn(){
    $("#basicTable_view_btn").click(function () {
        //1.判断是否可以预览
        var basicInfoStatus = $("#basicInfoStatus").text();
        if(basicInfoStatus == "完成")
            refreshToBasicInfoViewPage(getMyUserIdFromPage());
        else if(basicInfoStatus == "未完成")
            model_tip_show('model_tip','model_tip_content','基本信息表不存在，请先新增');
        else
            alert("System inner error :"+" basicInfoStatus "+basicInfoStatus);
    })

}

function tjbCreateBtn(){
    $("#tjb_create_btn").click(function () {
        //1.判断是否可以新增
        var tjbStatus = $("#tjbStatus").text();
        if(tjbStatus == "未完成")
            refreshToTjbFramePage(getMyUserIdFromPage(),"0");
        else if(tjbStatus == "完成")
            model_tip_show('model_tip','model_tip_content','论文推荐表已经存在，不能再新增');
        else
            alert("System inner error :"+" tjbStatus "+tjbStatus);
    })
}

function tjbEditBtn(){
    $("#tjb_edit_btn").click(function () {
        //1.判断是否可以修改
        var tjbStatus = $("#tjbStatus").text();
        if(tjbStatus == "完成")
            refreshToTjbFramePage(getMyUserIdFromPage(),"1");
        else if(tjbStatus == "未完成")
            model_tip_show('model_tip','model_tip_content','论文推荐表不存在，请先新增');
        else
            alert("System inner error :"+" tjbStatus "+tjbStatus);
    })
}

function tjbViewBtn(){
    $("#tjb_view_btn").click(function () {
        //1.判断是否可以修改
        var tjbStatus = $("#tjbStatus").text();
        if(tjbStatus == "完成")
            refreshToTjbFramePage(getMyUserIdFromPage(),"2");
        else if(tjbStatus == "未完成")
            model_tip_show('model_tip','model_tip_content','论文推荐表不存在，请先新增');
        else
            alert("System inner error :"+" tjbStatus "+tjbStatus);
    })
}

function deleteApplyBtn(){
    //delete_btn
    $("#deleteApply_btn").click(function(){
        //删除前判断状态是否可以删除
        var applyStatus = $("#applyStatus").text();
        if(applyStatus == "待学生提交" || applyStatus == "待学院上报")
        //确认是否删除
            model_ok_show("model_ok","model_ok_content","确认是否要删除申请","model_ok_btn",deleteApply1);
        else
            model_tip_show('model_tip','model_tip_content','申请已经提交, 不能删除');
    });//click
}

function deleteApply1(){
    $("#model_ok").modal("hide");
    //1.删除该申请
    // url: /home/student/thesis/manage/delete
    //删除论文申请    200 成功    300 学院教务员已审核通过, 不能删除     500 失败
    $.ajax({
        type: "POST",
        url: "/home/student/thesis/apply/delete",
        contentType: "application/json",
        data: JSON.stringify({

        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            // 200 成功    300 学院教务员已审核通过, 不能删除     500 失败
            var status = data.code;
            var msg = data.msg;
            if(status == "200"){//200 成功
                model_tip_show('model_tip','model_tip_content','删除申请成功',refreshToStudentQueryStatus);
            }
            else if(status == "300")  //300 学院教务员已审核通过, 不能删除
                model_tip_show('model_tip','model_tip_content','学院教务员已审核通过, 不能删除');
            else if(status == "500")  //服务器繁忙
                model_tip_show('model_tip','model_tip_content','服务器繁忙，请稍后再试');
            else
                var empty = "";
        },
        error: function(XMLHttpRequest, textStatus) {

        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax

    //2.页面重新刷新
    refreshToStudentQueryStatus();
}

function submitApplyBtn(){
    $("#submitApply_btn").click(function () {
        var basicInfoStatus = $("#basicInfoStatus").text();
        var tjbStatus = $("#tjbStatus").text();
        var applyStatus = $("#applyStatus").text();
        if(basicInfoStatus == "未完成" || tjbStatus == "未完成")
            model_tip_show('model_tip','model_tip_content','基本信息表或推荐表有未完成，请先完成');
        else{ //两个表都完成了
            if(applyStatus == "待学生提交")
                submitApply1();
            else
                model_tip_show('model_tip','model_tip_content','申请已提交，不能再重复提交');
        }

    }) //click
}
function submitApply1(){
    $("#model_ok").modal("hide");
    $.ajax({
        type: "POST",
        url: "/home/student/thesis/apply/submit",
        contentType: "application/json",
        data: JSON.stringify({

        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            // 200 成功    300 重复申请  500 失败
            var status = data.code;
            var msg = data.msg;
            if(status == "200"){//200 成功
                model_tip_show('model_tip','model_tip_content','提交申请成功',refreshToStudentQueryStatus);
            }
            else if(status == "300")  //300 重复申请
                model_tip_show('model_tip','model_tip_content','您已提交申请, 请关注申请状态');
            else if(status == "500")  //服务器繁忙
                model_tip_show('model_tip','model_tip_content','服务器繁忙，请稍后再试');
            else
                var empty = "";
        },

        error: function(XMLHttpRequest, textStatus) {
        },

        complete: function(XMLHttpRequest, textStatus){
        }

    });
}




