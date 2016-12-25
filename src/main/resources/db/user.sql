create database etas;

use etas;

-- 用户信息表
DROP TABLE IF EXISTS user;

create table user(
id int not null primary key auto_increment,
user_id varchar(50) not null unique COMMENT '学号',
password varchar(256) not null COMMENT '密码',
department varchar(128) not null COMMENT '所在院系',
real_name varchar(50) not null COMMENT '真实姓名',
phone_number varchar(50) not null COMMENT '联系电话',
email varchar(50) not null unique COMMENT '电子邮件',
role varchar(20) not null COMMENT '用户类型',
active tinyint(1) not null COMMENT '是否激活',
update_time datetime COMMENT '更新时间',
login_time datetime COMMENT '登录时间',
remark varchar(512) COMMENT '备注'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

INSERT INTO user (password, department, real_name, phone_number, email, user_id, role, active)
VALUES ('4QrcOUm6Wau+VuBX8g+IPg==', '软件学院', '肖雷', '13277930065', '490313386@qq.com',
'M201476135', '管理员', 1);