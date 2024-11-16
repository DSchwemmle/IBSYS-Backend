package com.ibsys.ibsysbackend.dto;

import com.ibsys.ibsysbackend.entities.WaitingListStockWaitingList;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WaitingListStockWaitingListDto {
    private int period;
    private int orderNumber;
    private int firstBatch;
    private int lastBatch;
    private int item;
    private int amount;
    private int timeNeed;

    public WaitingListStockWaitingList toWaitingListStockWaitingList() {
        return WaitingListStockWaitingList.builder()
                .period(period)
                .orderNumber(orderNumber)
                .firstBatch(firstBatch)
                .lastBatch(lastBatch)
                .item(item)
                .amount(amount)
                .timeNeed(timeNeed)
                .build();
    }
}
