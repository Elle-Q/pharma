
-- ----------------------------
-- Table structure for drug
-- ----------------------------
DROP TABLE IF EXISTS `drug`;
CREATE TABLE `drug`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'drug name',
  `batch_number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `manufacturer_id` bigint NOT NULL,
  `expiry_date` date NOT NULL,
  `stock` int NOT NULL DEFAULT 0,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `create_time` timestamp NOT NULL,
  `update_time` timestamp NOT NULL,
  `version` int NOT NULL DEFAULT 1 COMMENT 'optimistic lock filed',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_batch_manufacturer`(`batch_number` ASC, `manufacturer_id` ASC) USING BTREE,
  INDEX `idx_manufacturer_ref`(`manufacturer_id` ASC) USING BTREE,
  INDEX `idx_batch_number`(`batch_number` ASC) USING BTREE,
  INDEX `idx_name`(`name` ASC) USING BTREE,
  INDEX `idx_expiry`(`expiry_date` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of drug
-- ----------------------------
INSERT INTO `drug` VALUES (1, 'Aspirin Enteric-coated Tablets', 'ASPI2025000001', 1, '2026-12-31', 2990, 'Used for pain relief, fever reduction, and anti-inflammatory purposes', '2025-10-24 23:18:43', '2025-10-24 23:18:43', 3);
INSERT INTO `drug` VALUES (2, 'Aspirin Enteric-coated Tablets', 'ASPI2025000001', 2, '2026-12-31', 1950, 'Used for pain relief, fever reduction, and anti-inflammatory purposes', '2025-10-24 23:18:43', '2025-10-24 23:18:43', 2);
INSERT INTO `drug` VALUES (3, 'Amoxicillin Capsules', 'AMOX2024000001', 2, '2024-08-15', 5000, 'Broad-spectrum antibiotic for bacterial infections', '2023-10-24 23:18:43', '2023-10-24 23:18:43', 1);
INSERT INTO `drug` VALUES (4, 'Metformin Hydrochloride Tablets', 'MET2025000001', 1, '2027-03-20', 7997, 'Oral anti-diabetic medication for type 2 diabetes', '2025-10-24 23:18:43', '2025-10-24 23:18:43', 2);
INSERT INTO `drug` VALUES (5, 'Atorvastatin Calcium Tablets', 'ATOR2025000001', 1, '2023-11-30', 6000, 'Cholesterol-lowering medication, statin class', '2022-10-24 23:18:43', '2022-10-24 23:18:43', 1);
INSERT INTO `drug` VALUES (6, 'Levothyroxine Sodium Tablets', 'LEVO2025000001', 3, '2027-01-15', 4500, 'Thyroid hormone replacement therapy', '2025-10-24 23:18:43', '2025-10-24 23:18:43', 1);
INSERT INTO `drug` VALUES (7, 'Losartan Potassium Tablets', 'LOS2025000001', 2, '2026-09-25', 7000, 'Angiotensin II receptor blocker for hypertension', '2025-10-24 23:18:43', '2025-10-24 23:18:43', 1);

SET FOREIGN_KEY_CHECKS = 1;
