use etas;

-- 论文基本信息表
DROP TABLE IF EXISTS thesis_basic_info;

create table thesis_basic_info(
id int not null primary key auto_increment,
ssdm varchar(10) NOT NULL DEFAULT '' COMMENT '省市代码',
ssmc varchar(50) NOT NULL DEFAULT '' COMMENT '省市名称',
xxdm varchar(20) NOT NULL DEFAULT '' COMMENT '学校代码',
xxmc varchar(50) NOT NULL DEFAULT '' COMMENT '学校名称',
cplx varchar(128) DEFAULT '' COMMENT '参评类型',
xh varchar(50) DEFAULT '' COMMENT '序号',
zzxh varchar(50) DEFAULT '' COMMENT '作者学号',
zzxm varchar(30) DEFAULT '' COMMENT '作者姓名',
xb varchar(10) DEFAULT '' COMMENT '性别',
csny varchar(20) DEFAULT '' COMMENT '出生年月',
mz varchar(30) DEFAULT '' COMMENT '民族',
dsxm varchar(30) DEFAULT '' COMMENT '导师姓名',
lwtm varchar(256) DEFAULT '' COMMENT '论文题目',
lwywtm varchar(512) DEFAULT '' COMMENT '论文英文题目',
yjfx varchar(1024) DEFAULT '' COMMENT '研究方向',
lwzwgjz varchar(256) DEFAULT '' COMMENT '中文关键字',
lwys int(3) DEFAULT 0 COMMENT '论文页数',
lwtjblj varchar(1024) DEFAULT '' COMMENT 'Word论文推荐表路径',
lwywlj varchar(1024) DEFAULT '' COMMENT 'PDF论文原文路径',
rxny varchar(20) DEFAULT '' COMMENT '入学年月',
hdxwrq varchar(20) DEFAULT '' COMMENT '获学位日期',
yjxkdm varchar(20) DEFAULT '' COMMENT '一级学科代码',
yjxkmc varchar(128) DEFAULT '' COMMENT '一级学科名称',
ejxkdm varchar(20) DEFAULT '' COMMENT '二级学科代码',
ejxkmc varchar(128) DEFAULT '' COMMENT '二级学科名称',
gdlb varchar(50) DEFAULT '' COMMENT '攻读类别',
gdxwfs varchar(50) DEFAULT '' COMMENT '攻读方式',
zzzc varchar(50) DEFAULT '' COMMENT '作者职称',
xxlxr varchar(50) DEFAULT '' COMMENT '学校联系人',
bz varchar(256) DEFAULT '' COMMENT '备注',

applyYear varchar(10) DEFAULT '' COMMENT '申请年份',
studentType varchar(10) DEFAULT '' COMMENT '学生类型:硕士 博士',
applyStatus varchar(20) DEFAULT '' COMMENT '申请状态'

)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论文基本信息表';
