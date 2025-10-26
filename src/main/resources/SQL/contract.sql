
-- ----------------------------
-- Table structure for pharmacy_drug_allocation
-- ----------------------------
    DROP TABLE IF EXISTS `pharmacy_drug_allocation`;
CREATE TABLE `contract`  (
                             `contract_id` bigint NOT NULL,
                             `pharmacy_id` bigint NOT NULL,
                             `drug_id` bigint NOT NULL,
                             `allocated_amount` int NOT NULL,
                             `used_amount` int NOT NULL,
                             `status` smallint NOT NULL COMMENT '0-inactive  1-active',
                             `end_time` timestamp NOT NULL COMMENT 'contract end time',
                             `create_time` timestamp NOT NULL,
                             `update_time` timestamp NOT NULL,
                             `version` int NOT NULL DEFAULT 1 COMMENT 'optimistic lock filed',
                             INDEX `idx_pharmacyid_status`(`pharmacy_id` ASC, `status` ASC) USING BTREE,
                             INDEX `idx_drugid_status`(`drug_id` ASC, `status` ASC) USING BTREE,
                             UNIQUE INDEX `idx_pharmacyid_drug_id`(`pharmacy_id` ASC, `drug_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of contract
-- ----------------------------
INSERT INTO `pharmacy_drug_allocation` VALUES (1, 1, 1, 2000, 5, 1, '2027-01-25 09:12:15', '2025-10-25 09:12:32', '2025-10-25 09:12:36', 2);
INSERT INTO `pharmacy_drug_allocation` VALUES (4, 1, 2, 1000, 250, 1, '2027-02-01 11:16:15', '2025-10-25 11:16:19', '2025-10-25 11:16:22', 2);
INSERT INTO `pharmacy_drug_allocation` VALUES (5, 1, 3, 2000, 500, 1, '2028-11-01 11:19:04', '2025-10-25 11:19:09', '2025-10-25 11:19:12', 1);
INSERT INTO `pharmacy_drug_allocation` VALUES (6, 1, 4, 1000, 3, 1, '2028-01-01 16:46:12', '2025-10-26 16:46:18', '2025-10-26 16:46:20', 2);
INSERT INTO `pharmacy_drug_allocation` VALUES (2, 2, 1, 500, 100, 1, '2027-04-01 09:12:58', '2025-10-25 09:13:05', '2025-10-25 09:13:08', 1);
INSERT INTO `pharmacy_drug_allocation` VALUES (3, 3, 1, 1000, 900, 1, '2028-04-01 09:13:52', '2025-10-25 09:13:59', '2025-10-25 09:14:02', 1);