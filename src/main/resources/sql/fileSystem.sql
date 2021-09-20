/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : 127.0.0.1:3306
 Source Schema         : fileSystem

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 20/09/2021 17:33:29
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
) ENGINE=InnoDB AUTO_INCREMENT=819 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
BEGIN;
INSERT INTO `file` VALUES (783, 'desktop.png', '6d5a01bc372ed957dfa8260ac96cdeb0', '/group1/shaoming/desktop.png', '2021-09-20 16:10:06', 18, 1);
INSERT INTO `file` VALUES (784, 'abc-123.jpg', '6ad63ba4f4dd13d22cfb5766ae16250f', '/group1/cs-01/测试/abc-123.jpg', '2021-09-20 17:03:41', 18, 0);
INSERT INTO `file` VALUES (785, 'Colorful-Abstraction03.jpg', '6225043dfb809be6b9d074b63da93d48', '/group1/cs-01/测试/Colorful-Abstraction03.jpg', '2021-09-20 17:03:41', 18, 0);
INSERT INTO `file` VALUES (786, 'desktop_orig.jpg', 'cf5d90483746b5d5ef0dfe58c3ca0d67', '/group1/cs-01/测试/desktop_orig.jpg', '2021-09-20 17:03:41', 18, 0);
INSERT INTO `file` VALUES (787, 'cristina-gottardi-wndpWTiDuT0-unsplash.jpg', '5dd7455a420847d25343e1f4f43e33a8', '/group1/cs-01/测试/cristina-gottardi-wndpWTiDuT0-unsplash.jpg', '2021-09-20 17:03:41', 18, 0);
INSERT INTO `file` VALUES (788, 'Colorful-Abstraction01.jpg', '3956d272fc068d00dff60e333596b0df', '/group1/cs-01/测试/Colorful-Abstraction01.jpg', '2021-09-20 17:03:41', 18, 0);
INSERT INTO `file` VALUES (789, 'Colorful-Abstraction02.jpg', 'd24ffc58cc99cd9cd29fd31d338a4eab', '/group1/cs-01/测试/Colorful-Abstraction02.jpg', '2021-09-20 17:03:41', 18, 0);
INSERT INTO `file` VALUES (790, 'francesco-ungaro-1fzbUyzsHV8-unsplash.jpg', 'e4881c63597db42240d04a711c1a00fe', '/group1/cs-01/测试/francesco-ungaro-1fzbUyzsHV8-unsplash.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (791, 'garrett-patz-Ilu1Vv6EYds-unsplash.jpg', '7fc986079509758073ec900cce82a464', '/group1/cs-01/测试/garrett-patz-Ilu1Vv6EYds-unsplash.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (792, 'Glossy-Gradient.jpg', '4bc9a808230fd38ef17db09ce3594e93', '/group1/cs-01/测试/Glossy-Gradient.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (793, 'Flying_Whale_by_Shu_Le.jpg', '8bc42c8a7da307d5ceeae853a68d2d9f', '/group1/cs-01/测试/Flying_Whale_by_Shu_Le.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (794, 'desktop2.jpg', '355c9ec0c0ed5a711318c44706896d7f', '/group1/cs-01/测试/desktop2.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (795, 'jeremy-bishop-2e3hgvDnCpM-unsplash.jpg', '4d2b5f23ea3b5d3a649bc262c0050ad8', '/group1/cs-01/测试/jeremy-bishop-2e3hgvDnCpM-unsplash.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (796, 'desktop.jpg', '7b80cd617f98d8033e47a53eaebc31c8', '/group1/cs-01/测试/desktop.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (797, 'luca-micheli-ruWkmt3nU58-unsplash.jpg', 'f9709a02382c2541879ed75688fac024', '/group1/cs-01/测试/luca-micheli-ruWkmt3nU58-unsplash.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (798, 'Hummingbird_by_Shu_Le.jpg', 'de71b62da4e0d203db308483254d7ec4', '/group1/cs-01/测试/Hummingbird_by_Shu_Le.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (799, 'kyaw-tun-k6BHLfw_jUg-unsplash.jpg', '0b2cd6dbb7379f9912e3fc30a0279f9a', '/group1/cs-01/测试/kyaw-tun-k6BHLfw_jUg-unsplash.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (800, 'joshua-coleman-KzPefInJW58-unsplash.jpg', 'dc11c2a551110b83da860a71c08177f7', '/group1/cs-01/测试/joshua-coleman-KzPefInJW58-unsplash.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (801, 'lucian-dachman-qxO2PBn7eKU-unsplash.jpg', 'a9a964471173b516c813e046dc58872e', '/group1/cs-01/测试/lucian-dachman-qxO2PBn7eKU-unsplash.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (802, 'marian-kroell-qElMHWePpok-unsplash.jpg', 'c632f5c9967fcaf2f4e149ce8ddfb3e3', '/group1/cs-01/测试/marian-kroell-qElMHWePpok-unsplash.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (803, 'massimiliano-morosinotto-MljwsnGwdOY-unsplash.jpg', 'b564f5def26354d1bbcf6ba9a198a02b', '/group1/cs-01/测试/massimiliano-morosinotto-MljwsnGwdOY-unsplash.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (804, 'mike-yukhtenko-a2kD4b0KK4s-unsplash.jpg', '74f675bcf47759e882469c62cf006f82', '/group1/cs-01/测试/mike-yukhtenko-a2kD4b0KK4s-unsplash.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (805, 'luke-stackpoole-mStq-dtsXCQ-unsplash.jpg', 'd8a25bf443579f36bd301b3d00b477d8', '/group1/cs-01/测试/luke-stackpoole-mStq-dtsXCQ-unsplash.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (806, 'sourav-ghosh-gTvhFsQMqnA-unsplash.jpg', 'd44e5a63288ec687792937f9aa394295', '/group1/cs-01/测试/sourav-ghosh-gTvhFsQMqnA-unsplash.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (807, 'UOS-棉花壁纸-02.jpg', '478cd83edb3477ac5c8b7a068afcc94d', '/group1/cs-01/测试/UOS-棉花壁纸-02.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (808, 'UOS-棉花壁纸-01.jpg', 'bb67d6e86ca8581ecf04adacf1f3c9cc', '/group1/cs-01/测试/UOS-棉花壁纸-01.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (809, 'UOS-棉花壁纸-03.jpg', 'af9f8e08b917e3d646572e768ee7b855', '/group1/cs-01/测试/UOS-棉花壁纸-03.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (810, 'UOS地球日壁纸-3840x2400-03.jpg', 'fcf20082b743027d97fadf69d93bc301', '/group1/cs-01/测试/UOS地球日壁纸-3840x2400-03.jpg', '2021-09-20 17:03:42', 18, 1);
INSERT INTO `file` VALUES (811, 'UOS地球日壁纸-3840x2400-02.jpg', 'fb34c79813bec2e3cf9a16eb27b82821', '/group1/cs-01/测试/UOS地球日壁纸-3840x2400-02.jpg', '2021-09-20 17:03:42', 18, 1);
INSERT INTO `file` VALUES (812, 'UOS地球日壁纸-3840x2400-01.jpg', 'e440424130bd6b81313b740a521a828e', '/group1/cs-01/测试/UOS地球日壁纸-3840x2400-01.jpg', '2021-09-20 17:03:42', 18, 1);
INSERT INTO `file` VALUES (813, 'willian-justen-de-vasconcellos-ASKGjAeIY_U-unsplash.jpg', '2d9b1e2dc80ea97fd875093dbe3baf8c', '/group1/cs-01/测试/willian-justen-de-vasconcellos-ASKGjAeIY_U-unsplash.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (814, 'UOS地球日壁纸-3840x2400-05.jpg', '8575b1e7f462fbc555a21f44fb9f5635', '/group1/cs-01/测试/UOS地球日壁纸-3840x2400-05.jpg', '2021-09-20 17:03:42', 18, 1);
INSERT INTO `file` VALUES (815, 'UOS地球日壁纸-3840x2400-04.jpg', 'b46ed5f10c3eb84cfdff6fd49d69b6e4', '/group1/cs-01/测试/UOS地球日壁纸-3840x2400-04.jpg', '2021-09-20 17:03:42', 18, 1);
INSERT INTO `file` VALUES (816, 'zetong-li-VAT6XKKwaIE-unsplash.jpg', '75f531b553a8b3d529dd469a06d13b24', '/group1/cs-01/测试/zetong-li-VAT6XKKwaIE-unsplash.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (817, 'wolfgang-hasselmann-WrVvYxq11Yk-unsplash.jpg', '8879cee08e7e14c0e91a59e3777b93f9', '/group1/cs-01/测试/wolfgang-hasselmann-WrVvYxq11Yk-unsplash.jpg', '2021-09-20 17:03:42', 18, 0);
INSERT INTO `file` VALUES (818, 'zetong-li-TbHYpbi_Gbc-unsplash.jpg', 'aafdc1654864623dc324241484fb36af', '/group1/cs-01/测试/zetong-li-TbHYpbi_Gbc-unsplash.jpg', '2021-09-20 17:03:42', 18, 0);
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
  `disk_total_size` double DEFAULT NULL COMMENT '磁盘空间总大小',
  `disk_left_size` double DEFAULT NULL COMMENT '磁盘剩余可用空间大小',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of peers
-- ----------------------------
BEGIN;
INSERT INTO `peers` VALUES (18, 'default', 'group1', 'http://192.168.0.106:8080', '', 861956986634.24, 851219568394.24, '2021-09-19 17:53:56', NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share
-- ----------------------------
BEGIN;
INSERT INTO `share` VALUES (50, 783, 'desktop.png', '', '7.98MB', 'shaoming', '邵明', '2021-09-20 16:10:42', 0, 5);
INSERT INTO `share` VALUES (51, 812, 'UOS地球日壁纸-3840x2400-01.jpg', '测试', '1.68MB', 'cs-01', 'cs-01', '2021-09-20 17:04:23', 0, 0);
INSERT INTO `share` VALUES (52, 811, 'UOS地球日壁纸-3840x2400-02.jpg', '测试', '2.84MB', 'cs-01', 'cs-01', '2021-09-20 17:04:23', 0, 1);
INSERT INTO `share` VALUES (53, 810, 'UOS地球日壁纸-3840x2400-03.jpg', '测试', '1.10MB', 'cs-01', 'cs-01', '2021-09-20 17:04:24', 0, 1);
INSERT INTO `share` VALUES (54, 815, 'UOS地球日壁纸-3840x2400-04.jpg', '测试', '2.85MB', 'cs-01', 'cs-01', '2021-09-20 17:04:25', 0, 2);
INSERT INTO `share` VALUES (55, 814, 'UOS地球日壁纸-3840x2400-05.jpg', '测试', '835.06KB', 'cs-01', 'cs-01', '2021-09-20 17:04:26', 0, 2);
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (30, 'shaoming', '邵明', '2e7500eb2969c01aee169c3fc118b8f1', 1, 18, '1650666953@qq.com', 18, 'f06e68e26da74ecb97ed3b18bb5cfda8', 5368709120, 5360337130, '2021-09-20 11:43:11', NULL, '2021-09-20 17:25:48', 0);
INSERT INTO `user` VALUES (31, 'cs-01', 'cs-01', '3dea8309793cc176fea223b07c69dc6f', 1, 18, '01@cs.com', 18, '6d21ee2a2cff4cef8918d8d695447b74', 5368709120, 5322065305, '2021-09-20 16:58:56', NULL, '2021-09-20 17:24:56', 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=1680 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_file
-- ----------------------------
BEGIN;
INSERT INTO `user_file` VALUES (1644, 30, 783);
INSERT INTO `user_file` VALUES (1645, 31, 787);
INSERT INTO `user_file` VALUES (1646, 31, 784);
INSERT INTO `user_file` VALUES (1647, 31, 789);
INSERT INTO `user_file` VALUES (1648, 31, 788);
INSERT INTO `user_file` VALUES (1649, 31, 785);
INSERT INTO `user_file` VALUES (1650, 31, 786);
INSERT INTO `user_file` VALUES (1651, 31, 790);
INSERT INTO `user_file` VALUES (1652, 31, 791);
INSERT INTO `user_file` VALUES (1653, 31, 792);
INSERT INTO `user_file` VALUES (1654, 31, 793);
INSERT INTO `user_file` VALUES (1655, 31, 794);
INSERT INTO `user_file` VALUES (1656, 31, 795);
INSERT INTO `user_file` VALUES (1657, 31, 796);
INSERT INTO `user_file` VALUES (1658, 31, 797);
INSERT INTO `user_file` VALUES (1659, 31, 798);
INSERT INTO `user_file` VALUES (1660, 31, 799);
INSERT INTO `user_file` VALUES (1661, 31, 800);
INSERT INTO `user_file` VALUES (1662, 31, 801);
INSERT INTO `user_file` VALUES (1663, 31, 802);
INSERT INTO `user_file` VALUES (1664, 31, 803);
INSERT INTO `user_file` VALUES (1665, 31, 804);
INSERT INTO `user_file` VALUES (1666, 31, 805);
INSERT INTO `user_file` VALUES (1667, 31, 806);
INSERT INTO `user_file` VALUES (1668, 31, 807);
INSERT INTO `user_file` VALUES (1669, 31, 808);
INSERT INTO `user_file` VALUES (1670, 31, 809);
INSERT INTO `user_file` VALUES (1671, 31, 810);
INSERT INTO `user_file` VALUES (1672, 31, 811);
INSERT INTO `user_file` VALUES (1673, 31, 812);
INSERT INTO `user_file` VALUES (1674, 31, 813);
INSERT INTO `user_file` VALUES (1675, 31, 814);
INSERT INTO `user_file` VALUES (1676, 31, 815);
INSERT INTO `user_file` VALUES (1677, 31, 816);
INSERT INTO `user_file` VALUES (1678, 31, 817);
INSERT INTO `user_file` VALUES (1679, 31, 818);
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (26, 30, 1);
INSERT INTO `user_role` VALUES (27, 31, 4);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
