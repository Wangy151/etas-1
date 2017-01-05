/**
 * Created by Administrator on 2017/1/5.
 */
    $(document).ready(function () {
        applyThesis();
    });

function applyThesis(){
    //applyThesis_btn   name="checkboxStatus"
    $("#applyThesis_btn").click(function () {
        var length = $("input[name='checkboxStatus']:checked").length;
        if(length>1){//选中了多个
            model_tip_show('model_tip','model_tip_content','一次只能提交一个申请');
        }else if(length<=0){//未选中
            model_tip_show('model_tip','model_tip_content','请选择要提交的申请');
        }else{ //选中了1个
            $.ajax({
                type: "POST",
                url: "/home/student/thesis/manage/submit",
                contentType: "application/json",
                data: JSON.stringify({

                }),

                beforeSend: function(XMLHttpRequest){
                },

                success: function(data){
                    // 200 成功    300 重复申请  500 失败
                    var status = data.code;
                    var msg = data.msg;
                    if(status == "200")  //200 成功
                        model_tip_show('model_tip','model_tip_content','提交申请成功');
                    else if(status == "300")  //300 重复申请
                        model_tip_show('model_tip','model_tip_content','您已提交申请, 请关注申请状态');
                    else if(status == "500")  //服务器繁忙
                        model_tip_show('model_tip','model_tip_content','服务器繁忙，请稍后再试');
                    else
                        var empty = "";
                },

                error: function(XMLHttpRequest, textStatus) {
                },

                complete: function(XMLHttpRequest, textStatus){
                }

            });
        } //else

    }) //click
}
