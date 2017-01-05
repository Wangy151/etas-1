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
