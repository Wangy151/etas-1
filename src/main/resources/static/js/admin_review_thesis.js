/**
 * Created by Administrator on 2017/1/7.
 */

//year apply_status department   student_type  realName   search_btn
$(document).ready(function () {
    search();
});

//搜索按钮事件
function search(){
    //talbe_wrap   search_btn   school  year  apply_status   student_type  realName
    $("#search_btn").click(function(){
        $.ajax({
            type: "POST",
            url: "/home/admin/thesis/search",
            contentType: "application/json",
            data: JSON.stringify({
                "department":$("#school").text(),
                "applyYear":$("#year").val(),
                "applyStatus":$("#apply_status").val(),
                "studentType":$("#student_type").val(),
                "zzxm":$("#realName").val(),
            }),

            beforeSend: function(XMLHttpRequest){
                $("#talbe_wrap").empty();
            },

            success: function(data){
                $("#talbe_wrap").html(data);
            },

            error: function(XMLHttpRequest, textStatus) {
            },

            complete: function(XMLHttpRequest, textStatus){
            }

        }); //ajax
    }) //click
}
