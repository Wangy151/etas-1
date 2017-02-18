/**
 * Created by jason on 2017/2/18.
 */

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
    $("#export_thesis_pdf_file_btn").click(function () {

    })
};
//导出论文推荐表
function exportLwtjbFile(){
    $("#export_lwtjb_file_btn").click(function () {

    })
};
//导出论文PDF文件
function exportThesisPdfFile(){
    $("#export_thesis_pdf_file_btn").click(function () {

    })
};
