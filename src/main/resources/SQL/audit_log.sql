-- table pharmacy

CREATE TABLE `pharma`.`audit_log`
(
    `id`              bigint                                                        NOT NULL AUTO_INCREMENT,
    `operation_type`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL,
    `prescription_id` int NULL DEFAULT NULL,
    `patient_id`      int                                                           NOT NULL,
    `pharmacy_id`     int                                                           NOT NULL,
    `operator_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `status`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL,
    `drugs_requested` json NULL,
    `drugs_dispensed` json NULL,
    `failure_reasons` json NULL,
    `create_time`     timestamp                                                     NOT NULL,
    `update_time`     timestamp                                                     NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX             `idx_patientid_pharmaid_status`(`patient_id` ASC, `pharmacy_id` ASC, `status` ASC) USING BTREE,
    INDEX             `idx_create_time`(`create_time` ASC) USING BTREE,
    INDEX             `idx_prescription_id`(`prescription_id` ASC) USING BTREE,
    INDEX             `idx_pharmacy_id`(`pharmacy_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;


-- mock data
INSERT INTO pharmacy (name, address, telephone, manager)
VALUES ('Beijing Union Medical Pharmacy', '1 Shuai Fu Yuan, Dongcheng District, Beijing', '010-69151188',
        'David Zhang'),
       ('Shanghai Huashan Pharmacy', '433 Huashan Road, Jingan District, Shanghai', '021-52889999', 'Lisa Li'),
       ('Guangzhou Zhongshan Pharmacy', '58 Zhongshan Er Road, Yuexiu District, Guangzhou', '020-87332233',
        'Michael Wang'),
       ('Shenzhen People''s Hospital Pharmacy', '1017 Dongmen North Road, Luohu District, Shenzhen', '0755-25533018',
        'Kevin Chen'),
       ('Chengdu Huaxi Pharmacy', '37 Guoxue Alley, Wuhou District, Chengdu', '028-85422114', 'Sarah Liu'),
       ('Wuhan Tongji Pharmacy', '1095 Jiefang Avenue, Qiaokou District, Wuhan', '027-83662688', 'Robert Zhao'),
       ('Nanjing Gulou Pharmacy', '321 Zhongshan Road, Gulou District, Nanjing', '025-83106666', 'Amy Zhou'),
       ('Hangzhou Zheyi Pharmacy', '79 Qingchun Road, Shangcheng District, Hangzhou', '0571-87236114', 'Daniel Wu'),
       ('Xi''an Xijing Pharmacy', '127 Changle West Road, Xincheng District, Xi''an', '029-84775555', 'Jessica Zheng'),
       ('Chongqing Southwest Pharmacy', '30 Gaotanyan Street, Shapingba District, Chongqing', '023-68754147',
        'Steven Sun'),
       ('Tianjin General Hospital Pharmacy', '154 Anshan Road, Heping District, Tianjin', '022-60362255', 'Brian Qian'),
       ('Shenyang CMU Pharmacy', '155 Nanjing North Street, Heping District, Shenyang', '024-96615', 'Mark Ma');

