package com.ibsys.ibsysbackend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "waitinglist_workplace")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WaitingListWorkPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "period")
    private int period;
    @Column(name = "order_number")
    private int order;
    @Column(name = "firstbatch")
    private int firstbatch;
    @Column(name = "lastbatch")
    private int lastbatch;
    @Column(name = "item")
    private int item;
    @Column(name = "amount")
    private int amount;
    @Column(name = "timeneed")
    private int timeneed;
    @ManyToOne
    @JoinColumn(name = "workplace_fk")
    private WorkPlace workplace;
}