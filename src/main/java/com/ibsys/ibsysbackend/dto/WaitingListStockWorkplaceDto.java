package com.ibsys.ibsysbackend.dto;

import lombok.*;

import java.util.List;
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WaitingListStockWorkplaceDto {
    private List<WaitingListStockWaitingListDto> waitingList;
    private int id;
    private int timeneed;
}
