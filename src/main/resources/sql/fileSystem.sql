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

 Date: 26/08/2021 18:08:23
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
BEGIN;
INSERT INTO `file` VALUES (78, '欧罗巴报告.mp4', '/group1/test/movies/欧罗巴报告.mp4');
INSERT INTO `file` VALUES (80, '魔法王国 艺术设计动漫风景4k壁纸_彼岸图网.jpg', '/group1/test/picture/魔法王国 艺术设计动漫风景4k壁纸_彼岸图网.jpg');
INSERT INTO `file` VALUES (81, '女孩 脸 玻璃 ĥɰ 好看动漫人物4k壁纸_彼岸图网.jpg', '/group1/test/picture/女孩 脸 玻璃 ĥɰ 好看动漫人物4k壁纸_彼岸图网.jpg');
INSERT INTO `file` VALUES (82, '螺旋星系4k壁纸_彼岸图网.jpg', '/group1/test/picture/螺旋星系4k壁纸_彼岸图网.jpg');
INSERT INTO `file` VALUES (83, '简约设计blissed绿色5k壁纸5120x2880_彼岸图网.jpg', '/group1/test/picture/简约设计blissed绿色5k壁纸5120x2880_彼岸图网.jpg');
INSERT INTO `file` VALUES (84, '日落 天空 火车 云 电线 Χǽ 女孩 男孩 4k动漫壁纸_彼岸图网.jpg', '/group1/test/picture/日落 天空 火车 云 电线 Χǽ 女孩 男孩 4k动漫壁纸_彼岸图网.jpg');
INSERT INTO `file` VALUES (85, '鲸鱼骑士4k壁纸 by_Artur Sadlos_彼岸图网.jpg', '/group1/test/picture/鲸鱼骑士4k壁纸 by_Artur Sadlos_彼岸图网.jpg');
INSERT INTO `file` VALUES (86, '带你去旅行 - 校长（张驰）.mp3', '/group1/test/music/带你去旅行 - 校长（张驰）.mp3');
INSERT INTO `file` VALUES (87, '曾经的你 - 许巍.mp3', '/group1/test/music/曾经的你 - 许巍.mp3');
INSERT INTO `file` VALUES (88, '倒数 - G.E.M.邓紫棋.mp3', '/group1/test/music/倒数 - G.E.M.邓紫棋.mp3');
INSERT INTO `file` VALUES (89, '多幸运 - 韩安旭.mp3', '/group1/test/music/多幸运 - 韩安旭.mp3');
INSERT INTO `file` VALUES (90, '非酋 - 薛明媛、朱贺.mp3', '/group1/test/music/非酋 - 薛明媛、朱贺.mp3');
INSERT INTO `file` VALUES (91, '稻香 - 周杰伦.mp3', '/group1/test/music/稻香 - 周杰伦.mp3');
INSERT INTO `file` VALUES (92, '粉红色的回忆 - 韩宝仪.mp3', '/group1/test/music/粉红色的回忆 - 韩宝仪.mp3');
INSERT INTO `file` VALUES (93, '独家记忆 - 陈小春.mp3', '/group1/test/music/独家记忆 - 陈小春.mp3');
INSERT INTO `file` VALUES (94, '后来 - 刘若英.mp3', '/group1/test/music/后来 - 刘若英.mp3');
INSERT INTO `file` VALUES (95, '大海 - 张雨生.mp3', '/group1/test/music/大海 - 张雨生.mp3');
INSERT INTO `file` VALUES (96, '成都 - 赵雷.mp3', '/group1/test/music/成都 - 赵雷.mp3');
INSERT INTO `file` VALUES (97, '风吹麦浪 - 李健.mp3', '/group1/test/music/风吹麦浪 - 李健.mp3');
INSERT INTO `file` VALUES (98, '丑八怪 - 薛之谦.mp3', '/group1/test/music/丑八怪 - 薛之谦.mp3');
INSERT INTO `file` VALUES (99, '光年之外 - G.E.M.邓紫棋.mp3', '/group1/test/music/光年之外 - G.E.M.邓紫棋.mp3');
INSERT INTO `file` VALUES (100, '江南 - 林俊杰.mp3', '/group1/test/music/江南 - 林俊杰.mp3');
INSERT INTO `file` VALUES (101, 'CDImage.wav', '/group1/test/music/CDImage.wav');
INSERT INTO `file` VALUES (102, '绘画 海景 帆船3440x1440带鱼屏壁纸_彼岸图网.png', '/group1/test/picture/绘画 海景 帆船3440x1440带鱼屏壁纸_彼岸图网.png');
INSERT INTO `file` VALUES (103, '绘画 海景 帆船3440x1440带鱼屏壁纸_彼岸图网.png', '/group1/test/picture/绘画 海景 帆船3440x1440带鱼屏壁纸_彼岸图网.png');
INSERT INTO `file` VALUES (104, '绘画 海景 帆船3440x1440带鱼屏壁纸_彼岸图网.png', '/group1/test/picture/绘画 海景 帆船3440x1440带鱼屏壁纸_彼岸图网.png');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of peers
-- ----------------------------
BEGIN;
INSERT INTO `peers` VALUES (14, 'linux-company', 'group1', 'http://10.60.0.215:8080', '', '2021-08-26 07:31:01', NULL);
COMMIT;

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
-- Records of permission
-- ----------------------------
BEGIN;
COMMIT;

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
-- Records of role
-- ----------------------------
BEGIN;
COMMIT;

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
-- Records of role_permission
-- ----------------------------
BEGIN;
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (5, 'admin', 'linux', 'e07ebd8650ec8dba920e9c0c8a4e4257', 18, 'a18477019945@gmail.com', 14, 'b962363fe6ce4c9385e73adfe55790bc', '2021-08-26 07:31:01', NULL, '2021-08-26 18:04:33', 0);
COMMIT;

-- ----------------------------
-- Table structure for user_file
-- ----------------------------
DROP TABLE IF EXISTS `user_file`;
CREATE TABLE `user_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '默认id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `file_id` int(11) NOT NULL COMMENT '文件id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_file
-- ----------------------------
BEGIN;
INSERT INTO `user_file` VALUES (76, 5, 78);
INSERT INTO `user_file` VALUES (78, 5, 80);
INSERT INTO `user_file` VALUES (79, 5, 81);
INSERT INTO `user_file` VALUES (80, 5, 82);
INSERT INTO `user_file` VALUES (81, 5, 83);
INSERT INTO `user_file` VALUES (82, 5, 84);
INSERT INTO `user_file` VALUES (83, 5, 85);
INSERT INTO `user_file` VALUES (84, 5, 86);
INSERT INTO `user_file` VALUES (85, 5, 87);
INSERT INTO `user_file` VALUES (86, 5, 88);
INSERT INTO `user_file` VALUES (87, 5, 89);
INSERT INTO `user_file` VALUES (88, 5, 90);
INSERT INTO `user_file` VALUES (89, 5, 91);
INSERT INTO `user_file` VALUES (90, 5, 92);
INSERT INTO `user_file` VALUES (91, 5, 93);
INSERT INTO `user_file` VALUES (92, 5, 94);
INSERT INTO `user_file` VALUES (93, 5, 95);
INSERT INTO `user_file` VALUES (94, 5, 96);
INSERT INTO `user_file` VALUES (95, 5, 97);
INSERT INTO `user_file` VALUES (96, 5, 98);
INSERT INTO `user_file` VALUES (97, 5, 99);
INSERT INTO `user_file` VALUES (98, 5, 100);
INSERT INTO `user_file` VALUES (99, 5, 101);
INSERT INTO `user_file` VALUES (100, 5, 102);
INSERT INTO `user_file` VALUES (101, 5, 103);
INSERT INTO `user_file` VALUES (102, 5, 104);
COMMIT;

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

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
