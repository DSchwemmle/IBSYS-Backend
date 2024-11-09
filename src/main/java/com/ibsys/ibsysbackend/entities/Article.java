package com.ibsys.ibsysbackend.entities;

import jakarta.persistence.*;
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
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    private Integer id;
    private Integer amount;
    //@JsonProperty("startamount")
    private Double startAmount;
    private Double pct;
    private Double price;
    //@JsonProperty("stockvalue")
    private Double stockValue;
    private Integer period;

    private Integer geplanterSicherheitsbestand;

    @Column(name = "warteschlange")
    private Integer warteschlange;

    @Column(name = "zusaetzliche_produktionsauftraege")
    private Integer zuesaetlicheProduktionsauftaege;

    // Bezeichnung des Artikels
    private String number;

    private String description;

    // Produkt bei dem der Artikel verwendet wird. (P1, P2, und P3)
    private String usedFor;
}