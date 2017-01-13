/**
 * Created by jason on 2017/1/12.
 */

$(document).ready(function () {
    basic_info_validate();
    basic_info_submit();
    fileUpload();
    // inputDisableToAble();
});

function basic_info_submit(){
    //  basic_info_form   basic_info_submit_btn
    //  ssdm   ssmc  xxdm   xxmc  zzxh  xh  cplx  gdfs  zzxm  xb
    // csny  mz  dsxm  lwtm  lwywtm  yjfx  lwzwgjz  lwys  gdlb  lwtjblj
    // lwywlj  rxny  hxwrq  yjxkm  yjxkmc  ejxkm  ejxkmc  zzzc  xxlxr  bz
    $("#basic_info_submit_btn").click(function(){
        if(checkFormValidateStatus() == false){
            $("#m_table1_warn").html("未上传文件");
            return;
        }
        $("#m_table1_warn").html("");
        $.ajax({
            type: "POST",
            url: "/home/student/apply/save/basicInfoTable",
            contentType: "application/json",
            data: JSON.stringify({
                //  ssdm   ssmc  xxdm   xxmc  zzxh  xh  cplx  gdfs  zzxm  xb
                "ssdm":$("#ssdm").val(),
                "ssmc":$("#ssmc").val(),
                "xxdm":$("#xxdm").val(),
                "xxmc":$("#xxmc").val(),
                "zzxh":$("#zzxh").val(),
                "xh":$("#xh").val(),
                "cplx":$("#cplx").val(),
                "gdfs":$("#gdfs").val(),
                "zzxm":$("#zzxm").val(),
                "xb":$("#xb").val(),
                // csny  mz  dsxm  lwtm  lwywtm  yjfx  lwzwgjz  lwys  gdlb  lwtjblj
                "csny":$("#csny").val(),
                "mz":$("#mz").val(),
                "dsxm":$("#dsxm").val(),
                "lwtm":$("#lwtm").val(),
                "lwywtm":$("#lwywtm").val(),
                "yjfx":$("#yjfx").val(),
                "lwzwgjz":$("#lwzwgjz").val(),
                "lwys":$("#lwys").val(),
                "gdlb":$("#gdlb").val(),
                "lwtjblj":$("#lwtjblj").val(),
                // lwywlj  rxny  hxwrq  yjxkm  yjxkmc  ejxkm  ejxkmc  zzzc  xxlxr  bz
                "lwywlj":$("#lwywlj").val(),
                "rxny":$("#rxny").val(),
                "hxwrq":$("#hxwrq").val(),
                "yjxkm":$("#yjxkm").val(),
                "yjxkmc":$("#yjxkmc").val(),
                "ejxkm":$("#ejxkm").val(),
                "ejxkmc":$("#ejxkmc").val(),
                "zzzc":$("#zzzc").val(),
                "xxlxr":$("#xxlxr").val(),
                "bz":$("#bz").val(),
            }),

            beforeSend: function(XMLHttpRequest){
            },

            success: function(data){
                var status = data.code;
                var msg = data.msg;
                if(status == "200")  //信息保存成功
                    model_tip_show('model_tip','model_tip_content','信息保存成功!');
                else if(status == "500")  //服务器繁忙
                    model_tip_show('model_tip','model_tip_content','系统繁忙，请稍后再试!');
                else
                    var empty = "";
            },

            error: function(XMLHttpRequest, textStatus) {
            },

            complete: function(XMLHttpRequest, textStatus){
            }

        });
    }) //click

}


function checkFormValidateStatus(){
    //basic_info_form  fileUplodStatus
    var form_status = $("#basic_info_form").valid();
    var file_status = $("#fileUplodStatus").val();
    if(form_status==true&&file_status=='1')
        return true;
    return false;
}

function fileUpload(){
    // article_file    fileUpload_btn   file_status_warn
    $("#fileUpload_btn").click(function () {
        $("#file_status_warn").html("");
        $("#fileUplodStatus").val("0");
        var fileName = $("#article_file").val();
        //未选择文件
        if(fileName==""){
            $("#file_status_warn").attr("class","text-danger");
            $("#file_status_warn").html("没有选择文件!");
            return;
        }
        //判断文件的后缀名是否为pdf
        var file_extent = fileName.substring(fileName.lastIndexOf(".")+1);
        if(file_extent!="pdf"){ //文件格式错误
            $("#file_status_warn").attr("class","text-danger");
            $("#file_status_warn").html("文件格式错误！");
            return;
        }
        //文件后缀名正确，提交文件
        $("#file_submit_form").ajaxSubmit({
            url: "upload.txt",
            type: "post",
            success: function (data) { //服务器回调函数
                var status = data.code;
                var msg = data.msg;
                if(status == "200"){//文件上传成功
                    $("#file_status_warn").attr("class","text-info");
                    $("#file_status_warn").html("文件上传成功！");
                    $("#fileUplodStatus").val("1");

                }
                else if(status == "500"){//文件上传不成功
                    $("#file_status_warn").attr("class","text-danger");
                    $("#file_status_warn").html("服务器繁忙，请稍后再试！");
                }

                else
                    var empty = "";
            } //success
        });

    })
}

function inputDisableToAble(){
    //前端已赋值:  ssdm  ssmc  xxdm  xxmc  zzxh  xxlxr
    //csny  dsxm  lwtm  lwtjblj  lwywlj  rxny
    //hxwrq  yjxkm  yjxkmc  ejxkm  ejxkmc
    $('input[disabled="disabled"]').removeAttr('disabled');
    $('select[disabled="disabled"]').removeAttr('disabled');
}
