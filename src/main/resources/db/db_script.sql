create database etas;
create table user(
id int not null primary key auto_increment,
username varchar(128) not null unique,
password varchar(256) not null,
department varchar(50) not null,
real_name varchar(50) not null,
phone_number varchar(50) not null,
email varchar(50) not null,
tech_or_stud_num varchar(50) not null unique,
role varchar(20) not null,
active tinyint(2) not null,
update_time datetime,
login_time datetime,
remark varchar(256));