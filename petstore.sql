/*
 Navicat MySQL Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50515
 Source Host           : localhost:3306
 Source Schema         : petstore

 Target Server Type    : MySQL
 Target Server Version : 50515
 File Encoding         : 65001

 Date: 21/06/2019 21:39:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL DEFAULT 0 COMMENT '商品 id',
  `quantity` int(11) NOT NULL DEFAULT 0 COMMENT '数量',
  `checked` int(1) NOT NULL DEFAULT 0 COMMENT '是否选择,1=已勾选,0=未勾选',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id_index`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `sales` int(11) NOT NULL DEFAULT 0,
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `brand` varchar(55) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `category` int(11) NOT NULL DEFAULT 0 COMMENT '//1狗粮2狗玩具3装备4医疗5清洁\r\n\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10036 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES (10001, '法国皇家ROYAL CANIN 小型犬离乳期奶糕1kg', 74.00, '使用犬类怀孕、哺乳期母犬和离乳期幼犬', 102, 'http://img-new.boqiicdn.com/Data/Shop/0/4/487/shoppicpath11540776135_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '法国皇家ROYAL CANIN', 1);
INSERT INTO `item` VALUES (10002, '宝路 幼犬粮肉类奶蔬菜谷物配方狗粮1.3kg', 33.60, '宝路幼犬犬粮懂得你对狗宝宝的爱，推出幼犬全面营养犬粮，精选天然优质原料，采用科学配方精心调配，特别添加独特的乳钙奶心酥，富含天然牛奶精华，配合狗宝宝所需的维生素、矿物质及亚油酸，为狗宝宝的健康成长提供充足动力。', 103, 'http://img-new.boqiicdn.com/Data/Shop/0/39/3986/shoppicpath11540808995_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '宝路', 1);
INSERT INTO `item` VALUES (10003, '加拿大纽顿nutram 低敏系列中大型成犬粮去骨鸡肉糙米狗粮11.4kg S6原装进口粮', 669.00, '纽顿低敏S6中大型成犬（去骨鸡肉&糙米）11.4kg，采用新鲜去骨鲜肉，不使用色素、香精、防腐剂等，为爱宠提供均衡营养的同时强化爱宠的消化和吸收能力，从内而外的呵护爱宠健康', 116, 'http://img-new.boqiicdn.com/Data/Shop/2/252/25269/shoppicpath11540552018_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '加拿大纽顿nutram', 1);
INSERT INTO `item` VALUES (10004, '酷极Kyjen 耐咬网球训导玩具 狗玩具单个', 6.90, 'Kyjen酷极 超耐咬网球训导玩具大小尺寸发声网球，专为小型犬设计，质量一如既往，会发声，互动性更强；经典欢乐球，无毒、优化天然橡胶制成、耐撕咬、防爆；激励爱犬、消除忧郁、消磨时间。', 100, 'http://img-new.boqiicdn.com/Data/Shop/0/92/9206/shoppicpath11548209941_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 'Kyjen酷极', 2);
INSERT INTO `item` VALUES (10005, '伊丽Elite 彩色布条耐咬玩具', 7.50, '伊丽彩色布条耐咬玩具，由棉布制成，结实耐用，也很健康，经常给狗狗咬有助于清洁牙齿和锻炼牙齿，降低牙结石等疾病，保持口腔清新。', 112, 'http://img-new.boqiicdn.com/Data/Shop/2/237/23719/shoppicpath11501145261_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '伊丽', 2);
INSERT INTO `item` VALUES (10006, '宜特Eetoys 耐咬宠物弯骨牛皮磨牙棒 狗狗玩具', 14.80, '宜特 狗狗耐咬宠物弯骨磨牙棒，采用牛皮，经过处理制成产品，尼龙PU材质，高韧性，高硬度，动物蛋白及胶质更能让狗狗的皮毛亮泽，骨骼肌肉更加坚实紧致。', 117, 'http://img-new.boqiicdn.com/Data/Shop/2/282/28271/shoppicpath11539742207_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '宜特', 2);
INSERT INTO `item` VALUES (10007, 'TARKY 网状狗口罩', 25.50, 'TARKY 网状狗口罩，透气网状狗口罩，透气舒适，安全亲肌，大小可调节，稳固防脱，弧线设计，贴合嘴巴，可自由喝水。', 106, 'http://img-new.boqiicdn.com/Data/Shop/2/262/26206/shoppicpath11540646427_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 'TARKY', 3);
INSERT INTO `item` VALUES (10008, '德丰Dog\'s life 德式泡棉狗狗单颈带', 10.40, 'Dog\'s life 德式泡棉狗狗单颈带，优质的尼龙，颈围加了泡棉，狗狗戴上会更加柔软舒适；缓冲作用：内垫软泡棉，可以起到缓冲作用，同时也可以保护宠物脖子上毛发不容易损伤；做工精细：锌合金金属扣件，结实牢固，经久耐用，优秀出行轻松牵出来；简约设计：做工非常精致，设计简单大气。', 101, 'http://img-new.boqiicdn.com/Data/Shop/1/152/15293/shoppicpath11539944806_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 'Dog\'s life', 3);
INSERT INTO `item` VALUES (10009, '小佩Petkit Air狗胸背带狗狗牵引绳透气遛宠外出旅行探险狗背带', 123.00, '小佩Air胸背带，人用高端运动面料，让爱宠与您享受同样的舒适与运动，运动面料和工艺，柔软舒适的同时更加贴合舒适。', 100, 'http://img-new.boqiicdn.com/Data/Shop/2/253/25390/shoppicpath41515750790_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '小佩Petkit Air', 3);
INSERT INTO `item` VALUES (10010, '拜宠清 狗狗体内驱虫药犬用打虫药 单片装', 32.00, '适用症状：治疗宠物犬的线虫和条虫感染	用法用量：直接服用，每10kg体重，1片。两三个月驱虫一次', 100, 'http://img-new.boqiicdn.com/Data/Shop/0/16/1683/shoppicpath11540371370_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '拜宠清', 4);
INSERT INTO `item` VALUES (10011, '福来恩犬心保 牛肉块12kg-22kg内中型犬体内驱虫药整盒 蛔虫钩虫心丝虫打虫药', 185.00, '适用症状：驱逐体内6大寄生虫	用法用量：每月一次', 100, 'http://img-new.boqiicdn.com/Data/Shop/0/38/3884/shoppicpath11540871023_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '福来恩', 4);
INSERT INTO `item` VALUES (10012, '福来恩小型犬套装（犬心保滴剂套装）', 313.00, '福来恩小型犬套装（犬心宝滴剂套装），有效驱杀体内外寄生虫，爱宠健康无烦恼。', 100, 'http://img-new.boqiicdn.com/Data/Shop/2/245/24532/shoppicpath11540274290_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '福来恩', 4);
INSERT INTO `item` VALUES (10013, '怡亲 全犬种香波500ML', 15.90, '怡亲 全犬种香波500ML，天然植物萃取配方，滋养修复，柔顺洁净，让狗狗毛发持续靓丽有光泽。', 100, 'http://img-new.boqiicdn.com/Data/Shop/2/223/22339/shoppicpath11540893801_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '怡亲', 5);
INSERT INTO `item` VALUES (10014, 'LION艾宠 犬用每日洗二合一香波 柔和花香型 550ml', 59.90, 'LION艾宠每日洗二合一香波柔和花香型，泡沫丰富，容易冲洗。呵护敏感肌肤和皮毛的狗狗的同时，瞬间去污除臭。含有天然草泥成分，护肤效果更佳，保护吹干后的肌肤和毛发，免受梳理摩擦或刺激性物质的影响。采用护发素成分，含有保湿成分氨基酸，令毛发蓬松柔顺对宠物皮肤无刺激。', 116, 'http://img-new.boqiicdn.com/Data/Shop/1/178/17836/shoppicpath11540541141_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 'LION艾宠', 5);
INSERT INTO `item` VALUES (10015, '逸诺Enoug SOS蓬松有型香波狗狗沐浴露 530ml 无泪留香', 33.00, '逸诺SOS蓬松有型香波，采用无泪配方，蕴含天然矿物元素令毛发柔顺而富有韧性；富含多种氨基酸，能直接渗透至皮毛质，重建和强化毛织内部结构；特制配方，在深层清洁宠物皮毛时强化对毛发营养调理，使毛发倍感光滑柔软，自然蓬松；生物留香缓释因子技术，有效清除及预防产生异味的原体，增添宠物体表清香，持续留香10天以上。', 100, 'http://img-new.boqiicdn.com/Data/Shop/1/167/16718/shoppicpath11539742171_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '逸诺', 5);
INSERT INTO `item` VALUES (10016, '皇家ROYAL CANIN 室内成猫猫粮2kg i27', 124.00, '室内猫咪常有便便异味、肠道毛球、体重超重的问题，针对这3大问题，皇家室内成猫粮i27，专为室内成猫量身定制“三大营养配方”，全面解决室内成猫健康问题。高易消化配方，减少粪便异味；天然植物纤维配方，促进肠道毛球排出；优化能量配方，有助维持理想体态。', 90, 'http://img-new.boqiicdn.com/Data/Shop/0/4/497/shoppicpath11540361032_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '法国皇家', 6);
INSERT INTO `item` VALUES (10017, '伟嘉 幼猫粮海洋鱼味猫粮1.2kg', 33.90, '升级的伟嘉海洋鱼味幼猫猫粮，可提供猫咪每日均衡营养所需。含“亮毛因子”，促进爱猫的皮毛闪闪有光泽；更有“明目因子”，帮助猫咪眼睛更明亮有神。', 90, 'http://img-new.boqiicdn.com/Data/Shop/0/39/3985/shoppicpath11540783850_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '伟嘉', 6);
INSERT INTO `item` VALUES (10018, '喜乐比 全期猫粮三文鱼+金枪鱼猫粮1.4kg*2联包', 56.00, '喜乐比 全期猫粮三文鱼+金枪鱼猫粮1.4kg*2联包，美味蔬果活力加倍！严选美味海鱼及鸡肉，营养丰富高蛋白；丰富天然蔬果，富含天然纤维素及抗氧化因子；添加牛磺酸，维护视力及心脏健康。', 90, 'http://img-new.boqiicdn.com/Data/Shop/2/249/24998/shoppicpath11517305910_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '喜乐比', 6);
INSERT INTO `item` VALUES (10019, '田田猫 弹簧钢丝天然鸡毛逗猫棒 猫玩具', 10.80, '田田猫弹簧钢丝天然鸡毛逗猫棒设计新颖，乐趣无穷，各种造型，吸引力十足，材质环保，符合现代家店健康标准。', 105, 'http://img-new.boqiicdn.com/Data/Shop/1/188/18878/shoppicpath11450069684_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '田田猫', 7);
INSERT INTO `item` VALUES (10020, '田田猫 兔皮老鼠（5只装） 猫玩具', 6.50, '田田猫 兔皮老鼠（5只装） 猫玩具 手工制作，天然兔毛材质，猫咪更加钟爱；经过严格质检，形状规范，造型栩栩如生。', 90, 'http://img-new.boqiicdn.com/Data/Shop/1/186/18603/shoppicpath21449120139_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '田田猫', 7);
INSERT INTO `item` VALUES (10021, '怡亲Yoken 拱桥型猫抓板猫玩具\r\n怡亲Yoken 拱桥型猫抓板猫玩具\r\n怡亲Yoken 拱桥型猫抓板猫玩具', 12.90, '怡亲 拱桥型猫抓板，8.5cm坡度设计，符合猫咪个性，舒适趴着磨爪子。', 90, 'http://img-new.boqiicdn.com/Data/Shop/0/86/8689/shoppicpath11540532856_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '怡亲', 7);
INSERT INTO `item` VALUES (10022, '田田猫 反光条织带猫项圈', 7.70, '田田猫反光条织带猫项圈，反光条的设计，可以在夜间反射周围的光线，对路人和车辆起到一定的警示作用。商品有3个颜色可供任意体型的猫咪选择。', 90, 'http://img-new.boqiicdn.com/Data/Shop/1/173/17389/shoppicpath11542793106_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '田田猫', 8);
INSERT INTO `item` VALUES (10023, '多可特宠物出行便携背包红黑白三色', 78.00, '多可特宠物出行便携背包红黑白三色，携带方便，多孔设计，舒适透气。手提握感舒适，肩带加厚，柔软可调。外壳保护膜减少剐蹭，确保背包透亮，背部加厚毛垫可拆洗魔术贴，固定不移位，不锈钢拉锁，结实耐用。', 90, 'http://img-new.boqiicdn.com/Data/Shop/2/282/28219/shoppicpath11532317197_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '多可特', 8);
INSERT INTO `item` VALUES (10024, '加拿大opawz 宠物兔毛蝴蝶结流苏猫咪狗狗蕾丝项圈', 30.60, 'opawz 宠物兔毛蝴蝶结流苏粉色绸带项圈，采用优质蕾丝材质，柔软舒适，可调节设计，佩戴方便，甜美的粉色蕾丝，时尚混搭，简约中带着俏皮、活泼，清新亮眼。', 90, 'http://img-new.boqiicdn.com/Data/Shop/1/198/19876/shoppicpath21462773053_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 'opawz', 8);
INSERT INTO `item` VALUES (10025, '福来恩 猫用增效灭虱滴剂体外驱虫整盒装3支装 驱杀跳蚤蜱虫虫卵幼虫', 144.00, '适用症状：预防和驱杀跳蚤、蚤卵和蜱虫	用法用量：每月一次', 90, 'http://img-new.boqiicdn.com/Data/Shop/0/5/571/shoppicpath11540274217_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '福来恩', 9);
INSERT INTO `item` VALUES (10026, '贝帮Beaphar 精油驱虫系列-成猫用滴剂3支/盒 荷兰原装进口', 120.00, '贝帮精油驱虫系列-成猫用滴剂3支/盒，草本配方，天然驱除蜱虫、跳蚤、蚊虫等各类宠物寄生虫，有效预防，呵护爱宠。', 90, 'http://img-new.boqiicdn.com/Data/Shop/2/237/23796/shoppicpath11540275356_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '贝帮', 9);
INSERT INTO `item` VALUES (10027, '索来多 吡虫啉氟氯苯氰菊酯 猫项圈', 398.00, '杀灭跳蚤，保护宠物和家具', 90, 'http://img-new.boqiicdn.com/Data/Shop/3/329/32953/shoppicpath11557223396_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '拜耳', 9);
INSERT INTO `item` VALUES (10028, '逸诺 SOS持久留香型香波沐浴露 猫用 530ml', 33.00, '第一步：用温水浸湿宠物毛发，取适量香波，涂遍宠物全身；\r\n\r\n \r\n\r\n第二步：揉搓至泡沫丰富后，用清水冲洗干净；\r\n\r\n \r\n\r\n第三步：用电吹风吹干毛发，并用针梳梳理整齐，若要取得最佳效果，请按步骤重复清洗一遍即可。', 90, 'http://img-new.boqiicdn.com/Data/Shop/1/167/16712/shoppicpath11540893746_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', '逸诺SOS', 10);
INSERT INTO `item` VALUES (10029, 'LION艾宠 护肤二合一香波花卉草本香型 猫用 330ml', 52.90, 'LION艾宠 护肤二合一香波花卉草本香型 这是一款由植物萃取的天然犬猫沐浴露。在药物成分的作用下能够轻松和去除猫狗身上的跳蚤和螨虫。丰富的泡沫充分洗净爱犬爱猫的毛发污垢和体味，沐浴露中的护毛成分也让毛发洗后柔顺发亮', 90, 'http://img-new.boqiicdn.com/Data/Shop/1/172/17255/shoppicpath11540893573_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 'LION艾宠', 10);
INSERT INTO `item` VALUES (10030, 'LION艾宠 每日洗二合一香波柔和花香型 爱猫用 330ML', 52.90, 'LION艾宠 每日洗二合一香波柔和花香型，泡沫丰富，容易冲洗。呵护敏感肌肤和皮毛的猫咪的同时，瞬间去污除臭。含有天然草泥成分，护肤效果更佳，保护吹干后的肌肤和毛发，免受梳理摩擦或刺激性物质的影响。采用护发素成分，含有保湿成分氨基酸，令毛发蓬松柔顺对宠物皮肤无刺激。', 90, 'http://img-new.boqiicdn.com/Data/Shop/1/172/17252/shoppicpath11529396701_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 'LION艾宠', 10);
INSERT INTO `item` VALUES (10035, 'e-r图', 3232.00, '3232', 0, '3232', '32', 1);

-- ----------------------------
-- Table structure for item_stock
-- ----------------------------
DROP TABLE IF EXISTS `item_stock`;
CREATE TABLE `item_stock`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock` int(11) NOT NULL DEFAULT 0,
  `item_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `item_id`(`item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of item_stock
-- ----------------------------
INSERT INTO `item_stock` VALUES (1, 13, 10001);
INSERT INTO `item_stock` VALUES (2, 57, 10002);
INSERT INTO `item_stock` VALUES (3, 59, 10003);
INSERT INTO `item_stock` VALUES (4, 41, 10004);
INSERT INTO `item_stock` VALUES (5, 54, 10005);
INSERT INTO `item_stock` VALUES (6, 62, 10006);
INSERT INTO `item_stock` VALUES (7, 67, 10007);
INSERT INTO `item_stock` VALUES (8, 92, 10008);
INSERT INTO `item_stock` VALUES (9, 110, 10009);
INSERT INTO `item_stock` VALUES (10, 202, 10010);
INSERT INTO `item_stock` VALUES (11, 456, 10011);
INSERT INTO `item_stock` VALUES (12, 90, 10012);
INSERT INTO `item_stock` VALUES (13, 88, 10013);
INSERT INTO `item_stock` VALUES (14, 61, 10014);
INSERT INTO `item_stock` VALUES (15, 33, 10015);
INSERT INTO `item_stock` VALUES (16, 65, 10016);
INSERT INTO `item_stock` VALUES (17, 56, 10017);
INSERT INTO `item_stock` VALUES (18, 188, 10018);
INSERT INTO `item_stock` VALUES (19, 184, 10019);
INSERT INTO `item_stock` VALUES (20, 211, 10020);
INSERT INTO `item_stock` VALUES (21, 32, 10021);
INSERT INTO `item_stock` VALUES (22, 67, 10022);
INSERT INTO `item_stock` VALUES (23, 45, 10023);
INSERT INTO `item_stock` VALUES (24, 23, 10024);
INSERT INTO `item_stock` VALUES (25, 12, 10025);
INSERT INTO `item_stock` VALUES (26, 79, 10026);
INSERT INTO `item_stock` VALUES (27, 67, 10027);
INSERT INTO `item_stock` VALUES (28, 23, 10028);
INSERT INTO `item_stock` VALUES (29, 67, 10029);
INSERT INTO `item_stock` VALUES (30, 32, 10030);
INSERT INTO `item_stock` VALUES (34, 32, 10035);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单子表 id',
  `user_id` int(11) NULL DEFAULT NULL,
  `order_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `item_id` int(11) NULL DEFAULT NULL COMMENT '商品 id',
  `item_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `item_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片地址',
  `current_unit_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '生成订单时的商品单价，单位是元,保留两位小数',
  `quantity` int(10) NULL DEFAULT NULL COMMENT '商品数量',
  `total_price` decimal(20, 2) NULL DEFAULT NULL COMMENT '商品总价,单位是元,保留两位小数',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_no_index`(`order_no`) USING BTREE,
  INDEX `order_no_user_id_index`(`user_id`, `order_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 263 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES (217, 6, '2019061800038611', 10003, '加拿大纽顿nutram 低敏系列中大型成犬粮去骨鸡肉糙米狗粮11.4kg S6原装进口粮', 'http://img-new.boqiicdn.com/Data/Shop/2/252/25269/shoppicpath11540552018_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 669.00, 3, 2007.00, '2019-06-18 20:19:21', '2019-06-18 20:19:21');
INSERT INTO `order_info` VALUES (218, 6, '2019061800038711', 10007, 'TARKY 网状狗口罩', 'http://img-new.boqiicdn.com/Data/Shop/2/262/26206/shoppicpath11540646427_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 25.50, 3, 76.50, '2019-06-18 20:31:33', '2019-06-18 20:31:33');
INSERT INTO `order_info` VALUES (219, 6, '2019061800038811', 10004, '酷极Kyjen 耐咬网球训导玩具 狗玩具单个', 'http://img-new.boqiicdn.com/Data/Shop/0/92/9206/shoppicpath11548209941_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 6.90, 2, 13.80, '2019-06-18 21:30:39', '2019-06-18 21:30:39');
INSERT INTO `order_info` VALUES (220, 6, '2019061800038911', 10002, '宝路 幼犬粮肉类奶蔬菜谷物配方狗粮1.3kg', 'http://img-new.boqiicdn.com/Data/Shop/0/39/3986/shoppicpath11540808995_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 33.60, 4, 134.40, '2019-06-18 22:09:48', '2019-06-18 22:09:48');
INSERT INTO `order_info` VALUES (221, 6, '2019061800038911', 10006, '宜特Eetoys 耐咬宠物弯骨牛皮磨牙棒 狗狗玩具', 'http://img-new.boqiicdn.com/Data/Shop/2/282/28271/shoppicpath11539742207_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 14.80, 3, 44.40, '2019-06-18 22:09:48', '2019-06-18 22:09:48');
INSERT INTO `order_info` VALUES (222, 6, '2019061900039011', 10004, '酷极Kyjen 耐咬网球训导玩具 狗玩具单个', 'http://img-new.boqiicdn.com/Data/Shop/0/92/9206/shoppicpath11548209941_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 6.90, 3, 20.70, '2019-06-19 11:13:45', '2019-06-19 11:13:45');
INSERT INTO `order_info` VALUES (223, 6, '2019061900039111', 10006, '宜特Eetoys 耐咬宠物弯骨牛皮磨牙棒 狗狗玩具', 'http://img-new.boqiicdn.com/Data/Shop/2/282/28271/shoppicpath11539742207_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 14.80, 3, 44.40, '2019-06-19 11:53:52', '2019-06-19 11:53:52');
INSERT INTO `order_info` VALUES (224, 6, '2019061900039111', 10005, '伊丽Elite 彩色布条耐咬玩具', 'http://img-new.boqiicdn.com/Data/Shop/2/237/23719/shoppicpath11501145261_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 7.50, 2, 15.00, '2019-06-19 11:53:52', '2019-06-19 11:53:52');
INSERT INTO `order_info` VALUES (225, 6, '2019061900039211', 10003, '加拿大纽顿nutram 低敏系列中大型成犬粮去骨鸡肉糙米狗粮11.4kg S6原装进口粮', 'http://img-new.boqiicdn.com/Data/Shop/2/252/25269/shoppicpath11540552018_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 669.00, 2, 1338.00, '2019-06-19 17:00:55', '2019-06-19 17:00:55');
INSERT INTO `order_info` VALUES (226, 6, '2019061900039211', 10005, '伊丽Elite 彩色布条耐咬玩具', 'http://img-new.boqiicdn.com/Data/Shop/2/237/23719/shoppicpath11501145261_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 7.50, 1, 7.50, '2019-06-19 17:00:55', '2019-06-19 17:00:55');
INSERT INTO `order_info` VALUES (227, 6, '2019062000039311', 10005, '伊丽Elite 彩色布条耐咬玩具', 'http://img-new.boqiicdn.com/Data/Shop/2/237/23719/shoppicpath11501145261_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 7.50, 3, 22.50, '2019-06-20 11:37:54', '2019-06-20 11:37:54');
INSERT INTO `order_info` VALUES (228, 6, '2019062000039411', 10005, '伊丽Elite 彩色布条耐咬玩具', 'http://img-new.boqiicdn.com/Data/Shop/2/237/23719/shoppicpath11501145261_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 7.50, 3, 22.50, '2019-06-20 11:43:50', '2019-06-20 11:43:50');
INSERT INTO `order_info` VALUES (229, 6, '2019062000039511', 10003, '加拿大纽顿nutram 低敏系列中大型成犬粮去骨鸡肉糙米狗粮11.4kg S6原装进口粮', 'http://img-new.boqiicdn.com/Data/Shop/2/252/25269/shoppicpath11540552018_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 669.00, 2, 1338.00, '2019-06-20 19:52:13', '2019-06-20 19:52:13');
INSERT INTO `order_info` VALUES (230, 6, '2019062000039511', 10019, '田田猫 弹簧钢丝天然鸡毛逗猫棒 猫玩具', 'http://img-new.boqiicdn.com/Data/Shop/1/188/18878/shoppicpath11450069684_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 10.80, 3, 32.40, '2019-06-20 19:52:13', '2019-06-20 19:52:13');
INSERT INTO `order_info` VALUES (231, 6, '2019062000039511', 10001, '法国皇家ROYAL CANIN 小型犬离乳期奶糕1kg', 'http://img-new.boqiicdn.com/Data/Shop/0/4/487/shoppicpath11540776135_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 74.00, 1, 74.00, '2019-06-20 19:52:13', '2019-06-20 19:52:13');
INSERT INTO `order_info` VALUES (232, 12, '2019062000039611', 10006, '宜特Eetoys 耐咬宠物弯骨牛皮磨牙棒 狗狗玩具', 'http://img-new.boqiicdn.com/Data/Shop/2/282/28271/shoppicpath11539742207_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 14.80, 3, 44.40, '2019-06-20 20:32:15', '2019-06-20 20:32:15');
INSERT INTO `order_info` VALUES (233, 12, '2019062000039711', 10001, '法国皇家ROYAL CANIN 小型犬离乳期奶糕1kg', 'http://img-new.boqiicdn.com/Data/Shop/0/4/487/shoppicpath11540776135_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 74.00, 3, 222.00, '2019-06-20 20:32:51', '2019-06-20 20:32:51');
INSERT INTO `order_info` VALUES (234, 12, '2019062000039811', 10005, '伊丽Elite 彩色布条耐咬玩具', 'http://img-new.boqiicdn.com/Data/Shop/2/237/23719/shoppicpath11501145261_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 7.50, 3, 22.50, '2019-06-20 20:45:01', '2019-06-20 20:45:01');
INSERT INTO `order_info` VALUES (235, 12, '2019062000039811', 10007, 'TARKY 网状狗口罩', 'http://img-new.boqiicdn.com/Data/Shop/2/262/26206/shoppicpath11540646427_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 25.50, 3, 76.50, '2019-06-20 20:45:01', '2019-06-20 20:45:01');
INSERT INTO `order_info` VALUES (236, 12, '2019062000039811', 10001, '法国皇家ROYAL CANIN 小型犬离乳期奶糕1kg', 'http://img-new.boqiicdn.com/Data/Shop/0/4/487/shoppicpath11540776135_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 74.00, 1, 74.00, '2019-06-20 20:45:01', '2019-06-20 20:45:01');
INSERT INTO `order_info` VALUES (237, 12, '2019062000039911', 10003, '加拿大纽顿nutram 低敏系列中大型成犬粮去骨鸡肉糙米狗粮11.4kg S6原装进口粮', 'http://img-new.boqiicdn.com/Data/Shop/2/252/25269/shoppicpath11540552018_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 669.00, 3, 2007.00, '2019-06-20 20:46:01', '2019-06-20 20:46:01');
INSERT INTO `order_info` VALUES (238, 12, '2019062100040011', 10003, '加拿大纽顿nutram 低敏系列中大型成犬粮去骨鸡肉糙米狗粮11.4kg S6原装进口粮', 'http://img-new.boqiicdn.com/Data/Shop/2/252/25269/shoppicpath11540552018_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 669.00, 3, 2007.00, '2019-06-21 08:18:27', '2019-06-21 08:18:27');
INSERT INTO `order_info` VALUES (239, 12, '2019062100040011', 10006, '宜特Eetoys 耐咬宠物弯骨牛皮磨牙棒 狗狗玩具', 'http://img-new.boqiicdn.com/Data/Shop/2/282/28271/shoppicpath11539742207_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 14.80, 3, 44.40, '2019-06-21 08:18:27', '2019-06-21 08:18:27');
INSERT INTO `order_info` VALUES (240, 12, '2019062100040011', 10005, '伊丽Elite 彩色布条耐咬玩具', 'http://img-new.boqiicdn.com/Data/Shop/2/237/23719/shoppicpath11501145261_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 7.50, 3, 22.50, '2019-06-21 08:18:27', '2019-06-21 08:18:27');
INSERT INTO `order_info` VALUES (241, 12, '2019062100040011', 10020, '田田猫 兔皮老鼠（5只装） 猫玩具', 'http://img-new.boqiicdn.com/Data/Shop/1/186/18603/shoppicpath21449120139_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 6.50, 3, 19.50, '2019-06-21 08:18:27', '2019-06-21 08:18:27');
INSERT INTO `order_info` VALUES (242, 12, '2019062100040111', 10008, '德丰Dog\'s life 德式泡棉狗狗单颈带', 'http://img-new.boqiicdn.com/Data/Shop/1/152/15293/shoppicpath11539944806_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 10.40, 1, 10.40, '2019-06-21 08:35:51', '2019-06-21 08:35:51');
INSERT INTO `order_info` VALUES (243, 12, '2019062100040111', 10007, 'TARKY 网状狗口罩', 'http://img-new.boqiicdn.com/Data/Shop/2/262/26206/shoppicpath11540646427_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 25.50, 3, 76.50, '2019-06-21 08:35:51', '2019-06-21 08:35:51');
INSERT INTO `order_info` VALUES (244, 12, '2019062100040211', 10006, '宜特Eetoys 耐咬宠物弯骨牛皮磨牙棒 狗狗玩具', 'http://img-new.boqiicdn.com/Data/Shop/2/282/28271/shoppicpath11539742207_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 14.80, 3, 44.40, '2019-06-21 08:40:46', '2019-06-21 08:40:46');
INSERT INTO `order_info` VALUES (245, 12, '2019062100040311', 10005, '伊丽Elite 彩色布条耐咬玩具', 'http://img-new.boqiicdn.com/Data/Shop/2/237/23719/shoppicpath11501145261_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 7.50, 3, 22.50, '2019-06-21 08:41:19', '2019-06-21 08:41:19');
INSERT INTO `order_info` VALUES (246, 12, '2019062100040411', 10004, '酷极Kyjen 耐咬网球训导玩具 狗玩具单个', 'http://img-new.boqiicdn.com/Data/Shop/0/92/9206/shoppicpath11548209941_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 6.90, 3, 20.70, '2019-06-21 08:53:04', '2019-06-21 08:53:04');
INSERT INTO `order_info` VALUES (247, 12, '2019062100040511', 10014, 'LION艾宠 犬用每日洗二合一香波 柔和花香型 550ml', 'http://img-new.boqiicdn.com/Data/Shop/1/178/17836/shoppicpath11540541141_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 59.90, 3, 179.70, '2019-06-21 15:56:12', '2019-06-21 15:56:12');
INSERT INTO `order_info` VALUES (248, 12, '2019062100040611', 10014, 'LION艾宠 犬用每日洗二合一香波 柔和花香型 550ml', 'http://img-new.boqiicdn.com/Data/Shop/1/178/17836/shoppicpath11540541141_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 59.90, 3, 179.70, '2019-06-21 15:56:46', '2019-06-21 15:56:46');
INSERT INTO `order_info` VALUES (249, 12, '2019062100040711', 10014, 'LION艾宠 犬用每日洗二合一香波 柔和花香型 550ml', 'http://img-new.boqiicdn.com/Data/Shop/1/178/17836/shoppicpath11540541141_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 59.90, 3, 179.70, '2019-06-21 15:58:17', '2019-06-21 15:58:17');
INSERT INTO `order_info` VALUES (250, 12, '2019062100040811', 10006, '宜特Eetoys 耐咬宠物弯骨牛皮磨牙棒 狗狗玩具', 'http://img-new.boqiicdn.com/Data/Shop/2/282/28271/shoppicpath11539742207_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 14.80, 3, 44.40, '2019-06-21 16:11:52', '2019-06-21 16:11:52');
INSERT INTO `order_info` VALUES (251, 12, '2019062100040911', 10002, '宝路 幼犬粮肉类奶蔬菜谷物配方狗粮1.3kg', 'http://img-new.boqiicdn.com/Data/Shop/0/39/3986/shoppicpath11540808995_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 33.60, 3, 100.80, '2019-06-21 16:18:11', '2019-06-21 16:18:11');
INSERT INTO `order_info` VALUES (252, 12, '2019062100041011', 10014, 'LION艾宠 犬用每日洗二合一香波 柔和花香型 550ml', 'http://img-new.boqiicdn.com/Data/Shop/1/178/17836/shoppicpath11540541141_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 59.90, 4, 239.60, '2019-06-21 16:20:42', '2019-06-21 16:20:42');
INSERT INTO `order_info` VALUES (253, 12, '2019062100041111', 10014, 'LION艾宠 犬用每日洗二合一香波 柔和花香型 550ml', 'http://img-new.boqiicdn.com/Data/Shop/1/178/17836/shoppicpath11540541141_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 59.90, 3, 179.70, '2019-06-21 16:21:34', '2019-06-21 16:21:34');
INSERT INTO `order_info` VALUES (254, 12, '2019062100041211', 10019, '田田猫 弹簧钢丝天然鸡毛逗猫棒 猫玩具', 'http://img-new.boqiicdn.com/Data/Shop/1/188/18878/shoppicpath11450069684_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 10.80, 3, 32.40, '2019-06-21 16:22:59', '2019-06-21 16:22:59');
INSERT INTO `order_info` VALUES (255, 12, '2019062100041311', 10019, '田田猫 弹簧钢丝天然鸡毛逗猫棒 猫玩具', 'http://img-new.boqiicdn.com/Data/Shop/1/188/18878/shoppicpath11450069684_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 10.80, 3, 32.40, '2019-06-21 16:23:19', '2019-06-21 16:23:19');
INSERT INTO `order_info` VALUES (256, 12, '2019062100041411', 10005, '伊丽Elite 彩色布条耐咬玩具', 'http://img-new.boqiicdn.com/Data/Shop/2/237/23719/shoppicpath11501145261_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 7.50, 3, 22.50, '2019-06-21 16:25:05', '2019-06-21 16:25:05');
INSERT INTO `order_info` VALUES (257, 12, '2019062100041511', 10019, '田田猫 弹簧钢丝天然鸡毛逗猫棒 猫玩具', 'http://img-new.boqiicdn.com/Data/Shop/1/188/18878/shoppicpath11450069684_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 10.80, 3, 32.40, '2019-06-21 16:40:03', '2019-06-21 16:40:03');
INSERT INTO `order_info` VALUES (258, 12, '2019062100041611', 10003, '加拿大纽顿nutram 低敏系列中大型成犬粮去骨鸡肉糙米狗粮11.4kg S6原装进口粮', 'http://img-new.boqiicdn.com/Data/Shop/2/252/25269/shoppicpath11540552018_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 669.00, 3, 2007.00, '2019-06-21 20:37:25', '2019-06-21 20:37:25');
INSERT INTO `order_info` VALUES (259, 12, '2019062100041611', 10006, '宜特Eetoys 耐咬宠物弯骨牛皮磨牙棒 狗狗玩具', 'http://img-new.boqiicdn.com/Data/Shop/2/282/28271/shoppicpath11539742207_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 14.80, 3, 44.40, '2019-06-21 20:37:25', '2019-06-21 20:37:25');
INSERT INTO `order_info` VALUES (260, 12, '2019062100041711', 10019, '田田猫 弹簧钢丝天然鸡毛逗猫棒 猫玩具', 'http://img-new.boqiicdn.com/Data/Shop/1/188/18878/shoppicpath11450069684_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 10.80, 3, 32.40, '2019-06-21 20:38:07', '2019-06-21 20:38:07');
INSERT INTO `order_info` VALUES (261, 12, '2019062100041811', 10003, '加拿大纽顿nutram 低敏系列中大型成犬粮去骨鸡肉糙米狗粮11.4kg S6原装进口粮', 'http://img-new.boqiicdn.com/Data/Shop/2/252/25269/shoppicpath11540552018_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 669.00, 3, 2007.00, '2019-06-21 20:46:14', '2019-06-21 20:46:14');
INSERT INTO `order_info` VALUES (262, 12, '2019062100041811', 10006, '宜特Eetoys 耐咬宠物弯骨牛皮磨牙棒 狗狗玩具', 'http://img-new.boqiicdn.com/Data/Shop/2/282/28271/shoppicpath11539742207_y.jpg?imageView2/2/w/360/h/360/q/100/interlace/0', 14.80, 5, 74.00, '2019-06-21 20:46:14', '2019-06-21 20:46:14');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单 id',
  `order_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '订单号',
  `user_id` int(11) NULL DEFAULT 0 COMMENT '用户 id',
  `shipping_id` int(11) NULL DEFAULT 0,
  `payment` decimal(20, 2) NULL DEFAULT 0.00 COMMENT '实际付款金额,单位是元,保留两位小数',
  `payment_type` int(4) NULL DEFAULT NULL COMMENT '支付类型,1支付宝 2微信',
  `postage` int(10) NULL DEFAULT 0 COMMENT '运费,单位是元',
  `status` int(10) NULL DEFAULT 0 COMMENT '订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭',
  `payment_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `send_time` datetime NULL DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime NULL DEFAULT NULL COMMENT '交易关闭时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 404 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (371, '2019061800038611', 6, 4, 2007.00, 1, 0, 20, '2019-06-19 11:17:25', NULL, NULL, NULL, '2019-06-18 20:19:21', '2019-06-19 11:17:25');
INSERT INTO `orders` VALUES (372, '2019061800038711', 6, 4, 76.50, NULL, 0, 10, NULL, NULL, NULL, NULL, '2019-06-18 20:31:33', '2019-06-18 20:31:33');
INSERT INTO `orders` VALUES (373, '2019061800038811', 6, 4, 13.80, NULL, 0, 10, NULL, NULL, NULL, NULL, '2019-06-18 21:30:39', '2019-06-18 21:30:39');
INSERT INTO `orders` VALUES (374, '2019061800038911', 6, 4, 178.80, NULL, 0, 10, NULL, NULL, NULL, NULL, '2019-06-18 22:09:48', '2019-06-18 22:09:48');
INSERT INTO `orders` VALUES (375, '2019061900039011', 6, 8, 20.70, NULL, 0, 10, NULL, NULL, NULL, NULL, '2019-06-19 11:13:45', '2019-06-19 11:13:45');
INSERT INTO `orders` VALUES (376, '2019061900039111', 6, 4, 59.40, 1, 0, 20, '2019-06-19 11:54:00', NULL, NULL, NULL, '2019-06-19 11:53:52', '2019-06-19 11:54:00');
INSERT INTO `orders` VALUES (377, '2019061900039211', 6, 4, 1345.50, 1, 0, 20, '2019-06-19 17:01:02', NULL, NULL, NULL, '2019-06-19 17:00:55', '2019-06-19 17:01:02');
INSERT INTO `orders` VALUES (378, '2019062000039311', 6, 12, 22.50, NULL, 0, 10, NULL, NULL, NULL, NULL, '2019-06-20 11:37:54', '2019-06-20 11:37:54');
INSERT INTO `orders` VALUES (379, '2019062000039411', 6, 12, 22.50, 1, 0, 20, '2019-06-20 11:48:14', NULL, NULL, NULL, '2019-06-20 11:43:50', '2019-06-20 11:48:14');
INSERT INTO `orders` VALUES (380, '2019062000039511', 6, 13, 1444.40, 1, 0, 20, '2019-06-20 19:52:20', NULL, NULL, NULL, '2019-06-20 19:52:13', '2019-06-20 19:52:20');
INSERT INTO `orders` VALUES (381, '2019062000039611', 12, 15, 44.40, 1, 0, 20, '2019-06-21 12:42:27', NULL, NULL, NULL, '2019-06-20 20:32:15', '2019-06-21 12:42:27');
INSERT INTO `orders` VALUES (382, '2019062000039711', 12, 15, 222.00, NULL, 0, 10, NULL, NULL, NULL, NULL, '2019-06-20 20:32:51', '2019-06-20 20:32:51');
INSERT INTO `orders` VALUES (383, '2019062000039811', 12, 15, 173.00, 1, 0, 20, '2019-06-20 20:45:06', NULL, NULL, NULL, '2019-06-20 20:45:01', '2019-06-20 20:45:06');
INSERT INTO `orders` VALUES (384, '2019062000039911', 12, 15, 2007.00, 1, 0, 20, '2019-06-20 20:46:03', NULL, NULL, NULL, '2019-06-20 20:46:01', '2019-06-20 20:46:03');
INSERT INTO `orders` VALUES (385, '2019062100040011', 12, 15, 2093.40, NULL, 0, 10, NULL, NULL, NULL, NULL, '2019-06-21 08:18:27', '2019-06-21 08:18:27');
INSERT INTO `orders` VALUES (386, '2019062100040111', 12, 15, 86.90, 1, 0, 20, '2019-06-21 12:33:57', NULL, NULL, NULL, '2019-06-21 08:35:51', '2019-06-21 12:33:57');
INSERT INTO `orders` VALUES (387, '2019062100040211', 12, 15, 44.40, NULL, 0, 10, NULL, NULL, NULL, NULL, '2019-06-21 08:40:46', '2019-06-21 08:40:46');
INSERT INTO `orders` VALUES (388, '2019062100040311', 12, 15, 22.50, NULL, 0, 10, NULL, NULL, NULL, NULL, '2019-06-21 08:41:19', '2019-06-21 08:41:19');
INSERT INTO `orders` VALUES (389, '2019062100040411', 12, 15, 20.70, NULL, 0, 10, NULL, NULL, NULL, NULL, '2019-06-21 08:53:04', '2019-06-21 08:53:04');
INSERT INTO `orders` VALUES (390, '2019062100040511', 12, 15, 179.70, 1, 0, 20, '2019-06-21 15:56:21', NULL, NULL, NULL, '2019-06-21 15:56:12', '2019-06-21 15:56:21');
INSERT INTO `orders` VALUES (391, '2019062100040611', 12, 15, 179.70, 1, 0, 20, '2019-06-21 15:56:51', NULL, NULL, NULL, '2019-06-21 15:56:46', '2019-06-21 15:56:51');
INSERT INTO `orders` VALUES (392, '2019062100040711', 12, 15, 179.70, 1, 0, 20, '2019-06-21 15:58:21', NULL, NULL, NULL, '2019-06-21 15:58:17', '2019-06-21 15:58:21');
INSERT INTO `orders` VALUES (393, '2019062100040811', 12, 15, 44.40, 1, 0, 20, '2019-06-21 16:18:00', NULL, NULL, NULL, '2019-06-21 16:11:52', '2019-06-21 16:18:00');
INSERT INTO `orders` VALUES (394, '2019062100040911', 12, 15, 100.80, 1, 0, 20, '2019-06-21 16:18:16', NULL, NULL, NULL, '2019-06-21 16:18:11', '2019-06-21 16:18:16');
INSERT INTO `orders` VALUES (395, '2019062100041011', 12, 15, 239.60, 1, 0, 20, '2019-06-21 16:20:47', NULL, NULL, NULL, '2019-06-21 16:20:43', '2019-06-21 16:20:47');
INSERT INTO `orders` VALUES (396, '2019062100041111', 12, 15, 179.70, 1, 0, 20, '2019-06-21 16:21:38', NULL, NULL, NULL, '2019-06-21 16:21:34', '2019-06-21 16:21:38');
INSERT INTO `orders` VALUES (397, '2019062100041211', 12, 15, 32.40, 1, 0, 20, '2019-06-21 16:23:02', NULL, NULL, NULL, '2019-06-21 16:22:59', '2019-06-21 16:23:02');
INSERT INTO `orders` VALUES (398, '2019062100041311', 12, 15, 32.40, 1, 0, 20, '2019-06-21 16:23:22', NULL, NULL, NULL, '2019-06-21 16:23:19', '2019-06-21 16:23:22');
INSERT INTO `orders` VALUES (399, '2019062100041411', 12, 15, 22.50, 1, 0, 20, '2019-06-21 16:25:10', NULL, NULL, NULL, '2019-06-21 16:25:06', '2019-06-21 16:25:10');
INSERT INTO `orders` VALUES (400, '2019062100041511', 12, 15, 32.40, 1, 0, 20, '2019-06-21 16:40:08', NULL, NULL, NULL, '2019-06-21 16:40:03', '2019-06-21 16:40:08');
INSERT INTO `orders` VALUES (401, '2019062100041611', 12, 15, 2051.40, NULL, 0, 10, NULL, NULL, NULL, NULL, '2019-06-21 20:37:25', '2019-06-21 20:37:25');
INSERT INTO `orders` VALUES (402, '2019062100041711', 12, 15, 32.40, 1, 0, 20, '2019-06-21 20:38:12', NULL, NULL, NULL, '2019-06-21 20:38:07', '2019-06-21 20:38:12');
INSERT INTO `orders` VALUES (403, '2019062100041811', 12, 15, 2081.00, 1, 0, 20, '2019-06-21 20:46:43', NULL, NULL, NULL, '2019-06-21 20:46:14', '2019-06-21 20:46:43');

-- ----------------------------
-- Table structure for sequence_info
-- ----------------------------
DROP TABLE IF EXISTS `sequence_info`;
CREATE TABLE `sequence_info`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `current_value` int(11) NOT NULL DEFAULT 0,
  `step` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sequence_info
-- ----------------------------
INSERT INTO `sequence_info` VALUES ('order_info', 419, 1);

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户 id',
  `receiver_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货姓名',
  `receiver_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货固定电话',
  `receiver_province` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '省份',
  `receiver_city` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '城市',
  `receiver_district` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '区/县',
  `receiver_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '详细地址',
  `receiver_zip` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮编',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_address
-- ----------------------------
INSERT INTO `user_address` VALUES (6, 7, '曹鹏', '187723213132', '陕西', '宝鸡市', '陇　县', '温水镇', '434100');
INSERT INTO `user_address` VALUES (7, 8, '杨亚宸', '18772812230', '湖北', '荆州市', '江陵县', '江陵县', '434100');
INSERT INTO `user_address` VALUES (13, 6, 'Bruce Lee', '12313123', '湖北', '黄石市', '西塞山区', '3123131231', '434100');
INSERT INTO `user_address` VALUES (14, 11, '杨亚陈', '1877283223', '湖北', '武汉市', '乔口区', '的撒旦水水大大是', '434100');
INSERT INTO `user_address` VALUES (15, 12, '杨亚陈', '18772812230', '湖北', '荆州市', '江陵县', '郝穴镇', '434100');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gender` int(1) NOT NULL DEFAULT 0,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `age` int(3) NOT NULL DEFAULT 0,
  `telphone` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `email` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `register_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `role` int(1) NOT NULL DEFAULT 0 COMMENT '1为用户，0为管理员',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_telphone`(`telphone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (2, 1, '李芳', 21, '18772812231', '102500556@qq.com', '2019-05-05 08:28:48', 0);
INSERT INTO `user_info` VALUES (3, 1, '杨亚宸', 23, '187728122222', '747378285@qq.com', '2019-05-05 19:47:34', 0);
INSERT INTO `user_info` VALUES (4, 1, '你好', 12, '12345678911', '747378285@qq.com', '2019-05-05 20:08:20', 0);
INSERT INTO `user_info` VALUES (5, 0, '纸飞机', 22, '18772812230', '747378285@qq.com', '2019-05-12 19:06:23', 0);
INSERT INTO `user_info` VALUES (6, 0, '天天', 21, '1111111111', '747378285@qq.com', '2019-05-13 09:40:47', 0);
INSERT INTO `user_info` VALUES (7, 0, '杨亚宸', 323, '22222222222', '1111111111', '2019-05-19 21:41:05', 0);
INSERT INTO `user_info` VALUES (10, 0, '杨亚宸', 23, '187728111111', '1111111111', '2019-05-20 08:57:48', 0);
INSERT INTO `user_info` VALUES (12, 0, '管理员', 23, '12345678', '', '2019-05-19 21:41:05', 1);
INSERT INTO `user_info` VALUES (13, 0, '杨亚宸', 23, '123456789', '1111111111', '2019-06-20 20:24:43', 0);
INSERT INTO `user_info` VALUES (14, 0, '杨亚宸', 32, '111111', '1111111111', '2019-06-20 20:25:14', 0);
INSERT INTO `user_info` VALUES (15, 0, '杨亚宸', 23, '123456123456', '747378285@qq.com', '2019-06-20 20:31:10', 0);

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `encrpt_password` varchar(55) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES (2, '123456', 2);
INSERT INTO `user_password` VALUES (3, '4QrcOUm6Wau+VuBX8g+IPg==', 3);
INSERT INTO `user_password` VALUES (4, 'ysowC997L0Rh6OQjPoPlEg==', 4);
INSERT INTO `user_password` VALUES (5, 'lueSGJZetyySpUndWjMBEg==', 5);
INSERT INTO `user_password` VALUES (6, 'lueSGJZetyySpUndWjMBEg==', 6);
INSERT INTO `user_password` VALUES (7, '4QrcOUm6Wau+VuBX8g+IPg==', 7);
INSERT INTO `user_password` VALUES (8, '4QrcOUm6Wau+VuBX8g+IPg==', 10);
INSERT INTO `user_password` VALUES (9, '4QrcOUm6Wau+VuBX8g+IPg==', 12);
INSERT INTO `user_password` VALUES (10, 'lueSGJZetyySpUndWjMBEg==', 13);
INSERT INTO `user_password` VALUES (11, '4QrcOUm6Wau+VuBX8g+IPg==', 14);
INSERT INTO `user_password` VALUES (12, '4QrcOUm6Wau+VuBX8g+IPg==', 15);

SET FOREIGN_KEY_CHECKS = 1;
