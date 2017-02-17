/**
 * Created by jason on 2017/1/12.
 */
var submitUrl = "/home/student/thesis/apply/basicInfoTable/save";
var fileUploadUrl = "/home/student/thesis/apply/basicInfoTable/pdf/upload";


$(document).ready(function () {
    basic_info_validate();
    basicInfoSave();
    basicInfoSubmit();
    fileUpload();
//  inputDisableToAble();
});

//保存基本信息
function basicInfoSave()  {
    //  basic_info_form   basic_info_submit_btn
    //  ssdm   ssmc  xxdm   xxmc  zzxh  xh  cplx  gdfs  zzxm  xb
    // csny  mz  dsxm  lwtm  lwywtm  yjfx  lwzwgjz  lwys  gdlb  lwtjblj
    // lwywlj  rxny  hdxwrq  yjxkdm  yjxkmc  ejxkdm  ejxkmc  zzzc  xxlxr  bz
    $("#basic_info_save_btn").click(function(){
        if(checkFormValidateStatus() == false){
            return;
        }
        $("#m_table1_warn").html("");
        var studentType;
        if($("#cplx").val() == "湖北省优秀硕士论文评选（已授学位）")
            studentType = "硕士";
        else if($("#cplx").val() == "湖北省优秀博士论文评选（已授学位）")
            studentType = "博士";
        else
            studentType = "硕士";
        //把学生类型保存到网页中
        saveApplyTypeToPage(studentType);
        //开始保存
        $.ajax({
            type: "POST",
            url: submitUrl,
            contentType: "application/json",
            data: JSON.stringify({
                "studentType":studentType,
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
                // lwywlj  rxny  hdxwrq  yjxkdm  yjxkmc  ejxkdm  ejxkmc  zzzc  xxlxr  bz
                "lwywlj":$("#lwywlj").val(),
                "rxny":$("#rxny").val(),
                "hdxwrq":$("#hdxwrq").val(),
                "yjxkdm":$("#yjxkdm").val(),
                "yjxkmc":$("#yjxkmc").val(),
                "ejxkdm":$("#ejxkdm").val(),
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
                if(status == "200")  //信息保存成功，进入下一步
                    model_tip_show('model_tip','model_tip_content','信息保存成功');
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

//新增基本信息
function basicInfoSubmit()  {
    //  basic_info_form   basic_info_submit_btn
    //  ssdm   ssmc  xxdm   xxmc  zzxh  xh  cplx  gdfs  zzxm  xb
    // csny  mz  dsxm  lwtm  lwywtm  yjfx  lwzwgjz  lwys  gdlb  lwtjblj
    // lwywlj  rxny  hdxwrq  yjxkdm  yjxkmc  ejxkdm  ejxkmc  zzzc  xxlxr  bz
    $("#basic_info_submit_btn").click(function(){
        if(checkFormValidateStatus() == false){
            return;
        }
        $("#m_table1_warn").html("");
        var studentType;
        if($("#cplx").val() == "湖北省优秀硕士论文评选（已授学位）")
            studentType = "硕士";
        else if($("#cplx").val() == "湖北省优秀博士论文评选（已授学位）")
            studentType = "博士";
        else
            studentType = "硕士";
        //把学生类型保存到网页中
        saveApplyTypeToPage(studentType);
        //开始保存
        $.ajax({
            type: "POST",
            url: submitUrl,
            contentType: "application/json",
            data: JSON.stringify({
                "studentType":studentType,
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
                // lwywlj  rxny  hdxwrq  yjxkdm  yjxkmc  ejxkdm  ejxkmc  zzzc  xxlxr  bz
                "lwywlj":$("#lwywlj").val(),
                "rxny":$("#rxny").val(),
                "hdxwrq":$("#hdxwrq").val(),
                "yjxkdm":$("#yjxkdm").val(),
                "yjxkmc":$("#yjxkmc").val(),
                "ejxkdm":$("#ejxkdm").val(),
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
                if(status == "200")  //信息保存成功，进入下一步
                    $("#model_next").show();
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


//上传文件
function fileUpload(){
    // article_file    fileUpload_btn   file_status_warn
    $("#fileUpload_btn").click(function () {
        //0.初始化
        $("#file_status_warn").html("");
        $("#fileUplodStatus").val("0");
        var fileName = $("#article_file").val();
        //1.未选择文件
        if(fileName==""){
            $("#file_status_warn").attr("class","text-danger");
            $("#file_status_warn").html("没有选择文件!");
            return;
        }
        //2.判断文件的后缀名是否为pdf
        var file_extent = fileName.substring(fileName.lastIndexOf(".")+1);
        if(file_extent!="pdf"){ //文件格式错误
            $("#file_status_warn").attr("class","text-danger");
            $("#file_status_warn").html("文件格式错误！");
            return;
        }
        //3.文件后缀名正确，提交文件
        fileUpload1();

    })
}

function fileUpload1(){
    // 1.检查是否可以上传文件
    if(checkIfCanUploadFile()==false){
        $("#file_status_warn").attr("class","text-danger");
        $("#file_status_warn").html("学号或者二级学科代码为空，不能上传文件");
        return;
    }
    // 2.组装数据到文件上传表单中
    var fileName = "10487"+"_"+getEjxkdmFromPage()+"_"+$("#zzxh").val()+"_LW.pdf";
    $("#FileUploadFileName").val(fileName);
    $("#FileUploadUserId").val(getUserIdFromPage());

    // 3.上传文件
    $("#file_submit_form").ajaxSubmit({
        url: fileUploadUrl,
        type: "post",
        success: function (data) { //服务器回调函数
            //200:成功  300:论文中含有敏感字符(如华中科技大学字样) 500:失败
            var status = data.code;
            var msg = data.msg;
            if(status == "200"){//文件上传成功
                $("#file_status_warn").attr("class","text-info");
                $("#file_status_warn").html("文件上传成功！");
                $("#fileUplodStatus").val("1");

            }
            else if(status == "300"){//论文中含有敏感字符(如华中科技大学字样)
                $("#file_status_warn").attr("class","text-danger");
                $("#file_status_warn").html("上传失败:论文中含有敏感字符！(如华中科技大学)");
            }

            else if(status == "500"){//文件上传不成功
                $("#file_status_warn").attr("class","text-danger");
                $("#file_status_warn").html("服务器繁忙，请稍后再试！");
            }
            else{
                $("#file_status_warn").attr("class","text-danger");
                $("#file_status_warn").html("服务器繁忙，请稍后再试！");
            }
        } //success
    }); //ajaxSubmit

}

//信息保存成功，进入下一步
function nextStepAfterSave(){
    refreshToTjbFramePage();
    var studentType = getApplyTypeFromPage();
    if(studentType == '硕士'){
        $("#student_type").val('master');
        refreshToMasterTjbPage();
    }else if(studentType == '博士'){
        $("#student_type").val('doctor');
        refreshToDoctorTjbPage();
    }else{
        alert("系统繁忙，请重新刷新页面");
    }

}

//检查表单验证状态
function checkFormValidateStatus(){
    //  basic_info_form   basic_info_submit_btn  m_table1_warn
    //  ssdm   ssmc  xxdm   xxmc  zzxh  xh  cplx  gdfs  zzxm  xb
    // csny  mz  dsxm  lwtm  lwywtm  yjfx  lwzwgjz  lwys  gdlb  lwtjblj
    // lwywlj  rxny  hdxwrq  yjxkdm  yjxkmc  ejxkdm  ejxkmc  zzzc  xxlxr  bz
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
    // lwywlj  rxny  hdxwrq  yjxkdm  yjxkmc  ejxkdm  ejxkmc  zzzc  xxlxr  bz
    if($("#lwywlj").valid() == false) return false;
    if($("#rxny").valid() == false) return false;
    if($("#hdxwrq").valid() == false) return false;
    if($("#yjxkdm").valid() == false) return false;
    if($("#yjxkmc").valid() == false) return false;
    if($("#ejxkdm").valid() == false) return false;
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

function basic_info_validate(){
    //  basic_info_form   basic_info_submit_btn  m_table1_warn
    //  ssdm   ssmc  xxdm   xxmc  zzxh  xh  cplx  gdfs  zzxm  xb
    // csny  mz  dsxm  lwtm  lwywtm  yjfx  lwzwgjz  lwys  gdlb  lwtjblj
    // lwywlj  rxny  hxwrq  yjxkm  yjxkmc  ejxkm  ejxkmc  zzzc  xxlxr  bz

    $("#basic_info_form").validate({
        rules:{
            //  ssdm   ssmc  xxdm   xxmc  zzxh  xh  cplx  gdfs  zzxm  xb
            ssdm:{ //省市代码
                required:true,
                digits:true,
            },
            ssmc:{ //省市名称
                required:true,
            },
            xxdm:{ //学校代码
                required:true,
                digits:true,
            },
            xxmc:{ //学校名称
                required:true,
            },
            zzxh:{ //作者学号
                required:true,
            },
            xh:{ //序号
            },
            cplx:{ //参评类型
                required:true,
            },
            gdfs:{ //攻读方式
                required:true,
            },
            zzxm:{ //作者姓名
                required:true,
            },
            xb:{ //性别
                required:true,
            },

            // csny  mz  dsxm  lwtm  lwywtm  yjfx  lwzwgjz  lwys  gdlb  lwtjblj
            csny:{ //出生年月
                required:true,
            },
            mz:{ //民族
                required:true,
            },
            dsxm:{ //导师姓名
                required:true,
            },
            lwtm:{ //论文题目
                required:true,
            },
            lwywtm:{ //论文英文题目
                required:true,
                maxlength:200,
            },
            yjfx:{ //研究方向
                required:true,
                maxlength:100,
            },
            lwzwgjz:{ ////中文关键词
                required:true,
            },
            lwys:{ //论文页数
                required:true,
                digits:true,
                range:[1,2000],
            },
            gdlb:{//攻读类别
                required:true,
            },
            lwtjblj:{// 论文推荐表路径

            },

            // lwywlj  rxny  hxwrq  yjxkm  yjxkmc  ejxkm  ejxkmc  zzzc  xxlxr  bz
            lwywlj:{ //论文原文路径

            },
            rxny:{ //入学年月
                required:true,
            },
            hxwrq:{ //获学位日期
                required:true,
            },
            yjxkm:{//一级学科码
                required:true,
            },
            yjxkmc:{//一级学科名称
                required:true,
            },
            ejxkm:{//二级学科码
                required:true,
            },
            ejxkmc:{ //二级学科名称
                required:true,
            },
            zzzc:{ //作者职称
                required:true,
                maxlength:10,
            },
            xxlxr:{ //学校联系人
                required:true,
            },
            bz:{//备注
                required:true,
                maxlength:100,
            },

        },
        messages:{
            //  ssdm   ssmc  xxdm   xxmc  zzxh  xh  cplx  gdfs  zzxm  xb
            ssdm:{ //省市代码
                required:"省市代码不能为空",
                digits:"省市代码为数字",
            },
            ssmc:{ //省市名称
                required:"省市名称不能为空",
            },
            xxdm:{ //学校代码
                required:"学校代码不能为空",
                digits:"学校代码为数字",
            },
            xxmc:{ //学校名称
                required:"学校名称不能为空",
            },
            zzxh:{ //作者学号
                required:"作者学号不能为空",
            },
            xh:{ //序号
            },
            cplx:{ //参评类型
                required:"参评类型不能为空",
            },
            gdfs:{ //攻读方式
                required:"攻读方式不能为空",
            },
            zzxm:{ //作者姓名
                required:"作者姓名不能为空",
            },
            xb:{ //性别
                required:"性别不能为空",
            },

            // csny  mz  dsxm  lwtm  lwywtm  yjfx  lwzwgjz  lwys  gdlb  lwtjblj
            csny:{ //出生年月
                required:"系统找不到您的出生年月，请联系学校学位办",
            },
            mz:{ //民族
                required:"民族不能为空",
            },
            dsxm:{ //导师姓名
                required:"系统找不到您的导师姓名，请联系学校学位办",
            },
            lwtm:{ //论文题目
                required:"系统找不到您的论文题目，请联系学校学位办",
            },
            lwywtm:{ //论文英文题目
                required:"论文英文题目不能为空",
                maxlength:"论文英文题目最长不超过200",
            },
            yjfx:{ //研究方向
                required:"研究方向不能为空",
                maxlength:"研究方向最长不超过郭100",
            },
            lwzwgjz:{ ////中文关键词
                required:"中文关键词不能为空",
            },
            lwys:{ //论文页数
                required:"论文页数不能为空",
                digits:"论文页数为数字",
                range:"论文页数范围在1-2000之间"
            },
            gdlb:{//攻读类别
                required:"攻读类别不能为空",
            },
            lwtjblj:{// 论文推荐表路径

            },

            // lwywlj  rxny  hxwrq  yjxkm  yjxkmc  ejxkm  ejxkmc  zzzc  xxlxr  bz
            lwywlj:{ //论文原文路径

            },
            rxny:{ //入学年月
                required:"入学年月不能为空",
            },
            hxwrq:{ //获学位日期
                required:"系统找不到您的获学位日期，请联系学校学位办",
            },
            yjxkm:{//一级学科码
                required:"系统找不到您的一级学科码，请联系学校学位办",
            },
            yjxkmc:{//一级学科名称
                required:"系统找不到您的一级学科名称，请联系学校学位办",
            },
            ejxkm:{//二级学科码
                required:"系统找不到您的二级学科码，请联系学校学位办",
            },
            ejxkmc:{ //二级学科名称
                required:"系统找不到您的二级学科名称，请联系学校学位办",
            },
            zzzc:{ //作者职称
                required:"作者职称不能为空",
                maxlength:"作者职称最长不能超过10",
            },
            xxlxr:{ //学校联系人
                required:"学校联系人不能为空",
            },
            bz:{//备注
                required:"备注不能为空",
                maxlength:"备注最长不超过100",
            },
        },//messages
        errorPlacement : function(error, element){
            $("#m_table1_warn").html(error);
        },

    })//validate


}

function inputDisableToAble(){
    //前端已赋值:  ssdm  ssmc  xxdm  xxmc  zzxh  xxlxr
    //csny  dsxm  lwtm  lwtjblj  lwywlj  rxny
    //hdxwrq  yjxkdm  yjxkmc  ejxkdm  ejxkmc
    $('input[disabled="disabled"]').removeAttr('disabled');
    $('select[disabled="disabled"]').removeAttr('disabled');
}


function getEjxkdmFromPage(){
    return $("#ejxkdm").val();
}

function checkIfCanUploadFile(){
    if(getEjxkdmFromPage()==""||$("#zzxh").val()=="") return false;
    return true;
}