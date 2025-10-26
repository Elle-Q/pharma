
-- ----------------------------
-- Table structure for prescription_item
-- ----------------------------
DROP TABLE IF EXISTS `prescription_item`;
CREATE TABLE `prescription_item`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `prescription_id` bigint NOT NULL,
  `drug_id` bigint NOT NULL,
  `drug_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `dosage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount` int NOT NULL,
  `instruction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prescription_item
-- ----------------------------
INSERT INTO `prescription_item` VALUES (1, 7, 1, 'Aspirin Enteric-coated Tablets', '10mg/d', 5, 'Take one tablet daily in the morning');
INSERT INTO `prescription_item` VALUES (2, 9, 2, 'Aspirin Enteric-coated Tablets', '10mg/d', 50, 'Take one tablet daily in the morning');
INSERT INTO `prescription_item` VALUES (3, 9, 4, 'Metformin Hydrochloride Tablets', '20mg/d', 3, 'twice a day');

SET FOREIGN_KEY_CHECKS = 1;
