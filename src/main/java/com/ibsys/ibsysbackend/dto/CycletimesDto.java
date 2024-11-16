package com.ibsys.ibsysbackend.dto;

import com.ibsys.ibsysbackend.dto.AppOrderDto;
import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CycletimesDto {
    private Integer startedorders;
    private Integer waitingorders;
    private List<AppOrderDto> order;
}
