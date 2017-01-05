/**
 * Created by Administrator on 2017/1/5.
 */

$(document).ready(function () {
    search();
});

//搜索按钮事件
function search(){
    //search_btn   school  year  apply_status   student_type  realName
    $("#search_btn").click(function(){
        $.ajax({
            type: "POST",
            url: "/home/student/thesis/manage/submit",
            contentType: "application/json",
            data: JSON.stringify({
                "department":$("#school").text(),
                "year":$("#year").val(),
                "applyStatus":$("#apply_status").val(),
                "studentType":$("#student_type").val(),
                "realName":$("#realName").val(),
            }),

            beforeSend: function(XMLHttpRequest){
            },

            success: function(data){
                // 200 成功    300 重复申请  500 失败
                var status = data.code;
                var msg = data.msg;
                if(status == "200")  //200 成功
                    model_tip_show('model_tip','model_tip_content','系统繁忙请稍后再试!')
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
