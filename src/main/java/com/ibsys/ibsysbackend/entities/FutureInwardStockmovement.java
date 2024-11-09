package com.ibsys.ibsysbackend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "future_inward_stockmovement_2")
public class FutureInwardStockmovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_period")
    private Integer orderperiod;
    private Integer mode;
    private Integer article;
    private Integer amount;
}