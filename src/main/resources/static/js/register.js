/**
 * Created by Administrator on 2016/12/2.
 */
var registerUrl = "/register/submit";

$(function () {
    validateForm ();

    //更换验证码
    ChangeValidateCode();
    //发送邮箱验证码
    sendEmailVerifyCodeBtn();
    //注册
    register();
    //初始化学生类型下拉框div
    initStudentTypeDiv();
    //学生类型下拉框div变化
    changeStudentTypeDiv();
})


// 注册表单验证
function validateForm () {
    $("#register_form").validate({
        rules: {
            role:{
                required:true,
            },
            studentType:{
                required:true,
            },
            userId:{
                required: true,
                rangelength:[3,15],
                remote: { //远程验证用户名是否已经存在,若存在false，否则true
                    url: "/register/checkUserIdRegister",//异步验证
                    type: "get",
                },
            },
            passwd: {
                required: true,
                rangelength:[6,20],
            },
            repasswd: {
                required: true,
                rangelength:[6,20],
                equalTo:"#passwd",
            },
            realName:{
                required: true,
                rangelength:[2,15],

            },

            college:{
                required: true,
            },
            contactNumber:{
                required: true,
                minlength:11,
                maxlength:11,
                number:true,
            },
            email:{
                required: true,
                email:true,
                maxlength:30,
                remote: { //远程验证用户名是否已经存在,若存在false，否则true
                    url: "/register/checkEmailRegister?"+$("#email").val(),//异步验证
                    type: "get",
                },
            },
            validateCode:{
                required: true,
            },
        },
        messages: {
            role:{
                required:"身份不能为空",
            },
            studentType:{
                required:"学生类型不能为空",
            },
            passwd: {
                required: "密码不能为空",
                rangelength:"长度在6-20之间",
            },
            repasswd: {
                required: "确认密码不能为空",
                rangelength:"长度在6-20之间",
                equalTo:"两次密码输入不一致",
            },
            realName:{
                required: "姓名不能为空",
                rangelength:"长度在2-15之间",

            },
            userId:{
                required: "学号/教工号不能为空",
                rangelength:"长度在2-15之间",
                remote:"已被注册",
            },
            college:{
                required: "学院不能为空",
            },
            contactNumber:{
                required: "联系方式不能为空",
                minlength:"长度为11位",
                maxlength:"长度为11位",
                number:"联系方式为11位数字",
            },
            email:{
                required: "邮箱不能为空",
                email:"邮箱不合法",
                maxlength:"最大长度为30",
                remote:"已被注册!",
            },
            validateCode:{
                required: "验证码不能为空",
            },
        },
        errorPlacement : function(error, element) {
            if(element.is("#username"))
                error.appendTo($("#v_username"));
            else if(element.is("#studentType"))
                error.appendTo($("#v_studentType"));
            else if(element.is("#passwd"))
                error.appendTo($("#v_passwd"));
            else if(element.is("#repasswd"))
                error.appendTo($("#v_repasswd"));
            else if(element.is("#realName"))
                error.appendTo($("#v_realName"));
            else if(element.is("#userId"))
                error.appendTo($("#v_idnumber"));
            else if(element.is("#college"))
                error.appendTo($("#v_college"));
            else if(element.is("#contactNumber"))
                error.appendTo($("#v_contactNumber"));
            else if(element.is("#email"))
                error.appendTo($("#v_email"));
            else if(element.is("#validateCode"))
                error.appendTo($("#v_validateCode"));
            else if(element.is("#role"))
                error.appendTo($("#v_role"));
            else
                ;
        }
    });
}

//注册按钮
function register(){
    $("#register_btn").click(function(){
        var status = $("#register_form").valid();
        if(status){  //注册前验证表单
            $.ajax({
                type: "POST",
                url: registerUrl,
                contentType: "application/json",

                data: JSON.stringify({
                    "userId": $("#userId").val(),
                    "password": $("#passwd").val(),
                    "repeatPassword": $("#repasswd").val(),
                    "department": $("#college").val(),
                    "realName": $("#realName").val(),
                    "phoneNumber": $("#contactNumber").val(),
                    "email": $("#email").val(),
                    "role": $("#role").val(),
                    "studentType": $("#studentType").val(),
                    "mailVerifyCode": $("#validateCode").val(),
                }),

                beforeSend: function(XMLHttpRequest){
                },

                success: function(data){
                    var status = data.code;
                    var msg = data.msg;
                    if(status == "200")  //学生注册成功
                        location.assign("/studentSuccessRegister.html");
                    else if(status == "201")  //教务员注册成功
                        location.assign("/teacherSuccessRegister.html");
                    else if(status == "300")  //邮箱验证码错误
                        $("#v_validateCode").html(msg);
                    else if(status == "500")  //服务器原因失败
                        $("#validateWarn").html("系统繁忙请稍后再试");
                    else
                        var empty = "";
                },

                error: function(XMLHttpRequest, textStatus) {
                },

                complete: function(XMLHttpRequest, textStatus){
                }

            });
        } //if valid
    }); //click
} //register function

//改变验证码
function ChangeValidateCode() {
    $("#validateCode_btn").click(function(){
        $("#validateCodeImg").attr("src", $("#validateCodeImg").attr("src") + 1);
    });

}

//发送验证码
function sendEmailVerifyCodeBtn() {
    $("#emailVerify_btn").click(function () {
        if($("#email").valid()){  //发送邮箱验证码前验证表单验证码是否合法
            var email = $("#email").val();
            sendEmailVerifyCode(email);
        } //valid
    });

}


// 隐藏或者显示学生类型Div
function changeStudentTypeDiv(){
    $("#role").change(function () {
        var role = $("#role").val();
        if(role == "")
            initStudentTypeDiv();
        else if(role == "学生")
            showStudentTypeDiv();
        else if(role == "学院教务员")
            hideStudentTypeDiv();
        else
            paramErrorAlert("role",role);
    })
}

function hideStudentTypeDiv(){
    //studentType_hide
    $("#studentType_hide").hide();
}

function showStudentTypeDiv(){
    //studentType_hide
    $("#studentType_hide").show();
}

function initStudentTypeDiv(){
    //studentType_hide
    $("#studentType_hide").hide();
}

