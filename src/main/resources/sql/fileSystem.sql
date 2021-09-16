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

 Date: 16/09/2021 18:07:11
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
  `open` int(1) DEFAULT '0' COMMENT '是否公开 0私有 1公开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=680 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
BEGIN;
INSERT INTO `file` VALUES (647, '蛇梯棋游戏的设计与发开.md', '/group1/zhangsan/test/蛇梯棋游戏的设计与发开.md', 17, 0);
INSERT INTO `file` VALUES (648, '鲸鱼骑士4k壁纸 by_Artur Sadlos_彼岸图网.jpg', '/group1/zhangsan/鲸鱼骑士4k壁纸 by_Artur Sadlos_彼岸图网.jpg', 17, 0);
INSERT INTO `file` VALUES (649, '和服美女 花 黑色背景 4k壁纸_彼岸图网.jpg', '/group1/zhangsan/test/和服美女 花 黑色背景 4k壁纸_彼岸图网.jpg', 17, 0);
INSERT INTO `file` VALUES (671, '蛇梯棋.md', '/group1/lisi/测试/中文/中文测试文件夹/蛇梯棋.md', 17, 0);
INSERT INTO `file` VALUES (672, '蛇梯棋.md', '/group1/lisi/蛇梯棋.md', 17, 0);
INSERT INTO `file` VALUES (673, '(完整版)网络工程试题 - 百度文库.pdf', '/group1/lisi/(完整版)网络工程试题 - 百度文库.pdf', 17, 0);
INSERT INTO `file` VALUES (674, '[ENG SUB]《云南虫谷 The Worm Valley》第01集——铁三角远赴云南寻找雮尘珠 得孔雀相告获悉遮龙山水洞.mp4', '/group1/lisi/测试/中文/中文测试文件夹/[ENG SUB]《云南虫谷 The Worm Valley》第01集——铁三角远赴云南寻找雮尘珠 得孔雀相告获悉遮龙山水洞.mp4', 17, 0);
INSERT INTO `file` VALUES (675, '稻香 - 周杰伦.mp3', '/group1/lisi/测试/中文/中文测试文件夹/稻香 - 周杰伦.mp3', 17, 0);
INSERT INTO `file` VALUES (676, '粉红色的回忆 - 韩宝仪.mp3', '/group1/lisi/测试/中文/中文测试文件夹/粉红色的回忆 - 韩宝仪.mp3', 17, 0);
INSERT INTO `file` VALUES (677, '曾经的你 - 许巍.mp3', '/group1/lisi/测试/中文/中文测试文件夹/曾经的你 - 许巍.mp3', 17, 0);
INSERT INTO `file` VALUES (678, '独家记忆 - 陈小春.mp3', '/group1/lisi/测试/中文/中文测试文件夹/独家记忆 - 陈小春.mp3', 17, 0);
INSERT INTO `file` VALUES (679, 'Java设计模式.md', '/group1/zhangsan/test/Java设计模式.md', 17, 0);
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
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of peers
-- ----------------------------
BEGIN;
INSERT INTO `peers` VALUES (15, 'tencent-cloud', 'group1', 'http://1.15.221.117:8080', NULL, '2021-08-26 07:31:01', NULL);
INSERT INTO `peers` VALUES (16, 'home', 'group1', 'http://192.168.0.118:8080', '', '2021-09-04 21:10:53', NULL);
INSERT INTO `peers` VALUES (17, 'linux-company', 'group1', 'http://10.60.1.79:8080', NULL, '2021-08-26 07:31:01', NULL);
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
  `share_time` datetime DEFAULT NULL COMMENT '分享时间',
  `download_count` int(11) DEFAULT '0' COMMENT '下载次数',
  `read_count` int(11) DEFAULT '0' COMMENT '浏览次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

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
  `create_time` datetime NOT NULL COMMENT '注册时间',
  `update_time` datetime DEFAULT NULL COMMENT '信息更新时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `deleted` int(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`,`username`,`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (5, 'tencent', 'tencent-cloud', 'e07ebd8650ec8dba920e9c0c8a4e4257', 1, 18, 'a18477019943@gmail.com', 15, 'b962363fe6ce4c9385e73adfe55790bc', '2021-08-26 07:31:01', '2021-09-02 20:48:14', '2021-09-05 16:35:33', 0);
INSERT INTO `user` VALUES (6, 'company', 'company', 'e07ebd8650ec8dba920e9c0c8a4e4257', 1, 18, 'a18477019944@gmail.com', 16, 'b962363fe6ce4c9385e73adfe55790bc', '2021-08-26 07:31:01', '2021-09-10 21:35:51', '2021-09-13 00:11:01', 0);
INSERT INTO `user` VALUES (7, 'home', 'company', 'e07ebd8650ec8dba920e9c0c8a4e4257', 1, 18, 'a18477019945@gmail.com', 16, 'b962363fe6ce4c9385e73adfe55790bc', '2021-08-26 07:31:01', '2021-09-15 12:40:33', '2021-09-13 00:22:34', 0);
INSERT INTO `user` VALUES (8, 'shaoming', '邵明', 'e07ebd8650ec8dba920e9c0c8a4e4257', 1, 20, '1650666953@qq.com', 17, 'b962363fe6ce4c9385e73adfe55790bc', '2021-09-12 17:40:43', '2021-09-12 17:40:45', '2021-09-16 13:49:30', 0);
INSERT INTO `user` VALUES (9, 'zhangsan', '张三', 'e07ebd8650ec8dba920e9c0c8a4e4257', 1, 20, '1650666954@qq.com', 17, 'b962363fe6ce4c9385e73adfe55790bc', '2021-09-12 17:40:43', '2021-09-15 15:01:22', '2021-09-15 15:01:42', 0);
INSERT INTO `user` VALUES (10, 'lisi', '李四', 'e07ebd8650ec8dba920e9c0c8a4e4257', 1, 20, '1650666955@qq.com', 17, 'b962363fe6ce4c9385e73adfe55790bc', '2021-09-12 17:40:43', '2021-09-16 09:39:37', '2021-09-16 18:05:49', 0);
INSERT INTO `user` VALUES (11, 'wangwu', 'test', 'b26bb24391c0f8cc327fd1400f94b8e1', 1, 18, '1650666999@qq.com', 17, '4dd1619966bb4107a4b93a646188c959', '2021-09-13 18:08:39', NULL, '2021-09-15 12:42:07', 0);
INSERT INTO `user` VALUES (12, '123', '123', '00c7a78f4a86199fb86e6da34c5414be', 1, 18, '123@qq.com', 17, 'c2ee7c22bd7a4eed9ee0ed41c82be233', '2021-09-14 09:38:59', NULL, NULL, 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=1541 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_file
-- ----------------------------
BEGIN;
INSERT INTO `user_file` VALUES (1508, 9, 647);
INSERT INTO `user_file` VALUES (1509, 9, 648);
INSERT INTO `user_file` VALUES (1510, 9, 649);
INSERT INTO `user_file` VALUES (1532, 10, 671);
INSERT INTO `user_file` VALUES (1533, 10, 672);
INSERT INTO `user_file` VALUES (1534, 10, 673);
INSERT INTO `user_file` VALUES (1535, 10, 674);
INSERT INTO `user_file` VALUES (1536, 10, 675);
INSERT INTO `user_file` VALUES (1537, 10, 676);
INSERT INTO `user_file` VALUES (1538, 10, 677);
INSERT INTO `user_file` VALUES (1539, 10, 678);
INSERT INTO `user_file` VALUES (1540, 9, 679);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (1, 8, 1);
INSERT INTO `user_role` VALUES (2, 5, 4);
INSERT INTO `user_role` VALUES (3, 6, 4);
INSERT INTO `user_role` VALUES (4, 7, 4);
INSERT INTO `user_role` VALUES (5, 9, 3);
INSERT INTO `user_role` VALUES (6, 10, 3);
INSERT INTO `user_role` VALUES (7, 11, 1);
INSERT INTO `user_role` VALUES (8, 12, 4);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
