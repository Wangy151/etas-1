use etas;
-- 华中科技大学院系表
-- ----------------------------
-- Table structure for `hust_department`
-- ----------------------------
DROP TABLE IF EXISTS `hust_department`;
CREATE TABLE `hust_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_id` varchar(255) DEFAULT '' COMMENT '院系代码',
  `department` varchar(255) NOT NULL COMMENT '院系名称',
  `remarks` varchar(255) DEFAULT '' COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='华中科技大学院系表';

-- ----------------------------
-- Records of hust_department
-- ----------------------------
INSERT INTO `hust_department` VALUES ('1', '', '机械科学与工程学院', '');
INSERT INTO `hust_department` VALUES ('2', '', '计算机科学与技术学院', '');
INSERT INTO `hust_department` VALUES ('3', '', '生命科学与技术学院', '');
INSERT INTO `hust_department` VALUES ('4', '', '电气与电子工程学院', '');
INSERT INTO `hust_department` VALUES ('5', '', '材料科学与工程学院', '');
INSERT INTO `hust_department` VALUES ('6', '', '船舶与海洋工程学院', '');
INSERT INTO `hust_department` VALUES ('7', '', '能源与动力工程学院', '');
INSERT INTO `hust_department` VALUES ('8', '', '自动化学院', '');
INSERT INTO `hust_department` VALUES ('9', '', '光学与电子信息学院', '');
INSERT INTO `hust_department` VALUES ('10', '', '水电与数字化工程学院', '');
INSERT INTO `hust_department` VALUES ('11', '', '软件学院', '');
INSERT INTO `hust_department` VALUES ('12', '', '环境科学与工程学院', '');
INSERT INTO `hust_department` VALUES ('13', '', '电子信息与通信学院', '');
INSERT INTO `hust_department` VALUES ('14', '', '建筑与城市规划学院', '');
INSERT INTO `hust_department` VALUES ('15', '', '土木工程与力学学院', '');
INSERT INTO `hust_department` VALUES ('16', '', '化学与化工学院', '');
INSERT INTO `hust_department` VALUES ('17', '', '数学与统计学院', '');
INSERT INTO `hust_department` VALUES ('18', '', '物理学院', '');
INSERT INTO `hust_department` VALUES ('19', '', '公共管理学院', '');
INSERT INTO `hust_department` VALUES ('20', '', '经济学院', '');
INSERT INTO `hust_department` VALUES ('21', '', '管理学院', '');
INSERT INTO `hust_department` VALUES ('22', '', '人文学院', '');
INSERT INTO `hust_department` VALUES ('23', '', '新闻与信息传播学院', '');
INSERT INTO `hust_department` VALUES ('24', '', '马克思主义学院', '');
INSERT INTO `hust_department` VALUES ('25', '', '社会学院', '');
INSERT INTO `hust_department` VALUES ('26', '', '法学院', '');
INSERT INTO `hust_department` VALUES ('27', '', '外国语学院', '');
INSERT INTO `hust_department` VALUES ('28', '', '基础医学院（含法医学系）', '');
INSERT INTO `hust_department` VALUES ('29', '', '药学院', '');
INSERT INTO `hust_department` VALUES ('30', '', '医药卫生管理学院', '');
INSERT INTO `hust_department` VALUES ('31', '', '公共卫生学院', '');
INSERT INTO `hust_department` VALUES ('32', '', '附属协和医院', '');
INSERT INTO `hust_department` VALUES ('33', '', '附属同济医院（含附属梨园医院、计划生育研究所、护理学系）', '');
INSERT INTO `hust_department` VALUES ('34', '', '教育科学研究院', '');
INSERT INTO `hust_department` VALUES ('35', '', '中欧清洁与可再生能源学院', '');
INSERT INTO `hust_department` VALUES ('36', '', '国学研究院', '');
INSERT INTO `hust_department` VALUES ('37', '', '体育部', '');
INSERT INTO `hust_department` VALUES ('38', '', '武汉光电国家实验室', '');
INSERT INTO `hust_department` VALUES ('39', '', '工程科学学院', '');
INSERT INTO `hust_department` VALUES ('40', '', '武汉国际微电子学院', '');