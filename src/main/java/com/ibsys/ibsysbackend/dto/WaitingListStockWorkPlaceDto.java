package com.ibsys.ibsysbackend.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WaitingListStockWorkPlaceDto {
    private List<WaitingListStockWorkPlaceDto> waitinglist;
    private int id;
    private int timeneed;
}