package com.awp.periodLog.service;

import com.awp.periodLog.dto.PeriodLogRequest;
import com.awp.periodLog.dto.PeriodLogResponse;
import com.awp.periodLog.dto.PeriodLogResponsePage;

public interface PeriodLogService {

    PeriodLogResponse createPeriodLog(Long authenticatedUserId, PeriodLogRequest request);

    PeriodLogResponse getPeriodLogById(Long periodLogId  , Long authenticatedUserId);

    PeriodLogResponsePage getAllPeriodLogs(Long authenticatedUserId, int pageNo, int pageSize, String sortBy);

    PeriodLogResponse updatePeriodLog(Long periodLogId  , Long authenticatedUserId, PeriodLogRequest request);

    void deletePeriodLog(Long periodLogId , Long authenticatedUserId);

}
