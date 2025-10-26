package com.sanofi.pharma.controller;

import com.sanofi.pharma.dto.vo.PharmaResponse;
import com.sanofi.pharma.dto.request.DrugCreateRequest;
import com.sanofi.pharma.entity.Drug;
import com.sanofi.pharma.service.DrugService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drugs")
public class DrugController {

    private final DrugService drugService;

    @PostMapping
    public PharmaResponse<Long> create(@Valid @RequestBody DrugCreateRequest request) {
        Long id = drugService.createDrug(request);
        return PharmaResponse.ok(id);
    }

    @GetMapping("/{id}")
    public PharmaResponse<Drug> getById(@PathVariable Long id) {
        Drug drug = drugService.getById(id);
        return PharmaResponse.ok(drug);
    }
}
