package com.ibsys.ibsysbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CycleTimes {

    @JsonProperty("startedorders")
    private Integer startedOrders;

    @JsonProperty("waitingorders")
    private Integer waitingOrders;

    private List<AppOrder> orders;
}