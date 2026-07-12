package com.awp.periodLog.repository;

import com.awp.periodLog.entity.PeriodLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PeriodLogRepository extends JpaRepository<PeriodLog, Long> {

    Optional<PeriodLog> findByIdAndAuthenticatedUserId(Long  periodLogId, Long authenticatedUserId );

    Page<PeriodLog> findAllByAuthenticatedUserId(Long authenticatedUserId, Pageable pageable);

    boolean existsByStartDateAndEndDate(LocalDate startDate, LocalDate endDate);
}
