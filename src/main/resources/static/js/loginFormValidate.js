/**
 * Created by Administrator on 2016/12/2.
 */
$(document).ready(function () {
    loginFormValidate();
})

function loginFormValidate(){
    $("#loginForm").validate({
        rules:{
            username:{
                required:true,
            },
            passwd:{
                required:true,
            },
            validateCode:{
                required:true,
            },
        },
        messages:{
            username:{
                required:"用户名不能为空",
            },
            passwd:{
                required:"密码不能为空",
            },
            validateCode:{
                required:"验证码不能为空",
            },
        },
        errorPlacement : function(error, element) {
            error.appendTo($("#validateWarn"));
        }
    })
}  //function