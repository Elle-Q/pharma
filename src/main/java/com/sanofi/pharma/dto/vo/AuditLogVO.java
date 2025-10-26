package com.sanofi.pharma.dto.vo;

import com.sanofi.pharma.entity.AuditLog;
import com.sanofi.pharma.entity.PrescriptionItem;
import com.sanofi.pharma.dto.common.FailureReason;
import com.sanofi.pharma.util.JsonUtil;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ELLE Q
 * @since 2025-10-26
 */
@Data
public class AuditLogVO {
    private String operationType;
    private Long prescriptionId;
    private Long patientId;
    private Long pharmacyId;
    private String operatorName;
    private String status;
    private List<PrescriptionItem> drugsRequested;
    private List<PrescriptionItem> drugsDispensed;
    private List<FailureReason> failureReasons;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static AuditLogVO fromEntity(AuditLog auditLog) {
        AuditLogVO vo = new AuditLogVO();
        vo.setOperationType(auditLog.getOperationType());
        vo.setPrescriptionId(auditLog.getPrescriptionId());
        vo.setPatientId(auditLog.getPatientId());
        vo.setPharmacyId(auditLog.getPharmacyId());
        vo.setOperatorName(auditLog.getOperatorName());
        vo.setStatus(auditLog.getStatus());
        vo.setCreateTime(auditLog.getCreateTime());
        vo.setUpdateTime(auditLog.getUpdateTime());

        // Parse Json
        vo.setDrugsRequested(JsonUtil.toList(auditLog.getDrugsRequested(), PrescriptionItem.class));
        vo.setDrugsDispensed(JsonUtil.toList(auditLog.getDrugsDispensed(), PrescriptionItem.class));
        vo.setFailureReasons(JsonUtil.toList(auditLog.getFailureReasons(), FailureReason.class));

        return vo;
    }
}
