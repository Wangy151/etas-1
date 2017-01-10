create database etas;

use etas;

-- 学生信息表
DROP TABLE IF EXISTS student_info_import;

create table student_info_import(
id int not null primary key auto_increment,
xh varchar(50) not null COMMENT '学号',
name varchar(50) DEFAULT '' COMMENT '姓名',
csrq varchar(50) DEFAULT '' COMMENT '出生日期',
yjxkdm varchar(20) DEFAULT '' COMMENT '一级学科代码',
yjxkmc varchar(128) DEFAULT '' COMMENT '一级学科名称',
ejxkdm varchar(20) DEFAULT '' COMMENT '二级学科代码',
ejxkmc varchar(128) DEFAULT '' COMMENT '二级学科名称',
ds varchar(50) DEFAULT '' COMMENT '导师',
lwtm varchar(1024) DEFAULT '' COMMENT '论文中文题目',
rxnf varchar(20) DEFAULT '' COMMENT '入学年份',
hxwsj varchar(50) DEFAULT '' COMMENT '获学位时间',
dbsj varchar(50) DEFAULT '' COMMENT '答辩时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生信息表';

-- 一级学科映射表
DROP TABLE IF EXISTS yjxk_map;

create table yjxk_map(
id int not null primary key auto_increment,
yjxkdm varchar(20) DEFAULT '' COMMENT '一级学科代码',
yjxkmc varchar(128) DEFAULT '' COMMENT '一级学科名称',
remark varchar(512) DEFAULT '' COMMENT '备用字段'

)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生信息表';