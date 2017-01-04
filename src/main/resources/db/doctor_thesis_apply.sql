use etas;
-- 博士学位论文推荐表
DROP TABLE IF EXISTS doctor_thesis_apply;

create table doctor_thesis_apply(
id int not null primary key auto_increment,

zzxh varchar(30) NOT NULL COMMENT '用户学号',

lwtm varchar(256) NOT NULL DEFAULT '' COMMENT '论文题目',
lwywtm varchar(512) NOT NULL DEFAULT '' COMMENT '论文英文题目',
zzxm varchar(30) NOT NULL DEFAULT '' COMMENT '作者姓名',
dbrq varchar(512) NOT NULL DEFAULT '' COMMENT '论文答辩日期',
hdxwrq varchar(512) NOT NULL DEFAULT '' COMMENT '获得学位日期',
lwsjdyjfx varchar(1024) NOT NULL DEFAULT '' COMMENT '论文涉及的研究方向',
yjxkdm varchar(20) NOT NULL DEFAULT '' COMMENT '一级学科代码',
yjxkmc varchar(128) NOT NULL DEFAULT '' COMMENT '一级学科名称',
ejxkdm varchar(20) NOT NULL DEFAULT '' COMMENT '二级学科代码',
ejxkmc varchar(128) NOT NULL DEFAULT '' COMMENT '二级学科名称',
zdjsxm varchar(64) NOT NULL DEFAULT '' COMMENT '指导教师姓名',
zdjsyjfx varchar(64) NOT NULL DEFAULT '' COMMENT '指导教师研究方向',

fbxslw text COMMENT '发表学术论文',
cbzz text COMMENT '出版专著',
hjxm text COMMENT '获奖项目',
lwdzycxd text COMMENT '论文的主要创新点',
dwtjyy text COMMENT '单位推荐意见',
tbrq varchar(20) NOT NULL DEFAULT '' COMMENT '填表日期',

dwdm varchar(20) NOT NULL DEFAULT '' COMMENT '单位代码',
dwmc varchar(50) NOT NULL DEFAULT '' COMMENT '单位名称',
student_type varchar(20) NOT NULL DEFAULT '' COMMENT '学生类型'

)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博士学位论文推荐表';
