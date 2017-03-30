use etas;
-- 华中科技大学院系表
DROP TABLE IF EXISTS hust_department;
CREATE TABLE hust_department (
  id varchar(255) NOT NULL COMMENT '院系代码',
  department varchar(255) NOT NULL COMMENT '院系名称',
  remarks varchar(255) DEFAULT '' COMMENT '保留字段'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='华中科技大学院系表';