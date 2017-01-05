/**
 * Created by Administrator on 2017/1/5.
 */

//select_all_btn  no_select_all_btn  report_btn  cancel_report_btn
$(document).ready(function () {
    select_all();
    no_select_all();
    report();
    cancel_report();
});

function select_all(){
    //select_all_btn
    $("#select_all_btn").click(function () {
        // name="checkboxStatus"
        $("input[name='checkboxStatus']").prop("checked",true);
    })
}

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

function report(){
    //report_btn
    $("#report_btn").click(function () {
        var checked = $("input[name='checkboxStatus']:checked");
        var checked_length = checked.length;
        var xh_array = new Array();
        if(checked_length <= 0){ //如果没有选中申请
            model_tip_show('model_tip1','model_tip_content1','未选择申请!');
            return;
        }
        checked.each(function(){ //将选中的学号放到xh_array数组中
            var value = $(this).parent().next().html();
            xh_array.push(value);
        })
        $.ajax({
            type: "POST",
            url: "/home/student/thesis/manage/submit",
            contentType: "application/json",
            data: JSON.stringify({
                "userIds":xh_array,
            }),

            beforeSend: function(XMLHttpRequest){
            },

            success: function(data){
                // 200 成功    300 重复申请  500 失败
                var status = data.code;
                var msg = data.msg;
                if(status == "200")  //200 成功
                    model_tip_show('model_tip1','model_tip_content1','未选择申请!');
                else
                    var empty = "";
            },

            error: function(XMLHttpRequest, textStatus) {
            },

            complete: function(XMLHttpRequest, textStatus){
            }

        }); //ajax

    }) //click
} //function

function cancel_report(){
    //cancel_report_btn
    $("#cancel_report_btn").click(function () {
        var checked = $("input[name='checkboxStatus']:checked");
        var checked_length = checked.length;
        var xh_array = new Array();
        if(checked_length <= 0){ //如果没有选中申请
            model_tip_show('model_tip1','model_tip_content1','未选择申请!');
            return;
        }
        checked.each(function(){ //将选中的学号放到xh_array数组中
            var value = $(this).parent().next().html();
            xh_array.push(value);
        })
        $.ajax({
            type: "POST",
            url: "/home/student/thesis/manage/submit",
            contentType: "application/json",
            data: JSON.stringify({
                "userIds":xh_array,
            }),

            beforeSend: function(XMLHttpRequest){
            },

            success: function(data){
                // 200 成功    300 重复申请  500 失败
                var status = data.code;
                var msg = data.msg;
                if(status == "200")  //200 成功
                    model_tip_show('model_tip1','model_tip_content1','未选择申请!');
                else
                    var empty = "";
            },

            error: function(XMLHttpRequest, textStatus) {
            },

            complete: function(XMLHttpRequest, textStatus){
            }

        }); //ajax

    }) //click
}