package com.awp.periodLog.service;

import com.awp.periodLog.dto.PeriodLogRequest;
import com.awp.periodLog.dto.PeriodLogResponse;
import com.awp.periodLog.dto.PeriodLogResponsePage;
import com.awp.periodLog.entity.PeriodLog;
import com.awp.periodLog.exception.periodDomain.PeriodLogNotFoundException;
import com.awp.periodLog.exception.periodDomain.PeriodLogOverlapException;
import com.awp.periodLog.exception.periodDomain.InvalidPeriodDateRangeException;
import com.awp.periodLog.mapper.PeriodLogMapper;
import com.awp.periodLog.repository.PeriodLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PeriodLogServiceImpl implements PeriodLogService {

    private final PeriodLogRepository periodLogRepository;
    private final PeriodLogMapper periodLogMapper;

    @Override
    @Transactional
    public PeriodLogResponse createPeriodLog(Long authenticatedUserId, PeriodLogRequest request) {
        if (request.startDate().isAfter(request.endDate())) {
            throw new InvalidPeriodDateRangeException("Period start date cannot be after period end date.");
        }
        if (periodLogRepository.existsByStartDateAndEndDate(request.startDate(),request.endDate())) {
            throw new PeriodLogOverlapException("Period log already exists for the given date range.");
        }

        PeriodLog periodLog = periodLogMapper.toEntity(request);
        periodLog.setAuthenticatedUserId(authenticatedUserId);
        PeriodLog savedPeriodLog = periodLogRepository.save(periodLog);
        return periodLogMapper.toResponse(savedPeriodLog);
    }

    @Override
    public PeriodLogResponse getPeriodLogById(Long  periodLogId,Long authenticatedUserId) {
        PeriodLog periodLog = periodLogRepository.findByIdAndAuthenticatedUserId(periodLogId,authenticatedUserId)
                .orElseThrow(() -> new PeriodLogNotFoundException("No period logs found!"));

        if (!periodLog.getAuthenticatedUserId().equals(authenticatedUserId)) {
            throw new AccessDeniedException("Access denied!, You are not authorized to access this period log.");
        }
        return periodLogMapper.toResponse(periodLog);
    }

    @Override
    public PeriodLogResponsePage getAllPeriodLogs(Long authenticatedUserId, int pageNo, int pageSize, String sortBy) {

        Sort sorted= Sort.by(sortBy).ascending();
        PageRequest pageRequest = PageRequest.of(pageNo,pageSize,sorted);
        Page<PeriodLog> periodLogsPage = periodLogRepository.findAllByAuthenticatedUserId(authenticatedUserId,pageRequest);

        log.info("User auth id is {}", authenticatedUserId);
        log.info("Logs , {}",periodLogsPage.getContent().size());

        List<PeriodLogResponse> periodLogs = periodLogsPage.getContent().stream().map(periodLogMapper::toResponse).toList();

        if(periodLogs.isEmpty()){
            throw new PeriodLogNotFoundException("No period logs found!");
        }
        return PeriodLogResponsePage.builder()
                .content(periodLogs)
                .pageNo(periodLogsPage.getNumber())
                .pageSize(periodLogsPage.getSize())
                .totalPages(periodLogsPage.getTotalPages())
                .lastPage(periodLogsPage.isLast())
                .build();
    }

    @Transactional
    @Override
    public PeriodLogResponse updatePeriodLog(Long periodLogId, Long authenticatedUserId , PeriodLogRequest request) {

        PeriodLog periodLog = periodLogRepository
                .findByIdAndAuthenticatedUserId(periodLogId, authenticatedUserId)
                .orElseThrow(() -> new AccessDeniedException("Access denied!, You are not authorized to access this period log."));

        periodLog.setNotes(request.notes());
        PeriodLog updatedPeriodLog = periodLogRepository.save(periodLog);
        return PeriodLogResponse.builder()
                .id(updatedPeriodLog.getId())
                .startDate(updatedPeriodLog.getStartDate())
                .endDate(updatedPeriodLog.getEndDate())
                .flowIntensity(updatedPeriodLog.getFlowIntensity())
                .notes(updatedPeriodLog.getNotes())
                .createdAt(updatedPeriodLog.getCreatedAt())
                .updatedAt(updatedPeriodLog.getUpdatedAt())
                .build();
    }

    @Transactional
    @Override
    public void deletePeriodLog(Long periodLogId, Long authenticatedUserId) {

        PeriodLog periodLog = periodLogRepository.findByIdAndAuthenticatedUserId(periodLogId, authenticatedUserId)
                .orElseThrow(() -> new AccessDeniedException("Access denied!, You are not authorized to access this period log."));

        periodLogRepository.delete(periodLog);

    }
}
