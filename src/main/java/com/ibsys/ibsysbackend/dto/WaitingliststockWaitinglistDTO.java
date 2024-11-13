package com.ibsys.ibsysbackend.dto;

import com.ibsys.ibsysbackend.entities.WaitingliststockWaitinglist;
import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WaitingliststockWaitinglistDTO {
    private int period;
    private int orderNumber;
    private int firstbatch;
    private int lastbatch;
    private int item;
    private int amount;
    private int timeNeed;

    public WaitingliststockWaitinglist toWaitingliststockWaitinglist() {
        return WaitingliststockWaitinglist.builder()
                .period(period)
                .orderNumber(orderNumber)
                .firstbatch(firstbatch)
                .firstbatch(lastbatch)
                .item(item)
                .amount(amount)
                .timeNeed(timeNeed)
                .build();
    }
}
