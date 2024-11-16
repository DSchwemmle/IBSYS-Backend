package com.ibsys.ibsysbackend.dto;

import com.ibsys.ibsysbackend.dto.WorkPlaceDto;
import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IdleTimeCostDto {
    private List<WorkPlaceDto> workplaceDTOS;
    private SumDto sumDTO;
}
