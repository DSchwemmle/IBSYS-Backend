package com.ibsys.ibsysbackend.dto;

import com.ibsys.ibsysbackend.entities.OrdersInWorkWorkplace;
import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrdersInWorkWorkplaceDTO {
    private int id;
    private int period;
    private int orderNumber;
    private int batch;
    private int item;
    private int amount;
    private long timeneed;

    public OrdersInWorkWorkplace toOrdersInWorkWorkplace() {
        return OrdersInWorkWorkplace.builder()
                .id(id)
                .period(period)
                .orderNumber(orderNumber)
                .batch(batch)
                .item(item)
                .amount(amount)
                .timeneed(timeneed)
                .build();
    }
}