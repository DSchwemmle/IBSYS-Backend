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
public class SaleAndProductionProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private UUID id;

    @JsonProperty("pN")
    private Integer pN;

    @JsonProperty("pNPlusOne")
    private Integer pNPlusOne;

    @JsonProperty("pNPlusTwo")
    private Integer pNPlusTwo;

    @JsonProperty("pNPlusThree")
    private Integer pNPlusThree;

    // Only possible values = P1, P2 and P3
    @JsonProperty("article")
    private String article;
}