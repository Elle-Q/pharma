package com.sanofi.pharma.service;

import com.sanofi.pharma.entity.Patient;
import com.sanofi.pharma.mapper.PatientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author ELLE Q
 * @since 2025-10-24
 */
@Component
@RequiredArgsConstructor
public class PatientService {

    private final PatientMapper patientMapper;

    public Long insert(Patient patient) {
        return patientMapper.insert(patient);
    }

    public Patient getById(Long id) {
        return patientMapper.getById(id);
    }
}
