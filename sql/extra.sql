/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : eladmin

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 18/04/2021 21:22:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mnt_server
-- ----------------------------
DROP TABLE IF EXISTS `mnt_server`;
CREATE TABLE `mnt_server`
(
    `server_id`   bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `account`     varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '报文格式',
    `ip`          varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '服务ID',
    `name`        varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
    `password`    varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务调用地址',
    `port`        int(11)                                                 NULL DEFAULT NULL COMMENT '所属系统编号',
    `version`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务版本',
    `state`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务状态',
    `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务描述',
    `create_by`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
    `update_by`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
    `create_time` datetime                                                NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`server_id`) USING BTREE,
    INDEX `idx_ip` (`ip`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '服务管理'
  ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of mnt_server
-- ----------------------------
INSERT INTO `mnt_server`
VALUES (3, 'SOAP1.1', '1', '监测服务列表查询', '11', 1, '1.0', '关闭', '查询可用的服务列表', 'admin', 'admin', '2021-04-06 14:48:35',
        '2021-04-06 14:48:35');

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `mnt_device`;
CREATE TABLE `mnt_device`
(
    `device_id`   int(11)                                                     NOT NULL,
    `code`        varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '设备编码',
    `name`        varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
    `description` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
    `system`      varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '所属系统',
    PRIMARY KEY (`device_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '设备'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mnt_device
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `mnt_task`;
CREATE TABLE `mnt_task`
(
    `task_id`     int(11)                                                     NOT NULL,
    `name`        varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
    `service_num` int(11)                                                     NULL DEFAULT NULL COMMENT '包含的服务数',
    PRIMARY KEY (`task_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '任务'
  ROW_FORMAT = Dynamic;