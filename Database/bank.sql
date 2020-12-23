/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 100138
 Source Host           : localhost:3306
 Source Schema         : bank

 Target Server Type    : MySQL
 Target Server Version : 100138
 File Encoding         : 65001

 Date: 23/12/2020 15:59:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (69);

-- ----------------------------
-- Table structure for mutasi
-- ----------------------------
DROP TABLE IF EXISTS `mutasi`;
CREATE TABLE `mutasi`  (
  `id_mutasi` int NOT NULL,
  `jenis_mutasi` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `jumlah_mutasi` double NULL DEFAULT NULL,
  `no_rekening` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `waktu_mutasi` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id_mutasi`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mutasi
-- ----------------------------
INSERT INTO `mutasi` VALUES (1, 'Top Up E-Wallet', 102000, '12345678', '2020-10-01 23:26:27');
INSERT INTO `mutasi` VALUES (42, 'Top Up E-Wallet', 52000, '12345678', '2020-12-21 22:41:04');
INSERT INTO `mutasi` VALUES (43, 'Top Up E-Wallet', 102000, '12345678', '2020-12-21 23:27:03');
INSERT INTO `mutasi` VALUES (45, 'Top Up E-Wallet', 13200, '12345678', '2020-12-22 04:34:00');
INSERT INTO `mutasi` VALUES (46, 'Top Up E-Wallet', 2000, '12345678', '2020-12-22 04:49:17');
INSERT INTO `mutasi` VALUES (47, 'Top Up E-Wallet', 2000, '12345678', '2020-12-22 04:50:07');
INSERT INTO `mutasi` VALUES (48, 'Top Up E-Wallet', 2000, '12345678', '2020-12-22 04:51:27');
INSERT INTO `mutasi` VALUES (49, 'Top Up E-Wallet', 2000, '12345678', '2020-12-22 04:57:37');
INSERT INTO `mutasi` VALUES (50, 'Top Up E-Wallet', 2000, '12345678', '2020-12-22 04:59:40');
INSERT INTO `mutasi` VALUES (51, 'Top Up E-Wallet', 10000, '12345678', '2020-12-22 05:26:37');
INSERT INTO `mutasi` VALUES (52, 'Top Up E-Wallet', 8000, '12345678', '2020-12-22 05:41:21');
INSERT INTO `mutasi` VALUES (53, 'Top Up E-Wallet', 20000, '12345678', '2020-12-22 05:42:20');
INSERT INTO `mutasi` VALUES (54, 'Top Up E-Wallet', 13200, '12345678', '2020-12-22 05:42:30');
INSERT INTO `mutasi` VALUES (55, 'Top Up E-Wallet', 17000, '12345678', '2020-12-22 05:43:55');
INSERT INTO `mutasi` VALUES (56, 'Top Up E-Wallet', 52000, '12345678', '2020-12-22 12:10:49');
INSERT INTO `mutasi` VALUES (57, 'Top Up E-Wallet', 98000, '12345678', '2020-12-22 17:40:41');
INSERT INTO `mutasi` VALUES (60, 'Top Up E-Wallet', 202000, '12345678', '2020-12-22 21:18:57');
INSERT INTO `mutasi` VALUES (62, 'Top Up E-Wallet', 22000, '12345678', '2020-12-23 00:29:06');
INSERT INTO `mutasi` VALUES (63, 'Top Up E-Wallet', 32000, '87654321', '2020-12-23 02:35:31');
INSERT INTO `mutasi` VALUES (64, 'Top Up E-Wallet', 22000, '12345678', '2020-12-23 10:27:24');
INSERT INTO `mutasi` VALUES (65, 'Top Up E-Wallet', 22000, '12345678', '2020-12-23 11:46:03');
INSERT INTO `mutasi` VALUES (66, 'Top Up E-Wallet', 32000, '12345678', '2020-12-23 11:52:37');

-- ----------------------------
-- Table structure for nasabah
-- ----------------------------
DROP TABLE IF EXISTS `nasabah`;
CREATE TABLE `nasabah`  (
  `id_nasabah` int NOT NULL AUTO_INCREMENT,
  `alamat` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nama_nasabah` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `tanggal_lahir` date NULL DEFAULT NULL,
  PRIMARY KEY (`id_nasabah`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of nasabah
-- ----------------------------
INSERT INTO `nasabah` VALUES (1, 'jakarta', 'budi', '2020-12-08');
INSERT INTO `nasabah` VALUES (2, 'padang', 'an', '2020-12-10');
INSERT INTO `nasabah` VALUES (3, 'bandung', 'yuli', '2020-12-09');

-- ----------------------------
-- Table structure for rekening
-- ----------------------------
DROP TABLE IF EXISTS `rekening`;
CREATE TABLE `rekening`  (
  `no_rekening` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `id_nasabah` int NULL DEFAULT NULL,
  `saldo` double NULL DEFAULT NULL,
  PRIMARY KEY (`no_rekening`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of rekening
-- ----------------------------
INSERT INTO `rekening` VALUES ('080080', 3, 500000);
INSERT INTO `rekening` VALUES ('12345678', 1, 4100600);
INSERT INTO `rekening` VALUES ('87654321', 2, 968000);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id_user` int NOT NULL,
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `id_nasabah` int NOT NULL,
  `no_telepon` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_user`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (40, 'a@a5.com', 2, '02938019123', 'pass1234');
INSERT INTO `user` VALUES (44, '2@2.com', 1, '012345678901', 'pass12345');
INSERT INTO `user` VALUES (68, 'alfi@gmail.com', 3, '01829038123', 'pass1234');

SET FOREIGN_KEY_CHECKS = 1;
