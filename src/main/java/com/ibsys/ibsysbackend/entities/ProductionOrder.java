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
public class ProductionOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private UUID id;

    @JsonProperty("article")
    private String article;

    @JsonProperty("articleNumber")
    private Integer articleNumber;

    // Vertriebswunsch
    @JsonProperty("saleOrder")
    private Integer saleOrder;

    // Warteschlange
    @JsonProperty("waitingQueue")
    private Integer waitingQueue;

    // Geplanter Sicherheitsbestand
    @JsonProperty("plannedSafetyStock")
    private Integer plannedSafetyStock;

    // Lagerbestand Vorperiode
    @JsonProperty("warehousePreviousPeriod")
    private Integer warehousePreviousPeriod;

    // Aufträge noch in Warteschlange
    @JsonProperty("ordersInWaitingQueue")
    private Integer ordersInWaitingQueue;

    // Aufträge in Bearbeitung
    @JsonProperty("workInProgress")
    private Integer workInProgress;

    // Produktionsauftrag
    @JsonProperty("productionOrder")
    private Integer productionOrder;

    // Verwendet für. Kann nur folgende Werte annehmen: "P1", "P2", "P3"
    @JsonProperty("usedFor")
    private String usedFor;
}