/**
 * Created by jason on 2017/1/13.
 */

var submitInfoUrl = "";

$(document).ready(function () {
    d_form1_validate();
    d_form2_validate();
    d_form1_submit();
    d_form2_submit();
});


function d_form1_submit(){
    // d_form1  d_form1_submit_btn  d_form1_warn
    // lwtm  lwywtm  zzxm  dbrq  hdxwrq  lwsjdyjfx
    // yjxkdm  yjxkmc  ejxkdm  ejxkmc  zdjsxm  zdjsyjfx
    $("#d_form1_submit_btn").click(function () {
        //1.验证表单是否合法
        var status = checkForm1IfValid();
        if(status == false)  return;
        // 2.开始提交
        $.ajax({
            type: "POST",
            url: "/home/student/apply/doctor/save",
            contentType: "application/json",
            data: JSON.stringify({
                // lwtm  lwywtm  zzxm  dbrq  hdxwrq  lwsjdyjfx

                "lwtm":$("#lwtm").val(),
                "lwywtm":$("#lwywtm").val(),
                "zzxm":$("#zzxm").val(),
                "dbrq":$("#dbrq").val(),
                "hdxwrq":$("#hdxwrq").val(),
                "lwsjdyjfx":$("#lwsjdyjfx").val(),

                // yjxkdm  yjxkmc  ejxkdm  ejxkmc  zdjsxm  zdjsyjfx
                "yjxkdm":$("#yjxkdm").val(),
                "yjxkmc":$("#yjxkmc").val(),
                "ejxkdm":$("#ejxkdm").val(),
                "ejxkmc":$("#ejxkmc").val(),
                "zdjsxm":$("#zdjsxm").val(),
                "zdjsyjfx":$("#zdjsyjfx").val(),

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

        }); //ajax
    });
};

function d_form2_submit(){
    //  d_form2  d_form2_submit_btn  d_form2_warn
    // fbxslw  cbzz  hjxm  lwdzycxd  dwtjyy  tbrq

    $("#d_form2_submit_btn").click(function () {
        //1.验证表单是否合法
        var status = checkForm2IfValid();
        if(status == false)  return;
        //2.提交表单2
        $.ajax({
            type: "POST",
            url: "/home/student/apply/doctor/save",
            contentType: "application/json",
            data: JSON.stringify({
                // fbxslw  cbzz  hjxm  lwdzycxd  dwtjyy  tbrq

                "fbxslw":$("#fbxslw").val(),
                "cbzz":$("#cbzz").val(),
                "hjxm":$("#hjxm").val(),
                "lwdzycxd":$("#lwdzycxd").val(),
                "dwtjyy":$("#dwtjyy").val(),
                "tbrq":$("#tbrq").val(),

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

        }); //ajax
    });
};


//验证表单
function d_form1_validate(){
    // d_form1  d_form1_submit_btn  d_form1_warn
    // lwtm  lwywtm  zzxm  dbrq  hdxwrq  lwsjdyjfx
    // yjxkdm  yjxkmc  ejxkdm  ejxkmc  zdjsxm  zdjsyjfx
    $("#d_form1").validate({
        rules:{
            // lwtm  lwywtm  zzxm  dbrq  hdxwrq  lwsjdyjfx
            lwtm:{ //论文题目
                required:true,
                maxlength:50,
            },
            lwywtm:{ //论文英文题目
                required:true,
                maxlength:200,
            },
            zzxm:{  //作者姓名
                required:true,
                maxlength:20,
            },
            dbrq:{ //答辩日期
                required:true,
                maxlength:8,
            },
            hdxwrq:{ //获学位日期
                required:true,
                maxlength:8,
            },
            lwsjdyjfx:{ //论文涉及的研究方向
                required:true,
                maxlength:20,
            },

            // yjxkdm  yjxkmc  ejxkdm  ejxkmc  zdjsxm  zdjsyjfx
            yjxkdm:{ //一级学科代码
                required:true,
            },
            yjxkmc:{ //一级学科名称
                required:true,
            },
            ejxkdm:{ //二级学科代码
                required:true,
            },
            ejxkmc:{ //二级学科名称
                required:true,
            },
            zdjsxm:{ //指导教师姓名
                required:true,
                maxlength:10,
            },
            zdjsyjfx:{ //指导教师研究方向
                required:true,
                maxlength:20,
            },


        }, //rules
        messages:{
            // lwtm  lwywtm  zzxm  dbrq  hdxwrq  lwsjdyjfx
            lwtm:{ //论文题目
                required:"系统找不到您的论文题目，请联系学校学位办",
                maxlength:"论文题目长度不超过50",
            },
            lwywtm:{ //论文英文题目
                required:"论文英文题目不能为空",
                maxlength:"论文英文题目长度不超过200",
            },
            zzxm:{  //作者姓名
                required:"作者姓名不能为空",
                maxlength:"作者姓名长度不超过20",
            },
            dbrq:{ //答辩日期
                required:"系统找不到您的答辩日期，请联系学校学位办",
                maxlength:"答辩日期长度不超过8",
            },
            hdxwrq:{ //获学位日期
                required:"系统找不到您的获学位日期，请联系学校学位办",
                maxlength:"获学位日期长度不超过8",
            },
            lwsjdyjfx:{ //论文涉及的研究方向
                required:"论文涉及的研究方向不能为空",
                maxlength:"论文涉及的研究方向长度不超过20",
            },

            // yjxkdm  yjxkmc  ejxkdm  ejxkmc  zdjsxm  zdjsyjfx
            yjxkdm:{ //一级学科代码
                required:"系统找不到您的一级学科代码，请联系学校学位办",
            },
            yjxkmc:{ //一级学科名称
                required:"系统找不到您的一级学科名称，请联系学校学位办",
            },
            ejxkdm:{ //二级学科代码
                required:"系统找不到您的二级学科代码，请联系学校学位办",
            },
            ejxkmc:{ //二级学科名称
                required:"系统找不到您的二级学科名称，请联系学校学位办",
            },
            zdjsxm:{ //指导教师姓名
                required:"系统找不到您的指导教师姓名，请联系学校学位办",
                maxlength:"指导教师姓名长度不超过10",
            },
            zdjsyjfx:{ //指导教师研究方向
                required:"指导教师研究方向不能为空",
                maxlength:"指导教师研究方向长度不超过20",
            },
        }, //messages
        errorPlacement : function(error, element){
            $("#d_form1_warn").html(error);
        }, //errorPlacement

    }); //validate
};

function d_form2_validate(){
    //  d_form2  d_form2_submit_btn  d_form2_warn
    // fbxslw  cbzz  hjxm  lwdzycxd  dwtjyy  tbrq
    $("#d_form2").validate({
        rules:{
            fbxslw:{ //发表学术论文
                required:true,
                maxlength:700,
            },
            cbzz:{  //出版专著
                required:true,
                maxlength:400,
            },
            hjxm:{  //获奖项目
                required:true,
                maxlength:600,
            },
            lwdzycxd:{  //论文的主要创新点
                required:true,
                maxlength:800,
            },
            tbrq:{ //填表日期
                required:true,
                maxlength:8,
                minlength:8,
            },
        }, //rules
        messages:{
            fbxslw:{ //发表学术论文
                required:"发表学术论文不能为空",
                maxlength:"发表学术论文长度不超过700",
            },
            cbzz:{  //出版专著
                required:"出版专著不能为空",
                maxlength:"出版专著长度不超过400",
            },
            hjxm:{  //获奖项目
                required:"获奖项目不能为空",
                maxlength:"获奖项目长度不超过600",
            },
            lwdzycxd:{  //论文的主要创新点
                required:"论文的主要创新点不能为空",
                maxlength:"论文的主要创新点长度不超过800",
            },
            tbrq:{ //填表日期
                required:"填表日期不能为空",
                maxlength:"填表日期长度为8",
                minlength:"填表日期长度为8",
            },
        }, //messages
        errorPlacement : function(error, element){
            $("#d_form2_warn").html(error);
        }, //errorPlacement
    })
};

//验证表单1是否合法
function  checkForm1IfValid() {
    // lwtm  lwywtm  zzxm  dbrq  hdxwrq  lwsjdyjfx
    // yjxkdm  yjxkmc  ejxkdm  ejxkmc  zdjsxm  zdjsyjfx
    ///////////////////////////////////////////////
    // lwtm  lwywtm  zzxm  dbrq  hdxwrq  lwsjdyjfx
    if($("#lwtm").valid() == false) return false;
    if($("#lwywtm").valid() == false) return false;
    if($("#zzxm").valid() == false) return false;
    if($("#dbrq").valid() == false) return false;
    if($("#hdxwrq").valid() == false) return false;
    if($("#lwsjdyjfx").valid() == false) return false;
    // yjxkdm  yjxkmc  ejxkdm  ejxkmc  zdjsxm  zdjsyjfx
    if($("#yjxkdm").valid() == false) return false;
    if($("#yjxkmc").valid() == false) return false;
    if($("#ejxkdm").valid() == false) return false;
    if($("#ejxkmc").valid() == false) return false;
    if($("#zdjsxm").valid() == false) return false;
    if($("#zdjsyjfx").valid() == false) return false;
    return true;

}
//验证表单2是否合法
function  checkForm2IfValid() {
    // fbxslw  cbzz  hjxm  lwdzycxd  dwtjyy  tbrq
    if($("#fbxslw").valid() == false) return false;
    if($("#cbzz").valid() == false) return false;
    if($("#hjxm").valid() == false) return false;
    if($("#lwdzycxd").valid() == false) return false;
    if($("#tbrq").valid() == false) return false;
    return true;
}
