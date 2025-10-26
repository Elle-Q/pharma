
-- ----------------------------
-- Table structure for manufacturer
-- ----------------------------
DROP TABLE IF EXISTS `manufacturer`;
CREATE TABLE `manufacturer`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manufacturer
-- ----------------------------
INSERT INTO `manufacturer` VALUES (1, 'Bayer Healthcare');
INSERT INTO `manufacturer` VALUES (2, 'Pfizer');
INSERT INTO `manufacturer` VALUES (3, 'Lisinopril');

SET FOREIGN_KEY_CHECKS = 1;
