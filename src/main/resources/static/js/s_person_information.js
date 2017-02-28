/**
 * Created by jason on 2017/2/27.
 */

var modifyBasicInfoUrl = "";
var modifyPasswordUrl = "";

$(document).ready(function () {
    initTabShow();
    initEmailVerifyCodeDivShow();
    changeEmailVerifyCodeDivShow();
    changeTabDiv();
    validateForm1();
    validateForm2();
    sendEmailValidateCode();
    submitBasicInfo();
    submitPasswordInfo();
});

function initEmailVerifyCodeDivShow(){
    $("#email_div_show").hide();
}

function changeEmailVerifyCodeDivShow(){
    $("#email").keyup(function(){
        $("#email_div_show").show();
    });
}

function changeTabDiv(){
    //
    $("#modify_basic_info_href").click(function(){
        $("#basic_info_model").show();
        $("#password_model").hide();
    });

    $("#modify_passwd_href").click(function(){
        $("#basic_info_model").hide();
        $("#password_model").show();
    });
}

function initTabShow(){
    $("#basic_info_model").show();
    $("#password_model").hide();
}

function validateForm1(){
    $("#basic_info_model_form").validate({
        // basic_info_model_form   realName   college  contactNumber   email  email_validate_code   modify_basic_info_btn
        rules:{
            studentType:{
                required:true,
            },
            realName:{
                required:true,
                maxlength:30,
            },
            college:{
                required:true,
            },
            contactNumber:{
                required:true,
                number:true,
                minlength:11,
                maxlength:11,
            },
            email:{
                required:true,
                email:true,
                remote: { //远程验证用户名是否已经存在,若存在false，否则true
                    url: "/register/checkEmailRegister?"+$("#email").val(),//异步验证
                    type: "get",
                },
            },
            email_validate_code:{
                required:true,
            },
        },
        messages:{
            studentType:{
                required:"学生类型不能为空",
            },
            realName:{
                required:"姓名不能为空",
                maxlength:"姓名长度不能超过30",
            },
            college:{
                required:"学院不能为空",
            },
            contactNumber:{
                required:"联系电话不能为空",
                number:"电话必须都为数字",
                minlength:"长度为11位",
                maxlength:"长度为11位",
            },
            email:{
                required:"邮箱不能为空",
                email:"邮箱格式不正确",
                remote:"已被注册!",
            },
            email_validate_code:{
                required:"邮箱验证码不能为空",
            },

        },
        errorPlacement : function(error, element) {
            if(element.is("#realName"))
                error.appendTo($("#v_realName"));
            else if(element.is("#college"))
                error.appendTo($("#v_college"));
            else if(element.is("#studentType"))
                error.appendTo($("#v_studentType"));
            else if(element.is("#contactNumber"))
                error.appendTo($("#v_contactNumber"));
            else if(element.is("#email"))
                error.appendTo($("#v_email"));
            else if(element.is("#email_validate_code"))
                error.appendTo($("#v_email_validate_code"));
            else
                ;
        },
    })
}

function validateForm2(){
    $("#password_model_form").validate({
        //new_password    repassword
        rules:{
            new_password:{
                required:true,
                minlength:5,
                maxlength:30,
            },
            repassword:{
                required:true,
                equalTo:"#new_password",
                minlength:5,
                maxlength:30,
            },

        },
        messages:{
            new_password:{
                required:"新密码不能为空",
                minlength:"最小长度为5",
                maxlength:"最大长度为30",
            },
            repassword:{
                required:"重复密码不能为空",
                equalTo:"两次密码输入不一致",
                minlength:"最小长度为5",
                maxlength:"最大长度为30",
            },

        },
        errorPlacement : function(error, element) {
            if(element.is("#new_password"))
                error.appendTo($("#v_new_password"));
            else if(element.is("#repassword"))
                error.appendTo($("#v_repassword"));
            else
                ;
        },
    })
}

function sendEmailValidateCode(){
    //emailVerify_btn
    $("#emailVerify_btn").click(function(){
        var email = $("#email").val();

        //检验邮箱输入框是否合法
        if($("#email").valid()) return;

        sendEmailVerifyCode(email);
    })
}

function submitBasicInfo(){
    // basic_info_model_form   realName   college  contactNumber   email  email_validate_code   modify_basic_info_btn
    $("#modify_basic_info_btn").click(function(){
        var status = $("#basic_info_model_form").valid();
        if(status == false) return;
        $.ajax({
            type: "POST",
            url: modifyBasicInfoUrl,
            contentType: "application/json",

            data: JSON.stringify({
                "studentType":$("#studentType").val(),
                "realName": $("#realName").val(),
                "department": $("#college").val(),
                "phoneNumber": $("#contactNumber").val(),
                "email": $("#email").val(),
                "mailVerifyCode": $("#email_validate_code").val(),

            }),

            beforeSend: function(XMLHttpRequest){
            },

            success: function(data){
                var status = data.code;
                var msg = data.msg;
                if(status == "200")  //成功
                    model_tip_show('model_tip','model_tip_content','信息保存成功',refreshToStudentPersonInfoPage);
                else if(status == "500")  //失败
                    model_tip_show('model_tip','model_tip_content','服务器繁忙，请稍后再试');
                else
                    model_tip_show('model_tip','model_tip_content','服务器繁忙，请稍后再试');
            },

            error: function(XMLHttpRequest, textStatus) {
            },

            complete: function(XMLHttpRequest, textStatus){
            }

        });//end ajax
    })
}

function submitPasswordInfo(){
    // password_model_form   new_password  repassword   modify_passwd_btn
    $("#modify_passwd_btn").click(function(){
        var status = $("#password_model_form").valid();
        if(status == false) return;
        $.ajax({
            type: "POST",
            url: modifyPasswordUrl,
            contentType: "application/json",

            data: JSON.stringify({
                "password": $("#new_password").val(),
                "repeatPassword": $("#repassword").val(),

            }),

            beforeSend: function(XMLHttpRequest){
            },

            success: function(data){
                var status = data.code;
                var msg = data.msg;
                if(status == "200")  //信息保存成功
                    model_tip_show('model_tip','model_tip_content','密码修改成功',refreshToStudentPersonInfoPage);
                else if(status == "500")  //保存失败
                    model_tip_show('model_tip','model_tip_content','服务器繁忙，请稍后再试');
                else
                    model_tip_show('model_tip','model_tip_content','服务器繁忙，请稍后再试');
            },

            error: function(XMLHttpRequest, textStatus) {
            },

            complete: function(XMLHttpRequest, textStatus){
            }

        });
    })
}
