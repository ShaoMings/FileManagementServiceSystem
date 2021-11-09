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

 Date: 09/11/2021 17:44:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for CLUSTERNODES
-- ----------------------------
DROP TABLE IF EXISTS `CLUSTERNODES`;
CREATE TABLE `CLUSTERNODES` (
  `ID` varchar(1000) NOT NULL,
  `MODIFIED` bigint(20) DEFAULT NULL,
  `MODCOUNT` bigint(20) DEFAULT NULL,
  `SIZE` bigint(20) DEFAULT NULL,
  `DATA` varchar(16384) DEFAULT NULL,
  `BDATA` blob,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CLUSTERNODES
-- ----------------------------
BEGIN;
INSERT INTO `CLUSTERNODES` VALUES ('1', NULL, 8, 467, '{\"instance\":\"\\/Users\\/shaoming\\/Documents\\/IdeaProjects\\/JavaContentRepositoryDemo\",\"machine\":\"mac:c0b883773e3b\",\"_id\":\"1\",\"state\":\"ACTIVE\",\"leaseEnd\":1632964252036,\"_modCount\":8,\"recoveryLock\":null,\"info\":\"id: 1,\\nstartTime: 1632964142690,\\nmachineId: mac:c0b883773e3b,\\ninstanceId: \\/Users\\/shaoming\\/Documents\\/IdeaProjects\\/JavaContentRepositoryDemo,\\npid: 62299,\\nuuid: dcb66b7f-8c16-4567-95a3-7cc69bad8e02,\\nreadWriteMode: null,\\nstate: ACTIVE,\\nrevLock: NONE\"}', NULL);
COMMIT;

-- ----------------------------
-- Table structure for DATASTORE_DATA
-- ----------------------------
DROP TABLE IF EXISTS `DATASTORE_DATA`;
CREATE TABLE `DATASTORE_DATA` (
  `ID` varchar(1000) NOT NULL,
  `DATA` blob,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DATASTORE_DATA
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for DATASTORE_META
-- ----------------------------
DROP TABLE IF EXISTS `DATASTORE_META`;
CREATE TABLE `DATASTORE_META` (
  `ID` varchar(1000) NOT NULL,
  `LEVEL` int(11) DEFAULT NULL,
  `LASTMOD` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DATASTORE_META
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for NODES
-- ----------------------------
DROP TABLE IF EXISTS `NODES`;
CREATE TABLE `NODES` (
  `ID` varchar(1000) NOT NULL,
  `MODIFIED` bigint(20) DEFAULT NULL,
  `MODCOUNT` bigint(20) DEFAULT NULL,
  `SIZE` bigint(20) DEFAULT NULL,
  `DATA` varchar(16384) DEFAULT NULL,
  `BDATA` blob,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of NODES
-- ----------------------------
BEGIN;
INSERT INTO `NODES` VALUES ('0:/', 1632963835, 2, 170, '{\"_deleted\":{\"r17c343abf6e-0-1\":\"false\"},\"_revisions\":{\"r17c343abf6e-0-1\":\"c\"},\"_id\":\"0:\\/\",\"_modified\":1632963835,\"_modCount\":2,\"_lastRev\":{\"r0-0-1\":\"r17c343abf6e-0-1\"}}', NULL);
COMMIT;

-- ----------------------------
-- Table structure for SETTINGS
-- ----------------------------
DROP TABLE IF EXISTS `SETTINGS`;
CREATE TABLE `SETTINGS` (
  `ID` varchar(1000) NOT NULL,
  `MODIFIED` bigint(20) DEFAULT NULL,
  `MODCOUNT` bigint(20) DEFAULT NULL,
  `SIZE` bigint(20) DEFAULT NULL,
  `DATA` varchar(16384) DEFAULT NULL,
  `BDATA` blob,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SETTINGS
-- ----------------------------
BEGIN;
INSERT INTO `SETTINGS` VALUES ('checkpoint', NULL, 1, 34, '{\"_id\":\"checkpoint\",\"_modCount\":1}', NULL);
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=654 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
BEGIN;
INSERT INTO `file` VALUES (1, '畫江湖之不良人第4季EP11.mp4', 'adee3378ee2380966490263112b0ea80', '/group1/shaoming/视频/畫江湖之不良人第4季EP11.mp4', '2021-09-24 13:38:34', 21, 0);
INSERT INTO `file` VALUES (190, '(完整版)网络工程试题 - 百度文库.docx', '1c91ec9a0455465b851b33c3eadf8c2c', '/group1/shaoming/视频/test/hello/(完整版)网络工程试题 - 百度文库.docx', '2021-10-15 17:08:52', 23, 0);
INSERT INTO `file` VALUES (191, '算法（第四版）中文版(Java描述)【完整版】Robert sedgewick.pdf', '2e7a05fe8c609b8134d9b7662c61de58', '/group1/shaoming/算法（第四版）中文版(Java描述)【完整版】Robert sedgewick.pdf', '2021-11-05 17:45:55', 23, 1);
INSERT INTO `file` VALUES (246, 'pom.xml', '0c562a2cf600aeaa6d9eeca378c79590', '/group1/shaoming/file-management-service-system/pom.xml', '2021-11-08 15:36:14', 23, 0);
INSERT INTO `file` VALUES (247, '.gitignore', '1dbae07635212b610f1bc5e390a97c54', '/group1/shaoming/file-management-service-system/.gitignore', '2021-11-08 15:36:15', 23, 0);
INSERT INTO `file` VALUES (248, 'aspose-words-20.4-c-jdk17.jar', '92450e09c5f16ed5ad8348a93e54bdf4', '/group1/shaoming/file-management-service-system/lib/aspose-words-20.4-c-jdk17.jar', '2021-11-08 15:36:16', 23, 0);
INSERT INTO `file` VALUES (249, 'aspose-cells-20.4 - c.jar', '6065e606a280c6a01fb8901dda9072ec', '/group1/shaoming/file-management-service-system/lib/aspose-cells-20.4 - c.jar', '2021-11-08 15:36:18', 23, 0);
INSERT INTO `file` VALUES (250, 'aspose-slides-20.4-jdk16-c.jar', 'ab575216fc19f24e6e85a56489d69498', '/group1/shaoming/file-management-service-system/lib/aspose-slides-20.4-jdk16-c.jar', '2021-11-08 15:36:19', 23, 0);
INSERT INTO `file` VALUES (251, 'FileManagementServiceApplicationTests.java', '5958c5f251c6d7723a85cbaa00e15d7f', '/group1/shaoming/file-management-service-system/src/test/java/com/graduation/FileManagementServiceApplicationTests.java', '2021-11-08 15:36:20', 23, 0);
INSERT INTO `file` VALUES (252, 'rebel.xml', 'd4f0d7a31ee5f2d29745c16e46283831', '/group1/shaoming/file-management-service-system/src/main/resources/rebel.xml', '2021-11-08 15:36:21', 23, 0);
INSERT INTO `file` VALUES (253, 'file.css', '946438b0f31b672e15e57d38652d35f1', '/group1/shaoming/file-management-service-system/src/main/resources/static/css/file.css', '2021-11-08 15:36:22', 23, 0);
INSERT INTO `file` VALUES (254, 'roboto.css', '688d75e03ff53489d6cf8e19f7841dab', '/group1/shaoming/file-management-service-system/src/main/resources/static/css/roboto.css', '2021-11-08 15:36:22', 23, 0);
INSERT INTO `file` VALUES (255, 'check.css', 'd07897c765f6395083723392878e9162', '/group1/shaoming/file-management-service-system/src/main/resources/static/css/check.css', '2021-11-08 15:36:23', 23, 0);
INSERT INTO `file` VALUES (256, 'login.css', 'e2be7bfaeb56fe07a826ac2ab2919a61', '/group1/shaoming/file-management-service-system/src/main/resources/static/css/login.css', '2021-11-08 15:36:25', 23, 0);
INSERT INTO `file` VALUES (257, 'install.css', '85d622fd54513d3c8e6acfbee408b0ab', '/group1/shaoming/file-management-service-system/src/main/resources/static/css/install.css', '2021-11-08 15:36:27', 23, 0);
INSERT INTO `file` VALUES (258, 'space.css', 'd38378c5ceebe25b8e8f307f3c888f98', '/group1/shaoming/file-management-service-system/src/main/resources/static/css/space.css', '2021-11-08 15:36:29', 23, 0);
INSERT INTO `file` VALUES (259, 'uppy.min.css', '77370765895b83a738f100b779ba0b89', '/group1/shaoming/file-management-service-system/src/main/resources/static/css/uppy.min.css', '2021-11-08 15:36:37', 23, 0);
INSERT INTO `file` VALUES (260, 'style.css', '10de846b6d5ffefd625fe7cc6ed8f8a7', '/group1/shaoming/file-management-service-system/src/main/resources/static/css/style.css', '2021-11-08 15:36:38', 23, 0);
INSERT INTO `file` VALUES (261, 'github-markdown.css', 'b9b3063f8699430f90f01c548d7ba31e', '/group1/shaoming/file-management-service-system/src/main/resources/static/css/github-markdown.css', '2021-11-08 15:36:41', 23, 0);
INSERT INTO `file` VALUES (262, 'sign-style.css', '82cfee5881cdf0475225609c138b43fc', '/group1/shaoming/file-management-service-system/src/main/resources/static/css/sign-style.css', '2021-11-08 15:36:47', 23, 0);
INSERT INTO `file` VALUES (263, 'status.css', '3fdc5b4215945f6aa0483ad0949cf9ac', '/group1/shaoming/file-management-service-system/src/main/resources/static/css/status.css', '2021-11-08 15:36:49', 23, 0);
INSERT INTO `file` VALUES (264, 'share.css', '4b16e40e6aa96c14ba8f201cb1ba4176', '/group1/shaoming/file-management-service-system/src/main/resources/static/css/share.css', '2021-11-08 15:36:50', 23, 0);
INSERT INTO `file` VALUES (265, 'favicon.ico', '37f52ac3a2a149ef3ab1cf3771c326e6', '/group1/shaoming/file-management-service-system/src/main/resources/static/images/favicon.ico', '2021-11-08 15:36:52', 23, 0);
INSERT INTO `file` VALUES (266, 'blue.png', '2dc15043659669ea303139810deb1143', '/group1/shaoming/file-management-service-system/src/main/resources/static/images/blue.png', '2021-11-08 15:36:53', 23, 0);
INSERT INTO `file` VALUES (267, 'user.png', '75d190169a4ca2cbd337a66d9819a272', '/group1/shaoming/file-management-service-system/src/main/resources/static/images/user.png', '2021-11-08 15:36:54', 23, 0);
INSERT INTO `file` VALUES (268, 'logo.png', '015699df6ef294fa0f2e24f909748cd8', '/group1/shaoming/file-management-service-system/src/main/resources/static/images/logo.png', '2021-11-08 15:36:55', 23, 0);
INSERT INTO `file` VALUES (269, 'cloud.png', '01b31e98ab15975173ad808f61cabfb1', '/group1/shaoming/file-management-service-system/src/main/resources/static/images/cloud.png', '2021-11-08 15:36:55', 23, 0);
INSERT INTO `file` VALUES (270, '1.png', '19921f52522b019ff6bdc2cd138af69d', '/group1/shaoming/file-management-service-system/src/main/resources/static/images/users/1.png', '2021-11-08 15:36:57', 23, 0);
INSERT INTO `file` VALUES (271, '0.png', '15de7793f3c5de13ea90b98ea3c3b558', '/group1/shaoming/file-management-service-system/src/main/resources/static/images/users/0.png', '2021-11-08 15:36:59', 23, 0);
INSERT INTO `file` VALUES (272, 'qrcode.js', '7eace1ac45dab3b1a6fa517900a3a781', '/group1/shaoming/file-management-service-system/src/main/resources/static/js/qrcode.js', '2021-11-08 15:37:00', 23, 0);
INSERT INTO `file` VALUES (273, 'kit.js', 'd4eaad4464eb7fd8cf889dad0c7528a6', '/group1/shaoming/file-management-service-system/src/main/resources/static/js/kit.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (274, 'easyplayer.min.js', '5b8a5845042c0b1f0cb4693a3377cd50', '/group1/shaoming/file-management-service-system/src/main/resources/static/js/easyplayer.min.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (275, 'upload.js', '01d580d6f9e51bc1fe89791b7b1ddb89', '/group1/shaoming/file-management-service-system/src/main/resources/static/js/upload.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (276, 'sign.js', '664789c72887e461305c09c2d683bd2b', '/group1/shaoming/file-management-service-system/src/main/resources/static/js/sign.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (277, 'showdown.min.js', 'a79b70fe4db76786f85bb14080f0cef6', '/group1/shaoming/file-management-service-system/src/main/resources/static/js/showdown.min.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (278, 'jquery.min.js', 'b2fd082cc5e45df1136ce05655523263', '/group1/shaoming/file-management-service-system/src/main/resources/static/js/jquery.min.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (279, 'main.js', '9822c1b5ea2ab088896cff2fccbaeb6f', '/group1/shaoming/file-management-service-system/src/main/resources/static/js/main.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (280, 'space.js', 'a64d66a2e12ccc9ad16a2896470c655b', '/group1/shaoming/file-management-service-system/src/main/resources/static/js/space.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (281, 'status.js', '4d04b0c5295a8d0b8e6f856cdfd8a0ca', '/group1/shaoming/file-management-service-system/src/main/resources/static/js/status.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (282, 'uppy.min.js', 'b77682153c2b2a36c4f9f05cfa51108e', '/group1/shaoming/file-management-service-system/src/main/resources/static/js/uppy.min.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (283, 'common.js', 'ec104946dd759e56be10d863ca09c322', '/group1/shaoming/file-management-service-system/src/main/resources/static/js/common.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (284, 'file.js', '13e6afcd86d92aded45fd5f893c9d99b', '/group1/shaoming/file-management-service-system/src/main/resources/static/js/file.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (285, 'repo-upload.js', '928b82b8c4e3bb834934e50748566c26', '/group1/shaoming/file-management-service-system/src/main/resources/static/js/repo-upload.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (286, 'repo.js', '0076e427b3ffbb47748b9928dc0261f4', '/group1/shaoming/file-management-service-system/src/main/resources/static/js/repo.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (287, 'jszip.js', '860465079ec8b7e517b2f7e54c5a3348', '/group1/shaoming/file-management-service-system/src/main/resources/static/js/jszip.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (288, 'share.js', 'e1110b7040a211b81bb62b50a0604f05', '/group1/shaoming/file-management-service-system/src/main/resources/static/js/share.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (289, 'template-web.js', 'e9589f053e1024540021f0461d5a98f5', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/template-web/template-web.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (290, 'jquery.form.min.js', 'c62e21acad68878e71f764c5718a24cb', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/jquery/jquery.form.min.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (291, 'jquery-3.5.1.min.js', '1f05dd46a1943c0907738329906947c1', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/jquery/jquery-3.5.1.min.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (292, 'iconfont.js', '2c1fbe30716b8c0b4318d839deabb909', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/iconfont/iconfont.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (293, 'demo.css', '0010dde491b8c683a091201a64c85c6d', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/iconfont/demo.css', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (294, 'iconfont.woff', 'f20fd98caf011d6c9ef8c4de3927e852', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/iconfont/iconfont.woff', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (295, 'iconfont.eot', '8e68a8e01ec9a09579b97804a8e40452', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/iconfont/iconfont.eot', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (296, 'iconfont.ttf', '4e4b5a9c08f36898c8c9deb8a6059b38', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/iconfont/iconfont.ttf', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (297, 'demo_index.html', '00ea8dcf5189ce3b5ee0bf5fe57d176c', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/iconfont/demo_index.html', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (298, 'iconfont.woff2', '3d4074e59e80632a965d651eb39fc722', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/iconfont/iconfont.woff2', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (299, 'iconfont.svg', 'b771d01b175a43e68c69e71a972d52ba', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/iconfont/iconfont.svg', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (300, 'iconfont.css', '6fcd148a926c0000b35aa8d1a8749f59', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/iconfont/iconfont.css', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (301, 'step.css', '53630827750463d81966fc2e5230399d', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/step-lay/step.css', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (302, 'step.js', '5c06580e96eb962f5f749106c47c9a45', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/step-lay/step.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (303, 'layui.js', 'd494d1087dd04285f70e595e666a8a91', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/layui.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (304, 'layui.mobile.css', '21d44758ab9416e60ed2a46fd2fc0845', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/css/layui.mobile.css', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (305, 'layui.css', 'e324319582745460810ea094e55ce19a', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/css/layui.css', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (306, 'code.css', 'b0ac7354472363408e6073e15bdb0322', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/css/modules/code.css', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (307, 'icon-ext.png', 'b3ebfbc936314f57a9fd0b59a7f15d51', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/css/modules/layer/default/icon-ext.png', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (308, 'icon.png', 'c51afaa5c6c327f9c5525dae5a1aab21', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/css/modules/layer/default/icon.png', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (309, 'loading-2.gif', '62161a44df64504f1c7b9112a9d542ec', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/css/modules/layer/default/loading-2.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (310, 'loading-0.gif', '95dc5f9674f642e69569a7de22c07254', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/css/modules/layer/default/loading-0.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (311, 'loading-1.gif', '22bbea6b4cac2dd3969ff9d66836ea8a', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/css/modules/layer/default/loading-1.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (312, 'layer.css', 'b81cbac7c7eec6d1a5feb9263e843936', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/css/modules/layer/default/layer.css', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (313, 'laydate.css', 'e0a388a8ef933bc760849485c72c7672', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/css/modules/laydate/default/laydate.css', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (314, 'layui.all.js', '57a84f590238998025efc740261c5055', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/layui.all.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (315, '21.gif', '7151d8a9b43d085d99041f274aa4504a', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/21.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (316, '35.gif', '3c1d5c32b94a7dfa7a20b356c52c5a89', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/35.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (317, '34.gif', 'e59a9f61335e63d3054664d927efc8c4', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/34.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (318, '20.gif', 'fdd3d6cb281570767598829546ab3ff4', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/20.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (319, '36.gif', '453adb4973d0a316498fb8f1f6d3cf92', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/36.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (320, '22.gif', '7f45a64f4b8b46894082f75ab5f24bd9', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/22.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (321, '23.gif', '2c73f8a16dd46a41b05c5bc4b11c8bc1', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/23.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (322, '37.gif', '8712b5f51bcac3568121cd0313cde8e4', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/37.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (323, '33.gif', '74e0be4a322e05ee48246536389fb5a6', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/33.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (324, '27.gif', 'd4fe75ad1b5ca727bd3a5988d5902ef4', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/27.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (325, '26.gif', 'b4cb67eb676126a7efc4744739c2462b', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/26.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (326, '32.gif', '8b601a2bfe17c3e8a93d438b026a9ffb', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/32.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (327, '18.gif', '47a00b9e0cb9a7cd5f5a191503c74f47', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/18.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (328, '24.gif', 'a81495b73f5000cfad33729a584f422a', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/24.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (329, '30.gif', '1b82e1f83d21d7709274f6fe2e71c2b5', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/30.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (330, '31.gif', '2fdb649ef5f138ed8832eb2ef81d9b57', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/31.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (331, '25.gif', '3553d4003461169ea14dee30d8ad6c73', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/25.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (332, '19.gif', '6dca447f959769255be6ba6dffc674e3', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/19.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (333, '42.gif', '36f9ad8a7137015f759d25a4f86caf43', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/42.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (334, '56.gif', '8ff930ca081e00457850cc58571a0e32', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/56.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (335, '4.gif', '94af5d47ce23051c1c6eefea12eeb8a0', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/4.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (336, '57.gif', '9a98b39cbe4a22aac64f8f432a97e129', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/57.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (337, '5.gif', 'e41267816277d8c0d1b633a1d7d38dca', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/5.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (338, '43.gif', '7a644a113893f12e32050924786b3856', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/43.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (339, '7.gif', 'fcb4107057a211e8a1bb181ec0daf0f7', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/7.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (340, '55.gif', 'afb2b01a4b4c854446f81400f39ce3ad', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/55.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (341, '41.gif', '77dc8fed1682f1c23bb7773393c3e9bf', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/41.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (342, '69.gif', 'f6fe75ea81bedacfbd5178deeb71e3f8', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/69.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (343, '68.gif', '6e684490bc8a0ab274e651f8ac54691c', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/68.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (344, '40.gif', '8d2875ee3a5d99d57e50866c9131383d', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/40.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (345, '6.gif', '05b29e53a2e29c7d77868f6c76874886', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/6.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (346, '54.gif', 'a7a33a5f21632f0442f5223b502db638', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/54.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (347, '50.gif', '072319c17772763373d692ff170301a9', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/50.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (348, '2.gif', 'e81cf538964df366f06c8bacb2ee0410', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/2.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (349, '44.gif', '51228f3cd3b4a16b97b2d19777b955b6', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/44.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (350, '45.gif', 'ba71ea06b487f35b681d2b595e701b86', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/45.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (351, '51.gif', 'ee9e9f3fd61cae344e3a878e1252b322', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/51.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (352, '3.gif', 'fc509e49d1c9caca95e163ae3110e838', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/3.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (353, '47.gif', '2b5dc83640e6dd32d098a80627f90891', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/47.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (354, '1.gif', 'd8046df724549ce685426e71dcc9d521', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/1.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (355, '53.gif', '8838cef031e6b3e481d9b1740b8a70e9', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/53.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (356, '0.gif', '03611041255c8066116af33062f169ed', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/0.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (357, '52.gif', 'cce527683714693dd99dbc726899921a', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/52.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (358, '46.gif', '79233886362f5c74e698c3bf357a2c65', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/46.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (359, '63.gif', '62651c265d17e392c7125590e29d2a25', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/63.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (360, '62.gif', '16dbe4103200dcb82e4d541bc6e7f3ba', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/62.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (361, '60.gif', '1ced6629f8685b0dd0b2d3aaed085fed', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/60.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (362, '48.gif', 'f803d25a9a292e4abecd1f4ba8b52268', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/48.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (363, '49.gif', 'f27e2a653c468ace1a15e740b225b913', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/49.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (364, '61.gif', 'e30c8eef5b23277e47ba9f0c721a9345', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/61.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (365, '59.gif', '35fd44d37f05a0ba53cae458559257c0', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/59.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (366, '71.gif', 'acfc9d0637a4b61a8d4ce4abf6a49203', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/71.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (367, '65.gif', 'dbe1917a28fb99bc738feae1271770d6', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/65.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (368, '64.gif', 'c4a3dfc08dff6bf013d09ea40f61caec', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/64.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (369, '70.gif', '9b4a51df66ff90381a19c6e089996dbc', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/70.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (370, '58.gif', 'c6d80189083cd101dee7cc316d4cdebe', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/58.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (371, '8.gif', '709a1e400cda36f4d03d5275f0e4805b', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/8.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (372, '66.gif', '35789e3d7741e95955fb8f6f7c3f97f8', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/66.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (373, '67.gif', 'b396ea291b143985ebf353d06edfd188', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/67.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (374, '9.gif', '0a79bbc737cd212b3ef7227ed93e9635', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/9.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (375, '14.gif', 'ff595407a8fee8ceacdfed30ddcc0f9e', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/14.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (376, '28.gif', 'df330a88ddfb50c2f77131bbd99b6468', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/28.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (377, '29.gif', '7ae5318507015446e31454eb0e05802e', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/29.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (378, '15.gif', '6f04c3e5a6571959d316661cb414e824', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/15.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (379, '17.gif', 'facbd60c884f8b6e418c7ebe9940923d', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/17.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (380, '16.gif', 'e8b90cc4ed269626b4e6b5b9c71abbc5', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/16.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (381, '12.gif', 'dfce19261443e9428f59ba5dde9ff3c7', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/12.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (382, '13.gif', '882e0496a7e4b161614262d2f49b3a34', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/13.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (383, '39.gif', '51863a35949d0753134c01d27da17a69', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/39.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (384, '11.gif', '39630e1ca51adb120198e572947ba769', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/11.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (385, '10.gif', '31994be58e63651534d989f59fe62fec', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/10.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (386, '38.gif', 'b66b9a8d3674b7c3edbf4b5e117a40f5', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/images/face/38.gif', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (387, 'mobile.js', 'cbec81656ce0e48e2a18848921c89a39', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/mobile.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (388, 'rate.js', '1e31c0e5e46316fd10435b397289c6c3', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/rate.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (389, 'util.js', '38942ed2e77376d5e78cd6d974753857', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/util.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (390, 'element.js', '415fed7b6bb9c166580570a0d7cbbbe9', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/element.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (391, 'flow.js', '3b7d0473ff548c476228a5031c18330f', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/flow.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (392, 'code.js', '5d37f477dd42c2e1a001cfdce694cfe5', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/code.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (393, 'tree.js', '830ac26d2b22155cbf7c89baacb4f141', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/tree.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (394, 'laydate.js', '2dd34640d68332140b59f0774e0de3f8', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/laydate.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (395, 'upload.js', '67770ab8b1a22c19582fdcb94f52820a', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/upload.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (396, 'colorpicker.js', '16e0e95c6e2967834caa6482be2f5a53', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/colorpicker.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (397, 'table.js', '48c9d383b726e158e51a71386f60b1ab', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/table.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (398, 'carousel.js', '02dab3feb365d2837292f0fe29c628a5', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/carousel.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (399, 'form.js', 'def6c160dddc0e81cd47b844b472cb9c', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/form.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (400, 'transfer.js', '96252c16c50f316b4de8901f10d648fc', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/transfer.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (401, 'slider.js', '45ec8aceee57387cb84feab0a287eb42', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/slider.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (402, 'laypage.js', '83e521d0430a9597567e768276ea3128', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/laypage.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (403, 'layedit.js', 'cd882e5132f2b9bb90f8c68d9a2691f5', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/layedit.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (404, 'layer.js', '66b0c4ded59b0a330422bf85da6b605a', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/layer.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (405, 'jquery.js', 'b44d8d012a080bb41b83eb0b5a9d01c1', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/jquery.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (406, 'laytpl.js', '1d573363d95d57e324161d1a094f489a', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/lay/modules/laytpl.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (407, 'iconfont.woff', 'd90870d45bfc32336bab99bc26e037f0', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/font/iconfont.woff', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (408, 'iconfont.eot', 'a4f8eff6c01aa8471d411cd632afb2f4', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/font/iconfont.eot', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (409, 'iconfont.ttf', '8a5152c9b683087cc84b8e33c4fb3bd4', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/font/iconfont.ttf', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (410, 'iconfont.woff2', 'ca5962d74c4ff1ea228b8b94adba19eb', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/font/iconfont.woff2', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (411, 'iconfont.svg', 'b00482383a720511229804a21ed60b76', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/layui-v2.5.6/layui/font/iconfont.svg', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (412, 'echarts.common.min.js', '69593aecc75dd22efaeeec7667f14004', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/echarts/echarts.common.min.js', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (413, 'font-awesome.css', '979725ec6c1c94fe28db03390dbf3aec', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/css/font-awesome.css', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (414, 'font-awesome.min.css', 'f92e70b947cdc52ed84efce61bd96e5b', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/css/font-awesome.min.css', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (415, 'list.less', '26a89a812d9ab9e4d53ab18e40d52aed', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/less/list.less', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (416, 'stacked.less', 'a5d386dacd4dac38649eb524a055333a', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/less/stacked.less', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (417, 'core.less', 'c90216601bee144d0b0958afb8e27ce8', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/less/core.less', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (418, 'fixed-width.less', 'f04c24f7c84b8c73aacffe9d52231511', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/less/fixed-width.less', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (419, 'variables.less', 'be1ca02c3cc7bca23e885823ff3f0cd1', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/less/variables.less', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (420, 'rotated-flipped.less', '493174b00c9a7b0ae00dad7b7b246d02', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/less/rotated-flipped.less', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (421, 'font-awesome.less', 'd0be36696d111bdf7922e25cb2fdcd1d', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/less/font-awesome.less', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (422, 'icons.less', '71b7231b80dee147ed5272819e20674e', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/less/icons.less', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (423, 'path.less', 'fe9315f67138e026d586c2a6f09fbd13', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/less/path.less', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (424, 'animated.less', 'b74c822311101c471a4b8b3f614861cf', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/less/animated.less', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (425, 'bordered-pulled.less', '76401894dcdc2730e87a7ac20f0c6f95', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/less/bordered-pulled.less', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (426, 'larger.less', '8c2ef770f20d3479b8172b8e1ed017f7', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/less/larger.less', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (427, 'mixins.less', '0cf9fa3a2712b92ba62d8cd0b145d68e', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/less/mixins.less', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (428, 'screen-reader.less', '639e7cd9dc57dcdbb219f90742305f3a', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/less/screen-reader.less', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (429, '_stacked.scss', 'e5d1afc4562b21239f47e860896d1bc9', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/scss/_stacked.scss', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (430, '_variables.scss', 'fc4f27bff83f18293724b6029443f288', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/scss/_variables.scss', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (431, 'font-awesome.scss', '5c42314fc186b9023590c162ae434430', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/scss/font-awesome.scss', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (432, '_rotated-flipped.scss', 'bdb8556ef933e9ace2f187a4443a7433', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/scss/_rotated-flipped.scss', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (433, '_path.scss', 'fa145da0bb261fb98d209e2ca5ddfd8a', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/scss/_path.scss', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (434, '_list.scss', 'f564cc90908a7785774b523d1a49a8eb', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/scss/_list.scss', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (435, '_screen-reader.scss', '345a57d234056083056b0ea667ea431b', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/scss/_screen-reader.scss', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (436, '_larger.scss', '2974d3e5e989b0f9e158e3fff6ebecd4', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/scss/_larger.scss', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (437, '_core.scss', '959c6fd9c8b7c3241ee53080f63a868d', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/scss/_core.scss', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (438, '_mixins.scss', '22e5caccd1d38d6d7ffd5b357d372a19', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/scss/_mixins.scss', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (439, '_animated.scss', 'a376a359c60b27ea91684bf3f8bf069b', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/scss/_animated.scss', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (440, '_icons.scss', '56d30915183d91fe5b46c4156d71f30c', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/scss/_icons.scss', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (441, '_fixed-width.scss', '2bba181a19251545ca8b6e306d549247', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/scss/_fixed-width.scss', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (442, '_bordered-pulled.scss', 'beff73a904ddecd812b3149a1f678222', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/scss/_bordered-pulled.scss', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (443, 'HELP-US-OUT.txt', 'e439daa4b3d34dc4e516e5b4e4266e6c', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/HELP-US-OUT.txt', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (444, 'fontawesome-webfont.svg', '04626c96cb8e486621cfe46a2c51399a', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/fonts/fontawesome-webfont.svg', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (445, 'FontAwesome.otf', 'f03c7a1c81fba94ed8f552f81f29bab8', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/fonts/FontAwesome.otf', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (446, 'fontawesome-webfont.woff2', '691f01ace1ea148e1632d1f893e160a5', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/fonts/fontawesome-webfont.woff2', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (447, 'fontawesome-webfont.ttf', '9f9c5ad2f8b4bf01acd506886ee6a97f', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/fonts/fontawesome-webfont.ttf', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (448, 'fontawesome-webfont.woff', '7eb8974601fe96a169d6997060989d7e', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/fonts/fontawesome-webfont.woff', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (449, 'fontawesome-webfont.eot', '3324d0ff9b0c878c22c071d9025e1331', '/group1/shaoming/file-management-service-system/src/main/resources/static/lib/font-awesome-4.7.0/fonts/fontawesome-webfont.eot', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (450, '500.html', 'f9cfdba4f34e6ce6c16293d449c7be4e', '/group1/shaoming/file-management-service-system/src/main/resources/static/error/500.html', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (451, '404.html', 'a3cc0401d5d28edb73498508f4c1bfd7', '/group1/shaoming/file-management-service-system/src/main/resources/static/error/404.html', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (452, '401.html', '8fa474e6ecaee3b6d390f8247035d9be', '/group1/shaoming/file-management-service-system/src/main/resources/static/error/401.html', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (453, 'install.html', '5187498bcd62d17969b30570371d4372', '/group1/shaoming/file-management-service-system/src/main/resources/templates/install.html', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (454, 'user.html', '3f05cc260b5035ec9e1e67fd99a463a5', '/group1/shaoming/file-management-service-system/src/main/resources/templates/settings/user.html', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (455, 'status.html', 'dc602bb52c3068cb2fe818b2ac1e860c', '/group1/shaoming/file-management-service-system/src/main/resources/templates/status.html', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (456, 'index.html', '2936954ba944335b8ab784cf4e6e7f62', '/group1/shaoming/file-management-service-system/src/main/resources/templates/index.html', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (457, 'inboxes.html', '7dc2d51dcbd4d52361c6d0a81922aea9', '/group1/shaoming/file-management-service-system/src/main/resources/templates/inboxes.html', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (458, 'file.html', '30777a319c541114e4fc0ad0e8aff41f', '/group1/shaoming/file-management-service-system/src/main/resources/templates/file/file.html', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (459, 'upload.html', 'd8d590445c1408cb1d148c1794aa598a', '/group1/shaoming/file-management-service-system/src/main/resources/templates/file/upload.html', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (460, 'login.html', 'bbfde102c64b6aa6dde2861018a79f37', '/group1/shaoming/file-management-service-system/src/main/resources/templates/bak/login.html', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (461, 'manage.html', 'e47cea9c7320d452ecde4bce44582a5c', '/group1/shaoming/file-management-service-system/src/main/resources/templates/manage.html', '2021-11-08 15:41:02', 23, 0);
INSERT INTO `file` VALUES (462, 'sider.html', '0641328a929dba3ba132b3259da85962', '/group1/shaoming/file-management-service-system/src/main/resources/templates/layout/sider.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (463, 'header.html', '581b921e55f173d7d5d406200f115fd9', '/group1/shaoming/file-management-service-system/src/main/resources/templates/layout/header.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (464, 'readMail.html', '189d67138448e1b3b2d09d66c0b78a21', '/group1/shaoming/file-management-service-system/src/main/resources/templates/readMail.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (465, 'mail.html', 'cea9a923ffa3b424a4e94fd8d1b01771', '/group1/shaoming/file-management-service-system/src/main/resources/templates/mail.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (466, 'login.html', '7b3b3e6e46b88836e5937c8cc59d523d', '/group1/shaoming/file-management-service-system/src/main/resources/templates/login.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (467, 'sendboxes.html', '931038ae7b2d6a2e59ec93efc9099a45', '/group1/shaoming/file-management-service-system/src/main/resources/templates/sendboxes.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (468, 'check.html', '3f40d38f830d100da761bfa87de8ccdc', '/group1/shaoming/file-management-service-system/src/main/resources/templates/check.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (469, 'list.html', 'c154ae6a3f2908f5f653e56ff222cc79', '/group1/shaoming/file-management-service-system/src/main/resources/templates/peers/list.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (470, 'add.html', 'c0f21fe44b4f127a69420af03c9e51db', '/group1/shaoming/file-management-service-system/src/main/resources/templates/peers/add.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (471, 'edit.html', '014165acb44c81a172c44e07827affa2', '/group1/shaoming/file-management-service-system/src/main/resources/templates/peers/edit.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (472, 'outmoded.html', '34166f97a6fdf2badb6393e167c0a441', '/group1/shaoming/file-management-service-system/src/main/resources/templates/error/outmoded.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (473, 'checkError.html', '5ef603ca8413578359a0ad5c3cbbb35d', '/group1/shaoming/file-management-service-system/src/main/resources/templates/error/checkError.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (474, 'overdue.html', '4bbbc0795a697c5f8fa75312c24edf7a', '/group1/shaoming/file-management-service-system/src/main/resources/templates/error/overdue.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (475, 'space.html', '2ffd257e2a9969eae12a7f85b349a41a', '/group1/shaoming/file-management-service-system/src/main/resources/templates/space.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (476, 'share.html', 'c8186eb2bd5fc29df96600e542e4f6e7', '/group1/shaoming/file-management-service-system/src/main/resources/templates/share.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (477, 'file.html', '1315822c288d7f1511a8ba6b5dd06e81', '/group1/shaoming/file-management-service-system/src/main/resources/templates/repo/file.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (478, 'gitee-auth.html', '6b131ffccb3371d2621443575a9c8a0c', '/group1/shaoming/file-management-service-system/src/main/resources/templates/repo/gitee-auth.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (479, 'upload.html', '90f7dc827e919d1a761c8c0823b33c24', '/group1/shaoming/file-management-service-system/src/main/resources/templates/repo/upload.html', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (480, 'application.yml', '767fa49e38098239eaee863ce7d6a97d', '/group1/shaoming/file-management-service-system/src/main/resources/application.yml', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (481, 'fileSystem.sql', '1dff11dd4f7a5723189c4d6bf0405e57', '/group1/shaoming/file-management-service-system/src/main/resources/sql/fileSystem.sql', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (482, 'MyMetaObjectHandler.java', '68925d796a1abc7039cb04bc567cd333', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/handler/MyMetaObjectHandler.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (483, 'UnifiedExceptionHandler.java', '1da498adb5bd5c1b1bd6909eaf977b08', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/handler/UnifiedExceptionHandler.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (484, 'FileManagementServiceApplication.java', 'e08eabe56cf05e8058d8c958201aefe3', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/FileManagementServiceApplication.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (485, 'SolrConfig.java', 'c18cf3af8c5d2fd2884a62e78d1baa91', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/config/SolrConfig.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (486, 'WebMvcConfiguration.java', '45fca6e48520f7396e071188a2026cfc', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/config/WebMvcConfiguration.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (487, 'ShiroRealm.java', 'cf0bafb07d52a44b718a6512dff222ce', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/config/ShiroRealm.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (488, 'RedisConfig.java', 'c72aa191d8611e1f0f31ba1c4b84ee90', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/config/RedisConfig.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (489, 'WebConfig.java', 'dfe9500bea9dcac8205adce2deb42d11', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/config/WebConfig.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (490, 'SwaggerConfig.java', 'db35567d5c91fe92d51038fc6cca3c7f', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/config/SwaggerConfig.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (491, 'MybatisPlusConfig.java', '19f73295febca7e47040e0db23ef338c', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/config/MybatisPlusConfig.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (492, 'ShiroConfig.java', '18512854ca65ba0f07b4f5d74040e146', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/config/ShiroConfig.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (493, 'ThreadExecutorConfig.java', '640024d02719a3753321f47d863d9d65', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/config/ThreadExecutorConfig.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (494, 'PeersMapper.java', '4180efa859140f6f626f8f9080e2887b', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/PeersMapper.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (495, 'FileMapper.java', 'e15255885d6810bdf26b561ad9770562', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/FileMapper.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (496, 'UserFileMapper.java', 'b4b39c04905804e1e5672c6e6f29f64b', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/UserFileMapper.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (497, 'PermissionMapper.java', '77373726caa72efa9e07f2b81ecf3d05', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/PermissionMapper.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (498, 'RolePermissionMapper.java', '37b46b960a6b430c5197e4ecfbbdd43c', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/RolePermissionMapper.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (499, 'PermissionMapper.xml', '0c916d6ba5f8360a819f5c5c188abdf8', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/xml/PermissionMapper.xml', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (500, 'UserMapper.xml', '2d550dcad5df6bd9f9274cf355de0dc7', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/xml/UserMapper.xml', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (501, 'MailMapper.xml', 'e0fc715d21eac7cee5007b9e0c08e797', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/xml/MailMapper.xml', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (502, 'ShareMapper.xml', '6cd26d25ea11328155ff570ca0fdb029', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/xml/ShareMapper.xml', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (503, 'PeersMapper.xml', '60d6eeb51ab7cd8d4f9fec0e910b187f', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/xml/PeersMapper.xml', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (504, 'MailSendMapper.xml', 'fa976f9960b44d1b0bbc977359d7139d', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/xml/MailSendMapper.xml', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (505, 'MailReceiveMapper.xml', '17aab368ece3c895a89aff8324779cdd', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/xml/MailReceiveMapper.xml', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (506, 'RolePermissionMapper.xml', '71b5273a07a184b51cde50f6621a7541', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/xml/RolePermissionMapper.xml', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (507, 'UserFileMapper.xml', 'e027b229552007884579136a84dd35a7', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/xml/UserFileMapper.xml', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (508, 'FileMapper.xml', 'a5612fd1336293e1ff7c2956973d8e2a', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/xml/FileMapper.xml', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (509, 'RoleMapper.xml', 'a971684837a1f474dce780fe35a7c5cd', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/xml/RoleMapper.xml', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (510, 'UserRoleMapper.xml', 'c148994560302ba4fe5fcb91756c98f6', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/xml/UserRoleMapper.xml', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (511, 'MailSendMapper.java', '7776ee3315cbe7f08018c4acaa0c33d8', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/MailSendMapper.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (512, 'UserMapper.java', '596ecd3ea0b93915f1377eb0cfe3aaea', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/UserMapper.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (513, 'MailMapper.java', '99bfe1dec6d99145f6bceac51132c734', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/MailMapper.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (514, 'UserRoleMapper.java', 'fc9d9c6afa87f2ef99b366e4af430223', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/UserRoleMapper.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (515, 'RoleMapper.java', 'a11e83b3ba9fc8d58d4b3a5fbd48fa25', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/RoleMapper.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (516, 'MailReceiveMapper.java', '83ee353861b5afa55cc712534772d9d4', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/MailReceiveMapper.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (517, 'ShareMapper.java', '6bbf815beee61054ec675d39cc03b704', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/mapper/ShareMapper.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (518, 'VideoUtils.java', '1e33776be441d321f7d3177da04b3f7c', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/VideoUtils.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (519, 'DocumentConverter.java', '06837f9f4edf851509613185e01c69ab', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/DocumentConverter.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (520, 'AesUtils.java', '05726642016000e4bbb387a36b4f4abc', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/AesUtils.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (521, 'FileUtils.java', '06e6cb35d82b09a31ca82175f1c3e9f6', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/FileUtils.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (522, 'AudioConverter.java', '0498b956f5a006d55ad61935df175f96', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/AudioConverter.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (523, 'Md5Utils.java', '17b61bda2003862c9e0fa8ae926befe9', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/Md5Utils.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (524, 'CommonUtils.java', '26353ce6129ed503831db9f1f138fcde', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/CommonUtils.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (525, 'RedisUtils.java', '251b7b5e0b2de3371c39e3dd58299575', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/RedisUtils.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (526, 'Constant.java', '2c69f7ae26d7f3d01b63c48bf9bb6e18', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/Constant.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (527, 'NetUtils.java', 'b5fad1580de0674aa645f4418d03b6e6', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/NetUtils.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (528, 'DateConverter.java', 'da490379c3b81c01f2f4932d0a430cfb', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/DateConverter.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (529, 'PictureConverter.java', '4a0b9a1d272dfd4519a54c992a17f84e', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/PictureConverter.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (530, 'RegexUtils.java', '6e7370d52be57aebe962e29f03bfefa7', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/RegexUtils.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (531, 'GetFileList.java', '19e2bb8659fe6cf603268fecccfb299b', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/GetFileList.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (532, 'GitUtils.java', 'f48031809af535fb5f2cee5cd915c6aa', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/GitUtils.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (533, 'CodeGenerator.java', 'c424509a8815a7063292946dc0155f8c', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/CodeGenerator.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (534, 'FileSizeConverter.java', '53483c8c2f1a50af3866c7552e31d334', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/FileSizeConverter.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (535, 'TokenUtils.java', 'a7c712ef4bf8d79a874bc06c22060211', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/TokenUtils.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (536, 'YmlUtils.java', 'e57e542fb1c75f8a97b5876632efe48a', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/utils/YmlUtils.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (537, 'MailController.java', '3e0f386bb91385bb7b93cd949e9c59d7', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/MailController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (538, 'CheckAuthController.java', '54403214ee42ad4a2634291d4a79c095', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/CheckAuthController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (539, 'StatusController.java', '73f62efa8fa1611c61cec2547cd24036', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/StatusController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (540, 'PeersController.java', '3dd73bff2747920fcd512dfa89678ce0', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/PeersController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (541, 'MailReceiveController.java', 'a9b001c25e05f8278dc094e0de1fc224', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/MailReceiveController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (542, 'CallBackController.java', 'eff37fcfc8230f148e8556a4297eb889', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/CallBackController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (543, 'IndexController.java', '23c0ca7338ea5fc6dbdaf6ef051c1ce3', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/IndexController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (544, 'ShareController.java', '52d22662b0a0da74c45260aec0c20f58', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/ShareController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (545, 'JcrController.java', '007a8e0bd420f414f1d2b4e39f60bb77', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/JcrController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (546, 'MailSendController.java', '522e486c06496718688d59eff51c32e9', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/MailSendController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (547, 'AuthenticationController.java', 'fe0fd88e18b7b65d7a4149703df6a63f', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/adapter/gitee/AuthenticationController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (548, 'GiteeIndexController.java', '28b774543ea0142ba56c5444e3d0d114', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/adapter/gitee/GiteeIndexController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (549, 'TokenController.java', '33837920ce34b049a3077b526eaf31b6', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/adapter/gitee/TokenController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (550, 'ContentBlobController.java', 'd93c6751c196f59a2c881ce8530dc7c2', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/adapter/gitee/ContentBlobController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (551, 'RepoContentController.java', '87551112f2c12d4dcda63232cc51bc90', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/adapter/gitee/RepoContentController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (552, 'ContentTreeController.java', '9a32d270065fdbef6a3f4c4de592a17f', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/adapter/gitee/ContentTreeController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (553, 'RepoIndexController.java', 'e0518578f08c7f81b60f053a02c27b06', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/adapter/RepoIndexController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (554, 'SettingController.java', '96bb6ae31124b83b599482de929793f0', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/SettingController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (555, 'CheckController.java', '56b4994ad7bb53edff6c5e2499a66b97', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/CheckController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (556, 'InitSystemController.java', '11db169845ef3cc0beca035eed97d458', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/InitSystemController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (557, 'FileController.java', 'd50ac27cb5d3613c743dd0b9122ffe4f', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/FileController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (558, 'PreviewController.java', '2e348e3f15a0d7843bf314c41b57c543', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/PreviewController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (559, 'BaseController.java', 'f2650bc4bf8b5d065f3103e30991a9e0', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/BaseController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (560, 'UploadController.java', '8e162f840ea8db6794ac243aa910ea12', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/UploadController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (561, 'HomeController.java', '883e2abb26a768f4d1f5e496e0230941', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/HomeController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (562, 'TestController.java', '8ee4fa263c225c410c03fb205af7ac6d', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/TestController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (563, 'ShareFileController.java', '3b0089fb93e1af31426f0a34e54a4136', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/ShareFileController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (564, 'ErrorController.java', 'dcf38b85239b52cd7f841d3582fc2424', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/ErrorController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (565, 'SignController.java', 'a809c3f6c6f819398d4fbdcdccced1fe', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/SignController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (566, 'UserController.java', 'a90cc5daa86ce2319a6d5701e317d101', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/controller/UserController.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (567, 'TreeDto.java', '5c6898e5584264e17a8e06a897e34cd4', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/dto/gitee/response/TreeDto.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (568, 'ContentTreeDto.java', '52b02832bb175ab9bb04772dcfbe13e5', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/dto/gitee/response/ContentTreeDto.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (569, 'RepoInfoDto.java', '05e4ee032d668f9964a18c716cff73cf', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/dto/gitee/response/RepoInfoDto.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (570, 'SingleFileResultDto.java', '76e9e2609c394390b11cfc0178d2b506', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/dto/gitee/response/SingleFileResultDto.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (571, 'ContentBlobDto.java', 'b18feb3436883324ec1bc775138b9d89', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/dto/gitee/request/ContentBlobDto.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (572, 'AllRepoDto.java', '98922e86ad82f1e684250a2228ab8339', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/dto/gitee/request/AllRepoDto.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (573, 'OneRepoDto.java', '9f7385e098c6158a52688e44b9ca2517', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/dto/gitee/request/OneRepoDto.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (574, 'PageInfoDto.java', '7344c38dd88da0fdb3ab5aaad849ac8d', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/dto/PageInfoDto.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (575, 'ManageUserVo.java', 'b5f645c55ce6e9134a29328b64883f11', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/ManageUserVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (576, 'AuthTokenVo.java', 'dc0b27886249bbe2b478065614b60b23', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/gitee/AuthTokenVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (577, 'RepoInfoVo.java', '54af3d96944181efed229fefa4523c3b', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/gitee/RepoInfoVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (578, 'FileListInfoVo.java', '26b91c14b5bb9d3a46418d5693a3cb75', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/gitee/FileListInfoVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (579, 'FileInfoVo.java', 'f7c655cf84819eca80d2b9e1ae858488', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/gitee/FileInfoVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (580, 'RepoSimpleInfoVo.java', '6616ffab6ba14a2b7928cba367fa9a98', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/gitee/RepoSimpleInfoVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (581, 'SendResponseVo.java', 'ef5ce7c0aa8fc22f7846756ea70aba63', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/SendResponseVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (582, 'ModifyUserVo.java', '4d5443e4fce3fddea400b544c905ffec', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/ModifyUserVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (583, 'UserOpenFileInfoVo.java', 'ab5e98b76f6cee49bb47c9c2c58441d0', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/UserOpenFileInfoVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (584, 'ListDataResponseVo.java', 'fe9becd9279bcc56d0273ef8a7c1d6ad', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/ListDataResponseVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (585, 'EmailSendVo.java', '4648093dc05d701bff776eedde6cb7a0', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/EmailSendVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (586, 'FileDetailsVo.java', '257e595fc7c59d08a638a8ef2d22d215', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/FileDetailsVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (587, 'IndexConfigInfoVo.java', '95a54e902fba18d6febba2055cc25197', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/IndexConfigInfoVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (588, 'ShareFileLinkVo.java', 'f1d559d84d2622475930da6e9d88b7ff', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/ShareFileLinkVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (589, 'UserSignUpVo.java', '075a30b11d6b501f5a65b5fcda85f1a2', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/UserSignUpVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (590, 'FileResponseVo.java', '25e5c0db19a43f2541560cb4a3954ced', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/FileResponseVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (591, 'EmailReceiveVo.java', 'f6b4849e5cc46ac4d082c060ad353b80', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/EmailReceiveVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (592, 'BigFileInfoVo.java', 'a431d227903699928162c10a5944a28d', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/BigFileInfoVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (593, 'ShareFileVo.java', 'a81c8b27847b90e41b14d8d774d89450', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/ShareFileVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (594, 'FileInfoVo.java', '0137e68be04fcf33a9565db71e26955f', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/FileInfoVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (595, 'OpenFileInfoVo.java', '14bb78f84fbd88ba4c108c6ed75caf2b', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/OpenFileInfoVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (596, 'InstallVo.java', 'd88d3aa5374e0e2e88aebc3fd31bcdd2', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/InstallVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (597, 'ConvertVo.java', 'ac1dbfec0b970ab6527f32953545f9c1', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/ConvertVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (598, 'ApiResultVo.java', '58de83a7deba9d298919f8b616710ec2', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/ApiResultVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (599, 'UserDirStatusVo.java', 'd435b2caaa431a955a5d4e263db62bab', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/UserDirStatusVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (600, 'UploadParamVo.java', 'b404c06ef0daa98936aa115f64e52d53', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/UploadParamVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (601, 'UploadResultVo.java', '62554c5f0a996d38e428f9bdeb94aa5f', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/UploadResultVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (602, 'EmailSendRecordVo.java', 'fa112ff02a4fb216abcfa303a1d9c5f9', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/EmailSendRecordVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (603, 'UserLoginVo.java', '0a37a39f6d1307927f5a79801ac17e0d', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/vo/UserLoginVo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (604, 'Peers.java', 'face1dcf2654954df1b354b246297339', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/pojo/Peers.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (605, 'UserRole.java', '331ca05e6f83b02953f5c8e1fcabecd7', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/pojo/UserRole.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (606, 'SolrFileInfo.java', 'fec882cbdda92e52a18d34aa8c43b45a', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/pojo/gitee/SolrFileInfo.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (607, 'MailReceive.java', '55f073e01c91a75e87999ac18195e061', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/pojo/MailReceive.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (608, 'Mail.java', '66a6bee1f7a10346c00b9f08d96ada71', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/pojo/Mail.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (609, 'User.java', '83a45b54fffd410941f23e69d5e6128f', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/pojo/User.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (610, 'UserFile.java', '44d2f2471ba3137f7bb268ffe1ccc1f1', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/pojo/UserFile.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (611, 'MailSend.java', '7437013c80a607c629d88f2a924ddf81', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/pojo/MailSend.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (612, 'RolePermission.java', 'fecb4326a2f691e9c8f07e24b51a1c3d', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/pojo/RolePermission.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (613, 'File.java', '6b636b46855becda575dd9c4a202a3b4', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/pojo/File.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (614, 'Permission.java', '4c38cffd68e2190b037f3d108def5aec', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/pojo/Permission.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (615, 'Role.java', 'e894ca294da6aa33c226d2a59d29757c', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/pojo/Role.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (616, 'Share.java', 'f3c6b86b35c5850efb84fa19b9fc0ffc', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/model/pojo/Share.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (617, 'JcrUtils.java', 'cf8ec4b8aa0f9bfcfa557c30bad72e6b', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/jcr/utils/JcrUtils.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (618, 'JcrContentTreeDto.java', 'd06226e52ce655746655bc1634586324', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/jcr/model/dto/JcrContentTreeDto.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (619, 'Jcr.java', '29b91ee3c5f69eca73e96118985595fc', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/jcr/api/Jcr.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (620, 'PeersService.java', 'b2d1bb4ef71ee1ce12b4dfe1f1d78103', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/PeersService.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (621, 'FileServiceImpl.java', '2b8bf9a5adfcb02802c392950d69fb40', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/impl/FileServiceImpl.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (622, 'ShareServiceImpl.java', '984579d7598af10cff9c23c8cb7be805', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/impl/ShareServiceImpl.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (623, 'RoleServiceImpl.java', 'b97daf6798a9e8c91f776ae44c3087b6', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/impl/RoleServiceImpl.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (624, 'MailServiceImpl.java', '49a531043bced80c5140fc60c953eb1c', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/impl/MailServiceImpl.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (625, 'IndexServiceImpl.java', '4ac1e0d5e6a94034aa81139f5fd98835', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/impl/IndexServiceImpl.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (626, 'MailSendServiceImpl.java', '80efcc7f3bf7eba5895db54f1af05394', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/impl/MailSendServiceImpl.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (627, 'PermissionServiceImpl.java', 'dcf85b32c8747c35b0ba8b0f71b3def6', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/impl/PermissionServiceImpl.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (628, 'RolePermissionServiceImpl.java', '1663e46e8447f47c09645f8bca0a53b9', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/impl/RolePermissionServiceImpl.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (629, 'UserFileServiceImpl.java', 'ff895b9c2dfdd183d7342a6e160d5753', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/impl/UserFileServiceImpl.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (630, 'UserServiceImpl.java', '18b1d8c28a53853594fcd22f50871767', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/impl/UserServiceImpl.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (631, 'MailReceiveServiceImpl.java', 'fa82d9147a7c6f68b4168306ad34018f', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/impl/MailReceiveServiceImpl.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (632, 'PeersServiceImpl.java', 'ef86f2efc0d4c4622723074c0464a5dd', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/impl/PeersServiceImpl.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (633, 'UserRoleServiceImpl.java', '2d795e4f79ded35c6783d0932406eff9', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/impl/UserRoleServiceImpl.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (634, 'UserService.java', 'f072738b6e984940203a7351de4e81dd', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/UserService.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (635, 'IndexService.java', 'a483b029e6c5958148411badabc24670', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/IndexService.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (636, 'MailService.java', 'a1bf105b625b83abf9612a6149a1a452', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/MailService.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (637, 'FileService.java', 'cb13499c4aa8478f9c99a971c40d4702', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/FileService.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (638, 'RolePermissionService.java', 'f987c1bc5ccf46357aeda0c4eed4e817', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/RolePermissionService.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (639, 'RoleService.java', '45d4016ac24921f9bd09f827f93776fe', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/RoleService.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (640, 'MailSendService.java', '4ec6d3eb66d99d46df929b9223bb12cc', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/MailSendService.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (641, 'UserFileService.java', 'f89aeb6af1511a400f39c3232b2b660c', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/UserFileService.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (642, 'PermissionService.java', 'ce590a677c2a5fa0d3aba06dc637f22e', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/PermissionService.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (643, 'ShareService.java', '813de6cd00a272741a352cb3eaf1439c', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/ShareService.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (644, 'UserRoleService.java', '3153cb82eb5a3b4ad152a10a7d0b3183', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/UserRoleService.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (645, 'MailReceiveService.java', '95f64d2850538f033d382a6bb83b4232', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/service/MailReceiveService.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (646, 'ResponseException.java', 'af1e81d5e82a52c0d4b745da81f96c4a', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/exception/ResponseException.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (647, 'FileDownloadException.java', 'a2f24e1588f09be91944f4c08a98f248', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/exception/FileDownloadException.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (648, 'FileConverterException.java', '12d301e56ba8e12a1fd498ab3f35b2a7', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/exception/FileConverterException.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (649, 'LoginInterceptor.java', '18c26ffe06f14600ba7b2d6d61ea1cb5', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/interceptor/LoginInterceptor.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (650, 'FileDownloadInterceptor.java', 'd1d71f33ab0bc7250dc7f36f3c5daca7', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/interceptor/FileDownloadInterceptor.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (651, 'GiteeAdapter.java', '1762e153eb9682dc2393d4647afd2709', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/repo/adapter/GiteeAdapter.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (652, 'TokenProxy.java', '0201aee0e85bf225bf7092dc8ce9a4e8', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/repo/adapter/TokenProxy.java', '2021-11-08 15:41:03', 23, 0);
INSERT INTO `file` VALUES (653, 'SolrComponent.java', '88c43019ac42485c4a4f89a4b3bc3f1c', '/group1/shaoming/file-management-service-system/src/main/java/com/graduation/repo/solr/SolrComponent.java', '2021-11-08 15:41:03', 23, 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of peers
-- ----------------------------
BEGIN;
INSERT INTO `peers` VALUES (23, 'default', 'group1', 'http://10.60.1.243:8080', '', 50165218017.28, 44796508897.28, 683818352.64, '2021-10-15 09:36:04', NULL);
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
  `remote` int(1) DEFAULT '0' COMMENT '远程标识 0本地 1远程',
  `token` varchar(255) DEFAULT '' COMMENT '用于远程文件的token',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share
-- ----------------------------
BEGIN;
INSERT INTO `share` VALUES (4, 191, '算法（第四版）中文版(Java描述)【完整版】Robert sedgewick.pdf', '/group1/shaoming/算法（第四版）中文版(Java描述)【完整版】Robert sedgewick.pdf', '181.71MB', 'shaoming', '邵明', 1, '2021-11-09 09:34:03', 0, 0, 0, '');
INSERT INTO `share` VALUES (20, NULL, 'README.md', '/shaoming/Test/README.md', '928B', 'shaoming', '邵明', 1, '2021-11-09 15:17:04', 1, 0, 1, '380184ezsa293pr6e3w7d09dcb5aa18e');
INSERT INTO `share` VALUES (22, NULL, '(完整版)网络工程试题 - 百度文库.pdf', '/shaoming/Test/src/(完整版)网络工程试题 - 百度文库.pdf', '1.12MB', 'shaoming', '邵明', 1, '2021-11-09 15:19:23', 0, 0, 1, '380184ezsa293pr6e3w7d09dcb5aa18e');
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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (39, 'shaoming', '邵明', '913b74b21ac6a286e5866d0540ba0d9e', 1, 18, 'a18477019945@gmail.com', 23, 'd69f23cb008340c4ab9d2515e87d229a', 5368709120, 4684988294, '2021-10-15 09:36:20', NULL, '2021-11-09 16:56:04', 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=654 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_file
-- ----------------------------
BEGIN;
INSERT INTO `user_file` VALUES (1, 35, 1);
INSERT INTO `user_file` VALUES (190, 39, 190);
INSERT INTO `user_file` VALUES (191, 39, 191);
INSERT INTO `user_file` VALUES (246, 39, 246);
INSERT INTO `user_file` VALUES (247, 39, 247);
INSERT INTO `user_file` VALUES (248, 39, 248);
INSERT INTO `user_file` VALUES (249, 39, 249);
INSERT INTO `user_file` VALUES (250, 39, 250);
INSERT INTO `user_file` VALUES (251, 39, 251);
INSERT INTO `user_file` VALUES (252, 39, 252);
INSERT INTO `user_file` VALUES (253, 39, 253);
INSERT INTO `user_file` VALUES (254, 39, 254);
INSERT INTO `user_file` VALUES (255, 39, 255);
INSERT INTO `user_file` VALUES (256, 39, 256);
INSERT INTO `user_file` VALUES (257, 39, 257);
INSERT INTO `user_file` VALUES (258, 39, 258);
INSERT INTO `user_file` VALUES (259, 39, 259);
INSERT INTO `user_file` VALUES (260, 39, 260);
INSERT INTO `user_file` VALUES (261, 39, 261);
INSERT INTO `user_file` VALUES (262, 39, 262);
INSERT INTO `user_file` VALUES (263, 39, 263);
INSERT INTO `user_file` VALUES (264, 39, 264);
INSERT INTO `user_file` VALUES (265, 39, 265);
INSERT INTO `user_file` VALUES (266, 39, 266);
INSERT INTO `user_file` VALUES (267, 39, 267);
INSERT INTO `user_file` VALUES (268, 39, 268);
INSERT INTO `user_file` VALUES (269, 39, 269);
INSERT INTO `user_file` VALUES (270, 39, 270);
INSERT INTO `user_file` VALUES (271, 39, 271);
INSERT INTO `user_file` VALUES (272, 39, 272);
INSERT INTO `user_file` VALUES (273, 39, 273);
INSERT INTO `user_file` VALUES (274, 39, 274);
INSERT INTO `user_file` VALUES (275, 39, 275);
INSERT INTO `user_file` VALUES (276, 39, 276);
INSERT INTO `user_file` VALUES (277, 39, 277);
INSERT INTO `user_file` VALUES (278, 39, 278);
INSERT INTO `user_file` VALUES (279, 39, 279);
INSERT INTO `user_file` VALUES (280, 39, 280);
INSERT INTO `user_file` VALUES (281, 39, 281);
INSERT INTO `user_file` VALUES (282, 39, 282);
INSERT INTO `user_file` VALUES (283, 39, 283);
INSERT INTO `user_file` VALUES (284, 39, 284);
INSERT INTO `user_file` VALUES (285, 39, 285);
INSERT INTO `user_file` VALUES (286, 39, 286);
INSERT INTO `user_file` VALUES (287, 39, 287);
INSERT INTO `user_file` VALUES (288, 39, 288);
INSERT INTO `user_file` VALUES (289, 39, 289);
INSERT INTO `user_file` VALUES (290, 39, 290);
INSERT INTO `user_file` VALUES (291, 39, 291);
INSERT INTO `user_file` VALUES (292, 39, 292);
INSERT INTO `user_file` VALUES (293, 39, 293);
INSERT INTO `user_file` VALUES (294, 39, 294);
INSERT INTO `user_file` VALUES (295, 39, 295);
INSERT INTO `user_file` VALUES (296, 39, 296);
INSERT INTO `user_file` VALUES (297, 39, 297);
INSERT INTO `user_file` VALUES (298, 39, 298);
INSERT INTO `user_file` VALUES (299, 39, 299);
INSERT INTO `user_file` VALUES (300, 39, 300);
INSERT INTO `user_file` VALUES (301, 39, 301);
INSERT INTO `user_file` VALUES (302, 39, 302);
INSERT INTO `user_file` VALUES (303, 39, 303);
INSERT INTO `user_file` VALUES (304, 39, 304);
INSERT INTO `user_file` VALUES (305, 39, 305);
INSERT INTO `user_file` VALUES (306, 39, 306);
INSERT INTO `user_file` VALUES (307, 39, 307);
INSERT INTO `user_file` VALUES (308, 39, 308);
INSERT INTO `user_file` VALUES (309, 39, 309);
INSERT INTO `user_file` VALUES (310, 39, 310);
INSERT INTO `user_file` VALUES (311, 39, 311);
INSERT INTO `user_file` VALUES (312, 39, 312);
INSERT INTO `user_file` VALUES (313, 39, 313);
INSERT INTO `user_file` VALUES (314, 39, 314);
INSERT INTO `user_file` VALUES (315, 39, 315);
INSERT INTO `user_file` VALUES (316, 39, 316);
INSERT INTO `user_file` VALUES (317, 39, 317);
INSERT INTO `user_file` VALUES (318, 39, 318);
INSERT INTO `user_file` VALUES (319, 39, 319);
INSERT INTO `user_file` VALUES (320, 39, 320);
INSERT INTO `user_file` VALUES (321, 39, 321);
INSERT INTO `user_file` VALUES (322, 39, 322);
INSERT INTO `user_file` VALUES (323, 39, 323);
INSERT INTO `user_file` VALUES (324, 39, 324);
INSERT INTO `user_file` VALUES (325, 39, 325);
INSERT INTO `user_file` VALUES (326, 39, 326);
INSERT INTO `user_file` VALUES (327, 39, 327);
INSERT INTO `user_file` VALUES (328, 39, 328);
INSERT INTO `user_file` VALUES (329, 39, 329);
INSERT INTO `user_file` VALUES (330, 39, 330);
INSERT INTO `user_file` VALUES (331, 39, 331);
INSERT INTO `user_file` VALUES (332, 39, 332);
INSERT INTO `user_file` VALUES (333, 39, 333);
INSERT INTO `user_file` VALUES (334, 39, 334);
INSERT INTO `user_file` VALUES (335, 39, 335);
INSERT INTO `user_file` VALUES (336, 39, 336);
INSERT INTO `user_file` VALUES (337, 39, 337);
INSERT INTO `user_file` VALUES (338, 39, 338);
INSERT INTO `user_file` VALUES (339, 39, 339);
INSERT INTO `user_file` VALUES (340, 39, 340);
INSERT INTO `user_file` VALUES (341, 39, 341);
INSERT INTO `user_file` VALUES (342, 39, 342);
INSERT INTO `user_file` VALUES (343, 39, 343);
INSERT INTO `user_file` VALUES (344, 39, 344);
INSERT INTO `user_file` VALUES (345, 39, 345);
INSERT INTO `user_file` VALUES (346, 39, 346);
INSERT INTO `user_file` VALUES (347, 39, 347);
INSERT INTO `user_file` VALUES (348, 39, 348);
INSERT INTO `user_file` VALUES (349, 39, 349);
INSERT INTO `user_file` VALUES (350, 39, 350);
INSERT INTO `user_file` VALUES (351, 39, 351);
INSERT INTO `user_file` VALUES (352, 39, 352);
INSERT INTO `user_file` VALUES (353, 39, 353);
INSERT INTO `user_file` VALUES (354, 39, 354);
INSERT INTO `user_file` VALUES (355, 39, 355);
INSERT INTO `user_file` VALUES (356, 39, 356);
INSERT INTO `user_file` VALUES (357, 39, 357);
INSERT INTO `user_file` VALUES (358, 39, 358);
INSERT INTO `user_file` VALUES (359, 39, 359);
INSERT INTO `user_file` VALUES (360, 39, 360);
INSERT INTO `user_file` VALUES (361, 39, 361);
INSERT INTO `user_file` VALUES (362, 39, 362);
INSERT INTO `user_file` VALUES (363, 39, 363);
INSERT INTO `user_file` VALUES (364, 39, 364);
INSERT INTO `user_file` VALUES (365, 39, 365);
INSERT INTO `user_file` VALUES (366, 39, 366);
INSERT INTO `user_file` VALUES (367, 39, 367);
INSERT INTO `user_file` VALUES (368, 39, 368);
INSERT INTO `user_file` VALUES (369, 39, 369);
INSERT INTO `user_file` VALUES (370, 39, 370);
INSERT INTO `user_file` VALUES (371, 39, 371);
INSERT INTO `user_file` VALUES (372, 39, 372);
INSERT INTO `user_file` VALUES (373, 39, 373);
INSERT INTO `user_file` VALUES (374, 39, 374);
INSERT INTO `user_file` VALUES (375, 39, 375);
INSERT INTO `user_file` VALUES (376, 39, 376);
INSERT INTO `user_file` VALUES (377, 39, 377);
INSERT INTO `user_file` VALUES (378, 39, 378);
INSERT INTO `user_file` VALUES (379, 39, 379);
INSERT INTO `user_file` VALUES (380, 39, 380);
INSERT INTO `user_file` VALUES (381, 39, 381);
INSERT INTO `user_file` VALUES (382, 39, 382);
INSERT INTO `user_file` VALUES (383, 39, 383);
INSERT INTO `user_file` VALUES (384, 39, 384);
INSERT INTO `user_file` VALUES (385, 39, 385);
INSERT INTO `user_file` VALUES (386, 39, 386);
INSERT INTO `user_file` VALUES (387, 39, 387);
INSERT INTO `user_file` VALUES (388, 39, 388);
INSERT INTO `user_file` VALUES (389, 39, 389);
INSERT INTO `user_file` VALUES (390, 39, 390);
INSERT INTO `user_file` VALUES (391, 39, 391);
INSERT INTO `user_file` VALUES (392, 39, 392);
INSERT INTO `user_file` VALUES (393, 39, 393);
INSERT INTO `user_file` VALUES (394, 39, 394);
INSERT INTO `user_file` VALUES (395, 39, 395);
INSERT INTO `user_file` VALUES (396, 39, 396);
INSERT INTO `user_file` VALUES (397, 39, 397);
INSERT INTO `user_file` VALUES (398, 39, 398);
INSERT INTO `user_file` VALUES (399, 39, 399);
INSERT INTO `user_file` VALUES (400, 39, 400);
INSERT INTO `user_file` VALUES (401, 39, 401);
INSERT INTO `user_file` VALUES (402, 39, 402);
INSERT INTO `user_file` VALUES (403, 39, 403);
INSERT INTO `user_file` VALUES (404, 39, 404);
INSERT INTO `user_file` VALUES (405, 39, 405);
INSERT INTO `user_file` VALUES (406, 39, 406);
INSERT INTO `user_file` VALUES (407, 39, 407);
INSERT INTO `user_file` VALUES (408, 39, 408);
INSERT INTO `user_file` VALUES (409, 39, 409);
INSERT INTO `user_file` VALUES (410, 39, 410);
INSERT INTO `user_file` VALUES (411, 39, 411);
INSERT INTO `user_file` VALUES (412, 39, 412);
INSERT INTO `user_file` VALUES (413, 39, 413);
INSERT INTO `user_file` VALUES (414, 39, 414);
INSERT INTO `user_file` VALUES (415, 39, 415);
INSERT INTO `user_file` VALUES (416, 39, 416);
INSERT INTO `user_file` VALUES (417, 39, 417);
INSERT INTO `user_file` VALUES (418, 39, 418);
INSERT INTO `user_file` VALUES (419, 39, 419);
INSERT INTO `user_file` VALUES (420, 39, 420);
INSERT INTO `user_file` VALUES (421, 39, 421);
INSERT INTO `user_file` VALUES (422, 39, 422);
INSERT INTO `user_file` VALUES (423, 39, 423);
INSERT INTO `user_file` VALUES (424, 39, 424);
INSERT INTO `user_file` VALUES (425, 39, 425);
INSERT INTO `user_file` VALUES (426, 39, 426);
INSERT INTO `user_file` VALUES (427, 39, 427);
INSERT INTO `user_file` VALUES (428, 39, 428);
INSERT INTO `user_file` VALUES (429, 39, 429);
INSERT INTO `user_file` VALUES (430, 39, 430);
INSERT INTO `user_file` VALUES (431, 39, 431);
INSERT INTO `user_file` VALUES (432, 39, 432);
INSERT INTO `user_file` VALUES (433, 39, 433);
INSERT INTO `user_file` VALUES (434, 39, 434);
INSERT INTO `user_file` VALUES (435, 39, 435);
INSERT INTO `user_file` VALUES (436, 39, 436);
INSERT INTO `user_file` VALUES (437, 39, 437);
INSERT INTO `user_file` VALUES (438, 39, 438);
INSERT INTO `user_file` VALUES (439, 39, 439);
INSERT INTO `user_file` VALUES (440, 39, 440);
INSERT INTO `user_file` VALUES (441, 39, 441);
INSERT INTO `user_file` VALUES (442, 39, 442);
INSERT INTO `user_file` VALUES (443, 39, 443);
INSERT INTO `user_file` VALUES (444, 39, 444);
INSERT INTO `user_file` VALUES (445, 39, 445);
INSERT INTO `user_file` VALUES (446, 39, 446);
INSERT INTO `user_file` VALUES (447, 39, 447);
INSERT INTO `user_file` VALUES (448, 39, 448);
INSERT INTO `user_file` VALUES (449, 39, 449);
INSERT INTO `user_file` VALUES (450, 39, 450);
INSERT INTO `user_file` VALUES (451, 39, 451);
INSERT INTO `user_file` VALUES (452, 39, 452);
INSERT INTO `user_file` VALUES (453, 39, 453);
INSERT INTO `user_file` VALUES (454, 39, 454);
INSERT INTO `user_file` VALUES (455, 39, 455);
INSERT INTO `user_file` VALUES (456, 39, 456);
INSERT INTO `user_file` VALUES (457, 39, 457);
INSERT INTO `user_file` VALUES (458, 39, 458);
INSERT INTO `user_file` VALUES (459, 39, 459);
INSERT INTO `user_file` VALUES (460, 39, 460);
INSERT INTO `user_file` VALUES (461, 39, 461);
INSERT INTO `user_file` VALUES (462, 39, 462);
INSERT INTO `user_file` VALUES (463, 39, 463);
INSERT INTO `user_file` VALUES (464, 39, 464);
INSERT INTO `user_file` VALUES (465, 39, 465);
INSERT INTO `user_file` VALUES (466, 39, 466);
INSERT INTO `user_file` VALUES (467, 39, 467);
INSERT INTO `user_file` VALUES (468, 39, 468);
INSERT INTO `user_file` VALUES (469, 39, 469);
INSERT INTO `user_file` VALUES (470, 39, 470);
INSERT INTO `user_file` VALUES (471, 39, 471);
INSERT INTO `user_file` VALUES (472, 39, 472);
INSERT INTO `user_file` VALUES (473, 39, 473);
INSERT INTO `user_file` VALUES (474, 39, 474);
INSERT INTO `user_file` VALUES (475, 39, 475);
INSERT INTO `user_file` VALUES (476, 39, 476);
INSERT INTO `user_file` VALUES (477, 39, 477);
INSERT INTO `user_file` VALUES (478, 39, 478);
INSERT INTO `user_file` VALUES (479, 39, 479);
INSERT INTO `user_file` VALUES (480, 39, 480);
INSERT INTO `user_file` VALUES (481, 39, 481);
INSERT INTO `user_file` VALUES (482, 39, 482);
INSERT INTO `user_file` VALUES (483, 39, 483);
INSERT INTO `user_file` VALUES (484, 39, 484);
INSERT INTO `user_file` VALUES (485, 39, 485);
INSERT INTO `user_file` VALUES (486, 39, 486);
INSERT INTO `user_file` VALUES (487, 39, 487);
INSERT INTO `user_file` VALUES (488, 39, 488);
INSERT INTO `user_file` VALUES (489, 39, 489);
INSERT INTO `user_file` VALUES (490, 39, 490);
INSERT INTO `user_file` VALUES (491, 39, 491);
INSERT INTO `user_file` VALUES (492, 39, 492);
INSERT INTO `user_file` VALUES (493, 39, 493);
INSERT INTO `user_file` VALUES (494, 39, 494);
INSERT INTO `user_file` VALUES (495, 39, 495);
INSERT INTO `user_file` VALUES (496, 39, 496);
INSERT INTO `user_file` VALUES (497, 39, 497);
INSERT INTO `user_file` VALUES (498, 39, 498);
INSERT INTO `user_file` VALUES (499, 39, 499);
INSERT INTO `user_file` VALUES (500, 39, 500);
INSERT INTO `user_file` VALUES (501, 39, 501);
INSERT INTO `user_file` VALUES (502, 39, 502);
INSERT INTO `user_file` VALUES (503, 39, 503);
INSERT INTO `user_file` VALUES (504, 39, 504);
INSERT INTO `user_file` VALUES (505, 39, 505);
INSERT INTO `user_file` VALUES (506, 39, 506);
INSERT INTO `user_file` VALUES (507, 39, 507);
INSERT INTO `user_file` VALUES (508, 39, 508);
INSERT INTO `user_file` VALUES (509, 39, 509);
INSERT INTO `user_file` VALUES (510, 39, 510);
INSERT INTO `user_file` VALUES (511, 39, 511);
INSERT INTO `user_file` VALUES (512, 39, 512);
INSERT INTO `user_file` VALUES (513, 39, 513);
INSERT INTO `user_file` VALUES (514, 39, 514);
INSERT INTO `user_file` VALUES (515, 39, 515);
INSERT INTO `user_file` VALUES (516, 39, 516);
INSERT INTO `user_file` VALUES (517, 39, 517);
INSERT INTO `user_file` VALUES (518, 39, 518);
INSERT INTO `user_file` VALUES (519, 39, 519);
INSERT INTO `user_file` VALUES (520, 39, 520);
INSERT INTO `user_file` VALUES (521, 39, 521);
INSERT INTO `user_file` VALUES (522, 39, 522);
INSERT INTO `user_file` VALUES (523, 39, 523);
INSERT INTO `user_file` VALUES (524, 39, 524);
INSERT INTO `user_file` VALUES (525, 39, 525);
INSERT INTO `user_file` VALUES (526, 39, 526);
INSERT INTO `user_file` VALUES (527, 39, 527);
INSERT INTO `user_file` VALUES (528, 39, 528);
INSERT INTO `user_file` VALUES (529, 39, 529);
INSERT INTO `user_file` VALUES (530, 39, 530);
INSERT INTO `user_file` VALUES (531, 39, 531);
INSERT INTO `user_file` VALUES (532, 39, 532);
INSERT INTO `user_file` VALUES (533, 39, 533);
INSERT INTO `user_file` VALUES (534, 39, 534);
INSERT INTO `user_file` VALUES (535, 39, 535);
INSERT INTO `user_file` VALUES (536, 39, 536);
INSERT INTO `user_file` VALUES (537, 39, 537);
INSERT INTO `user_file` VALUES (538, 39, 538);
INSERT INTO `user_file` VALUES (539, 39, 539);
INSERT INTO `user_file` VALUES (540, 39, 540);
INSERT INTO `user_file` VALUES (541, 39, 541);
INSERT INTO `user_file` VALUES (542, 39, 542);
INSERT INTO `user_file` VALUES (543, 39, 543);
INSERT INTO `user_file` VALUES (544, 39, 544);
INSERT INTO `user_file` VALUES (545, 39, 545);
INSERT INTO `user_file` VALUES (546, 39, 546);
INSERT INTO `user_file` VALUES (547, 39, 547);
INSERT INTO `user_file` VALUES (548, 39, 548);
INSERT INTO `user_file` VALUES (549, 39, 549);
INSERT INTO `user_file` VALUES (550, 39, 550);
INSERT INTO `user_file` VALUES (551, 39, 551);
INSERT INTO `user_file` VALUES (552, 39, 552);
INSERT INTO `user_file` VALUES (553, 39, 553);
INSERT INTO `user_file` VALUES (554, 39, 554);
INSERT INTO `user_file` VALUES (555, 39, 555);
INSERT INTO `user_file` VALUES (556, 39, 556);
INSERT INTO `user_file` VALUES (557, 39, 557);
INSERT INTO `user_file` VALUES (558, 39, 558);
INSERT INTO `user_file` VALUES (559, 39, 559);
INSERT INTO `user_file` VALUES (560, 39, 560);
INSERT INTO `user_file` VALUES (561, 39, 561);
INSERT INTO `user_file` VALUES (562, 39, 562);
INSERT INTO `user_file` VALUES (563, 39, 563);
INSERT INTO `user_file` VALUES (564, 39, 564);
INSERT INTO `user_file` VALUES (565, 39, 565);
INSERT INTO `user_file` VALUES (566, 39, 566);
INSERT INTO `user_file` VALUES (567, 39, 567);
INSERT INTO `user_file` VALUES (568, 39, 568);
INSERT INTO `user_file` VALUES (569, 39, 569);
INSERT INTO `user_file` VALUES (570, 39, 570);
INSERT INTO `user_file` VALUES (571, 39, 571);
INSERT INTO `user_file` VALUES (572, 39, 572);
INSERT INTO `user_file` VALUES (573, 39, 573);
INSERT INTO `user_file` VALUES (574, 39, 574);
INSERT INTO `user_file` VALUES (575, 39, 575);
INSERT INTO `user_file` VALUES (576, 39, 576);
INSERT INTO `user_file` VALUES (577, 39, 577);
INSERT INTO `user_file` VALUES (578, 39, 578);
INSERT INTO `user_file` VALUES (579, 39, 579);
INSERT INTO `user_file` VALUES (580, 39, 580);
INSERT INTO `user_file` VALUES (581, 39, 581);
INSERT INTO `user_file` VALUES (582, 39, 582);
INSERT INTO `user_file` VALUES (583, 39, 583);
INSERT INTO `user_file` VALUES (584, 39, 584);
INSERT INTO `user_file` VALUES (585, 39, 585);
INSERT INTO `user_file` VALUES (586, 39, 586);
INSERT INTO `user_file` VALUES (587, 39, 587);
INSERT INTO `user_file` VALUES (588, 39, 588);
INSERT INTO `user_file` VALUES (589, 39, 589);
INSERT INTO `user_file` VALUES (590, 39, 590);
INSERT INTO `user_file` VALUES (591, 39, 591);
INSERT INTO `user_file` VALUES (592, 39, 592);
INSERT INTO `user_file` VALUES (593, 39, 593);
INSERT INTO `user_file` VALUES (594, 39, 594);
INSERT INTO `user_file` VALUES (595, 39, 595);
INSERT INTO `user_file` VALUES (596, 39, 596);
INSERT INTO `user_file` VALUES (597, 39, 597);
INSERT INTO `user_file` VALUES (598, 39, 598);
INSERT INTO `user_file` VALUES (599, 39, 599);
INSERT INTO `user_file` VALUES (600, 39, 600);
INSERT INTO `user_file` VALUES (601, 39, 601);
INSERT INTO `user_file` VALUES (602, 39, 602);
INSERT INTO `user_file` VALUES (603, 39, 603);
INSERT INTO `user_file` VALUES (604, 39, 604);
INSERT INTO `user_file` VALUES (605, 39, 605);
INSERT INTO `user_file` VALUES (606, 39, 606);
INSERT INTO `user_file` VALUES (607, 39, 607);
INSERT INTO `user_file` VALUES (608, 39, 608);
INSERT INTO `user_file` VALUES (609, 39, 609);
INSERT INTO `user_file` VALUES (610, 39, 610);
INSERT INTO `user_file` VALUES (611, 39, 611);
INSERT INTO `user_file` VALUES (612, 39, 612);
INSERT INTO `user_file` VALUES (613, 39, 613);
INSERT INTO `user_file` VALUES (614, 39, 614);
INSERT INTO `user_file` VALUES (615, 39, 615);
INSERT INTO `user_file` VALUES (616, 39, 616);
INSERT INTO `user_file` VALUES (617, 39, 617);
INSERT INTO `user_file` VALUES (618, 39, 618);
INSERT INTO `user_file` VALUES (619, 39, 619);
INSERT INTO `user_file` VALUES (620, 39, 620);
INSERT INTO `user_file` VALUES (621, 39, 621);
INSERT INTO `user_file` VALUES (622, 39, 622);
INSERT INTO `user_file` VALUES (623, 39, 623);
INSERT INTO `user_file` VALUES (624, 39, 624);
INSERT INTO `user_file` VALUES (625, 39, 625);
INSERT INTO `user_file` VALUES (626, 39, 626);
INSERT INTO `user_file` VALUES (627, 39, 627);
INSERT INTO `user_file` VALUES (628, 39, 628);
INSERT INTO `user_file` VALUES (629, 39, 629);
INSERT INTO `user_file` VALUES (630, 39, 630);
INSERT INTO `user_file` VALUES (631, 39, 631);
INSERT INTO `user_file` VALUES (632, 39, 632);
INSERT INTO `user_file` VALUES (633, 39, 633);
INSERT INTO `user_file` VALUES (634, 39, 634);
INSERT INTO `user_file` VALUES (635, 39, 635);
INSERT INTO `user_file` VALUES (636, 39, 636);
INSERT INTO `user_file` VALUES (637, 39, 637);
INSERT INTO `user_file` VALUES (638, 39, 638);
INSERT INTO `user_file` VALUES (639, 39, 639);
INSERT INTO `user_file` VALUES (640, 39, 640);
INSERT INTO `user_file` VALUES (641, 39, 641);
INSERT INTO `user_file` VALUES (642, 39, 642);
INSERT INTO `user_file` VALUES (643, 39, 643);
INSERT INTO `user_file` VALUES (644, 39, 644);
INSERT INTO `user_file` VALUES (645, 39, 645);
INSERT INTO `user_file` VALUES (646, 39, 646);
INSERT INTO `user_file` VALUES (647, 39, 647);
INSERT INTO `user_file` VALUES (648, 39, 648);
INSERT INTO `user_file` VALUES (649, 39, 649);
INSERT INTO `user_file` VALUES (650, 39, 650);
INSERT INTO `user_file` VALUES (651, 39, 651);
INSERT INTO `user_file` VALUES (652, 39, 652);
INSERT INTO `user_file` VALUES (653, 39, 653);
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (35, 39, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
