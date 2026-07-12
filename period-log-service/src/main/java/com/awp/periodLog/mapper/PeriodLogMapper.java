package com.awp.periodLog.mapper;

import com.awp.periodLog.dto.PeriodLogRequest;
import com.awp.periodLog.dto.PeriodLogResponse;
import com.awp.periodLog.entity.PeriodLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface PeriodLogMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "authenticatedUserId", ignore = true)
    PeriodLog toEntity(PeriodLogRequest periodLogRequest);

    PeriodLogResponse toResponse(PeriodLog periodLog);
}
