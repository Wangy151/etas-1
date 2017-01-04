/**
 * Created by Administrator on 2016/12/25.
 */

function model_tip_show(model_id,tip_id,content){
    $("#"+tip_id).html(""+content);

    $("#"+model_id).modal('show');
}
