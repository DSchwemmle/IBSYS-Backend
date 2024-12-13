package com.ibsys.ibsysbackend.dto;

/*import com.ibsys.ibsysbackend.entities.WorkPlace;
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
}*/

import com.ibsys.ibsysbackend.entities.WorkPlace;
import com.fasterxml.jackson.annotation.JsonProperty;

public record WorkPlaceDto(
        Integer idFromXml,
        Integer period,
        //@JsonProperty("setupevents")
        Integer setupEvents,
        //@JsonProperty("idletime")
        Integer idleTime,
        //@JsonProperty("wageidletimecosts")
        Double wageIdleTimeCosts,
        //@JsonProperty("wagecosts")
        Double wageCosts,
        //@JsonProperty("machineidletimecosts")
        Double machineIdleTimeCosts,
        // Needed for other wrapper classes
        //@JsonProperty("timeneed")
        Integer timeNeed,
        @JsonProperty("order")
        Integer appOrder,
        Integer batch,
        Integer item,
        Integer amount,
        Integer number,
        Boolean isMissingPart,
        Boolean isIdleTimeCosts,
        Boolean isWaitingListWorkStations,
        Boolean isWaitingListStock,
        Boolean isOrdersInWork
) {
    public WorkPlace toWorkPlace() {
        return WorkPlace.builder()
                .id(null)
                .idFromXml(idFromXml)
                .period(period)
                .setupEvents(setupEvents)
                .idleTime(idleTime)
                .wageIdleTimeCosts(wageIdleTimeCosts)
                .wageCosts(wageCosts)
                .machineIdleTimeCosts(machineIdleTimeCosts)
                .timeNeed(timeNeed)
                .appOrder(appOrder)
                .batch(batch)
                .item(item)
                .amount(amount)
                .number(number)
                .isMissingPart(isMissingPart)
                .isIdleTimeCosts(isIdleTimeCosts)
                .isWaitingListWorkStations(isWaitingListWorkStations)
                .isWaitingListStock(isWaitingListStock)
                .isOrdersInWork(isOrdersInWork)
                .build();
    }
}