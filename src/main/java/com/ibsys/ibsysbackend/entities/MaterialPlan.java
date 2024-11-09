package com.ibsys.ibsysbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MaterialPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private UUID id;

    private String article;

    @JsonProperty("articleNumber")
    private Integer articleNumber;

    @JsonProperty("deliveryTime")
    private Double deliveryTime;

    @JsonProperty("deviation")
    private Double deviation;

    @JsonProperty("usedInP1")
    private Integer usedInP1;

    @JsonProperty("usedInP2")
    private Integer usedInP2;

    @JsonProperty("usedInP3")
    private Integer usedInP3;

    @JsonProperty("discountQuantity")
    private Integer discountQuantity;

    @JsonProperty("initialStockInPeriodN")
    private Integer initialStockInPeriodN;

    @JsonProperty("grossRequirementBasedOnProductionProgramN")
    private Integer grossRequirementBasedOnProductionProgramN;

    @JsonProperty("grossRequirementBasedOnProductionProgramNPlus1")
    private Integer grossRequirementBasedOnProductionProgramNPlus1;

    @JsonProperty("grossRequirementBasedOnProductionProgramNPlus2")
    private Integer grossRequirementBasedOnProductionProgramNPlus2;

    @JsonProperty("grossRequirementBasedOnProductionProgramNPlus3")
    private Integer grossRequirementBasedOnProductionProgramNPlus3;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("orderType")
    private String orderType;
}