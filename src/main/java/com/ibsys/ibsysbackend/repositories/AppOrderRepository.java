package com.ibsys.ibsysbackend.repositories;

import com.ibsys.ibsysbackend.entities.AppOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppOrderRepository extends JpaRepository<AppOrder, Integer> {

    List<AppOrder> findAppOrdersByPeriod(final Integer period);

    List<AppOrder> findAppOrdersByIsInwardStockMovement(final Boolean isInwardStockMovement);
}
