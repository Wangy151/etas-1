/**
 * Created by Administrator on 2016/12/25.
 */
var sendEmailVerifyCodeUrl = "/sendVerifyCodeMail?emailTo=";
var basicInfoCreateUrl = "/home/student/thesis/apply/basicInfoTable/create";
var basicInfoEditUrl = "/home/student/thesis/apply/basicInfoTable/edit";
var basicInfoViewUrl = "/home/student/thesis/apply/basicInfoTable/view";
var tjbFrameUrl = "/home/student/thesis/apply/tjb/frame";
var tjbCreateUrl = "/home/student/thesis/apply/tjb/create";
var tjbEditUrl = "/home/student/thesis/apply/tjb/edit";
var tjbViewUrl = "/home/student/thesis/apply/tjb/view";
var studentQueryThesisStatusUrl = "/home/student/thesis/apply/index";
var studentPersonInfoUrl = "";


var adminReviewThesisApplyUrl = "/home/admin/thesis/index";
var adminImportStudentInfoUrl = "/home/admin/import/index";
var adminExportThesisApplyUrl = "/home/admin/export/index";
var adminAddAdministratorUrl = "";
var adminActiveTeacherUrl = "";
var adminPersonInfoUrl = "";


var teacherReportThesisApplyUrl = "/home/teacher/thesis/index";
var teacherPersonInfoUrl = "";

///////////////////////////////////////////
///////// 页面刷新开始       ///////////
////////////////////////////////////
function refreshToBasicInfoCreatePage(userId){
    var wrapLocation = "#home_right_wrap";
    $.ajax({
        type: "POST",
        url: basicInfoCreateUrl,
        contentType: "application/json",
        data: JSON.stringify({
            "userId":userId,
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 401) {
                $(wrapLocation).html("您没有访问权限 ~");
            } else {
                $(wrapLocation).html("服务器繁忙, 请稍后再试 ~");
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}

function refreshToBasicInfoEditPage(userId){
    var wrapLocation = "#home_right_wrap";
    $.ajax({
        type: "POST",
        url: basicInfoEditUrl,
        contentType: "application/json",
        data: JSON.stringify({
            "userId":userId,
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 401) {
                $(wrapLocation).html("您没有访问权限 ~");
            } else {
                $(wrapLocation).html("服务器繁忙, 请稍后再试 ~");
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}

function refreshToBasicInfoViewPage(userId){
    var wrapLocation = "#home_right_wrap";
    $.ajax({
        type: "POST",
        url: basicInfoViewUrl,
        contentType: "application/json",
        data: JSON.stringify({
            "userId":userId,
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 401) {
                $(wrapLocation).html("您没有访问权限 ~");
            } else {
                $(wrapLocation).html("服务器繁忙, 请稍后再试 ~");
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}

// function refreshToTjbFramePage(userId,pageType){
//     saveUserIdToPage(userId);
//     saveTjbPageTypeToPage(pageType);
//     var wrapLocation = "#home_right_wrap";
//     $.ajax({
//         type: "POST",
//         url: tjbFrameUrl,
//         contentType: "application/json",
//         data: JSON.stringify({
//         }),
//
//         beforeSend: function(XMLHttpRequest){
//         },
//
//         success: function(data){
//             $(wrapLocation).html(data);
//         },
//         error: function(XMLHttpRequest, textStatus) {
//             if (XMLHttpRequest.status == 401) {
//                 $(wrapLocation).html("您没有访问权限 ~");
//             } else {
//                 $(wrapLocation).html("服务器繁忙, 请稍后再试 ~");
//             }
//         },
//         complete: function(XMLHttpRequest, textStatus){
//         }
//     }); // end ajax
// }

function refreshToTjbCreatePage(userId){
    var wrapLocation = "#home_right_wrap";
    $.ajax({
        type: "POST",
        url: tjbCreateUrl,
        contentType: "application/json",
        data: JSON.stringify({
            "userId":userId,
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 401) {
                $(wrapLocation).html("您没有访问权限 ~");
            } else {
                $(wrapLocation).html("服务器繁忙, 请稍后再试 ~");
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}

function refreshToTjbEditPage(userId){
    var wrapLocation = "#home_right_wrap";
    $.ajax({
        type: "POST",
        url: tjbEditUrl,
        contentType: "application/json",
        data: JSON.stringify({
            "userId":userId,
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 401) {
                $(wrapLocation).html("您没有访问权限 ~");
            } else {
                $(wrapLocation).html("服务器繁忙, 请稍后再试 ~");
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}

function refreshToTjbViewPage(userId){
    var wrapLocation = "#home_right_wrap";
    $.ajax({
        type: "POST",
        url: tjbViewUrl,
        contentType: "application/json",
        data: JSON.stringify({
            "userId":userId,
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 401) {
                $(wrapLocation).html("您没有访问权限 ~");
            } else {
                $(wrapLocation).html("服务器繁忙, 请稍后再试 ~");
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}

function refreshToStudentQueryStatus() {
    var wrapLocation = "#home_right_wrap";
    $.ajax({
        type: "POST",
        url: studentQueryThesisStatusUrl,
        contentType: "application/json",
        data: JSON.stringify({
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 401) {
                $(wrapLocation).html("您没有访问权限 ~");
            } else {
                $(wrapLocation).html("服务器繁忙, 请稍后再试 ~");
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}

function refreshToStudentPersonInfoPage(){
    var wrapLocation = "#home_right_wrap";
    $.ajax({
        type: "POST",
        url: studentPersonInfoUrl,
        contentType: "application/json",

        data: JSON.stringify({

        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },

        error: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 401) {
                $(wrapLocation).html("您没有访问权限 ~");
            } else {
                $(wrapLocation).html("服务器繁忙, 请稍后再试 ~");
            }
        },

        complete: function(XMLHttpRequest, textStatus){
        }

    });//end ajax
}



function refreshToAdminReviewThesisApplyPage(){
    var wrapLocation = "#home_right_wrap";
    $.ajax({
        type: "POST",
        url: adminReviewThesisApplyUrl,
        contentType: "application/json",
        data: JSON.stringify({
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 401) {
                $(wrapLocation).html("您没有访问权限 ~");
            } else {
                $(wrapLocation).html("服务器繁忙, 请稍后再试 ~");
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}

function refreshToAdminExportThesisApplyPage(){
    var wrapLocation = "#home_right_wrap";
    $.ajax({
        type: "POST",
        url: adminExportThesisApplyUrl,
        contentType: "application/json",
        data: JSON.stringify({
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 401) {
                $(wrapLocation).html("您没有访问权限 ~");
            } else {
                $(wrapLocation).html("服务器繁忙, 请稍后再试 ~");
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}

function refreshToAdminImportStudentInfoPage(){
    var wrapLocation = "#home_right_wrap";
    $.ajax({
        type: "POST",
        url: adminImportStudentInfoUrl,
        contentType: "application/json",
        data: JSON.stringify({
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 401) {
                $(wrapLocation).html("您没有访问权限 ~");
            } else {
                $(wrapLocation).html("服务器繁忙, 请稍后再试 ~");
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}

function refreshToAdminAddAdministratorPage(){
    var wrapLocation = "#home_right_wrap";
    $.ajax({
        type: "POST",
        url: adminAddAdministratorUrl,
        contentType: "application/json",
        data: JSON.stringify({
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 401) {
                $(wrapLocation).html("您没有访问权限 ~");
            } else {
                $(wrapLocation).html("服务器繁忙, 请稍后再试 ~");
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}

function refreshToAdminActiveTeacherPage(){
    var wrapLocation = "#home_right_wrap";
    $.ajax({
        type: "POST",
        url: adminActiveTeacherUrl,
        contentType: "application/json",
        data: JSON.stringify({
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 401) {
                $(wrapLocation).html("您没有访问权限 ~");
            } else {
                $(wrapLocation).html("服务器繁忙, 请稍后再试 ~");
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}

function refreshToAdminPersonInfoPage(){
    var wrapLocation = "#home_right_wrap";
    $.ajax({
        type: "POST",
        url: adminPersonInfoUrl,
        contentType: "application/json",

        data: JSON.stringify({

        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },

        error: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 401) {
                $(wrapLocation).html("您没有访问权限 ~");
            } else {
                $(wrapLocation).html("服务器繁忙, 请稍后再试 ~");
            }
        },

        complete: function(XMLHttpRequest, textStatus){
        }

    });//end ajax
}



function refreshToTeacherReportThesisApplyPage(){
    var wrapLocation = "#home_right_wrap";
    $.ajax({
        type: "POST",
        url: teacherReportThesisApplyUrl,
        contentType: "application/json",
        data: JSON.stringify({
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 401) {
                $(wrapLocation).html("您没有访问权限 ~");
            } else {
                $(wrapLocation).html("服务器繁忙, 请稍后再试 ~");
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}

function refreshToTeacherPersonInfoPage(){
    var wrapLocation = "#home_right_wrap";
    $.ajax({
        type: "POST",
        url: teacherPersonInfoUrl,
        contentType: "application/json",

        data: JSON.stringify({

        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },

        error: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 401) {
                $(wrapLocation).html("您没有访问权限 ~");
            } else {
                $(wrapLocation).html("服务器繁忙, 请稍后再试 ~");
            }
        },

        complete: function(XMLHttpRequest, textStatus){
        }

    });//end ajax
}


///////////////////////////////////////////
///////// 页面刷新结束       ///////////
////////////////////////////////////


////////////     保存临时值到页面中       //////////////
function saveUserIdToPage(userId){
    // savedThesisApplyUserId
    $("#savedThesisApplyUserId").text(userId);
}

function getUserIdFromPage(){
    // savedThesisApplyUserId
    var userId = $("#savedThesisApplyUserId").text();
    return userId;
}

// thesisPageType    0代表新增   1代表编辑   2代表查看
function saveTjbPageTypeToPage(thesisPageType){
    //  #savedThesisPageType
    $("#savedThesisPageType").text(thesisPageType);
}

function getTjbPageTypeFromPage(){
    //  #savedThesisPageType
    return $("#savedThesisPageType").text();
}

function getMyUserIdFromPage(){
    return $("#savedMyUserId").text();
}
// 硕士\博士
function saveApplyTypeToPage(applyType){
    $("#savedApplyType").text(applyType)
}

function getApplyTypeFromPage(){
    return $("#savedApplyType").text();
}


function model_tip_show(model_id,tip_id,content,close_func){
    $("#"+tip_id).html(""+content);

    $("#"+model_id).modal('show');

    $('div[class="model_btn_close"] button').click(close_func);;

}

//model_ok_show("model_ok","model_ok_content","确认是否提交申请","model_ok_btn",success_func)
function model_ok_show(model_id,content_id,content,ok_btn_id,success_func){
    $("#"+content_id).html(""+content);

    $("#"+model_id).modal('show');

    $("#"+ok_btn_id).click(success_func);

}

function closeModal(modalId){
    $("#"+modalId).modal('hide');
}

function openModalWindow(modalId){
    $("#"+modalId).modal('show');
}

function paramErrorAlert(paramName,paramValue){
    alert("system param invalid error: "+ paramName + "  " + paramValue);
}

function sendEmailVerifyCode(email){
    //emailVerify_btn
    $.ajax({
        type: "GET",
        url: sendEmailVerifyCodeUrl+email,
        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            var status = data.code;
            var msg = data.msg;
            if(status == "200")
                model_tip_show('model_tip','model_tip_content','系统已发送邮箱验证码，请查收');
            else if(status ==  "500")
                model_tip_show('model_tip','model_tip_content','系统繁忙请稍后再试');
            else
                model_tip_show('model_tip','model_tip_content','系统繁忙请稍后再试');
        },

        error: function(XMLHttpRequest, textStatus) {
        },

        complete: function(XMLHttpRequest, textStatus){
        }

    });
}
