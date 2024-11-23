package com.ibsys.ibsysbackend.dto;

import com.ibsys.ibsysbackend.dto.WaitingListWorkplaceDto;
import com.ibsys.ibsysbackend.entities.WorkPlace;
import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WorkPlaceDto {
    private int id;
    private int setupevents;
    private int idletime;
    private double wageidletimecosts;
    private double wagecosts;
    private double machineidletimecosts;
    private int timeneed;
    private List<WaitingListWorkplaceDto> waitinglist;
    private int period;
    private int order;
    private int batch;
    private int item;
    private int amount;

    public WorkPlace toWorkPlace() {
        return WorkPlace.builder()
                .id(id)
                .setupEvents(setupevents)
                .idleTime(idletime)
                .wageIdleTimeCosts(wageidletimecosts)
                .wageCosts(wagecosts)
                .machineIdleTimeCosts(machineidletimecosts)
                .timeNeed(timeneed)
                .period(period)
                .appOrder(order)
                .batch(batch)
                .item(item)
                .amount(amount)
                .build();
    }
}