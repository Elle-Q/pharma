package com.sanofi.pharma.service;

import com.sanofi.pharma.dto.common.PrescriptionDetail;
import com.sanofi.pharma.dto.request.AuditLogSearchRequest;
import com.sanofi.pharma.entity.AuditLog;
import com.sanofi.pharma.entity.Prescription;
import com.sanofi.pharma.entity.PrescriptionItem;
import com.sanofi.pharma.enums.OperationCode;
import com.sanofi.pharma.mapper.AuditLogMapper;
import com.sanofi.pharma.dto.common.FailureReason;
import com.sanofi.pharma.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author ELLE Q
 * @since 2025-10-24
 */
@Component
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogMapper auditLogMapper;

    @Async
    public void createOnSuccess(OperationCode operationCode, PrescriptionDetail prescriptionDetail) {
        List<PrescriptionItem> items = prescriptionDetail.getItems();

        AuditLog auditLog = makeAuditLog(prescriptionDetail);
        auditLog.setOperationType(operationCode.name());
        if (operationCode == OperationCode.PRESCRIPTION_FULFILL_SUCCESS) {
            auditLog.setDrugsDispensed(JsonUtil.toJsonString(items));
        }
        auditLog.setStatus("success");
        auditLogMapper.insert(auditLog);
    }

    /**
     * TODO: PAGING
     */
    public List<AuditLog> findBy(AuditLogSearchRequest request) {
        return auditLogMapper.findBy(request);
    }

    public void createOnFail(OperationCode operationCode, PrescriptionDetail prescriptionDetail, List<FailureReason> failureReasons) {
        AuditLog auditLog = makeAuditLog(prescriptionDetail);
        auditLog.setOperationType(operationCode.name());
        auditLog.setFailureReasons(JsonUtil.toJsonString(failureReasons));
        auditLog.setStatus("fail");
        auditLogMapper.insert(auditLog);
    }

    public AuditLog makeAuditLog(PrescriptionDetail prescriptionDetail) {
        Prescription prescription = prescriptionDetail.getPrescription();
        List<PrescriptionItem> items = prescriptionDetail.getItems();
        AuditLog auditLog = new AuditLog();
        auditLog.setPrescriptionId(prescription.getId());
        auditLog.setPatientId(prescription.getPatientId());
        auditLog.setPharmacyId(prescription.getPharmacyId());
        auditLog.setOperatorName(prescription.getPharmacistName());
        auditLog.setDrugsRequested(JsonUtil.toJsonString(items));
        auditLog.setCreateTime(LocalDateTime.now());
        auditLog.setUpdateTime(LocalDateTime.now());
        return auditLog;
    }

}
