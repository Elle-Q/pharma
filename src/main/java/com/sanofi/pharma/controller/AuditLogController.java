package com.sanofi.pharma.controller;

import com.sanofi.pharma.dto.request.AuditLogSearchRequest;
import com.sanofi.pharma.dto.vo.AuditLogVO;
import com.sanofi.pharma.dto.vo.PharmaResponse;
import com.sanofi.pharma.entity.AuditLog;
import com.sanofi.pharma.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auditlogs")
public class AuditLogController {

    private final AuditLogService auditLogService;

    @PostMapping
    public PharmaResponse<?> searchAuditLog(@RequestBody AuditLogSearchRequest request) {
        List<AuditLog> auditLogList = auditLogService.findBy(request);
        // 转换为 VO 对象，包含解析后的 JSON 数据
        List<AuditLogVO> result = auditLogList.stream()
                .map(AuditLogVO::fromEntity)
                .collect(Collectors.toList());
        return PharmaResponse.ok(result);
    }


}
