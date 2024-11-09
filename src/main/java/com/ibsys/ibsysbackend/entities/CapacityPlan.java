package com.ibsys.ibsysbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CapacityPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private UUID id;

    @JsonProperty("articleNumber")
    @Column(unique = true, nullable = false)
    private Integer articleNumber;

    @JsonProperty("article")
    @Column(unique = true, nullable = false)
    private String article;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("workplace1")
    private Integer workplace1;

    @JsonProperty("workplace2")
    private Integer workplace2;

    @JsonProperty("workplace3")
    private Integer workplace3;

    @JsonProperty("workplace4")
    private Integer workplace4;

    @JsonProperty("workplace6")
    private Integer workplace6;

    @JsonProperty("workplace7")
    private Integer workplace7;

    @JsonProperty("workplace8")
    private Integer workplace8;

    @JsonProperty("workplace9")
    private Integer workplace9;

    @JsonProperty("workplace10")
    private Integer workplace10;

    @JsonProperty("workplace11")
    private Integer workplace11;

    @JsonProperty("workplace12")
    private Integer workplace12;

    @JsonProperty("workplace13")
    private Integer workplace13;

    @JsonProperty("workplace14")
    private Integer workplace14;

    @JsonProperty("workplace15")
    private Integer workplace15;
}