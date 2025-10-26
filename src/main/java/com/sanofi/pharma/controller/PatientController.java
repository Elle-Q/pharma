package com.sanofi.pharma.controller;

import com.sanofi.pharma.entity.Patient;
import com.sanofi.pharma.dto.vo.PharmaResponse;
import com.sanofi.pharma.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public PharmaResponse<Long> create(@RequestBody Patient patient) {
        patientService.insert(patient);
        return PharmaResponse.ok(patient.getId());
    }
}
