/**
 * Created by jason on 2017/2/26.
 */

$(document).ready(function () {
// t_report_thesis_btn   t_personal_info_btn
// a_review_thesis_btn    a_export_thesis_info_btn    a_import_student_info_btn
// a_active_teacher_btn   a_add_admin_account_btn   a_personal_info_btn
    loadStudentModalPage();
    loadTeacherModalPage();
    loadAdminModalPage();

});

//学生菜单模块
function loadStudentModalPage(){
    // 学生模块 --> 申请优秀论文|查询论文审核状态
    $("#s_thesis_apply_btn").click(function(){
        var savedUserId = $("#homeUserId").text();
        refreshToStudentQueryStatus();
    });

    // 学生模块 --> 管理个人信息
    $("#s_personal_info_btn").click(function () {
        refreshToStudentPersonInfoPage();
    });
};


//教务员菜单模块
function loadTeacherModalPage(){
    //教务员模块 --- 上报学生论文申请
    $("#t_report_thesis_btn").click(function(){
        refreshToTeacherReportThesisApplyPage();
    });

    //教务员模块 --- 管理个人信息
    $("#t_personal_info_btn").click(function(){
        refreshToTeacherPersonInfoPage();
    });
};


//管理员菜单模块
function loadAdminModalPage(){

    //管理员模块 --- 审核论文上报
    $("#a_review_thesis_btn").click(function(){
        // $("#home_right_wrap").load("admin_review_thesis.html");
        refreshToAdminReviewThesisApplyPage();
    });

    //管理员模块 --- 导出论文申请信息
    $("#a_export_thesis_info_btn").click(function(){
        // $("#home_right_wrap").load("admin_export_thesis_info.html");
        refreshToAdminExportThesisApplyPage();
    });

    //管理员模块 --- 导入毕业生信息
    $("#a_import_student_info_btn").click(function(){
        // $("#home_right_wrap").load("admin_import_student_info.html");
        refreshToAdminImportStudentInfoPage();
    });

    //管理员模块 --- 激活学院教务员
    $("#a_active_teacher_btn").click(function(){
        refreshToAdminActiveTeacherPage();
    });

    //管理员模块 --- 添加管理员
    $("#a_add_admin_account_btn").click(function(){
        refreshToAdminAddAdministratorPage();
    });

    //管理员模块 --- 管理个人信息
    $("#a_personal_info_btn").click(function(){
        refreshToAdminPersonInfoPage();
    });
};

