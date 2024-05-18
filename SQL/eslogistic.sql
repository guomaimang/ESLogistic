/*
 Navicat Premium Data Transfer

 Source Server         : Mysql-localhost
 Source Server Type    : MySQL
 Source Server Version : 80400 (8.4.0)
 Source Host           : localhost:3306
 Source Schema         : eslogistic

 Target Server Type    : MySQL
 Target Server Version : 80400 (8.4.0)
 File Encoding         : 65001

 Date: 18/05/2024 15:08:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pack
-- ----------------------------
DROP TABLE IF EXISTS `pack`;
CREATE TABLE `pack` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sender_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `sender_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `sender_work_node_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `receiver_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `receiver_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `receiver_work_node_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pack_type` int NOT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
  `current_work_node_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `frozen_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of pack
-- ----------------------------
BEGIN;
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (1, 'bnpcif', '13195536255', 's2', 'lqcavq', '19594691907', 's1', 2, '1', 's1', '2024-05-18 04:25:01');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (2, 'mssbvz', '15115029718', 's7', 'trdusw', '16105405669', 's6', 1, '1', 's6', '2024-05-18 04:24:59');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (3, 'nlcqyc', '18665482172', 's2', 'mhtkru', '12226251698', 's3', 1, '1', 's3', '2024-05-18 04:25:01');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (4, 'nsxeft', '14206086287', 's4', 'xclekg', '15765440022', 's8', 2, '4', 's8', '2024-05-18 04:29:59');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (5, 'kymsta', '12653269295', 's7', 'spsrlp', '17057627383', 's7', 1, '4', 's7', '2024-05-18 03:40:41');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (6, 'tvthzm', '12931655408', 's12', 'rcfhco', '18194948883', 's10', 1, '1', 's10', '2024-05-18 04:25:04');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (7, 'lxtwci', '17423891676', 's6', 'tgpzvj', '17829026095', 's6', 2, '4', 's6', '2024-05-18 03:40:45');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (8, 'oyrzrj', '17168997510', 's11', 'vvufev', '17519842475', 's8', 2, '4', 's8', '2024-05-18 04:29:39');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (9, 'tnqgip', '18687560880', 's4', 'jocqpi', '16315363766', 's5', 2, '4', 's5', '2024-05-18 04:29:58');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (10, 'whuzzg', '16555730593', 's6', 'croaud', '12653344087', 's10', 1, '4', 's10', '2024-05-18 04:29:54');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (11, 'hccmbk', '19054912413', 's2', 'trxyxd', '19661289090', 's12', 2, '4', 's12', '2024-05-18 04:30:13');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (12, 'mlhvzf', '10008722614', 's3', 'drkfqq', '17360626995', 's5', 1, '4', 's5', '2024-05-18 04:29:38');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (13, 'mazyvp', '10470105855', 's8', 'ubuqmp', '10896266746', 's10', 1, '4', 's10', '2024-05-18 04:29:54');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (14, 'zxdisw', '10176332621', 's5', 'fislxy', '14791371989', 's10', 1, '4', 's10', '2024-05-18 04:29:54');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (15, 'konuqq', '16114604672', 's10', 'odehdb', '10752900585', 's6', 2, '4', 's6', '2024-05-18 04:29:47');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (16, 'yzulev', '16387174351', 's12', 'lxilbe', '18790131384', 's8', 2, '4', 's8', '2024-05-18 04:29:39');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (17, 'siydbn', '16524155831', 's11', 'rkzndr', '11002251602', 's2', 1, '4', 's2', '2024-05-18 04:29:50');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (18, 'gypnpu', '10104831035', 's2', 'khavlc', '15847914590', 's12', 1, '4', 's12', '2024-05-18 04:29:53');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (19, 'lijbjp', '11862002217', 's1', 'mkdpxc', '18504832418', 's8', 1, '4', 's8', '2024-05-18 04:29:39');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (20, 'jbrjzz', '12060709642', 's6', 'dqfoiu', '18796179524', 's1', 2, '4', 's1', '2024-05-18 04:30:10');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (21, 'ccvufg', '16641751783', 's6', 'oykhtn', '14406674457', 's8', 1, '1', 's8', '2024-05-18 04:24:58');
INSERT INTO `pack` (`id`, `sender_name`, `sender_phone`, `sender_work_node_id`, `receiver_name`, `receiver_phone`, `receiver_work_node_id`, `pack_type`, `status`, `current_work_node_id`, `frozen_time`) VALUES (22, 'kdywen', '14057829107', 's4', 'uzujqu', '16557441064', 's2', 1, '1', 's2', '2024-05-18 04:24:55');
COMMIT;

-- ----------------------------
-- Table structure for pack_record
-- ----------------------------
DROP TABLE IF EXISTS `pack_record`;
CREATE TABLE `pack_record` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pack_id` bigint NOT NULL,
  `message` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of pack_record
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for transportation
-- ----------------------------
DROP TABLE IF EXISTS `transportation`;
CREATE TABLE `transportation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `driver_id` bigint NOT NULL,
  `type` int NOT NULL,
  `license` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `start_node_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `end_node_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int NOT NULL,
  `pack_num` int NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of transportation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for worknode
-- ----------------------------
DROP TABLE IF EXISTS `worknode`;
CREATE TABLE `worknode` (
  `id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` int NOT NULL,
  `coordinate_x` decimal(10,2) NOT NULL,
  `coordinate_y` decimal(10,2) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of worknode
-- ----------------------------
BEGIN;
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('a1', 3, 40.00, 20.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('a2', 3, 37.00, 113.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('a4', 3, 442.00, 114.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('c1', 2, 30.00, 30.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('c2', 2, 87.00, 117.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('c3', 2, 37.00, 147.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('c4', 2, 438.00, 85.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('s1', 1, 12.00, 21.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('s10', 1, 19.00, 164.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('s11', 1, 46.00, 169.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('s12', 1, 14.00, 147.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('s13', 1, 393.00, 147.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('s14', 1, 400.00, 80.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('s15', 1, 428.00, 48.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('s16', 1, 480.00, 60.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('s2', 1, 21.00, 12.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('s3', 1, 22.00, 15.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('s4', 1, 40.00, 40.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('s5', 1, 86.00, 125.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('s6', 1, 70.00, 120.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('s7', 1, 102.00, 127.00, 1);
INSERT INTO `worknode` (`id`, `type`, `coordinate_x`, `coordinate_y`, `status`) VALUES ('s8', 1, 89.00, 108.00, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
