create database etas;

use etas;

DROP TABLE IF EXISTS user;

create table user(
id int not null primary key auto_increment,
user_id varchar(50) not null unique,
password varchar(256) not null,
department varchar(128) not null,
real_name varchar(50) not null,
phone_number varchar(50) not null,
email varchar(50) not null unique,
role varchar(20) not null,
active tinyint(1) not null,
update_time datetime,
login_time datetime,
remark varchar(512)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

INSERT INTO user (password, department, real_name, phone_number, email, user_id, role, active)
VALUES ('123456', '软件学院', '肖雷', '13277930065', '490313386@qq.com',
'M201476135', '管理员', 1);