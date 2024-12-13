package com.ibsys.ibsysbackend.dto;

import com.ibsys.ibsysbackend.entities.WorkPlace;
import com.ibsys.ibsysbackend.entities.WorkPlaceIMPORT;
import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WorkPlaceDtoIMPORT {
    private int id;
    private int setupevents;
    private int idletime;
    private String wageidletimecosts;
    private String wagecosts;
    private String machineidletimecosts;
    private String timeneed;
    private List<WaitingListWorkplaceDto> waitinglist;
    private int period;
    private int order;
    private int batch;
    private int item;
    private int amount;

    public WorkPlaceIMPORT toWorkPlace() {
        return WorkPlaceIMPORT.builder()
                .id(id)
                .setupevent(setupevents)
                .idletime(idletime)
                .wageidletimecosts(wageidletimecosts)
                .wagecosts(wagecosts)
                .machineidletimecosts(machineidletimecosts)
                .timeneed(Integer.parseInt(timeneed))
                .period(period)
                .orderNumber(order)
                .batch(batch)
                .item(item)
                .amount(amount)
                .build();
    }
}
