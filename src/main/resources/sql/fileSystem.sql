/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : fileSystem

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 13/09/2021 19:50:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `peer_id` int(11) DEFAULT NULL COMMENT '所属集群id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=636 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mail
-- ----------------------------
DROP TABLE IF EXISTS `mail`;
CREATE TABLE `mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '信件id',
  `mail_title` longtext COMMENT '标题',
  `mail_content` longtext COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mail_receive
-- ----------------------------
DROP TABLE IF EXISTS `mail_receive`;
CREATE TABLE `mail_receive` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mail_id` int(11) DEFAULT NULL COMMENT '邮件id',
  `sender_name` varchar(255) DEFAULT NULL COMMENT '发信人姓名 ',
  `receive_id` int(11) DEFAULT NULL COMMENT '接收人id',
  `receive_time` datetime DEFAULT NULL COMMENT '接收时间',
  `state` int(1) DEFAULT NULL COMMENT '状态 0未读 1已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mail_send
-- ----------------------------
DROP TABLE IF EXISTS `mail_send`;
CREATE TABLE `mail_send` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '默认主键',
  `receive_user_email` varchar(255) DEFAULT NULL COMMENT '接收人email',
  `send_out_name` varchar(255) DEFAULT NULL COMMENT '发送人姓名',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `mail_id` int(11) DEFAULT NULL COMMENT '邮件id',
  `send_state` int(1) DEFAULT NULL COMMENT '发送状态 0已发送 1已删除',
  `receive_state` int(1) DEFAULT NULL COMMENT '接收状态 0 未接收 1 已接收',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for peers
-- ----------------------------
DROP TABLE IF EXISTS `peers`;
CREATE TABLE `peers` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '集群id',
  `name` longtext NOT NULL COMMENT '集群名称',
  `group_name` longtext COMMENT '组名',
  `server_address` longtext NOT NULL COMMENT '集群服务地址',
  `show_address` longtext COMMENT '访问域名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_tag` varchar(255) DEFAULT NULL COMMENT '权限标签',
  `permission_desc` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `permission_url` varchar(255) DEFAULT NULL COMMENT '权限资源路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(255) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '默认id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(15) NOT NULL COMMENT '用户名',
  `real_name` varchar(255) NOT NULL COMMENT '真实姓名',
  `password` longtext NOT NULL COMMENT '用户密码',
  `age` int(3) NOT NULL COMMENT '用户年龄',
  `email` varchar(50) NOT NULL COMMENT '用户邮箱',
  `peersId` int(11) NOT NULL COMMENT '所属集群id',
  `credentials_salt` longtext NOT NULL COMMENT '证书盐值',
  `create_time` datetime NOT NULL COMMENT '注册时间',
  `update_time` datetime DEFAULT NULL COMMENT '信息更新时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `deleted` int(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`,`username`,`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_file
-- ----------------------------
DROP TABLE IF EXISTS `user_file`;
CREATE TABLE `user_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '默认id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `file_id` int(11) NOT NULL COMMENT '文件id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1497 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '默认id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
