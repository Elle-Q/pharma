package com.sanofi.pharma.service;

import com.sanofi.pharma.dto.common.PrescriptionDetail;
import com.sanofi.pharma.dto.request.PrescriptionItemRequest;
import com.sanofi.pharma.dto.request.PrescriptionSubmitRequest;
import com.sanofi.pharma.dto.vo.PrescriptionProcessResult;
import com.sanofi.pharma.enums.FailureCode;
import com.sanofi.pharma.enums.OperationCode;
import com.sanofi.pharma.dto.common.FailureReason;
import com.sanofi.pharma.service.validation.ValidationContext;
import com.sanofi.pharma.service.validation.ValidationResult;
import com.sanofi.pharma.service.validation.validator.Validator;
import com.sanofi.pharma.service.validation.validator.ValidatorChainBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PrescriptionProcessServiceTest {
    @Mock
    private PrescriptionService prescriptionService;
    @Mock
    private AuditLogService auditLogService;
    @Mock
    private ValidatorChainBuilder validatorChainBuilder;

    private PrescriptionProcessService prescriptionProcessService;
    private PrescriptionSubmitRequest request;

    @BeforeEach
    void setUp() {
        prescriptionProcessService = new PrescriptionProcessService(prescriptionService, auditLogService, validatorChainBuilder);
        createValidRequest();
    }

    @Test
    void submit_fail_when_any_of_validator_fails() {
        List<FailureReason> failureReasons = List.of(FailureReason.fail0(FailureCode.PRESCRIPTION_EXPIRED, request.getNumber()));
        ValidationResult validationResult = new ValidationResult();
        validationResult.setFailureReasons(failureReasons);

        Validator<PrescriptionDetail> mockValidator = mock(Validator.class);
        when(validatorChainBuilder.<PrescriptionDetail>buildValidationChain()).thenReturn(mockValidator);

        when(mockValidator.validate(any(PrescriptionDetail.class), any(ValidationContext.class)))
                .thenAnswer(invocation -> {
                    ValidationContext context = invocation.getArgument(1);
                    failureReasons.forEach(context::addFailureReason);
                    return validationResult;
                });

        PrescriptionProcessResult result = prescriptionProcessService.submit(request);

        assertThat(result.isSuccess()).isFalse();
        verify(prescriptionService, never()).submitPrescription(any());
        verify(auditLogService).createOnFail(eq(OperationCode.PRESCRIPTION_SUBMIT_FAIL), any(), any());

    }

    private void createValidRequest() {
        request = new PrescriptionSubmitRequest();
        request.setNumber("XXXX20251026001");
        request.setPharmacyId(1L);
        request.setDoctorName("Dr.Wang");
        request.setPatientId(1L);
        request.setPatientName("lll");
        request.setPharmacistName("Joshoa");
        request.setDiagnosis("Patient requires ongoing medication management and regular blood pressure monitoring.");
        request.setValidDays(5);
        request.setCreateTime(LocalDateTime.now());
        List<PrescriptionItemRequest> itemRequests = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            PrescriptionItemRequest itemRequest = new PrescriptionItemRequest();
            itemRequest.setDrugId(2L);
            itemRequest.setDrugName("Aspirin Enteric-coated Tablets");
            itemRequest.setDosage("0.5g");
            itemRequest.setAmount(20);
            itemRequest.setInstruction("Take one tablet daily in the morning");
            itemRequests.add(itemRequest);
        }
        request.setItems(itemRequests);
    }
}