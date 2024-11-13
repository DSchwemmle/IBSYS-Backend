package com.ibsys.ibsysbackend.dto;

import com.ibsys.ibsysbackend.entities.WorkPlace;

public record WorkPlaceDto(
        Integer id,
        int setupevent,
        int idletime,
        String wageidletimecosts,
        String wagecosts,
        String machineidletimecosts,
        int timeneed,
        int period,
        int orderNumber,
        int batch,
        int item,
        int amount
) {

    public WorkPlace toWorkPlace() {
        return WorkPlace.builder()
                .id(id)
                .setupevent(setupevent)
                .idletime(idletime)
                .wageidletimecosts(wageidletimecosts)
                .wagecosts(wagecosts)
                .machineidletimecosts(machineidletimecosts)
                .timeneed(timeneed)
                .period(period)
                .orderNumber(orderNumber)
                .batch(batch)
                .item(item)
                .amount(amount)
                .build();
    }
}
