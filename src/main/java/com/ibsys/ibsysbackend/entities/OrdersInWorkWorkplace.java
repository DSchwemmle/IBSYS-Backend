package com.ibsys.ibsysbackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "orders_in_work_workplace")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdersInWorkWorkplace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    private UUID uuid;

    private int id;
    @Column(name = "period")
    private int period;
    @Column(name = "order_number")
    private int orderNumber;
    @Column(name = "batch")
    private int batch;
    @Column(name = "item")
    private int item;
    @Column(name = "amount")
    private int amount;
    @Column(name = "timeneed")
    private long timeneed;
}