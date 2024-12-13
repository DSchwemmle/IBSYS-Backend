package com.ibsys.ibsysbackend.dto;

import com.ibsys.ibsysbackend.entities.WaitingListWorkPlace;
import com.ibsys.ibsysbackend.entities.WorkPlace;
import com.ibsys.ibsysbackend.entities.WorkPlaceIMPORT;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WaitingListWorkplaceDto {
    private int period;
    private int order;
    private int firstbatch;
    private int lastbatch;
    private int item;
    private int amount;
    private String timeneed;
    private WorkPlaceIMPORT workplace;

    public WaitingListWorkPlace toWaitinglistWorkplace() {
        return WaitingListWorkPlace.builder()
                .period(period)
                .order(order)
                .firstbatch(firstbatch)
                .lastbatch(lastbatch)
                .item(item)
                .amount(amount)
                .timeneed(Integer.parseInt(timeneed))
                .workplace(workplace)
                .build();
    }
}
