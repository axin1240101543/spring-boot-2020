DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dict_code` varchar(100) DEFAULT NULL COMMENT '字典编码',
  `dict_name` varchar(20) DEFAULT NULL COMMENT '字典名称',
  `dict_type` varchar(20) DEFAULT NULL COMMENT '字典类型',
  `dict_desc` varchar(400) DEFAULT NULL COMMENT '字典详情',
  `remark` varchar(400) DEFAULT NULL COMMENT '备注',
	`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`ext_fields` VARCHAR(1000) COMMENT '扩展字段',
  PRIMARY KEY (`id`),
  KEY `dict_code`(`dict_code`),
  KEY `dict_type`(`dict_type`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='字典表';

ALTER TABLE user AUTO_INCREMENT = 0;
TRUNCATE TABLE user;

INSERT INTO `darren_service_center`.`dict` (`id`, `dict_code`, `dict_name`, `dict_type`, `dict_desc`, `remark`, `create_time`, `update_time`, `ext_fields`) VALUES ('1', '1', '男', 'USER_SEX', NULL, NULL, '2019-03-28 14:48:50', '2019-03-28 14:48:50', NULL);
INSERT INTO `darren_service_center`.`dict` (`id`, `dict_code`, `dict_name`, `dict_type`, `dict_desc`, `remark`, `create_time`, `update_time`, `ext_fields`) VALUES ('2', '2', '女', 'USER_SEX', NULL, NULL, '2019-03-28 14:48:50', '2019-03-28 14:48:50', NULL);
