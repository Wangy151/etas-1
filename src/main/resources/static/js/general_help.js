/**
 * Created by Administrator on 2016/12/25.
 */

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


//  mid_body         -->  ApplyThesisPage
// apply_form_wrap   -->  BasicInfoPage   TjbFramePage
//  tjb_wrap         -->  MasterTjbPage   DoctorTjbPage
//  savedThesisApplyUserId

function refreshToApplyThesisPage(userId,pageType){
    saveUserIdToPage(userId);
    saveThesisPageTypeToPage(pageType);
    var wrapLocation = ".mid_body";
    var requestUrl = "/home/student/apply/index";
    $.ajax({
        type: "POST",
        url: requestUrl,
        contentType: "application/json",
        data: JSON.stringify({

        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $("#savedThesisApplyUserId").val(userId);
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 401) {
                $("#home_right_wrap").html("您没有访问权限 ~");
            } else {
                $("#home_right_wrap").html("服务器繁忙, 请稍后再试 ~");
            }
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}

function refreshToBasicInfoPage(){
    var wrapLocation = "#apply_form_wrap";
    var requestUrl = "/home/student/apply/load/basicInfoTable";
    $.ajax({
        type: "POST",
        url: requestUrl,
        contentType: "application/json",
        data: JSON.stringify({
            "userId":getUserIdFromPage(),
            "pageType":getThesisPageTypeFromPage(),
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}

function refreshToTjbFramePage(){
    var wrapLocation = "#apply_form_wrap";
    var requestUrl = "/home/student/apply/load/tjbFrame";
    $.ajax({
        type: "POST",
        url: requestUrl,
        contentType: "application/json",
        data: JSON.stringify({
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}

function refreshToMasterTjbPage(){
    var wrapLocation = "#tjb_wrap";
    var requestUrl = "/home/student/apply/load/tjb";
    $.ajax({
        type: "POST",
        url: requestUrl,
        contentType: "application/json",
        data: JSON.stringify({
            "studentType":"master",
            "userId":getUserIdFromPage(),
            "pageType":getThesisPageTypeFromPage(),
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}

function refreshToDoctorTjbPage(userId,pageType){
    var wrapLocation = "#tjb_wrap";
    var requestUrl = "/home/student/apply/load/tjb";
    $.ajax({
        type: "POST",
        url: requestUrl,
        contentType: "application/json",
        data: JSON.stringify({
            "studentType":"doctor",
            "userId":getUserIdFromPage(),
            "pageType":getThesisPageTypeFromPage(),
        }),

        beforeSend: function(XMLHttpRequest){
        },

        success: function(data){
            $(wrapLocation).html(data);
        },
        error: function(XMLHttpRequest, textStatus) {
        },
        complete: function(XMLHttpRequest, textStatus){
        }
    }); // end ajax
}


////////////     保存临时值到页面中       //////////////
function saveUserIdToPage(userId){
    // savedThesisApplyUserId
    $("#savedThesisApplyUserId").val(userId);
}

function getUserIdFromPage(){
    // savedThesisApplyUserId
    var userId = $("#savedThesisApplyUserId").val();
    return userId;
}

// thesisPageType    0代表新增   1代表编辑   2代表查看
function saveThesisPageTypeToPage(thesisPageType){
    //  #savedThesisPageType
    $("#savedThesisPageType").val(thesisPageType);
}

function getThesisPageTypeFromPage(){
    //  #savedThesisPageType
    return $("#savedThesisPageType").val();
}

function getMyUserIdFromPage(){
    return $("#savedMyUserId").text();
}