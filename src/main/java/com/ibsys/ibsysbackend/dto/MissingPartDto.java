package com.ibsys.ibsysbackend.dto;

import lombok.*;

import java.util.List;
//This file could case error
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class MissingPartDto {
    private Integer id;
    private List<WaitingListStockWaitingListDto> workplace;
}
