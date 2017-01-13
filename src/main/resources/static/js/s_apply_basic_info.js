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

function checkFormValidateStatus(){
    //  basic_info_form   basic_info_submit_btn  m_table1_warn
    //  ssdm   ssmc  xxdm   xxmc  zzxh  xh  cplx  gdfs  zzxm  xb
    // csny  mz  dsxm  lwtm  lwywtm  yjfx  lwzwgjz  lwys  gdlb  lwtjblj
    // lwywlj  rxny  hxwrq  yjxkm  yjxkmc  ejxkm  ejxkmc  zzzc  xxlxr  bz
    //////////////////////////////////////////////////////////////////
    //  ssdm   ssmc  xxdm   xxmc  zzxh  xh  cplx  gdfs  zzxm  xb
    if($("#ssdm").valid() == false) return false;
    if($("#ssmc").valid() == false) return false;
    if($("#xxdm").valid() == false) return false;
    if($("#xxmc").valid() == false) return false;
    if($("#zzxh").valid() == false) return false;
    if($("#xh").valid() == false) return false;
    if($("#cplx").valid() == false) return false;
    if($("#gdfs").valid() == false) return false;
    if($("#zzxm").valid() == false) return false;
    if($("#xb").valid() == false) return false;
    // csny  mz  dsxm  lwtm  lwywtm  yjfx  lwzwgjz  lwys  gdlb  lwtjblj
    if($("#csny").valid() == false) return false;
    if($("#mz").valid() == false) return false;
    if($("#dsxm").valid() == false) return false;
    if($("#lwtm").valid() == false) return false;
    if($("#lwywtm").valid() == false) return false;
    if($("#yjfx").valid() == false) return false;
    if($("#lwzwgjz").valid() == false) return false;
    if($("#lwys").valid() == false) return false;
    if($("#gdlb").valid() == false) return false;
    if($("#lwtjblj").valid() == false) return false;
    // lwywlj  rxny  hxwrq  yjxkm  yjxkmc  ejxkm  ejxkmc  zzzc  xxlxr  bz
    if($("#lwywlj").valid() == false) return false;
    if($("#rxny").valid() == false) return false;
    if($("#hxwrq").valid() == false) return false;
    if($("#yjxkm").valid() == false) return false;
    if($("#yjxkmc").valid() == false) return false;
    if($("#ejxkm").valid() == false) return false;
    if($("#ejxkmc").valid() == false) return false;
    if($("#zzzc").valid() == false) return false;
    if($("#xxlxr").valid() == false) return false;
    if($("#bz").valid() == false) return false;

    if($("#fileUplodStatus").val()!='1'){
        $("#m_table1_warn").html("未上传文件");
        return false;
    }
    return true;
}


