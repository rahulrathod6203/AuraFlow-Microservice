package com.awp.periodLog.controller;

import com.awp.periodLog.config.UserPrincipal;
import com.awp.periodLog.dto.PeriodLogRequest;
import com.awp.periodLog.dto.PeriodLogResponse;
import com.awp.periodLog.dto.PeriodLogResponsePage;
import com.awp.periodLog.service.PeriodLogService;
import com.awp.periodLog.util.AppConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/period-logs")
@RequiredArgsConstructor
@Slf4j
public class PeriodLogController {

    private final PeriodLogService periodLogService;

    @GetMapping
    public ResponseEntity<PeriodLogResponsePage> getAllPeriodLogs(
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NO) int pageNo,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(
                periodLogService.getAllPeriodLogs(userPrincipal.getUserId(), pageNo, pageSize, sortBy)
        );
    }

    @PostMapping
    public ResponseEntity<PeriodLogResponse> createPeriodLog(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                             @RequestBody PeriodLogRequest request) {
        PeriodLogResponse periodLog = periodLogService.createPeriodLog(userPrincipal.getUserId(), request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build()
                .toUri();
        return ResponseEntity.created(location).body(periodLog);
    }

    @GetMapping("/{periodLogId}")
    public ResponseEntity<PeriodLogResponse> getPeriodLogById(@PathVariable Long periodLogId,
                                                              @AuthenticationPrincipal UserPrincipal userPrincipal){
        return ResponseEntity.ok(periodLogService.getPeriodLogById(periodLogId, userPrincipal.getUserId()));
    }

    @PutMapping("/{periodLogId}")
    public ResponseEntity<PeriodLogResponse> updatePeriodLog(
                                @PathVariable Long periodLogId,
                                @AuthenticationPrincipal UserPrincipal userPrincipal,
                                @RequestBody PeriodLogRequest request) {
        PeriodLogResponse updatedPeriodLog =
                            periodLogService.updatePeriodLog(periodLogId, userPrincipal.getUserId(), request);
        return ResponseEntity.accepted().body(updatedPeriodLog);
    }

    @DeleteMapping("/{periodLogId}")
    public ResponseEntity<String> deletePeriodLog(@PathVariable Long periodLogId,
                                                  @AuthenticationPrincipal UserPrincipal userPrincipal) {
        periodLogService.deletePeriodLog(periodLogId, userPrincipal.getUserId());
        return ResponseEntity.ok("Period Log deleted successfully!");
    }

}