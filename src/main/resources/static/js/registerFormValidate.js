/**
 * Created by Administrator on 2016/12/2.
 */
$(function () {
    validateForm ();
})

function validateForm () {
    $("#register_form").validate({
        rules: {
            role:{
                required:true,
            },
            username: {
                required:true,
                rangelength:[2,20],
                remote: { //远程验证用户名是否已经存在,若存在false，否则true
                    url: "/register/checkUsername",//异步验证
                    type: "post",
                    dataType: "json",
                    data: {//发送数据
                        username: function () {
                            return $("#username").val();
                        }
                    },

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
            idnumber:{
                required: true,
                rangelength:[3,15],

            },
            college:{
                required: true,
            },
            contactNumber:{
                required: true,
                minlength:11,
                maxlength:11,

            },
            email:{
                required: true,
                email:true,
                maxlength:30,
            },
            validateCode:{
                required: true,
            },
        },
        messages: {
            role:{
                required:"身份不能为空",
            },
            username: {
                required:"用户名不能为空",
                rangelength:"长度在2-20之间",
                remote:"用户名已存在",
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
            idnumber:{
                required: "学号/教工号不能为空",
                rangelength:"长度在2-15之间",

            },
            college:{
                required: "学院不能为空",
            },
            contactNumber:{
                required: "联系方式不能为空",
                minlength:"长度为11位",
                maxlength:"长度为11位",

            },
            email:{
                required: "邮箱不能为空",
                email:"邮箱不合法",
                maxlength:"最大长度为30",
            },
            validateCode:{
                required: "验证码不能为空",
            },
        },
        errorPlacement : function(error, element) {
            if(element.is("#username"))
                error.appendTo($("#v_username"));
            else if(element.is("#passwd"))
                error.appendTo($("#v_passwd"));
            else if(element.is("#repasswd"))
                error.appendTo($("#v_repasswd"));
            else if(element.is("#realName"))
                error.appendTo($("#v_realName"));
            else if(element.is("#idnumber"))
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