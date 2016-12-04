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
            },
            validateCode: {
                required: true,
            },
        },

        messages: {
            userId: {
                required: "学号/教工号不能为空",
            },
            validateCode: {
                required: "验证码不为空",
            },
        },
        errorPlacement: function (error, element) {
            if (element.is("#userId"))
                error.appendTo($("#v_idnumber"));
            else if (element.is("#validateCode"))
                error.appendTo($("#v_validateCode"));
            else ;
        }
    })
}
 //function