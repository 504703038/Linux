/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : datebase_experiment

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2019-09-06 15:57:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` varchar(12) NOT NULL COMMENT '课程ID',
  `course_name` varchar(20) NOT NULL COMMENT '课程名称',
  `type` varchar(5) NOT NULL COMMENT '课程类型\r\n1、必选\r\n2、限选\r\n3、任选\r\n4、通识',
  `credit` float NOT NULL COMMENT '课程学分',
  `depart_id` varchar(12) NOT NULL COMMENT '开课院系',
  `description` varchar(30) default NULL COMMENT '课程说明',
  PRIMARY KEY  (`course_id`),
  KEY `co_id` USING BTREE (`course_id`),
  KEY `co_de_id` (`depart_id`),
  CONSTRAINT `co_de_id` FOREIGN KEY (`depart_id`) REFERENCES `department` (`depart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('0001', '课程1', '必修', '2', '00001', null);
INSERT INTO `course` VALUES ('0002', '课程2', '选修', '1', '00001', null);
INSERT INTO `course` VALUES ('0003', '课程3', '通选', '4', '00002', null);

-- ----------------------------
-- Table structure for `course_section`
-- ----------------------------
DROP TABLE IF EXISTS `course_section`;
CREATE TABLE `course_section` (
  `section_id` varchar(12) NOT NULL COMMENT '开课ID',
  `course_id` varchar(12) NOT NULL COMMENT '课程ID',
  `year` int(11) NOT NULL COMMENT '开课年份',
  `semseter` varchar(2) NOT NULL COMMENT '开课学期',
  `instructor_id` varchar(12) NOT NULL COMMENT '讲师',
  `capacity` int(11) NOT NULL COMMENT '课容量',
  `begin_time` date NOT NULL COMMENT '开课时间',
  `end_time` date NOT NULL COMMENT '结课时间',
  PRIMARY KEY  (`section_id`),
  KEY `cs_id` USING BTREE (`section_id`),
  KEY `cs_co_id` (`course_id`),
  KEY `cs_in_id` (`instructor_id`),
  CONSTRAINT `cs_in_id` FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`instructor_id`),
  CONSTRAINT `cs_co_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_section
-- ----------------------------
INSERT INTO `course_section` VALUES ('0001', '0001', '2017', '春', 't0001', '50', '2019-09-04', '2019-07-03');
INSERT INTO `course_section` VALUES ('0002', '0001', '2018', '秋', 't0002', '20', '2019-09-03', '2019-09-08');
INSERT INTO `course_section` VALUES ('0003', '0002', '2017', '秋', 't0003', '10', '2019-09-04', '2019-09-09');
INSERT INTO `course_section` VALUES ('0004', '0003', '2019', '秋', 't0001', '1', '2019-09-13', '2019-09-17');

-- ----------------------------
-- Table structure for `course_selection`
-- ----------------------------
DROP TABLE IF EXISTS `course_selection`;
CREATE TABLE `course_selection` (
  `student_id` varchar(12) NOT NULL COMMENT '学生学号',
  `section_id` varchar(12) NOT NULL COMMENT '开课ID',
  `usual_grade` float default NULL COMMENT '平时成绩',
  `final_grade` float default NULL COMMENT '期末成绩',
  `grade` float default NULL COMMENT '最终成绩',
  `GPA` float default NULL COMMENT '绩点',
  PRIMARY KEY  (`student_id`,`section_id`),
  KEY `co_el_co_se_id` (`section_id`),
  KEY `co_el_co_st_id` USING BTREE (`student_id`),
  CONSTRAINT `co_el_co_se_id` FOREIGN KEY (`section_id`) REFERENCES `course_section` (`section_id`),
  CONSTRAINT `co_el_st_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_selection
-- ----------------------------
INSERT INTO `course_selection` VALUES ('s0001', '0001', '20', '40', '60', null);
INSERT INTO `course_selection` VALUES ('s0001', '0004', '10', '40', '50', null);
INSERT INTO `course_selection` VALUES ('s0002', '0002', null, null, null, null);
INSERT INTO `course_selection` VALUES ('s0002', '0003', null, null, null, null);

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `depart_id` varchar(12) NOT NULL COMMENT '院系ID',
  `depart_name` varchar(20) NOT NULL COMMENT '院系名称',
  `address` varchar(32) NOT NULL COMMENT '院系地址',
  PRIMARY KEY  (`depart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('00001', '软件学院', '济南');
INSERT INTO `department` VALUES ('00002', '微电子学院', '软件园');

-- ----------------------------
-- Table structure for `instructor`
-- ----------------------------
DROP TABLE IF EXISTS `instructor`;
CREATE TABLE `instructor` (
  `instructor_id` varchar(12) NOT NULL COMMENT '工号',
  `instructor_name` varchar(20) NOT NULL COMMENT '姓名',
  `depart_id` varchar(12) NOT NULL COMMENT '所属院系',
  `sex` varchar(2) NOT NULL COMMENT '性别',
  `birthday` date default NULL COMMENT '年龄',
  `id_card` varchar(20) default NULL COMMENT '身份证号码',
  `phone` varchar(15) default NULL COMMENT '联系电话',
  PRIMARY KEY  (`instructor_id`),
  KEY `in_de_id` (`depart_id`),
  CONSTRAINT `in_de_id` FOREIGN KEY (`depart_id`) REFERENCES `department` (`depart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of instructor
-- ----------------------------
INSERT INTO `instructor` VALUES ('t0001', '老师1号', '00001', '男', '2019-09-09', '111111111111111111', '55222222233');
INSERT INTO `instructor` VALUES ('t0002', '老师2号', '00001', '女', '2019-09-22', '000000000000000000', '45678912311');
INSERT INTO `instructor` VALUES ('t0003', '老师3号', '00002', '男', '2019-10-29', '0000000520000', '456789');

-- ----------------------------
-- Table structure for `major`
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `major_id` varchar(12) NOT NULL COMMENT '专业ID',
  `major_name` varchar(20) NOT NULL COMMENT '专业名称',
  `study_time` varchar(5) NOT NULL COMMENT '修学年限',
  `depart_id` varchar(12) NOT NULL COMMENT '所属院系',
  PRIMARY KEY  (`major_id`),
  KEY `mj_de_id` (`depart_id`),
  CONSTRAINT `mj_de_id` FOREIGN KEY (`depart_id`) REFERENCES `department` (`depart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('00001', '软件工程', '4', '00001');
INSERT INTO `major` VALUES ('00002', '微电子', '4', '00002');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` varchar(12) NOT NULL default '' COMMENT '学生学号',
  `student_name` varchar(20) NOT NULL COMMENT '学生姓名',
  `clas` int(11) NOT NULL COMMENT '年级',
  `major_id` varchar(12) NOT NULL COMMENT '所学专业',
  `sex` varchar(12) NOT NULL COMMENT '性别',
  `birthday` date default NULL COMMENT '年龄',
  `id_card` varchar(20) default NULL COMMENT '身份证号',
  `phone` varchar(15) default NULL COMMENT '联系电话',
  PRIMARY KEY  (`student_id`),
  KEY `st_ma_id` (`major_id`),
  CONSTRAINT `st_ma_id` FOREIGN KEY (`major_id`) REFERENCES `major` (`major_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('s0001', '学生1号', '2017', '00001', '男', '1999-10-01', ' XXXXXXXXXXXXXXXXXX', '11111115154');
INSERT INTO `student` VALUES ('s0002', '学生2号', '2017', '00001', '女', '2019-09-10', '11111111111111111111', '11111111111');
INSERT INTO `student` VALUES ('s0003', '学生3号', '3017', '00002', '女', '2019-09-02', '000000000000000000', '33333333333');

-- ----------------------------
-- Table structure for `student_training`
-- ----------------------------
DROP TABLE IF EXISTS `student_training`;
CREATE TABLE `student_training` (
  `case_id` varchar(20) NOT NULL COMMENT '事件ID',
  `student_id` varchar(12) NOT NULL COMMENT '学生学号',
  `type` varchar(11) NOT NULL COMMENT '奖罚类型\r\n''0'' 表示奖项\r\n''1'' 表示记过',
  `level` varchar(11) NOT NULL COMMENT '奖罚级别\r\n''0'' 表示院级\r\n''1'' 表示校级\r\n''2'' 表示国家级\r\n''3'' 表示世界级',
  `date` date NOT NULL COMMENT '奖罚时间',
  `description` varchar(30) default NULL COMMENT '说明',
  PRIMARY KEY  (`case_id`),
  KEY `st_tr_st_id` (`student_id`),
  CONSTRAINT `st_tr_st_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_training
-- ----------------------------
INSERT INTO `student_training` VALUES ('0001', 's0001', '奖项', '院级', '2019-09-12', null);
INSERT INTO `student_training` VALUES ('0002', 's0001', '处分', '院级', '2019-08-22', null);
INSERT INTO `student_training` VALUES ('0003', 's0002', '奖项', '校级', '2019-09-18', null);
INSERT INTO `student_training` VALUES ('0004', 's0003', '处分', '校级', '2019-09-17', null);

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(12) NOT NULL COMMENT '账号',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `role` int(11) NOT NULL COMMENT '用户类型\r\n''0'' 表示学生用户\r\n''1'' 表示讲师用户\r\n''2'' 表示教务用户\r\n',
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', '000000', '2');
INSERT INTO `user` VALUES ('s0001', '000000', '0');
INSERT INTO `user` VALUES ('t0001', '000000', '1');

-- ----------------------------
-- View structure for `award_info`
-- ----------------------------
DROP VIEW IF EXISTS `award_info`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `award_info` AS select `student_training`.`case_id` AS `case_id`,`student_training`.`student_id` AS `student_id`,`student_training`.`level` AS `level`,`student_training`.`date` AS `date`,`student_training`.`description` AS `description`,`student`.`student_name` AS `student_name`,`student_training`.`type` AS `type` from (`student_training` join `student`) where (`student_training`.`student_id` = `student`.`student_id`) ;

-- ----------------------------
-- View structure for `cour_info`
-- ----------------------------
DROP VIEW IF EXISTS `cour_info`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cour_info` AS select `course`.`course_id` AS `course_id`,`course`.`course_name` AS `course_name`,`course`.`type` AS `type`,`course`.`credit` AS `credit`,`course`.`description` AS `description`,`department`.`depart_name` AS `depart_name` from (`course` join `department`) where (`course`.`depart_id` = `department`.`depart_id`) ;

-- ----------------------------
-- View structure for `ins_info`
-- ----------------------------
DROP VIEW IF EXISTS `ins_info`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ins_info` AS select `instructor`.`instructor_id` AS `instructor_id`,`instructor`.`instructor_name` AS `instructor_name`,`instructor`.`sex` AS `sex`,`instructor`.`birthday` AS `birthday`,`instructor`.`id_card` AS `id_card`,`instructor`.`phone` AS `phone`,`department`.`depart_name` AS `depart_name` from (`instructor` join `department`) where (`instructor`.`depart_id` = `department`.`depart_id`) ;

-- ----------------------------
-- View structure for `major_info`
-- ----------------------------
DROP VIEW IF EXISTS `major_info`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `major_info` AS select `major`.`major_id` AS `major_id`,`major`.`major_name` AS `major_name`,`major`.`study_time` AS `study_time`,`department`.`depart_id` AS `depart_id`,`department`.`depart_name` AS `depart_name` from (`major` join `department`) where (`major`.`depart_id` = `department`.`depart_id`) ;

-- ----------------------------
-- View structure for `section_info`
-- ----------------------------
DROP VIEW IF EXISTS `section_info`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `section_info` AS select `course`.`course_name` AS `course_name`,`course`.`type` AS `type`,`course`.`credit` AS `credit`,`course_section`.`section_id` AS `section_id`,`course_section`.`year` AS `year`,`course_section`.`semseter` AS `semseter`,`course_section`.`capacity` AS `capacity`,`course_section`.`begin_time` AS `begin_time`,`course_section`.`end_time` AS `end_time`,`department`.`depart_name` AS `depart_name`,`instructor`.`instructor_name` AS `instructor_name` from (((`course` join `course_section`) join `department`) join `instructor`) where ((`course_section`.`instructor_id` = `instructor`.`instructor_id`) and (`course_section`.`course_id` = `course`.`course_id`) and (`course`.`depart_id` = `department`.`depart_id`)) ;

-- ----------------------------
-- View structure for `select_info`
-- ----------------------------
DROP VIEW IF EXISTS `select_info`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `select_info` AS select `section_info`.`course_name` AS `course_name`,`section_info`.`type` AS `type`,`section_info`.`credit` AS `credit`,`section_info`.`section_id` AS `section_id`,`section_info`.`year` AS `year`,`section_info`.`begin_time` AS `begin_time`,`section_info`.`end_time` AS `end_time`,`section_info`.`instructor_name` AS `instructor_name`,`course_selection`.`student_id` AS `student_id` from (`section_info` join `course_selection`) where (`section_info`.`section_id` = `course_selection`.`section_id`) ;

-- ----------------------------
-- View structure for `stu_grade`
-- ----------------------------
DROP VIEW IF EXISTS `stu_grade`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `stu_grade` AS select `course`.`credit` AS `credit`,`course_section`.`year` AS `year`,`course_section`.`semseter` AS `semseter`,`course`.`type` AS `type`,`course`.`course_name` AS `course_name`,`course_selection`.`section_id` AS `section_id`,`course_selection`.`usual_grade` AS `usual_grade`,`course_selection`.`final_grade` AS `final_grade`,`course_selection`.`grade` AS `grade`,`course_selection`.`student_id` AS `student_id` from ((`course` join `course_section`) join `course_selection`) where ((`course`.`course_id` = `course_section`.`course_id`) and (`course_selection`.`section_id` = `course_section`.`section_id`)) ;

-- ----------------------------
-- View structure for `stu_info`
-- ----------------------------
DROP VIEW IF EXISTS `stu_info`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `stu_info` AS select `student`.`student_id` AS `student_id`,`student`.`student_name` AS `student_name`,`student`.`clas` AS `clas`,`student`.`sex` AS `sex`,`student`.`birthday` AS `birthday`,`student`.`id_card` AS `id_card`,`student`.`phone` AS `phone`,`department`.`depart_name` AS `depart_name`,`major`.`major_name` AS `major_name` from ((`student` join `department`) join `major`) where ((`student`.`major_id` = `major`.`major_id`) and (`major`.`depart_id` = `department`.`depart_id`)) ;
