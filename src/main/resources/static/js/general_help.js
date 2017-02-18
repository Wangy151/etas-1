/**
 * Created by Administrator on 2016/12/25.
 */

var basicInfoCreateUrl = "/home/student/thesis/apply/basicInfoTable/create";
var basicInfoEditUrl = "/home/student/thesis/apply/basicInfoTable/edit";
var basicInfoViewUrl = "/home/student/thesis/apply/basicInfoTable/view";
var tjbFrameUrl = "/home/student/thesis/apply/tjb/frame";
var tjbCreateUrl = "/home/student/thesis/apply/tjb/create";
var tjbEditUrl = "/home/student/thesis/apply/tjb/edit";
var tjbViewUrl = "/home/student/thesis/apply/tjb/view";
var studentQueryThesisStatusUrl = "/home/student/thesis/apply/index";

///     刷新页面                ////
//  mid_body         -->  ApplyThesisPage
// apply_form_wrap   -->  BasicInfoPage   TjbFramePage
//  tjb_wrap         -->  MasterTjbPage   DoctorTjbPage
//  savedThesisApplyUserId


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

function refreshToTjbFramePage(userId,pageType){
    saveUserIdToPage(userId);
    saveTjbPageTypeToPage(pageType);
    var wrapLocation = "#home_right_wrap";
    $.ajax({
        type: "POST",
        url: tjbFrameUrl,
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

function refreshToTjbCreatePage(studentType,userId){
    var wrapLocation = "#tjb_wrap";
    $.ajax({
        type: "POST",
        url: tjbCreateUrl,
        contentType: "application/json",
        data: JSON.stringify({
            "studentType":studentType,
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

function refreshToTjbEditPage(studentType,userId){
    var wrapLocation = "#tjb_wrap";
    $.ajax({
        type: "POST",
        url: tjbEditUrl,
        contentType: "application/json",
        data: JSON.stringify({
            "studentType":studentType,
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

function refreshToTjbViewPage(studentType,userId){
    var wrapLocation = "#tjb_wrap";
    $.ajax({
        type: "POST",
        url: tjbViewUrl,
        contentType: "application/json",
        data: JSON.stringify({
            "studentType":studentType,
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

    $("#model_tip_close_btn").click(close_func);
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

function paramErrorAlert(paramName,paramValue){
    alert("system param invalid error: "+ paramName + "  " + paramValue);
}