/**
 * Created by jason on 2017/1/13.
 */

var infoSubmitUrl = "";

$(document).ready(function () {
    m_apply_form1_validate();
    m_apply_form2_validate();
    m_apply_form3_validate();
    m_apply_form4_validate();

    m_apply_form1_submit();
    m_apply_form2_submit();
    m_apply_form3_submit();
    m_apply_form4_submit();
});


function m_apply_form1_submit(){
    // m_form1  m_form1_submit_btn  m_form1_warn
    // zzxh  zzxm  xb  csny  mz  lwtm  lwywtm  rxny
    // dbrq  hdxwrq  yjxkdm  yjxkmc  ejxkdm  ejxkmc  lwsjdyjfx
    $("#m_form1_submit_btn").click(function () {
        var status = checkForm1IfValid();
        if(status == false) return;
        /////       URL                      /////
        $.ajax({
            type: "POST",
            url: "/home/student/apply/master/save",
            contentType: "application/json",
            data: JSON.stringify({
                //  zzxh  zzxm  xb  csny  mz  lwtm  lwywtm  rxny
                "zzxh":$("#zzxh").val(),
                "zzxm":$("#zzxm").val(),
                "xb":$("#xb").val(),
                "csny":$("#csny").val(),
                "mz":$("#mz").val(),
                "lwtm":$("#lwtm").val(),
                "lwywtm":$("#lwywtm").val(),
                "rxny":$("#rxny").val(),

                // dbrq  hdxwrq  yjxkdm  yjxkmc  ejxkdm  ejxkmc  lwsjdyjfx
                "dbrq":$("#dbrq").val(),
                "hdxwrq":$("#hdxwrq").val(),
                "yjxkdm":$("#yjxkdm").val(),
                "yjxkmc":$("#yjxkmc").val(),
                "ejxkdm":$("#ejxkdm").val(),
                "ejxkmc":$("#ejxkmc").val(),
                "lwsjdyjfx":$("#lwsjdyjfx").val(),

                "part":"part1",

            }),

            beforeSend: function(XMLHttpRequest){
            },

            success: function(data){
                var status = data.code;
                var msg = data.msg;
                if(status == "200")  //信息保存成功
                    model_tip_show('model_tip','model_tip_content','保存成功');
                else if(status == "500")  //服务器繁忙
                    model_tip_show('model_tip','model_tip_content','系统繁忙请稍后再试!');
                else
                    var empty = "";
            },

            error: function(XMLHttpRequest, textStatus) {
            },

            complete: function(XMLHttpRequest, textStatus){
            }

        });

    })
}

function m_apply_form2_submit(){
    //  m_form2   m_form2_submit_btn  m_form2_warn
    // dyzz  dezz  sci  ei  ssci  istp  zls  cgjx
    $("#m_form2_submit_btn").click(function () {
        var status = checkForm2IfValid();
        if(status == false)  return;
        /////          URL           ///////////
        $.ajax({
            type: "POST",
            url: "/home/student/apply/master/save",
            contentType: "application/json",
            data: JSON.stringify({
                //  dyzz  dezz  sci  ei  ssci  istp  zls  cgjx
                "zzxh":getUserIdFromPage(),
                "dyzz":$("#dyzz").val(),
                "dezz":$("#dezz").val(),
                "sci":$("#sci").val(),
                "ei":$("#ei").val(),
                "ssci":$("#ssci").val(),
                "istp":$("#istp").val(),
                "zls":$("#zls").val(),
                "cgjx":$("#cgjx").val(),

                "part":"part2",


            }),

            beforeSend: function(XMLHttpRequest){
            },

            success: function(data){
                var status = data.code;
                var msg = data.msg;
                if(status == "200")  //信息保存成功
                    model_tip_show('model_tip','model_tip_content','保存成功');
                else if(status == "500")  //服务器繁忙
                    model_tip_show('model_tip','model_tip_content','系统繁忙请稍后再试!');
                else
                    var empty = "";
            },

            error: function(XMLHttpRequest, textStatus) {
            },

            complete: function(XMLHttpRequest, textStatus){
            }

        });
    })
}

function m_apply_form3_submit(){
    // m_form3  m_form3_submit_btn  m_form3_warn
    // gdxwfs  bkjdxx  gdssxwdw  zzdw  zzdz
    // zzyb  zzdh  zc  zw  zdjsxm  zdjsyjfx
    $("#m_form3_submit_btn").click(function(){
        var status = checkForm3IfValid();
        if(status == false)   return;
        /////          URL           ///////////
        $.ajax({
            type: "POST",
            url: "/home/student/apply/master/save",
            contentType: "application/json",
            data: JSON.stringify({
                // gdxwfs  bkjdxx  gdssxwdw  zzdw  zzdz
                "zzxh":getUserIdFromPage(),
                "gdxwfs":$("#gdxwfs").val(),
                "bkjdxx":$("#bkjdxx").val(),
                "gdssxwdw":$("#gdssxwdw").val(),
                "zzdw":$("#zzdw").val(),
                "zzdz":$("#zzdz").val(),

                // zzyb  zzdh  zc  zw  zdjsxm  zdjsyjfx
                "zzyb":$("#zzyb").val(),
                "zzdh":$("#zzdh").val(),
                "zc":$("#zc").val(),
                "zw":$("#zw").val(),
                "zdjsxm":$("#zdjsxm").val(),
                "zdjsyjfx":$("#zdjsyjfx").val(),

                "part":"part3",

            }),

            beforeSend: function(XMLHttpRequest){
            },

            success: function(data){
                var status = data.code;
                var msg = data.msg;
                if(status == "200")  //信息保存成功
                    model_tip_show('model_tip','model_tip_content','保存成功');
                else if(status == "500")  //服务器繁忙
                    model_tip_show('model_tip','model_tip_content','系统繁忙请稍后再试!');
                else
                    var empty = "";
            },

            error: function(XMLHttpRequest, textStatus) {
            },

            complete: function(XMLHttpRequest, textStatus){
            }

        });
    })
}

function m_apply_form4_submit(){
    //  m_form4  m_form4_submit_btn  m_form4_warn
    //  fbxslw  cbzz  hjxm  lwdzycxd  dwtjyy  tbrq
    $("#m_form4_submit_btn").click(function(){
        var status = checkForm4IfValid();
        if(status == false)  return;
        $.ajax({
            type: "POST",
            url: "/home/student/apply/master/save",
            contentType: "application/json",
            data: JSON.stringify({
                //  fbxslw  cbzz  hjxm  lwdzycxd  dwtjyy  tbrq
                "zzxh":getUserIdFromPage(),
                "fbxslw":$("#fbxslw").val(),
                "cbzz":$("#cbzz").val(),
                "hjxm":$("#hjxm").val(),
                "lwdzycxd":$("#lwdzycxd").val(),
                "dwtjyy":$("#dwtjyy").val(),
                "tbrq":$("#tbrq").val(),

                "part":"part4",

            }),

            beforeSend: function(XMLHttpRequest){
            },

            success: function(data){
                var status = data.code;
                var msg = data.msg;
                if(status == "200")  //信息保存成功
                    model_tip_show('model_tip','model_tip_content','保存成功');
                else if(status == "500")  //服务器繁忙
                    model_tip_show('model_tip','model_tip_content','系统繁忙请稍后再试!');
                else
                    var empty = "";
            },

            error: function(XMLHttpRequest, textStatus) {
            },

            complete: function(XMLHttpRequest, textStatus){
            }

        });
    }); //click
}


//验证
function m_apply_form1_validate(){
    // m_form1  m_form1_submit_btn  m_form1_warn
    // zzxh  zzxm  xb  csny  mz  lwtm  lwywtm  rxny
    // dbrq  hdxwrq  yjxkdm  yjxkmc  ejxkdm  ejxkmc  lwsjdyjfx
    $("#m_form1").validate({
        rules:{
            // zzxh  zzxm  xb  csny  mz  lwtm  lwywtm  rxny
            zzxh:{ //作者学号
                required:true,
            },
            zzxm:{ //作者姓名
                required:true,
                maxlength:20,
            },
            xb:{ //性别
                required:true,
            },
            csny:{ //出生年月
                required:true,
            },
            mz:{ //民族
                required:true,
            },
            lwtm:{ //论文题目
                required:true,
                maxlength:150,
            },
            lwywtm:{ //论文英文题目
                required:true,
                maxlength:200,
            },
            rxny:{ //入学年月
                required:true,
            },
            // dbrq  hdxwrq  yjxkdm  yjxkmc  ejxkdm  ejxkmc  lwsjdyjfx
            dbrq:{ //答辩日期
                required:true,
            },
            hdxwrq:{ //获学位日期
                required:true,
                maxlength:8,
            },
            yjxkdm:{ //一级学科码
                required:true,
            },
            yjxkmc:{ //一级学科名称
                required:true,
            },
            ejxkdm:{ //二级学科码
                required:true,
            },
            ejxkmc:{ //二级学科名称
                required:true,
            },
            lwsjdyjfx:{ //论文涉及的研究方向
                required:true,
                maxlength:100,
            },

        },
        messages:{
            // zzxh  zzxm  xb  csny  mz  lwtm  lwywtm  rxny
            zzxh:{ //作者学号
                required:"作者学号不能为空",
            },
            zzxm:{ //作者姓名
                required:"作者姓名不能为空",
                maxlength:"作者姓名最长不超过20",
            },
            xb:{ //性别
                required:"性别不能为空",
            },
            csny:{ //出生年月
                required:"系统找不到您的出生年月，请联系学校学位办",
            },
            mz:{ //民族
                required:"民族不能为空",
            },
            lwtm:{ //论文题目
                required:"系统找不到您的论文题目，请联系学校学位办",
                maxlength:"论文题目不超过150",
            },
            lwywtm:{ //论文英文题目
                required:"论文英文题目不能为空",
                maxlength:"论文英文题目长度不超过200",
            },
            rxny:{ //入学年月
                required:"入学年月不能为空",
            },
            // dbrq  hdxwrq  yjxkdm  yjxkmc  ejxkdm  ejxkmc  lwsjdyjfx
            dbrq:{ //答辩日期
                required:"系统找不到您的答辩日期，请联系学校学位办",
            },
            hdxwrq:{ //获学位日期
                required:"系统找不到您的获学位日期，请联系学校学位办",
                maxlength:"获学位日期长度不超过8",
            },
            yjxkdm:{ //一级学科码
                required:"系统找不到您的一级学科码，请联系学校学位办",
            },
            yjxkmc:{ //一级学科名称
                required:"系统找不到您的一级学科名称，请联系学校学位办",
            },
            ejxkdm:{ //二级学科码
                required:"系统找不到您的二级学科码，请联系学校学位办",
            },
            ejxkmc:{ //二级学科名称
                required:"系统找不到您的二级学科名称，请联系学校学位办",
            },
            lwsjdyjfx:{ //论文涉及的研究方向
                required:"论文涉及的研究方向不能为空",
                maxlength:"论文涉及的研究方向最长不超过100",
            },
        },//messages
        errorPlacement : function(error, element){
            $("#m_form1_warn").html(error);
        }, //errorPlacement
    }) //validate
};

function m_apply_form2_validate(){
    //  m_form2   m_form2_submit_btn  m_form2_warn
    // dyzz  dezz  sci  ei  ssci  istp  zls  cgjx
    $("#m_form2").validate({
        rules:{
            dyzz:{ //第一作者
                required:true,
                digits:true,
            },
            dezz:{ //第二作者
                required:true,
                digits:true,
            },
            sci:{ //SCI
                required:true,
                digits:true,
            },
            ei:{ //EI
                required:true,
                digits:true,
            },
            ssci:{ //SSCI
                required:true,
                digits:true,
            },
            istp:{ //ISTP
                required:true,
                digits:true,
            },
            zls:{ //ZLS
                required:true,
                digits:true,
            },
            cgjx:{ //出国进修
                required:true,
                maxlength:500,
            },
        }, //rules
        messages:{
            dyzz:{ //第一作者
                required:"第一作者不能为空",
                digits:"第一作者为数字",
            },
            dezz:{ //第二作者
                required:"第二作者不能为空",
                digits:"第二作者为数字",
            },
            sci:{ //SCI
                required:"SCI不能为空",
                digits:"SCI为数字",
            },
            ei:{ //EI
                required:"EI不能为空",
                digits:"EI位数字",
            },
            ssci:{ //SSCI
                required:"SSCI不能为空",
                digits:"SSCI为数字",
            },
            istp:{ //ISTP
                required:"ISTP不能为空",
                digits:"ISTP为数字",
            },
            zls:{ //ZLS
                required:"ZLS不能为空",
                digits:"ZLS位数字",
            },
            cgjx:{ //出国进修
                required:"出国进修不能为空",
                maxlength:"出国进修不超过500",
            },
        }, //messages
        errorPlacement : function(error, element){
            $("#m_form2_warn").html(error);
        }, //errorPlacement

    }) //validate
}

function m_apply_form3_validate(){
    // m_form3  m_form3_submit_btn  m_form3_warn
    // gdxwfs  bkjdxx  gdssxwdw  zzdw  zzdz
    // zzyb  zzdh  zc  zw  zdjsxm  zdjsyjfx
    $("#m_form3").validate({
        rules:{
            // gdxwfs  bkjdxx  gdssxwdw  zzdw  zzdz
            gdxwfs:{ //攻读硕士学位方式
                required:true,
            },
            bkjdxx:{ //本科就读学校
                required:true,
                maxlength:30,
            },
            gdssxwdw:{ //攻读硕士学位单位
                required:true,
                maxlength:20
            },
            zzdw:{ //作者单位
                required:true,
                maxlength:30,
            },
            zzdz:{ //作者地址
                required:true,
                maxlength:50,
            },

            // zzyb  zzdh  zc  zw  zdjsxm  zdjsyjfx
            zzyb:{ //作者邮编
                required:true,
                minlength:6,
                maxlength:6,
            },
            zzdh:{ //作者电话
                required:true,
                number:true,
                maxlength:11,
                minlength:11,
            },
            zc:{ //职称
                required:true,
                maxlength:10,
            },
            zw:{ //职务
                required:true,
                maxlength:10,
            },
            zdjsxm:{ //指导教师姓名
                required:true,
                maxlength:10,
            },
            zdjsyjfx:{ //指导教师研究方向
                required:true,
                maxlength:15,
            },
        }, //rules
        messages:{
            // gdxwfs  bkjdxx  gdssxwdw  zzdw  zzdz
            gdxwfs:{ //攻读硕士学位方式
                required:"攻读硕士学位方式不能为空",
            },
            bkjdxx:{ //本科就读学校
                required:"本科就读学校不能为空",
                maxlength:"本科就读学校长度不超过30",
            },
            gdssxwdw:{ //攻读硕士学位单位
                required:"攻读硕士学位单位不能为空",
                maxlength:"攻读硕士学位单位长度不超过20",
            },
            zzdw:{ //作者单位
                required:"作者单位不能为空",
                maxlength:"作者单位长度不超过30",
            },
            zzdz:{ //作者地址
                required:"作者地址不能为空",
                maxlength:"作者地址长度不超过50",
            },

            // zzyb  zzdh  zc  zw  zdjsxm  zdjsyjfx
            zzyb:{ //作者邮编
                required:"作者邮编不能为空",
                minlength:"邮编长度为6",
                maxlength:"邮编长度为6",
            },
            zzdh:{ //作者电话
                required:"作者电话不能为空",
                number:"作者电话格式不正确",
                maxlength:"作者电话长度为11",
                minlength:"作者电话长度为11",
            },
            zc:{ //职称
                required:"职称不能为空",
                maxlength:"职称长度不超过10",
            },
            zw:{ //职务
                required:"职务不能为空",
                maxlength:"职务长度不超过10",
            },
            zdjsxm:{ //指导教师姓名
                required:"指导教师姓名不能为空",
                maxlength:"指导教师姓名长度不超过10",
            },
            zdjsyjfx:{ //指导教师研究方向
                required:"指导教师研究方向不能为空",
                maxlength:"指导教师研究方向长度不超过15",
            },
        }, //messages
        errorPlacement : function(error, element){
            $("#m_form3_warn").html(error);
        }, //errorPlacement
    }); //validate
}

function m_apply_form4_validate(){
    //  m_form4  m_form4_submit_btn  m_form4_warn
    //  fbxslw  cbzz  hjxm  lwdzycxd  dwtjyy  tbrq
    $("#m_form4").validate({
        rules:{
            fbxslw:{ //发表学术论文
                required:true,
                maxlength:600,
            },
            cbzz:{ //出版专著
                required:true,
                maxlength:600,
            },
            hjxm:{ //获奖项目
                required:true,
                maxlength:500,
            },
            lwdzycxd:{ //论文主要创新点
                required:true,
                maxlength:600,
            },
            tbrq:{ //单位推荐日期
                required:true,
                minlength:8,
                maxlength:8,
            },
        }, //rules
        messages:{
            fbxslw:{ //发表学术论文
                required:"发表学术论文不能为空",
                maxlength:"发表学术论文长度不超过600",
            },
            cbzz:{ //出版专著
                required:"出版专著不能为空",
                maxlength:"出版专著长度不超过600",
            },
            hjxm:{ //获奖项目
                required:"获奖项目不能为空",
                maxlength:"获奖项目长度不超过500",
            },
            lwdzycxd:{ //论文主要创新点
                required:"论文主要创新点不能为空",
                maxlength:"论文主要创新点长度不超过600",
            },
            tbrq:{ //单位推荐日期
                required:"单位推荐日期不能为空",
                minlength:"单位推荐日期长度为8",
                maxlength:"单位推荐日期长度为8",
            },
        }, //messages
        errorPlacement : function(error, element){
            $("#m_form4_warn").html(error);
        }, //errorPlacement

    }); //validate
}

function checkForm1IfValid(){
    // zzxh  zzxm  xb  csny  mz  lwtm  lwywtm  rxny
    // dbrq  hdxwrq  yjxkdm  yjxkmc  ejxkdm  ejxkmc  lwsjdyjfx
    /////////////////////////////////////////////////////
    // zzxh  zzxm  xb  csny  mz  lwtm  lwywtm  rxny
    if($("#zzxh").valid() == false) return false;
    if($("#zzxm").valid() == false) return false;
    if($("#xb").valid() == false) return false;
    if($("#csny").valid() == false) return false;
    if($("#mz").valid() == false) return false;
    if($("#lwtm").valid() == false) return false;
    if($("#lwywtm").valid() == false) return false;
    if($("#rxny").valid() == false) return false;
    // dbrq  hdxwrq  yjxkdm  yjxkmc  ejxkdm  ejxkmc  lwsjdyjfx
    if($("#dbrq").valid() == false) return false;
    if($("#hdxwrq").valid() == false) return false;
    if($("#yjxkdm").valid() == false) return false;
    if($("#yjxkmc").valid() == false) return false;
    if($("#ejxkdm").valid() == false) return false;
    if($("#ejxkmc").valid() == false) return false;
    if($("#lwsjdyjfx").valid() == false) return false;
    return true;
}

function checkForm2IfValid(){
    // dyzz  dezz  sci  ei  ssci  istp  zls  cgjx
    if($("#dyzz").valid() == false) return false;
    if($("#dezz").valid() == false) return false;
    if($("#sci").valid() == false) return false;
    if($("#ei").valid() == false) return false;
    if($("#ssci").valid() == false) return false;
    if($("#istp").valid() == false) return false;
    if($("#zls").valid() == false) return false;
    if($("#cgjx").valid() == false) return false;
    return true;
}

function checkForm3IfValid(){
    // gdxwfs  bkjdxx  gdssxwdw  zzdw  zzdz
    // zzyb  zzdh  zc  zw  zdjsxm  zdjsyjfx
    if($("#gdxwfs").valid() == false) return false;
    if($("#bkjdxx").valid() == false) return false;
    if($("#gdssxwdw").valid() == false) return false;
    if($("#zzdw").valid() == false) return false;
    if($("#zzdz").valid() == false) return false;
    if($("#zzyb").valid() == false) return false;
    if($("#zzdh").valid() == false) return false;
    if($("#zc").valid() == false) return false;
    if($("#zw").valid() == false) return false;
    if($("#zdjsxm").valid() == false) return false;
    if($("#zdjsyjfx").valid() == false) return false;
    return true;
}

function checkForm4IfValid(){
    //  fbxslw  cbzz  hjxm  lwdzycxd  dwtjyy  tbrq
    if($("#fbxslw").valid() == false) return false;
    if($("#cbzz").valid() == false) return false;
    if($("#hjxm").valid() == false) return false;
    if($("#lwdzycxd").valid() == false) return false;
    if($("#tbrq").valid() == false) return false;
    return true;
}