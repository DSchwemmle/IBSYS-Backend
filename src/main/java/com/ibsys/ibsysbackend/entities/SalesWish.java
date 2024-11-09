package com.ibsys.ibsysbackend.entities;

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
public class SalesWish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private UUID id;

    private Integer p1PNPlusOne;
    private Integer p1PNPlusTwo;
    private Integer p1PNPlusThree;

    private Integer p2PNPlusOne;
    private Integer p2PNPlusTwo;
    private Integer p2PNPlusThree;

    private Integer p3PNPlusOne;
    private Integer p3PNPlusTwo;
    private Integer p3PNPlusThree;
}