package com.ibsys.ibsysbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CapacityPlanSumUp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private UUID id;

    @JsonProperty("capacityRequirement")
    private Integer capacityRequirement;

    @JsonProperty("setupTime")
    private Integer setupTime;

    @JsonProperty("capacityRequirementBacklogPrevPeriod")
    private Integer capacityRequirementBacklogPrevPeriod;

    @JsonProperty("setupTimeBacklogPrevPeriod")
    private Integer setupTimeBacklogPrevPeriod;

    @JsonProperty("totalCapacityRequirements")
    private Integer totalCapacityRequirements;

    @JsonProperty("shifts")
    private Integer shifts;

    @JsonProperty("overtimeWeek")
    private Integer overtimeWeek;

    @JsonProperty("overtimeDay")
    private Integer overtimeDay;

    @JsonProperty("workPlaceNumber")
    private Integer workPlaceNumber;
}