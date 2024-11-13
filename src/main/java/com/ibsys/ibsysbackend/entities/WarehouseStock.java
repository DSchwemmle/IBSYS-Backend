package com.ibsys.ibsysbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lagerarticle
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseStock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer amount;

    // TODO: What is this ??? Ask Robert
    //  Remove if not master data
    private Double pct;

    private Double price;

    //@JsonProperty("stockvalue")
    private Double stockValue;

    private Integer period;

    private Integer articleId;
}
