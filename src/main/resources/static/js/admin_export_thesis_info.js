/**
 * Created by jason on 2017/2/18.
 */

var adminExportSearchUrl = "/home/admin/export/search";

$(document).ready(function () {
    search();
    select_all();
    no_select_all();
    exportExeclFile();
    exportLwtjbFile();
    exportThesisPdfFile();
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
                "department":$("#department").text(),
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
