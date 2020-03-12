DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `age` int(4) DEFAULT NULL COMMENT '年龄',
  `mobile` varchar(20) DEFAULT NULL COMMENT '号码',
	`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`ext_fields` VARCHAR(1000) COMMENT '扩展字段',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户表';

ALTER TABLE user AUTO_INCREMENT = 0;
TRUNCATE TABLE user;

INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('1', 'Darren', '男', '22', '12345678910', '2019-02-26 23:49:03', '2019-04-02 10:08:14', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('2', '神兽', '男', '30', '12345678910', '2019-03-26 11:49:03', '2019-04-02 10:06:13', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('3', '李斯', '男', '25', '12345678910', '2019-03-26 11:49:03', '2019-04-02 10:06:13', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('4', '宇凡', '男', '21', '12345678910', '2019-03-26 11:49:03', '2019-04-02 10:06:13', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('5', '张宇', '男', '25', '12345678910', '2019-03-26 11:49:03', '2019-04-02 10:07:48', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('6', '张丽', '女', '21', '12345678910', '2019-02-26 11:49:03', '2019-04-02 10:06:13', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('7', '哥哥', '女', '28', '12345678910', '2019-03-26 11:49:03', '2019-04-02 10:06:13', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('8', '王路', '男', '18', '12345678910', '2019-03-26 11:49:03', '2019-04-02 10:06:13', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('9', '高山', '男', '22', '12345678910', '2019-03-26 11:49:03', '2019-04-02 10:06:13', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('10', '李咯', '男', '30', '12345678912', '2019-03-26 11:49:03', '2019-04-02 10:10:03', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('11', '李伟', '男', '25', '12345678910', '2019-03-26 11:49:03', '2019-04-02 10:06:13', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('12', '李鹏', '男', '21', '12345678910', '2019-03-26 11:49:04', '2019-04-02 10:06:13', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('13', '戴维', '男', '25', '12345678910', '2019-03-26 11:49:04', '2019-04-02 10:06:13', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('14', '戴分', '女', '21', '12345678910', '2019-03-26 11:49:04', '2019-04-02 10:06:13', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('15', '蔡芬', '女', '28', '12345678910', '2019-03-26 11:49:04', '2019-04-02 10:06:13', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('16', '刘峰', '男', '18', '12345678910', '2019-03-26 11:49:04', '2019-04-02 10:06:13', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('17', '文龙', '男', '22', '138363850', '2019-04-02 10:54:01', '2019-04-02 10:54:01', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('18', '张健康', '男', '22', '12345678912', '2019-04-10 16:20:04', '2019-04-10 16:20:04', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('19', '李飞', '男', '25', '789456133', '2019-04-12 11:41:46', '2019-04-12 11:41:46', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('20', '李芳芳', '女', '18', '46578974654', '2019-04-12 11:42:07', '2019-04-12 11:42:07', NULL);
INSERT INTO `darren_service_center`.`user` (`id`, `name`, `sex`, `age`, `mobile`, `create_time`, `update_time`, `ext_fields`) VALUES ('21', '张静', '女', '22', '451236985214', '2019-04-12 11:42:24', '2019-04-12 11:42:24', NULL);
