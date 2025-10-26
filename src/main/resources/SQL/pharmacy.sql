
-- ----------------------------
-- Table structure for pharmacy
-- ----------------------------
DROP TABLE IF EXISTS `pharmacy`;
CREATE TABLE `pharmacy`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `manager` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pharmacy
-- ----------------------------
INSERT INTO `pharmacy` VALUES (1, 'Beijing Union Medical Pharmacy', '1 Shuai Fu Yuan, Dongcheng District, Beijing', '010-69151188', 'David Zhang');
INSERT INTO `pharmacy` VALUES (2, 'Shanghai Huashan Pharmacy', '433 Huashan Road, Jingan District, Shanghai', '021-52889999', 'Lisa Li');
INSERT INTO `pharmacy` VALUES (3, 'Guangzhou Zhongshan Pharmacy', '58 Zhongshan Er Road, Yuexiu District, Guangzhou', '020-87332233', 'Michael Wang');
INSERT INTO `pharmacy` VALUES (4, 'Shenzhen People\'s Hospital Pharmacy', '1017 Dongmen North Road, Luohu District, Shenzhen', '0755-25533018', 'Kevin Chen');
INSERT INTO `pharmacy` VALUES (5, 'Chengdu Huaxi Pharmacy', '37 Guoxue Alley, Wuhou District, Chengdu', '028-85422114', 'Sarah Liu');
INSERT INTO `pharmacy` VALUES (6, 'Wuhan Tongji Pharmacy', '1095 Jiefang Avenue, Qiaokou District, Wuhan', '027-83662688', 'Robert Zhao');
INSERT INTO `pharmacy` VALUES (7, 'Nanjing Gulou Pharmacy', '321 Zhongshan Road, Gulou District, Nanjing', '025-83106666', 'Amy Zhou');
INSERT INTO `pharmacy` VALUES (8, 'Hangzhou Zheyi Pharmacy', '79 Qingchun Road, Shangcheng District, Hangzhou', '0571-87236114', 'Daniel Wu');
INSERT INTO `pharmacy` VALUES (9, 'Xi\'an Xijing Pharmacy', '127 Changle West Road, Xincheng District, Xi\'an', '029-84775555', 'Jessica Zheng');
INSERT INTO `pharmacy` VALUES (10, 'Chongqing Southwest Pharmacy', '30 Gaotanyan Street, Shapingba District, Chongqing', '023-68754147', 'Steven Sun');
INSERT INTO `pharmacy` VALUES (11, 'Tianjin General Hospital Pharmacy', '154 Anshan Road, Heping District, Tianjin', '022-60362255', 'Brian Qian');
INSERT INTO `pharmacy` VALUES (12, 'Shenyang CMU Pharmacy', '155 Nanjing North Street, Heping District, Shenyang', '024-96615', 'Mark Ma');

SET FOREIGN_KEY_CHECKS = 1;
