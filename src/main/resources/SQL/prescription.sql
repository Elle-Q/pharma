
-- ----------------------------
-- Table structure for prescription
-- ----------------------------
DROP TABLE IF EXISTS `prescription`;
CREATE TABLE `prescription`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `pharmacy_id` bigint NOT NULL,
  `doctor_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `pharmacist_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `patient_id` bigint NOT NULL,
  `diagnosis` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `expire_time` timestamp NOT NULL,
  `fulfilled_time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL,
  `update_time` timestamp NOT NULL,
  `version` int NOT NULL DEFAULT 1 COMMENT 'optimistic lock filed',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_prescription_number`(`number` ASC) USING BTREE,
  INDEX `idx_patient_id_status`(`patient_id` ASC, `status` ASC) USING BTREE,
  INDEX `idx_pharmacy_status`(`pharmacy_id` ASC, `status` ASC) USING BTREE,
  INDEX `idx_expiry_status`(`status` ASC, `expire_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prescription
-- ----------------------------
INSERT INTO `prescription` VALUES (7, 'XXXX202510250001', 1, 'Dr.Ludwig', 'Joshoa', 1, 'Hypertension stage 1 with mild symptoms. Patient requires ongoing medication management and regular blood pressure monitoring.', 'FULFILLED', '2025-10-30 10:30:00', NULL, '2025-10-26 14:57:35', '2025-10-26 16:43:56', 3);
INSERT INTO `prescription` VALUES (9, 'XXXX202510250002', 1, 'Dr.Wang', 'Joshoa', 2, 'Patient requires ongoing medication management and regular blood pressure monitoring.', 'FULFILLED', '2025-10-31 10:30:00', NULL, '2025-10-26 16:48:07', '2025-10-26 19:19:25', 2);

SET FOREIGN_KEY_CHECKS = 1;
