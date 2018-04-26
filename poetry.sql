/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : poetry

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-04-26 17:32:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `name_sp` varchar(64) DEFAULT NULL,
  `desc` text,
  `desc_sp` text,
  `dynasty` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12654 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for daily_poetry
-- ----------------------------
DROP TABLE IF EXISTS `daily_poetry`;
CREATE TABLE `daily_poetry` (
  `day` char(10) NOT NULL,
  `poetry_id` int(8) NOT NULL,
  PRIMARY KEY (`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for poetry
-- ----------------------------
DROP TABLE IF EXISTS `poetry`;
CREATE TABLE `poetry` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `author` varchar(64) DEFAULT NULL,
  `author_sp` varchar(64) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `title_sp` varchar(255) DEFAULT NULL,
  `paragraphs` text,
  `paragraphs_sp` text,
  `strains` text,
  `dynasty` varchar(16) DEFAULT NULL,
  `style` int(4) DEFAULT '0' COMMENT '0:诗，1:词',
  `author_id` int(5) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_author` (`author_sp`) USING BTREE,
  KEY `idx_authorId` (`author_id`) USING BTREE,
  KEY `idx_title` (`title`(191)) USING BTREE,
  FULLTEXT KEY `idx_fullText` (`author_sp`,`title_sp`,`paragraphs_sp`,`keywords`,`tags`),
  FULLTEXT KEY `idx_tags` (`tags`)
) ENGINE=InnoDB AUTO_INCREMENT=333181 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for search_result
-- ----------------------------
DROP TABLE IF EXISTS `search_result`;
CREATE TABLE `search_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `total` int(11) DEFAULT NULL,
  `top100Id` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_keyword` (`keyword`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1814 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL DEFAULT '',
  `password` varchar(255) DEFAULT NULL,
  `group` tinyint(4) NOT NULL COMMENT '0-admin，1-common，2-banned',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
