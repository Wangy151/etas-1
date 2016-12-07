/**
 * Created by Administrator on 2016/12/6.
 */
$(document).ready(function () {
    m_form1_validate();
    m_form2_validate();

    $("#m_table2_submit_btn").click(function () {
        var status = $("#m_form2").valid();

    })

})//document.ready

function m_form1_validate(){

    $("#m_form1").validate({
        rules:{
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
            lwzwgjz:{ //论文中文关键字
                required:true,
                maxlength:40,
            },
            lwys:{ //论文页数
                digits:true,
                range:[10,400],
            },
            gdlb:{//攻读类别
                required:true,
            },
            lwtjblj:{// 论文推荐表路径

            },
            lwywlj:{ //论文原文路径

            },
            zzzc:{ //作者职称
                required:true,
            },
            xxlxr:{ //学校联系人

            },
            bz:{ //备注

            }
        },
        messages:{
            ssdm:{ //省市代码
                required:"省市代码不能为空",
                digits:"省市代码必须为数字",
            },
            ssmc:{ //省市名称
                required:"省市名称不能为空",
            },
            xxdm:{ //学校代码
                required:"学校代码不能为空",
                digits:"学校代码必须为数字",
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
            lwzwgjz:{ //论文中文关键字
                required:"论文中文关键字不能为空",
                maxlength:"论文中文关键字不能超过40",
            },
            lwys:{ //论文页数
                digits:"论文页数必须为数字",
                range:"论文页数在10-400之间",
            },
            gdlb:{//攻读类别
                required:"攻读类别不能为空",
            },
            lwtjblj:{// 论文推荐表路径

            },
            lwywlj:{ //论文原文路径

            },
            zzzc:{ //作者职称
                required:"作者职称不能为空",
            },
            xxlxr:{ //学校联系人

            },
            bz:{ //备注

            }
        },//messages
        errorPlacement : function(error, element){
            $("#m_table1_warn").html(error);
        },

    })//validate
}


function m_form2_validate(){

    $("#m_form2").validate({
        rules:{
            zzxm:{  //作者姓名
                required:true,
            },
            xb:{  //性别
                required:true,
            },
            zzxm:{  //作者姓名
                required:true,
            },
            csny:{  //出生年月
                required:true,
            },
            mz:{  //民族
                required:true,
            },
            lwtm:{  //论文题目
                required:true,
            },
            lwywtm:{  //论文英文题目
                required:true,
            },
            rxny:{  //入学年月
                required:true,
            },
            dbrq:{  //论文答辩日期
                required:true,
            },
            hdxwrq:{  //获得硕士学位日期
                required:true,
            },
            yjxkdm:{  //一级学科代码
                required:true,
                digits:true,
            },
            yjxkmc:{  //一级学科名称
                required:true,
            },
            ejxkdm:{  //二级学科代码
                required:true,
            },
            ejxkmc:{  //二级学科名称
                required:true,
            },
            lwsjdyjfx:{  //论文涉及的研究方向
                required:true,
            },

        },
        messages:{
            zzxm:{  //作者姓名
                required:"作者姓名不能为空",
            },
            xb:{  //性别
                required:"XX不能为空",
            },
            zzxm:{  //作者姓名
                required:"性别不能为空",
            },
            csny:{  //出生年月
                required:"出生年月不能为空",
            },
            mz:{  //民族
                required:"民族不能为空",
            },
            lwtm:{  //论文题目
                required:"论文题目不能为空",
            },
            lwywtm:{  //论文英文题目
                required:"论文英文题目不能为空",
            },
            rxny:{  //入学年月
                required:"XX不能为空",
            },
            dbrq:{  //论文答辩日期
                required:"入学年月不能为空",
            },
            hdxwrq:{  //获得硕士学位日期
                required:"获得硕士学位日期不能为空",
            },
            yjxkdm:{  //一级学科代码
                required:"一级学科代码不能为空",
                digits:"一级学科代码为数字",
            },
            yjxkmc:{  //一级学科名称
                required:"一级学科名称不能为空",
            },
            ejxkdm:{  //二级学科代码
                required:"二级学科代码不能为空",
            },
            ejxkmc:{  //二级学科名称
                required:"二级学科名称不能为空",
            },
            lwsjdyjfx:{  //论文涉及的研究方向
                required:"论文涉及的研究方向不能为空",
            },
        },
        errorPlacement : function(error, element){
            $("#m_table2_warn").html(error);
        },
    })//validate

}//function

function m_form3_validate(){

    $("#m_form3").validate({
        rules:{
            dyzz:{  //发表学术论文数(第一作者)

            },
            dezz:{  //发表学术论文数(第二作者)

            },
            sci:{  //论文被检索数(SCI)

            },
            ei:{  //论文被检索数(EI

            },
            ssci:{  //论文被检索数(SSCI

            },
            istp:{  //论文被检索数(ISTP)

            },
            zls:{  //获发明或实用新型专利数

            },
            cgjx:{  //出国进修的时间 国名 内容

            },

        },
        messages:{
            dyzz:{  //发表学术论文数(第一作者)

            },
            dezz:{  //发表学术论文数(第二作者)

            },
            sci:{  //论文被检索数(SCI)

            },
            ei:{  //论文被检索数(EI

            },
            ssci:{  //论文被检索数(SSCI

            },
            istp:{  //论文被检索数(ISTP)

            },
            zls:{  //获发明或实用新型专利数

            },
            cgjx:{  //出国进修的时间 国名 内容

            },
        },
        errorPlacement : function(error, element){
            $("#m_form3_warn").html(error);
        },
    })//validate

}//function
