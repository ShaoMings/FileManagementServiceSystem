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

 Date: 18/09/2021 20:46:20
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
) ENGINE=InnoDB AUTO_INCREMENT=753 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
BEGIN;
INSERT INTO `file` VALUES (740, '断了的弦 - 周杰伦.mp3', NULL, '/group1/shaoming/断了的弦 - 周杰伦.mp3', NULL, 17, 1);
INSERT INTO `file` VALUES (741, '稻香 - 周杰伦.mp3', NULL, '/group1/shaoming/稻香 - 周杰伦.mp3', NULL, 17, 1);
INSERT INTO `file` VALUES (742, '带你去旅行 - 校长（张驰）.mp3', NULL, '/group1/shaoming/带你去旅行 - 校长（张驰）.mp3', NULL, 17, 1);
INSERT INTO `file` VALUES (743, '曾经的你 - 许巍.mp3', NULL, '/group1/shaoming/曾经的你 - 许巍.mp3', NULL, 17, 1);
INSERT INTO `file` VALUES (744, '丑八怪 - 薛之谦.mp3', NULL, '/group1/shaoming/丑八怪 - 薛之谦.mp3', NULL, 17, 1);
INSERT INTO `file` VALUES (745, '安和桥 - 宋冬野.mp3', NULL, '/group1/shaoming/安和桥 - 宋冬野.mp3', NULL, 17, 1);
INSERT INTO `file` VALUES (746, '畫江湖之不良人第4季EP11.mp4', NULL, '/group1/cs-05/畫江湖之不良人第4季EP11.mp4', NULL, 17, 1);
INSERT INTO `file` VALUES (751, 'derby.log', NULL, '/group1/cs-05/derby.log', '2021-09-18 17:29:45', 17, 0);
INSERT INTO `file` VALUES (752, 'JavaWeb-Servlet.md', NULL, '/group1/cs-05/JavaWeb-Servlet.md', '2021-09-18 18:01:12', 17, 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail
-- ----------------------------
BEGIN;
INSERT INTO `mail` VALUES (13, '你好世界!', 'Hello World！<img src=\"http://10.60.2.0:8081/static/lib/layui-v2.5.6/layui/images/face/47.gif\" alt=\"[心]\">');
INSERT INTO `mail` VALUES (14, '测试信息', '<p><span>噫吁嚱，危乎高哉！</span><span>蜀道</span><span>之难，难于上青天！蚕丛及鱼凫，开国何茫然！尔来四万八千岁，不与秦塞通人烟。西当太白有鸟道，可以横绝峨眉巅。地崩山摧壮士死，然后天梯石栈相钩连。上有六龙回日之高标，下有冲波逆折之回川。黄鹤之飞尚不得过，猿猱欲度愁攀援。青泥何盘盘，百步九折萦岩峦。扪参历井仰胁息，以手抚膺坐长叹。</span><br><span>问君西游何时还？畏途巉岩不可攀。但见悲鸟号古木，雄飞雌从绕林间。又闻子规啼夜月，愁空山。</span><span>蜀道</span><span>之难，难于上青天，使人听此凋朱颜！连峰去天不盈尺，枯松倒挂倚绝壁。飞湍瀑流争喧豗，砯崖转石万壑雷。其险也如此，嗟尔远道之人胡为乎来哉！</span><br><p><span>剑阁峥嵘而崔嵬，一夫当关，万夫莫开。所守或匪亲，化为狼与豺。朝避猛虎，夕避长蛇；磨牙吮血，杀人如麻。锦城虽云乐，不如早还家。</span><span>蜀道</span><span>之难，难于上青天，侧身西望长咨嗟！</span></p><p><span><br></span></p><p><span><img src=\"http://10.60.2.0:8081/static/lib/layui-v2.5.6/layui/images/face/1.gif\" alt=\"[嘻嘻]\"><img src=\"http://10.60.2.0:8081/static/lib/layui-v2.5.6/layui/images/face/22.gif\" alt=\"[委屈]\"><br></span></p><p><span><br></span></p><p><span>噫吁嚱，危乎高哉！</span><span>蜀道</span><span>之难，难于上青天！蚕丛及鱼凫，开国何茫然！尔来四万八千岁，不与秦塞通人烟。西当太白有鸟道，可以横绝峨眉巅。地崩山摧壮士死，然后天梯石栈相钩连。上有六龙回日之高标，下有冲波逆折之回川。黄鹤之飞尚不得过，猿猱欲度愁攀援。青泥何盘盘，百步九折萦岩峦。扪参历井仰胁息，以手抚膺坐长叹。</span><br><span>问君西游何时还？畏途巉岩不可攀。但见悲鸟号古木，雄飞雌从绕林间。又闻子规啼夜月，愁空山。</span><span>蜀道</span><span>之难，难于上青天，使人听此凋朱颜！连峰去天不盈尺，枯松倒挂倚绝壁。飞湍瀑流争喧豗，砯崖转石万壑雷。其险也如此，嗟尔远道之人胡为乎来哉！</span><br><p><span>剑阁峥嵘而崔嵬，一夫当关，万夫莫开。所守或匪亲，化为狼与豺。朝避猛虎，夕避长蛇；磨牙吮血，杀人如麻。锦城虽云乐，不如早还家。</span><span>蜀道</span><span>之难，难于上青天，侧身西望长咨嗟！</span></p><p><span><br></span></p><p><span><img src=\"http://10.60.2.0:8081/static/lib/layui-v2.5.6/layui/images/face/35.gif\" alt=\"[失望]\"><br></span></p><p><span><br></span></p><p><span><br></span></p><p><span>噫吁嚱，危乎高哉！</span><span>蜀道</span><span>之难，难于上青天！蚕丛及鱼凫，开国何茫然！尔来四万八千岁，不与秦塞通人烟。西当太白有鸟道，可以横绝峨眉巅。地崩山摧壮士死，然后天梯石栈相钩连。上有六龙回日之高标，下有冲波逆折之回川。黄鹤之飞尚不得过，猿猱欲度愁攀援。青泥何盘盘，百步九折萦岩峦。扪参历井仰胁息，以手抚膺坐长叹。</span><br><span>问君西游何时还？畏途巉岩不可攀。但见悲鸟号古木，雄飞雌从绕林间。又闻子规啼夜月，愁空山。</span><span>蜀道</span><span>之难，难于上青天，使人听此凋朱颜！连峰去天不盈尺，枯松倒挂倚绝壁。飞湍瀑流争喧豗，砯崖转石万壑雷。其险也如此，嗟尔远道之人胡为乎来哉！</span><br><p><span>剑阁峥嵘而崔嵬，一夫当关，万夫莫开。所守或匪亲，化为狼与豺。朝避猛虎，夕避长蛇；磨牙吮血，杀人如麻。锦城虽云乐，不如早还家。</span><span>蜀道</span><span>之难，难于上青天，侧身西望长咨嗟！<img src=\"http://10.60.2.0:8081/static/lib/layui-v2.5.6/layui/images/face/10.gif\" alt=\"[鄙视]\"></span></p><p><span><br></span></p><p><span><br></span></p><p><span>噫吁嚱，危乎高哉！</span><span>蜀道</span><span>之难，难于上青天！蚕丛及鱼凫，开国何茫然！尔来四万八千岁，不与秦塞通人烟。西当太白有鸟道，可以横绝峨眉巅。地崩山摧壮士死，然后天梯石栈相钩连。上有六龙回日之高标，下有冲波逆折之回川。黄鹤之飞尚不得过，猿猱欲度愁攀援。青泥何盘盘，百步九折萦岩峦。扪参历井仰胁息，以手抚膺坐长叹。</span><br><span>问君西游何时还？畏途巉岩不可攀。但见悲鸟号古木，雄飞雌从绕林间。又闻子规啼夜月，愁空山。</span><span>蜀道</span><span>之难，难于上青天，使人听此凋朱颜！连峰去天不盈尺，枯松倒挂倚绝壁。飞湍瀑流争喧豗，砯崖转石万壑雷。其险也如此，嗟尔远道之人胡为乎来哉！</span><br><p><span>剑阁峥嵘而崔嵬，一夫当关，万夫莫开。所守或匪亲，化为狼与豺。朝避猛虎，夕避长蛇；磨牙吮血，杀人如麻。锦城虽云乐，不如早还家。</span><span>蜀道</span><span>之难，难于上青天，侧身西望长咨嗟！</span></p><p><span><br></span></p><p><span><br></span></p><p><span>噫吁嚱，危乎高哉！</span><span>蜀道</span><span>之难，难于上青天！蚕丛及鱼凫，开国何茫然！尔来四万八千岁，不与秦塞通人烟。西当太白有鸟道，可以横绝峨眉巅。地崩山摧壮士死，然后天梯石栈相钩连。上有六龙回日之高标，下有冲波逆折之回川。黄鹤之飞尚不得过，猿猱欲度愁攀援。青泥何盘盘，百步九折萦岩峦。扪参历井仰胁息，以手抚膺坐长叹。</span><br><span>问君西游何时还？畏途巉岩不可攀。但见悲鸟号古木，雄飞雌从绕林间。又闻子规啼夜月，愁空山。</span><span>蜀道</span><span>之难，难于上青天，使人听此凋朱颜！连峰去天不盈尺，枯松倒挂倚绝壁。飞湍瀑流争喧豗，砯崖转石万壑雷。其险也如此，嗟尔远道之人胡为乎来哉！</span><br><p><span>剑阁峥嵘而崔嵬，一夫当关，万夫莫开。所守或匪亲，化为狼与豺。朝避猛虎，夕避长蛇；磨牙吮血，杀人如麻。锦城虽云乐，不如早还家。</span><span>蜀道</span><span>之难，难于上青天，侧身西望长咨嗟！</span></p><p><span><br></span></p><p><span><br></span></p><p><span><br></span></p><p><span>噫吁嚱，危乎高哉！</span><span>蜀道</span><span>之难，难于上青天！蚕丛及鱼凫，开国何茫然！尔来四万八千岁，不与秦塞通人烟。西当太白有鸟道，可以横绝峨眉巅。地崩山摧壮士死，然后天梯石栈相钩连。上有六龙回日之高标，下有冲波逆折之回川。黄鹤之飞尚不得过，猿猱欲度愁攀援。青泥何盘盘，百步九折萦岩峦。扪参历井仰胁息，以手抚膺坐长叹。</span><br><span>问君西游何时还？畏途巉岩不可攀。但见悲鸟号古木，雄飞雌从绕林间。又闻子规啼夜月，愁空山。</span><span>蜀道</span><span>之难，难于上青天，使人听此凋朱颜！连峰去天不盈尺，枯松倒挂倚绝壁。飞湍瀑流争喧豗，砯崖转石万壑雷。其险也如此，嗟尔远道之人胡为乎来哉！</span><br><p><span>剑阁峥嵘而崔嵬，一夫当关，万夫莫开。所守或匪亲，化为狼与豺。朝避猛虎，夕避长蛇；磨牙吮血，杀人如麻。锦城虽云乐，不如早还家。</span><span>蜀道</span><span>之难，难于上青天，侧身西望长咨嗟！</span></p><p><span><br></span></p><p><span><br></span></p><p><span><br></span></p><p><span>噫吁嚱，危乎高哉！</span><span>蜀道</span><span>之难，难于上青天！蚕丛及鱼凫，开国何茫然！尔来四万八千岁，不与秦塞通人烟。西当太白有鸟道，可以横绝峨眉巅。地崩山摧壮士死，然后天梯石栈相钩连。上有六龙回日之高标，下有冲波逆折之回川。黄鹤之飞尚不得过，猿猱欲度愁攀援。青泥何盘盘，百步九折萦岩峦。扪参历井仰胁息，以手抚膺坐长叹。</span><br><span>问君西游何时还？畏途巉岩不可攀。但见悲鸟号古木，雄飞雌从绕林间。又闻子规啼夜月，愁空山。</span><span>蜀道</span><span>之难，难于上青天，使人听此凋朱颜！连峰去天不盈尺，枯松倒挂倚绝壁。飞湍瀑流争喧豗，砯崖转石万壑雷。其险也如此，嗟尔远道之人胡为乎来哉！</span><br><span>剑阁峥嵘而崔嵬，一夫当关，万夫莫开。所守或匪亲，化为狼与豺。朝避猛虎，夕避长蛇；磨牙吮血，杀人如麻。锦城虽云乐，不如早还家。</span><span>蜀道</span><span>之难，难于上青天，侧身西望长咨嗟！</span></p></p></p></p><span></span></p></p></p>');
INSERT INTO `mail` VALUES (15, '你好吗？', '<p><p style=\"text-align: center;\">君不见，黄河之水天上来，奔流到海不复回。<br>君不见，高堂明镜悲白发，朝如青丝暮成雪。<br>人生得意须尽欢，莫使金樽空对月。<br>天生我材必有用，千金散尽还复来。<br>烹羊宰牛且为乐，会须一饮三百杯。<br>岑夫子，丹丘生，将进酒，杯莫停。<br>与君歌一曲，请君为我倾耳听。<br>钟鼓馔玉不足贵，但愿长醉不复醒。<br>古来圣贤皆寂寞，惟有饮者留其名。<br>陈王昔时宴平乐，斗酒十千恣欢谑。<br>主人何为言少钱，径须沽取对君酌。<br>君不见，黄河之水天上来，奔流到海不复回。<br>君不见，高堂明镜悲白发，朝如青丝暮成雪。<br>人生得意须尽欢，莫使金樽空对月。<br>天生我材必有用，千金散尽还复来。<br>烹羊宰牛且为乐，会须一饮三百杯。<br>岑夫子，丹丘生，将进酒，杯莫停。<br>与君歌一曲，请君为我倾耳听。<br>钟鼓馔玉不足贵，但愿长醉不复醒。<br>古来圣贤皆寂寞，惟有饮者留其名。<br>陈王昔时宴平乐，斗酒十千恣欢谑。<br>主人何为言少钱，径须沽取对君酌。<br>君不见，黄河之水天上来，奔流到海不复回。<br>君不见，高堂明镜悲白发，朝如青丝暮成雪。<br>人生得意须尽欢，莫使金樽空对月。<br>天生我材必有用，千金散尽还复来。<br>烹羊宰牛且为乐，会须一饮三百杯。<br>岑夫子，丹丘生，将进酒，杯莫停。<br>与君歌一曲，请君为我倾耳听。<br>钟鼓馔玉不足贵，但愿长醉不复醒。<br>古来圣贤皆寂寞，惟有饮者留其名。<br>陈王昔时宴平乐，斗酒十千恣欢谑。<br>主人何为言少钱，径须沽取对君酌。</p><p style=\"text-align: center;\"><span>五花马，千金裘，呼儿将出换美酒，与尔同销万古愁。</span></p><p style=\"text-align: center; \"><span><img src=\"http://10.60.2.0:8081/static/lib/layui-v2.5.6/layui/images/face/1.gif\" alt=\"[嘻嘻]\"><br></span></p><p style=\"text-align: center; \"><span><br></span></p><p><p style=\"text-align: center;\"><span>五花马，千金裘，呼儿将出换美酒，与尔同销万古愁。</span></p><p style=\"text-align: center;\"><span><br></span></p><p style=\"text-align: center; \"><span><img src=\"http://10.60.2.0:8081/static/lib/layui-v2.5.6/layui/images/face/47.gif\" alt=\"[心]\"><br></span></p><p style=\"text-align: center; \"><span><br></span></p><p style=\"text-align: center; \"><span><br></span></p><p><p style=\"text-align: center;\"><span>五花马，千金裘，呼儿将出换美酒，与尔同销万古愁。</span></p><p style=\"text-align: center;\"><span><br></span></p><p style=\"text-align: center; \"><span><img src=\"http://10.60.2.0:8081/static/lib/layui-v2.5.6/layui/images/face/35.gif\" alt=\"[失望]\"><br></span></p></p></p></p>');
INSERT INTO `mail` VALUES (16, '测试', '<p><img src=\"http://10.60.2.0:8081/static/lib/layui-v2.5.6/layui/images/face/45.gif\" alt=\"[怒骂]\"></p><p>你还有吗？</p>');
INSERT INTO `mail` VALUES (17, '你睡觉', '萨达发<img src=\"http://10.60.2.0:8081/static/lib/layui-v2.5.6/layui/images/face/28.gif\" alt=\"[馋嘴]\">');
INSERT INTO `mail` VALUES (18, '测试', '<p><img src=\"http://10.60.2.0:8081/static/lib/layui-v2.5.6/layui/images/face/42.gif\" alt=\"[抓狂]\"></p><p>不要烦恼!</p>');
INSERT INTO `mail` VALUES (19, '通知', '测试');
INSERT INTO `mail` VALUES (20, '通知', '对用户进行管理统计!');
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
  `state` int(1) DEFAULT NULL COMMENT '状态 0未读 1已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail_receive
-- ----------------------------
BEGIN;
INSERT INTO `mail_receive` VALUES (88, 14, 'zhangsan', 10, '2021-09-13 11:34:38', 1);
INSERT INTO `mail_receive` VALUES (90, 15, 'lisi', 9, '2021-09-13 12:23:21', 1);
INSERT INTO `mail_receive` VALUES (91, 15, 'lisi', 9, '2021-09-13 12:23:23', 1);
INSERT INTO `mail_receive` VALUES (92, 15, 'lisi', 9, '2021-09-13 12:23:23', 1);
INSERT INTO `mail_receive` VALUES (93, 15, 'lisi', 9, '2021-09-13 12:23:23', 1);
INSERT INTO `mail_receive` VALUES (94, 15, 'lisi', 9, '2021-09-13 12:23:23', 1);
INSERT INTO `mail_receive` VALUES (95, 15, 'lisi', 9, '2021-09-13 12:23:23', 1);
INSERT INTO `mail_receive` VALUES (98, 16, 'zhangsan', 10, '2021-09-13 14:11:23', 1);
INSERT INTO `mail_receive` VALUES (99, 18, 'zhangsan', 10, '2021-09-14 15:16:16', 1);
INSERT INTO `mail_receive` VALUES (100, 20, 'shaoming', 10, '2021-09-16 09:42:29', 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail_send
-- ----------------------------
BEGIN;
INSERT INTO `mail_send` VALUES (91, '1650666955@qq.com', 'zhangsan', '2021-09-13 11:34:38', 14, 0, 0);
INSERT INTO `mail_send` VALUES (100, '1650666955@qq.com', 'zhangsan', '2021-09-14 15:16:16', 18, 0, 1);
INSERT INTO `mail_send` VALUES (101, '1650666955@qq.co', 'shaoming', '2021-09-16 09:42:03', 19, 0, 1);
INSERT INTO `mail_send` VALUES (102, '1650666955@qq.com', 'shaoming', '2021-09-16 09:42:29', 20, 0, 1);
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
  `disk_total_size` varchar(255) DEFAULT NULL COMMENT '磁盘空间总大小',
  `disk_left_size` varchar(255) DEFAULT NULL COMMENT '磁盘剩余可用空间大小',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of peers
-- ----------------------------
BEGIN;
INSERT INTO `peers` VALUES (17, 'linux-company', 'group1', 'http://10.60.1.79:8080', NULL, NULL, NULL, '2021-08-26 07:31:01', NULL);
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
  `share_time` datetime DEFAULT NULL COMMENT '分享时间',
  `download_count` int(11) DEFAULT '0' COMMENT '下载次数',
  `read_count` int(11) DEFAULT '0' COMMENT '浏览次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share
-- ----------------------------
BEGIN;
INSERT INTO `share` VALUES (40, 744, '丑八怪 - 薛之谦.mp3', '', '3.82MB', 'shaoming', '邵明', '2021-09-18 14:37:39', 0, 0);
INSERT INTO `share` VALUES (41, 745, '安和桥 - 宋冬野.mp3', '', '3.84MB', 'shaoming', '邵明', '2021-09-18 14:37:40', 0, 0);
INSERT INTO `share` VALUES (42, 742, '带你去旅行 - 校长（张驰）.mp3', '', '3.48MB', 'shaoming', '邵明', '2021-09-18 14:37:41', 0, 0);
INSERT INTO `share` VALUES (43, 740, '断了的弦 - 周杰伦.mp3', '', '4.58MB', 'shaoming', '邵明', '2021-09-18 14:37:42', 0, 0);
INSERT INTO `share` VALUES (44, 743, '曾经的你 - 许巍.mp3', '', '4.00MB', 'shaoming', '邵明', '2021-09-18 14:37:43', 0, 1);
INSERT INTO `share` VALUES (45, 741, '稻香 - 周杰伦.mp3', '', '3.46MB', 'shaoming', '邵明', '2021-09-18 14:37:43', 0, 1);
INSERT INTO `share` VALUES (46, 746, '畫江湖之不良人第4季EP11.mp4', '', '411.82MB', 'cs-05', 'cs-05', '2021-09-18 16:23:44', 0, 1);
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
  `create_time` datetime NOT NULL COMMENT '注册时间',
  `update_time` datetime DEFAULT NULL COMMENT '信息更新时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `deleted` int(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`,`username`,`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (13, 'shaoming', '邵明', '2744f4aa45ec70fc0c85c52a479dfcad', 1, 18, '1650666953@qq.com', 17, '58328a13af2d4fad9892808897eda7a0', '2021-09-17 13:48:25', NULL, '2021-09-18 15:35:17', 0);
INSERT INTO `user` VALUES (14, 'cs-01', 'cs-01', 'b4b8d1bf6e571823894f291aa74d1c73', 1, 18, '01@cs.com', 17, '96fdd19a9c104635b667e2ee2776c9a7', '2021-09-18 10:20:44', NULL, '2021-09-18 13:30:40', 1);
INSERT INTO `user` VALUES (15, 'cs-02', 'cs-02', '2a9837c75c445cf2a71dd00079765041', 1, 18, '02@cs.com', 17, '492b86826d2b442dae86908658291483', '2021-09-18 10:21:16', NULL, '2021-09-18 13:57:26', 1);
INSERT INTO `user` VALUES (16, 'cs-03', 'cs-03', '4e7bc869bc5b606fb367a76c372f258e', 1, 18, '03@cs.com', 17, 'b9b458e7914448f897af531ad615086f', '2021-09-18 10:21:45', NULL, '2021-09-18 14:06:53', 1);
INSERT INTO `user` VALUES (17, 'cs-04', 'cs-04', '62fb7f6cc806d652e7a417e562263628', 1, 18, '04@cs.com', 17, 'eff6b1269da248779c7d68c66aba8f68', '2021-09-18 10:22:15', NULL, '2021-09-18 14:07:04', 1);
INSERT INTO `user` VALUES (18, 'cs-05', 'cs-05', '23456de57b3a61405b259bcbd71a81a0', 1, 18, '05@cs.com', 17, '4c088eabf9ec4dedb0d397d49c938259', '2021-09-18 10:22:46', NULL, '2021-09-18 18:00:25', 0);
INSERT INTO `user` VALUES (19, 'cs-06', 'cs-06', 'f7a9495d6c04ee5b44ade32637aa6c7a', 1, 18, '06@cs.com', 17, '6b8f5e603f9c4b4eb7315001dfbf6215', '2021-09-18 10:23:18', NULL, NULL, 0);
INSERT INTO `user` VALUES (20, 'cs-07', 'cs-07', 'bd4f4463b6ca3b1cd7a298bf64f62c56', 1, 18, '07@cs.com', 17, 'af95aa17ada74953807596f422e7c1ef', '2021-09-18 10:23:46', NULL, NULL, 0);
INSERT INTO `user` VALUES (21, 'cs-08', 'cs-08', 'b1a86803ca997ac8fa81639d270141ba', 1, 18, '08@cs.com', 17, 'fd1c362b7ef04c00af3272905337ae4f', '2021-09-18 10:24:08', NULL, NULL, 0);
INSERT INTO `user` VALUES (22, 'cs-09', 'cs-09', '779fb3ecc42769ce1a4d7bd8c7fe34f8', 1, 18, '09@cs.com', 17, 'fbec821b0c464ad2ad88297d2c278a61', '2021-09-18 10:24:36', NULL, NULL, 0);
INSERT INTO `user` VALUES (23, 'cs-10', 'cs-10', 'ef8d8af38228572ba34928a09ca5bc22', 1, 18, '10@cs.com', 17, '8bbc8092de3049db8a382f6cce6d0502', '2021-09-18 10:24:57', NULL, NULL, 0);
INSERT INTO `user` VALUES (24, 'cs-11', 'cs-11', '02357f7b13cbedf1473d47631bd8e1f5', 1, 18, '11@cs.com', 17, '81b266b8ead34840b56c6bb778c70ff3', '2021-09-18 10:25:19', NULL, NULL, 0);
INSERT INTO `user` VALUES (25, 'cs-12', 'cs-12', '7603f88c27ce66a6534179a65ce7ac0c', 1, 18, '12@cs.com', 17, 'ffd222e441d945f785afa099e114ce95', '2021-09-18 10:25:40', NULL, NULL, 0);
INSERT INTO `user` VALUES (26, 'cs-13', 'cs-13', '64f3b0be30119091c01d8772a06ea6ec', 1, 18, '13@cs.com', 17, '84508bdc865f4ffeb6d445573a665823', '2021-09-18 10:26:02', NULL, NULL, 0);
INSERT INTO `user` VALUES (27, 'cs-14', 'cs-14', '9488275c47fdcc098986ea0691e68cb2', 1, 18, '14@cs.com', 17, 'd324ed94e6e04e9b9ec8997d0c147715', '2021-09-18 10:26:22', NULL, NULL, 0);
INSERT INTO `user` VALUES (28, 'cs-15', 'cs-15', '1d40b7a5ff3e03dcbb41d55bd62b0b5c', 1, 18, '15@cs.com', 17, '0ce026a91a3b4979bfb573d94ebae450', '2021-09-18 10:26:46', NULL, NULL, 0);
INSERT INTO `user` VALUES (29, 'cs-16', 'cs-16', '2af6b9d538f4f8fecc19bd101a399128', 1, 18, '16@cs.com', 17, '3be7665e6c4b4b1b843739334f6968f2', '2021-09-18 10:30:32', NULL, NULL, 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=1614 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_file
-- ----------------------------
BEGIN;
INSERT INTO `user_file` VALUES (1601, 13, 740);
INSERT INTO `user_file` VALUES (1602, 13, 741);
INSERT INTO `user_file` VALUES (1603, 13, 742);
INSERT INTO `user_file` VALUES (1604, 13, 743);
INSERT INTO `user_file` VALUES (1605, 13, 744);
INSERT INTO `user_file` VALUES (1606, 13, 745);
INSERT INTO `user_file` VALUES (1607, 18, 746);
INSERT INTO `user_file` VALUES (1612, 18, 751);
INSERT INTO `user_file` VALUES (1613, 18, 752);
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (9, 13, 1);
INSERT INTO `user_role` VALUES (10, 14, 4);
INSERT INTO `user_role` VALUES (11, 15, 4);
INSERT INTO `user_role` VALUES (12, 16, 4);
INSERT INTO `user_role` VALUES (13, 17, 4);
INSERT INTO `user_role` VALUES (14, 18, 4);
INSERT INTO `user_role` VALUES (15, 19, 4);
INSERT INTO `user_role` VALUES (16, 20, 4);
INSERT INTO `user_role` VALUES (17, 21, 4);
INSERT INTO `user_role` VALUES (18, 22, 4);
INSERT INTO `user_role` VALUES (19, 23, 4);
INSERT INTO `user_role` VALUES (20, 24, 4);
INSERT INTO `user_role` VALUES (21, 25, 4);
INSERT INTO `user_role` VALUES (22, 26, 4);
INSERT INTO `user_role` VALUES (23, 27, 4);
INSERT INTO `user_role` VALUES (24, 28, 4);
INSERT INTO `user_role` VALUES (25, 29, 4);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
