package com.awp.periodLog.mapper;

import com.awp.periodLog.dto.PeriodLogRequest;
import com.awp.periodLog.dto.PeriodLogResponse;
import com.awp.periodLog.entity.PeriodLog;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-11T12:52:12+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class PeriodLogMapperImpl implements PeriodLogMapper {

    @Override
    public PeriodLog toEntity(PeriodLogRequest periodLogRequest) {
        if ( periodLogRequest == null ) {
            return null;
        }

        PeriodLog.PeriodLogBuilder periodLog = PeriodLog.builder();

        periodLog.startDate( periodLogRequest.startDate() );
        periodLog.endDate( periodLogRequest.endDate() );
        periodLog.flowIntensity( periodLogRequest.flowIntensity() );
        periodLog.notes( periodLogRequest.notes() );

        return periodLog.build();
    }

    @Override
    public PeriodLogResponse toResponse(PeriodLog periodLog) {
        if ( periodLog == null ) {
            return null;
        }

        PeriodLogResponse.PeriodLogResponseBuilder periodLogResponse = PeriodLogResponse.builder();

        periodLogResponse.id( periodLog.getId() );
        periodLogResponse.startDate( periodLog.getStartDate() );
        periodLogResponse.endDate( periodLog.getEndDate() );
        periodLogResponse.flowIntensity( periodLog.getFlowIntensity() );
        periodLogResponse.notes( periodLog.getNotes() );
        periodLogResponse.createdAt( periodLog.getCreatedAt() );
        periodLogResponse.updatedAt( periodLog.getUpdatedAt() );

        return periodLogResponse.build();
    }
}
