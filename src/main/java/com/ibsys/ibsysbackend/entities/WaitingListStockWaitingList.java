package com.ibsys.ibsysbackend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "waitingliststock_waitinglist")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WaitingListStockWaitingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "period")
    private int period;
    @Column(name = "order_number")
    private int orderNumber;
    @Column(name = "first_batch")
    private int firstBatch;
    @Column(name = "last_batch")
    private int lastBatch;
    @Column(name = "item")
    private int item;
    @Column(name = "amount")
    private int amount;
    @Column(name = "time_need")
    private int timeNeed;
}
