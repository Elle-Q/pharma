package com.sanofi.pharma.controller;

import com.sanofi.pharma.dto.vo.PharmaResponse;
import com.sanofi.pharma.dto.common.PharmacyContractDetail;
import com.sanofi.pharma.service.PhamarcyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pharmacies")
public class PhamarcyController {

    private final PhamarcyService phamarcyService;

    /**
     * get all Pharmacies with contracts
     * todo: paging (page size)
     */
    @GetMapping("with-contracts")
    public PharmaResponse<List<PharmacyContractDetail>> getPharmaciesWithContracts() {
        List<PharmacyContractDetail> result = phamarcyService.getPharmaciesWithContracts();
        return PharmaResponse.ok(result);
    }
}
