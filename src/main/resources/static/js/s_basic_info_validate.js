/**
 * Created by Administrator on 2016/12/6.
 */


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
                required:"出生年月不能为空",
            },
            mz:{ //民族
                required:"民族不能为空",
            },
            dsxm:{ //导师姓名
                required:"导师姓名不能为空",
            },
            lwtm:{ //论文题目
                required:"论文题目不能为空",
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
                required:"获学位日期不能为空",
            },
            yjxkm:{//一级学科码
                required:"一级学科码不能为空",
            },
            yjxkmc:{//一级学科名称
                required:"一级学科名称不能为空",
            },
            ejxkm:{//二级学科码
                required:"二级学科码不能为空",
            },
            ejxkmc:{ //二级学科名称
                required:"二级学科名称不能为空",
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

