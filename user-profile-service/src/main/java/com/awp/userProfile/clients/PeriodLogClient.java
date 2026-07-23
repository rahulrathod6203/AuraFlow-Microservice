package com.awp.userProfile.clients;

import com.awp.userProfile.dto.PeriodLogResponsePage;
import com.awp.userProfile.util.AppConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "period-log-service")
public interface PeriodLogClient {

    @GetMapping("/api/v1/period-logs")
    public ResponseEntity<PeriodLogResponsePage> getAllPeriodLogs(
            @RequestParam Long userId,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NO) int pageNo,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy
    );
}
