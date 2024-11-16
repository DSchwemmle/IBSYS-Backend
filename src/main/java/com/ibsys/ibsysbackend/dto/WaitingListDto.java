package com.ibsys.ibsysbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibsys.ibsysbackend.entities.WaitingList;

public record WaitingListDto(
        Integer period,
        @JsonProperty("order")
        Integer appOrder,
        //@JsonProperty("firstbatch")
        Integer firstBatch,
        //@JsonProperty("lastbatch")
        Integer lastBatch,
        Integer item,
        Integer amount,
        Integer timeNeed,
        Integer workPlaceId
) {
    public WaitingList toWaitingList() {
        return WaitingList.builder()
                .id(null)
                .period(period)
                .appOrder(appOrder)
                .firstBatch(firstBatch)
                .lastBatch(lastBatch)
                .item(item)
                .amount(amount)
                .timeNeed(timeNeed)
                .workPlaceId(workPlaceId)
                .build();
    }
}
