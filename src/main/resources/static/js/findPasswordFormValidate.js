/**
 * Created by Administrator on 2016/12/2.
 */
$(document).ready(function () {
    findPasswordFormValidate();
})

function findPasswordFormValidate() {
    $("#findPasswordForm").validate({
        rules: {
            userId: {
                required: true,
                remote: { //远程验证用户名是否已经存在,若存在false，否则true
                    url: "/register/checkUserIdRegister",//异步验证
                    type: "get",
                },
            },
            validateCode: {
                required: true,
            },
        },

        messages: {
            // userId: {
            //     required: "学号/教工号不能为空",
            //     remote: "用户不存在",
            // },
            validateCode: {
                required: "验证码不为空",
            },
        },
        errorPlacement: function (error, element) {
            // if (element.is("#userId"))
            //     error.appendTo($("#v_idnumber"));
            // else
            if (element.is("#validateCode"))
                error.appendTo($("#v_validateCode"));
            else ;
        }
    })
}
 //function