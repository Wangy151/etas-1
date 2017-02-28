/**
 * Created by jason on 2017/2/15.
 */

var addAdimistratorUrl = "/home/admin/addAdministrator/save";

$(document).ready(function () {
    validateForm();
    sendEmailValidateCodeBtn();
    addAdministratorBtn();
});


function addAdministratorBtn(){
    // role   userId   realName   contactNumber   password  repeatPassword
    // email  email_validate_code   add_administrator_btn
    $("#add_administrator_btn").click(function(){
        var status = $("#basic_info_model_form").valid();
        if(status == false) return;
        $.ajax({
            type: "POST",
            url: addAdimistratorUrl,
            contentType: "application/json",

            data: JSON.stringify({
                "role":$("#role").val(),
                "userId":$("#userId").val(),
                "realName": $("#realName").val(),
                "phoneNumber": $("#contactNumber").val(),
                "password":$("#password").val(),
                "department":"研究生院学位办",
                "email": $("#email").val(),
                "mailVerifyCode": $("#email_validate_code").val(),

            }),

            beforeSend: function(XMLHttpRequest){
            },

            success: function(data){
                var status = data.code;
                var msg = data.msg;
                if(status == "200")  //学生注册成功
                    model_tip_show('model_tip','model_tip_content','管理员添加成功',refreshToAdminAddAdministratorPage);
                else if(status == "300")  //服务器原因失败
                    $("#v_email_validate_code").text("验证码错误");
                else if(status == "500")  //服务器原因失败
                    model_tip_show('model_tip','model_tip_content','服务器繁忙，请稍后再试');
                else
                    paramErrorAlert("data.status: ",status);
            },

            error: function(XMLHttpRequest, textStatus) {
            },

            complete: function(XMLHttpRequest, textStatus){
            }

        });
    })
}

function validateForm(){
    $("#basic_info_model_form").validate({
        //realName   contactNumber   email  email_validate_code
        rules:{
            userId:{
                required:true,
                remote: { //远程验证用户名是否已经存在,若存在false，否则true
                    url: "/register/checkUserIdRegister",//异步验证
                    type: "get",
                },
            },
            realName:{
                required:true,
                maxlength:30,
            },
            contactNumber:{
                required:true,
                number:true,
                minlength:11,
                maxlength:11,
            },
            password:{
                required:true,
                rangelength:[6,20],
            },
            repeatPassword:{
                required:true,
                equalTo:"#password",
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
            userId:{
                required:"教工号不能为空",
                remote:"已被注册",
            },
            realName:{
                required:"姓名不能为空",
                maxlength:"姓名长度不能超过30",
            },
            contactNumber:{
                required:"联系电话不能为空",
                number:"电话必须都为数字",
                minlength:"长度为11位",
                maxlength:"长度为11位",
            },
            password:{
                required:"登录密码不能为空",
                rangelength:"登录密码长度在6-20之间",
            },
            repeatPassword:{
                required:"确认密码不能为空",
                equalTo:"密码不一致",
            },
            email:{
                required:"邮箱不能为空",
                email:"邮箱格式不正确",
                remote:"已被注册",
            },
            email_validate_code:{
                required:"邮箱验证码不能为空",
            },

        },
        errorPlacement : function(error, element) {
            if(element.is("#realName"))
                error.appendTo($("#v_realName"));
            else if(element.is("#contactNumber"))
                error.appendTo($("#v_contactNumber"));
            else if(element.is("#userId"))
                error.appendTo($("#v_userId"));
            else if(element.is("#password"))
                error.appendTo($("#v_password"));
            else if(element.is("#repeatPassword"))
                error.appendTo($("#v_repeatPassword"));
            else if(element.is("#email"))
                error.appendTo($("#v_email"));
            else if(element.is("#email_validate_code"))
                error.appendTo($("#v_email_validate_code"));
            else
                ;
        },
    })
}

function sendEmailValidateCodeBtn(){
    //emailVerify_btn
    $("#emailVerify_btn").click(function(){
        if($("#email").valid() == false) return;
        var email = $("#email").val();
        sendEmailVerifyCode(email);
    })
}

