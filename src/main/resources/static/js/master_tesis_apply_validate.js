/**
 * Created by Administrator on 2016/12/6.
 */
$(document).ready(function () {
    m_form1_validate();

    $("#m_table1_submit_btn").click(function(){
        var status = $("#master_table1_form").valid();

    })


})//document.ready

function m_form1_validate(){

    $("#master_table1_form").validate({
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