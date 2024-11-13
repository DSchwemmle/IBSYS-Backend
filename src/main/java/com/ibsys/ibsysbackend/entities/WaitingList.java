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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WaitingList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer period;

    @JsonProperty("order")
    private Integer appOrder;

    @JsonProperty("firstbatch")
    private Integer firstBatch;

    @JsonProperty("lastbatch")
    private Integer lastBatch;

    private Integer item;

    private Integer amount;

    private Integer timeNeed;

    private Integer workPlaceId;

}
