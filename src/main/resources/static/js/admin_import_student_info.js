/**
 * Created by jason on 2017/2/26.
 */

var downloadFileModelUrl = "";
var uploadStudentFileUrl = "/home/admin/import/upload"
$(document).ready(function () {
    downloadFileModel();
    uploadStudentInfoFile();
    initUploadMsgDiv();
});

function downloadFileModel(){
    //#downloadFile_btn
    $("#downloadFile_btn").click(function () {
        //下载文件模板
        //1.ajax组装form表单  下载文件
        var form=$("<form>");//定义一个form表单
        form.attr("style","display:none");
        form.attr("enctype","multipart/form-data")
        form.attr("target","_self");
        form.attr("method","post");
        form.attr("action",uploadStudentFileUrl);
        $(".wrap_body").append(form);//将表单放置在web中

        form.submit();//表单提交
    })
};

function uploadStudentInfoFile(){
    //#uploadFile_btn   #file_status_warn   #student_info_file
    $("#uploadFile_btn").click(function () {
        hideUploadMsgDiv();
        var fileName = $("#student_info_file").val();
        //1. 判断未选择文件
        if(fileName==""){
            model_tip_show('model_tip','model_tip_content','未选择文件');
            return;
        }
        //2. 判断文件的后缀名是否为xls
        var file_extent = fileName.substring(fileName.lastIndexOf(".")+1);
        if(file_extent!="xls"){ //文件格式错误
            model_tip_show('model_tip','model_tip_content','文件后缀名必须为xls格式');
            return;
        }
        //3. 文件后缀名正确，弹出浮层
        $("#model_file_status").modal('show');
        //4. 提交文件,上传文件
        uploadStudentInfoFile1();
    })
};

function uploadStudentInfoFile1(){
    $("#form1").ajaxSubmit({
        url: uploadStudentFileUrl,
        type: "post",
        success: function (data) { //服务器回调函数
            //1.关闭文件提示浮层
            $("#model_file_status").modal('hide');
            //{code:'',msg:'',errorRowNum:'',totalNum:''}
            // 200:成功 --> totalNum     300:格式有误 --> errorRowNum
            // 301:学号重复  --> errorRowNum     500:失败
            var status = data.code;
            var errorMsg;
            var succMsg;
            if(status == "200"){//文件上传成功
                succMsg = "已经成功上传"+data.totalNum+"条记录到系统中";
                $("#total_number").text(data.totalNum);
                showUploadMsgDiv();
                model_tip_show('model_tip','model_tip_content',succMsg);
            }
            else if(status == "300"){//文件字段名不符合要求
                errorMsg = "第"+data.errorRowNum+"行有字段为空或格式不符合要求";
                model_tip_show('model_tip','model_tip_content',errorMsg);
            }
            else if(status == "301"){//学号重复
                errorMsg = "第"+data.errorRowNum+"行的学号重复";
                model_tip_show('model_tip','model_tip_content',errorMsg);
            }
            else // status =='500'
                model_tip_show('model_tip','model_tip_content','上传失败,请检查所上传文件,稍后再试');
        } //success
    });
}

function initUploadMsgDiv(){
    $("#file_status_warn").hide();
}

function showUploadMsgDiv(){
    $("#file_status_warn").show();
}

function hideUploadMsgDiv(){
    $("#file_status_warn").hide();
}
