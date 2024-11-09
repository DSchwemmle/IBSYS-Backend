package com.ibsys.ibsysbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "work_place")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer idFromXml;

    private Integer period;

    @JsonProperty("setupevents")
    private Integer setupEvents;

    @JsonProperty("idletime")
    private Integer idleTime;

    @JsonProperty("wageidletimecosts")
    private Double wageIdleTimeCosts;

    @JsonProperty("wagecosts")
    private Double wageCosts;

    @JsonProperty("machineidletimecosts")
    private Double machineIdleTimeCosts;

    // Needed for other wrapper classes
    @JsonProperty("timeneed")
    private Integer timeNeed;

    @JsonProperty("order")
    private Integer appOrder;

    private Integer batch;

    private Integer item;

    private Integer amount;

    private Integer number;

    private Boolean isMissingPart;

    private Boolean isIdleTimeCosts;

    private Boolean isWaitingListWorkStations;

    private Boolean isWaitingListStock;

    private Boolean isOrdersInWork;
}