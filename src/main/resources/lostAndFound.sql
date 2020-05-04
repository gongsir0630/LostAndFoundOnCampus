/*
 Navicat Premium Data Transfer

 Source Server         : gongsir
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : www.gongsir.club:3306
 Source Schema         : lostAndFound

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 04/05/2020 21:06:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Image
-- ----------------------------
DROP TABLE IF EXISTS `Image`;
CREATE TABLE `Image` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更信人',
  `updatetime` datetime NOT NULL COMMENT '更新时间',
  `img_title` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片描述',
  `img_link` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片地址',
  `img_status` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'no' COMMENT '显示状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='图片';

-- ----------------------------
-- Table structure for PDMAN_DB_VERSION
-- ----------------------------
DROP TABLE IF EXISTS `PDMAN_DB_VERSION`;
CREATE TABLE `PDMAN_DB_VERSION` (
  `DB_VERSION` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `VERSION_DESC` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CREATED_TIME` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `adm_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
  `adm_pass` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `real_name` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '管理员' COMMENT '姓名',
  `telephone` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `status` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ok' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_adm_id_uindex` (`adm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员';

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '证件id',
  `openid` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '发布人',
  `card_type` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '证件类型',
  `card_num` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '证件卡号',
  `card_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '证件姓名',
  `relation` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系方式',
  `card_time` datetime NOT NULL COMMENT '发布时间',
  `card_status` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'no' COMMENT '认领状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='证件信息表';

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '物品id',
  `openid` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '发布者',
  `good_title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `good_texts` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述（可选）',
  `good_class` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '物品分类',
  `good_image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片',
  `relation` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系方式',
  `good_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '丢失分类',
  `time` datetime NOT NULL COMMENT '发布时间',
  `good_status` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'no' COMMENT '认领状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='物品';

-- ----------------------------
-- Table structure for listen
-- ----------------------------
DROP TABLE IF EXISTS `listen`;
CREATE TABLE `listen` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '监听id',
  `openid` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接收用户',
  `lis_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '证件类型',
  `lis_num` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '证件号码',
  `telephone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话 手机号，用于短信通知用户',
  `lis_status` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'no' COMMENT '监听状态',
  `lis_time` datetime NOT NULL COMMENT '监听时间',
  `form_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'formId-QQ QQ推送模板消息需要',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='监听列表';

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `context` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公告内容',
  `adm_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `notice_status` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ok' COMMENT '公告状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公告';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_openid` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户唯一标识',
  `stu_num` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学号',
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '昵称',
  `user_head` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '头像',
  `user_app` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '小程序类型',
  `user_status` int(10) NOT NULL DEFAULT '0' COMMENT '用户状态 用户被举报即增加一次违规数，超过2次禁止登陆（状态设置为-1）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_openid_key` (`user_openid`)
) ENGINE=InnoDB AUTO_INCREMENT=262 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='小程序用户信息表';

SET FOREIGN_KEY_CHECKS = 1;
