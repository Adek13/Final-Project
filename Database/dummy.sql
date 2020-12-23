/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 100138
 Source Host           : localhost:3306
 Source Schema         : dummy

 Target Server Type    : MySQL
 Target Server Version : 100138
 File Encoding         : 65001

 Date: 23/12/2020 15:59:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ewallet
-- ----------------------------
DROP TABLE IF EXISTS `ewallet`;
CREATE TABLE `ewallet`  (
  `virtual_account` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nama` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `saldo` double NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`virtual_account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ewallet
-- ----------------------------
INSERT INTO `ewallet` VALUES ('080000000000', 'raze', 613000, 'ewallet1@gmail.com', 'pass1234');
INSERT INTO `ewallet` VALUES ('082837283799', 'budi', 322400, 'ewallet2@gmail.com', 'pass1234');

SET FOREIGN_KEY_CHECKS = 1;
