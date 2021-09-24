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

 Date: 24/09/2021 21:41:59
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
  `file_md5` varchar(255) DEFAULT NULL COMMENT '文件md5',
  `file_path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `file_upload_time` datetime DEFAULT NULL COMMENT '文件上传时间',
  `peer_id` int(11) DEFAULT NULL COMMENT '所属集群id',
  `open` int(1) DEFAULT '0' COMMENT '是否公开 0私有 1公开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
BEGIN;
INSERT INTO `file` VALUES (1, '畫江湖之不良人第4季EP11.mp4', 'adee3378ee2380966490263112b0ea80', '/group1/shaoming/视频/畫江湖之不良人第4季EP11.mp4', '2021-09-24 13:38:34', 21, 0);
INSERT INTO `file` VALUES (126, 'pom.xml', '19734c20383b2f8102a07d7729860bd1', '/group1/cs-01/shiro-demo/pom.xml', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (127, '.gitignore', '55272edf6681a84fd2e62110bb605faf', '/group1/cs-01/shiro-demo/.gitignore', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (128, 'ShiroDemoApplicationTests.java', '81e95bf3598986f41338d2c9ed6e1524', '/group1/cs-01/shiro-demo/src/test/java/com/study/ShiroDemoApplicationTests.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (129, 'index.html', 'f1e5ad443fce0c2f8c87b00e9f78d4f2', '/group1/cs-01/shiro-demo/src/main/resources/templates/index.html', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (130, 'addUser.html', 'a8f66a0adf3df99469d4e2cd47451891', '/group1/cs-01/shiro-demo/src/main/resources/templates/addUser.html', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (131, '500.html', '6abceb00edb372181afaea1d001763eb', '/group1/cs-01/shiro-demo/src/main/resources/templates/500.html', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (132, '404.html', '7e3b448d214bd7dfb6e179568f0fad89', '/group1/cs-01/shiro-demo/src/main/resources/templates/404.html', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (133, 'login.html', 'aee9960d3dd5fab488c21384172260eb', '/group1/cs-01/shiro-demo/src/main/resources/templates/login.html', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (134, 'userList.html', '215799128cd58a5143d82977ee60e003', '/group1/cs-01/shiro-demo/src/main/resources/templates/userList.html', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (135, 'application.yml', 'a5705bfd1546f56ec3184181ce03d0ac', '/group1/cs-01/shiro-demo/src/main/resources/application.yml', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (136, 'shiroDemo.sql', '009b9f8ce46a3295f448521898e591b2', '/group1/cs-01/shiro-demo/src/main/resources/sql/shiroDemo.sql', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (137, 'MyMetaObjectHandler.java', '957c1deeb6d8dfa725b50be0843b0412', '/group1/cs-01/shiro-demo/src/main/java/com/study/handler/MyMetaObjectHandler.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (138, 'UnifiedExceptionHandler.java', 'ea0c22f5bbf96f413b6bf4e0fcd73b30', '/group1/cs-01/shiro-demo/src/main/java/com/study/handler/UnifiedExceptionHandler.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (139, 'ShiroRedisCacheManager.java', 'bf61ce437e4929035709b6543151c9a9', '/group1/cs-01/shiro-demo/src/main/java/com/study/config/ShiroRedisCacheManager.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (140, 'ShiroRealm.java', 'f1b4caf9808db68daa973c6d44d0471c', '/group1/cs-01/shiro-demo/src/main/java/com/study/config/ShiroRealm.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (141, 'SwaggerConfig.java', 'a33053c53f89cbcef652d3f01102facb', '/group1/cs-01/shiro-demo/src/main/java/com/study/config/SwaggerConfig.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (142, 'MybatisPlusConfig.java', '1bf72100580f3e4ea2f768e04e634655', '/group1/cs-01/shiro-demo/src/main/java/com/study/config/MybatisPlusConfig.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (143, 'ShiroConfig.java', '50c1adec58d5a443ba6d0d616ef7aa24', '/group1/cs-01/shiro-demo/src/main/java/com/study/config/ShiroConfig.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (144, 'JedisConfig.java', '02faba2cb7fad5c0d00129a54858b06c', '/group1/cs-01/shiro-demo/src/main/java/com/study/config/JedisConfig.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (145, 'ShiroTagFreeMarkerConfigurer.java', 'b29d84da8b52e3d1ac8f5b1d6443430c', '/group1/cs-01/shiro-demo/src/main/java/com/study/config/ShiroTagFreeMarkerConfigurer.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (146, 'SysUserRole.java', '48fb22c01a0e97473474fe3d24400f6f', '/group1/cs-01/shiro-demo/src/main/java/com/study/entity/SysUserRole.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (147, 'SysRole.java', '03d6dd89c2010caec5e119f08a336baf', '/group1/cs-01/shiro-demo/src/main/java/com/study/entity/SysRole.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (148, 'SysUser.java', '6546437f7c0f443e635c8df9a6ce0269', '/group1/cs-01/shiro-demo/src/main/java/com/study/entity/SysUser.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (149, 'SysRolePermission.java', '9e88057aefe1234313503b8fe59d3849', '/group1/cs-01/shiro-demo/src/main/java/com/study/entity/SysRolePermission.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (150, 'SysPermission.java', '4300ee42490b37314cbbcd58398cd7a4', '/group1/cs-01/shiro-demo/src/main/java/com/study/entity/SysPermission.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (151, 'SysPermissionMapper.xml', '209aaffe8952039a0c600ee52b1f44dd', '/group1/cs-01/shiro-demo/src/main/java/com/study/mapper/xml/SysPermissionMapper.xml', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (152, 'SysUserMapper.xml', '24eb1ca5fc7cc239ad349909a46ed5aa', '/group1/cs-01/shiro-demo/src/main/java/com/study/mapper/xml/SysUserMapper.xml', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (153, 'SysRoleMapper.xml', 'ad36e0f33fc8e52ace11d90d3c84c2c4', '/group1/cs-01/shiro-demo/src/main/java/com/study/mapper/xml/SysRoleMapper.xml', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (154, 'SysRolePermissionMapper.xml', '952fa801034f7a6e91cf7d596d7e5c6b', '/group1/cs-01/shiro-demo/src/main/java/com/study/mapper/xml/SysRolePermissionMapper.xml', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (155, 'SysUserRoleMapper.xml', '21146cc8667fbeb0d4ece0201aca9b6e', '/group1/cs-01/shiro-demo/src/main/java/com/study/mapper/xml/SysUserRoleMapper.xml', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (156, 'SysRoleMapper.java', 'e3e5f39c2710a26d682da73ec022269e', '/group1/cs-01/shiro-demo/src/main/java/com/study/mapper/SysRoleMapper.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (157, 'SysUserMapper.java', '4af908ad607b049219df988c0470b310', '/group1/cs-01/shiro-demo/src/main/java/com/study/mapper/SysUserMapper.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (158, 'SysUserRoleMapper.java', 'd7d6a0e97f15fb859e106f729d13694c', '/group1/cs-01/shiro-demo/src/main/java/com/study/mapper/SysUserRoleMapper.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (159, 'SysPermissionMapper.java', 'a73504fbbd0c73d9083b1ac016d0d495', '/group1/cs-01/shiro-demo/src/main/java/com/study/mapper/SysPermissionMapper.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (160, 'SysRolePermissionMapper.java', '38514b93f41263aae783fd75f0927a7f', '/group1/cs-01/shiro-demo/src/main/java/com/study/mapper/SysRolePermissionMapper.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (161, 'Md5Utils.java', '28216dd8add6832134d93123788433aa', '/group1/cs-01/shiro-demo/src/main/java/com/study/utils/Md5Utils.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (162, 'SpringBootUtils.java', '0853e1fedb8645c7d728d1c078bf9f36', '/group1/cs-01/shiro-demo/src/main/java/com/study/utils/SpringBootUtils.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (163, 'CodeGenerator.java', '80d4318abd822cded7429940970117a9', '/group1/cs-01/shiro-demo/src/main/java/com/study/utils/CodeGenerator.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (164, 'JedisUtil.java', 'c12f0281b4ded608d0381e32ada223ed', '/group1/cs-01/shiro-demo/src/main/java/com/study/utils/JedisUtil.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (165, 'SerializableUtil.java', '12e30fa89ee471b2daf5e22e42ae85dd', '/group1/cs-01/shiro-demo/src/main/java/com/study/utils/SerializableUtil.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (166, 'YmlUtils.java', '3d99c7202c5c4f9cfee26093cf59bb81', '/group1/cs-01/shiro-demo/src/main/java/com/study/utils/YmlUtils.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (167, 'SysPermissionController.java', '912850128dbdaaa97d7c03d0dbcdb99e', '/group1/cs-01/shiro-demo/src/main/java/com/study/controller/SysPermissionController.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (168, 'SysRolePermissionController.java', '1451a2dddfff80f5d40b333be06f5cfd', '/group1/cs-01/shiro-demo/src/main/java/com/study/controller/SysRolePermissionController.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (169, 'SysUserController.java', 'cab2d3ee14f80fd7b77c28a121ffca0b', '/group1/cs-01/shiro-demo/src/main/java/com/study/controller/SysUserController.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (170, 'SysUserRoleController.java', '4b974ba3fd455aa012d4e30ec49bb7a9', '/group1/cs-01/shiro-demo/src/main/java/com/study/controller/SysUserRoleController.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (171, 'LoginController.java', '44873780de779bb6464a3e0203d3c11c', '/group1/cs-01/shiro-demo/src/main/java/com/study/controller/LoginController.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (172, 'SysRoleController.java', '3262176c1c32c7a757c9f8beec164778', '/group1/cs-01/shiro-demo/src/main/java/com/study/controller/SysRoleController.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (173, 'ErrorPageController.java', '563c430dcfad59ff5160cffdef45e4b0', '/group1/cs-01/shiro-demo/src/main/java/com/study/controller/ErrorPageController.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (174, 'SysRoleServiceImpl.java', '69180022abd6f9209b0a3d4448b205c6', '/group1/cs-01/shiro-demo/src/main/java/com/study/service/impl/SysRoleServiceImpl.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (175, 'SysRolePermissionServiceImpl.java', 'e78d2bf097f42fd3122e291b5ed78e48', '/group1/cs-01/shiro-demo/src/main/java/com/study/service/impl/SysRolePermissionServiceImpl.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (176, 'SysUserRoleServiceImpl.java', '2975a9b4357f8c838d317e6a4932091d', '/group1/cs-01/shiro-demo/src/main/java/com/study/service/impl/SysUserRoleServiceImpl.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (177, 'SysUserServiceImpl.java', 'f3efa2c24c85a4a379dc39dc494a4cd5', '/group1/cs-01/shiro-demo/src/main/java/com/study/service/impl/SysUserServiceImpl.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (178, 'SysPermissionServiceImpl.java', '86f3b81f95ce4e1c86969ff1a3699619', '/group1/cs-01/shiro-demo/src/main/java/com/study/service/impl/SysPermissionServiceImpl.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (179, 'SysRoleService.java', 'fceb20aea358bdd64033ad3500f0bdb6', '/group1/cs-01/shiro-demo/src/main/java/com/study/service/SysRoleService.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (180, 'SysUserService.java', 'f57da402c51548ba1b90857c82862736', '/group1/cs-01/shiro-demo/src/main/java/com/study/service/SysUserService.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (181, 'SysRolePermissionService.java', 'c0d75472d4faa6ecfe120a2aaaefdba3', '/group1/cs-01/shiro-demo/src/main/java/com/study/service/SysRolePermissionService.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (182, 'SysUserRoleService.java', '6491398b5ce4ac5bc0db9c3249cd1ce6', '/group1/cs-01/shiro-demo/src/main/java/com/study/service/SysUserRoleService.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (183, 'SysPermissionService.java', '3160e9082a5d0e9c7ad74998b2cb2d0f', '/group1/cs-01/shiro-demo/src/main/java/com/study/service/SysPermissionService.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (184, 'ShiroDemoApplication.java', '5c907079ce50fac4b9d76f6cc5ac15af', '/group1/cs-01/shiro-demo/src/main/java/com/study/ShiroDemoApplication.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (185, 'CustomException.java', '544a5765939cc81f393fc7ad2e1fe108', '/group1/cs-01/shiro-demo/src/main/java/com/study/exception/CustomException.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (186, 'ShiroRedisCache.java', 'b41c6d4cb77236fe429f8b9c108d7e5a', '/group1/cs-01/shiro-demo/src/main/java/com/study/shiro/ShiroRedisCache.java', '2021-09-24 14:28:21', 21, 0);
INSERT INTO `file` VALUES (187, 'RedisSessionDAO.java', '6bbd0ad4700d22ef1324a94ec8a80e4b', '/group1/cs-01/shiro-demo/src/main/java/com/study/shiro/RedisSessionDAO.java', '2021-09-24 14:28:21', 21, 0);
COMMIT;

-- ----------------------------
-- Table structure for mail
-- ----------------------------
DROP TABLE IF EXISTS `mail`;
CREATE TABLE `mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '信件id',
  `mail_title` longtext COMMENT '标题',
  `mail_content` longtext COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail
-- ----------------------------
BEGIN;
INSERT INTO `mail` VALUES (1, '你好', '这是一封测试邮件!<img src=\"http://10.60.2.0:8081/static/lib/layui-v2.5.6/layui/images/face/1.gif\" alt=\"[嘻嘻]\">');
INSERT INTO `mail` VALUES (3, '文件被删除通知!', '您在 2021-09-24 17:41:29 分享的文件: pom.xml  于 2021-09-24 17:41:55 被管理员: cs-02 删除,请悉知!');
COMMIT;

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
  `state` int(1) DEFAULT '0' COMMENT '状态 0未读 1已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail_receive
-- ----------------------------
BEGIN;
INSERT INTO `mail_receive` VALUES (1, 1, 'cs-01', 35, '2021-09-24 14:41:47', 1);
INSERT INTO `mail_receive` VALUES (3, 3, 'cs-02', 36, '2021-09-24 17:41:55', 1);
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail_send
-- ----------------------------
BEGIN;
INSERT INTO `mail_send` VALUES (1, '1650666953@qq.com', 'cs-01', '2021-09-24 14:41:47', 1, 0, 1);
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
  `disk_total_size` double DEFAULT NULL COMMENT '集群空间总大小',
  `disk_left_size` double DEFAULT NULL COMMENT '集群剩余可分配空间大小',
  `disk_used_size` double DEFAULT NULL COMMENT '集群已使用空间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of peers
-- ----------------------------
BEGIN;
INSERT INTO `peers` VALUES (21, 'default', 'group1', 'http://10.60.1.79:8080', '', 51056423731.2, 34950296371.2, 431918940.16, '2021-09-23 15:21:05', NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, '超级管理员', '(拥有自己的邮箱以及文件管理空间，允许对自己的文件进行管理，权限包括上传，下载，删除，可编辑自己的文本文件，可浏览开放的文本文件，同时可以分享自己的文件，管理全部用户除同级的用户信息，可以对以上用户发布的公开文件进行管理，管理首页内容以及热门内容，可以将用户公开的内容进行处理，如加上精华，热门等标签，可以添加修改或删除公共空间分类');
INSERT INTO `role` VALUES (2, '特殊用户', '拥有自己的邮箱以及文件管理空间，允许对自己的文件进行管理，权限包括上传，下载，删除，可编辑自己的文本文件，可浏览开放的文本文件，同时可以分享自己的文件，管理普通用户以及一般用户的用户信息，可以对以上用户发布的公开文件进行管理');
INSERT INTO `role` VALUES (3, '一般用户', '拥有自己的邮箱以及文件管理空间，允许对自己的文件进行管理，权限包括上传，下载，删除，可编辑自己的文本文件，可浏览开放的文本文件，同时可以分享自己的文件，管理普通用户的用户信息');
INSERT INTO `role` VALUES (4, '普通用户', '拥有自己的邮箱以及文件管理空间，允许对自己的文件进行管理，权限包括上传，下载，删除，可编辑自己的文本文件，可浏览开放的文本文件');
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
-- Table structure for share
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '默认主键',
  `file_id` int(11) DEFAULT NULL COMMENT '文件id',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `file_size` varchar(255) DEFAULT NULL COMMENT '文件大小',
  `sharer_username` varchar(255) DEFAULT NULL COMMENT '分享人用户名',
  `sharer` varchar(255) DEFAULT NULL COMMENT '分享人',
  `sharer_role` int(11) DEFAULT NULL COMMENT '分享人角色',
  `share_time` datetime DEFAULT NULL COMMENT '分享时间',
  `download_count` int(11) DEFAULT '0' COMMENT '下载次数',
  `read_count` int(11) DEFAULT '0' COMMENT '浏览次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share
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
  `nick_name` varchar(255) NOT NULL COMMENT '用户昵称',
  `password` longtext NOT NULL COMMENT '用户密码',
  `gender` int(1) DEFAULT '1' COMMENT '性别 0 女 1 男',
  `age` int(3) NOT NULL COMMENT '用户年龄',
  `email` varchar(50) NOT NULL COMMENT '用户邮箱',
  `peersId` int(11) NOT NULL COMMENT '所属集群id',
  `credentials_salt` longtext NOT NULL COMMENT '证书盐值',
  `total_disk_space` double DEFAULT NULL COMMENT '用户总存储空间',
  `left_disk_space` double DEFAULT NULL COMMENT '用户剩余存储空间',
  `create_time` datetime NOT NULL COMMENT '注册时间',
  `update_time` datetime DEFAULT NULL COMMENT '信息更新时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `deleted` int(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`,`username`,`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (35, 'shaoming', '邵明', '9ba85c58b67a4ee824f1d2baf1366390', 1, 18, '1650666953@qq.com', 21, 'ea451f6464ca4cce9158b54eeae1fc86', 5368709120, 4936884557, '2021-09-23 15:21:27', NULL, '2021-09-24 17:40:34', 0);
INSERT INTO `user` VALUES (36, 'cs-01', 'cs-01', 'd4be17846d753ad8bdf8c50d697a4df3', 1, 18, '01@cs.com', 21, '02bd13cba3da4d3fb684b3a40095eb50', 5368709120, 5368612864, '2021-09-24 14:01:15', NULL, '2021-09-24 17:42:33', 0);
INSERT INTO `user` VALUES (37, 'cs-02', 'cs-02', 'e74e43b6eea4a7cd20230cd22b775f75', 1, 18, '02@cs.com', 21, '4e6ae673c36a4a48be6d23594da4ca94', 5368709120, 5368709120, '2021-09-24 16:57:43', NULL, '2021-09-24 17:41:58', 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_file
-- ----------------------------
BEGIN;
INSERT INTO `user_file` VALUES (1, 35, 1);
INSERT INTO `user_file` VALUES (126, 36, 126);
INSERT INTO `user_file` VALUES (127, 36, 127);
INSERT INTO `user_file` VALUES (128, 36, 128);
INSERT INTO `user_file` VALUES (129, 36, 129);
INSERT INTO `user_file` VALUES (130, 36, 130);
INSERT INTO `user_file` VALUES (131, 36, 131);
INSERT INTO `user_file` VALUES (132, 36, 132);
INSERT INTO `user_file` VALUES (133, 36, 133);
INSERT INTO `user_file` VALUES (134, 36, 134);
INSERT INTO `user_file` VALUES (135, 36, 135);
INSERT INTO `user_file` VALUES (136, 36, 136);
INSERT INTO `user_file` VALUES (137, 36, 137);
INSERT INTO `user_file` VALUES (138, 36, 138);
INSERT INTO `user_file` VALUES (139, 36, 139);
INSERT INTO `user_file` VALUES (140, 36, 140);
INSERT INTO `user_file` VALUES (141, 36, 141);
INSERT INTO `user_file` VALUES (142, 36, 142);
INSERT INTO `user_file` VALUES (143, 36, 143);
INSERT INTO `user_file` VALUES (144, 36, 144);
INSERT INTO `user_file` VALUES (145, 36, 145);
INSERT INTO `user_file` VALUES (146, 36, 146);
INSERT INTO `user_file` VALUES (147, 36, 147);
INSERT INTO `user_file` VALUES (148, 36, 148);
INSERT INTO `user_file` VALUES (149, 36, 149);
INSERT INTO `user_file` VALUES (150, 36, 150);
INSERT INTO `user_file` VALUES (151, 36, 151);
INSERT INTO `user_file` VALUES (152, 36, 152);
INSERT INTO `user_file` VALUES (153, 36, 153);
INSERT INTO `user_file` VALUES (154, 36, 154);
INSERT INTO `user_file` VALUES (155, 36, 155);
INSERT INTO `user_file` VALUES (156, 36, 156);
INSERT INTO `user_file` VALUES (157, 36, 157);
INSERT INTO `user_file` VALUES (158, 36, 158);
INSERT INTO `user_file` VALUES (159, 36, 159);
INSERT INTO `user_file` VALUES (160, 36, 160);
INSERT INTO `user_file` VALUES (161, 36, 161);
INSERT INTO `user_file` VALUES (162, 36, 162);
INSERT INTO `user_file` VALUES (163, 36, 163);
INSERT INTO `user_file` VALUES (164, 36, 164);
INSERT INTO `user_file` VALUES (165, 36, 165);
INSERT INTO `user_file` VALUES (166, 36, 166);
INSERT INTO `user_file` VALUES (167, 36, 167);
INSERT INTO `user_file` VALUES (168, 36, 168);
INSERT INTO `user_file` VALUES (169, 36, 169);
INSERT INTO `user_file` VALUES (170, 36, 170);
INSERT INTO `user_file` VALUES (171, 36, 171);
INSERT INTO `user_file` VALUES (172, 36, 172);
INSERT INTO `user_file` VALUES (173, 36, 173);
INSERT INTO `user_file` VALUES (174, 36, 174);
INSERT INTO `user_file` VALUES (175, 36, 175);
INSERT INTO `user_file` VALUES (176, 36, 176);
INSERT INTO `user_file` VALUES (177, 36, 177);
INSERT INTO `user_file` VALUES (178, 36, 178);
INSERT INTO `user_file` VALUES (179, 36, 179);
INSERT INTO `user_file` VALUES (180, 36, 180);
INSERT INTO `user_file` VALUES (181, 36, 181);
INSERT INTO `user_file` VALUES (182, 36, 182);
INSERT INTO `user_file` VALUES (183, 36, 183);
INSERT INTO `user_file` VALUES (184, 36, 184);
INSERT INTO `user_file` VALUES (185, 36, 185);
INSERT INTO `user_file` VALUES (186, 36, 186);
INSERT INTO `user_file` VALUES (187, 36, 187);
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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (31, 35, 1);
INSERT INTO `user_role` VALUES (32, 36, 4);
INSERT INTO `user_role` VALUES (33, 37, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
