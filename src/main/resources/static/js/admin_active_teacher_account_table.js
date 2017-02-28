/**
 * Created by jason on 2017/2/27.
 */

var adminSearchTeacherUrl = "/home/admin/activeTeacher/search";
var adminActiveTeacherUrl = "/home/admin/activeTeacher/active";
var adminCancelActiveTeacherUrl = "/home/admin/activeTeacher/cancelActive";
$(document).ready(function () {
    select_all();
    no_select_all();
    active();
    cancelActive();
})


//激活按钮事件  cancel_active_btn
function active(){
    //report_btn
    $("#active_btn").click(function () {
        //1.检查是否选中
        var checked = $("input[name='checkboxStatus']:checked");
        var checked_length = checked.length;

        if(checked_length <= 0){ //如果没有选中激活
            model_tip_show('model_tip','model_tip_content','未选择');
            return;
        }

        //2.检查选中的申请的状态是否有不可激活状态
        if(checkBeforeActive()==false){
            model_tip_show('model_tip','model_tip_content','只能激活状态为【未激活】, 请重新选择');
            return;
        }
        //3. 确认是否审核通过
        model_ok_show("model_ok","model_ok_content","确认是否激活选中的账户","model_ok_btn",active1);

    }) //click
} //function

function active1(){
    var checked = $("input[name='checkboxStatus']:checked");
    var xh_array = new Array();
    //2.组装数据，准备将数据发往服务器
    checked.each(function(){ //将选中的学号放到xh_array数组中
        var value = $(this).parent().parent().children("td.userId").text();
        xh_array.push(value);
    })
    $.ajax({
        type: "POST",
        url: adminActiveTeacherUrl,   //url
        contentType: "application/json",
        data: JSON.stringify({
            "userIds":xh_array,
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            // 200 成功   500 失败
            closeModal("model_ok");
            var status = data.code;
            var msg = data.msg;
            if(status == "200")  //200 成功
                model_tip_show('model_tip','model_tip_content','激活成功',refreshSearchResult);
            else if(status == "500")  //服务器繁忙
                model_tip_show('model_tip','model_tip_content','服务器繁忙，请稍后再试');
            else
                model_tip_show('model_tip','model_tip_content','服务器开小差了, 请稍后再试');
        },

        error: function(XMLHttpRequest, textStatus) {
        },

        complete: function(XMLHttpRequest, textStatus){
        }

    }); //ajax
} //function review1

//取消激活按钮事件
function cancelActive(){
    //cancel_report_btn
    $("#cancel_active_btn").click(function () {
        //1.检查是否有选中
        var checked = $("input[name='checkboxStatus']:checked");
        var checked_length = checked.length;
        if(checked_length <= 0){ //如果没有选中申请
            model_tip_show('model_tip','model_tip_content','未选择激活');
            return;
        }
        //2.检查选中的状态是否正确
        if(checkBeforeCancelActive()==false){
            model_tip_show('model_tip','model_tip_content','只能取消状态为【已激活】的账户, 请重新选择');
            return;
        }
        //确认是否取消审核
        model_ok_show("model_ok","model_ok_content","确认是否取消激活该账户","model_ok_btn",cancelActive1);

    }) //click
}

function cancelActive1(){
    var checked = $("input[name='checkboxStatus']:checked");
    var xh_array = new Array();
    //3.组装数据发给后台服务器处理
    checked.each(function(){ //将选中的学号放到xh_array数组中
        var value = $(this).parent().parent().children("td.userId").text();
        xh_array.push(value);
    })
    $.ajax({
        type: "POST",
        url: adminCancelActiveTeacherUrl,
        contentType: "application/json",
        data: JSON.stringify({
            "userIds":xh_array,
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            // 200 成功    500 失败
            closeModal("model_ok");
            var status = data.code;
            var msg = data.msg;
            if(status == "200")  //200 成功
                model_tip_show('model_tip','model_tip_content','取消激活成功',refreshSearchResult);
            else if(status == "500")  //服务器繁忙
                model_tip_show('model_tip','model_tip_content','服务器繁忙，请稍后再试');
            else
                model_tip_show('model_tip','model_tip_content','服务器开小差了, 请稍后再试');
        },

        error: function(XMLHttpRequest, textStatus) {
        },

        complete: function(XMLHttpRequest, textStatus){
        }

    }); //ajax
}

function refreshSearchResult(){
    var wrapLocation = "#table_wrap";
    $.ajax({
        type: "POST",
        url: adminSearchTeacherUrl,
        contentType: "application/json",
        data: JSON.stringify({
            "department":$("#department").val(),
            "activeStatus":$("#activeStatus").val(),
            "realName":$("#realName").val(),
            "userId":$("#userId").val(),
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
}

//激活前检查
function checkBeforeActive(){
    var _checked = $("input[name='checkboxStatus']:checked");

    var inspectV = 1;
    _checked.each(function(){
        var status =  $(this).parent().parent().children("td.active_status").text();
        if(status!='未激活'){
            inspectV = 0;
            return;
        }
    });
    if(inspectV==1)
        return true;
    else
        return false;
}

//取消激活前检查
function checkBeforeCancelActive(){
    var _checked = $("input[name='checkboxStatus']:checked");
    var inspectV = 1;
    _checked.each(function(){
        var status = $(this).parent().parent().children("td.active_status").text();
        if(status !='已激活'){
            inspectV = 0;
            return;
        }
    });
    if(inspectV==1)
        return true;
    else
        return false;
}

//全选按钮事件
function select_all(){
    //select_all_btn
    $("#select_all_btn").click(function () {
        // name="checkboxStatus"
        $("input[name='checkboxStatus']").prop("checked",true);
    })
}

//反选按钮事件
function no_select_all(){
    //no_select_all_btn
    $("#no_select_all_btn").click(function () {
        // name="checkboxStatus"
        var checkbox = $("input[name='checkboxStatus']");
        checkbox.each(function () {
            $(this).prop("checked", !$(this).prop("checked"));
        })
    })
}
