/**
 * Created by jason on 2017/2/18.
 */

var exportExeclFileUrl = "/home/admin/export/excel";
var exportLwtjbFileUrl = "/home/admin/export/tjb";
var exportThesisPdfFileUrl = "/home/admin/export/pdf";


$(document).ready(function () {
    select_all();
    no_select_all();
    exportExeclFile();
    exportLwtjbFile();
    exportThesisPdfFile();
})

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

//导出Excel基本信息表
function exportExeclFile(){
    $("#export_execl_file_btn").click(function () {
        //1.检查是否有选中
        var checked = $("input[name='checkboxStatus']:checked");
        var checked_length = checked.length;
        if(checked_length <= 0){ //如果没有选中申请
            model_tip_show('model_tip','model_tip_content','未选择申请');
            return;
        }
        //2.确认是否导出Execl文件
        model_ok_show("model_ok","model_ok_content","确认是否导出Execl文件","model_ok_btn",exportExeclFile1);
    })
};

function exportExeclFile1(){
    //1.取出选中userId
    var checked = $("input[name='checkboxStatus']:checked");
    var xh_array = new Array();
    checked.each(function(){ //将选中的学号放到xh_array数组中
        var value = $(this).parent().parent().children("td.userId").text();
        xh_array.push(value);
    });
    // alert("组装form开始");
    //2.开始向后台发送数据
    var form=$("<form>");//定义一个form表单
    form.attr("style","display:none");
    form.attr("enctype","multipart/form-data")
    form.attr("target","_self");
    form.attr("method","post");
    form.attr("action",exportExeclFileUrl);
    var input1=$("<input>");
    input1.attr("type","hidden");
    input1.attr("name","userIds");
    input1.attr("value",xh_array);
    $(".table_body").append(form);//将表单放置在web中
    form.append(input1);

    form.submit();//表单提交

    closeModal("model_ok");

}

//导出论文推荐表
function exportLwtjbFile(){
    $("#export_lwtjb_file_btn").click(function () {
        //1.检查是否有选中
        var checked = $("input[name='checkboxStatus']:checked");
        var checked_length = checked.length;
        if(checked_length <= 0){ //如果没有选中申请
            model_tip_show('model_tip','model_tip_content','未选择申请');
            return;
        }
        //2.确认是否导出论文推荐表
        model_ok_show("model_ok","model_ok_content","确认是否导出论文推荐表","model_ok_btn",exportLwtjbFile1);
    })
};

function exportLwtjbFile1(){
    //1.取出选中userId
    var checked = $("input[name='checkboxStatus']:checked");
    var xh_array = new Array();
    checked.each(function(){ //将选中的学号放到xh_array数组中
        var value = $(this).parent().parent().children("td.userId").text();
        xh_array.push(value);
    })
    //2.开始向后台发送数据
    //2.开始向后台发送数据
    var form=$("<form>");//定义一个form表单
    form.attr("style","display:none");
    form.attr("enctype","multipart/form-data")
    form.attr("target","_self");
    form.attr("method","post");
    form.attr("action",exportLwtjbFileUrl);
    var input1=$("<input>");
    input1.attr("type","hidden");
    input1.attr("name","userIds");
    input1.attr("value",xh_array);
    $(".table_body").append(form);//将表单放置在web中
    form.append(input1);

    form.submit();//表单提交

    closeModal("model_ok");
}
//导出论文PDF文件
function exportThesisPdfFile(){
    $("#export_thesis_pdf_file_btn").click(function () {
        //1.检查是否有选中
        var checked = $("input[name='checkboxStatus']:checked");
        var checked_length = checked.length;
        if(checked_length <= 0){ //如果没有选中申请
            model_tip_show('model_tip','model_tip_content','未选择申请');
            return;
        }
        //2.确认是否导出论文推荐表
        model_ok_show("model_ok","model_ok_content","确认是否导出所选论文","model_ok_btn",exportThesisPdfFile1);
    })
};

function exportThesisPdfFile1(){
    //1.取出选中userId
    var checked = $("input[name='checkboxStatus']:checked");
    var xh_array = new Array();
    checked.each(function(){ //将选中的学号放到xh_array数组中
        var value = $(this).parent().parent().children("td.userId").text();
        xh_array.push(value);
    })

    //2.开始向后台发送数据
    var form=$("<form>");//定义一个form表单
    form.attr("style","display:none");
    form.attr("enctype","multipart/form-data")
    form.attr("target","_self");
    form.attr("method","post");
    form.attr("action",exportThesisPdfFileUrl);
    var input1=$("<input>");
    input1.attr("type","hidden");
    input1.attr("name","userIds");
    input1.attr("value",xh_array);
    $(".table_body").append(form);//将表单放置在web中
    form.append(input1);

    form.submit();//表单提交

    closeModal("model_ok");
}

